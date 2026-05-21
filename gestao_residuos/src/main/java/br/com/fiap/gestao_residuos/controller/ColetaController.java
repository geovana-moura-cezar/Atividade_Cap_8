package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.ColetaCadastroDTO;
import br.com.fiap.gestao_residuos.dto.ColetaExibicaoDTO;
import br.com.fiap.gestao_residuos.service.ColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/coletas")
public class ColetaController {

        @Autowired
        private ColetaService service;

        //LISTAR TODOS
        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public Page<ColetaExibicaoDTO> listarTodos(
                @PageableDefault(size = 2, page = 0)
                Pageable paginacao
        ){
            return service.listarTodos(paginacao);
        }

        // BUSCAR POR ID
        @GetMapping("/{id}")
        public ResponseEntity<ColetaExibicaoDTO> buscarPorId(@PathVariable Long id){
            return ResponseEntity
                    .ok(service.buscarPorId(id));

        }

        // CADASTRAR
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ColetaExibicaoDTO cadastrar(@RequestBody @Valid ColetaCadastroDTO coleta){
            return service.cadastrar(coleta);
        }

        // ATUALIZAR
        @PutMapping
        public ResponseEntity<ColetaExibicaoDTO> atualizar (@RequestBody @Valid ColetaCadastroDTO coleta){
            ColetaExibicaoDTO coletaExibicaoDTO =
                    service.atualizar(coleta);
            return ResponseEntity.ok(coletaExibicaoDTO);
        }

        // EXCLUIR
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void excluir(@PathVariable Long id){
            service.excluir(id);
        }

}
