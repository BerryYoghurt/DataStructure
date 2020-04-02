<<<<<<< HEAD
package eg.edu.alexu.csd.datastructure.stack;

public interface IStack {
	/**
	* Removes the element at the top of stack and returns that element.
	*
	* @return top of stack element, or through exception if empty
	*/
	public Object pop();
	/**
	* Get the element at the top of stack without removing it from stack.
	*
	* @return top of stack element, or throw exception if empty
	*/
	public Object peek();
	/**
	* Pushes an item onto the top of this stack.
	*
	* @param element
	* to insert
	*/
	public void push(Object element);
	/**
	* Tests if this stack is empty
	*
	* @return true if stack empty
	*/
	public boolean isEmpty();
	/**
	* Returns the number of elements in the stack.
	*
	* @return number of elements in the stack
	*/
	public int size();
}
=======
package eg.edu.alexu.csd.datastructure.stack;

public interface IStack {
	/**
	* Removes the element at the top of stack and returns that element.
	*
	* @return top of stack element, or through exception if empty
	*/
	public Object pop();
	/**
	* Get the element at the top of stack without removing it from stack.
	*
	* @return top of stack element, or throw exception if empty
	*/
	public Object peek();
	/**
	* Pushes an item onto the top of this stack.
	*
	* @param element
	* to insert
	*/
	public void push(Object element);
	/**
	* Tests if this stack is empty
	*
	* @return true if stack empty
	*/
	public boolean isEmpty();
	/**
	* Returns the number of elements in the stack.
	*
	* @return number of elements in the stack
	*/
	public int size();
}
>>>>>>> d7a4750f329a2af912a30500c137e49590a0efd2
