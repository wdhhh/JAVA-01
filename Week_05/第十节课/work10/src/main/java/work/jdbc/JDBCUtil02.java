package work.jdbc;

import java.sql.*;

/**
 * 使用事务，PrepareStatement 方式，批处理方式，改进JDBC操作
 */
public class JDBCUtil02 {
    public static final String URL="jdbc:mysql://localhost:3306/work10";
    public static final String USER="root";
    public static final String PWD="root";
    public static Connection connection;


    /**
     * 新增，PrepareStatement
     * @param studentName
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void insert(String studentName) throws ClassNotFoundException, SQLException {
        if (null==studentName){
            return;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("新增学生："+studentName);
        PreparedStatement insertPreSatement = connection.prepareStatement("INSERT INTO student(NAME) VALUES(?)");
        insertPreSatement.setString(1,studentName);
        int ret = insertPreSatement.executeUpdate();
        System.out.println("受影响的行数"+ret);

        //5.关闭资源
        statement.close();
        connection.close();
    }

    /**
     * 删除，PrepareStatement
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void delete(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("删除学生："+id);
        PreparedStatement delPreSatement = connection.prepareStatement("DELETE FROM student WHERE ID = ?");
        delPreSatement.setInt(1,id);
        int ret =  delPreSatement.executeUpdate();
        System.out.println("受影响的行数"+ret);

        statement.close();
        connection.close();
    }

    /**
     * 修改，PrepareStatement
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
        PreparedStatement updatePreSatement = connection.prepareStatement("UPDATE student set name = ? where id = ?");
        updatePreSatement.setString(1,studentName);
        updatePreSatement.setInt(2,id);
        int ret =  updatePreSatement.executeUpdate();
        System.out.println("受影响的行数"+ret);

        statement.close();
        connection.close();
    }

    /**
     * 查询，PrepareStatement
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void select() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("查询学生信息：");
        PreparedStatement selectPreSatement = connection.prepareStatement("select * from student");
        ResultSet resultSet =  selectPreSatement.executeQuery();
        while (resultSet.next()){
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            System.out.println("学生-id：" + id + ",姓名：" + name);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * 批处理 事务 + PrepareStatement + 批处理
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void batch01() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("批量处理:");
        try{
            connection.setAutoCommit(false);
            PreparedStatement insertPreSatement = connection.prepareStatement("INSERT INTO student(NAME) VALUES(?)");
            insertPreSatement.setString(1,"studentChou");
            insertPreSatement.addBatch();
            insertPreSatement.setString(1,"studentWu");
            insertPreSatement.addBatch();
           /* insertPreSatement.setString(3,"studentWu");
            insertPreSatement.addBatch();*/
            insertPreSatement.executeBatch();
            connection.commit();
            System.out.println("批量完成");
        }catch (Exception e){
            System.out.println("出现异常，事务回滚");
            connection.rollback();
        }

        statement.close();
        connection.close();
    }

    /**
     * 批处理2 事务 + PrepareStatement + 批处理
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void batch02() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PWD);
        Statement statement = connection.createStatement();

        System.out.println("批量处理:");
        try{
            connection.setAutoCommit(false);
            statement.addBatch("INSERT INTO student(NAME) VALUES('batchAdd01')");
            statement.addBatch("INSERT INTO student(NAME) VALUES('batchAdd02')");
            statement.addBatch("INSERT INTO student(NAME) VALUES('batchAdd03')");
            statement.addBatch("UPDATE student set name = 'batchAdd03update' where name = 'batchAdd03'");
            statement.addBatch("delete from student where name = 'batchAdd01'");
            //测试回滚
            //statement.addBatch("select * from ");
            statement.executeBatch();
            connection.commit();
            System.out.println("批量完成");
        }catch (Exception e){
            System.out.println("出现异常，事务回滚");
            connection.rollback();
        }


        statement.close();
        connection.close();
    }


}
