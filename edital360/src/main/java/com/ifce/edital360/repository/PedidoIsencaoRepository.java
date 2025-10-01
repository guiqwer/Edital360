package com.ifce.edital360.repository;

import com.ifce.edital360.model.edital.Notice;
import com.ifce.edital360.model.isencao.PedidoIsencao;
import com.ifce.edital360.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoIsencaoRepository extends JpaRepository<PedidoIsencao, UUID> {

    /**
     * Verifica de forma eficiente se já existe um registro de PedidoIsencao
     * para uma combinação específica de Edital (Notice) e Candidato (User).
     * O Spring Data JPA cria a consulta SQL correspondente a este nome de método.
     *
     * @param notice O edital a ser verificado.
     * @param aplicante O usuário candidato a ser verificado.
     * @return true se já existir um pedido, false caso contrário.
     */
    boolean existsByNoticeAndAplicante(Notice notice, User aplicante);
}