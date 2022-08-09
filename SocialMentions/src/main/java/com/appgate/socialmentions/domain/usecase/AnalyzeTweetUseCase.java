package com.appgate.socialmentions.domain.usecase;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import org.springframework.stereotype.Component;

@Component
public class AnalyzeTweetUseCase implements AnalyzeMention {


    @Override
    public String analyze(SocialMention mention) {
        return null;
    }
}
