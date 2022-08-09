package com.appgate.socialmentions.domain.usecase;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import com.appgate.socialmentions.persistence.services.TweetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AnalyzePostUseCase implements AnalyzeMention {

    private TweetServiceImpl tweetService;

    @Override
    public String analyze(SocialMention socialMentionPayload) {

        socialMentionPayload.setMessage("facebookMessage: " + socialMentionPayload.getMessage());
        String comments = socialMentionPayload
                .getFacebookComments()
                .stream()
                .reduce("", (h, c) -> h + " " + c);
        socialMentionPayload.setMessage(socialMentionPayload.getMessage() + " || comments: " +
                comments);

        double facebookCommentsScore = FacebookAnalyzer.calculateFacebookCommentsScore(
                socialMentionPayload
                        .getMessage()
                        .substring(socialMentionPayload.getMessage().indexOf("comments:"))
        );

        double facebookScore = 0;

        if (facebookCommentsScore < 50d) {
            facebookScore = Double.sum(facebookScore, -100d);
        }

        // Analyze facebook post (if facebook is already low then skip this analysis)
        if (facebookScore > -100) {
            facebookScore = FacebookAnalyzer.analyzePost(
                    socialMentionPayload.getMessage(),
                    socialMentionPayload.getFacebookAccount()
            );

            this.tweetService.createMention(facebookScore, socialMentionPayload);

        }
        if (facebookScore == -100d) {
            return "HIGH_RISK";
        } else if (facebookScore > -100d && facebookScore < 50d) {
            return "MEDIUM_RISK";
        } else {
            return "LOW_RISK";
        }

    }
}
