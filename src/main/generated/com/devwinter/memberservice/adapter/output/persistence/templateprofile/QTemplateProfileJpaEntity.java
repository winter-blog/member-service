package com.devwinter.memberservice.adapter.output.persistence.templateprofile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTemplateProfileJpaEntity is a Querydsl query type for TemplateProfileJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplateProfileJpaEntity extends EntityPathBase<TemplateProfileJpaEntity> {

    private static final long serialVersionUID = 1788421783L;

    public static final QTemplateProfileJpaEntity templateProfileJpaEntity = new QTemplateProfileJpaEntity("templateProfileJpaEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath path = createString("path");

    public QTemplateProfileJpaEntity(String variable) {
        super(TemplateProfileJpaEntity.class, forVariable(variable));
    }

    public QTemplateProfileJpaEntity(Path<? extends TemplateProfileJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTemplateProfileJpaEntity(PathMetadata metadata) {
        super(TemplateProfileJpaEntity.class, metadata);
    }

}

