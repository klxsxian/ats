package com.lk.model.interview.tree;

/**
 * java写二叉树算法，实现添加数据形成二叉树功能，并以先序的方式打印出来
 * @author Administrator
 *
 */
public class JavaTree {
	 private MyTree tree;

	    /**
	     *二叉树的插入，参数为（关键字，数据）
	     * 
	     **/
	    public void insert(int key, int data) {
	        if (tree == null) {
	            tree = new MyTree();
	            tree.key = key;
	            tree.data = data;
	        } else {
	            MyTree newTree = new MyTree();
	            newTree.key = key;
	            newTree.data = data;
	            MyTree parent = tree;
	            while (true) {
	                if (newTree.key < parent.key) {
	                    if (parent.leftChild == null) {
	                        parent.leftChild = newTree;
	                        return;
	                    } else {
	                        parent = parent.leftChild;
	                    }
	                } else if (newTree.key > parent.key) {
	                    if (parent.rightChild == null) {
	                        parent.rightChild = newTree;
	                        return;
	                    } else {
	                        parent = parent.rightChild;
	                    }
	                }
	            }

	        }
	    }

	    /**
	     * 二叉树的查找，参数为（关键字），返回值为 myTree的一个实例
	     * 
	     * **/
	    public MyTree find(int key) {
	        if (tree == null)
	            return null;
	        MyTree curr = new MyTree();
	        curr.key = key;
	        MyTree parent = tree;
	        while (true) {
	            if (parent == null) {
	                return null;
	            } else if (curr.key == parent.key) {
	                return parent;
	            } else if (curr.key > parent.key) {
	                parent = parent.rightChild;
	            } else if (curr.key < parent.key) {
	                parent = parent.leftChild;
	            }
	        }
	    }

	    /*
	     * 
	     * 递归的二叉树中序遍历
	     */
	    private static void midOrder(MyTree tree) {
	        if (tree != null) {
	            midOrder(tree.leftChild);
	            System.out.println(" " + tree.key + "," + tree.data);
	            midOrder(tree.rightChild);
	        }
	    }

	    /*
	     * 前序遍历
	     */
	    private static void frontOrder(MyTree tree) {
	        if (tree != null) {
	            System.out.println("" + tree.key + "   ,   " + tree.data);
	            frontOrder(tree.leftChild);
	            frontOrder(tree.rightChild);
	        }
	    }

	    public static void main(String[] args) {
	        System.out.println("Tree   view   Begin");
	        JavaTree t1 = new JavaTree();
	        t1.insert(8, 25);
	        t1.insert(5, 9);
	        t1.insert(58, 87);
	        t1.insert(13, 82);
	        t1.insert(4, 8);
	        t1.insert(12, 54);
	        t1.insert(53, 123);
	        t1.insert(56, 47);
	        t1.insert(2, 75);
	        t1.insert(34, 5);
	        t1.insert(6, 23);
	        System.out.println("现在开始遍历:");
	        frontOrder(t1.tree);
	        midOrder(t1.tree);
	    }

	}

	class MyTree {
		int key;
		int data;
		MyTree leftChild;
		MyTree rightChild;
	}

