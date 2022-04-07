package com.starking.prova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.starking.prova.DTO.UsuarioRequest;
import com.starking.prova.DTO.UsuarioResponse;
import com.starking.prova.entity.Usuario;
import com.starking.prova.exception.EntityNotFoundException;
import com.starking.prova.exception.handler.ResourceNotFoundException;
import com.starking.prova.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ConverterUtils converterUtils;

    public UsuarioService(UsuarioRepository repository, ConverterUtils converterUtils) {
        this.repository = repository;
        this.converterUtils = converterUtils;
    }

    public List<UsuarioResponse> listar(){
        return (List<UsuarioResponse>) converterUtils
                .convertToListResponse(this.repository.findAll(), UsuarioResponse.class);

    }

    public void salvarUsuario(UsuarioRequest request){
        this.repository.save(
                (Usuario) converterUtils.convertRequestToEntity(request, Usuario.class)
        );
    }

    public UsuarioResponse buscarPorId(Long id){
        return (UsuarioResponse)
        converterUtils.convertEntityToResponse(this.repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found")),UsuarioResponse.class);
    }

    public void updateUsuario(UsuarioRequest request, Long id){
         var entity = this.repository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
         var entityUpdated = (Usuario) converterUtils.convertRequestToEntity(request, entity.getClass());
         entityUpdated.setId(id);
         this.repository.save(entityUpdated);
    }

    public void deletarUsuario(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

}