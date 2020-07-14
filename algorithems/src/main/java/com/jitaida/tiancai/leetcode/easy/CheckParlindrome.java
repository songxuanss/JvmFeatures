package com.jitaida.tiancai.leetcode.easy;

/**
 * ����һ���ַ�������֤���Ƿ��ǻ��Ĵ���ֻ������ĸ�������ַ������Ժ�����ĸ�Ĵ�Сд��
 *
 * ˵���������У����ǽ����ַ�������Ϊ��Ч�Ļ��Ĵ���
 *
 * ʾ�� 1:
 *
 * ����: "A man, a plan, a canal: Panama"
 * ���: true
 * ʾ�� 2:
 *
 * ����: "race a car"
 * ���: false
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/valid-palindrome
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
