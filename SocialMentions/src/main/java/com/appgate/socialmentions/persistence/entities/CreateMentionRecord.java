package com.appgate.socialmentions.persistence.entities;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;

import javax.persistence.Entity;

public interface CreateMentionRecord<T> {

    public T createMention (Double score, SocialMention socialMention) ;
}
