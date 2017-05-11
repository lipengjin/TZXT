/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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

    enum Auth {
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
    }

}
