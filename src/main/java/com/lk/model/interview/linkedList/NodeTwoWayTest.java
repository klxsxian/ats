package com.lk.model.interview.linkedList;

/** 
 * 双向循环链表测试类 
 * @author 宏宇 
 * @editor Jan 23, 2012 8:21:33 PM 
 */ 
public class NodeTwoWayTest {
	public static void main(String[] args) {  
        NodeTwoWay node11 = new NodeTwoWay("node11_data");  
        NodeTwoWay node22 = new NodeTwoWay("node22_data");  
        NodeTwoWay node33 = new NodeTwoWay("node33_data");  
        node11.previous = node33; //生成前驱和后继关系  
        node11.next = node22;  
        node22.previous = node11;  
        node22.next = node33;  
        node33.previous = node22;  
        node33.next = node11;  
        /** 
         * 生成node44对象，并将其插入到node11和node22中间 
         */  
        NodeTwoWay node44 = new NodeTwoWay("node44_data");  
        node44.previous = node11;  
        node44.next = node22;  
        node11.next = node44;  
        node22.previous = node44;  
        /** 
         * 删除node44对象 
         */  
        node44.previous = null;  
        node44.next = null;  
        node11.next = node22;  
        node22.previous = node11;  
    }  
}
