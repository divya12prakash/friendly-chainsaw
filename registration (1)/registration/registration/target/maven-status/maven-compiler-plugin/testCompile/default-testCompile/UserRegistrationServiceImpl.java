package com.magento.registration.service.impl;

import com.magento.registration.entities.UserRegistrationInput;
import com.magento.registration.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This class will be used to call Magento exposed service
 * and register the user over the server
 */
@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    /**
     * User registration url to be
     * retrieved from property file
     */
    @Value("${user.registration.url}")
    private String userRegistrationUrl;

    /**
     * This method will be used to register
     * the user on Magento server
     *
     * @return UserRegistrationResponse
     */
    @Override
    public ResponseEntity registerUser(UserRegistrationInput userRegistrationInput) {
        log.debug("Entered  UserRegistrationServiceImpl.registerUser() " +
                "with input {}",userRegistrationInput);
        ResponseEntity responseEntity = null;
        ResponseEntity newResponseEntity = null;
        final RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create(userRegistrationUrl));
//        return new ResponseEntity<Void>(headers, HttpStatus.FOUND);

//        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        CloseableHttpClient httpClient = HttpClientBuilder.create()
//                .setRedirectStrategy(new LaxRedirectStrategy())
//                .build();
//        factory.setHttpClient(httpClient);
//
//        restTemplate.setRequestFactory(factory);

        try {
             responseEntity = restTemplate.
                    postForEntity(userRegistrationUrl, userRegistrationInput, UserRegistrationInput.class);
             if(responseEntity.getStatusCode().equals(HttpStatus.FOUND)){
                 newResponseEntity = restTemplate.getForEntity(responseEntity.getHeaders().getLocation(),String.class);
            }
        }catch(Exception exception){
            log.error(ExceptionUtils.getFullStackTrace(exception));
            throw exception;
        }
        log.debug("Exited  UserRegistrationServiceImpl.registerUser()");
        return newResponseEntity;
    }
}
