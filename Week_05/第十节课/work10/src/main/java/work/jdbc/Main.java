package work.jdbc;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        JDBCUtil02.select();

        JDBCUtil02.batch01();

        JDBCUtil02.select();

    }
}
