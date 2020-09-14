package br.com.portalpeladas.portalpeladas.api.model.api.rest;

import br.com.portalpeladas.portalpeladas.api.model.entity.Pelada;
import br.com.portalpeladas.portalpeladas.api.model.repository.PeladaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/peladas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PeladaController {

    private final PeladaRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pelada save(@RequestBody Pelada pelada) {
        return repository.save(pelada);
    }

    @GetMapping
    public List<Pelada> getAllPeladas() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Pelada findPeladaById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pelada não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(pelada -> {
            repository.delete(pelada);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pelada não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Pelada peladaAtualizado) {
        repository.findById(id).map(pelada -> {
            pelada.setNomeEventoEsportivo(peladaAtualizado.getNomeEventoEsportivo());
            pelada.setLocalEvento(peladaAtualizado.getLocalEvento());
            pelada.setDataEvento(peladaAtualizado.getDataEvento());
            pelada.setHora(peladaAtualizado.getHora());
            return repository.save(pelada);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pelada não encontrada"));
    }

    @GetMapping("filtrarByNotUsuarioId")
    public List<Pelada> findByNotUsuarioId(@RequestParam(value = "usuarioId", required = true) Integer usuarioId) {
        return repository.findByNotUsuarioId(usuarioId);
    }

}
