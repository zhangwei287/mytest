package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        final Target  target   =  new Target();
        final Enhance  enhance = new Enhance();

        Interface proxy = (Interface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        enhance.before();
                        method.invoke(target, args);
                        enhance.after();
                        return null;
                    }
                }
        );

        proxy.save();
    }
}
