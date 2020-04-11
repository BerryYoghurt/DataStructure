package eg.edu.alexu.csd.datastructure.queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestQueue {

	IQueue q;
	@Test
	void testArray() {
		q = new AQueue(3);
		for(int i = 0; i < 2; i++)
			combined();
	
	}
	
	void combined() {
		assertThrows(QueueUnderflowException.class, () -> q.dequeue());
		assertEquals(0,q.size());
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		assertEquals(3, q.size());
		assertThrows(QueueOverflowException.class, () -> q.enqueue(3));
		assertEquals("a", q.dequeue());
		assertEquals("b", q.dequeue());
		q.enqueue("e");
		assertEquals("c", q.dequeue());
		q.enqueue("f");
		q.enqueue("g");
		q.dequeue();
		q.dequeue();
		q.dequeue();
		assertTrue(q.isEmpty());
		assertThrows(QueueUnderflowException.class, () -> q.dequeue());
	}
	
	
	@Test
	void testLinked() {
		q = new LQueue();
		for(int i = 0; i < 2; i++)
			combined2();
		q = new LQueue();
		testCap1();
	}
	
	void combined2() {
		assertThrows(QueueUnderflowException.class, () -> q.dequeue());
		assertEquals(0,q.size());
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		assertEquals(3, q.size());
		assertEquals("a", q.dequeue());
		assertEquals("b", q.dequeue());
		q.enqueue("e");
		assertEquals("c", q.dequeue());
		q.enqueue("f");
		q.enqueue("g");
		q.dequeue();
		q.dequeue();
		q.dequeue();
		assertTrue(q.isEmpty());
		assertThrows(QueueUnderflowException.class, () -> q.dequeue());
	}

	/*@Test
	void testArray2b() {
		q = new Queue2b(3);
		for(int i = 0; i < 2; i++)
			combined();
		q = new Queue2b(1);
		testCap1();
	}
	*ظ
	void testCap1() {
		
		q.enqueue("1");
		assertEquals(1, q.size());
		if(q instanceof IArrayBased )
			assertThrows(QueueOverflowException.class, ()->q.enqueue("d"));
		q.dequeue();
		assertTrue(q.isEmpty());
	}
}
