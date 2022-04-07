package com.starking.prova.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starking.prova.entity.Usuario;
import com.starking.prova.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        this.repository.save(usuario);
    }

    public void updateUsuario(Usuario usuario, Long id){
        var user = repository.findById(id).get();
        user.setCpf(usuario.getCpf());
        user.setEmail(usuario.getEmail());
        user.setNome(usuario.getNome());
        user.setTelefone(usuario.getTelefone());
        repository.save(user);
    }

    public List<Usuario> listar(){
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletarUsuario(Long id){
        repository.deleteById(id);
    }

}
