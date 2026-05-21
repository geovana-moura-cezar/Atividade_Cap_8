package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.ResiduoCadastroDTO;
import br.com.fiap.gestao_residuos.dto.ResiduoExibicaoDTO;
import br.com.fiap.gestao_residuos.service.ResiduoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/residuos")
public class ResiduoController {

    @Autowired
    private ResiduoService service;

    //LISTAR TODOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ResiduoExibicaoDTO> listarTodos(
            @PageableDefault(size = 2, page = 0)
            Pageable paginacao
    ){
        return service.listarTodos(paginacao);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ResiduoExibicaoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity
                    .ok(service.buscarPorId(id));

    }

    // CADASTRAR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResiduoExibicaoDTO cadastrar(@RequestBody @Valid ResiduoCadastroDTO residuo){
        return service.cadastrar(residuo);
    }

    // ATUALIZAR
    @PutMapping
    public ResponseEntity<ResiduoExibicaoDTO> atualizar (@RequestBody @Valid ResiduoCadastroDTO residuo){
        ResiduoExibicaoDTO residuoExibicaoDTO =
                    service.atualizar(residuo);
        return ResponseEntity.ok(residuoExibicaoDTO);

    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.excluir(id);
    }

}
