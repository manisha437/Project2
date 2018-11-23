package com.dao;

import com.models.User;

public interface UserDao {
void registerUser(User user);
boolean isEmailUnique(String email);
User login(User user);//i/p email and password ,o/p values for all properties if valid input,else null
//valid credentials  -o/p user object
//invalid credentials  - o/p null object
void updateUser(User user);
User getUser(String email);
}

