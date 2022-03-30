package com.starking.prova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starking.prova.DTO.UsuarioRequest;
import com.starking.prova.DTO.UsuarioResponse;
import com.starking.prova.entity.Usuario;
import com.starking.prova.exception.EntityNotFoundException;
import com.starking.prova.repository.UsuarioRepository;
import com.starking.prova.utils.ConverterUtils;

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

    public void salvarUsuario(UsuarioRequest request){
        this.repository.save(
                (Usuario) converterUtils.convertRequestToEntity(request, Usuario.class)
        );
    }

    public void updateUsuario(UsuarioRequest request, Long id){
         var entity = this.repository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("entidade não encontrada"));
         var entityUpdated = (Usuario) converterUtils.convertRequestToEntity(request, entity.getClass());
         entityUpdated.setId(id);
         this.repository.save(entityUpdated);
    }

    public List<UsuarioResponse> listar(){
        return (List<UsuarioResponse>) converterUtils
                .convertToListResponse(this.repository.findAll(), UsuarioResponse.class);
    }

    public UsuarioResponse buscarPorId(Long id){
        return (UsuarioResponse)
                converterUtils.convertEntityToResponse(this.repository.findById(id).get(),
                        UsuarioResponse.class);
    }

    public void deletarUsuario(Long id){
        this.repository.deleteById(id);
    }
}
