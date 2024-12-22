package study.cases.durations.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import study.cases.durations.entity.Majors;

/**
 * 数据层处理接口
 * 专业信息
 */
@Repository("majorsDao")
public interface MajorsDao extends BaseMapper<Majors> {
	

}