package com.gabrielgarcia.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielgarcia.workshopmongo.domain.Usuario;
import com.gabrielgarcia.workshopmongo.dto.UsuarioDTO;
import com.gabrielgarcia.workshopmongo.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService servico;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		List<Usuario> list = servico.findAll();
		List<UsuarioDTO> listDTO = list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList()); // Passando o Usuario para a sobrecarga do DTO
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable String id){
		Usuario obj = servico.findById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(obj)); // Convertando o obj para UsuarioDTO
	}
	
	@PostMapping
	public ResponseEntity<Void> insertUsuario(@RequestBody UsuarioDTO objDTO){
		Usuario obj = servico.fromDTO(objDTO); // Transforma o UsuarioDTO em Usuario 
		obj = servico.insertUsuario(obj); // Insere o usuário
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // Pega o endereço do novo objeto inserido
		return ResponseEntity.created(uri).build(); // Created retorna o código 201(código de novo recurso) - com o cabecalho contendo o endereço uri
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable String id){
		servico.deleteUsuario(id);
		return ResponseEntity.noContent().build(); // Resposta 204, código que não tem que retornar nada
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> updateUsuario(@RequestBody UsuarioDTO objDTO, @PathVariable String id){
		Usuario obj = servico.fromDTO(objDTO);  // Transforma o UsuarioDTO em Usuario 
		obj.setId(id);
		obj = servico.updateUsuario(obj);
		return ResponseEntity.noContent().build();
	}
}
