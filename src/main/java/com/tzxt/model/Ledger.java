package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 台账
 *
 * Created by dell pc on 2017/5/7.
 */
@Data
public class Ledger {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    private Date updateAt;

    private String name;
    private String comment;

}
