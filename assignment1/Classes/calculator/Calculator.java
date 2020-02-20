package calculator;

public class Calculator implements ICalculator {
	
	public int add(int x, int y) {
		return x+y;
	}
	
	public float divide(int x, int y) throws RuntimeException{
									/*FROM STACKEXCHANGE IEEE 754 defines 1.0 / 0.0 as Infinity and -1.0 / 0.0 as -Infinity and 0.0 / 0.0 as NaN.*/
		if(y==0)throw new RuntimeException("Division By Zero Is Not Allowed");/*BTW Floating point also has -0.0 and so 1.0/ -0.0 is -Infinity.*/
		float result = (float)x/y;	/*Integer arithmetic doesn't have any of these values and throws an Exception instead.*/
		return result;
	}
	
}
