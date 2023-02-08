package com.gabrielgarcia.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
