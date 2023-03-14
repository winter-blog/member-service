package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberProfileCollectionJpaEntity is a Querydsl query type for MemberProfileCollectionJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberProfileCollectionJpaEntity extends BeanPath<MemberProfileCollectionJpaEntity> {

    private static final long serialVersionUID = 1137727473L;

    public static final QMemberProfileCollectionJpaEntity memberProfileCollectionJpaEntity = new QMemberProfileCollectionJpaEntity("memberProfileCollectionJpaEntity");

    public final ListPath<MemberProfileJpaEntity, QMemberProfileJpaEntity> profiles = this.<MemberProfileJpaEntity, QMemberProfileJpaEntity>createList("profiles", MemberProfileJpaEntity.class, QMemberProfileJpaEntity.class, PathInits.DIRECT2);

    public QMemberProfileCollectionJpaEntity(String variable) {
        super(MemberProfileCollectionJpaEntity.class, forVariable(variable));
    }

    public QMemberProfileCollectionJpaEntity(Path<? extends MemberProfileCollectionJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberProfileCollectionJpaEntity(PathMetadata metadata) {
        super(MemberProfileCollectionJpaEntity.class, metadata);
    }

}

