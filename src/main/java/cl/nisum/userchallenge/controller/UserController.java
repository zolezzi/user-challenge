package cl.nisum.userchallenge.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nisum.userchallenge.dto.UserDTO;
import cl.nisum.userchallenge.service.impl.UserServiceImpl;
import cl.nisum.userchallenge.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController("user")
@Api(value = "User Controller")
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl service;
	
    @ApiOperation(
            value = "Service that return an user with details",
            notes = "This service returns an user with details",
            nickname = "register",
            response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded.", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error.", response = UserDTO.class) })
    @PostMapping(
            value = "/register",
            produces = { "application/json" },
            consumes = {"application/json"}
    )
    public UserDTO register(@RequestBody UserVO user){
        return service.register(user);
    }
}
