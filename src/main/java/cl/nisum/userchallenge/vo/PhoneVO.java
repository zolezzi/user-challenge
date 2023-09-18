package cl.nisum.userchallenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PhoneVO {

	@JsonProperty("number")
	private String number;
	
	@JsonProperty("citycode")
	private String cityCode;
	
	@JsonProperty("countrycode")
	private String countryCode;
}
