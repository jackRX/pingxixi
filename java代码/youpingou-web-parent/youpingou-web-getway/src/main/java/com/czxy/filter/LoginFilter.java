package com.czxy.filter;

import com.czxy.auth.entity.UserInfo;
import com.czxy.auth.utils.JwtUtils;
import com.czxy.config.FilterProperties;
import com.czxy.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProp;

    @Autowired
    private FilterProperties filterProp;

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest req = ctx.getRequest();
        // 获取路径
        String requestURI = req.getRequestURI();
        // 判断白名单
        boolean allowPath = !isAllowPath(requestURI);

        return allowPath;
    }
    private boolean isAllowPath(String requestURI) {
        // 定义一个标记
        boolean flag = false;
        // 遍历允许访问的路径
        for (String path : this.filterProp.getAllowPaths()) {
            // 然后判断是否是符合
              if(requestURI.contains(path)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = ctx.getRequest();
        // 获取token
        String token = request.getHeader("Authorization");
        // 校验
        if (token!=null){
            try {
                // 校验通过什么都不做，即放行
                UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProp.getPublicKey());
                System.out.println(userInfo);
                ctx.addZuulRequestHeader("authorization",token);
                ctx.addZuulRequestHeader("userId",userInfo.getId().toString());
                return null;
            } catch (Exception e) {
                // 校验出现异常，返回403
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(403);
                logger.error("非法访问，未登录，地址：{}", request.getRemoteHost(), e );
            }
        }

        return null;
    }
}