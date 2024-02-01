package com.example.budgetducklingsspring.controller;

import com.example.budgetducklingsspring.model.User;
import com.example.budgetducklingsspring.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest req, @RequestBody User user)  {

        if (user == null) {
            return ResponseEntity.status(401).body("Wrong Login details");
        }

        User currentUser = loginService.login(user);

        if (currentUser == null) {
            return ResponseEntity.status(401).body("Wrong login details");
        }
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            return ResponseEntity.status(200).body("Already logged in");
        } else if (currentUser.getPassword().equals(user.getPassword())){
            session =  req.getSession(true);
            session.setAttribute("username", currentUser.getUsername());
            return ResponseEntity.status(200).body("Login Successful");
        }
        return ResponseEntity.status(401).body("Wrong login details");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest req){

        HttpSession session = req.getSession(false);
        if (session == null){
            return ResponseEntity.status(200).body("Already logged out");
        } else {
            session.invalidate();
            return ResponseEntity.status(200).body("Logged out");
        }
    }


}
