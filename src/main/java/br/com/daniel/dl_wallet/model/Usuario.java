package br.com.daniel.dl_wallet.model;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    Long id;

    String nome;

    @Column(unique = true)
    String email;
}
