package com.gabrielgarcia.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielgarcia.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//Buscar posts contendo um dado String em qualquer lugar (titulo, corpo ou comentários) e em um dado intervalo de datas.
	@Query("{ $and: [ { data: { $gte: ?1 } }, { data: { $lte: ?2 } } , { $or: [ { 'titulo': { $regex: ?0, $options: 'i' } }, {{ 'corpo': { $regex: ?0, $options: 'i' } },  { 'comentario.texto': { $regex: ?0, $options: 'i' } } ] } ] }")
	public List<Post> fullSearch(String texto, Date minData, Date maxData);
	
	
	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }") // ?0 é o primeiro parâmetro que vem no método (no caso String texto) o <i> para maiúsculas e minúsculas, para mais informacoes consultar a documentacao do mongodb 
	public List<Post> findByTitulo(String texto);
	
	public List<Post> findByTituloContainingIgnoreCase(String texto); // Gerado pelos query métodos 
}