package com.bakalauras.backend.controllers;

import com.bakalauras.backend.payload.mapper.UserMapper;
import com.bakalauras.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;
    private final UserMapper userMapper;

//    @GetMapping
//    public ResponseEntity<?> getUsers() {
//        try {
//            return ResponseEntity.ok(userMapper.convertToDto(userService.getUsers()));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                    .body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUser(@PathVariable String id) {
//        try {
//            return ResponseEntity.ok(userMapper.convertToDto(userService.getUser(Long.valueOf(id))));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                    .body(e.getMessage());
//        }
//    }
}
