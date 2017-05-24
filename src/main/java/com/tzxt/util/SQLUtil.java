package com.tzxt.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tzxt.model.FieldType.Type;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import org.apache.ibatis.jdbc.SQL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SQLUtil 工具类，根据 参数 组装 sql 语句
 * Created by wangshang on 17/5/11.
 */
public class SQLUtil {

    /**
     * 拼装 动态 建表 语句
     * 例子：
     * <p>
     * CREATE TABLE `tableName` (
     * `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
     * `field1` VARCHAR(255) COMMENT 'field1Comment',
     * `field2` INT(255) COMMENT 'field2Comment',
     * `create_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     * `update_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
     * PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tableComment';
     *
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    public static String createLedgerDictionaryTable(Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CREATE(ledger.getTableName()));
        ledgerDictionaries.forEach(ld -> stringBuilder.append(COLUMN(ld)));
        stringBuilder.append(CREATE_AT);
        stringBuilder.append(UPDATE_AT);
        stringBuilder.append(PRIMARY_KEY);
        stringBuilder.append(END(ledger.getComment()));
        return stringBuilder.toString();
    }

    public static final String SHOW_TABLES = "show tables;";

    private static final String SPACE = " ";
    private static final String REVERSE_DOT = "`";
    private static final String DOT = ",\n";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";
    private static final String CREATE_AT = "`create_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'" + DOT;
    private static final String UPDATE_AT = "`update_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'" + DOT;
    private static final String PRIMARY_KEY = "PRIMARY KEY (`id`)\n";

    private static String CREATE(String tableName) {
        return "CREATE TABLE `" + tableName + "` (\n `id` BIGINT(20) NOT NULL AUTO_INCREMENT" + DOT;
    }

    private static String COLUMN(LedgerDictionary ledgerDictionary) {
        return REVERSE_DOT + ledgerDictionary.getSourceField() + REVERSE_DOT + SPACE + fieldType(ledgerDictionary) + " COMMENT '" + ledgerDictionary.getLdComment() + "'" + DOT;
    }

    private static String END(String tableComment) {
        return ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='" + tableComment + "';";
    }

    private static String VALUES(List<String> ledgerFields, Map<String, Object> data) {
//        List<String> vals = Lists.newArrayList();
//        ledgerFields.forEach(field -> vals.add(data.containsKey(field) ? data.get(field).toString() : ""));
        StringBuilder sb = new StringBuilder();
        ledgerFields.forEach(f -> {
            System.out.println(data.get(f).getClass());
            if (data.get(f) instanceof Boolean)
                data.put(f, Boolean.TRUE.equals(data.get(f)) ? 1 : 0);
            sb.append("'").append(data.getOrDefault(f, "")).append("',");
        });
        String sql = sb.toString();
        return sql.substring(0, sql.length() - 1);
    }

    private static String fieldType(LedgerDictionary ledgerDictionary) {
        switch (Type.getType(ledgerDictionary.getFieldType())) {
            case BIGINT:
                return Type.BIGINT.getValue();
            case INT:
                return ledgerDictionary.getLength() > 0 ? Type.INT.getValue() + LEFT + ledgerDictionary.getLength() + RIGHT : Type.INT.getValue();
            case TEXT:
                return Type.TEXT.getValue();
            case FLOAT:
                return Type.FLOAT.getValue();
            case BOOLEAN:
                return Type.BOOLEAN.getValue();
            case VARCHAR:
                return ledgerDictionary.getLength() > 0 ? Type.VARCHAR.getValue() + LEFT + ledgerDictionary.getLength() + RIGHT : Type.VARCHAR.getValue() + LEFT + "255" + RIGHT;
            case DATETIME:
                return Type.DATETIME.getValue();
            default:
                return "";
        }
    }

    /**
     * 动态 拼装 插入台账数据 的 SQL
     * <p>
     * insert into `table1` (`field1`,`field2`,`field3`,`field4`,`field5`)
     * values ('value1','value2','value3','value4','value5'),
     * ('value11','value22','value33','value44','value55'),
     * ('value111','value222','value333','value444','value555');
     *
     * @param ledger
     * @param ledgerDictionaries
     * @param data
     * @return
     */
    public static String insertList(Ledger ledger, List<LedgerDictionary> ledgerDictionaries, List<Map<String, Object>> data) {
        List<String> ledgerFields = ledgerDictionaries.stream().map(LedgerDictionary::getSourceField).collect(Collectors.toList());
        StringBuilder sql = new StringBuilder(new SQL() {{
            INSERT_INTO(ledger.getTableName());
            INTO_COLUMNS(ledgerFields.toArray(new String[0]));
        }}.toString());
        sql.append("\nVALUES ");
        data.forEach(d -> sql.append("(").append(VALUES(ledgerFields, d)).append("),\n"));
        String sqll = sql.toString();
        return sqll.substring(0, sqll.length() - 2) + ";";
    }

