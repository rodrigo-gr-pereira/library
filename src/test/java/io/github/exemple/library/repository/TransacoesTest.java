package io.github.exemple.library.repository;

import io.github.exemple.library.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirmar alteraçoes
     * Roolback -> desfazer alteracoes
     */
    @Test
    @Transactional
    void transacaoSimples(){
        transacaoService.executar();
    }

    @Test
    void atualizarSemAtualizar(){
        transacaoService.atualizacaoSemAtualizar();
    }


}
