package com.appgate.socialmentions.entrypoint.api;

import com.appgate.socialmentions.domain.usecase.AnalyzeMention;
import com.appgate.socialmentions.domain.usecase.AnalyzePostUseCase;
import com.appgate.socialmentions.domain.usecase.AnalyzeTweetUseCase;
import com.appgate.socialmentions.entrypoint.api.payload.SocialMention;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/social-mentions")
//@RequestMapping("/Social-Mentions") dont break current contracts
class SocialMentionController {


    private final  AnalyzePostUseCase analyzePostUseCase;

    private final  AnalyzeTweetUseCase analyzeTweetUseCase;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/analyze",produces = MediaType.APPLICATION_JSON_VALUE)

   // @PostMapping("/AnalyzeSocialMention") dont break current contracts
    public ResponseEntity<String> analyze(@Valid @RequestBody SocialMention socialMentionPayload) {


        AnalyzeMention analyzerUseCase;
        if (socialMentionPayload.getFacebookAccount() != null) {
             analyzerUseCase = analyzePostUseCase;


        } else if (socialMentionPayload.getTweeterAccount() != null) {
             analyzerUseCase = analyzeTweetUseCase;

        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( "Error, Tweeter or Facebook account must be present");
        }

        return  ResponseEntity.status(HttpStatus.OK).body( analyzerUseCase.analyze(socialMentionPayload));

    }
}
