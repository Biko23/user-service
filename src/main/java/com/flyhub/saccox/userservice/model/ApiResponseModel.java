package com.flyhub.saccox.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseModel {

        private Boolean success;

        private String error;

        private String message;

        private Object data;
}
