package com.fast.pay.read.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuditTrialQueryDTO {
    private String name;
    private Integer age;
    private String address;
}
