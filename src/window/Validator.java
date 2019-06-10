package window;

public class Validator {
	
	public Validator() {
		
	}
	
	public static boolean validateVariableName(String inp) {
		if(inp.contains(" ")) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean validatePath(String inp) {
		return true;
	}

}
