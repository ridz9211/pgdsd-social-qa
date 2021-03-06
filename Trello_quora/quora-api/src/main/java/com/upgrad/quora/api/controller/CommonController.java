package com.upgrad.quora.api.controller;


import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.business.CommonBusinessService;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CommonController {

    @Autowired
    private CommonBusinessService userProfileService;

    @RequestMapping(method = RequestMethod.GET, path = "/userprofile/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDetailsResponse> viewUserProfile(@PathVariable("userId") final String userUuid, @RequestHeader("authorization") final String authorization)
            throws AuthorizationFailedException, UserNotFoundException {
        UserEntity userEntity = userProfileService.viewUserProfile(userUuid, authorization);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse().userName(userEntity.getUserName()).firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName()).emailAddress(userEntity.getEmail()).dob(userEntity.getDob()).country(userEntity.getCountry()).aboutMe(userEntity.getAboutMe())
                .contactNumber(userEntity.getContactNumber());
        return new ResponseEntity<UserDetailsResponse>(userDetailsResponse, HttpStatus.OK);
    }
}