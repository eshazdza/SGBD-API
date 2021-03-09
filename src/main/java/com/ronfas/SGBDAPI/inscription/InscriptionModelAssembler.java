package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.classes.ClasseModelAssembler;
import com.ronfas.SGBDAPI.role.RoleModelAssembler;
import com.ronfas.SGBDAPI.user.UserModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscriptionModelAssembler extends RepresentationModelAssemblerSupport<InscriptionEntity, Inscription> {

    private final ClasseModelAssembler classeModelAssembler;
    private final UserModelAssembler userModelAssembler;
    private final RoleModelAssembler roleModelAssembler;

    public InscriptionModelAssembler(ClasseModelAssembler classeModelAssembler, UserModelAssembler userModelAssembler, RoleModelAssembler roleModelAssembler) {
        super(InscriptionController.class, Inscription.class);
        this.classeModelAssembler = classeModelAssembler;
        this.userModelAssembler = userModelAssembler;
        this.roleModelAssembler = roleModelAssembler;
    }

    @Override
    public Inscription toModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = instantiateModel(inscriptionEntity);
        inscription.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .one(inscriptionEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withRel("inscriptions")
        );

        inscription.setId(inscriptionEntity.getId());
        inscription.setClasse(classeModelAssembler.toModel(inscriptionEntity.getClasse()));
        inscription.setUser(userModelAssembler.toShortModel(inscriptionEntity.getUser()));
        inscription.setRole(roleModelAssembler.toModel(inscriptionEntity.getRole()));

        return inscription;
    }

    @Override
    public CollectionModel<Inscription> toCollectionModel(Iterable<? extends InscriptionEntity> inscriptions) {
        CollectionModel<Inscription> inscriptionModels = super.toCollectionModel(inscriptions);
        inscriptionModels.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withSelfRel()
        );
        return inscriptionModels;
    }
//    TODO lists and relations

}
