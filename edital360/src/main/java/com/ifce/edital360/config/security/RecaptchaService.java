package com.ifce.edital360.config.security;

import com.ifce.edital360.config.recaptcha.ReCaptchaConfig;
import com.ifce.edital360.dto.captcha.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecaptchaService {

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @Autowired
    private ReCaptchaConfig reCaptchaConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.test-mode}")
    private boolean testMode;

    @Value("${recaptcha.test-token}")
    private String testToken;

    public boolean validacaoRecaptcha(String recaptchaResponse) {

        if (testMode && testToken.equals(recaptchaResponse)) {
            return true;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("secret", reCaptchaConfig.getSecretKey());
        requestBody.add("response", recaptchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<RecaptchaResponse> response = restTemplate.postForEntity(
                    GOOGLE_RECAPTCHA_VERIFY_URL,
                    request,
                    RecaptchaResponse.class
            );

            return response.getBody() != null && response.getBody().isSuccess();
        } catch (Exception e) {
            return false;
        }
    }
}


