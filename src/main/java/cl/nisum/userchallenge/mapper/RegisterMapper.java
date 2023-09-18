package cl.nisum.userchallenge.mapper;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.nisum.userchallenge.dto.UserDTO;
import cl.nisum.userchallenge.entity.Phone;
import cl.nisum.userchallenge.entity.User;
import cl.nisum.userchallenge.vo.PhoneVO;
import cl.nisum.userchallenge.vo.UserVO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;

@Component
public class RegisterMapper {

	@Autowired
	private MapperFactory mapperFactory;
	
	@Autowired
	private Mapper mapper;
	
	@PostConstruct
	public void initialize() {

		mapperFactory.classMap(UserVO.class, User.class).customize(new CustomMapper<UserVO, User>() {
			@Override
			public void mapBtoA(User b, UserVO a, MappingContext context) {
				a.setEmail(b.getEmail());
				a.setPassword(b.getPassword());
				a.setName(b.getName());
			}
			@Override
			public void mapAtoB(UserVO a, User b, MappingContext context) {
				b.setEmail(a.getEmail());
				b.setPassword(a.getPassword());
				b.setName(a.getName());
				b.setPhones(mapper.mapList(a.getPhones(), Phone.class));
			}
		}).byDefault().register();
		
		mapperFactory.classMap(UserDTO.class, User.class).customize(new CustomMapper<UserDTO, User>() {
			@Override
			public void mapBtoA(User b, UserDTO a, MappingContext context) {
				a.setId(b.getId().toString());
				a.setCreate(b.getCreate());
				a.setModify(b.getModify());
				a.setLastLogin(b.getLastLogin());
				a.setToken(b.getToken());
				a.setActive(b.isActive());
			}
		}).byDefault().register();
		
		mapperFactory.classMap(PhoneVO.class, Phone.class).customize(new CustomMapper<PhoneVO, Phone>() {
			@Override
			public void mapBtoA(Phone b, PhoneVO a, MappingContext context) {
				a.setNumber(b.getNumber());
				a.setCityCode(b.getCityCode());
				a.setCountryCode(b.getCountryCode());
			}
		}).byDefault().register();
	}
}