    /**
     * 动态 拼装 查询台账 数据的 SQL
     *
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    public static String selectListByParam(Map<String, Object> params, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        return new SQL() {{
//            SELECT((String[]) ledgerDictionaries.stream().map(LedgerDictionary::getSourceField).collect(Collectors.toList()).toArray(new String[0]));
            SELECT("*");
            FROM(ledger.getTableName());
            ledgerDictionaries.forEach(ld -> {
                if (params.containsKey(ld.getSourceField()) && params.get(ld.getSourceField()) != null && !"".equals(params.get(ld.getSourceField()))) {
                    WHERE("`" + ld.getSourceField() + "`='" + params.get(ld.getSourceField()) + "'");
                }
            });
        }}.toString();
    }

    /**
     * 动态 拼装 查询 原始 数据的 SQL
     *
     * @param pageNo
     * @param pageSize
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    public static String selectPage(Integer pageNo, Integer pageSize, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        return new SQL() {{
            SELECT((String[]) ledgerDictionaries.stream().map(LedgerDictionary::getSourceField).collect(Collectors.toList()).toArray(new String[0]));
            FROM(ledger.getSourceTable());
            if (ledger.getStartDate() != null && !"".equals(ledger.getStartDate()))
                WHERE("mouth>=" + ledger.getStartDate());
            if (ledger.getEndDate() != null && !"".equals(ledger.getEndDate()))
                WHERE("mouth<=" + ledger.getEndDate());
        }}.toString() + " LIMIT " + (pageNo - 1) * pageSize + "," + pageSize + ";";
    }

    /**
     * 动态 拼装 根据ID 查询 台账数据的 SQL
     *
     * @param id
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    public static String selectById(Long id, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        return new SQL() {{
            SELECT((String[]) ledgerDictionaries.stream().map(LedgerDictionary::getSourceField).collect(Collectors.toList()).toArray(new String[0]));
//            SELECT("*");
            FROM(ledger.getTableName());
            WHERE("`id`=" + id);
        }}.toString();
    }

    /**
     * 动态 拼装 根据 ID 更新 台账数据的 SQL
     *
     * @param id
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    public static String updateById(Long id, Ledger ledger, List<LedgerDictionary> ledgerDictionaries, Map<String, Object> data) {
        return new SQL() {{
            UPDATE(ledger.getTableName());
            ledgerDictionaries.forEach(ld -> {
                if (data.containsKey(ld.getSourceField()) && data.get(ld.getSourceField()) != null && !"".equals(data.get(ld.getSourceField()))) {
                    SET("`" + ld.getSourceField() + "`='" + data.get(ld.getSourceField()) + "'");
                }
            });
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SET("`update_at`='" + simpleDateFormat.format(new Date()) + "'");
            WHERE("`id`=" + id);
        }}.toString();
    }

    /**
     * 动态 拼装 根据 指定参数 查询台账 数据的 SQL
     * <p>
     * 目前的参数包括：
     * 单位
     * 特定含义的时间段
     *
     * @param params
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    public static String selectPageByParam(Integer pageNo, Integer pageSize, Map<String, Object> params, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        return new SQL() {{
//            SELECT((String[]) ledgerDictionaries.stream().map(LedgerDictionary::getSourceField).collect(Collectors.toList()).toArray(new String[0]));
            SELECT("*");
            FROM(ledger.getTableName());
            ledgerDictionaries.forEach(ld -> {
                if (params.containsKey(ld.getSourceField()) && params.get(ld.getSourceField()) != null && !"".equals(params.get(ld.getSourceField()))) {
                    WHERE("`" + ld.getSourceField() + "`='" + params.get(ld.getSourceField()) + "'");
                }
            });
        }}.toString() + " LIMIT " + (pageNo - 1) * pageSize + "," + pageSize + ";";
    }

    /**
     * 根据参数 统计数据量
     *
     * @param params
     * @param ledger
     * @return
     */
    public static String countByParam(Map<String, Object> params, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        return new SQL() {{
            SELECT("COUNT(1)");
            FROM(ledger.getTableName());
            ledgerDictionaries.forEach(ld -> {
                if (params.containsKey(ld.getSourceField()) && params.get(ld.getSourceField()) != null && !"".equals(params.get(ld.getSourceField()))) {
                    WHERE("`" + ld.getSourceField() + "`='" + params.get(ld.getSourceField()) + "'");
                }
            });
        }}.toString();
    }

