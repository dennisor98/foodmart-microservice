package com.opensoft.foodmart.domain;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseFoodDomain implements UserDetails,Serializable{

	private static final long serialVersionUID = -4762142029527540880L;

	
	@Column()
	private String firstName;
	
	@Column()
	private String lastName;
	
	@Column()
	private String mobileNumber;
	
	@Column(nullable=true)
	private String email;
	
	@Column()
	private Boolean verified;
	
	@Column()
	private String password;
	@Column()
	private Boolean active;
	
	@OneToOne()
	@JoinColumn(name="store_id")
	private Store store;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}	
}
