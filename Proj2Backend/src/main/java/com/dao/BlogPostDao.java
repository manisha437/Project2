package com.dao;

import java.util.List;

import com.models.BlogPost;

public interface BlogPostDao {
void  addBlogPost(BlogPost blogPost);
List<BlogPost> blogsWaitingForApproval();
List<BlogPost> blogsApproved();
BlogPost getBlog(int blogPostId);
void updateBlogPost(BlogPost blogPost);
void deleteBlogPost(BlogPost blogPost);
}
