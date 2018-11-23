package com.dao;

import com.models.BlogPost;
import com.models.BlogPostLikes;

public interface BlogPostLikesDao {
BlogPostLikes hasUserLikedBlogPost(int blogPostId,String email);

BlogPost updateLikes(int blogPostId, String email);
}