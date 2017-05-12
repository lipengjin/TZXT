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

    List<Map> selectList(@Param("sql") String sql);
}
