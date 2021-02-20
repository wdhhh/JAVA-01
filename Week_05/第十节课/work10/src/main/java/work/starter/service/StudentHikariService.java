package work.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
public class StudentHikariService {

    @Autowired
    public JdbcTemplate jdbcTemplate;


    /**
     * 新增
     * @param studentName
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return
     */
    public int insert(String studentName) {
        if (null==studentName){
            return 0;
        }
        return jdbcTemplate.update("INSERT INTO student(NAME) VALUES(?)",studentName);
    }

    /**
     * 删除
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return
     */
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM student WHERE ID = ?",id);
    }

    /**
     * 修改
     * @param studentName
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return
     */
    public int update(String studentName, int id) {
        if (null==studentName){
            return 0;
        }

        return jdbcTemplate.update("UPDATE student set name = ? where id = ?",studentName,id);
    }

    /**
     * 查询
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return
     */
    public List<Map<String, Object>> select()  {
        return jdbcTemplate.queryForList("select * from student");
    }

    /**
     * 批量更新
     * @param students
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int[] updateBatch(List<Object[]> students) {
        return jdbcTemplate.batchUpdate("UPDATE student set name = ? where name = ?",students);
    }



}
