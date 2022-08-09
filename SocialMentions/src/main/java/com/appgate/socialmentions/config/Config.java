package com.appgate.socialmentions.config;


import com.appgate.socialmentions.persistence.repositories.TweetRepository;
import com.appgate.socialmentions.persistence.services.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private TweetRepository tweetRepository;

    @Bean
    public TweetServiceImpl CreateTweetServiceImpl() {
        return new TweetServiceImpl(tweetRepository);
    }


}
