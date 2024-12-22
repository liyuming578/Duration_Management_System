package study.cases.durations.service;

import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Majors;

import java.util.List;

/**
 * 业务层处理
 * 专业信息
 */
public interface MajorsService extends BaseService<Majors, String> {

	/**
	 * 是否可以删除专业
	 * @param id 专业编号
	 * @return
	 */
	public Boolean isRemove(String id);

	/**
	 * 获取全部的专业信息
	 * @return
	 */
	public List<Majors> getAll();

	/**
	 * 分页查询专业信息信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param majors 模糊查询条件
	 * @return
	 */	
	public PageData getPageInfo(Long pageIndex, Long pageSize, Majors majors);
}