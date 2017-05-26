package com.tzxt.model;

import lombok.Data;
import org.assertj.core.util.Lists;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 *
 * @author liuzh
 * @since 2016-01-31 21:39
 */
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String phone;

    private String realName;
    /**
     * 单位代码
     */
    private Long unitId;
    private String unit;

    /**
     * 账号 类型
     */
    private Integer accountType;

    /**
     * 角色
     */
    private Long roleId;
    private String role;

    /**
     * 权限
     */
    private Integer auth = Auth.SELECT.getValue();

    /**
     * 是否锁定
     */
    private Boolean locked;

    private Date createAt;
    private Date updateAt;

    public enum Auth {
        SELECT(1, "数据查询"), LOAD(2, "数据录入");

        private Integer value;
        private String display;

        Auth(Integer value, String display) {
            this.value = value;
            this.display = display;
        }

        public Integer getValue() {
            return value;
        }

        public String getDisplay() {
            return display;
        }

        public static Auth fromValue(Integer auth) {
            if (auth == null) {
                return null;
            }
            for (Auth auth1 : Auth.values()) {
                if (auth1.getValue().equals(auth)) {

                    return auth1;
                }
            }
            return null;
        }

        public static List<UserAuth> authList() {
            List<UserAuth> authList = Lists.newArrayList();
            for (Auth auth1 : Auth.values()) {
                authList.add(UserAuth.builder().id(Long.valueOf(auth1.getValue())).auth(auth1.getDisplay()).build());
            }
            return authList;
        }
    }

}
