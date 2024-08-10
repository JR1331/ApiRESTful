package com.softtek.WheelsWonder.servicios;


import com.softtek.WheelsWonder.dao.request.SignInRequest;
import com.softtek.WheelsWonder.dao.request.SignUpRequest;
import com.softtek.WheelsWonder.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);

}
