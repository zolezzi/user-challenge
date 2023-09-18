package cl.nisum.userchallenge.controller.response;

import cl.nisum.userchallenge.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponse extends BasicResponse{
	
	private UserDTO response;
	
	public UserResponse(UserDTO user, Boolean error, String message){
		super(message,error);
		this.response = user;
	}
}
