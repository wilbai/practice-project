package com.wil.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wil on 2018/8/17.
 * 结果集处理接口
 */
public interface RowMapper<T> {
    T mapperRow(ResultSet rs) throws SQLException;
}
