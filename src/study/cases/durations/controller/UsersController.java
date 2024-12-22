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

import study.cases.durations.entity.Users;
import study.cases.durations.service.UsersService;

/**
 * 系统请求响应控制器
 * 系统用户
 */
@Controller
@RequestMapping("/users")
public class UsersController extends BaseController{

	protected static final Logger Log = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	@RequestMapping("")
	public String index() {

		return "pages/Users";
	}

	@GetMapping("/info")
	@ResponseBody
	public R getInfo(String id) {

		Log.info("查找指定系统用户，ID：{}", id);

		Users users = usersService.getOne(id);

		return R.successData(users);
	}

	@GetMapping("/page")
	@ResponseBody
	public R getPageInfos(Long pageIndex, Long pageSize, 
			Users users) {

		Log.info("分页查找系统用户，当前页码：{}，"
				+ "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex, 
				pageSize, users);

		PageData page = usersService.getPageInfo(pageIndex, pageSize, users);

		return R.successData(page);
	}

	@PostMapping("/add")
	@ResponseBody
	public R addInfo(Users users) {
       
                users.setId(IDUtils.makeIDByCurrent());

		Log.info("添加系统用户，传入参数：{}", users);

		usersService.add(users);

		return R.success();
	}

	@PostMapping("/upd")
	@ResponseBody
	public R updInfo(Users users) {

		Log.info("修改系统用户，传入参数：{}", users);

		usersService.update(users);

		return R.success();
	}

	@PostMapping("/del")
	@ResponseBody
	public R delInfo(String id) {

		Log.info("删除系统用户, ID:{}", id);

		Users users = usersService.getOne(id);

		usersService.delete(users);

		return R.success();
	}
}