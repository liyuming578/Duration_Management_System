package study.cases.durations.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 数据实体类
 * 时长记录
 */
@TableName(value = "durations")
public class Durations implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

		/**
	 * 活动时长
	 */
	@TableField(value = "active")
	private Double active;

		/**
	 * 理想信念
	 */
	@TableField(value = "ideal")
	private Double ideal;

		/**
	 * 文体时长
	 */
	@TableField(value = "recreation")
	private Double recreation;

		/**
	 * 创新创业
	 */
	@TableField(value = "innovate")
	private Double innovate;

		/**
	 * 志愿时长
	 */
	@TableField(value = "wish")
	private Double wish;

		/**
	 * 时长总计
	 */
	@TableField(value = "total")
	private Double total;

	
	public String getId(){

		return id;
	}

	public void setId(String id){

		this.id = id;
	}

	
	public Double getActive(){

		return active;
	}

	public void setActive(Double active){

		this.active = active;
	}

	
	public Double getIdeal(){

		return ideal;
	}

	public void setIdeal(Double ideal){

		this.ideal = ideal;
	}

	
	public Double getRecreation(){

		return recreation;
	}

	public void setRecreation(Double recreation){

		this.recreation = recreation;
	}

	
	public Double getInnovate(){

		return innovate;
	}

	public void setInnovate(Double innovate){

		this.innovate = innovate;
	}

	
	public Double getWish(){

		return wish;
	}

	public void setWish(Double wish){

		this.wish = wish;
	}

	
	public Double getTotal(){

		return total;
	}

	public void setTotal(Double total){

		this.total = total;
	}

	
	@Override
	public String toString() {

		return "Durations [id=" + id 
				+ ", active=" + active
				+ ", ideal=" + ideal
				+ ", recreation=" + recreation
				+ ", innovate=" + innovate
				+ ", wish=" + wish
				+ ", total=" + total
				+ "]";
	}

}