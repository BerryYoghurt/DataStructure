package eg.edu.alexu.csd.datastructure.queue;

public class AQueue implements IArrayBased, IQueue {
	
	private int front;
	private int rear;
	private Object[] arr;
	private int size;
	
	
	public AQueue(int capacity) {
		this.front = 0;
		this.rear = 0;
		this.size = 0;
		arr = new Object[capacity];
	}
	
	@Override
	public void enqueue(Object item) {
		if(size < arr.length) {
			arr[rear] = item;
			rear = (rear + 1) % arr.length;
			size++;
		}else {
			throw new QueueOverflowException("Element exceeds capacity");
		}
	}

	@Override
	public Object dequeue() {
		if(size > 0) {
			Object temp = arr[front];
			front = (front+1) % arr.length;
			size--;
			return temp;
		}
		else {
			throw new QueueUnderflowException("Queue already empty");
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

}
