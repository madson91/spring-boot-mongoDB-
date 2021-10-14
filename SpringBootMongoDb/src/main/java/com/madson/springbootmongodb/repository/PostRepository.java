package com.madson.springbootmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.madson.springbootmongodb.domain.Post;

@Repository
public interface PostRepository  extends MongoRepository<Post, String>{
	
	List<Post> findByTitleContainingIgnoreCase(String title);
	
	@Query("{ title: { $regex: ?0, $options: 'i' } }")
	List<Post> findbyTitle(String text);

}
