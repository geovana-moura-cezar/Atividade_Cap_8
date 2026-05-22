package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_local_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LocalColeta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_LOCAL_COLETA"
    )
    @SequenceGenerator(
            name = "SEQ_LOCAL_COLETA",
            sequenceName = "SEQ_LOCAL_COLETA",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(name = "capacidade_maxima", nullable = false)
    private BigDecimal capacidadeMaxima;

}
