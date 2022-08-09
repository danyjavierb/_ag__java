package com.appgate.socialmentions.persistence.services;

import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import com.appgate.socialmentions.persistence.entities.CreateMentionRecord;
import com.appgate.socialmentions.persistence.entities.PostEntity;
import com.appgate.socialmentions.persistence.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;



@AllArgsConstructor
public class PostServiceImpl implements CreateMentionRecord<PostEntity> // implements  TweetService{
{

    private final PostRepository postRepository;

    @Override
    public PostEntity createMention (Double score, SocialMention socialMention) {
        PostEntity entity = new PostEntity(null,score,socialMention.getMessage(), socialMention.getFacebookAccount() );
        return postRepository.save(entity);
    }

}
