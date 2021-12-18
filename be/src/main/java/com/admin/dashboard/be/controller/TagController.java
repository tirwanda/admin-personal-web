package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.dto.TagDTO;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.service.TagServiceImpl;
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
public class TagController {
    private final TagServiceImpl tagService;
    private final ModelMapper modelMapper;

    @PostMapping("/tag")
    public ResponseEntity<ResponseData<Tag>> saveTag(@Valid @RequestBody() TagDTO tagDTO, Errors errors) {
        ResponseData<Tag> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Tag tag = modelMapper.map(tagDTO, Tag.class);
        responseData.setStatus(true);
        responseData.setPayload(tagService.saveTag(tag));
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping("/tags")
    public ResponseEntity<ResponseData<List<Tag>>> findAllTag() {
        ResponseData<List<Tag>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(tagService.getAllTags());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<ResponseData<Tag>> findTagById(@PathVariable("id") Long id) {
        ResponseData<Tag> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(tagService.getTag(id));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/tag")
    public ResponseEntity<ResponseData<Tag>> updateTag(@Valid @RequestBody Tag tag, Errors errors) {
        ResponseData<Tag> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(tagService.saveTag(tag));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/tag/{tagId}/add/{projectId}")
    public ResponseEntity<ResponseData<String>> addProjectToTag(@PathVariable("tagId") Long tagId,
                                                                @PathVariable("projectId") Long projectId) {
        ResponseData<String> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(tagService.addProjectToTag(tagId, projectId));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/tag/remove-project/{projectId}")
    public ResponseEntity<ResponseData<String>> remcveProjectFromTag(@PathVariable("projectId") Long projectId) {
        ResponseData<String> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(tagService.removeProjectFromTag(projectId));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/tag/{id}")
    public ResponseEntity<String> removeTag(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(tagService.deleteTag(id));
    }
}
