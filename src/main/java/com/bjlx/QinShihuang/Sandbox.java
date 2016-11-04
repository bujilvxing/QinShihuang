package com.bjlx.QinShihuang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于测试
 * Created by xiaozhi on 2016/10/21.
 */
public class Sandbox {
	static List<Map<String, String>> listA = new ArrayList<Map<String, String>>();
	static List<Map<String, String>> listB = new ArrayList<Map<String, String>>();
	static void f() {
		for(int i = 0; i < 100000; i++) {
			Map<String, String> itemA = new HashMap<String, String>();
			itemA.put("key-1-" + i, "value-1-" + i);
			listA.add(itemA);
			Map<String, String> itemB = new HashMap<String, String>();
			itemB.put("key-2-" + i, "value-2-" + i);
			listB.add(itemB);
		}
	}
    public static void main(String[] args) {
    	f();
    	List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
    	List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
    	long t1 = System.currentTimeMillis();
    	for(Map map:listA){
    		list1.add(map);
    	}
    	for(Map map:listB){
    		list2.add(map);
    	}
    	long i = 0;
    	for(Map map:list1){
    		for(long j = 0; j < 10000; j++){
    			i++;
    			i--;
    		}
    	}
    	i = 0;
    	for(Map map:list2){
    		for(long j = 0; j < 10000; j++){
    			i++;
    			i--;
    		}
    	}
    	long t2 = System.currentTimeMillis();
    	System.out.println("t2 - t1 = " + (t2-t1));
    	long t3 = System.currentTimeMillis();
    	i = 0;
    	for(Map map:listA){
    		for(long j = 0; j < 10000; j++){
    			i++;
    			i--;
    		}
    	}
    	i = 0;
    	for(Map map:listB){
    		for(long j = 0; j < 10000; j++){
    			i++;
    			i--;
    		}
    	}
    	long t4 = System.currentTimeMillis();
    	System.out.println("t4 - t3 = " + (t4-t3));
    	
    }
}
