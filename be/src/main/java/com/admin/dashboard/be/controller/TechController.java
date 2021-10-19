package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.service.TechServiceImpl;
import lombok.RequiredArgsConstructor;
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
public class TechController {
    private final TechServiceImpl techService;

    @PostMapping("/tech")
    public ResponseEntity<ResponseData<Tech>> saveTech(@Valid @RequestBody Tech tech, Errors errors) {
        ResponseData<Tech> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(techService.saveTech(tech));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/tech")
    public ResponseEntity<ResponseData<List<Tech>>> findAllTech() {
        ResponseData<List<Tech>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(techService.getAllTech());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/tech/{id}")
    public ResponseEntity<ResponseData<Tech>> findTechById(@PathVariable("id") Long techId) {
        ResponseData<Tech> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(techService.getTechById(techId));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/tech")
    public ResponseEntity<ResponseData<Tech>> updateTech(@RequestBody Tech tech, Errors errors) {
        ResponseData<Tech> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(techService.saveTech(tech));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/tech/{id}")
    public void removeTech(@PathVariable("id") Long techId) {
        techService.deleteTech(techId);
    }
}
