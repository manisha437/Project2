package com.dao;

import java.util.List;

import com.models.BlogComment;

public interface BlogCommentDao {
void addBlogComment(BlogComment blogComment);//insert into blogcomment table
List<BlogComment> getAllBlogComments(int blogPostId);
     //select * from blogcomments where  
}
