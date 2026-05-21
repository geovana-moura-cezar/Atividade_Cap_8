package br.com.fiap.gestao_residuos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USUARIO"
    )
    @SequenceGenerator(
            name = "SEQ_USUARIO",
            sequenceName = "SEQ_USUARIO",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;
}
