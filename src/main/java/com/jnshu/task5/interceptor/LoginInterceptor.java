package com.jnshu.task5.interceptor;

import com.jnshu.task5.beans.Login;
import com.jnshu.task5.service.LoginService;
import com.jnshu.task5.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    LoginService loginService;

    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    /*    //拦截器拦截未登陆用户访问/u/profession页面
        String str = request.getSession().getId();
        logger.info("sessionId = "+ str);
        String uri = request.getRequestURI();
        logger.info("uri" + uri);
        String loginName = (String) request.getSession().getAttribute("loginName");
        logger.info("loginName = " + loginName);
        //session中有数据,则正常访问
        if (loginName != null ){
            return true;
        }
        //session中没有数据,前台输出信息,返回到login页面
        request.setAttribute("errorMessage","未登录,请登陆");
        //重定向到/login页面,
        request.getRequestDispatcher("/login").forward(request,response);
        logger.error("getRequestDispatcher任务后");
        return false;*/
    //获取cookie中的token
        String token = request.getParameter("token");
        if(token == null){
            return  true;
        }

        //将token解码;
        Map map = null;
        try {
            map = JwtUtil.decode(token, "jnshu","123");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (token != null){
            String loginName = (String) map.get("loginName");
            String pwd = (String) map.get("pwd");
            logger.info("loginName + = " + loginName);
            logger.info("pwd =" + pwd);
            //与数据库进行比对
            Login login = loginService.selectLoginByName(loginName);
            logger.info("login = " +login);
            logger.info("getPwd = " + login.getPwd());

            if (pwd.equals(login.getPwd())){
                return true;
            }
        } else {
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
        return true;

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
