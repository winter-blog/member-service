package com.devwinter.memberservice.adapter.output.bulk;

import com.devwinter.memberservice.adapter.output.persistence.bulk.MemberJpaEntityBulkInsertRepository;
import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;
import com.devwinter.memberservice.fixture.MemberJpaEntityFixtureFactory;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Disabled
class MemberJpaEntityBulkInsertRepositoryImplTest {

    @Autowired
    private MemberJpaEntityBulkInsertRepository memberJpaEntityBulkInsertRepository;

    @Test
    public void bulkInsert() {
        EasyRandom easyRandom = MemberJpaEntityFixtureFactory.get();

        List<MemberJpaEntity> posts = IntStream.range(0,100)
                                               .parallel()
                                               .mapToObj(j -> easyRandom.nextObject(MemberJpaEntity.class))
                                               .toList();

        memberJpaEntityBulkInsertRepository.bulkInsert(posts);
    }

}