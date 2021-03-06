package com.joker.tecsteps.javatecsteps.reflectsteps;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

/**
 * Created by liwenle on 17-2-20.
 */
public class ReflectStepTest {
    @Test
    public void formatBigCount(){
        long bigCount = 54023;
        String result;
        if(bigCount < 10000){
            result = String.valueOf(bigCount);
        }else if(bigCount < 99990000){
            DecimalFormat format = new DecimalFormat("#.0");
            float  calc = (bigCount * 0.01f /10000) * 100;
            result = format.format(calc) + "w";
        }else {
            result = "9999w+";
        }
        System.out.println("result: " + result);
//        return result;
    }

    @Test
    public  void testReflectStep(){
        try{
            // 通配符　代表不知道具体的类型　是java泛型的一部分
            // ? extends classA 代表classA的某个子类
            Class<?> step = Class.forName("com.joker.tecsteps.javatecsteps.reflectsteps.ReflectStep");

            /**
            step.isAnonymousClass(); //是否是匿名内部类
            step.isInterface();　// 是否是一个接口
            step.isMemberClass();//是否是内部类
             step.isEnum(); //　是否是枚举
             Class.isAssignableFrom(Class class1); Class 是否跟class1一样或者是它的超类或借口
             */

            System.out.println("----print constructor begin ----");
            for (Constructor constructor : step.getConstructors()){
                System.out.println(constructor.toString());
            }
            System.out.println("----print constructor end----");

            System.out.println("----print public methods begin ----");
            for (Method method : step.getDeclaredMethods()){
                System.out.println(method.toString());
            }
            System.out.println("----print public methods end ----");

            System.out.println("----print interface begin ----");
            for (Class inteference : step.getInterfaces()){
                System.out.println(inteference.toString());
                for(Method method : inteference.getDeclaredMethods()){
                    System.out.println("interface method: " + method.toString());
                }
            }
            System.out.println("----print public interface end ----");

            System.out.println("----print public declared method begin ----");
            for (Method method : step.getDeclaredMethods()){
                System.out.println(method.toString());
            }
            System.out.println("----print public declared method end ----");

            System.out.println("----print public declared field begin ----");
            for(Field field : step.getDeclaredFields()){
                System.out.println(field.toString());
            }
            System.out.println("----print public declared field end ----");
            /**
             * 调用私有方法
             */
            ReflectStep instance = (ReflectStep) step.newInstance();
            instance.setTag("test1");
            Method method = step.getDeclaredMethod("printTag");
            method.setAccessible(true);
            method.invoke(instance);
            method.getModifiers();// 获取方法的关键字　public abstract synchorized等
            method.getParameterTypes(); // 参数类型列表
            /**
             * 访问私有成员变量　判断变量的值
             * 更改私有成员变量
             */
            Field field = step.getDeclaredField("protectedTag");
            if(field != null){
                field.setAccessible(true);
            }
            if(field.get(instance) == null){
                field.set(instance, "new protected tag");
            }

            Method method2 = step.getDeclaredMethod("testProtectMethod");
            method2.setAccessible(true);
            method2.invoke(instance);

            // 动态类型判断
            //  instanceof 与　isInstance　区别在于　instanceof考虑了继承　而isInstance没有
            if(instance instanceof IReflectInterface){
                System.out.println("instance implemention IReflectInterface");
            }

            if(step.isInstance(instance)){
                System.out.println("instance of class is:  " + step.getSimpleName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
