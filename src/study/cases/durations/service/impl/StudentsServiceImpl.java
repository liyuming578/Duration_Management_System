package study.cases.durations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.cases.durations.dao.DurationsDao;
import study.cases.durations.dao.UsersDao;
import study.cases.durations.entity.Durations;
import study.cases.durations.entity.Users;
import study.cases.durations.msg.PageData;
import study.cases.durations.entity.Students;
import study.cases.durations.dao.StudentsDao;
import study.cases.durations.service.StudentsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("studentsService")
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private DurationsDao durationsDao;

    @Override
    @Transactional
    public void add(Students students) {

        studentsDao.insert(students);
    }

    @Override
    @Transactional
    public void addStudent(Users user, Students student){

        usersDao.insert(user);
        studentsDao.insert(student);
    }

    @Override
    @Transactional
    public void update(Students students) {

        studentsDao.updateById(students);
    }

    @Override
    @Transactional
    public void delete(Students students) {

        Durations durations = durationsDao.selectById(students.getId());

        studentsDao.deleteById(students);

        Users user = usersDao.selectById(students.getId());
        usersDao.deleteById(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Students getOne(String id) {

        Students students = studentsDao.selectById(id);

        return students;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageInfo(Long pageIndex, Long pageSize, String userName, String name, String majorId) {

        Page<Map<String, Object>> page =
                studentsDao.qryPageInfos(new Page<Map<String, Object>>(pageIndex, pageSize), userName, name, majorId);

        return parsePage(page);
    }

    /**
     * 转化分页查询的结果
     */
    public PageData parsePage(Page<Map<String, Object>> p) {

        PageData pageData = new PageData(p.getCurrent(), p.getSize(), p.getTotal(), p.getRecords());

        return pageData;
    }
}