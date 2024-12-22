package study.cases.durations.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cases.durations.entity.Users;
import study.cases.durations.utils.IDUtils;
import study.cases.durations.msg.R;
import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Durations;
import study.cases.durations.service.DurationsService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 系统请求响应控制器
 * 时长记录
 */
@Controller
@RequestMapping("/durations")
public class DurationsController extends BaseController {

    protected static final Logger Log = LoggerFactory.getLogger(DurationsController.class);

    @Autowired
    private DurationsService durationsService;

    @RequestMapping("")
    public String index() {

        return "pages/Durations";
    }

    @RequestMapping("/show")
    public String show(Map<String, Object> map, HttpSession session) {

        Users user = (Users) getSessionUser(session);

        Durations duration = durationsService.getOne(user.getId());

        map.put("duration", duration);

        return "pages/ShowDurtions";
    }

    @GetMapping("/info")
    @ResponseBody
    public R getInfo(String id) {

        Log.info("查找指定时长记录，ID：{}", id);

        Durations durations = durationsService.getOne(id);

        return R.successData(durations);
    }

    @GetMapping("/page")
    @ResponseBody
    public R getPageInfos(Long pageIndex, Long pageSize,
                          String studentName, Integer type) {

        Log.info("分页查找时长记录，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，学生姓名：{}，查询类型：{}", pageIndex,
                pageSize, studentName, type);

        PageData page = durationsService.getPageInfo(pageIndex, pageSize, studentName, type);

        return R.successData(page);
    }

    @PostMapping("/add")
    @ResponseBody
    public R addInfo(Durations durations) {

        Log.info("添加时长记录，传入参数：{}", durations);

        durationsService.add(durations);

        return R.success();
    }

    @PostMapping("/upd")
    @ResponseBody
    public R updInfo(Durations durations) {

        Log.info("修改时长记录，传入参数：{}", durations);

        durationsService.update(durations);

        return R.success();
    }

    @PostMapping("/del")
    @ResponseBody
    public R delInfo(String id) {

        Log.info("删除时长记录, ID:{}", id);

        Durations durations = durationsService.getOne(id);

        durationsService.delete(durations);

        return R.success();
    }
}