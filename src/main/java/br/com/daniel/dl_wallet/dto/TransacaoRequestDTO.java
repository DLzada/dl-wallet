package br.com.daniel.dl_wallet.dto;

import br.com.daniel.dl_wallet.enums.CategoriaTransacao;
import br.com.daniel.dl_wallet.enums.TipoTransacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequestDTO {
    @NotNull(message = "A descricao é obrigatoria")
    @Size(min = 3, max = 100, message = "A descrição precisa ter entre 3 e 100 caracteres")
    private String descricao;

    @NotNull(message = "O valor é obrigatorio")
    @Positive(message = "O valor deve ser maior que zero!")
    private BigDecimal valor;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaTransacao categoria;

    @NotNull(message = "O tipo da transacao é obrigatorio")
    private TipoTransacao tipoTransacao;

    @NotNull(message = "O id do usuario é obrigatorio")
    private Long usuarioId;
}
