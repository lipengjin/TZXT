package com.tzxt.service;

import com.tzxt.model.Unit;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by wangshang on 17/5/12.
 */
public interface UnitService {
    Response<List<Unit>> selectAll();

    Response<Unit> findById(Long id);
}
