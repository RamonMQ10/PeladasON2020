package br.com.portalpeladas.portalpeladas.api.model.repository;

import br.com.portalpeladas.portalpeladas.api.model.entity.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(" select u from Usuario u " + " where u.email = :email and u.senha = :senha ")
    Usuario findUsuarioByEmailSenha(@Param("email") String email, @Param("senha") String senha);

    @Query(" select u from Usuario u " + " where u.usuarioId <> :usuarioId ")
    List<Usuario> getUsuariosNotUsuarioId(@Param("usuarioId") Integer usuarioId);

}
