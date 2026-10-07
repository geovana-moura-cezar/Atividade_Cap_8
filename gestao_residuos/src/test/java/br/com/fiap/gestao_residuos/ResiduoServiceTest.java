package br.com.fiap.gestao_residuos;

import br.com.fiap.gestao_residuos.dto.ResiduoCadastroDTO;
import br.com.fiap.gestao_residuos.dto.ResiduoExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Residuo;
import br.com.fiap.gestao_residuos.repository.ResiduoRepository;
import br.com.fiap.gestao_residuos.service.ResiduoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResiduoServiceTest {

    @Mock
    private ResiduoRepository repository;

    @InjectMocks
    private ResiduoService service;

    @Test
    void deveCadastrarResiduo() {
        ResiduoCadastroDTO entrada = new ResiduoCadastroDTO(
                null, "Garrafa PET", "PLASTICO", new BigDecimal("2.50"));

        when(repository.save(any(Residuo.class))).thenAnswer(invocation -> {
            Residuo residuo = invocation.getArgument(0);
            residuo.setId(1L);
            return residuo;
        });

        ResiduoExibicaoDTO resultado = service.cadastrar(entrada);

        assertEquals(1L, resultado.id());
        assertEquals("Garrafa PET", resultado.nome());
        assertEquals("PLASTICO", resultado.tipo());
        assertEquals(new BigDecimal("2.50"), resultado.peso());
        verify(repository).save(any(Residuo.class));
    }

    @Test
    void deveBuscarResiduoPorId() {
        Residuo residuo = new Residuo(
                10L, "Papel branco", "PAPEL", new BigDecimal("5.00"));

        when(repository.findById(10L)).thenReturn(Optional.of(residuo));

        ResiduoExibicaoDTO resultado = service.buscarPorId(10L);

        assertEquals(10L, resultado.id());
        assertEquals("Papel branco", resultado.nome());
        verify(repository).findById(10L);
    }

    @Test
    void deveFalharAoBuscarResiduoInexistente() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException erro = assertThrows(
                RuntimeException.class,
                () -> service.buscarPorId(999L));

        assertEquals("Residuo não encontrado!", erro.getMessage());
    }
}
