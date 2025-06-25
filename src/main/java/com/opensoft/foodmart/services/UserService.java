package com.opensoft.foodmart.services;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.opensoft.foodmart.beans.AdvancedUniqueKeyGenerator;
import com.opensoft.foodmart.domain.Otp;
import com.opensoft.foodmart.domain.Role;
import com.opensoft.foodmart.domain.User;
import com.opensoft.foodmart.domain.UserRole;
import com.opensoft.foodmart.dto.request.UserOnBoardDto;
import com.opensoft.foodmart.enums.OtpType;
import com.opensoft.foodmart.repository.OtpRepository;
import com.opensoft.foodmart.repository.RoleRepository;
import com.opensoft.foodmart.repository.UserRepository;
import com.opensoft.foodmart.repository.UserRoleRepository;




@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private RoleRepository roleRepository;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;
    
	public void createSuperUser() {
		String mobileNumber = "700000000";
		Optional<User> userOpt =  this.userRepository.findByMobileNumber(mobileNumber);

		if(userOpt.isEmpty()) {
			User user = User.builder().active(true).firstName("Admin").lastName("Admin").mobileNumber(mobileNumber)
					.password(new BCryptPasswordEncoder().encode("admin1234"))
					.build();

			Optional<Role> roleOpt =  this.roleRepository.findByName("SUPER_ADMIN");
			try {
				this.userRepository.save(user);
				this.userRepository.flush();
			}catch(Exception ex) {
				ex.printStackTrace();

				return;
			}
			if(roleOpt.isPresent()) {
				Role role = roleOpt.get();

				Optional<UserRole> userRoleOpt = this.userRoleRepository.findByRoleAndUser(role, user);

				if(userRoleOpt.isEmpty()) {
					UserRole userRole = UserRole.builder().creator(user).role(role).user(user).build(); 
					try {
						this.userRoleRepository.save(userRole);
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}

			}



		}
	}
	

	public Object registerUser(UserOnBoardDto req) {
		String mobileNumber = req.getMobileNumber();

		if(mobileNumber.length() < 9) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(
							"success",false,
							"message","Invalid mobile number"
							));
		}
		String mobile = mobileNumber.trim().substring(mobileNumber.trim().length() -9);
		Optional<User> userOpt =  this.userRepository.findByMobileNumber(mobile);

		if(userOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					Map.of(
							"success",false,
							"message","Account already exist"
							));
		}
		User userBuild = User.builder()
				.email(req.getEmail()).firstName(req.getFirstName())
				.verified(false).active(true)
				.lastName(req.getLastName()).mobileNumber(mobileNumber)
				.build();
		
		SecureRandom secureRandom = new SecureRandom();
		int OTP_LENGTH = 6;
		
		StringBuilder otp = new StringBuilder();
		for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(secureRandom.nextInt(10)); // digits 0–9
        }
		
		String hash =  AdvancedUniqueKeyGenerator.generateUniqueKey(128).toLowerCase();
		try {
			User user = this.userRepository.save(userBuild);
			
		
				Otp otpBuild = Otp.builder().code(otp.toString()).mobileNumber(mobileNumber).otpHash(hash).otpType(OtpType.USER_REG_OTP)
						.user(user).otpExpiryAt(Instant.now().plus(5, ChronoUnit.MINUTES)).build();				
				this.otpRepository.save(otpBuild);
				redisTemplate.opsForValue().set(mobile,"true", 60*5, TimeUnit.SECONDS);
				
				
				System.out.println("{otp}"+ otp);
				return ResponseEntity.status(HttpStatus.OK).body(
						Map.of(
								"success",true,
								"message","Enter OTP sent to mobile number "+mobileNumber,
								"hash",hash

								));
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					Map.of(
							"success",true,
							"message","Account registered"
							));
		}
	}
	
	public Object registerVendor(UserOnBoardDto req) {
		String mobileNumber = req.getMobileNumber();

		if(mobileNumber.length() < 9) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					Map.of(
							"success",false,
							"message","Invalid mobile number"
							));
		}
		String mobile = mobileNumber.trim().substring(mobileNumber.trim().length() -9);
		Optional<User> userOpt =  this.userRepository.findByMobileNumber(mobile);

		if(userOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					Map.of(
							"success",false,
							"message","Account already exist"
							));
		}
		User user = User.builder()
				.email(req.getEmail()).firstName(req.getFirstName())
				.lastName(req.getLastName()).mobileNumber(mobileNumber).build();
		try {
			this.userRepository.save(user);
			
			return ResponseEntity.status(HttpStatus.OK).body(
					Map.of(
							"success",true,
							"message","Account registered"
							));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					Map.of(
							"success",true,
							"message","Account registered"
							));
		}
	}
	
	public Object applyForVendor() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user.getStore() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success",false,"message","You already have a store."));
		}
		
		
		return null;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = this.userRepository.findById(username);

		return userOpt.isEmpty() ? userOpt.get() : null;
	}

	public boolean findPermissionByRoleName(Optional<Role> role, Object permission) {
		return false;
	}
	
	public Object getUsers(Pageable pageable) {
		try {
			Page<User> userList =  this.userRepository.findAll(pageable);
			var users = userList.stream()
					.map((u)->{
						return Map.of(
								"id",u.getId(),
								"firstName",u.getFirstName(),
								"lastName",u.getLastName(),
								"mobileNumber",u.getMobileNumber()
								);
					});
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Request complete","users",users));
		}catch(Exception ex) {
			ex.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",false,"message","Opps! Error encountered"));
		}
		
	}


	public Optional<Role> getUserRoleByUserId(String string) {
		Optional<User> userOpt = this.userRepository.findById(string);
		if(userOpt.isPresent()) {
			var user = userOpt.get();
			return this.userRoleRepository.findRoleByUser(user);
		}

		return Optional.empty();
	}

	public Optional<User> findUserByPhone(String mobileNumber){
		return this.userRepository.findByMobileNumber(mobileNumber);
	}
}
