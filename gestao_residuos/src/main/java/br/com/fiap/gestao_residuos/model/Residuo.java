package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_residuo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Residuo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_RESIDUOS"
    )
    @SequenceGenerator(
            name = "SEQ_RESIDUOS",
            sequenceName = "SEQ_RESIDUOS",
            allocationSize = 1
    )
    private Long id;

    private String nome;
    private String tipo;
    private Double peso;

}
