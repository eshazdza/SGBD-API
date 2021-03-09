package com.ronfas.SGBDAPI.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
    CollectionModel<RoleModel> all() {
        return CollectionModel.of(this.roleService.getAllRoles(), linkTo(
                methodOn(RoleController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    RoleModel one(
            @PathVariable Long id
    ) {
        return this.roleService.getRoleById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newRole(
            @RequestBody @Valid Role newRole
    ) {
        RoleModel roleEntityModel = this.roleService.saveRole(newRole);

        return ResponseEntity.created(
                roleEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(roleEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateRole(
            @RequestBody @Valid Role role,
            @PathVariable Long id
    ) {
        RoleModel roleEntityModel = this.roleService.updateRole(role, id);

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
