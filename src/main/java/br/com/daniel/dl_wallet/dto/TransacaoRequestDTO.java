package br.com.daniel.dl_wallet.dto;

import br.com.daniel.dl_wallet.enums.CategoriaTransacao;
import br.com.daniel.dl_wallet.enums.TipoTransacao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequestDTO {
    @NotNull(message = "A descricao é obrigatoria")
    private String descricao;

    @NotNull(message = "O valor é obrigatorio")
    private BigDecimal valor;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaTransacao categoria;

    @NotNull(message = "O tipo da transacao é obrigatorio")
    private TipoTransacao tipoTransacao;

    @NotNull(message = "O id do usuario é obrigatorio")
    private Long usuarioId;
}
