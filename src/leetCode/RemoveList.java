package leetCode;

/**
 * 
 * @createTime 2018年4月19日 下午9:13:57
 * @author MrWang
 */
public class RemoveList {
	public static void main(String[] args) {
		ListNode list = new ListNode(1);
		ListNode head = list;
		list.next = new ListNode(2);
		list = list.next;
		list.next = new ListNode(3);
		list = list.next;
		list.next = new ListNode(4);
		list = list.next;
		list.next = new ListNode(5);
		
		printList(head);
		System.out.println();
		
		head = removeNthFromEnd(head,2);
		printList(head);
	}
	
	public static void printList(ListNode list){
		while(list != null) {
			System.out.print(list.val + " ");
			list = list.next;
		}
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode first = head;
		int length = 0;
		while(first != null){
			length++;
			first = first.next;
		}
		first = dummy;
		length -= n;
		while(length > 0){
			length--;
			first = first.next;
		}
		first.next = first.next.next;
		return dummy.next;
	}

}
