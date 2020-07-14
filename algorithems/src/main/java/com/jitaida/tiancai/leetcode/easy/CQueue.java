package com.jitaida.tiancai.leetcode.easy;

import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead?操作返回 -1 )
 *
 * ?
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对?appendTail、deleteHead 进行?10000?次调用
 */
public class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        Stack<Integer> stack;

        if (stack1.empty() && stack2.empty()){
            stack1.push(value);
            return;
        } else if (stack1.empty()){
            stack = stack2;
        } else {
            stack = stack1;
        }

        stack.push(value);
    }

    public int deleteHead() {
        Stack<Integer> oldS;
        Stack<Integer> newS;

        if (stack1.empty() && stack2.empty()){
            return -1;
        } else if (stack1.empty()){
            oldS = stack2;
            newS = stack1;
        } else {
            newS = stack2;
            oldS = stack1;
        }

        Integer tmp = null;
        while (!oldS.empty()){
            tmp = oldS.pop();
            newS.push(tmp);
        }

        int ret = newS.pop();

        while(!newS.empty()) {
            tmp = newS.pop();
            oldS.push(tmp);
        }

        return ret;
    }

    public static void main(String[] args){
        CQueue c = new CQueue();
        c.appendTail(10);
        c.appendTail(12);
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
        System.out.println(c.deleteHead());
    }

}
