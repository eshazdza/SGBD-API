package com.ronfas.SGBDAPI.role;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleModelAssembler extends RepresentationModelAssemblerSupport<RoleEntity, Role> {

    public RoleModelAssembler() {
        super(RoleController.class, Role.class);
    }

    @Override
    public Role toModel(RoleEntity roleEntity) {
        Role role = instantiateModel(roleEntity);
        role.add(
                linkTo(
                        methodOn(RoleController.class)
                                .one(roleEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withRel("roles")
        );

        role.setId(roleEntity.getId());
        role.setRoleType(roleEntity.getRoleType());
        role.setDescription(roleEntity.getDescription());

//        TODO SETTERS RELATIONS

        return role;
    }

    @Override
    public CollectionModel<Role> toCollectionModel(Iterable<? extends RoleEntity> roles) {
        CollectionModel<Role> roleModels = super.toCollectionModel(roles);
        roleModels.add(
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withSelfRel()
        );
        return roleModels;
    }

}
