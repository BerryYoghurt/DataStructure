package eg.edu.alexu.csd.datastructure.queue;

public class Queue2b implements IQueue, IArrayBased{
	
	private int f;
	private int r;
	private Object[] arr;
	
	public Queue2b(int capacity) {
		arr = new Object[capacity];
		f = -1;
		r = -1;
	}
	
	@Override
	public void enqueue(Object item) {
		if(size() == arr.length || (((f-r) % arr.length)+arr.length)%arr.length == 1)//instead of modulus, queue full
		{
			throw new QueueOverflowException("Queue Full");
		}
		else {
			r = (r + 1)%arr.length;
			arr[r] = item;
			if(f == -1) {
				f = 0;
			}
		}
		
	}

	@Override
	public Object dequeue() {
		Object temp;
		if(f == -1) {
			throw new QueueUnderflowException("Empty");
		}
		else {
			temp = arr[f];
			if(f == r) {
				f = -1;
				r = -1;
			}
			else {
				f = (f+1) % arr.length;
			}
		}
		return temp;
	}

	@Override
	public boolean isEmpty() {
		return f == -1;
	}

	@Override
	public int size() {
		if(f == -1)
			return 0;
		if(arr.length == 1)
			return f+1;
		//return Math.floorMod(r-f+1, arr.length);
		return arr.length - Math.floorMod(f-r, arr.length)+1; //doesnt work for length 1
	}

}
