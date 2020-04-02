package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestStack {

	/*Stack sort(Stack stack) {
		Object top, next, temp;
		if(stack.size() == 1 ||stack.size() == 0) {
			return stack;
		}else {
			top = stack.pop();
			next = stack.pop();
			sort(stack);
			if((int)top < (int)next) {
				temp = top;
				top = next;
				next = temp;
			}
			stack.push(next);
			sort(stack);
			stack.push(top);
			//sort(stack);
			return stack;
		}
		
	}
	
	*/
	/*Stack sort(Stack stack, Object min) {
		Stack sortedStack;
		Object top, next;
		if(stack.size() == 0) {
			sortedStack = new Stack();
		}
		else if(stack.size() == 1) {
			next = stack.pop();
			sortedStack = new Stack();
			if((int)min < (int)next) {
				sortedStack.push(min);
				sortedStack.push(next);
			}
			else {
				sortedStack.push(next);
				sortedStack.push(min);
			}
		}
		else{
			
			//top = stack.pop();
			next = stack.pop();
			if((int)min > (int)next) {
				sortedStack = sort(stack, next);
			}
			else {
				sortedStack = sort(stack,min);
			}
			if((int)top > (int)next) {
				sortedStack.push(next);
				sortedStack.push(top);
			}
			else {
				sortedStack.push(top);
				sortedStack.push(next);
			}
			stack.push(next);
			stack.push(top);
		}
		return sortedStack;
	}*/
	
	void sort(Stack s) {
	    if (!s.isEmpty()) {
	        Object x = s.pop();
	        sort(s);
	        insert(s, x);
	    }
	}

	void insert(Stack s, Object x) {
	    if (!s.isEmpty()) {  
	        Object y = s.peek();
	        if ((int)x < (int)y) {
	            s.pop();
	            insert(s, x);
	            s.push(y);
	        } else {
	            s.push(x);
	        }
	    } else {
	        s.push(x); 
	    }
	}
	
	@Test
	void test() {
		Stack stack = new Stack();
		/*Istack.push(5);
		stack.push(4);
		stack.push(2);
		stack.push(6);
		stack.push(10);
		stack.push(1);
		stack.push(3);*/
		for(int i = 6; i >= 1; i--) {
			stack.push(i);
		}
		System.out.println("Old Stack");
		/*while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}*/
		sort(stack);
		
		System.out.println("New Stack");
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		
	}

	boolean palindrome(String w) {
		int lowerHalf = w.length()/2;
		int upperHalf = (w.length()%2 == 0)?lowerHalf:lowerHalf+1;
		Stack s = new Stack();
		boolean valid = true;
		for(int i = 0; i < w.length(); i++) {
			if(i < lowerHalf) {
				s.push(w.charAt(i));
			}
			else if(i >= upperHalf) {
				if(!((Character)s.peek()).equals(w.charAt(i))) {
					valid = false;
					break;
				}
				else {
					s.pop();
				}
			}
		}
		return valid;
	}
	
	boolean palindrome1(String w) {
		int count = w.length()/2;
		Stack s = new Stack();
		for(int i = 0; i  < count; i++) {
			s.push(w.charAt(i));
		}
		if(w.length()%2 == 1) count++;
		boolean palindrome = true;
		for(int i = count; i  < w.length(); i++) {
			if(!((Character)s.peek()).equals(w.charAt(i))) {
				palindrome = false;
				break;
			}
			s.pop();
		}
		return palindrome;
	}
	
	@Test
	void test1() {
		assertTrue(palindrome("ama"));
		assertTrue(palindrome("rev i ver"));
		assertFalse(palindrome("eama"));
		assertFalse(palindrome("amae"));
		assertTrue(palindrome1("ama"));
		assertTrue(palindrome1("rev i ver"));
		assertFalse(palindrome1("eama"));
		assertFalse(palindrome1("amae"));
	}
}
