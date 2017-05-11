package com.tzxt.util;

import com.google.common.collect.Lists;
import com.tzxt.model.FieldType.Type;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;

import java.util.List;

/**
 * SQLUtil 工具类，根据 参数 组装 sql 语句
 * Created by wangshang on 17/5/11.
 */
public class SQLUtil {

    /**
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
//        new SQL() {{
//            SELECT()
//        }}.toString();
        return stringBuilder.toString();
    }

    private static final String SPACE = " ";
    private static final String REVERSE_DOT = "`";
    private static final String DOT = ",\n";
    private static final String LEFT = "(";
    private static final String RIGHT = ")";
    private static final String CREATE_AT = "`create_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'" + DOT;
    private static final String UPDATE_AT = "`update_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'" + DOT;
    private static final String PRIMARY_KEY = "PRIMARY KEY (`id`)" + DOT;

    private static String CREATE(String tableName) {
        return "CREATE TABLE `" + tableName + "` (\n `id` BIGINT(20) NOT NULL AUTO_INCREMENT" + DOT;
    }

    private static String COLUMN(LedgerDictionary ledgerDictionary) {
        return REVERSE_DOT + ledgerDictionary.getFieldName() + REVERSE_DOT + SPACE + fieldType(ledgerDictionary) + " COMMENT '" + ledgerDictionary.getLdComment() + "'" + DOT;
    }

    private static String END(String tableComment) {
        return ") ENGINE=InnoDB DEFAULT COMMENT='" + tableComment + "';";
    }

    private static String fieldType(LedgerDictionary ledgerDictionary) {
        switch (Type.getType(ledgerDictionary.getFieldType())) {
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

    public static void main(String[] args) {

        Ledger ledger = new Ledger();
        ledger.setTableName("ledger1");
        ledger.setComment("ledger1Comment");
        List<LedgerDictionary> ledgerDictionaries = Lists.newArrayList();
        for (int i = 1; i <= 6; i++) {
            LedgerDictionary ledgerDictionary = new LedgerDictionary();
            ledgerDictionary.setFieldName("field" + i);
            if (i==1) {
                ledgerDictionary.setFieldType("VARCHAR");
                ledgerDictionary.setLength(125);
            }
            if (i==2) {
                ledgerDictionary.setFieldType("INT");
                ledgerDictionary.setLength(1);
            }

            if (i==3) {
                ledgerDictionary.setFieldType("BOOLEAN");
                ledgerDictionary.setLength(0);
            }

            if (i==4) {
                ledgerDictionary.setFieldType("TEXT");
            }

            if (i==5) {
                ledgerDictionary.setFieldType("DATETIME");
            }

            if (i==6) {
                ledgerDictionary.setFieldType("FLOAT");
            }
            ledgerDictionary.setLdComment("field" + i + "Comment");
            ledgerDictionaries.add(ledgerDictionary);
        }

        System.out.println(createLedgerDictionaryTable(ledger, ledgerDictionaries));
    }
}
