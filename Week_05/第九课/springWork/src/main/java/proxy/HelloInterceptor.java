package proxy;

import lombok.NoArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@NoArgsConstructor
public class HelloInterceptor implements MethodInterceptor {


    /**
     *
     * @param o 被代理对象的实例
     * @param method
     * @param objects
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGInvoke start-----");
        // 不要使用invoke，会出现oom的情况
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("CGInvoke end-----");
        return object;
    }
}
