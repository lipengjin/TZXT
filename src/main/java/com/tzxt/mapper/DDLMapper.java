package com.tzxt.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshang on 17/5/11.
 */
public interface DDLMapper {

    void executeSQL(@Param("sql") String sql);

    List<String> showTables();

    Long count(@Param("name") String name);

    Long countByParam(@Param("sql") String sql);

    List<Map> selectList(@Param("sql") String sql);

    List<Map<String, Object>> selectListByParam(@Param("sql") String sql);

    Map selectById(@Param("sql") String sql);

    void updateById(@Param("sql") String sql);

    List<Map<String, Object>> selectPage(@Param("sql") String sql);

    void insertList(@Param("sql") String sql);

    void drop(@Param("tableName") String tableName);
}
