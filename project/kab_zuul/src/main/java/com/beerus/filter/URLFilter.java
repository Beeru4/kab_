package com.beerus.filter;

import com.alibaba.fastjson.JSON;
import com.beerus.common.pojo.MemberUser;
import com.beerus.common.utils.RedisConstant;
import com.beerus.redis.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Author Beerus
 * @Description 拦截路径
 * @Date 2019-06-15
 **/
@Component
public class URLFilter extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(ZuulFilter.class);
    // 跳转的路径
    private static final String SCRIPT = "<script>alert('您没有访问权限,请先登录!');window.location.href='/member/login'</script>";
    @Resource
    private RedisUtil redisUtil;
    @Value("${db.username}")
    private String userNaem;

    /**
     * 权限控制
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 得到request
        HttpServletRequest request = ctx.getRequest();
        // 得到session  为了后面方便拿到会话id
        HttpSession session = request.getSession();
        // 拿到当前访问的路径
        String url = request.getRequestURL().toString();
        // 得到Redis验证
        String Authname = redisUtil.get(RedisConstant.USER_KEY + session.getId().replace("-", ""), RedisConstant.USER_DB);
        // 读取用户 让页面显示
        // 判断当前会话缓存当中是否有用户
        if (null != Authname) {
            if (null == session.getAttribute("member")) {
                // 保存到当前用户当中
                session.setAttribute("member", JSON.parseObject(Authname, MemberUser.class));
            }
        }
        // 此处判断是否要拦截**************
        // 过滤方法
        if (url.contains("/member/checkUserName")
                ||url.contains("/member/memberRegister")
                ||url.contains("/member/updatePwd")
                ||url.contains("/member/modifyRegister")
                ||url.contains("/member/sendCode")
                || url.contains("/member/checkPhone")
                ||url.contains("/member/memberLogin")
                || url.contains("/member/login")
                || url.contains("/member/pwd")
                || url.contains("/member/register")) {
            return null;
        } else if (url.contains("/curriculum")
                || url.contains("/news")
                || url.contains("/comment")
                || url.contains("/survey")
                || url.contains("/consultation")
                || url.contains("/course")
                || url.contains("/public")) {
            return null;
        }
        // *******************开始拦截****************************
        // 没有加认证token 就没有访问权限
        if (StringUtils.isBlank(Authname)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            /* ctx.setResponseBody("{\"code\":401,\"msg\":\"没有访问权限！\"}");*/
          /*  ctx.setResponseBody("<a href=\"/member/login\">您没有访问权限,请先登录!</a>");*/
            ctx.setResponseBody(SCRIPT);
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
        }

        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

}
