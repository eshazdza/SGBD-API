package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleModelAssembler roleModelAssembler;

    public RoleService(RoleRepository roleRepository, RoleModelAssembler roleModelAssembler) {
        this.roleRepository = roleRepository;
        this.roleModelAssembler = roleModelAssembler;
    }

    public CollectionModel<Role> getAllRoles() {
        return roleModelAssembler.toCollectionModel(roleRepository.findAll());
    }


    public Role getRoleById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(RoleEntity.class, "id", id.toString()));
        return roleModelAssembler.toModel(roleEntity);
    }

    public Role getRoleByType(RoleType roleType) {
        RoleEntity roleEntity = roleRepository.findByRoleType(roleType);
        if (roleEntity == null){
            throw new EntityNotFoundException(RoleEntity.class, "roleType", roleType.toString());
        }
        return roleModelAssembler.toModel(roleEntity);
    }

    public Role saveRole(RoleEntity roleEntity) {
        return roleModelAssembler.toModel(roleRepository.save(roleEntity));
    }

    public Role updateRole(RoleEntity roleEntity, Long id) {
        RoleEntity updatedRoleEntity = roleRepository.findById(id)
                .map(foundRole -> {
                    foundRole.setDescription(roleEntity.getDescription());
                    foundRole.setRoleType(roleEntity.getRoleType());
                    foundRole.setUserList(roleEntity.getUserList());
                    return roleRepository.save(foundRole);
                })
                .orElseGet(() -> {
                    roleEntity.setId(id);
                    return roleRepository.save(roleEntity);
                });
        return roleModelAssembler.toModel(updatedRoleEntity);
    }

    public void deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(RoleEntity.class, "id", id.toString());
        }
    }
}
