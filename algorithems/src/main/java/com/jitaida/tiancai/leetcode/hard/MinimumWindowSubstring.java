package com.jitaida.tiancai.leetcode.hard;

import java.util.*;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        /**
         * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
         *
         * Example:
         *
         * Input: S = "ADOBECODEBANC", T = "ABC"
         * Output: "BANC"
         * Note:
         *
         * If there is no such window in S that covers all characters in T, return the empty string "".
         * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
         */

        Map<Character, Integer> result = new HashMap<>();
        int value;
        for (int i = 0; i < t.length(); i ++) {
            result.put( t.charAt(i), result.getOrDefault(t.charAt(i), 0) + 1);
        }


        int best = Integer.MAX_VALUE;
        int resultLeft = -1;
        int resultRight = -1;
        Map<Character, Integer> buffer = new HashMap();
        int startPtr = 0;
        int endPtr = -1;
        while (endPtr < s.length()) {
            endPtr++;
            if (endPtr < s.length() && result.containsKey(s.charAt(endPtr))) {
                buffer.put(s.charAt(endPtr), buffer.getOrDefault(s.charAt(endPtr), 0) + 1);
            }

            while(startPtr <= endPtr && check(result, buffer)){

                // check if we already have better result, meaning best smaller
                if (best > endPtr - startPtr + 1) {
                    best = endPtr - startPtr + 1;
                    resultLeft = startPtr;
                    resultRight = endPtr;
                }

                if (startPtr<endPtr && result.containsKey(s.charAt(startPtr))){
                    buffer.put(s.charAt(startPtr), buffer.getOrDefault(s.charAt(startPtr), 1) - 1);
                }

                startPtr++;

            }
        }
        return (resultRight == -1 )? "": s.substring(resultLeft, resultRight+1);
    }

    private boolean check(Map<Character, Integer> ori, Map<Character, Integer> buffer){
        for (Map.Entry<Character, Integer> entry: ori.entrySet()){
            if (buffer.getOrDefault(entry.getKey(), 0) < entry.getValue()){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
    }
}
