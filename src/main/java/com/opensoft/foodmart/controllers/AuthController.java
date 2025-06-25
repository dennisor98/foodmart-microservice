package com.opensoft.foodmart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensoft.foodmart.annotations.CustomController;
import com.opensoft.foodmart.dto.request.GetOtpDto;
import com.opensoft.foodmart.dto.request.UserOnBoardDto;
import com.opensoft.foodmart.dto.request.ValidateOtpDto;
import com.opensoft.foodmart.services.AuthService;
import com.opensoft.foodmart.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("auth")
@Tag(name="Authentication")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    
    @PostMapping("/user/register")
    public Object onboardUser(@Valid @RequestBody UserOnBoardDto req) {
		return userService.registerUser(req);
    	
    }
    
    @PostMapping("/vendor/register")
    public Object registerVendor(@Valid @RequestBody UserOnBoardDto req) {
		return userService.registerUser(req);
    	
    }
    
    @PostMapping("/vendor/getOtp")
    public Object getVenorOtp(@Valid @RequestBody GetOtpDto req) {
    	return this.authService.generateUserOtp(req);
    }
    
    @PostMapping("/vendor/validateOtp")
    public Object validateVendorOtp(@Valid @RequestBody ValidateOtpDto req) {
    	return this.authService.validateVendorOtp(req);
    }
    
    @PostMapping("/user/getOtp")
    public Object getUserOtp(@Valid @RequestBody GetOtpDto req) {
    	return this.authService.generateUserOtp(req);
    }
    
    @PostMapping("/user/validateOtp")
    public Object validateUserOtp(@Valid @RequestBody ValidateOtpDto req) {
    	return this.authService.validateUserOtp(req);
    }
    
    @PostMapping("/user/register/validateOtp")
    public Object validateUserRegisterOtp(@Valid @RequestBody ValidateOtpDto req) {
    	return this.authService.validateUserRegisterOtp(req);
    }
    

	
}
