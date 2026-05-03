package br.com.daniel.dl_wallet.database.repository;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ITransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
    List<TransacaoEntity> findAllByUsuarioId(Long usuarioId );
    List<TransacaoEntity> findAllByUsuarioIdAndDataBetween(Long usuarioId, LocalDate dataInicio, LocalDate dataFim);
}