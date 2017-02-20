package com.joker.tecsteps.javatecsteps.reflectsteps;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liwenle on 17-2-20.
 */
public class ReflectStepTest {
    @Test
    public  void testReflectStep(){
        try{
            Class step = Class.forName("com.joker.tecsteps.javatecsteps.reflectsteps.ReflectStep");
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
