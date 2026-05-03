package br.com.daniel.dl_wallet.controller;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import br.com.daniel.dl_wallet.dto.ExtratoResponseDTO;
import br.com.daniel.dl_wallet.dto.TransacaoRequestDTO;
import br.com.daniel.dl_wallet.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransacaoEntity> listarPorUsuario(@PathVariable Long usuarioId){
        return transacaoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/saldo/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal buscarSaldo(@PathVariable Long usuarioId){
        return transacaoService.calcularSaldo(usuarioId);
    }

    @GetMapping("/extrato/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransacaoEntity> buscarExtrato(
            @PathVariable Long usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim
            ){
        return transacaoService.buscarExtratoPorPeriodo(usuarioId, inicio, fim);
    }

    @GetMapping("/extrato-completo/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public ExtratoResponseDTO buscarExtratoCompleto(@PathVariable Long usuarioId){
        return transacaoService.buscarExtratoCompleto(usuarioId);
    }
}
