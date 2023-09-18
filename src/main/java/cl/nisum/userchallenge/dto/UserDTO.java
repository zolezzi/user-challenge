package cl.nisum.userchallenge.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDTO {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("token")
	private String token;
	
	@JsonProperty("create")
	private Date create;
	
	@JsonProperty("modify")
	private Date modify;
	
	@JsonProperty("last_login")
	private Date lastLogin;
	
	@JsonProperty("isactive")
	private boolean isActive;
}
