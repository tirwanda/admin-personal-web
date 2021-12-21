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
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final String UPLOADED_PATH =
            "E:/Software-Development/Learn Website/Personal Website/Admin Personal Website/fe/src/assets/images";

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
    public String updateImage(@PathVariable("userId") Long userId,
                                                          @RequestParam("fileImage") MultipartFile multipartFile,
                                                          RedirectAttributes redirectAttributes){

        if (multipartFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message","Please Select a file");
//            return "redirect:status";
            return "Please Select a Image";
        }

        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_PATH + multipartFile.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "Success Uploaded " + multipartFile.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

        userService.uploadUserProfileImage(userId, multipartFile.getOriginalFilename());
        // return "redirect:status";
        return "Success Uploaded an Image";
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

