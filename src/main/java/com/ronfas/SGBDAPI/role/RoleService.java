package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleModelAssembler roleModelAssembler;

    public RoleService(RoleRepository roleRepository, RoleModelAssembler roleModelAssembler) {
        this.roleRepository = roleRepository;
        this.roleModelAssembler = roleModelAssembler;
    }

    public List<EntityModel<Role>> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleModelAssembler::toModel)
                .collect(Collectors.toList());
    }


    public EntityModel<Role> getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Role.class, "id", id.toString()));
        return roleModelAssembler.toModel(role);
    }

    public EntityModel<Role> getRoleByType(RoleType roleType) {
        Role role = roleRepository.findByRoleType(roleType);
        if (role == null){
            throw new EntityNotFoundException(Role.class, "roleType", roleType.toString());
        }
        return roleModelAssembler.toModel(role);
    }

    public EntityModel<Role> saveRole(Role role) {
        return roleModelAssembler.toModel(roleRepository.save(role));
    }

    public EntityModel<Role> updateRole(Role role, Long id) {
        Role updatedRole = roleRepository.findById(id)
                .map(foundRole -> {
                    foundRole.setDescription(role.getDescription());
                    foundRole.setRoleType(role.getRoleType());
                    foundRole.setUsersList(role.getUsersList());
                    return roleRepository.save(foundRole);
                })
                .orElseGet(() -> {
                    role.setId(id);
                    return roleRepository.save(role);
                });
        return roleModelAssembler.toModel(updatedRole);
    }

    public void deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(Role.class, "id", id.toString());
        }
    }
}
