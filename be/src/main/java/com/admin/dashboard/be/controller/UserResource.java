package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.dto.RoleToUserForm;
import com.admin.dashboard.be.dto.UserDTO;
import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/users")
    public ResponseEntity<ResponseData<List<User>>> getUsers() {
        ResponseData<List<User>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(userService.getUsers());
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("user/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return  userService.getUser(username);
    }

    @PostMapping("/user/save")
    public ResponseEntity<ResponseData<User>> saveUser(@Valid @RequestBody UserDTO userDTO, Errors errors) {
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/user/save").toUriString());
        ResponseData<User> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for ( ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        User user = modelMapper.map(userDTO, User.class);
        responseData.setStatus(true);
        responseData.setPayload(userService.saveUser(user));
        return ResponseEntity.created(uri).body(responseData);
    }

    @PostMapping("/role/add-to-user")
    public ResponseEntity<?> saveRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/user/update")
    public ResponseEntity<ResponseData<User>> updateUser(@RequestBody User user) {
        ResponseData<User> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(userService.updateUser(user));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping(value = "/upload/image/{userId}")
    public ResponseEntity<ResponseData<User>> updateImage(@PathVariable("userId") Long userId,
                                          @RequestParam("fileImage") MultipartFile multipartFile)
            throws IOException {
        ResponseData<User> responseData = new ResponseData<>();
        User user = userService.getUserByUserId(userId);
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        user.setProfileImage(fileName);
        String uploadDir = "/user/profile/" + user.getUserId();
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            responseData.setStatus(true);
            responseData.setPayload(userService.uploadUserProfileImage(user));
            return ResponseEntity.ok(responseData);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }
    }

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

