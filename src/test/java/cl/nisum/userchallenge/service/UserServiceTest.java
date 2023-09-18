package cl.nisum.userchallenge.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cl.nisum.userchallenge.dto.UserDTO;
import cl.nisum.userchallenge.entity.User;
import cl.nisum.userchallenge.exception.UserChallengeException;
import cl.nisum.userchallenge.mapper.Mapper;
import cl.nisum.userchallenge.repository.PhoneRepository;
import cl.nisum.userchallenge.repository.UserRepository;
import cl.nisum.userchallenge.service.impl.UserServiceImpl;
import cl.nisum.userchallenge.utils.TokenUtils;
import cl.nisum.userchallenge.validator.UserValidator;
import cl.nisum.userchallenge.vo.PhoneVO;
import cl.nisum.userchallenge.vo.UserVO;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final String ID = "32e2fweffsd-fwf4frfer-3224";
	private static final String EMAIL = "admin@dominio.cl";
	private static final String PASSWORD = "HolaAdmin123!";
	private static final String NAME = "Lucas Jorge";
	private static final String PHONE_NUMBER = "12345678";
	private static final String PHONE_CITY_CODE = "1";
	private static final String PHONE_COUNTRY_CODE = "57";
	private static final String EMAIL_FOUND = "existUser@dominio.cl";
	private static final String TOKEN_VALUE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY3NTMwMTAyNCwibmFtZSI6ImFkbWluIn0.1nWH5XPt71NJ3cC4nzrvXPLr31DnXg5iUUFg0dyemHbhYirBI4IMbR0KH_iNnt0x_dAwNWZco66w8XCOXIut9g";
	private static final Date CREATE_DATE = new Date();
	@Mock
	private User user;
	
	@Mock
	private User aNewUser;
	
	@Mock
	private UserDTO userDto;
	
	@Mock
	private UserVO userVO;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private PhoneRepository phoneRepository;

	@Mock
	private TokenUtils tokenUtil;
	
	@Mock
	private UserValidator validator;
	
	@Mock
	private Mapper mapper;
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@InjectMocks
	private UserServiceImpl service;
	
	@Before
	public void setUp(){
		service = new UserServiceImpl(repository, phoneRepository, validator, mapper, tokenUtil);
		when(repository.findOneByEmail(eq(EMAIL))).thenReturn(Optional.empty());
		when(repository.findOneByEmail(eq(EMAIL_FOUND))).thenReturn(Optional.of(user));
		when(aNewUser.getEmail()).thenReturn(EMAIL);
		when(user.getEmail()).thenReturn(EMAIL_FOUND);
		when(mapper.map(eq(userVO), eq(User.class))).thenReturn(user);
		when(mapper.map(any(), eq(UserDTO.class))).thenReturn(userDto);
		when(validator.validate(eq(aNewUser), any())).thenReturn(Boolean.TRUE);
		when(validator.validate(eq(user), any())).thenReturn(Boolean.FALSE);
		when(userDto.getId()).thenReturn(ID);
		when(userDto.getCreate()).thenReturn(CREATE_DATE);
		when(userDto.getLastLogin()).thenReturn(CREATE_DATE);
		when(userDto.getToken()).thenReturn(TOKEN_VALUE);
		when(userVO.getEmail()).thenReturn(EMAIL_FOUND);

	}
	
	@Test
	public void testCreateUserWithDataValidThenReturnANewUser(){
		UserVO user = createUserVO();
		when(mapper.map(eq(user), eq(User.class))).thenReturn(aNewUser);
		UserDTO userDTO = service.register(user);
	    assertThat(userDTO.getToken(), is(TOKEN_VALUE));
	    assertNotNull(userDTO.getId());
	    assertNotNull(userDTO.getCreate());
	    assertNotNull(userDTO.getLastLogin());
	    assertNull(userDTO.getModify());
	}

	@Test
	public void testCreateUserWithUserEmailExistInDatabaseThenReturnThenReturnException(){
		ex.expect(UserChallengeException.class);
		ex.expectMessage("[El correo ya registrado]");
		service.register(userVO);
		verify(repository).findOneByEmail(eq(EMAIL_FOUND));
	}
	
	private UserVO createUserVO() {
		UserVO user = new UserVO();
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		user.setName(NAME);
		PhoneVO phone = new PhoneVO();
		phone.setNumber(PHONE_NUMBER);
		phone.setCityCode(PHONE_CITY_CODE);
		phone.setCountryCode(PHONE_COUNTRY_CODE);
		user.setPhones(Arrays.asList(phone));
		return user;
	}
}
