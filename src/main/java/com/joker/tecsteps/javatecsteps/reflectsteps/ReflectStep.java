package com.joker.tecsteps.javatecsteps.reflectsteps;

/**
 * Created by liwenle on 17-2-20.
 */
public class ReflectStep implements IReflectInterface{
    private String tag;
    private String protectedTag;
    public ReflectStep(){

    }

    public ReflectStep(String tag){
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private void printTag(){
        System.out.println("ReflectStep tag is : " + tag);
    }

    private void testProtectMethod(){
        System.out.println("testProtectMethod: " + protectedTag);
    }

    public void interfaceMethod() {
        System.out.println("interfaceMethod");
    }
}
