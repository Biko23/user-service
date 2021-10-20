package com.flyhub.saccox.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantOnlineMembersProcedureEntity {
    @Id
    private UUID system_user_global_id;
    private String first_name;
    private String last_name;
    private String primary_phone;
    private String primary_email;
    private String login_status;
    private String login_date;
}
