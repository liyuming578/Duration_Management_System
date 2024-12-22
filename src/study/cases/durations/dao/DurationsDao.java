package study.cases.durations.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import study.cases.durations.entity.Durations;

import java.util.Map;

/**
 * 数据层处理接口
 * 时长记录
 */
@Repository("durationsDao")
public interface DurationsDao extends BaseMapper<Durations> {

    /**
     * 查看全部的活动时差信息
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageInfos(Page<Map<String, Object>> page,
                                                  @Param("studentName")String studentName);

    /**
     * 查看活动时长不足10的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND d.active &lt; 10 " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotActives(Page<Map<String, Object>> page,
                                                  @Param("studentName")String studentName);

    /**
     * 查看文体时长不足5的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND d.recreation &lt; 5 " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotRecreations(Page<Map<String, Object>> page,
                                                       @Param("studentName")String studentName);

    /**
     * 查看创新时长不足10的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND d.innovate &lt; 10 " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotInnovate(Page<Map<String, Object>> page,
                                                           @Param("studentName")String studentName);

    /**
     * 查看志愿时长不足30的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND d.wish &lt; 30 " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotWish(Page<Map<String, Object>> page,
                                                        @Param("studentName")String studentName);

    /**
     * 查看理想信念时长不足5的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND d.ideal &lt; 5 " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotIdeal(Page<Map<String, Object>> page,
                                                    @Param("studentName")String studentName);

    /**
     * 查看总时长不足60的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND d.total &lt; 60 " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotTotal(Page<Map<String, Object>> page,
                                                     @Param("studentName")String studentName);


    /**
     * 查看全部时长不足的记录
     * @param page 分页信息
     * @param studentName 学生姓名
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "d.id, d.active, d.ideal, d.recreation, d.innovate, d.wish, d.total, " +
            "(SELECT name FROM users WHERE id = d.id) studentName, " +
            "(SELECT gender FROM users WHERE id = d.id) studentGender,s.grade, " +
            "(SELECT name FROM majors WHERE id = s.major_id) majorName " +
            "FROM students s, durations d " +
            "<where>" +
            "s.id = d.id AND (d.active &lt; 10 OR d.recreation &lt; 5 " +
            "OR d.innovate &lt; 10 OR d.wish &lt; 30  OR d.ideal &lt; 5 ) " +
            "<if test='studentName != null and studentName.trim() != &quot;&quot; '>" +
            "AND (SELECT name FROM users WHERE id = d.id) LIKE CONCAT('%', #{studentName}, '%') " +
            "</if>" +
            "</where>" +
            "</script>")
    public Page<Map<String, Object>> qryPageNotAll(Page<Map<String, Object>> page,
                                                     @Param("studentName")String studentName);
}