package java8;

public class lambdaTest {
    public static void main(String[] args){
        //java 7
        MyInterfaceImpl myInterface = new MyInterfaceImpl();
        enact(myInterface,"myInterface");

        // lambda
        enact((s)->System.out.println(s),"aBlockOfCode");
    }

    public static void enact(MyLambdaInterface myLambda,String s){
        myLambda.doSomeShit(s);
    }
}


interface MyLambdaInterface{
    void doSomeShit(String s);
}

class MyInterfaceImpl implements MyLambdaInterface{

    @Override
    public void doSomeShit(String s) {
        System.out.println(s);
    }
}
