package com.jitaida.tiancai.leetcode.easy;

import java.util.Stack;

/**
 * ������ջʵ��һ�����С����е��������£���ʵ�������������� appendTail �� deleteHead ���ֱ�����ڶ���β�������������ڶ���ͷ��ɾ�������Ĺ��ܡ�(��������û��Ԫ�أ�deleteHead?�������� -1 )
 *
 * ?
 *
 * ʾ�� 1��
 *
 * ���룺
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * �����[null,null,3,-1]
 * ʾ�� 2��
 *
 * ���룺
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * �����[null,-1,null,null,5,2]
 * ��ʾ��
 *
 * 1 <= values <= 10000
 * �����?appendTail��deleteHead ����?10000?�ε���
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
