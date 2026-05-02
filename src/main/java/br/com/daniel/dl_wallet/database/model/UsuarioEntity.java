package br.com.daniel.dl_wallet.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String nome;

    @Column(nullable = false, unique = true)
    String email;
}
