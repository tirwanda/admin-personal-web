package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Skill;
import com.admin.dashboard.be.entity.User;
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
    private User user1;

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

        user1 = User.builder()
                .username("user1")
                .name("user satu")
                .password("12345")
                .title("Backend Engineer")
                .roles(new ArrayList<>())
                .projects(new ArrayList<>())
                .skills(new ArrayList<>())
                .email("usersatu@gmail.com")
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
}
