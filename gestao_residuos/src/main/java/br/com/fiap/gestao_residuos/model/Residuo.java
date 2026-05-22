package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
            generator = "SEQ_RESIDUO"
    )
    @SequenceGenerator(
            name = "SEQ_RESIDUO",
            sequenceName = "SEQ_RESIDUO",
            allocationSize = 1
    )
    private Long id;

    private String nome;
    private String tipo;
    private BigDecimal peso;

}
