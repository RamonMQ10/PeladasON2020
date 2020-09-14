package br.com.portalpeladas.portalpeladas.api.model.repository;

import br.com.portalpeladas.portalpeladas.api.model.entity.PeladaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeladaUsuarioRepository extends JpaRepository<PeladaUsuario, Integer> {

        @Query(" select p from PeladaUsuario p join p.usuario u " + " where u.usuarioId =:usuarioId ")
        List<PeladaUsuario> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

        @Query(" select p from PeladaUsuario p join p.pelada pe " + " where pe.peladaId =:peladaId ")
        List<PeladaUsuario> findByPeladaId(@Param("peladaId") Integer peladaId);

        @Query(" select p from PeladaUsuario p join p.pelada pe " + " join p.usuario u "
                        + " where u.usuarioId =:usuarioId and pe.peladaId =:peladaId ")
        PeladaUsuario findByUsuarioIdPeladaId(@Param("usuarioId") Integer usuarioId,
                        @Param("peladaId") Integer peladaId);

}
