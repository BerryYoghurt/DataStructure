package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class CalculatorTest {

	Calculator c = new Calculator();
	@Test
	public void testNormalAddition() {
		assertEquals(5, c.add(3, 2));
		assertEquals(-5,c.add(4,-9));
		assertEquals(-5, c.add(-3, -2));
		assertEquals(-5, c.add(-9, 4));
	}
	
	@Test
	public void testNormalDivision() {
		assertEquals(0.5f, c.divide(1, 2));
		assertEquals(1.0f, c.divide(4, 4));
		assertEquals(2.5f, c.divide(5,2));
		assertEquals(-3.0f, c.divide(-6,2));
		assertEquals(-0.2f, c.divide(2, -10));
		assertEquals(1.0f, c.divide(-2, -2));
	}
	
	@Test
	public void testDivisionZero() {
		assertThrows(RuntimeException.class,()-> {c.divide(2, 0);});
		assertThrows(RuntimeException.class, ()->{c.divide(-3, 0);});
	}

}
