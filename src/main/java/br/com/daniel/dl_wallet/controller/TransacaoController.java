package br.com.daniel.dl_wallet.controller;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import br.com.daniel.dl_wallet.dto.TransacaoRequestDTO;
import br.com.daniel.dl_wallet.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoEntity criar(@Valid @RequestBody TransacaoRequestDTO dto){
        return transacaoService.salvar(dto);
    }

    @GetMapping("/saldo/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal buscarSaldo(@PathVariable Long usuarioId){
        return transacaoService.calcularSaldo(usuarioId);
    }
}
