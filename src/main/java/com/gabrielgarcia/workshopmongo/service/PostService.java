package com.gabrielgarcia.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielgarcia.workshopmongo.domain.Post;
import com.gabrielgarcia.workshopmongo.repository.PostRepository;
import com.gabrielgarcia.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto <Post> não encontrado"));
	}
	
	public List<Post> findByTitulo(String texto){
		return repo.findByTitulo(texto);
	}
}
