package com.appgate.socialmentions.domain.usecase;

import java.util.Random;

public class TweeterAnalyzer {

    public static Double analyzeTweet(
            String message,
            String tweeterUrl,
            String tweeterAccount
    ) {
        //Logic to analyze Tweeter post
        return (double) new Random().ints(1, 100).findFirst().getAsInt();
    }
}
