package com.opensoft.foodmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensoft.foodmart.domain.Otp;

public interface OtpRepository extends JpaRepository<Otp,String>{
  Optional<Otp> findByCodeAndOtpHash(String code,String hash);
}
