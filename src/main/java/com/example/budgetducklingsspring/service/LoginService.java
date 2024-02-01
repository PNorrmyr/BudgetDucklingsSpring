package com.example.budgetducklingsspring.service;

import com.example.budgetducklingsspring.model.User;
import com.example.budgetducklingsspring.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public User login(User user) {
        return loginRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
