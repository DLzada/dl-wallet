package br.com.daniel.dl_wallet.service;

import br.com.daniel.dl_wallet.database.model.TransacaoEntity;
import br.com.daniel.dl_wallet.database.model.UsuarioEntity;
import br.com.daniel.dl_wallet.database.repository.ITransacaoRepository;
import br.com.daniel.dl_wallet.database.repository.IUsuarioRepository;
import br.com.daniel.dl_wallet.dto.TransacaoRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final ITransacaoRepository transacaoRepository;
    private final IUsuarioRepository usuarioRepository;

    @Transactional
    public TransacaoEntity salvar(TransacaoRequestDTO dto){
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        TransacaoEntity transacao = TransacaoEntity.builder()
                .descricao(dto.getDescricao())
                .valor(dto.getValor())
                .tipo(dto.getTipoTransacao())
                .data(LocalDate.now())
                .usuario(usuario)
                .build();

        return transacaoRepository.save(transacao);
    }
}
