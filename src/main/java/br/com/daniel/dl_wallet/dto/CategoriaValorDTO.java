package br.com.daniel.dl_wallet.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class CategoriaValorDTO {
    private String categoria;
    private BigDecimal total;
}