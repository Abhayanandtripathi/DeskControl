package com.example.RemotePeriControlServer;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private static Map<Character,Integer> romanMap = new HashMap<>();
    static {
        romanMap.put('I',1);
        romanMap.put('V',5);
        romanMap.put('X',10);
        romanMap.put('L',50);
        romanMap.put('C',100);
        romanMap.put('D',500);
        romanMap.put('M',1000);
    }

    public int romanToInt(String s) {
        if(s ==null || s.length() == 0) return 0;


        if(s.length() == 1) return romanMap.get(s.charAt(0));

        int number = 0;

        for(int i=0; i<s.length(); i++){
            int current = romanMap.get(s.charAt(i));
            int next = i<s.length()-1? romanMap.get(s.charAt(i+1)): 0;
            if(current < next){
                number += (next-current);
                i++;
            }
            else number += current;

        }

        return number;

    }
}