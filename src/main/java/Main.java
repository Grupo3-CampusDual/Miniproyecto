
public class Main {

	public static void main(String[] args) {
		
		InputReader inputReader = new InputReader();
		int opcion = 0;
		while (opcion == 0) {
			System.out.println("Elija una opción: ");
			opcion = inputReader.getMenuOption(4);
		}
	}

}
