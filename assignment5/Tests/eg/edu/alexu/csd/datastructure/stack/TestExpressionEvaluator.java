<<<<<<< HEAD
package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestExpressionEvaluator {
	
	ExpressionEvaluator e = new ExpressionEvaluator();
	@Test
	void test() {
		assertEquals("2 3 4 * +",e.infixToPostfix("2 + 3 * 4"));
		assertEquals(14, e.evaluate("2 3 4 * +"));
	}
	
	@Test
	void test0() {
		assertEquals("a b * 5 +",e.infixToPostfix("a * b + 5"));
	}
	
	@Test
	void test1() {
		assertEquals("1 2 + 7 *", e.infixToPostfix("(1 + 2) * 7"));
		assertEquals(21, e.evaluate("1 2 + 7 *"));
	}
	
	@Test
	void test2() {
		assertEquals("a b * c /", e.infixToPostfix("a * b / c"));
	}
	
	@Test
	void test3() {
		assertEquals("a b c - d + / e a - * c *", e.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));
	}
	
	@Test
	void test4() {
		assertEquals("a b / c - d e * + a c * -", e.infixToPostfix("a / b - c + d * e - a * c"));
	}
	
	@Test
	void test5() {
		assertEquals("a b c + * d *", e.infixToPostfix("a * (b + c) * d"));
		assertEquals(1, e.evaluate("1"));
	}
	
	@Test
	void test6() {
		assertEquals("12 31 + 70 *", e.infixToPostfix("(12 + 31) * 70"));
		assertEquals(3010, e.evaluate("12 31 + 70 *"));//remove *
	}
	
	@Test
	void test7() {
		assertEquals("0 0 12 - 0 + 31 - 0 70 - * -", e.infixToPostfix("-(-12 + -31) * -70"));
		assertEquals(-3010, e.evaluate("0 0 12 - 0 + 31 - 0 70 - * -"));
	}
	
	@Test
	void test8() {
		assertThrows(IllegalArgumentException.class,() -> e.infixToPostfix("1 2 + +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 2 + +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 2 + 3 4 +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("+ 1"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 + 1"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("3 3 ^"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 c +"));
	}
}
=======
package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestExpressionEvaluator {
	
	ExpressionEvaluator e = new ExpressionEvaluator();
	@Test
	void test() {
		assertEquals("2 3 4 * +",e.infixToPostfix("2 + 3 * 4"));
		assertEquals(14, e.evaluate("2 3 4 * +"));
	}
	
	@Test
	void test0() {
		assertEquals("a b * 5 +",e.infixToPostfix("a * b + 5"));
	}
	
	@Test
	void test1() {
		assertEquals("1 2 + 7 *", e.infixToPostfix("(1 + 2) * 7"));
		assertEquals(21, e.evaluate("1 2 + 7 *"));
	}
	
	@Test
	void test2() {
		assertEquals("a b * c /", e.infixToPostfix("a * b / c"));
	}
	
	@Test
	void test3() {
		assertEquals("a b c - d + / e a - * c *", e.infixToPostfix("(a / (b - c + d)) * (e - a) * c"));
	}
	
	@Test
	void test4() {
		assertEquals("a b / c - d e * + a c * -", e.infixToPostfix("a / b - c + d * e - a * c"));
	}
	
	@Test
	void test5() {
		assertEquals("a b c + * d *", e.infixToPostfix("a * (b + c) * d"));
		assertEquals(1, e.evaluate("1"));
	}
	
	@Test
	void test6() {
		assertEquals("12 31 + 70 *", e.infixToPostfix("(12 + 31) * 70"));
		assertEquals(3010, e.evaluate("12 31 + 70 *"));//remove *
	}
	
	@Test
	void test7() {
		assertEquals("0 0 12 - 0 + 31 - 0 70 - * -", e.infixToPostfix("-(-12 + -31) * -70"));
		assertEquals(-3010, e.evaluate("0 0 12 - 0 + 31 - 0 70 - * -"));
	}
	
	@Test
	void test8() {
		assertThrows(IllegalArgumentException.class,() -> e.infixToPostfix("1 2 + +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 2 + +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 2 + 3 4 +"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("+ 1"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 + 1"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("3 3 ^"));
		assertThrows(IllegalArgumentException.class,() -> e.evaluate("1 c +"));
	}
}
>>>>>>> d7a4750f329a2af912a30500c137e49590a0efd2
