package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserDao;
import com.models.ErrorClazz;
import com.models.User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
public UserController(){
	System.out.println("UserController bean is instantiated");
}

@RequestMapping(value="/register",method=RequestMethod.POST)

public ResponseEntity<?> registration(@RequestBody User user)
{
	if(!userDao.isEmailUnique(user.getEmail()))
	{
		ErrorClazz errorClazz=new ErrorClazz(2,"Email already exists select a new one");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	try
	{
		userDao.registerUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
		
	}
	catch(Exception e)
	{
		
		ErrorClazz errorClazz=new ErrorClazz(1,"Unable to register user details "+ e.getMessage());
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@RequestMapping(value="/login",method=RequestMethod.PUT)
public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
	User validUser=userDao.login(user);
	if(validUser==null){
		ErrorClazz errorClazz=new ErrorClazz(3,"Invalid email id/password.. please enter valid credentials");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	else{
		validUser.setOnline(true);
		userDao.updateUser(validUser);
		System.out.println("Session Id Is " +session.getId());
		System.out.println("Session Creation Time is " + session.getCreationTime());
		session.setAttribute("email", validUser.getEmail());
		return new ResponseEntity<User>(validUser,HttpStatus.OK);
	}
}


@RequestMapping(value="/logout",method=RequestMethod.PUT)
public ResponseEntity<?> logout(HttpSession session)
{
   String email=(String)session.getAttribute("email");
   if(email==null)
   {
	   ErrorClazz errorClazz=new ErrorClazz(5,"please login..");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	   
   }
   
   User user=userDao.getUser(email);
   user.setOnline(false);
   userDao.updateUser(user);
   session.removeAttribute("email");
   session.invalidate();
   return new ResponseEntity<Void>(HttpStatus.OK);
}
}



