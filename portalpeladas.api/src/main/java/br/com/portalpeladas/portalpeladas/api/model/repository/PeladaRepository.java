package br.com.portalpeladas.portalpeladas.api.model.repository;

import br.com.portalpeladas.portalpeladas.api.model.entity.Pelada;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeladaRepository extends JpaRepository<Pelada, Integer> {

    @Query(" select pe from Pelada pe " + " where pe.peladaId not in ( "
            + " select p.pelada.peladaId from PeladaUsuario p " + " where p.usuario.usuarioId = :usuarioId " + " ) ")
    List<Pelada> findByNotUsuarioId(@Param("usuarioId") Integer usuarioId);

}
