package com.appgate.socialmentions.entrypoint.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMention implements Serializable {
    @NotNull
    private String message;

    @NotNull
    private String facebookAccount;

    @NotNull
    private String tweeterAccount;

    @NotNull
    private String creationDate;

    @NotNull
    private String tweeterUrl;

    @NotNull
    private List<String> facebookComments;

}
