package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_descarte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Descarte {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DESCARTE"
    )
    @SequenceGenerator(
            name = "SEQ_DESCARTE",
            sequenceName = "SEQ_DESCARTE",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private LocalDate dataDescarte;

    private BigDecimal quantidade;
    private String status;

    @ManyToOne@JoinColumn(name = "residuo_id", nullable = false)
    private Residuo residuo;

    @JoinColumn(name = "local_coleta_id", nullable = false)
    @ManyToOne
    private LocalColeta localColeta;
}
