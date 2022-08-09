package com.appgate.socialmentions.persistence.services;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import com.appgate.socialmentions.persistence.entities.TweetEntity;
import com.appgate.socialmentions.persistence.repositories.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TweetServiceImpl // implements  TweetService{
{

    private final TweetRepository tweetRepository;

    public TweetEntity createTweet (SocialMention socialMention) {
        TweetEntity entity = new TweetEntity(socialMention.)
        tweetRepository.save(n)
    }

}
