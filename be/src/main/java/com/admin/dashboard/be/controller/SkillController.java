package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.dto.SkillDTO;
import com.admin.dashboard.be.entity.Skill;
import com.admin.dashboard.be.service.SkillServiceImpl;
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
public class SkillController {

    private final SkillServiceImpl skillService;
    private final ModelMapper modelMapper;

    @PostMapping("/skill")
    public ResponseEntity<ResponseData<Skill>> saveSkill(@Valid @RequestBody SkillDTO skillDTO, Errors errors) {
        ResponseData<Skill> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Skill skill = modelMapper.map(skillDTO, Skill.class);
        responseData.setStatus(true);
        responseData.setPayload(skillService.saveSkill(skill));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/skills")
    public ResponseEntity<ResponseData<List<Skill>>> getAllSkill() {
        ResponseData<List<Skill>> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(skillService.getAllSkills());

        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<ResponseData<Skill>> findSkillById(@PathVariable("skillId") Long skillId) {
        ResponseData<Skill> responseData = new ResponseData<>();

        Skill skill = skillService.getSkillById(skillId);

        if (skill == null) {
            responseData.setStatus(false);
            responseData.setMessages(List.of("Skill is not found"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(skill);

        return ResponseEntity.ok(responseData);
    }
}
