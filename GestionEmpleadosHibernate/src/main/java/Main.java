import java.util.logging.Logger;

import controller.Controller;

public class Main {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Main.class.getName());
		logger.info("Inicio del programa");
		new Controller();
	}
}