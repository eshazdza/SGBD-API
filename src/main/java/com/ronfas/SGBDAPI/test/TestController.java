package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import com.ronfas.SGBDAPI.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    CollectionModel<EntityModel<Test>> all() {
        return CollectionModel.of(testService.getAllTests(), linkTo(
                methodOn(TestController.class).all()).withSelfRel()
        );
    }


    @GetMapping("/{id}")
    EntityModel<Test> one(
            @PathVariable Long id
    ) {
        return testService.getTestById(id);
    }


    @PostMapping("")
    ResponseEntity<?> newTest(
            @RequestBody Test newTest
    ) {
        EntityModel<Test> testEntityModel = testService.saveTest(newTest);

        return ResponseEntity.created(
                testEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(testEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTest(
            @RequestBody @Valid Test test,
            @PathVariable Long id
    ) {
        EntityModel<Test> testEntityModel = this.testService.updateTest(test, id);

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