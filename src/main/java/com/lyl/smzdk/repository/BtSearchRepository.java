package com.lyl.smzdk.repository;

import com.lyl.smzdk.model.BtSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BtSearchRepository extends JpaRepository<BtSearch, Long> {

    /**
     * 没有账号的用户，今天查询的次数
     *
     * @param uuid    uuid
     * @param nowDate 今天 零点 的日期
     * @return 今天的查询次数
     */
    int countByUuidAndCreateTimeAfter(String uuid, Date nowDate);

    /**
     * 指定账户，今天查询的次数
     *
     * @param userId  用户id
     * @param nowDate 今天零点的日期
     * @return 今天查询的次数
     */
    int countByUserIdAndCreateTimeAfter(Long userId, Date nowDate);
}
