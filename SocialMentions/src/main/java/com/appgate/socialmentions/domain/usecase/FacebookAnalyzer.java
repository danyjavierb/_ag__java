package com.appgate.socialmentions.domain.usecase;

import java.util.Random;

public class FacebookAnalyzer {

    public static double calculateFacebookCommentsScore(String substring) {
        //Logic to calculate Facebook comments score
        return (double) new Random().ints(0, 100).findFirst().getAsInt();
    }

    public static Double analyzePost(String message, String facebookAccount) {
        //Logic to analyze post
        return (double) new Random().ints(100, 200).findFirst().getAsInt();
    }
}
