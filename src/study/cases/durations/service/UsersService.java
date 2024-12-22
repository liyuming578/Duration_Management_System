package study.cases.durations.service;

import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Users;

/**
 * 业务层处理
 * 系统用户
 */
public interface UsersService extends BaseService<Users, String> {

	/**
	 * 依据用户账号获取用户信息
	 * @param userName 用户账号
	 * @return
	 */
	public Users getUserByUserName(String userName);

	/**
	 * 分页查询系统用户信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param users 模糊查询条件
	 * @return
	 */	
	public PageData getPageInfo(Long pageIndex, Long pageSize, Users users);
}