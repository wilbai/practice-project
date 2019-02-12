package com.wil.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wil on 2018/8/17.
 * 数据库连接，结果集处理等工具类
 */
public class DbHelp {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///blogsystem?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 新增，修改，删除
     * @param sql
     * @param params
     */
    public static Integer executeUpdate(String sql, Object... params) {

        Connection conn = getConnection();
        PreparedStatement pst = null;
        Integer id = 0;
        try {
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pst, conn);
        }
        return id;
    }

    public static <T> T queryForObject(String sql, RowMapper<T> row, Object... params) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        T obj = null;

        try {
            pst = conn.prepareStatement(sql);
            for(int i = 0; i < params.length; i++) {
                pst.setObject(i+1, params[i]);
            }
            rs = pst.executeQuery();

            if(rs.next()) {
                obj = row.mapperRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pst, conn);
        }
        return obj;
    }

    public static <T> List<T> queryForList(String sql, RowMapper<T> row, Object... params) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        List<T> list = new ArrayList<>();
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            for(int i = 0; i < params.length; i++) {
                pst.setObject(i+1, params[i]);
            }
            rs = pst.executeQuery();
            while (rs.next()) {
                T obj = row.mapperRow(rs);
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pst, conn);
        }

        return list;
    }

    public static Integer queryForCount(String sql, Object... params) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Integer count = 0;

        try {
            pst = conn.prepareStatement(sql);
            for(int i = 0; i < params.length; i++) {
                pst.setObject(i+1, params[i]);
            }
            rs = pst.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pst, conn);
        }
        return count;
    }



    public static void close(Statement sts, Connection conn) {

        try {
            if(sts != null && !sts.isClosed()) {
                sts.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(ResultSet rs, Statement sts, Connection conn) {
        try {
            if(rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(sts, conn);
        }
    }


}
