package com.mmall.controller.interceptor;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("preHandle----------------------------------------------------");

        //请求中Controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod) o;
        //解析HandlerMethod
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();

        //解析参数,具体的参数key以及value是什么，我们打印日志
        StringBuffer requestParamBuffer = new StringBuffer();
        Map paramMap = httpServletRequest.getParameterMap();
        Iterator it = paramMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String mapKey = (String) entry.getKey();
            String mapValue = StringUtils.EMPTY;
            //request这个参数的map，里面的value返回的是一个String[]
            Object obj = entry.getValue();
            if (obj instanceof String[]) {
                String[] strs = (String[]) obj;
                mapValue = Arrays.toString(strs);
            }
            requestParamBuffer.append(mapKey).append("=").append(mapValue);
        }

        if (StringUtils.equals(className, "UserManageController") && StringUtils.equals(methodName, "login")) {
            //如果是拦截到登录请求，不打印参数，因为参数里面有密码，全部会打印到日志中，防止日志泄露
            log.info("权限拦截器拦截到请求,className:{},methodName:{}", className, methodName);
            return true;
        }

        log.info("权限拦截器拦截到请求,className:{},methodName:{},param:{}", className, methodName, requestParamBuffer.toString());

        User user = null;

        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isNotEmpty(loginToken)) {
            String userJsonStr = RedisPoolUtil.get(loginToken);
            user = JsonUtil.stringToObj(userJsonStr, User.class);
        }

        if (user == null) {
            httpServletResponse.reset();// 这里要添加reset，否则报异常 getWriter() has already been called for this response.
            httpServletResponse.setCharacterEncoding("UTF-8");// 这里要设置编码，否则会乱码
            httpServletResponse.setContentType("application/json;charset=UTF-8");// 这里要设置返回值的类型，因为全部是json接口。
            PrintWriter out = httpServletResponse.getWriter();
            out.print(JsonUtil.objToString(ServerResponse.createByErrorMessage("拦截器拦截,用户未登录")));
            out.flush();
            out.close();//这里要关闭
            return false;//返回false.即不会调用controller里的方法
        }

        //有user登录信息
        if (StringUtils.equals(className, "ProductManageController") &&
                StringUtils.equals(methodName, "richtextImgUpload")) {

            if (user.getRole() != Const.Role.ROLE_ADMIN) {//不是管理员
                httpServletResponse.reset();// 这里要添加reset，否则报异常 getWriter() has already been called for this response.
                httpServletResponse.setCharacterEncoding("UTF-8");// 这里要设置编码，否则会乱码
                httpServletResponse.setContentType("application/json;charset=UTF-8");// 这里要设置返回值的类型，因为全部是json接口
                PrintWriter out = httpServletResponse.getWriter();
                out.print(JsonUtil.objToString(ServerResponse.createByErrorMessage("拦截器拦截,用户无权限操作")));
                out.flush();
                out.close();// 这里要关闭
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle----------------------------------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        log.info("afterCompletion----------------------------------------------------");
    }
}
