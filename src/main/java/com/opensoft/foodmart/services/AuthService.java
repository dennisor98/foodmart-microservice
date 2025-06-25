package com.opensoft.foodmart.services;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.opensoft.foodmart.beans.AdvancedUniqueKeyGenerator;
import com.opensoft.foodmart.domain.Otp;
import com.opensoft.foodmart.domain.User;
import com.opensoft.foodmart.dto.request.GetOtpDto;
import com.opensoft.foodmart.dto.request.ValidateOtpDto;
import com.opensoft.foodmart.dto.request.VendorLoginDto;
import com.opensoft.foodmart.enums.OtpType;
import com.opensoft.foodmart.repository.OtpRepository;
import com.opensoft.foodmart.repository.UserRepository;

@Service
public class AuthService {
   @Autowired
   private UserService userService;
   @Autowired
   private JwtService jwtService;
   @Autowired
   private OtpRepository otpRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private StringRedisTemplate redisTemplate;
   
	public Object vendorLogin(VendorLoginDto req) {
		String mobileNumber = req.getMobileNumber();
		if(mobileNumber.length() < 9) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success",false,"message","Invalid phone number"));
		}
		
		String phone  = mobileNumber.substring(mobileNumber.length() -9);
		Optional<User> userOpt =  this.userService.findUserByPhone(phone);
		
