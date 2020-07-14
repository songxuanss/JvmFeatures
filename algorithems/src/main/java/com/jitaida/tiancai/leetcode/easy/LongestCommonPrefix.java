package com.jitaida.tiancai.leetcode.easy;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }

        char common = '!';
        int i = -1;
        int j;
        boolean shouldBreak = false;
        StringBuffer sb = new StringBuffer();
        char buffer;
        while(true){
            i++;

            for (j = 0; j < strs.length; j++){
                if (i >= strs[j].length()){
                    shouldBreak = true;
                    break;
                }

                buffer = strs[j].charAt(i);

                if (j == 0){
                    common = buffer;
                    continue;
                }

                if (buffer != common){
                    shouldBreak = true;
                    break;
                }
            }

            if (shouldBreak){
                break;
            }

            sb.append(common);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println(lcp.longestCommonPrefix(new String[]{}));
    }
}
