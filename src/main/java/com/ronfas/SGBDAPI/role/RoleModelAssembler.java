package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.inscription.InscriptionModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleModelAssembler extends RepresentationModelAssemblerSupport<Role, RoleModel> {

    private final InscriptionModelAssembler inscriptionModelAssembler;

    public RoleModelAssembler(InscriptionModelAssembler inscriptionModelAssembler) {
        super(RoleController.class, RoleModel.class);
        this.inscriptionModelAssembler = inscriptionModelAssembler;
    }

    @Override
    public RoleModel toModel(Role role) {
        RoleModel roleModel = instantiateModel(role);
        roleModel.add(
                linkTo(
                        methodOn(RoleController.class)
                                .one(role.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withRel("roles")
        );

        roleModel.setId(role.getId());
        roleModel.setDescription(role.getDescription());
        roleModel.setRoleType(role.getRoleType());
        roleModel.setUserList(inscriptionModelAssembler.toCollectionModel(role.getUsersList()));

        return roleModel;
    }

    @Override
    public CollectionModel<RoleModel> toCollectionModel(Iterable<? extends Role> roles) {
        CollectionModel<RoleModel> roleModels = super.toCollectionModel(roles);
        roleModels.add(
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withSelfRel()
        );
        return roleModels;
    }

}
