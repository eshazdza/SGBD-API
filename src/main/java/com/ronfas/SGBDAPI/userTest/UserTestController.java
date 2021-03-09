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
    CollectionModel<UserTest> all() {
        return CollectionModel.of(this.userTestService.getAllUserTests(), linkTo(
                methodOn(UserTestController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    UserTest one(
            @PathVariable Long id
    ) {
        return this.userTestService.getUserTestById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newUserTest(
            @RequestBody @Valid UserTestEntity newUserTestEntity
    ) {
        UserTest userTestEntityModel = this.userTestService.saveUserTest(newUserTestEntity);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                userTestEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(userTestEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUserTest(
            @RequestBody @Valid UserTestEntity userTestEntity,
            @PathVariable Long id
    ) {
       UserTest userTestEntityModel = this.userTestService.updateUserTest(userTestEntity, id);

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
