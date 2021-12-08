package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.dto.RoleDTO;
import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.service.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {

    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;

    @PostMapping("/role/save")
    public ResponseEntity<ResponseData<Role>> saveRole(@Valid @RequestBody RoleDTO roleDTO, Errors errors) {
        ResponseData<Role> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Role role = modelMapper.map(roleDTO, Role.class);
        responseData.setStatus(true);
        responseData.setPayload(roleService.saveRole(role));
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/role")
    public ResponseEntity<ResponseData<List<Role>>> getAllRole() {
        ResponseData<List<Role>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(roleService.getAllRoles());
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<ResponseData<Role>> getRoleById(@PathVariable Long roleId) {
        ResponseData<Role> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(roleService.getRole(roleId));

        return ResponseEntity.ok().body(responseData);
    }

    @PutMapping("/role/update")
    public ResponseEntity<ResponseData<Role>> updateRole(@RequestBody Role role, Errors errors) {
        ResponseData<Role> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
        }

        responseData.setStatus(true);
        responseData.setPayload(roleService.saveRole(role));
        return ResponseEntity.ok().body(responseData);
    }

    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<ResponseData<String>> deleteRole(@PathVariable Long roleId) {
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(roleService.deleteRole(roleId));

        return ResponseEntity.ok().body(responseData);
    }
}
