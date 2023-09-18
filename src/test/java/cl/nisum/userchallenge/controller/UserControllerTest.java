package cl.nisum.userchallenge.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cl.nisum.userchallenge.dto.UserDTO;
import cl.nisum.userchallenge.service.impl.UserServiceImpl;
import cl.nisum.userchallenge.vo.PhoneVO;
import cl.nisum.userchallenge.vo.UserVO;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private static final String EMAIL = "admin@dominio.cl";
	private static final String PASSWORD = "HolaAdmin123!";
	private static final String NAME = "Lucas Jorge";
	private static final String PHONE_NUMBER = "12345678";
	private static final String PHONE_CITY_CODE = "1";
	private static final String PHONE_COUNTRY_CODE = "57";
	
	@Mock
	private UserServiceImpl service;
	
	@Mock
	private UserDTO userDto;
	
	@InjectMocks
	private UserController controller;
	
	@Before
	public void setUp(){
		when(service.register(any())).thenReturn(userDto);
	}
	
	@Test
	public void testRegisterUserThenReturnANewUserDTO(){
		UserVO user = createUserVO();
		assertThat(controller.register(user), is(userDto));
		verify(service).register(any());
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
