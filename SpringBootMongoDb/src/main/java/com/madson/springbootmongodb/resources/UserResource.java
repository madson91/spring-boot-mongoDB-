package com.madson.springbootmongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.madson.springbootmongodb.domain.Post;
import com.madson.springbootmongodb.domain.User;
import com.madson.springbootmongodb.dto.UserDTO;
import com.madson.springbootmongodb.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@ApiOperation(value="Busca todos os usuarios")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> lista = service.findAll();
		List<UserDTO> listaDto = lista.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}
	
	@ApiOperation(value="Busca por id")
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById( @PathVariable String id){

		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@ApiOperation(value="Adiciona usuário")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
		
		User obj = service.fromUserDto(userDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		return ResponseEntity.created(uri).build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir um usuário ....."),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@ApiOperation(value="apaga usuário pelo id")
	@RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Atualiza o usuário por id")
	@RequestMapping(value = "/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody UserDTO userDTO, @PathVariable String id){
		
		User obj = service.fromUserDto(userDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Busca todos as postagens do usuário")
	@RequestMapping(value = "/{id}/posts" ,method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts( @PathVariable String id){

		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
