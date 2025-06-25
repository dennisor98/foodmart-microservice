package com.opensoft.foodmart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserOnBoardDto {
  @NotNull()
  private String mobileNumber;
  
  @NotNull()
  private String firstName;
  
  @NotNull()
  private String lastName;
  
  private String email;
  
}
