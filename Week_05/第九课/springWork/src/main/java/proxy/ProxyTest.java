package proxy;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 作业：使 Java 里的动态代理，实现一个简单的 AOP。
 */
public class ProxyTest {
    public static void main(String[] args) {

        System.out.println("基于JDK的动态代理");
        IHello hello = new Hello();
        InvocationHandler invocationHandler = new HelloProxy(hello);
        //  (代理类的类加载器，被代理类的接口(有多个传数组)，代理类实例)
        IHello proxyHello = (IHello) Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                invocationHandler);
        proxyHello.hello();

        System.out.println("\r\n基于CGLIB的动态代理");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGHello.class);
        enhancer.setCallback(new HelloInterceptor());
        CGHello cGsubject = (CGHello) enhancer.create();
        cGsubject.hello();
        System.out.println("cGsubject对象AOP代理后的实际类型："+cGsubject.getClass());

        /**
         * cglib的思想：通过“继承”可以继承父类所有的公开方法，然后可以重写这些方法，在重写时对这些方法增强
         * jdk动态代理要求对象必须实现接口，就是newProxyInstance的第二个参数，CGLIB对此没有要求。
         * 使用CGLIB会生成一个继承CGHello的类型（代理类），这个代理类持有一个MethodInterceptor，是setCallback时传入的。
         * 代理类中重写CGHello中的所有方法，然后构建名为“CGLIB”+“$父类方法名$”的方法（cglib方法）,方法体里只有一句话super.方法名()，
         * 可以简单的认为保持了对父类方法的一个引用，方便调用。
         * 代理类中就有了重写方法、cglib方法、父类方法（不可见），还有一个统一的拦截方法（增强方法intercept）。其中重写方法和cglib方法肯定是有映射关系的
         */
    }
}
