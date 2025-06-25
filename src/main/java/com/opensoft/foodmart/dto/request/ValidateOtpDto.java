package com.opensoft.foodmart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidateOtpDto {
   @NotNull()
   private String hash;
   
   @NotNull()
   private String code;
}
