package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloProxy implements InvocationHandler {

    private IHello hello;

    public HelloProxy(IHello hello){
        this.hello = hello;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke start-----");
        Object invoke = method.invoke(hello, args);
        System.out.println("invoke end-----");
        return invoke;
    }
}
