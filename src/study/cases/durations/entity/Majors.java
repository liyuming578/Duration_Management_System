package study.cases.durations.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 专业信息
 */
@TableName(value = "majors")
public class Majors implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 专业名称
	 */
	@TableField(value = "name")
	private String name;

		/**
	 * 所属学院
	 */
	@TableField(value = "college")
	private String college;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public String getName(){

		return name;
	}

	public void setName(String name){

		this.name = name;
	}

	
	public String getCollege(){

		return college;
	}

	public void setCollege(String college){

		this.college = college;
	}

	
	@Override
	public String toString() {

		return "Majors [id=" + id 
				+ ", name=" + name
				+ ", college=" + college
				+ "]";
	}

}