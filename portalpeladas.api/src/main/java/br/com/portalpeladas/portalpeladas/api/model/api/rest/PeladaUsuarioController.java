package br.com.portalpeladas.portalpeladas.api.model.api.rest;

import br.com.portalpeladas.portalpeladas.api.model.entity.PeladaUsuario;
import br.com.portalpeladas.portalpeladas.api.model.repository.PeladaUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/peladas-usuarios")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PeladaUsuarioController {

    private final PeladaUsuarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeladaUsuario save(@RequestBody PeladaUsuario peladaUsuario) {
        return repository.save(peladaUsuario);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(peladaUsuario -> {
            repository.delete(peladaUsuario);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dados não encontrados"));
    }

    @GetMapping("usuario")
    public List<PeladaUsuario> findByUsuarioId(@RequestParam(value = "usuarioId", required = true) Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    @GetMapping("pelada")
    public List<PeladaUsuario> findByPeladaId(@RequestParam(value = "peladaId", required = true) Integer peladaId) {
        return repository.findByPeladaId(peladaId);
    }

    @GetMapping("usuario-pelada")
    public PeladaUsuario findByUsuarioIdPeladaId(@RequestParam(value = "usuarioId", required = true) Integer usuarioId,
            @RequestParam(value = "peladaId", required = true) Integer peladaId) {
        return repository.findByUsuarioIdPeladaId(usuarioId, peladaId);
    }

}
