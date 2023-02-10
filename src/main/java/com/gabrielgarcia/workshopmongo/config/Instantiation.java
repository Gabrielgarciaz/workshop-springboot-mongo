package com.gabrielgarcia.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielgarcia.workshopmongo.domain.Post;
import com.gabrielgarcia.workshopmongo.domain.Usuario;
import com.gabrielgarcia.workshopmongo.dto.AutorDTO;
import com.gabrielgarcia.workshopmongo.dto.ComentarioDTO;
import com.gabrielgarcia.workshopmongo.repository.PostRepository;
import com.gabrielgarcia.workshopmongo.repository.UsuarioRepository;


@Configuration
public class Instantiation implements CommandLineRunner{

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		usuarioRepository.deleteAll();
		
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		
		
		usuarioRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		
		Post p1 = new Post("1", sdf.parse("01/10/2020"), "O dia está bonito", "O dia começou bem no Rio de Janeiro com Sol e menos chuvas.", new AutorDTO(maria));
		Post p2 = new Post("2", sdf.parse("15/09/2022"), "O dia está chuvoso", "O dia amanheceu com Temporais na Zona Norte do Rio de Janeiro.", new AutorDTO(alex));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		maria.getPosts().add(p1);
		alex.getPosts().add(p2);	
		
		ComentarioDTO c1 = new ComentarioDTO("Verdade! Aqui também está.", sdf.parse("01/11/2020"), new AutorDTO(alex));
		ComentarioDTO c2 = new ComentarioDTO("Aqui já parou de chover.", sdf.parse("16/09/2022"), new AutorDTO(maria));
		
		p1.getComentario().add(c1);
		p2.getComentario().add(c2);
	
	}

}
