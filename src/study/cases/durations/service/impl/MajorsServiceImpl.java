package study.cases.durations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.cases.durations.dao.StudentsDao;
import study.cases.durations.entity.Students;
import study.cases.durations.msg.PageData;
import study.cases.durations.entity.Majors;
import study.cases.durations.dao.MajorsDao;
import study.cases.durations.service.MajorsService;
import study.cases.durations.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("majorsService")
public class MajorsServiceImpl implements MajorsService {

    @Autowired
    private MajorsDao majorsDao;

    @Autowired
    private StudentsDao studentsDao;

    @Override
    @Transactional
    public void add(Majors majors) {

        majorsDao.insert(majors);
    }

    @Override
    @Transactional
    public void update(Majors majors) {

        majorsDao.updateById(majors);
    }

    @Override
    @Transactional
    public void delete(Majors majors) {

        majorsDao.deleteById(majors);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Boolean isRemove(String id){

        QueryWrapper<Students> qw = new QueryWrapper<Students>();
        qw.eq("major_id", id);

        Integer total = studentsDao.selectCount(qw);

        return total <= 0;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Majors getOne(String id) {

        Majors majors = majorsDao.selectById(id);

        return majors;
    }

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Majors> getAll(){

		List<Majors> list = majorsDao.selectList(null);

		return list;
	}

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageInfo(Long pageIndex, Long pageSize, Majors majors) {

        QueryWrapper<Majors> qw = new QueryWrapper<Majors>();

		if(StringUtils.isNotNullOrEmpty(majors.getName())){

			qw.like("name", majors.getName());
		}

		if(StringUtils.isNotNullOrEmpty(majors.getCollege())){

			qw.like("college", majors.getCollege());
		}

        Page<Majors> page =
                majorsDao.selectPage(new Page<Majors>(pageIndex, pageSize), qw);

        return parsePage(page);
    }

    /**
     * 转化分页查询的结果
     */
    public PageData parsePage(Page<Majors> p) {

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        for (Majors majors : p.getRecords()) {

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id", majors.getId());
            temp.put("name", majors.getName());
            temp.put("college", majors.getCollege());
            resl.add(temp);
        }

        PageData pageData = new PageData(p.getCurrent(), p.getSize(), p.getTotal(), resl);

        return pageData;
    }
}