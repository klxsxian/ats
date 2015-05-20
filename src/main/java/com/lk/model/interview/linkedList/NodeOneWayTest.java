package com.lk.model.interview.linkedList;

/**
 * 单向链表测试类
 * 
 * @author Administrator
 * 
 */
public class NodeOneWayTest {
	public static void main(String[] args) {
		NodeOneWay node11 = new NodeOneWay("node11_data");
		NodeOneWay node22 = new NodeOneWay("node22_data");
		NodeOneWay node33 = new NodeOneWay("node33_data");
		node11.next = node22; // 生成后继关系
		node22.next = node33;
		System.out.println(node11.next.next.data); // 通过node11获得node33的data属性值
		/**
		 * 生成node44对象，并将其插入到node11和node22中间
		 */
		NodeOneWay node44 = new NodeOneWay("node44_data");
		node11.next = node44; // 修改node11的后继关系指向node44
		node44.next = node22; // 修改node44的后继关系指向node22
		System.out.println(node11.next.next.next.data); // 通过node11获得node33的data属性值
		System.out.println(node11.next.next.data); // 通过node11获得node22的data属性值
		/**
		 * 删除node44对象
		 */
		node11.next = node22; // 即node11的后继关系指向node22，node44的后继关系不再指向node22
		node44.next = null;
		System.out.println(node11.next.next.data); // 通过node11获得node33的data属性值
	}
}
