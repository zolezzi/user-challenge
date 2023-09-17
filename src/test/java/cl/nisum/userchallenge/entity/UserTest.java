package cl.nisum.userchallenge.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "Luke";
	private static final String PASSWORD = "adminSW";
	private static final String EMAIL = "admin@nisum.cl";
	private static final String TOKEN = "3RDsdf4f4F3FdsfsvdtREyjkhjggfFBVDC";
	private static final Date CREATE_DATE = new Date();
	private static final Date MODIFY_DATE = new Date();
	private static final Date LAST_LOGIN_DATE = new Date();
	private static final boolean IS_ACTIVE = true;
	
	private User user;
	
	private User otherUser;
	
	@Before
	public void setUp(){
		otherUser = new User();
		otherUser.setEmail(EMAIL);
		otherUser.setName(NAME);
		otherUser.setPassword(PASSWORD);
		otherUser.setToken(TOKEN);
		otherUser.setCreate(CREATE_DATE);
		otherUser.setModify(MODIFY_DATE);
		otherUser.setLastLogin(LAST_LOGIN_DATE);
		otherUser.setActive(IS_ACTIVE);
		user = new User();
		user.setId(ID);
		user.setEmail(EMAIL);
		user.setName(NAME);
		user.setPassword(PASSWORD);
		user.setToken(TOKEN);
		user.setCreate(CREATE_DATE);
		user.setModify(MODIFY_DATE);
		user.setLastLogin(LAST_LOGIN_DATE);
		user.setActive(IS_ACTIVE);
	}
	
	@Test
	public void testAccessors(){
		assertThat(user.getId(), is(ID));
		assertThat(user.getName(), is(NAME));
		assertThat(user.getPassword(), is(PASSWORD));
		assertThat(user.getToken(), is(TOKEN));
		assertThat(user.getCreate(), is(CREATE_DATE));
		assertThat(user.getModify(), is(MODIFY_DATE));
		assertThat(user.getLastLogin(), is(LAST_LOGIN_DATE));
		assertTrue(user.isActive());
		assertNotNull(user.toString());
		assertNotNull(user.hashCode());
		assertFalse(user.equals(otherUser));
	}
}
