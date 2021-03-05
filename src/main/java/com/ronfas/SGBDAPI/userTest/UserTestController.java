package com.ronfas.SGBDAPI.userTest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usertests")
public class UserTestController {

    private final UserTestService userTestService;

    public UserTestController( UserTestService userTestService) {
        this.userTestService = userTestService;
    }

    @GetMapping("")
    CollectionModel<EntityModel<UserTest>> all() {
        return CollectionModel.of(this.userTestService.getAllUserTests(), linkTo(
                methodOn(UserTestController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    EntityModel<UserTest> one(
            @PathVariable Long id
    ) {
        return this.userTestService.getUserTestById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newUserTest(
            @RequestBody @Valid UserTest newUserTest
    ) {
        EntityModel<UserTest> userTestEntityModel = this.userTestService.saveUserTest(newUserTest);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                userTestEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(userTestEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUserTest(
            @RequestBody @Valid UserTest userTest,
            @PathVariable Long id
    ) {
        EntityModel<UserTest> userTestEntityModel = this.userTestService.updateUserTest(userTest, id);

        return ResponseEntity
                .created(userTestEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(userTestEntityModel);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        userTestService.deleteUserTest(id);
        return ResponseEntity.noContent().build();
    }

}
