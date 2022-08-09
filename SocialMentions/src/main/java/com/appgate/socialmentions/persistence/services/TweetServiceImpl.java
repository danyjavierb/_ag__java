package com.appgate.socialmentions.persistence.services;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import com.appgate.socialmentions.persistence.entities.CreateMentionRecord;
import com.appgate.socialmentions.persistence.entities.TweetEntity;
import com.appgate.socialmentions.persistence.repositories.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@AllArgsConstructor
public class TweetServiceImpl implements CreateMentionRecord<TweetEntity> // implements  TweetService{
{

    private final TweetRepository tweetRepository;

    @Override
    public TweetEntity createMention(Double score, SocialMention socialMention) {
        TweetEntity entity = new TweetEntity(null,score,socialMention.getMessage(),socialMention.getTweeterUrl(), socialMention.getTweeterAccount() );
        return tweetRepository.save(entity);
    }
}
