package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.PermissionEntity;
import br.com.luciano.delivery.domain.exception.GroupNotFoundException;
import br.com.luciano.delivery.domain.repository.PermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public PermissionEntity save(PermissionEntity permission) {
        return this.permissionRepository.save(permission);
    }

    public PermissionEntity findByIdOrFail(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Transactional
    public PermissionEntity update(PermissionEntity permission, Long id) {
        PermissionEntity permissionEntityActual = this.findByIdOrFail(id);

        BeanUtils.copyProperties(permission, permissionEntityActual, "id");

        return save(permissionEntityActual);
    }

    public List<PermissionEntity> findAll() {
        return this.permissionRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        PermissionEntity group = findByIdOrFail(id);

        this.permissionRepository.delete(group);
    }
}
