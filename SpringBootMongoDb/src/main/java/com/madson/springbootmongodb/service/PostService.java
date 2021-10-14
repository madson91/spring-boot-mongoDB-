package com.madson.springbootmongodb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madson.springbootmongodb.domain.Post;
import com.madson.springbootmongodb.repository.PostRepository;
import com.madson.springbootmongodb.service.exception.copy.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;


	public Post findById(String id) {

		Optional<Post> user = postRepo.findById(id);
		if (user.isEmpty())
			throw new ObjectNotFoundException("Postagem não encontrada");
		return user.get();
	}
	
	public List<Post> findByTitle(String text){
		
		return postRepo.findbyTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		
		return postRepo.fullSearch(text, minDate, maxDate);	
	}

}
