package eg.edu.alexu.csd.datastructure.queue;
import eg.edu.alexu.csd.datastructure.linkedList.Classes.*;

public class LQueue implements ILinkedBased, IQueue{
	private DLinkedList ll;
	public LQueue() {
		ll = new DLinkedList();
	}
	@Override
	public void enqueue(Object item) {
		ll.add(item);
	}
	@Override
	public Object dequeue() {
		if(ll.isEmpty()) {
			throw new QueueUnderflowException("Queue empty");
		}
		Object temp = ll.get(0);
		ll.remove(0);
		return temp;
	}
	@Override
	public boolean isEmpty() {
		return ll.isEmpty();
	}
	@Override
	public int size() {
		return ll.size();
	}
	
}
