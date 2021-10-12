package com.flyhub.saccox.userservice.visualobject;
import com.flyhub.saccox.userservice.entity.FunctionalGroupEntity;
import com.flyhub.saccox.userservice.entity.HelperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisualObject {
	
	private Boolean success;
	
	private String error;
	
	private String message;

	private TokenEntityModel data;
}
