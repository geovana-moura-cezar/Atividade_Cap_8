package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.UsuarioCadastroDTO;
import br.com.fiap.gestao_residuos.dto.UsuarioExibicaoDTO;
import br.com.fiap.gestao_residuos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //LISTAR TODOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDTO> listarTodos(
            @PageableDefault(size = 2, page = 0)
            Pageable paginacao
    ){
        return service.listarTodos(paginacao);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity
                    .ok(service.buscarPorId(id));

    }

    // ATUALIZAR
    @PutMapping
    public ResponseEntity<UsuarioExibicaoDTO> atualizar (@RequestBody @Valid UsuarioCadastroDTO usuario){
        UsuarioExibicaoDTO usuarioExibicaoDTO =
                    service.atualizar(usuario);return ResponseEntity.ok(usuarioExibicaoDTO);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }

}
