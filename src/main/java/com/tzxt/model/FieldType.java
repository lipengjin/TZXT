package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 台账 数据字典 字段类型
 * <p>
 * Created by dell pc on 2017/5/7.
 */
@Data
public class FieldType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    private Date updateAt;

    /**
     * INT	        整形数据
     * FLOAT	    浮点型数据
     * VARCHAR	    字符型数据
     * DATETIME	    日期类型数据
     * TEXT	        文本类型数据
     * BOOLEAN	    bool类型数据
     */
    private String type;
    private String display;

    public enum Type {
        INT("INT"), FLOAT("FLOAT"), VARCHAR("VARCHAR"), DATETIME("DATETIME"), TEXT("TEXT"), BOOLEAN("BOOLEAN");

        private String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }


        public static Type getType(String value) {
            if (value == null || "".equals(value)) {
                return null;
            }
            for (Type t : Type.values()) {
                if (t.value.equals(value)) {
                    return t;
                }
            }
            return null;
        }
    }

}
