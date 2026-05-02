package br.com.daniel.dl_wallet.service;

import br.com.daniel.dl_wallet.database.repository.IUsuarioRepository;
import br.com.daniel.dl_wallet.dto.UsuarioRequestDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@@AllArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    @Transactional
    public void salvar(UsuarioRequestDTO dto){

    }
}
