package br.com.daniel.dl_wallet.dto;

import br.com.daniel.dl_wallet.enums.TipoTransacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransacaoResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private TipoTransacao tipo;
    private String nomeUsuario;
}