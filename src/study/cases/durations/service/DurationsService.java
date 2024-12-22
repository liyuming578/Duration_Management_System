package study.cases.durations.service;

import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Durations;

/**
 * 业务层处理
 * 时长记录
 */
public interface DurationsService extends BaseService<Durations, String> {

	/**
	 * 分页查询时长记录信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param studentName 学生姓名
	 * @param type 不足类型
	 * @return
	 */	
	public PageData getPageInfo(Long pageIndex, Long pageSize, String studentName, Integer type);
}