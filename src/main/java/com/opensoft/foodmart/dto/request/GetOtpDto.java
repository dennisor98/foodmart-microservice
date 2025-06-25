package com.opensoft.foodmart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetOtpDto {
  @NotNull()
  private String mobileNumber;
}
