package com.jitaida.tiancai.leetcode.easy;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckParlindrome {
    public boolean checkParlindrome(String s){
        char[] result = new char[s.length()];
        int index = 0;
        char buffer;
        for (int i = 0; i < s.length(); i++ ){
            buffer = s.charAt(i);

            if ((buffer >= 'a' && 'z' >= buffer) || ('A' <= buffer && 'Z' >= buffer ) || ('0' <= buffer && '9' >= buffer)){
                result[index] = Character.toLowerCase(buffer);
                index++;
            }
        }

        for (int i = 0; i < index/2; i++){
            if (result[i] != result[index-1-i]){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args){
        CheckParlindrome c = new CheckParlindrome();
        System.out.println(c.checkParlindrome("0P"));
    }
}
