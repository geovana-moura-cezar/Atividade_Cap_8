package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.DescarteCadastroDTO;
import br.com.fiap.gestao_residuos.dto.DescarteExibicaoDTO;
import br.com.fiap.gestao_residuos.service.DescarteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/descartes")
public class DescarteController {

    @Autowired
    private DescarteService service;

    //LISTAR TODOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DescarteExibicaoDTO> listarTodos(
            @PageableDefault(size = 2, page = 0)
            Pageable paginacao
    ){
        return service.listarTodos(paginacao);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<DescarteExibicaoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity
                    .ok(service.buscarPorId(id));
    }

    // CADASTRAR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DescarteExibicaoDTO cadastrar(@RequestBody @Valid DescarteCadastroDTO descarte){
        return service.cadastrar(descarte);
    }

    // ATUALIZAR
    @PutMapping
    public ResponseEntity<DescarteExibicaoDTO> atualizar (@RequestBody @Valid DescarteCadastroDTO descarte){
        DescarteExibicaoDTO descarteExibicaoDTO =
                    service.atualizar(descarte);
        return ResponseEntity.ok(descarteExibicaoDTO);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }


}
