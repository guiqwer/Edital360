package com.ifce.edital360.dto.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RecaptchaResponse(
        boolean success,

        @JsonProperty("challenge_ts")
        String challengeTs,

        String hostname,

        @JsonProperty("erro-codes")
        List<String> errorCodes
) {

    public boolean isSuccess() {
        return success;
    }

}
