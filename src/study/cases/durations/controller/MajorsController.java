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

import study.cases.durations.utils.IDUtils;
import study.cases.durations.msg.R;
import study.cases.durations.msg.PageData;

import study.cases.durations.entity.Majors;
import study.cases.durations.service.MajorsService;

/**
 * 系统请求响应控制器
 * 专业信息
 */
@Controller
@RequestMapping("/majors")
public class MajorsController extends BaseController {

    protected static final Logger Log = LoggerFactory.getLogger(MajorsController.class);

    @Autowired
    private MajorsService majorsService;

    @RequestMapping("")
    public String index() {

        return "pages/Majors";
    }

    @GetMapping("/info")
    @ResponseBody
    public R getInfo(String id) {

        Log.info("查找指定专业信息，ID：{}", id);

        Majors majors = majorsService.getOne(id);

        return R.successData(majors);
    }

    @GetMapping("/page")
    @ResponseBody
    public R getPageInfos(Long pageIndex, Long pageSize,
                          Majors majors) {

        Log.info("分页查找专业信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, majors);

        PageData page = majorsService.getPageInfo(pageIndex, pageSize, majors);

        return R.successData(page);
    }

    @PostMapping("/add")
    @ResponseBody
    public R addInfo(Majors majors) {

        majors.setId(IDUtils.makeIDByCurrent());

        Log.info("添加专业信息，传入参数：{}", majors);

        majorsService.add(majors);

        return R.success();
    }

    @PostMapping("/upd")
    @ResponseBody
    public R updInfo(Majors majors) {

        Log.info("修改专业信息，传入参数：{}", majors);

        majorsService.update(majors);

        return R.success();
    }

    @PostMapping("/del")
    @ResponseBody
    public R delInfo(String id) {

        Log.info("删除专业信息, ID:{}", id);

		if(majorsService.isRemove(id)){

			Majors majors = majorsService.getOne(id);

			majorsService.delete(majors);

			return R.success();
		}else{

			return R.warn("学生未结业，无法删除");
		}
    }
}