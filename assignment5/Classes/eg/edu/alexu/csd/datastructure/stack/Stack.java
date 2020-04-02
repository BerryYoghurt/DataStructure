package eg.edu.alexu.csd.datastructure.stack;

import java.util.NoSuchElementException;


public class Stack implements IStack{

	private class Node{
		Object data;
		Node next;
		Node(Object data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	private int size;
	private Node top; 
	
	public Stack(){
		this.size = 0;
		this.top = null;
	}
	
	@Override
	public Object pop() {
		if(size != 0) {
			Object topData = top.data;
			top = top.next;
			size--;
			return topData;
		}
		else {
			throw new NoSuchElementException("Stack empty");
		}
	}

	@Override
	public Object peek() {
		if(size != 0) {
			return top.data;
		}
		else {
			throw new NoSuchElementException("Stack Empty");
		}
	}

	@Override
	public void push(Object element) {
		Node newNode = new Node(element, this.top);
		this.top = newNode;
		size++;
	}

	@Override
	public boolean isEmpty() {
		return this.top == null;
	}

	@Override
	public int size() {
		return this.size;
	}

}
