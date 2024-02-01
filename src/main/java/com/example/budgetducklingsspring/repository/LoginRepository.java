package com.example.budgetducklingsspring.repository;

import com.example.budgetducklingsspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);


}
