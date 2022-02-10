package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Skill;
import com.admin.dashboard.be.repository.SkillRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class SkillServiceTest {

    @Mock private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;
    private AutoCloseable autoCloseable;

    private Skill skill1;
    private Skill skill2;

    @BeforeEach
    void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        skill1 = Skill.builder()
                .name("skill1")
                .users(new ArrayList<>())
                .build();

        skill2 = Skill.builder()
                .name("skill2")
                .users(new ArrayList<>())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void injectedMockAreNotNull() {
        assertThat(skillRepository).isNotNull();
    }

    @Test
    void itShouldSaveSkill() {
        Mockito.when(skillRepository.save(skill1)).thenReturn(skill1);
        Skill actualSkill = skillService.saveSkill(skill1);

        verify(skillRepository, times(1)).save(skill1);
        assertThat(actualSkill).isEqualTo(skill1);
    }

    @Test
    void itShouldGetAllSkills() {
        Mockito.when(skillRepository.findAll()).thenReturn(List.of(skill1, skill2));
        List<Skill> actualSkills = skillService.getAllSkills();

        verify(skillRepository, times(1)).findAll();
        assertThat(actualSkills.containsAll(List.of(skill1, skill2))).isTrue();
    }

    @Test
    void itShouldReturnSkillById() {
        Mockito.when(skillRepository.findById(skill1.getSkillId())).thenReturn(Optional.of(skill1));
        Skill actualSkill = skillService.getSkillById(skill1.getSkillId());

        verify(skillRepository, times(1)).findById(skill1.getSkillId());
        assertThat(actualSkill).isEqualTo(skill1);
    }

    @Test
    void itShouldUpdateSkill() {
        Mockito.when(skillRepository.findById(skill1.getSkillId())).thenReturn(Optional.of(skill1));
        skill1.setName("Skill Update");
        Mockito.when(skillRepository.save(skill1)).thenReturn(skill1);

        Skill actualSkill = skillService.updateSkill(skill1);

        verify(skillRepository, times(1)).findById(skill1.getSkillId());
        verify(skillRepository, times(1)).save(skill1);
        assertThat(actualSkill.getName()).isEqualTo("Skill Update");
    }

    @Test
    void itShouldRemoveSkill() {
        Mockito.when(skillRepository.findById(skill1.getSkillId())).thenReturn(Optional.of(skill1));
        String response = skillService.removeSkill(skill1.getSkillId());
        verify(skillRepository, times(1)).findById(skill1.getSkillId());
        assertThat(response).isEqualTo("Skill is removed");
    }
}
