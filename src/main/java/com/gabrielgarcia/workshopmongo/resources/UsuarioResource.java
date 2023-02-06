package com.gabrielgarcia.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielgarcia.workshopmongo.domain.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		Usuario gabriel = new Usuario("1", "Gabriel Garcia", "gabriel@gmail.com");
		Usuario ronaldinho = new Usuario("2", "Ronaldinho Gaúcho", "ronaldinho@gmail.com");
		List<Usuario> list = new ArrayList<>();
		list.addAll(Arrays.asList(gabriel, ronaldinho));
		return ResponseEntity.ok().body(list);
	}
}
