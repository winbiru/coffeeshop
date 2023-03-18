package com.mycompany.coffeeshop.Service;

import com.mycompany.coffeeshop.Model.LoginRequest;
import com.mycompany.coffeeshop.Model.SignupRequest;
import com.mycompany.coffeeshop.Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    User registerUser(SignupRequest signupRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);

    static String login(LoginRequest loginRequest) {
        return null;
    }
}
