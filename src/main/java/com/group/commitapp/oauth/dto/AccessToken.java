package com.group.commitapp.oauth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public record AccessToken(String accessToken, String tokenType) {
    @JsonCreator
    public AccessToken(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("token_type") String tokenType) {

        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
