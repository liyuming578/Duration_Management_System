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

import study.cases.durations.entity.Majors;
import study.cases.durations.entity.Users;
import study.cases.durations.service.MajorsService;
import study.cases.durations.utils.IDUtils;
import study.cases.durations.msg.R;
import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Students;
import study.cases.durations.service.StudentsService;

import java.util.List;
import java.util.Map;

/**
 * 系统请求响应控制器
 * 学生信息
 */
@Controller
@RequestMapping("/students")
public class StudentsController extends BaseController {

    protected static final Logger Log = LoggerFactory.getLogger(StudentsController.class);

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private MajorsService majorsService;

    @RequestMapping("")
    public String index(Map<String, Object> map) {

        List<Majors> list = majorsService.getAll();

        map.put("majors", list);

        return "pages/Students";
    }

    @GetMapping("/info")
    @ResponseBody
    public R getInfo(String id) {

        Log.info("查找指定学生信息，ID：{}", id);

        Students students = studentsService.getOne(id);

        return R.successData(students);
    }

    @GetMapping("/page")
    @ResponseBody
    public R getPageInfos(Long pageIndex, Long pageSize,
                          String userName, String name, String majorId) {

        Log.info("分页查找学生信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，学生账号：{}，学生姓名：{}，学生专业：{}", pageIndex,
                pageSize, userName, name, majorId);

        PageData page = studentsService.getPageInfo(pageIndex, pageSize, userName, name, majorId);

        return R.successData(page);
    }

    @PostMapping("/add")
    @ResponseBody
    public R addInfo(Users user, Students students) {

        String id = IDUtils.makeIDByCurrent();

        students.setId(id);
        user.setId(id);

        Log.info("添加学生信息，传入参数：{}", students);

        studentsService.addStudent(user, students);

        return R.success();
    }

    @PostMapping("/upd")
    @ResponseBody
    public R updInfo(Students students) {

        Log.info("修改学生信息，传入参数：{}", students);

        studentsService.update(students);

        return R.success();
    }

    @PostMapping("/del")
    @ResponseBody
    public R delInfo(String id) {

        Log.info("删除学生信息, ID:{}", id);

        Students students = studentsService.getOne(id);

        studentsService.delete(students);

        return R.success();
    }
}