package com.volt.vest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOtpRequest {
    private String pan;
    private String mobileNumber;
}
