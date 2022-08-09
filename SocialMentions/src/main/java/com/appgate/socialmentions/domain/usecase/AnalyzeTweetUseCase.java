package com.appgate.socialmentions.domain.usecase;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import com.appgate.socialmentions.persistence.services.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AnalyzeTweetUseCase implements AnalyzeMention {

    private PostServiceImpl postService;

    @Override
    public String analyze(SocialMention socialMentionPayload) {

        socialMentionPayload.setMessage("tweeterMessage: " + socialMentionPayload.getMessage());

        double tweeterScore = TweeterAnalyzer.analyzeTweet(
                socialMentionPayload.getMessage(),
                socialMentionPayload.getTweeterUrl(),
                socialMentionPayload.getTweeterAccount());

        this.postService.createMention(tweeterScore,socialMentionPayload);

        if (tweeterScore >= -1 && tweeterScore <= -0.5d) {
            return "HIGH_RISK";
        } else if (tweeterScore > -0.5d && tweeterScore < 0.7d) {
            return "MEDIUM_RISK";
        } else {
            return "LOW_RISK";
        }

    }
}
