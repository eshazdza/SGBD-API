package com.ronfas.SGBDAPI.role;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleModelAssembler extends RepresentationModelAssemblerSupport<RoleEntity, Role> {

//    private final InscriptionModelAssembler inscriptionModelAssembler;
//
//    public RoleModelAssembler(InscriptionModelAssembler inscriptionModelAssembler) {
//        super(RoleController.class, RoleModel.class);
//        this.inscriptionModelAssembler = inscriptionModelAssembler;
//    }

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
        role.setDescription(roleEntity.getDescription());
        role.setRoleType(roleEntity.getRoleType());
//        roleModel.setUserList(inscriptionModelAssembler.toCollectionModel(role.getUsersList()));

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
