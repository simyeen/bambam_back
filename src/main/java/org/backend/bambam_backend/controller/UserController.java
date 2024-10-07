package org.backend.bambam_backend.controller;

import org.backend.bambam_backend.dto.UserDTO;
import org.backend.bambam_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // jwt 토큰을 통해서 유저정보 가져오기
    @GetMapping("/info")
    public UserDTO getUserInfo(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println(username + " 이걸로 정보가져오기");
        UserDTO userDTO = userService.getUserInfo(username);

        return userDTO;
    }

    // 인증 로직 -> formData 기반
    @PostMapping("/join")
    public String joinProcess(@RequestBody UserDTO userDTO) {
        // Assuming userDTO is already being populated from the request body.
        System.out.println("join Controller 실행 :" + " " + userDTO.getUsername() + " " + userDTO.getPassword() + " " + userDTO.getEmail());

        // Passing the userDTO to the service layer for further processing.
        userService.joinProcess(userDTO);

        return "ok";
    }

    // 비지니스 로직
    @Operation(summary = "Get user by ID", description = "Fetch a user by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found the user", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @Operation(summary = "Get all users", description = "Fetch all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fetched all users", content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)),
                    headers = @Header(name = "Location", description = "The URI of the newly created user", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        return ResponseEntity.status(201).header("Location", "/api/users/" + createdUser.getUserId()).body(createdUser);
    }

    @Operation(summary = "Update user by ID", description = "Update a user's details by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID or input supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete user by ID", description = "Delete a user by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}