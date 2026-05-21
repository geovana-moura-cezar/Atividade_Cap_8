package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_alerta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Alerta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ALERTA"
    )
    @SequenceGenerator(
            name = "SEQ_ALERTA",
            sequenceName = "SEQ_ALERTA",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private Integer nivel;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @JoinColumn(name = "local_coleta_id")
    @ManyToOne
    private LocalColeta localColeta;
}