		if(userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("success",false,"message","Invalid cridentials"));
		}
		
		User user = userOpt.get();
		
		if(user.getStore() == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("success",false,"message","Invalid logins"));
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(!encoder.matches(req.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("success",false,"message","Invalid logins"));
		}
		
		var authPayload  = Map.of(
				"firstName",user.getFirstName(),
				"lastName",user.getLastName(),
				"mobileNumber",user.getMobileNumber(),
				"vendor_id",user.getStore().getId(),
				"vendor_name",user.getStore().getName(),
				"access_token",this.jwtService.generateVendorAccessToken(user),
				"refresh_token",this.jwtService.generateVendorRefreshToken(user)
				);
		
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Login success","payload",authPayload));
	}
	
	
	public Object generateUserOtp(GetOtpDto req) {
		
		String mobileNumber = req.getMobileNumber();
		if(mobileNumber.length() < 9) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of());
		}
		
		String mobile = mobileNumber.substring(mobileNumber.length() -9);
		
		Optional<User> userOpt =  this.userService.findUserByPhone(mobile);
		if(userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success",false,"message","Account unavailable"));
		}
		
		String lockedPhone =  redisTemplate.opsForValue().get(mobile);
		if(lockedPhone !=null) {
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Map.of(
					"success",false,
					 "message",  "Otp request too soon. Try again after "+redisTemplate.getExpire(mobile,TimeUnit.SECONDS)+" seconds"));
		}
		
		User user = userOpt.get();
		SecureRandom secureRandom = new SecureRandom();
		int OTP_LENGTH = 6;
		
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(secureRandom.nextInt(10)); // digits 0–9
        }
		
		String hash =  AdvancedUniqueKeyGenerator.generateUniqueKey(128).toLowerCase();
		
		try {
			Otp otpBuild = Otp.builder().code(otp.toString()).mobileNumber(mobileNumber).otpHash(hash).otpType(OtpType.USER_OTP)
					.user(user).otpExpiryAt(Instant.now().plus(5, ChronoUnit.MINUTES)).build();
			
			this.otpRepository.save(otpBuild);
			redisTemplate.opsForValue().set(mobile,"true", 60*5, TimeUnit.SECONDS);
			
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"otp",otp,"hash",hash));
		}catch(Exception ex) {
			ex.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",false,"message","Oops!Server error"));
		}
		
	}
	
	public Object generateVendorOtp(GetOtpDto req) {
		String mobileNumber = req.getMobileNumber();
		if(mobileNumber.length() < 9) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of());
		}

		String mobile = mobileNumber.substring(mobileNumber.length() -9);

		Optional<User> userOpt =  this.userService.findUserByPhone(mobile);
		if(userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success",false,"message","Account unavailable"));
		}

		String lockedPhone =  redisTemplate.opsForValue().get(mobile);
		if(lockedPhone !=null) {
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Map.of(
					"success",false,
					"message",  "Otp request too soon. Try again after"+redisTemplate.getExpire(mobile,TimeUnit.SECONDS)+"seconds"));
		}

		User user = userOpt.get();
		SecureRandom secureRandom = new SecureRandom();
		int OTP_LENGTH = 6;

		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < OTP_LENGTH; i++) {
			otp.append(secureRandom.nextInt(10)); // digits 0–9
		}

		String hash =  AdvancedUniqueKeyGenerator.generateUniqueKey(12);

		try {
			Otp otpBuild = Otp.builder().code(otp.toString()).otpType(OtpType.VENDOR_OTP).otpHash(hash)
					.user(user).otpExpiryAt(Instant.now().plus(5, ChronoUnit.MINUTES)).build();
			this.otpRepository.save(otpBuild);
			redisTemplate.opsForValue().set(null,mobile, 60*5, TimeUnit.SECONDS);

			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"otp",otp,"hash",hash));
		}catch(Exception ex) {
			ex.printStackTrace();

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",false,"message","Oops!Server error"));
		}

	}

	public Object validateUserOtp(ValidateOtpDto req) {
		Optional<Otp> otpOpt =  this.otpRepository.findByCodeAndOtpHash(req.getCode(), req.getHash());
		if(otpOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success",false,"message","Invalid otp"));
		}
		
		Otp otp = otpOpt.get();
		if(otp.getOtpType() != OtpType.USER_OTP) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(
							"success",false,
							"message","Invalid OTP type"
							));
		}
		
		if(Instant.now().isAfter(otp.getOtpExpiryAt()) ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success",false,"message","Otp expired"));
		}
		
		User user = otp.getUser();
		
		var authPayload  = Map.of(
				"firstName",user.getFirstName(),
				"lastName",user.getLastName(),
				"mobileNumber",user.getMobileNumber(),
				"user_id",user.getId(),
				"access_token",this.jwtService.generateToken(user),
				"refresh_token",this.jwtService.generateRefreshToken(user)
				);
		
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Success","payload",authPayload));
	}
	
	public Object validateUserRegisterOtp(ValidateOtpDto req) {
		Optional<Otp> otpOpt =  this.otpRepository.findByCodeAndOtpHash(req.getCode(), req.getHash());
		if(otpOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success",false,"message","Invalid otp"));
		}
		
		Otp otp = otpOpt.get();
		if(otp.getOtpType() != OtpType.USER_REG_OTP) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(
							"success",false,
							"message","Invalid OTP type"
							));
		}
		
		if(Instant.now().isAfter(otp.getOtpExpiryAt()) ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success",false,"message","Otp expired"));
		}
		
		User user = otp.getUser();
		user.setActive(true);
		user.setVerified(true);
		
		try {
			this.userRepository.save(user);
			this.userRepository.flush();
			var authPayload  = Map.of(
					"firstName",user.getFirstName(),
					"lastName",user.getLastName(),
					"mobileNumber",user.getMobileNumber(),
					"user_id",user.getId(),
					"access_token",this.jwtService.generateToken(user),
					"refresh_token",this.jwtService.generateRefreshToken(user)
					);

			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Success","payload",authPayload));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",false,"message","Oops! Error occured."));
		}
		
	}
	
	public Object validateVendorOtp(ValidateOtpDto req) {
		Optional<Otp> otpOpt =  this.otpRepository.findByCodeAndOtpHash(req.getCode(), req.getHash());
		if(otpOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success",false,"message","Invalid otp"));
		}
		
		Otp otp = otpOpt.get();
		if(otp.getOtpType() != OtpType.VENDOR_OTP) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(
							"success",false,
							"message","Invalid OTP type"
							));
		}
		
		if(Instant.now().isAfter(otp.getOtpExpiryAt()) ) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success",false,"message","Otp expired"));
		}
		
		User user = otp.getUser();
		
		var authPayload  = Map.of(
				"firstName",user.getFirstName(),
				"lastName",user.getLastName(),
				"mobileNumber",user.getMobileNumber(),
				"vendor_id",user.getStore().getId(),
				"vendor_name",user.getStore().getName(),
				"access_token",this.jwtService.generateVendorAccessToken(user),
				"refresh_token",this.jwtService.generateVendorRefreshToken(user)
				);
		
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Success","payload",authPayload));
	}
	
	
	
}
