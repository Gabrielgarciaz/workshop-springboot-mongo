package com.gabrielgarcia.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielgarcia.workshopmongo.domain.Usuario;
import com.gabrielgarcia.workshopmongo.dto.UsuarioDTO;
import com.gabrielgarcia.workshopmongo.repository.UsuarioRepository;
import com.gabrielgarcia.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario findById(String id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Usuario insertUsuario(Usuario obj) {
		return repo.insert(obj);
	}
	
	public Usuario fromDTO(UsuarioDTO objDTO) {
		return new Usuario(objDTO.getId(), objDTO.getNome(),objDTO.getEmail());
	}
	
	public void deleteUsuario(String id) {
		findById(id); // Caso não encontrar o obj vai lançar a exceção que está no findbyId
		repo.deleteById(id);
	}
	
	public Usuario updateUsuario(Usuario obj) {
		Usuario newObj = findById(obj.getId()); // Pegando usuario no banco de Dados
		updateData(newObj, obj); // Responsável de passar os dados do obj para o new obj
		return repo.save(newObj);
		}
	public Usuario updateData(Usuario newObj, Usuario obj) { 
		newObj.setEmail(obj.getEmail());
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		return newObj;
	}
}
