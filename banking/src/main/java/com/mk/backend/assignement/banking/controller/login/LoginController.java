package com.mk.backend.assignement.banking.controller.login;

import com.mk.backend.assignement.banking.entities.user.User;
import com.mk.backend.assignement.banking.jwt.JwtTokenUtils;
import com.mk.backend.assignement.banking.navigation.Navigation;
import com.mk.backend.assignement.banking.request.dto.AuthRequest;
import com.mk.backend.assignement.banking.response.dto.UserTokenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Navigation.LOGIN_API)
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtil;


    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("login")
    public ResponseEntity<UserTokenDto> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
           String  token = jwtTokenUtil.generateToken(user);
            UserTokenDto  userTokenDto  = new UserTokenDto()  ;
            userTokenDto .setToken(token);
            userTokenDto.setUsername(user.getUsername());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    )
                    .body(userTokenDto);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
