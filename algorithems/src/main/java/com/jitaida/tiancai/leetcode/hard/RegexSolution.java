package com.jitaida.tiancai.leetcode.hard;

import java.util.Map;
import java.util.TreeMap;

/**
 * ����һ���ַ���?s?��һ���ַ�����?p��������ʵ��һ��֧�� '.'?��?'*'?��������ʽƥ�䡣
 * <p>
 * '.' ƥ�����ⵥ���ַ�
 * '*' ƥ���������ǰ�����һ��Ԫ��
 * ��νƥ�䣬��Ҫ����?����?�ַ���?s�ģ������ǲ����ַ�����
 * <p>
 * ˵��:
 * <p>
 * s?����Ϊ�գ���ֻ������?a-z?��Сд��ĸ��
 * p?����Ϊ�գ���ֻ������?a-z?��Сд��ĸ���Լ��ַ�?.?��?*��
 * ʾ�� 1:
 * <p>
 * ����:
 * s = "aa"
 * p = "a"
 * ���: false
 * ����: "a" �޷�ƥ�� "aa" �����ַ�����
 * ʾ�� 2:
 * <p>
 * ����:
 * s = "aa"
 * p = "a*"
 * ���: true
 * ����:?��Ϊ '*' �������ƥ���������ǰ�����һ��Ԫ��, ������ǰ���Ԫ�ؾ��� 'a'����ˣ��ַ��� "aa" �ɱ���Ϊ 'a' �ظ���һ�Ρ�
 * ʾ��?3:
 * <p>
 * ����:
 * s = "ab"
 * p = ".*"
 * ���: true
 * ����:?".*" ��ʾ��ƥ�����������'*'�������ַ���'.'����
 * ʾ�� 4:
 * <p>
 * ����:
 * s = "aab"
 * p = "c*a*b"
 * ���: true
 * ����:?��Ϊ '*' ��ʾ������������� 'c' Ϊ 0 ��, 'a' ���ظ�һ�Ρ���˿���ƥ���ַ��� "aab"��
 * ʾ�� 5:
 * <p>
 * ����:
 * s = "mississippi"
 * p = "mis*is*p*."
 * ���: false
 * <p>
 * ���ӣ�https://leetcode-cn.com/problems/regular-expression-matching
 */
public class RegexSolution {
    class Reg {
        // character should be a-z and .
        char character;
        // type should be 1 or *
        char type;

        Reg next;

        Reg(char character, char type) {
            this.character = character;
            this.type = type;
        }

        boolean isAnyAny(){
            return character == '.' && type == '*';
        }

        boolean isAny(){
            return type == '*';
        }
    }

    public boolean isMatch(String s, String p) {
        int regIndex = -1;
        int strIndex = 0;
        boolean isStarted = false;
        Reg reg = null;

        while (strIndex < s.length()) {
            if (!isStarted && reg == null) {
                reg = getNextReg(p, regIndex, new Reg('x','1'));
                regIndex++;
                isStarted = true;
            }

            if (reg == null){
                return false;
            }

            if (isMatch(s.charAt(strIndex), reg)){
                if (!reg.isAny()){
                    reg = getNextReg(p, regIndex, reg);
                    regIndex++;
                }
            } else {
                return false;
            }
            strIndex++;
        }

        return true;
    }

    private Reg getNextReg(String reg, int regIndex, Reg curReg) {
        int nextIndex = -1;
        switch (curReg.type) {
            case '*':
                nextIndex = regIndex + 2;
            case '1':
                nextIndex = regIndex + 1;
        }

        if (nextIndex == -1 || nextIndex >= reg.length()){
            return null;
        }

        Reg retval;

        if (nextIndex + 1 < reg.length() && reg.charAt(nextIndex + 1) == '*'){
            retval = new Reg(reg.charAt(nextIndex), '*');
        } else {
            retval = new Reg(reg.charAt(nextIndex), '1');
        }

        if (curReg.isAnyAny() && retval.isAnyAny()){
            return getNextReg(reg, nextIndex, retval);
        }

        if (retval.isAnyAny()){
            retval.next = getNextReg(reg, nextIndex, retval);
        }

        return retval;
    }

    private boolean isMatch(Character c, Reg reg){
        if (reg.isAnyAny() && isMatch(c, reg.next)){
            return false;
        } else if (reg.isAnyAny() && !isMatch(c, reg.next)){
            return true;
        }

        if(c == reg.character){
            return true;
        }

        if (reg.character == '.'){
            return true;
        }

        return false;
    }

    public static void main(String[] args){
        RegexSolution reg = new RegexSolution();

        System.out.println(reg.isMatch("mississippi","mis*is*p*."));
    }
}
