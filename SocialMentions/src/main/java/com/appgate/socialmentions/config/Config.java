package com.appgate.socialmentions.config;


import com.appgate.socialmentions.domain.usecase.AnalyzePostUseCase;
import com.appgate.socialmentions.domain.usecase.AnalyzeTweetUseCase;
import com.appgate.socialmentions.persistence.repositories.PostRepository;
import com.appgate.socialmentions.persistence.repositories.TweetRepository;
import com.appgate.socialmentions.persistence.services.PostServiceImpl;
import com.appgate.socialmentions.persistence.services.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private PostRepository postRepository;



    @Bean
    public TweetServiceImpl createTweetServiceImpl() {
        return new TweetServiceImpl(tweetRepository);
    }
    @Bean
    public PostServiceImpl createPostServiceImpl() {
        return new PostServiceImpl(postRepository);
    }

    @Bean
    public AnalyzePostUseCase CreateAnalyzePostUseCase() {
        return new AnalyzePostUseCase(createPostServiceImpl());
    }

    @Bean
    public AnalyzeTweetUseCase CreateAnalyzeTweetUseCase() {
        return new AnalyzeTweetUseCase(createTweetServiceImpl());
    }


}
