package com.devwinter.memberservice.adapter.output.persistence.bulk;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;

import java.util.List;

public interface MemberJpaEntityBulkInsertRepository {
    void bulkInsert(List<MemberJpaEntity> members);
}
