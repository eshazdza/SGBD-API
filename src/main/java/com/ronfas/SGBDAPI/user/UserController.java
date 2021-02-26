package com.ronfas.SGBDAPI.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    public UserController(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping("")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = userRepository.findAll()
                .stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(
                methodOn(UserController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    EntityModel<User> one(
            @PathVariable Long id
    ) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userModelAssembler.toModel(user);
    }

    @PostMapping("")
    ResponseEntity<?> newUser(
            @RequestBody @Valid User newUser
    ) {
//        Save the entity and creates a REST compliant model of said entity
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userRepository.save(newUser));

//        Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                userEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(userEntityModel);
    }

    //    Handle validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
