package com.madson.springbootmongodb.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.madson.springbootmongodb.domain.Post;
import com.madson.springbootmongodb.domain.User;
import com.madson.springbootmongodb.repository.PostRepository;
import com.madson.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	
	@Autowired
	private UserRepository useRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		useRepo.deleteAll();
		
		List<Post> posts = postRepo.findAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		maria.setPosts(posts);
		
		useRepo.saveAll(Arrays.asList(maria,alex,bob));
	}

}
