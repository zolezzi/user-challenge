package cl.nisum.userchallenge.validator;

import java.util.Vector;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.nisum.userchallenge.constants.UserChallengeConstants;
import cl.nisum.userchallenge.entity.User;

@Component
public class UserValidator {
	
	@Value("${regex.email:[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+.cl}")
	private String VALIDATE_EMAIL_REGEX;
	
	@Value("${regex.password.value:(?=.*[0-9])(?=.*[a-z])(?=.*\\d)(?=.*[#$@!%&*?]).{8,}}")
	private String VALIDATE_PASSWORD_REGEX;
	
	@Value("${regex.password.message: La contraseña debe ser mayor de 8 dígitos, no contener espacios en blanco, debe tener al menos una letra mayúscula, debe tener al menos un carácter especial y debe tener al menos un número.}")
	private String CONFIG_MESSAGE_REGEX_PASSWORD;


    public boolean validate(User user, Vector<String> erros) {
        Boolean isValid = Boolean.TRUE;
        if (!validatePassword(user.getPassword())) {
            erros.add(CONFIG_MESSAGE_REGEX_PASSWORD);
            isValid = Boolean.FALSE;
        }
        if (!validateEmail(user.getEmail())) {
            erros.add(UserChallengeConstants.ERROR_FORMAT_EMAIL);
            isValid = Boolean.FALSE;
        }
        return isValid;
    }
	
    private boolean validateEmail(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile(VALIDATE_EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        final Pattern PASSWORD_REGEX = Pattern.compile(VALIDATE_PASSWORD_REGEX);
        return PASSWORD_REGEX.matcher(password).matches();
    }
}
