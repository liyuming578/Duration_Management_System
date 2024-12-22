package study.cases.durations.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import study.cases.durations.msg.R;
import study.cases.durations.entity.Users;
import study.cases.durations.service.UsersService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    private static final Logger Log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping("")
    public String index(){

        return "pages/Index";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public R login(String userName, String passWord, HttpSession session){

        Log.info("用户登录，用户名：{}， 用户密码：{}", userName, passWord);

        Users user = usersService.getUserByUserName(userName);

        if(user == null) {

            return R.error("输入的用户名不存在");
        }else {

            if(passWord.equals(user.getPassWord().trim())) {

                setSessionUser(session, user);

                return R.successMsg("登录成功");
            }else {

                return R.error("输入的密码错误");
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        Log.info("用户退出系统并移除登录信息");

        session.removeAttribute(SESSION_USER);

        return "redirect:/login";
    }

    @GetMapping("/info")
    @ResponseBody
    public R info(HttpSession session, Map<String, Object> map){

        Users user = (Users) getSessionUser(session);

        return R.successData(user);
    }

    @PostMapping("/info")
    @ResponseBody
    public R info(HttpSession session, Users user){

        Log.info("修改用户信息，{}", user);

        usersService.update(user);

        session.removeAttribute(SESSION_USER);

        setSessionUser(session, user);

        return R.success();
    }

    @PostMapping("/pwd")
    @ResponseBody
    public R pwd(HttpSession session, String password) {

        Log.info("修改用户密码，{}", password);

        Users user = (Users) getSessionUser(session);

        user.setPassWord(password);

        usersService.update(user);

        session.removeAttribute(SESSION_USER);

        setSessionUser(session, user);

        return R.success();
    }

    @GetMapping("/rePwd")
    @ResponseBody
    public R rePwd(String pwd, HttpSession session) {

        Log.info("校验用户密码，输入密码：{}", pwd);

        Users user = (Users) getSessionUser(session);

        if(pwd.equals(user.getPassWord())) {

            return R.successMsg("输入密码正确");
        }else {

            return R.warn("输入原始密码不匹配");
        }
    }
}