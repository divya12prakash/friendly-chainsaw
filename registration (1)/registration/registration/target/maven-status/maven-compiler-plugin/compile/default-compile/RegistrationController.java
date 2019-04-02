package com.magento.registration.controller;

import com.magento.registration.entities.UserRegistrationInput;
import com.magento.registration.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller class will be used to
 * intercept user creation requests from
 * an external source/call
 */

@RestController
@RequestMapping(value="/register")
@CrossOrigin
public class RegistrationController {

    /**
     * User registration service declaration
     */
    @Autowired
    UserRegistrationService userRegistrationService;

    /**
     * This method will accept the user input in forrm of json
     * and parse it to user registration input with
     * the help of Jackson library. If registration is successful,
     * it returns a success json with response entity else
     * throws out corresponding error to the caller of the
     * API being exposed
     *
     * @param userRegistrationInput
     * @return ResonseEntity
     */
    @PostMapping(value="/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserRegistrationInput userRegistrationInput){

        //TODO: JSR validations on the input received
        ResponseEntity userRegistrationResponse = userRegistrationService.registerUser(userRegistrationInput);
//        encodedUserRegistrationResponse = ESAPI.encoder()
//                .encodeForHTML(userRegistrationResponse.getStatusCode());
        return ResponseEntity.status(HttpStatus.OK).body(userRegistrationResponse);
    }
}
