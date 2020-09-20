package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.exception.GrupoNaoEncontradoException;
import br.com.luciano.delivery.domain.entity.Group;
import br.com.luciano.delivery.domain.repository.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public Group save(Group group) {
        return this.groupRepository.save(group);
    }

    public Group findByIdOrFail(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Group update(Group group, Long id) {
        Group groupSalvo = this.findByIdOrFail(id);

        BeanUtils.copyProperties(group, groupSalvo, "id");

        return save(groupSalvo);
    }

    public List<Group> findAll() {
        return this.groupRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        Group group = findByIdOrFail(id);

        this.groupRepository.delete(group);
    }
}
