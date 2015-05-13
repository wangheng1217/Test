package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RecoverBinarySearchTreeSolution {
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        new RecoverBinarySearchTreeSolution().recoverTree(root);
        System.out.println(root.val);
        System.out.println(root.left.val);
    }
    
    public void recoverTree2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        TreeNode preNode = null;
        TreeNode firstNode = null;
        TreeNode secondNode = null;
        
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            
            if (preNode != null) {
                if (preNode.val > node.val) {
                    if (firstNode == null) {
                        firstNode = preNode;
                    }
                    
                    secondNode = node;
                }
            }
            preNode = node;
            
            node = node.right;
        }
        
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }
    
    public void recoverTree(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            
            list.add(node.val);
            
            node = node.right;
        }
        
        List<Integer> sortedList = new ArrayList<Integer>(list);
        Collections.sort(sortedList);
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).intValue() != sortedList.get(i).intValue()) {
                map.put(list.get(i), sortedList.get(i));
            }
        }
        
        node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            
            if (map.containsKey(node.val)) {
                node.val = map.get(node.val);
            }
            
            node = node.right;
        }
    }

    // not working yet
    public void recoverTreeNotWorking(TreeNode root) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<TreeNode>();
        TreeNode node = root;
        TreeNode node1 = null, node2 = null, node3 = null, node4 = null;
        TreeNode errorNode1 = null, errorNode2 = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                node = stack.pop();

                if (node1 == null) {
                    node1 = node;
                } else {
                    if (node2 == null) {
                        node2 = node;
                        if (node.val <= node1.val) {
                            if (errorNode1 == null) {
                                errorNode1 = node1;
                                errorNode2 = node2;
                            } else {
                                errorNode2 = node2;
                            }

                            node1 = node2;
                            node2 = null;
                        }
                    } else {
                        if (node3 == null) {
                            node3 = node;

                            if (node3.val <= node2.val) {
                                if (node3.val <= node1.val) {
                                    if (errorNode1 == null) {
                                        errorNode1 = node3;
                                    } else {
                                        errorNode2 = node3;
                                    }

                                    node1 = node2;
                                    node2 = node3;
                                    node3 = null;
                                } else {
                                    if (node3.right == null && stack.isEmpty()) {
                                        errorNode1 = node2;
                                        errorNode2 = node3;
                                    }
                                }
                            } else {
                                node1 = node2;
                                node2 = node3;
                                node3 = null;
                            }

                        } else {
                            node4 = node;

                            if (node4.val > node2.val) {
                                errorNode1 = node2;
                                errorNode2 = node3;
                            } else {
                                if (errorNode1 == null) {
                                    errorNode1 = node2;
                                } else {
                                    errorNode2 = node2;
                                }
                            }

                            node1 = node3;
                            node2 = node4;
                            node3 = null;
                            node4 = null;
                        }
                    }

                }

                node = node.right;
            }
        }

        if (errorNode1 != null && errorNode2 != null) {
            int temp = errorNode1.val;
            errorNode1.val = errorNode2.val;
            errorNode2.val = temp;
        }
    }

}
