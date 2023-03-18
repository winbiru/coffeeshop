package com.mycompany.coffeeshop.Controller;

import com.mycompany.coffeeshop.Model.LoginRequest;
import com.mycompany.coffeeshop.Model.SignupRequest;
import com.mycompany.coffeeshop.Model.User;
import com.mycompany.coffeeshop.Service.UserService;
import com.mycompany.coffeeshop.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;
    @Autowired
    public UserController(UserService userService, UserServiceImpl userServiceImpl){
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    };

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        User user = userService.registerUser(signupRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        String jwt = userServiceImpl.login(loginRequest);
        response.addHeader("Authorization", "Bearer " + jwt);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.ok("User information");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminInfo() {
        return ResponseEntity.ok("Admin information");
    }
}
