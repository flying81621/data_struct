//package 表栈队列;
//
///**
// * 这个是面试题中的链表反转题，凌云科技，补全链表反转的代码片段
// * @createTime 2018年2月11日 下午5:29:46
// * @author MrWang
// */
//public class ReverseLinkedList {
//	private static final int NODE_COUNT = 10;
//
//	private static class Node<Anytype>{
//		private Anytype data;
//		private Node<Anytype> next;
//
//		private Node(Anytype data, Node<Anytype> next){
//			this.data = data;
//			this.next = next;
//		}
//	}
//
//	/**
//	 * 创建一个给定数量的链表
//	 * @param count
//	 */
//	@SuppressWarnings("unchecked")
//	private static <Anytype> Node<Anytype> createLinkedList(int count, Anytype anytype){
//		Node<Anytype> linkedList = null;
//		for(int i = 0; i < count; i++){
//			//制造一个新节点，把原来的节点用构造方法放入新节点的next(尾部)，然后把原来的节点引用指向新节点，
//			//实际上是在往头部添加
//			if(anytype instanceof Integer){
//				//之所以有这个判断，是为了进行方便的测试，添加一些数字进去，好看反转的效果
//				linkedList = new Node<>((Anytype)new Integer(((int)anytype + i)), linkedList);
//				continue;
//			}
//			linkedList = new Node<>(anytype, linkedList);
//		}
//		return linkedList;
//	}
//
//	/**
//	 * 打印链表
//	 * @param linkedList
//	 */
//	private static <Anytype> void printLinkedList(Node<Anytype> linkedList){
//		while (linkedList != null) {
//			System.out.print(linkedList.data + ",");
//			linkedList = linkedList.next;
//		}
//		System.out.println();
//	}
//
//	/**
//	 * 用四行代码,只使用一次的链表的遍历，反转单向链表
//	 * 我的反转思路：
//	 * 原来的添加方式是往头部添加，所以第一个添加的一定是在最后，而现在我只要正向遍历的时候，再把这个往头部添加的过程在模拟一遍，就实际上
//	 * 是把链表给反转了
//	 * 步骤：
//	 * 1：判断当前链表不为空的时候，可以进行反转的操作（那个while循环）；
//	 * 2：定义一个链表的空引用reversedLinkedList，模拟的是链表添加的时候，最后一个空节点；
//	 * 3：将当前链表的下一个元素next取出来，然后将当前链表的节点linkedList指向reversedLinkedList，即模拟添加的时候往头部添加，此指向改变后，实质上当前节点和原链表已经断开；
//	 * 4：然后将reversedLinkedList的指针指向当前节点linkedList，这样实现了指针的后移，使得reversedLinkedList始终指向反转后的链表的最后一个节点；
//	 * 5：将当前节点指针linkedList指向之前取出来的next节点，于是这样一个过程完成之后，实现了将原链表的头部取出来，添加到新引用的头部；
//	 * 6：这样的循环完成之后，达到效果原链表的头部一个一个取出来，一个一个作为头部添加到reversedLinkedList的头部，于是先添加的实际上变成了尾部，完成反转；
//	 * @param linkedList
//	 */
//	private static <Anytype> Node<Anytype> reverseLinkedList(Node<Anytype> linkedList){
//		Node<Anytype> reversedLinkedList = null;
//		//while里的循环，用四行代码搞定链表的反转
//		while(linkedList != null){
//			Node<Anytype> next = linkedList.next;
//			linkedList.next = reversedLinkedList;
//			reversedLinkedList = linkedList;
//			linkedList = next;
//		}
//		return reversedLinkedList;
//	}
//
//	public static void main(String[] args) {
//		Node<Integer> linkedList = createLinkedList(NODE_COUNT, 1);
//		System.out.println("反转前");
//		printLinkedList(linkedList);
//		System.out.println("反转后");
//		Node<Integer> reverseLinkedList = reverseLinkedList(linkedList);
//		printLinkedList(reverseLinkedList);
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//}
