package com.appgate.socialmentions.entrypoint.api;

import com.appgate.socialmentions.DBService;
import com.appgate.socialmentions.FacebookAnalyzer;
import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import com.appgate.socialmentions.TweeterAnalyzer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/social-mentions")
//@RequestMapping("/Social-Mentions") dont break current contracts
class SocialMentionController {

    public static final String ANALYZED_TWEETS_TABLE = "analyzed_tweets";
    public static final String ANALYZED_FB_TABLE = "analyzed_fb_posts";

    private DBService dbService = new DBService("localhost", 5432); // database host and port

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/analyze",produces = MediaType.APPLICATION_JSON_VALUE)
   // @PostMapping("/AnalyzeSocialMention") dont break current contracts
    public String analyze(@Valid @RequestBody SocialMention socialMentionPayload) {

        double facebookCommentsScore = 0;
        boolean isFacebook = false;
        boolean isTweeter = false;


        double facebookScore = 0d; // General facebook score based on comments and message
        double tweeterScore = 0d; // General facebook score based on comments and message

        if (socialMentionPayload.getFacebookAccount() != null) {
            isFacebook = true;
        } else if (socialMentionPayload.getTweeterAccount() != null) {
            isTweeter = true;
        }


        

        if (isFacebook || isTweeter) {
            if (isFacebook) {
                socialMentionPayload.setMessage("facebookMessage: " + socialMentionPayload.getMessage());
                String comments = socialMentionPayload
                        .getFacebookComments()
                        .stream()
                        .reduce("", (h, c) -> h + " " + c);
                socialMentionPayload.setMessage(socialMentionPayload.getMessage() + " || comments: " +
                        comments);

            } else {
                socialMentionPayload.setMessage("tweeterMessage: " + socialMentionPayload.getMessage());
            }

            // Analyze and score facebook comments if present
            if (socialMentionPayload.getMessage().contains("comments:")) {
                facebookCommentsScore = FacebookAnalyzer.calculateFacebookCommentsScore(
                        socialMentionPayload
                                .getMessage()
                                .substring(socialMentionPayload.getMessage().indexOf("comments:"))
                );
            }
            if (facebookCommentsScore < 50d){
                facebookScore = Double.sum(facebookScore, -100d);
            }

            // Analyze facebook post (if facebook is already low then skip this analysis)
            if (isFacebook && facebookScore > -100) {
                facebookScore = FacebookAnalyzer.analyzePost(
                        socialMentionPayload.getMessage(),
                        socialMentionPayload.getFacebookAccount()
                );
                dbService.insertFBPost(
                        ANALYZED_FB_TABLE,
                        facebookScore,
                        socialMentionPayload.getMessage(),
                        socialMentionPayload.getFacebookAccount()
                );
            }

            // Analyze tweet
            if (isTweeter) {
                tweeterScore = TweeterAnalyzer.analyzeTweet(
                        socialMentionPayload.getMessage(),
                        socialMentionPayload.getTweeterUrl(),
                        socialMentionPayload.getTweeterAccount()
                );
                dbService.insertTweet(
                        ANALYZED_TWEETS_TABLE,
                        tweeterScore,
                        socialMentionPayload.getMessage(),
                        socialMentionPayload.getTweeterUrl(),
                        socialMentionPayload.getTweeterAccount()
                );
            }

            if (isFacebook) {
                if (facebookScore == -100d) {
                    return "HIGH_RISK";
                } else if (facebookScore > -100d && facebookScore < 50d) {
                    return "MEDIUM_RISK";
                } else if (facebookScore >= 50d) {
                    return "LOW_RISK";
                }
            }

            if (isTweeter) {
                if (tweeterScore >= -1 && tweeterScore <= -0.5d) {
                    return "HIGH_RISK";
                } else if (tweeterScore > -0.5d && tweeterScore < 0.7d) {
                    return "MEDIUM_RISK";
                } else if (facebookScore >= 0.7d) {
                    return "LOW_RISK";
                }
            }
        } else {
            return "Error, Tweeter or Facebook account must be present";
        }

        return socialMentionPayload.getMessage();
    }
}
