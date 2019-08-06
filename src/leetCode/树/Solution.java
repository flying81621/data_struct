package leetCode.树;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Solution {

	public static void main(String[] args) {
		TreeNode node = new TreeNode(10);
		node.left = new TreeNode(5);
		node.right = new TreeNode(15);
		node.right.left = new TreeNode(6);
		node.right.right = new TreeNode(20);
		System.out.println(new Solution().isValidBSTBetter(node));
	}

	public int maxDepth(TreeNode root) {
		if(root == null){
			return 0;
		}
		return maxDepgh(root, 1);
	}

	/**
	 * @param root
	 * @param i
	 * @return
	 */
	private int maxDepgh(TreeNode root, int i) {
		if(root == null){
			return i;
		}
		return Math.max(maxDepgh(root.left,i + 1), maxDepgh(root.right, i + 1));
	}
	
	/**
	 * 这又是一种不用额外定义方法的方法
	 * @param root
	 * @return
	 */
	public int maxDepthBetter(TreeNode root) {
		if(root == null){
			return 0;
		}
		return Math.max(maxDepthBetter(root.left), maxDepthBetter(root.left)) + 1;
	}
	
	public boolean isValidBSTBetter(TreeNode root) {
		if(root == null){
			return true;
		}
		boolean left = isValidBst(root, root.left, true);
		boolean right = isValidBst(root, root.right, false);
		if(left && right){
			left = isValidBSTBetter(root.left);
			right = isValidBSTBetter(root.right);
		}
		return left && right;
	}
	
	
	/**
	 * @param root
	 * @param left
	 * @param i
	 * @return
	 */
	private boolean isValidBst(TreeNode root, TreeNode target, boolean i) {
		if(root == null || target == null){
			return true;
		}
		if(i){
			if(root.val <= target.val){
				return false;
			}
		} else {
			if(root.val >= target.val){
				return false;
			}
		}
		boolean left = isValidBst(root, target.left, i);
		boolean right = isValidBst(root, target.right, i);
		return left && right;
	}

	/**
	 *----------》》》》》》》》》》这个方法在LeetCode上超时了
	 * 验证二叉搜索树
		给定一个二叉树，判断其是否是一个有效的二叉搜索树。
		
		一个二叉搜索树具有如下特征：
		
		节点的左子树只包含小于当前节点的数。
		节点的右子树只包含大于当前节点的数。
		所有左子树和右子树自身必须也是二叉搜索树。
		示例 1:
		
		输入:
		    2
		   / \
		  1   3
		输出: true
		示例 2:
		
		输入:
		    5
		   / \
		  1   4
		     / \
		    3   6
		输出: false
		解释: 输入为: [5,1,4,null,null,3,6]。
		     根节点的值为 5 ，但是其右子节点值为 4 
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
        if(root == null){
        	return true;
        }
        boolean left = isValidBST(root, root.left, 0);
        boolean right = isValidBST(root, root.right, 1);
        return left && right;
	}

	/**
	 * @param root
	 * @param target
	 * @param i 0:代表root应该比target及其子元素都要大    1：代表root应该比target及其子元素都要小
	 * @return
	 */
	private boolean isValidBST(TreeNode root, TreeNode target, int i) {
		if(root == null || target == null){
			return true;
		}
		boolean left = true;
		boolean right = true;
		if(i == 0){
			if(root.val <= target.val){
				return false;
			}
			if(left){
				left = isValidBST(root, target.left, 0);
			}
			if(left){
				left = isValidBST(root, target.right, 0);
			}
		} else {
			if(root.val >= target.val){
				return false;
			}
			if(left){
				left = isValidBST(root, target.left, 1);
			}
			if(left){
				left = isValidBST(root, target.right, 1);
			}
		}
		if(left && right){
			if(root.left != null){
				left = isValidBST(root.left, root.left.left, 0) && isValidBST(root.left, root.left.right, 1);
			}
			if(root.right != null){
				right = isValidBST(root.right, root.right.left, 0) && isValidBST(root.right, root.right.right, 1);
			}
		}
		return left && right;
	}

}