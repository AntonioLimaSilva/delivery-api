package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.GrupoNaoEncontradoException;
import br.com.luciano.delivery.domain.model.Grupo;
import br.com.luciano.delivery.domain.repository.GrupoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }

    public Grupo buscarPor(Long id) {
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Grupo atualizar(Grupo grupo, Long id) {
        Grupo grupoSalvo = this.buscarPor(id);

        BeanUtils.copyProperties(grupo, grupoSalvo, "id");

        return salvar(grupoSalvo);
    }

    public List<Grupo> buscarTodos() {
        return this.grupoRepository.findAll();
    }

    @Transactional
    public void excluir(Long idGrupo) {
        Grupo grupo = buscarPor(idGrupo);

        this.grupoRepository.delete(grupo);
    }
}
