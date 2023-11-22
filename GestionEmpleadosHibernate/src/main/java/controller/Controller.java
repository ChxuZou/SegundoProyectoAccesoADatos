package controller;

import view.View;

public class Controller {

	public Controller() {
		Integer opcion; 
		boolean fin= false;
		
		EmpleadoController empCon = new EmpleadoController();
		DepartamentoController departCon = new DepartamentoController();
		ProyectoController proCon = new ProyectoController();
		do {
			opcion = View.getOpcion();
			switch (opcion) {
			case 1:
				empCon.menu();
				break;
			case 2:
				departCon.menu();
				break;
			case 3:
				proCon.menu();
				break;
			case 0:
				fin= true;
				break;

			default:
				break;
			}
		} while (fin==false);
		

	}
}
