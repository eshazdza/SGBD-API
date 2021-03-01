package com.ronfas.SGBDAPI.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tests")
public class TestController {
    @Autowired
    private final TestRepository testRepository;
    private final TestModelAssembler testModelAssember;

    public TestController(TestRepository testRepository, TestModelAssembler testModelAssember) {
        this.testRepository = testRepository;
        this.testModelAssember = testModelAssember;
    }

    @GetMapping("")
    CollectionModel<EntityModel<Test>> all() {
        List<EntityModel<Test>> tests = testRepository.findAll()
                .stream()
                .map(testModelAssember::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tests, linkTo(
                methodOn(TestController.class).all()).withSelfRel()
        );
    }


    @GetMapping("/{id}")
    EntityModel<Test> one(
            @PathVariable Long id
    ) {
        Test test = testRepository.findById(id).orElseThrow();
        return testModelAssember.toModel(test);
    }


    @PostMapping("")
    ResponseEntity<?> newTest(
            @RequestBody Test newTest
    ) {
        EntityModel<Test> testEntityModel = testModelAssember.toModel(testRepository.save(newTest));

        return ResponseEntity.created(
                testEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(testEntityModel);
    }
}