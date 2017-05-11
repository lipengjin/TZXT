package com.tzxt.util;

public enum AccountType {
        ADMIN(1), ORDINARY_USER(2);

        private Integer value;

        AccountType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }