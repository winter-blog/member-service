package com.devwinter.memberservice.adapter.output.persistence.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberJpaEntity is a Querydsl query type for MemberJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberJpaEntity extends EntityPathBase<MemberJpaEntity> {

    private static final long serialVersionUID = -1302416732L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberJpaEntity memberJpaEntity = new QMemberJpaEntity("memberJpaEntity");

    public final com.devwinter.memberservice.adapter.output.persistence.QBaseTimeEntity _super = new com.devwinter.memberservice.adapter.output.persistence.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath deleted = createBoolean("deleted");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final QMemberProfileCollectionJpaEntity profiles;

    public QMemberJpaEntity(String variable) {
        this(MemberJpaEntity.class, forVariable(variable), INITS);
    }

    public QMemberJpaEntity(Path<? extends MemberJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberJpaEntity(PathMetadata metadata, PathInits inits) {
        this(MemberJpaEntity.class, metadata, inits);
    }

    public QMemberJpaEntity(Class<? extends MemberJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profiles = inits.isInitialized("profiles") ? new QMemberProfileCollectionJpaEntity(forProperty("profiles")) : null;
    }

}

