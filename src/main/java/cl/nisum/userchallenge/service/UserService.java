package cl.nisum.userchallenge.service;

import cl.nisum.userchallenge.dto.UserDTO;
import cl.nisum.userchallenge.vo.UserVO;

public interface UserService {
	
	UserDTO register(UserVO user);
}
