package com.ronfas.SGBDAPI.role;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public CollectionModel<Role> all() {
        return CollectionModel.of(this.roleService.getAllRoles(), linkTo(
                methodOn(RoleController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Role one(
            @PathVariable Long id
    ) {
        return this.roleService.getRoleById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newRole(
            @RequestBody @Valid RoleEntity newRoleEntity
    ) {
        Role roleEntityModel = this.roleService.saveRole(newRoleEntity);

        return ResponseEntity.created(
                roleEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(roleEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateRole(
            @RequestBody @Valid RoleEntity roleEntity,
            @PathVariable Long id
    ) {
        Role roleEntityModel = this.roleService.updateRole(roleEntity, id);

        return ResponseEntity
                .created(roleEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(roleEntityModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
