package br.com.daniel.dl_wallet.infra.exception;

import lombok.*;

@Data
@AllArgsConstructor
public class ErroCampoDTO {
    private String campo;
    private String mensage;
}
