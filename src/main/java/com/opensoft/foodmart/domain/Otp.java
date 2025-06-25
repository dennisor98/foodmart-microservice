package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.time.Instant;

import com.opensoft.foodmart.enums.OtpType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Otp extends BaseFoodDomain implements Serializable {
	private static final long serialVersionUID = 6514527326243106548L;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	@Column()
	private String code;
	
	@Column()
	private String mobileNumber;
	
	@Column()
	private String otpHash;
	
	@Column()
	private Instant otpExpiryAt;
	
	@Column()
	private OtpType otpType;

}
