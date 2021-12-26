package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.ProjectImage;
import com.admin.dashboard.be.repository.ProjectImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectImageServiceImpl implements ProjectImageService{
    private final ProjectImageRepository projectImageRepository;

    private static final String UPLOADED_PATH =
            "E:/Software-Development/Learn Website/Personal Website/Admin Personal Website/fe/src/assets/images/portfolios/";


    @Override
    public ProjectImage saveProjectImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_PATH + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProjectImage projectImage = new ProjectImage();
        projectImage.setImageURL(UPLOADED_PATH + file.getOriginalFilename());

        return projectImageRepository.save(projectImage);
    }
}
