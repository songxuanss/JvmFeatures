package com.jitaida.tiancai.leetcode.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MinimumAddToMakeValid {
    /**
     * 使括号有效的最少添加
     * <p>
     * 给定一个由?'('?和?')'?括号组成的字符串 S，我们需要添加最少的括号（ '('?或是?')'，可以在任何位置），以使得到的括号字符串有效。
     * <p>
     * 从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
     * <p>
     * 它是一个空字符串，或者
     * 它可以被写成?AB?（A?与?B?连接）, 其中?A 和?B?都是有效字符串，或者
     * 它可以被写作?(A)，其中?A?是有效字符串。
     * 给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
     * <p>
     * ?
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 示例 1：
     * <p>
     * 输入："())"
     * 输出：1
     * 示例 2：
     * <p>
     * 输入："((("
     * 输出：3
     * 示例 3：
     * <p>
     * 输入："()"
     * 输出：0
     * 示例 4：
     * <p>
     * 输入："()))((" "))((()))("
     * 输出：4
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
     * 使用栈来解决问题，有点意思
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
     * 官方解法
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
