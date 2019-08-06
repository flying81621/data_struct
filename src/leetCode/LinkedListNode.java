package leetCode;

/**
 * 
 * @createTime 2018年4月22日 下午11:49:29
 * @author MrWang
 */
public class LinkedListNode {

	/**
	 * Definition for singly-linked list.
	 */
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseList(ListNode head) {
		ListNode tail = null;
		while(head != null) {
			ListNode next = head.next;
			head.next = tail;
			tail = head;
			head = next;
		}
		return tail;
	}

}
