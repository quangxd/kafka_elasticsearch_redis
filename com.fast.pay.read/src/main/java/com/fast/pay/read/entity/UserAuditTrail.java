package com.fast.pay.read.entity;


import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@Builder
@Document(indexName = "user_audit_trial")
public class UserAuditTrail {

    @Id
    private UUID id;

    @Field(type = FieldType.Auto, name = "userId")
    private UUID userId;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Integer, name = "age")
    private Integer age;

    @Field(type = FieldType.Text, name = "address")
    private String address;

    @Field(type = FieldType.Text, name = "actionType")
    private String actionType;

    @Field(type = FieldType.Text, name = "createdAt")
    private String createdAt;

}
