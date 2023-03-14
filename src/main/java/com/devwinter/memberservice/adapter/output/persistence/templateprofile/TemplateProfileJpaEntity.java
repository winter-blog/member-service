package com.devwinter.memberservice.adapter.output.persistence.templateprofile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "template_profile")
public class TemplateProfileJpaEntity {

    @Id
    private Long id;

    private String path;
}
