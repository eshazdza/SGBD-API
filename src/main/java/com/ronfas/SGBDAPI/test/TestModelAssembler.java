package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.inscription.Inscription;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TestModelAssembler extends RepresentationModelAssemblerSupport<TestEntity, Test> {

    public TestModelAssembler() {
        super(TestController.class, Test.class);
    }

    @Override
    public Test toModel(TestEntity testEntity) {
        Test test = instantiateModel(testEntity);
        test.add(
                linkTo(
                        methodOn(TestController.class)
                                .one(testEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(TestController.class)
                                .all()
                ).withRel("inscriptions")
        );

        test.setId(testEntity.getId());
//        TODO SETTERS

        return test;
    }

    @Override
    public CollectionModel<Test> toCollectionModel(Iterable<? extends TestEntity> testEntities) {
        CollectionModel<Test> testCollectionModel = super.toCollectionModel(testEntities);
        testCollectionModel.add(
                linkTo(
                        methodOn(TestController.class)
                                .all()
                ).withSelfRel()
        );
        return testCollectionModel;
    }
}
