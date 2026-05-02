package br.com.daniel.dl_wallet.database.repository;

import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
