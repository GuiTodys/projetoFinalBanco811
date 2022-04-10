package com.santander.banco811.repositories;

import com.santander.banco811.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("select c from Usuario c where (c.cpf=:cpf and c.nome=:nome)")
    List<Usuario> findByNomeAndCpf(
            @Param("cpf") String cpf,
            @Param("nome") String nome);

    List<Usuario> findAllByOrderByIdAsc();
}
