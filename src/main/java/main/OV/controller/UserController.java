package main.OV.controller;


import jakarta.validation.ConstraintViolationException;
import main.OV.db.entity.UserEntity;
import main.OV.dto.UserDto;
import main.OV.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

public class UserController {

    private IUserService userService;

    @RequestMapping(value = "/users/saveUser", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserEntity user) {
        try {

            UserEntity newUser = userService.saveUser(user);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/users/editUser", method = RequestMethod.PATCH)
    public ResponseEntity<?> editUser(@RequestBody UserEntity user) {
        try {

            UserEntity newUser = userService.editUser(user);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream().map(s -> s.getMessage()).collect(Collectors.joining(", "));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/users/getUserById", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUserById() {
        try {
            return new ResponseEntity<>(userService.getUserById(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/getUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/users/getActiveUsers", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getActiveUsers() {
        try {
            return new ResponseEntity<>(userService.getActiveUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
