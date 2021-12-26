package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.ProjectImage;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectImageService {
    ProjectImage saveProjectImage(MultipartFile file);
}
