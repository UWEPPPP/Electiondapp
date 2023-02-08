package Tool;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * mysql工具
 * * @author 刘家辉
 * @date 2023/02/08
 */
public class mysqlUtil {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    /**
     * 获得连接
     *
     */
       public static ResultSet getResultSet() throws SQLException, IOException {
            Properties properties=new Properties();
            FileReader fre=new FileReader("properties");
            properties.load(fre);
            String url= properties.getProperty("URL");
            String username=properties.getProperty("username");
            String password=properties.getProperty("password");
             connection=DriverManager.getConnection(url,username,password);
             preparedStatement= connection.prepareStatement("select * from information");
             resultSet= preparedStatement.executeQuery();
             return resultSet;

       }

    /**
     * 关闭
     *
     */
    public static void close() throws SQLException {
        if(resultSet!=null) {
            resultSet.close();
        }
        if(preparedStatement!=null) {
            preparedStatement.close();
        }
        if (connection!=null) {
                connection.close();
            }
        }

}
