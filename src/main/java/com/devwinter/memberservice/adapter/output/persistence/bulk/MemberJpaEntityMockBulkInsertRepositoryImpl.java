package com.devwinter.memberservice.adapter.output.persistence.bulk;

import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberJpaEntity;
import com.devwinter.memberservice.adapter.output.persistence.member.entity.MemberProfileJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaEntityMockBulkInsertRepositoryImpl implements MemberJpaEntityMockBulkInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void bulkInsert(List<MemberJpaEntity> members) {
        String sql =
                "insert into member (created_at, modified_at, deleted, email, nick_name, password) " +
                        "values (?,?,?,?,?,?)";

        String sql2 =
                "insert into member_profile (created_at, modified_at, main, path, profile_type, member_id) " +
                        "values (?,?,?,?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                MemberJpaEntity member = members.get(i);
                ps.setTimestamp(1, Timestamp.valueOf(member.getCreatedAt()));
                ps.setTimestamp(2, Timestamp.valueOf(member.getCreatedAt()));
                ps.setBoolean(3, member.isDeleted());
                ps.setString(4, member.getEmail());
                ps.setString(5, member.getNickName());
                ps.setString(6, member.getPassword());
            }

            @Override
            public int getBatchSize() {
                return members.size();
            }
        });

        jdbcTemplate.batchUpdate(sql2, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                MemberProfileJpaEntity profile = members.get(i).getProfiles().getProfiles().get(0);
                ps.setTimestamp(1, Timestamp.valueOf(profile.getCreatedAt()));
                ps.setTimestamp(2, Timestamp.valueOf(profile.getCreatedAt()));
                ps.setBoolean(3, profile.isMain());
                ps.setString(4, profile.getPath());
                ps.setString(5, profile.getProfileType().name());
                ps.setLong(6, i + 1);
            }

            @Override
            public int getBatchSize() {
                return members.size();
            }
        });
    }
}
