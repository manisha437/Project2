package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.models.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
@Autowired
private SessionFactory sessionFactory;	
	
 public UserDaoImpl(){
	 System.out.println("UserDaoImpl bean is created");
 }
	
	
	public void registerUser(User user) {
		System.out.println("registeruser called");
     Session session=sessionFactory.getCurrentSession();
     session.save(user);
     System.out.println("registerUser method called");

	}


	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.getCurrentSession();
		//select * from user_s190038 where email='john@niit.com'
		User user=(User)session.get(User.class, email);
		if(user==null)
			return true; //entered email is unique
		else
			return false;//entered email is duplicate,another user has already registered with same email id 
	}


	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from User  where email=? and password=?");
		query.setString(0, user.getEmail());
		query.setString(1, user.getPassword());
		return (User)query.uniqueResult();//only one object
		
	}


	public void updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		
	}


	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		return user;
	}

}
