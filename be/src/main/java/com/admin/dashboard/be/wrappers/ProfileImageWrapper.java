package com.admin.dashboard.be.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProfileImageWrapper {
    private Long id;
    private String contentType;
    private String base64;
}
