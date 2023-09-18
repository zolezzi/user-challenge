package cl.nisum.userchallenge.service.impl;


import java.util.Date;
import java.util.Optional;
import java.util.Vector;

import org.springframework.stereotype.Service;

import cl.nisum.userchallenge.constants.UserChallengeConstants;
import cl.nisum.userchallenge.dto.UserDTO;
import cl.nisum.userchallenge.entity.User;
import cl.nisum.userchallenge.exception.UserChallengeException;
import cl.nisum.userchallenge.mapper.Mapper;
import cl.nisum.userchallenge.repository.UserRepository;
import cl.nisum.userchallenge.service.UserService;
import cl.nisum.userchallenge.utils.ErrorUtils;
import cl.nisum.userchallenge.utils.TokenUtils;
import cl.nisum.userchallenge.validator.UserValidator;
import cl.nisum.userchallenge.vo.UserVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository repository;
	private final UserValidator validator;
	private final Mapper mapper;
	private final TokenUtils tokenUtil;
	private Vector<String> errors = new Vector<>();
	
	@Override
	public UserDTO register(UserVO userVO) {
		User aNewUser = createUser(userVO);
		Optional<User> userDB = repository.findOneByEmail(aNewUser.getEmail());
        errors = userDB.isPresent() ? ErrorUtils.addError(errors, UserChallengeConstants.ERROR_EMAIL_FOUND_MESSAGE) : errors;
        if(!validator.validate(aNewUser, errors) || !errors.isEmpty()) {
			throw new UserChallengeException(errors.toString());
		}
		aNewUser = repository.save(aNewUser);
		return mapper.map(aNewUser, UserDTO.class);
	}

	private User createUser(UserVO userVO) {
		User aNewUser = mapper.map(userVO, User.class);
		Date creationDate = new Date();
		aNewUser.setCreate(creationDate);
		aNewUser.setModify(null);
		aNewUser.setLastLogin(creationDate);
		aNewUser.setActive(Boolean.TRUE.booleanValue());
		aNewUser.setToken(tokenUtil.createToken(userVO.getEmail()));
		return aNewUser;
	}

}
