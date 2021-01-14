package com.work;

import jvm.HelloClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        try{
            String path = "E:\\code\\java\\geektimedemo\\MyHomeWork\\out\\production\\MyHomeWork\\files\\Hello";
            HelloClassLoader helloClassLoader = new HelloClassLoader(path);
            //根据名称加载字节码文件
            Class<?> c = helloClassLoader.findClass("Hello");
            //用newInstance()创建对象实例
            Object helloClass = c.newInstance();
            //使用 getMethod(String name, Class<?>... parameterTypes)获得对象声明的公开方法,name:方法名，parameterTypes按声明顺序标识该方法形参类型
            Method method = c.getMethod("hello",null);
            //调用invoke(Object obj, Object... args)来调用helloClass
            method.invoke(helloClass,null);
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
