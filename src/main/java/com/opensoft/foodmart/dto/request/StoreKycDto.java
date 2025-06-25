package com.opensoft.foodmart.dto.request;

import com.opensoft.foodmart.annotations.ValidBase64;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class StoreKycDto {
	@ValidBase64()
	@NotNull()
	@NotBlank
	private String frontIDImage;
	
	@ValidBase64()
	@NotNull
	@NotBlank
	private String backIDImage;
	
	@ValidBase64()
	@NotNull
	@NotBlank
	private String selfieImage;
	
}
