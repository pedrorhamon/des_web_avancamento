package com.starking.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.prova.DTO.UsuarioRequest;
import com.starking.prova.DTO.UsuarioResponse;
import com.starking.prova.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Primeira-avaliação-web")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Salva um novo usuário")
    public ResponseEntity<?> salvar(@RequestBody UsuarioRequest request){
        service.salvarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @ApiOperation(value= "Lista todos os usuários do banco")
    public ResponseEntity<?> listarTodos(){
        return ResponseEntity.ok(this.service.listar());
    }

    @GetMapping("/{id}")
    @ApiOperation(value= "Busca um usuário no banco")
    public UsuarioResponse buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value= "Atualiza um usuário no banco")
    public void atualizar(@RequestBody UsuarioRequest request, @PathVariable Long id){
        service.updateUsuario(request, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value= "Deleta um usuário do banco")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id) {
        service.deletarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
