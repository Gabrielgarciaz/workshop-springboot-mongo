package com.gabrielgarcia.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielgarcia.workshopmongo.domain.Post;
import com.gabrielgarcia.workshopmongo.resources.util.URL;
import com.gabrielgarcia.workshopmongo.service.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService servico;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = servico.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/procurartitulo")
	public ResponseEntity<List<Post>> findByTitulo(@RequestParam(value = "texto", defaultValue = "") String texto){ // Se nenhum texto for informado vai colocar uma String vazia nele
		texto = URL.decodeParam(texto);
		List<Post> lista = servico.findByTitulo(texto);
		
		return ResponseEntity.ok().body(lista);
	}
	
	
	
	
	
}