    public static void main(String[] args) {

        Ledger ledger = new Ledger();
        ledger.setTableName("source1");
        ledger.setComment("source1Comment");
        List<Map<String, Object>> data1 = Lists.newArrayList();
        List<LedgerDictionary> ledgerDictionaries = Lists.newArrayList();
        Map<String, Object> data = Maps.newHashMap();
        for (int i = 1; i <= 100; i++) {

            LedgerDictionary ledgerDictionary = new LedgerDictionary();
            ledgerDictionary.setSourceField("field" + i);
            if (i % 9 == 1) {
                ledgerDictionary.setFieldType("VARCHAR");
                ledgerDictionary.setLength(125);
                data.put("field" + i, "value" + i);
            } else if (i % 9 == 2) {
                ledgerDictionary.setFieldType("INT");
                ledgerDictionary.setLength(1);
                data.put("field" + i, i);
            } else if (i % 9 == 3) {
                ledgerDictionary.setFieldType("BOOLEAN");
                ledgerDictionary.setLength(0);
                data.put("field" + i, i > 50 ? 1 : 0);
            } else if (i % 9 == 4) {
                ledgerDictionary.setFieldType("TEXT");
                data.put("field" + i, "sssssssssssssssssssssss" + i);
            } else if (i % 9 == 5) {
                ledgerDictionary.setFieldType("DATETIME");
                data.put("field" + i, "2019-09-09 00:00:00");
            } else if (i % 9 == 6) {
                ledgerDictionary.setFieldType("FLOAT");
                data.put("field" + i, 22.22 + i);
            } else {
                ledgerDictionary.setFieldType("VARCHAR");
                ledgerDictionary.setLength(125);
                data.put("field" + i, "value" + i);
            }
            ledgerDictionary.setLdComment("field" + i + "Comment");
            ledgerDictionaries.add(ledgerDictionary);
        }
        data1.add(data);
        data1.add(data);

//        Map<String, Object> data = Maps.newHashMap();
//        data.put("field1", "newData1");
//        data.put("field2", "newDate2");
//
//        Map<String, Object> dataa = Maps.newHashMap();
//        dataa.put("field1", "newData11");
//        dataa.put("field2", "newDate22");
//
//        List<Map<String, Object>> data1 = Lists.newArrayList();
//        data1.add(data);
//        data1.add(dataa);

//        System.out.println(selectPageByParam(1, 10, data, ledger, ledgerDictionaries));
//        System.out.println(updateById(1L, ledger, ledgerDictionaries, data));
//        System.out.println(selectById(1L, ledger, ledgerDictionaries));
//        System.out.println(selectPage(1, 10, ledger, ledgerDictionaries));
//        System.out.println(createLedgerDictionaryTable(ledger, ledgerDictionaries));
        System.out.println(insertList(ledger, ledgerDictionaries, data1));

    }
}
