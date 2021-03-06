package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.BlogComment;
@Repository
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao {
	@Autowired
private SessionFactory sessionFactory;
	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
	}

	public List<BlogComment> getAllBlogComments(int blogPostId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0, blogPostId);
		//SQL - select * from blogcomment_s190038 where blogpost_id=?
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}

}


