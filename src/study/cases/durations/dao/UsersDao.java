package study.cases.durations.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import study.cases.durations.entity.Users;

/**
 * 数据层处理接口
 * 系统用户
 */
@Repository("usersDao")
public interface UsersDao extends BaseMapper<Users> {
	

}