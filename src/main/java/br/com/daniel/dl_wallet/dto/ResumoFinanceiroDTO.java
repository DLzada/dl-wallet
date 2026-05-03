package br.com.daniel.dl_wallet.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
public class ResumoFinanceiroDTO {
    private List<CategoriaValorDTO> entradas;
    private List<CategoriaValorDTO> saidas;
}
