package br.com.fiap.gestao_residuos.controller;

import br.com.fiap.gestao_residuos.dto.AlertaExibicaoDTO;
import br.com.fiap.gestao_residuos.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @GetMapping
    public Page<AlertaExibicaoDTO> listarTodos (
            @PageableDefault(size = 2, page = 0)
            Pageable paginacao
    ){
        return service.listarTodos(paginacao);
    }
}
