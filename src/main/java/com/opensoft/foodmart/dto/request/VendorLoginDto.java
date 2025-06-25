package com.opensoft.foodmart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VendorLoginDto {
   @NotNull()
   private String mobileNumber;
   
   @NotNull()
   private String password;
}
