package com.appgate.socialmentions.domain.usecase;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;

public interface AnalyzeMention {

    public String analyze(SocialMention mention);

}
