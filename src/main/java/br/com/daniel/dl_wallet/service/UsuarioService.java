package br.com.daniel.dl_wallet.service;

import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import br.com.daniel.dl_wallet.database.repository.IUsuarioRepository;
import br.com.daniel.dl_wallet.dto.UsuarioRequestDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    @Transactional
    public UsuarioEntity salvar(UsuarioRequestDTO dto){
        if(usuarioRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Ja existe um usuario com esse email!");
        }

        UsuarioEntity usuario = UsuarioEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .build();

        return usuarioRepository.save(usuario);
    }
}
