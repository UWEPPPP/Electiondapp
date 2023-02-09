package DAO;

import Tool.MysqlUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 选举刀
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionDAO {
        private String key;
        private String http;

    public String getKey() {
        return key;
    }

    public String getHttp() {
        return http;
    }

    public ElectionDAO() throws SQLException, IOException {
            ResultSet resultSet= MysqlUtil.getResultSet();
            resultSet.next();
           key= (String) resultSet.getObject("key");
           http= (String) resultSet.getObject("http");
           MysqlUtil.close();
        }
}
