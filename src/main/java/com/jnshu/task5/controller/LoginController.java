package com.jnshu.task5.controller;

import com.jnshu.task5.beans.Login;
import com.jnshu.task5.beans.TokenSetting;
import com.jnshu.task5.service.LoginService;
import com.jnshu.task5.utils.CookieUtil;
import com.jnshu.task5.utils.JwtUtil;
import com.jnshu.task5.utils.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {



    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/testLoginJson" , method = RequestMethod.GET)
    public String textLoginJson(Model model){

//        Login login = new Login();
//        login.setLoginName("zzz");
//        login.setPwd("321");
//        model.addAttribute("login",login);
        return "json";
    }
    @RequestMapping(value = "/testReg",method = RequestMethod.POST)
    public String testRegJson(){
//        Login login = new Login();
//        login.setName("123");
//        login.setPwd("123");
//        login.setEmail("123@123.com");
//        login.setQq("111");
//        login.setPhone("321");
//        loginService.addlogin(login);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("login",login);
        return "json";
    }

//    /**
//     * 去往注册页面
//     * @return 去登陆login.jsp页面
//     */
//    @RequestMapping(value = "/registration")
//    public String registration(){
//
//        return "registration";
//    }


    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String toRegistrationPage(){
        return "registration";
    }



    /**
     *  注册功能
     * @return 重定向到home页
     */
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(String loginName, String pwd){
        Login login = new Login();
        login.setLoginName(loginName);
        String md5Pwd = Md5Util.digest(pwd);
//        String md5Pwd = Md5Util2.MD5Encode(pwd,"utf-8");
        login.setPwd(md5Pwd);
        System.out.println(login);
//        loginService.addlogin(login);
        loginService.addLoginAndPwd(login);
        return "redirect:/home";
    }

    //去往登陆页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLoginPage(){
        return "login";
    }

 /*   //实现登陆功能
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model, String loginName, String pwd){
        //登陆:
        Login login = loginService.selectLoginByName(loginName);
        //验证用户名和密码
        logger.info(loginName);
        System.out.println(login);
        String md5Pwd = Md5Util.digest(pwd);
//        String md5Pwd = login.getPwd();
        logger.info("用户输入密码" + pwd);
//        String md5Pwd = Md5Util3.EncoderByMd5(login.getPwd());
        logger.info("加密后的密码"+ md5Pwd);

        if (md5Pwd.equals(login.getPwd())){
            request.getSession().setAttribute("loginName",loginName);
            logger.info("loginController + " + loginName);
            return "redirect:/u/profession";
        } else {
            model.addAttribute("error","登陆失败");
            System.out.println("登陆失败");
            logger.info("logincontroller Error"  + loginName );
            return "redirect:/home";
        }


    }*/


    @RequestMapping(value = "/loginToken",method = RequestMethod.POST)
    public String loginToken(Map map, String loginName, String pwd, HttpServletResponse response, HttpServletRequest request) {
        String newPwd = Md5Util.digest(pwd);
        Login login = loginService.selectLoginByName(loginName);

        if (newPwd.equals(login.getPwd())){
            map.put("loginName",loginName);
            map.put("pwd",newPwd);
//            String key =TokenSetting.TOKEN_KEY + TokenSetting.TOKEN_SALT;
////            JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
//            JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);
//            jwtBuilder = jwtBuilder.setClaims(map);
//            String token = jwtBuilder.compact();
//            String newToken = JwtUtil.encode("jnshu",map,"123");
            String newToken = JwtUtil.encode(TokenSetting.TOKEN_KEY,map,TokenSetting.TOKEN_SALT);
            CookieUtil.setCookie(request,response,"token",newToken, TokenSetting.COOKIE_MAX_AGE,false);
            return "redirect:/home";
        }
        logger.info("账号或密码错误");
        return "redirect:/login";

        //从表单传来的数据制作成token数据;






            //解析token中的数据
//            map = JwtUtil.decode(token,TokenSetting.TOKEN_KEY,TokenSetting.TOKEN_SALT);
//            if (login.getLoginName().equals(map.get(loginName)) || login.getPwd().equals(map.get(pwd))){
//                logger.info("login.getloginName =" +login.getLoginName());
//                logger.info("loginName =" + loginName);
//                logger.info("login.getPwd = " + login.getPwd() );
//                logger.info("pwd = " + pwd );
//                return "/u/profession";



//        //将表单传过来的数据生成token;\
//        map.put("loginName",loginName);
//        map.put("pwd",Md5Util.digest(pwd));
//        String token = JwtUtil.encode(TokenSetting.TOKEN_KEY, map,TokenSetting.TOKEN_SALT);
//        logger.info("token " + token);
//        //将token放入到cookie中;
//        CookieUtil.setCookie(request,response,"token",token,TokenSetting.COOKIE_MAX_AGE,false);
//
//        return "/u/profession";
    }

    @RequestMapping(value = "/loginout" )
    public String loginout(HttpSession session){
        session.removeAttribute("isLogin");
        session.removeAttribute("loginName");
        session.invalidate();
        return "redirect:/home";
    }

 /*   @RequestMapping(value = "/loginToken", method = RequestMethod.POST)
    public String loginToken(HttpServletRequest request, String loginName,String pwd,Model model){
        //将当前时间作为盐值

        logger.info("loginToken salt = " + salt);
        //获取登陆信息
        //设置是否登陆信息
        boolean flag;
        //登陆:
        Login login = loginService.selectLoginByName(loginName);
        //验证用户名和密码

        String salt = String.valueOf(login.getId());
        logger.info(loginName);
        System.out.println(login);
        String md5Pwd = Md5Util.digest(pwd);
        logger.info("用户输入密码" + pwd);
        logger.info("加密后的密码"+ md5Pwd);

        if (md5Pwd.equals(login.getPwd())){
            //验证成功生成token
            request.getSession().setAttribute("loginName",loginName);
            logger.info("loginController + " + loginName);

            return "redirect:/u/profession";
        } else {
            model.addAttribute("error","登陆失败");
            System.out.println("登陆失败");
            logger.info("logincontroller Error"  + loginName );
            return "redirect:/home";
        }




        if (loginName != null){
            login = loginService.selectLoginByName(loginName);
            if (login == null){
                return "home";
            } else {
                //生成token
                Map<String,Object> map = new HashMap();
                map.put("loginName",login.getLoginName());
                map.put("pwd",login.getPwd());
                String token = JwtUtil.encode(tokenKey,map,salt);
                logger.info("token is =" + token);
                return token;
            }
        }
        return "home";
    }*/

  /*  //验证token方法
    @RequestMapping(value = "verify")
    public String verify(HttpServletRequest request){
        //从域中获取token和salt数据
        String token = request.getParameter("token");
        String salt = request.getParameter("salt");
        logger.info(token+ "");
        logger.info(salt + "");
        return "home";
    }*/



}
