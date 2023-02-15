package com.devwinter.memberservice.adapter.output.persistence.profile;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "profile")
public class ProfileJpaEntity {

    @Id
    private Long id;

    private String path;
}
