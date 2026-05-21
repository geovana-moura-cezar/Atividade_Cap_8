package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.LocalColetaCadastroDTO;
import br.com.fiap.gestao_residuos.dto.LocalColetaExibicaoDTO;
import br.com.fiap.gestao_residuos.service.LocalColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/local-coleta")
public class LocalColetaController {

    @Autowired
    private LocalColetaService service;

    //LISTAR TODOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<LocalColetaExibicaoDTO> listarTodos(
            @PageableDefault(size = 2, page = 0)
            Pageable paginacao
    ){
        return service.listarTodos(paginacao);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<LocalColetaExibicaoDTO> buscarPorId(@PathVariable Long id){
            return ResponseEntity
                    .ok(service.buscarPorId(id));
    }

    // CADASTRAR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocalColetaExibicaoDTO cadastrar(@RequestBody @Valid LocalColetaCadastroDTO localColeta){
        return service.cadastrar(localColeta);
    }

    // ATUALIZAR
    @PutMapping
    public ResponseEntity<LocalColetaExibicaoDTO> atualizar (@RequestBody @Valid LocalColetaCadastroDTO localColeta){
        LocalColetaExibicaoDTO localColetaExibicaoDTO =
                service.atualizar(localColeta);
        return ResponseEntity.ok(localColetaExibicaoDTO);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }

}
