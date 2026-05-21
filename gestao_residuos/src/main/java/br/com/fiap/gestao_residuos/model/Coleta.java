package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coleta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COLETA"
    )
    @SequenceGenerator(
            name = "SEQ_COLETA",
            sequenceName = "SEQ_COLETA",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @Column(name = "data_realizacao")
    private LocalDate dataRealizacao;

    @JoinColumn(name = "local_coleta_id")
    @ManyToOne
    private LocalColeta localColeta;
}
