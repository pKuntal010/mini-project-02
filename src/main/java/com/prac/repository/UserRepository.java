package com.prac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prac.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUserEmail(String userEmail);
    public User findByUserEmailAndPwd(String userEmail, String pwd);
}
