package com.santander.banco811.repositories;

import com.santander.banco811.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void quando_banco_vazio_retornar_vazio() {
        var usuarios = usuarioRepository.findAll();
        Assertions.assertEquals(Arrays.asList(), usuarios);
    }

    @Test
    public void quando_banco_nao_vazio_retornar_usuarios_salvos() {
        Usuario usuario = Usuario.builder().nome("Jonas").senha("senha01").cpf("1234567891521").build();
        Usuario usuario2 = Usuario.builder().nome("Marcia").senha("senha02").cpf("987456321369").build();
        Usuario usuario3 = Usuario.builder().nome("Gustavo").senha("senha03").cpf("456321789789").build();

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        var usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
    }
}