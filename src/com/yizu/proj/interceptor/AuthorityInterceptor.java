package com.yizu.proj.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-5-29
 * Time: 下午10:03
 * To change this template use File | Settings | File Templates.
 */
public class AuthorityInterceptor extends AbstractInterceptor{
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        return actionInvocation.invoke();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
