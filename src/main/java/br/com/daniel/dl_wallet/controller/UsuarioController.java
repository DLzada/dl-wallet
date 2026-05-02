package br.com.daniel.dl_wallet.controller;

import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import br.com.daniel.dl_wallet.dto.UsuarioRequestDTO;
import br.com.daniel.dl_wallet.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEntity salvar(@RequestBody @Valid UsuarioRequestDTO dto){
        return usuarioService.salvar(dto);
    }
}
