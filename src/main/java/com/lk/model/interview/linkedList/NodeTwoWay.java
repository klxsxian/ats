package com.lk.model.interview.linkedList;

/** 
 * 模拟双向循环链表 
 * @author 宏宇 
 * @editor Jan 23, 2012 8:16:34 PM 
 * @see java.util.ArrayList类的底层，是用数组实现的 
 * @see java.util.LinkedList类的底层，就是用双向循环链表实现的 
 * @see 双向链表内的每个对象除了数据本身外，还有两个引用，分别指向前一个元素和后一个元素 
 * @see 故add/remove操作时，LinkedList性能好一些，而get操作时，ArrayList性能好一些 
 */
public class NodeTwoWay {
	NodeTwoWay previous; //存放指向前一个节点的引用  
    String data;     //存放节点数据本身  
    NodeTwoWay next;     //存放指向后一个节点的引用  
      
    public NodeTwoWay(){}  
      
    public NodeTwoWay(String data){  
        this.data = data;  
    }  
}
