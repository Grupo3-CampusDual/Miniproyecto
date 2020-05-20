import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public int getMenuOption(int maxValue) {
		String userValue = null;
		try {
			userValue = reader.readLine();
		} catch (IOException e) {
			return 0;
		}
		
		int value = parseStringToInt(userValue);
		if (value < maxValue) {
			return value;
		} else {
			return 0;
		}
	}
	
	private int parseStringToInt(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
