package com.isfive.usearth.domain.project.entity;

import com.isfive.usearth.domain.common.FileImage;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProjectFileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FileImage fileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public void setProject(Project project) {
        this.project = project;
        project.getProjectImages().add(this);
    }
}
