package study.cases.durations.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.cases.durations.msg.PageData;
import study.cases.durations.entity.Durations;
import study.cases.durations.dao.DurationsDao;
import study.cases.durations.service.DurationsService;
import study.cases.durations.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("durationsService")
public class DurationsServiceImpl implements DurationsService {

    @Autowired
    private DurationsDao durationsDao;

    @Override
    @Transactional
    public void add(Durations durations) {

        double total = 0.0;

        if(durations.getActive() != null){

            total = total + durations.getActive();
        }

        if(durations.getIdeal() != null){

            total = total + durations.getIdeal();
        }

        if(durations.getRecreation() != null){

            total = total + durations.getRecreation();
        }

        if(durations.getInnovate() != null){

            total = total + durations.getActive();
        }

        if(durations.getWish() != null){

            total = total + durations.getWish();
        }

        durations.setTotal(total);

        durationsDao.insert(durations);
    }

    @Override
    @Transactional
    public void update(Durations durations) {

        double total = 0.0;

        if(durations.getActive() != null){

            total = total + durations.getActive();
        }

        if(durations.getIdeal() != null){

            total = total + durations.getIdeal();
        }

        if(durations.getRecreation() != null){

            total = total + durations.getRecreation();
        }

        if(durations.getInnovate() != null){

            total = total + durations.getActive();
        }

        if(durations.getWish() != null){

            total = total + durations.getWish();
        }

        durations.setTotal(total);

        durationsDao.updateById(durations);
    }

    @Override
    @Transactional
    public void delete(Durations durations) {

        durationsDao.deleteById(durations);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Durations getOne(String id) {

        Durations durations = durationsDao.selectById(id);

        return durations;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageData getPageInfo(Long pageIndex, Long pageSize, String studentName, Integer type) {

        if(type != null){

            if(type == 0){

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotActives(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }else if(type == 1){

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotRecreations(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }else if(type == 2){

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotInnovate(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }else if(type == 3){

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotWish(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }else if(type == 4){

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotIdeal(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }else if(type == 5){

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotTotal(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }else{

                Page<Map<String, Object>> page =
                        durationsDao.qryPageNotAll(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

                return parsePage(page);
            }
        }else{

            Page<Map<String, Object>> page =
                    durationsDao.qryPageInfos(new Page<Map<String, Object>>(pageIndex, pageSize), studentName);

            return parsePage(page);
        }
    }

    /**
     * 转化分页查询的结果
     */
    public PageData parsePage(Page<Map<String, Object>> p) {

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        PageData pageData = new PageData(p.getCurrent(), p.getSize(), p.getTotal(), p.getRecords());

        return pageData;
    }
}