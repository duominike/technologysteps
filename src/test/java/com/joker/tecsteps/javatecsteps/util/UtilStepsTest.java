package com.joker.tecsteps.javatecsteps.util;

import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liwenle on 17-3-8.
 */
public class UtilStepsTest {
    @Test
    public void testCalcFileMD5(){
        String path = "/home/liwenle/bin/vvlive1489559531886.jpg";
        File file = new File(path);
        String md5 = FileMd5Util.getMD5(file);
        System.out.println("file md5 : " + md5);
    }
    @Test
    public void testSubString(){
//        String values = "[111, 222, 33]";
//        String[] userIds = values.substring(1, values.length() -1).split(",");
//        for(String userId : userIds){
//            System.out.println("userId: " + userId);
//        }
        Set<Long> setCannotViews = new HashSet<Long>();
        setCannotViews.add(111L);
        setCannotViews.add(222L);
        setCannotViews.add(333L);
        Set<Long> setCanViews = new HashSet<Long>();
        setCanViews.add(444L);
        setCanViews.add(555L);
        setCanViews.add(666L);
        Set<Long> setCanViewsLocal = new HashSet<Long>();
        setCanViewsLocal.add(222L);
        setCanViewsLocal.add(444L);
        setCanViewsLocal.add(555L);
        setCanViewsLocal.add(666L);
        setCanViewsLocal.addAll(setCannotViews);
        setCanViewsLocal.removeAll(setCanViews);
        System.out.print(setCanViewsLocal);
    }
}
