package work.mybatis.dao;

import java.util.List;
import work.mybatis.model.student;

public interface studentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Fri Feb 19 21:08:36 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Fri Feb 19 21:08:36 CST 2021
     */
    int insert(student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Fri Feb 19 21:08:36 CST 2021
     */
    student selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Fri Feb 19 21:08:36 CST 2021
     */
    List<student> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table student
     *
     * @mbggenerated Fri Feb 19 21:08:36 CST 2021
     */
    int updateByPrimaryKey(student record);
}