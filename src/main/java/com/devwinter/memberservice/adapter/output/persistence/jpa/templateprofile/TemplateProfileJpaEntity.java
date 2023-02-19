package com.devwinter.memberservice.adapter.output.persistence.jpa.templateprofile;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "template_profile")
public class TemplateProfileJpaEntity {

    @Id
    private Long id;

    private String path;
}
