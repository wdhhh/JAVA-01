package work.jdbc;

import java.sql.*;

/**
 * 作业：使用 JDBC 原生接口，实现数据库的增删改查操作
 */
public class JDBCUtil01 {
    public static final String URL="jdbc:mysql://localhost:3306/work10";
    public static final String USER="root";
    public static final String PWD="root";
    public static Connection connection;

    /**
     * 新增
     * @param studentName
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void insert(String studentName) throws ClassNotFoundException, SQLException {
        if (null==studentName){
            return;
        }
        // 1.加载注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.获取连接对象connection
        connection = DriverManager.getConnection(URL, USER, PWD);
        // 3.获取语句对象statement
        Statement statement = connection.createStatement();
        // 4.执行sql

        System.out.println("新增学生："+studentName);
        String insertSql = "INSERT INTO student(NAME) VALUES(\""+studentName+"\")";
        int ret =  statement.executeUpdate(insertSql);
        System.out.println("受影响的行数"+ret);

        //5.关闭资源
        statement.close();
        connection.close();
    }

    /**
     * 删除
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void delete(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("删除学生："+id);
        String deleteSql = "DELETE FROM student WHERE ID = "+ id;
        int ret =  statement.executeUpdate(deleteSql);
        System.out.println("受影响的行数"+ret);

        statement.close();
        connection.close();
    }

    /**
     * 修改
     * @param studentName
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void update(String studentName,int id) throws ClassNotFoundException, SQLException {
        if (null==studentName){
            return;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("修改id为："+id+"的学生为："+studentName);
        String updateSql = "UPDATE student set name = '"+studentName+"' where id = " + id;
        int ret =  statement.executeUpdate(updateSql);
        System.out.println("受影响的行数"+ret);

        statement.close();
        connection.close();
    }

    /**
     * 查询
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void select() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("查询学生信息：");
        String selectSql = "select * from student";
        ResultSet resultSet =  statement.executeQuery(selectSql);
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            System.out.println("学生-id：" + id + ",姓名：" + name);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }


}
