package leetCode.设计问题;

/**
 * 最小栈
	设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
	
	push(x) -- 将元素x推入栈中。
	pop() -- 删除栈顶的元素。
	top() -- 获取栈顶元素。
	getMin() -- 检索栈中的最小元素。
	示例:
	
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> 返回 -3.
	minStack.pop();
	minStack.top();      --> 返回 0.
	minStack.getMin();   --> 返回 -2.
 * 
 * @createTime 2018年4月29日 下午2:34:03
 * @author MrWang
 */
public class MinStack {
	
	private class Node{
		int element;
		Node next;
		public Node(int element){
			this.element = element;
		}
	}

	private Node head;
	private int min = Integer.MIN_VALUE;
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
    	if(isEmpty()) {
    		min = x;
    	} else {
    		if(x < min){
    			min = x;
    		}
    	}
    	Node node = new Node(x);
    	node.next = head;
    	head = node;
    }
    
    public void pop() {
        if(!isEmpty()){
        	Node next = head.next;
        	head.next = null;
        	//更新最小元素
        	if(head.element == min){
        		min = findMin(next);
        	}
        	head = next;
        }
    }
    
    public int top() {
        if(!isEmpty()){
        	return head.element;
        }
        return -1;
    }
    
    public int getMin() {
        return min;
    }
    
    public boolean isEmpty(){
    	return head == null;
    }
    
    public int findMin(Node node){
    	if(node != null){
        	int min = node.element;
        	Node next = node.next;
        	while(next != null){
        		if(next.element < min){
        			min = next.element;
        		}
        		next = next.next;
        	}
        	return min;
        }
        return Integer.MIN_VALUE;
    }
    
    public static void main(String[] args) {
    	
//    	 Your MinStack object will be instantiated and called as such:
    	 /*MinStack obj = new MinStack();
    	 obj.push(x);
    	 obj.pop();
    	 int param_3 = obj.top();
    	 int param_4 = obj.getMin();*/
    	 
    	MinStack minStack = new MinStack();
    	minStack.push(-2);
    	minStack.push(0);
    	minStack.push(-3);
    	int min = minStack.getMin();   //--> 返回 -3.
    	System.out.println(min);
    	minStack.pop();
    	int top = minStack.top();      //--> 返回 0.
    	System.out.println(top);
    	int min2 = minStack.getMin();   //--> 返回 -2.
    	System.out.println(min2);
	}
}