package leetCode.链表;

/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		ListNode l1 = new ListNode(4);
		l1.next = new ListNode(8);
		l1.next.next = new ListNode(6);
		l1.next.next.next = new ListNode(6);
		solution.printListNode(l1);
		System.out.println();
		ListNode l2 = new ListNode(9);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(7);
		solution.printListNode(l2);
		System.out.println();
		ListNode result = solution.addTwoNumbers2(l1, l2);
		solution.printListNode(result);
	}
	
	public void printListNode(ListNode node){
		while(node != null){
			System.out.print(node.val + " ");
			node = node.next;
		}
	}
	

	/**
	 * 回文链表
	请检查一个链表是否为回文链表。
	
	进阶：
	你能在 O(n) 的时间和 O(1) 的额外空间中做到吗？
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {
		ListNode tempHead = head;
		if(head == null || head.next == null){
			return true;
		}
		ListNode temp = null;
		while(head != null){
			ListNode node = new ListNode(head.val);
			node.next = temp;
			temp = node;
			head = head.next;
		}
		while(tempHead != null){
			if(tempHead.val != temp.val){
				return false;
			}
			tempHead = tempHead.next;
			temp = temp.next;
		}
		return true;
	}
	
	/**
	 * 一种好的方法，不需要额外的空间
	 * @param head
	 * @return
	 */
	public boolean isPalindromeBetter(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            
            // here we need to revese the first half of the LinkedList.
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            
            slow = next;
        }
        if (fast != null)
            slow = slow.next;

        while (slow != null && prev != null)
            if (slow.val != prev.val)
                return false;
            else {
                slow = slow.next;
                prev = prev.next;
            }
                
        return true;
    }
	
	
	/**
	 * 合并两个有序链表
	将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	
	示例：
	
	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4
	 * @param l1
	 * @param l2
	 * @return
	 */
	/*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null){
			return l2;
		}
		if(l2 == null){
			return l1;
		}
        ListNode head = l1.val < l2.val?l1:l2;  错了
        while(l2 != null){
        	ListNode next = l1.next;
        	if(next != null){
        		if(next.val >= l2.val){
        			l1.next = l2;
        			ListNode l2Next = l2.next;
        			l2.next = next;
        			l2 = l2Next;
        		} else {
        			l1 = next;
        		}
        	} else {
        		next = l2;
        		break;
        	}
        }
        
        return head;
    }*/
	
	/**
	 * 这个方法超时了！！！！！！
	 * 两数相加
		题目描述提示帮助提交记录社区讨论阅读解答
		给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
		
		你可以假设除了数字 0 之外，这两个数字都不会以零开头。
		
		示例：
		
		输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
		输出：7 -> 0 -> 8
		原因：342 + 465 = 807
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
        	return l2;
        }
        if(l2 == null){
        	return l1;
        }
        int highNum = 0;
        int first = l1.val + l2.val + highNum;
        l1 = l1.next;
        l2 = l2.next;
        ListNode result = new ListNode(first % 10);
        ListNode head = result;
        highNum = first / 10;
        while(l1 !=null && l2 != null ){
        	first = l1.val + l2.val + highNum;
        	l1 = l1.next;
            l2 = l2.next;
            result.next = new ListNode(first % 10);
            result = result.next;
            highNum = first / 10;
        }
        while(l1 != null && l2 == null){
        	first = l1.val + highNum;
        	result.next = new ListNode(first % 10);
        	result = result.next;
        	highNum = first / 10;
        	l1 = l1.next;
        }
        while(l1 == null && l2 != null){
        	first = l2.val + highNum;
        	result.next = new ListNode(first % 10);
        	result = result.next;
        	highNum = first / 10;
        	l2 = l2.next;
        }
        if(highNum != 0){
        	result.next = new ListNode(highNum);
        }
        return head;
    }
	
	//反转单向链表
	private ListNode reverseLinkedList(ListNode head){
		ListNode node = null;
		while(head != null){
			ListNode next = head.next;
			head.next = node;
			node = head;
			head = next;
		}
		return node;
	}
	
	/**
	 * 由于上面的方法超时了，现在重新来
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		if(l1 == null){
        	return l2;
        }
        if(l2 == null){
        	return l1;
        }
        int highNum = 0;
        int temp = 0;
        ListNode head = l1;
        ListNode result = l1;
        while(l1 != null && l2 != null){
        	temp = l1.val + l2.val + highNum;
        	l1.val = temp % 10;
        	highNum = temp / 10;
        	result = l1;
        	l1 = l1.next;
        	l2 = l2.next;
        }
        ListNode tempNode = l1 != null?l1:l2;
        result.next = tempNode;
        while(tempNode != null){
        	temp = tempNode.val + highNum;
        	tempNode.val = temp % 10;
        	highNum = temp / 10;
        	result = tempNode;
        	tempNode = tempNode.next;
        }
        if(highNum != 0){
        	result.next = new ListNode(highNum);
        }
        return head;
	}
	
	/**
	 * 最好的思路
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbersBest(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int carry = sum/10;
        ListNode result = new ListNode(sum%10);
        l1 = l1.next;
        l2 = l2.next;
        ListNode cache = result;
        while (l1 != null || l2 != null || carry > 0) {
            sum = carry;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            cache.next = new ListNode(sum%10);
            carry = sum/10;
            cache = cache.next;
            if (l1 !=null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }            
        }
        return result;
    }
	
}