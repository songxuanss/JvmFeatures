package com.jitaida.tiancai.leetcode.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MinimumAddToMakeValid {
    /**
     * ʹ������Ч���������
     * <p>
     * ����һ����?'('?��?')'?������ɵ��ַ��� S��������Ҫ������ٵ����ţ� '('?����?')'���������κ�λ�ã�����ʹ�õ��������ַ�����Ч��
     * <p>
     * ����ʽ�Ͻ���ֻ���������漸��֮һ�������ַ���������Ч�ģ�
     * <p>
     * ����һ�����ַ���������
     * �����Ա�д��?AB?��A?��?B?���ӣ�, ����?A ��?B?������Ч�ַ���������
     * �����Ա�д��?(A)������?A?����Ч�ַ�����
     * ����һ�������ַ���������Ϊʹ����ַ�����Ч��������ӵ�������������
     * <p>
     * ?
     * <p>
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     * <p>
     * ʾ�� 1��
     * <p>
     * ���룺"())"
     * �����1
     * ʾ�� 2��
     * <p>
     * ���룺"((("
     * �����3
     * ʾ�� 3��
     * <p>
     * ���룺"()"
     * �����0
     * ʾ�� 4��
     * <p>
     * ���룺"()))((" "))((()))("
     * �����4
     * <p>
     * ��Դ�����ۣ�LeetCode��
     * ���ӣ�https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
     */


    private boolean isMatch(String S, int ptr, Set<Integer> ignore) {
        int prePtr;
        for (prePtr = ptr - 1; prePtr >= -1; prePtr--) {
            if (!ignore.contains(prePtr)) {
                break;
            }
        }

        if (prePtr == -1) {
            return false;
        }

        if (S.charAt(ptr) == ')' && S.charAt(prePtr) == '(') {
            ignore.add(ptr);
            ignore.add(prePtr);
            return true;
        }

        return false;
    }

    public int minAddToMakeValid(String S) {
        int numPairs = 0;
        int iPtr = 0;
        boolean pre = false;
        Set<Integer> igonre = new HashSet<>();

        for (iPtr = 0; iPtr < S.length(); iPtr++) {
            if (pre && isMatch(S, iPtr, igonre)) {
                numPairs++;
            } else {
                pre = false;
            }

            if (S.charAt(iPtr) == '(') {
                pre = true;
            }
        }

        return S.length() - numPairs * 2;
    }

    /**
     * ʹ��ջ��������⣬�е���˼
     *
     * @param S
     * @return
     */
    public int minAddToMakeValid3(String S) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : S.toCharArray()) {

            if (!stack.isEmpty() && c == ')' && stack.getLast() == '(')
                stack.removeLast();
            else stack.addLast(c);


        }
        return stack.size();

    }

    /**
     * �ٷ��ⷨ
     *
     * @param
     */

    public int minAddToMakeValid4(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); i++) {
            bal += S.charAt(i) == '(' ? 1 : -1;
            if (bal == -1) {
                ans++;
                bal++;
            }
        }

        return ans + bal;
    }

    public static void main(String[] args) {
        MinimumAddToMakeValid matm = new MinimumAddToMakeValid();
        System.out.println(matm.minAddToMakeValid4("))(((())(("));
    }
}
