package com.galvanize;


import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Algorithm {

    public boolean allEqual(String s) {
        if(s.equals("")){
            return false;
        }
        char[] chars = s.toLowerCase().toCharArray();
        char existingC = chars[0];
        for(char c : chars){
            if (c != existingC){
                return false;
            }
        }
        return true;
    }

    public Map<String, Long> letterCount(String s) {
        Map<String, Long> retMap = new HashMap<String, Long>();
        if(s.equals("")){
            return retMap;
        }
        char[] chars = s.toLowerCase().toCharArray();
        for(char c : chars){
            Long val = retMap.putIfAbsent(Character.toString(c), 1L);
            if(val != null){
                retMap.put(Character.toString(c), 1L + val);
            }
        }
        return retMap;
    }

    public String interleave(List<String> list1, List<String> list2) {
        String retStr = "";
        int length = list1.size();
        if(length == 0){
            return retStr;
        }
        for(int i = 0; i < length; i++){
            retStr += list1.get(i);
            retStr += list2.get(i);
        }
        return retStr;
    }
}
