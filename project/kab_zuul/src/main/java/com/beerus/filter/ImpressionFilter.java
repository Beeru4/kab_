package com.beerus.filter;

import com.beerus.utils.SensitiveWord;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * @Author Beerus
 * @Description 铭感词过滤
 * @Date 2019-07-03
 **/
@Component
public class ImpressionFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(ImpressionFilter.class);
    /**
     * 敏感词过滤
     */
    @Resource
    private SensitiveWord sensitiveWord;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    /**
     * 过滤铭感词
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        long startTimer = System.currentTimeMillis();
        RequestContext cxt = RequestContext.getCurrentContext();
        // 得到request对象
        HttpServletRequest request = cxt.getRequest();
        // 判断该路径是否要进行敏感词过滤
        if (request.getRequestURL().toString().contains("/public/sensor")) {
            // 拿到请求过来的参数名
            Enumeration<String> parameterNames = request.getParameterNames();
            // 循环拿到请求过来的数据并判断是否包括铭感词
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String parameter = request.getParameter(paramName);
                if (sensitiveWord.filterInfo(parameter)) {
                    cxt.getResponse().setContentType("application/json;charset=UTF-8");
                    cxt.setResponseBody("{\"code\":\"-1\",\"message\":\"error is sensor\"}");
                    break;
                }
            }
            cxt.setSendZuulResponse(false);
            long endTimer = System.currentTimeMillis();
            logger.info("耗时:" + (endTimer - startTimer));
            return null;
        } else {
            return null;
        }
    }
}


