package com.beerus.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author Beerus
 * @Description 过滤所有请求过来的参数 防止XSS脚本攻击
 * @Date 2019-07-03
 **/
@Component
public class ParamFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(ParamFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    /**
     * 过滤参数
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 得到所有请求过来的参数名字
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, List<String>> requestQueryParams = context.getRequestQueryParams();
        // 声明集合保存请求过来的参数 被调用的微服务可以直接 去取,就想普通的一样,框架会直接注入进去
        ArrayList<String> paramsList = null;
         if (requestQueryParams == null) requestQueryParams = new HashMap<>();
        // 循环参数列表
        while (parameterNames.hasMoreElements()) {
            paramsList = new ArrayList<>();
            // 得到单个参数名
            String paramName = parameterNames.nextElement();
            // 得到请求过来的数据并使用Spring过滤网页脚本
            String changeParam = HtmlUtils.htmlEscape(request.getParameter(paramName));
            // 替换脚本并添加到集合
            paramsList.add(changeParam);
            // 替换原来的参数
            requestQueryParams.put(paramName, paramsList);
        }
        // 把参数放回request当中
        context.setRequestQueryParams(requestQueryParams);
        return null;
    }
}
