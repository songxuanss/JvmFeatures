package com.jitaida.tiancai.leetcode.midium;

import java.util.List;

/**
 * ��������?�ǿ� ������������ʾ�����Ǹ������������У����Ǹ��Ե�λ���ǰ���?����?�ķ�ʽ�洢�ģ��������ǵ�ÿ���ڵ�ֻ�ܴ洢?һλ?���֡�
 *
 * ��������ǽ��������������������᷵��һ���µ���������ʾ���ǵĺ͡�
 *
 * �����Լ���������� 0 ֮�⣬���������������� 0?��ͷ��
 *
 * ʾ����
 *
 * ���룺(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * �����7 -> 0 -> 8
 * ԭ��342 + 465 = 807
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/add-two-numbers
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class AddTwoNumbers {
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, false);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, boolean hasExtra){
        if (l1 == null && l2 == null){
            if (hasExtra){
                return new ListNode(1);
            }

            return null;
        }

        ListNode retval;
        boolean shouldHasExtra = false;

        if (l1 == null) {
            l1 = new ListNode(0);
        }

        if ( l2 == null){
            l2 = new ListNode(0);
        }

        int val = l1.val + l2.val + ((hasExtra)? 1: 0);
        if ( val >= 10){
            val = val % 10;
            shouldHasExtra = true;
        }

        retval = new ListNode(val );
        if (shouldHasExtra || (l1.next != null || l2.next != null)){
            retval.next = addTwoNumbers(l1.next, l2.next, shouldHasExtra);
        }

        return retval;
    }

    public void run(){

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(9);
        ListNode a3 = new ListNode(9);

        a2.next = a3;

        ListNode ret = this.addTwoNumbers(a1, a2);
        while (true){
            System.out.println(ret.val + " ");
            ret = ret.next;
        }
    }

    public static void main(String[] args){
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        addTwoNumbers.run();
    }
}
