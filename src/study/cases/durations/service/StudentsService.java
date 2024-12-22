package study.cases.durations.service;

import study.cases.durations.entity.Users;
import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Students;

/**
 * 业务层处理
 * 学生信息
 */
public interface StudentsService extends BaseService<Students, String> {

	/**
	 * 添加学生信息
	 * @param user 学生账号
	 * @param student 学生信息
	 */
	public void addStudent(Users user, Students student);

	/**
	 * 分页查询学生信息信息
	 * @param pageIndex 当前页码
	 * @param pageSize 每页数据量
	 * @param userName 学生账号
	 * @param name 学生姓名
	 * @param majorId 学生专业
	 * @return
	 */	
	public PageData getPageInfo(Long pageIndex, Long pageSize, String userName, String name, String majorId);
}