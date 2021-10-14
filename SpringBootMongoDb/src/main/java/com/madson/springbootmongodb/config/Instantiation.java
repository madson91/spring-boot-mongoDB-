package com.madson.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.madson.springbootmongodb.domain.Post;
import com.madson.springbootmongodb.domain.User;
import com.madson.springbootmongodb.dto.AuthorDTO;
import com.madson.springbootmongodb.dto.CommentDTO;
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		useRepo.deleteAll();
		postRepo.deleteAll();
		

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		useRepo.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post = new Post(null, sdf.parse("14/10/2021"), "teste do post", "testando", new AuthorDTO(bob));
		Post post2 = new Post(null, sdf.parse("14/10/2021"), "teste do post2", "testando2", new AuthorDTO(maria));
		
		CommentDTO c1  =new CommentDTO("iar mano", sdf.parse("14/10/2021"), new AuthorDTO(alex));
		CommentDTO c2  =new CommentDTO("aparece mano", sdf.parse("14/10/2021"), new AuthorDTO(maria));
		CommentDTO c3  =new CommentDTO("iae como vc esta ", sdf.parse("14/10/2021"), new AuthorDTO(bob));
		
		post.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepo.saveAll(Arrays.asList(post,post2));
		
		maria.getPosts().addAll(Arrays.asList(post,post2));
		
		useRepo.save(maria);
		
	}

}
