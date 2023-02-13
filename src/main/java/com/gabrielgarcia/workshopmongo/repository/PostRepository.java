package com.gabrielgarcia.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielgarcia.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }") // ?0 é o primeiro parâmetro que vem no método o <i> para maiúsculas e minúsculas, para mais informacoes consultar a documentacao do mongodb 
	public List<Post> findByTitulo(String texto);
	
	public List<Post> findByTituloContainingIgnoreCase(String texto); // Gerado pelos query métodos 
}