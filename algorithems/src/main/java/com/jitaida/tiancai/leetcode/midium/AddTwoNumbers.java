package com.jitaida.tiancai.leetcode.midium;

import java.util.List;

/**
 * 给出两个?非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照?逆序?的方式存储的，并且它们的每个节点只能存储?一位?数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0?开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
