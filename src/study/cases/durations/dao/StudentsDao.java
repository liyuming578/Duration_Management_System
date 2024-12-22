package study.cases.durations.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import study.cases.durations.entity.Students;

import java.util.Map;

/**
 * 数据层处理接口
 * 学生信息
 */
@Repository("studentsDao")
public interface StudentsDao extends BaseMapper<Students> {

    @Select("<script>" +
            "SELECT " +
            "u.id, u.user_name userName, u.pass_word passWord, " +
            "u.name, u.gender, u.age, u.type, " +
            "s.grade, s.major_id majorId,  " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM users u, students s " +
            "<where>" +
            "u.id = s.id " +
            "<if test='userName != null and userName.trim() != &quot;&quot; '>" +
            "AND u.user_name LIKE CONCAT('%', #{userName}, '%') " +
            "</if>" +
            "<if test='name != null and name.trim() != &quot;&quot; '>" +
            "AND u.name LIKE CONCAT('%', #{name}, '%') " +
            "</if>" +
            "<if test='majorId != null and majorId.trim() != &quot;&quot; '>" +
            "AND s.major_id = #{majorId} " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageInfos(Page<Map<String, Object>> page,
                                                  @Param("userName") String userName,
                                                  @Param("name")String name,
                                                  @Param("majorId")String majorId);
}