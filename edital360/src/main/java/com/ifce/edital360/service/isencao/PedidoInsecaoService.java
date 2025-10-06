package com.ifce.edital360.service.isencao;

import com.ifce.edital360.dto.isencao.PedidoIsencaoResponseDTO;
import com.ifce.edital360.model.edital.Notice;
import com.ifce.edital360.model.isencao.ArquivosIsencao;
import com.ifce.edital360.model.isencao.PedidoIsencao;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.repository.NoticeRepository;
import com.ifce.edital360.repository.PedidoIsencaoRepository;
import com.ifce.edital360.repository.UserRepository;
import com.ifce.edital360.service.localStorage.LocalStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoInsecaoService {

    private final PedidoIsencaoRepository pedidoIsencaoRepository;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final LocalStorageService localStorageService;


    public PedidoInsecaoService(PedidoIsencaoRepository pedidoIsencaoRepository, NoticeRepository noticeRepository, UserRepository userRepository, LocalStorageService localStorageService) {
        this.pedidoIsencaoRepository = pedidoIsencaoRepository;
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
        this.localStorageService = localStorageService;
    }

    @Transactional
    public PedidoIsencaoResponseDTO criarPedidoIsencao(UUID noticeId, User aplicante, List<MultipartFile> arquivos) throws IOException {

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Edital Não encontrado com o ID: " + noticeId));


        if (arquivos == null || arquivos.isEmpty() || arquivos.stream().allMatch(MultipartFile::isEmpty)) {
            throw new RuntimeException("É necessário enviar ao menos um arquivo.");
        }

        PedidoIsencao novoPedido = new PedidoIsencao();
        novoPedido.setNotice(notice);
        novoPedido.setAplicante(aplicante);

        for (MultipartFile arquivo : arquivos) {
            try {
                String nomeUnicoArquivo = localStorageService.salvar(arquivo);

                ArquivosIsencao arquivoIsencao = new ArquivosIsencao();
                arquivoIsencao.setNomeOriginalArquivo(arquivo.getOriginalFilename());
                arquivoIsencao.setCaminhoArmazenamento(nomeUnicoArquivo);
                arquivoIsencao.setTamanho(arquivo.getSize());
                arquivoIsencao.setMimeType(arquivo.getContentType());

                novoPedido.addArquivo(arquivoIsencao);

            } catch (IOException e) {
                throw new RuntimeException("Falha ao salvar o arquivo: " + arquivo.getOriginalFilename(), e);
            }
        }

        PedidoIsencao pedidoSalvo = pedidoIsencaoRepository.save(novoPedido);

        return PedidoIsencaoResponseDTO.fromEntity(pedidoSalvo);
    }

    public PedidoIsencaoResponseDTO getPedidoById(UUID pedidoId) {
        PedidoIsencao pedido = pedidoIsencaoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido de Isenção não encontrado com o ID: " + pedidoId));

        return PedidoIsencaoResponseDTO.fromEntity(pedido);
    }
}
