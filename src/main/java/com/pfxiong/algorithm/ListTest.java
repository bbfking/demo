package com.pfxiong.algorithm;

import org.junit.Test;

/**
 * @author: pfXiong
 * @datetime: 2021/1/23 10:09
 * @description:
 */
public class ListTest {
    class ListNode {
        private ListNode nextNode;
        private ListNode preNode;
        private Integer value;

        public ListNode(ListNode nextNode, ListNode preNode, Integer value) {
            this.nextNode = nextNode;
            this.preNode = preNode;
            this.value = value;
        }
    }

    class ExamList {
        private ListNode first;
        private ListNode last;
        private int size;
    }

    /**
     * 反正链表
     */
    @Test
    public void reverseList() {
        ListNode listNode = firstList(10);
        ListNode preNode = null;
        ListNode reverseNodeHead = null;
        while (listNode != null) {
            ListNode nextNode = listNode.nextNode;
            if (nextNode == null) {
                reverseNodeHead = listNode;
            }
            listNode.nextNode = preNode;
            preNode = listNode;
            listNode = nextNode;
        }
        printList(reverseNodeHead);
    }

    private void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.nextNode;
        }
    }

    @Test
    public void mergeList() {
        ListNode listNode1 = firstList(10);
        ListNode listNode2 = firstList(10);
        printList(merge(listNode1, listNode2));
    }

    private ListNode merge(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        } else if (p2 == null) {
            return p1;
        }
        ListNode head = null;
        if (p1.value > p2.value) {
            head = p2;
            head.nextNode = merge(p1, p2.nextNode);
        } else {
            head = p1;
            head.nextNode = merge(p1.nextNode, p2);
        }
        return head;
    }
    /**
     * 倒数第K个结点
     */
    @Test
    public void findLastInList() {
        ExamList list = initList(20);
        int k = 5;
        if (k > list.size || k <= 0) {
            System.out.println("");
        }

        int order = list.size - k;
        ListNode cur = list.first;
        int index = 0;
        while (index < list.size) {
            if (index == order) {
                System.out.println(cur.value);
            }
            cur = cur.nextNode;
            index++;
        }

    }

    private ExamList initList(int size) {
        ExamList l = new ExamList();
        for (int i = 0; i < size; i++) {
            ListNode node = new ListNode(null, l.last, i);
            l.size = l.size + 1;
            if (l.first == null) {
                l.first = node;
            }
            if (l.last != null) {
                l.last.nextNode = node;
            }
            l.last = node;

        }
        return l;
    }

    /**
     * 单链表
     *
     * @return
     */
    private ListNode firstList(int size) {
        ListNode first = null;
        ListNode preNode = null;
        for (int i = 0; i < size; i++) {
            ListNode cur = new ListNode(null, null, i);
            if (first == null) {
                first = cur;
            }
            if (preNode != null) {
                preNode.nextNode = cur;
            }
            preNode = cur;
        }
        return first;
    }


}