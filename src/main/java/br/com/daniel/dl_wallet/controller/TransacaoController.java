package br.com.daniel.dl_wallet.controller;

import br.com.daniel.dl_wallet.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getSaldo(@PathVariable Long usuarioId){
        return transacaoService.calcularSaldo(usuarioId);
    }
}
