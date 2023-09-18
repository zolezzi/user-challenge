package cl.nisum.userchallenge.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.nisum.userchallenge.controller.response.BasicResponse;
import cl.nisum.userchallenge.exception.UserChallengeException;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{

	  @ExceptionHandler({UserChallengeException.class})
	  public ResponseEntity<BasicResponse> handlerServiceException(UserChallengeException e){
		  BasicResponse response = new BasicResponse(e.getMessage(), Boolean.TRUE);
	    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	  }
}
