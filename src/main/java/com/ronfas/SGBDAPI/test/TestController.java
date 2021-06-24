package com.ronfas.SGBDAPI.test;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public CollectionModel<Test> all() {
        return CollectionModel.of(testService.getAllTests(), linkTo(
                methodOn(TestController.class).all()).withSelfRel()
        );
    }


    @GetMapping("/{id}")
    public Test one(
            @PathVariable Long id
    ) {
        return testService.getTestById(id);
    }


    @PostMapping("")
    ResponseEntity<?> newTest(
            @RequestBody TestEntity newTestEntity
    ) {

        System.out.println(newTestEntity);
        Test testEntityModel = testService.saveTest(newTestEntity);

        return ResponseEntity.created(
                testEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(testEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTest(
            @RequestBody @Valid TestEntity testEntity,
            @PathVariable Long id
    ) {
        Test testEntityModel = this.testService.updateTest(testEntity, id);

        return ResponseEntity
                .created(testEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(testEntityModel);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        testService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }


}