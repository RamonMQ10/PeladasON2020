package br.com.portalpeladas.portalpeladas.api.model.api.rest;

import br.com.portalpeladas.portalpeladas.api.model.entity.Usuario;
import br.com.portalpeladas.portalpeladas.api.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario save(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Usuario findUsuarioById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(usuario -> {
            repository.delete(usuario);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Usuario usuarioAtualizado) {
        repository.findById(id).map(usuario -> {
            usuario.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
            usuario.setApelido(usuarioAtualizado.getApelido());
            usuario.setEmail(usuarioAtualizado.getEmail());
            return repository.save(usuario);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @GetMapping("/autenticar")
    public Usuario findUsuarioByEmailSenha(@RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "senha", required = true) String senha) {
        return repository.findUsuarioByEmailSenha(email, senha);
    }

    @GetMapping("/filtrarByNotUsuarioId")
    public List<Usuario> getUsuariosNotUsuarioId(
            @RequestParam(value = "usuarioId", required = true) Integer usuarioId) {
        return repository.getUsuariosNotUsuarioId(usuarioId);
    }

}
