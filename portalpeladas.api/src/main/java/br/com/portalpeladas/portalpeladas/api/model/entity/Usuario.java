package br.com.portalpeladas.portalpeladas.api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @Column(name = "nomeCompleto", length = 150, nullable = false)
    private String nomeCompleto;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column
    private String apelido;

}
