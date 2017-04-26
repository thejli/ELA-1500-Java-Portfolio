package testing;

public class Matherator implements Adder, Subtractor {

	public long subtract(long... operands) {
		long ret = operands[0]; 
		for (int i = 1; i < operands.length; i++){
			ret -= operands[i]; 
		}
		return ret; 
	}

	public long add(long... operands) {
		long ret = 0; 
		for (long operand : operands) {
			ret += operand; 
		}
		return ret; 
	}

}
