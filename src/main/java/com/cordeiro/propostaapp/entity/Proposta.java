package com.cordeiro.propostaapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorSolicitado;

    private int prazoPagamento;

    private Boolean aprovada;

    private boolean integrada;

    private String observacao;
    /*Como a proposta é dependente de um usuário, para não criar o código do método convercional, criando
     repository para a entity Usuário e relacionado, podemos persistir com o JPA com o comentário a seguir
     dentro do @onetoone : cascade = CascadeType.PERSIST. Irá persistir os dados que vamos colocar no body
     da requisição POST no nosso banco de dados, e assim assimilar a proposta a tal usuário cadastrado.
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;

}
