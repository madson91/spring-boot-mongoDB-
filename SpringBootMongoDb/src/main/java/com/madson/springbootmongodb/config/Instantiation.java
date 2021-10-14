package com.madson.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.madson.springbootmongodb.domain.Post;
import com.madson.springbootmongodb.domain.User;
import com.madson.springbootmongodb.dto.AuthorDTO;
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
		
		SimpleDateFormat df = new SimpleDateFormat("dd/mm/YYYY");
		useRepo.deleteAll();
		postRepo.deleteAll();
		

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		useRepo.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post = new Post(null, df.parse("14/10/2021"), "teste do post", "testando", new AuthorDTO(bob));
		postRepo.save(post);
		List<Post> posts = postRepo.findAll();
		maria.setPosts(posts);
		
		useRepo.saveAll(Arrays.asList(maria,alex,bob));
		
	}

}
