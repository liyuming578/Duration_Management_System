package study.cases.durations.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 学生信息
 */
@TableName(value = "students")
public class Students implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 所属班级
	 */
	@TableField(value = "grade")
	private String grade;

		/**
	 * 所学专业
	 */
	@TableField(value = "major_id")
	private String majorId;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public String getGrade(){

		return grade;
	}

	public void setGrade(String grade){

		this.grade = grade;
	}

	
	public String getMajorId(){

		return majorId;
	}

	public void setMajorId(String majorId){

		this.majorId = majorId;
	}

	
	@Override
	public String toString() {

		return "Students [id=" + id 
				+ ", grade=" + grade
				+ ", majorId=" + majorId
				+ "]";
	}

}