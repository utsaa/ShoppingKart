package com.utsa.IdentityService.controller;

import com.utsa.IdentityService.Service.AuthService;
import com.utsa.IdentityService.dto.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if (authenticate.isAuthenticated()) {
        if (true) {
            String token=authService.generateToken(authRequest.getUsername());
            logger.info("Auth token {} for user {}", token, authRequest);
            return token;
        } else {
            throw new RuntimeException("invalid access");
        }


}

@GetMapping("/validate")
public  String validateToken(@RequestParam("token") String token){
        logger.info("token is {}", token);
    authService.validateToken(token);
    return "Token is valid";
}
}
