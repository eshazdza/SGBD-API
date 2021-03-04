package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscriptionModelAssembler implements RepresentationModelAssembler<Inscription, EntityModel<Inscription>> {
    @Override
    public EntityModel<Inscription> toModel(Inscription inscription) {
        return
                EntityModel.of(inscription,
                        linkTo(
                                methodOn(InscriptionController.class)
                                        .one(inscription.getId())
                        ).withSelfRel(),
                        linkTo(
                                methodOn(InscriptionController.class)
                                        .all()
                        ).withRel("users")

                );
    }
}
