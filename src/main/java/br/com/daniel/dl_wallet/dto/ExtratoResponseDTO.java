package br.com.daniel.dl_wallet.dto;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoResponseDTO {
    private String nomeUsuario;
    private BigDecimal saldoAtual;
    private List<TransacaoEntity> transacoes;
}