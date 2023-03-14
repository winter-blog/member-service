package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberProfileJpaEntity is a Querydsl query type for MemberProfileJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberProfileJpaEntity extends EntityPathBase<MemberProfileJpaEntity> {

    private static final long serialVersionUID = -1445751249L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberProfileJpaEntity memberProfileJpaEntity = new QMemberProfileJpaEntity("memberProfileJpaEntity");

    public final com.devwinter.memberservice.adapter.output.persistence.QBaseTimeEntity _super = new com.devwinter.memberservice.adapter.output.persistence.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberJpaEntity memberJpaEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath path = createString("path");

    public final EnumPath<com.devwinter.memberservice.domain.Profile.ProfileType> profileType = createEnum("profileType", com.devwinter.memberservice.domain.Profile.ProfileType.class);

    public QMemberProfileJpaEntity(String variable) {
        this(MemberProfileJpaEntity.class, forVariable(variable), INITS);
    }

    public QMemberProfileJpaEntity(Path<? extends MemberProfileJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberProfileJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberProfileJpaEntity(PathMetadata metadata, PathInits inits) {
        this(MemberProfileJpaEntity.class, metadata, inits);
    }

    public QMemberProfileJpaEntity(Class<? extends MemberProfileJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberJpaEntity = inits.isInitialized("memberJpaEntity") ? new QMemberJpaEntity(forProperty("memberJpaEntity"), inits.get("memberJpaEntity")) : null;
    }

}

