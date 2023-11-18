package view;

import java.util.List;

import io.IO;

public class View {
	
	static final List<String> opciones = List.of(
			 "0.-Salir", "1.- Empleado", "2.- Departamento", "3.- Proyecto");
	
	public static int getOpcion() {
		IO.println(opciones);
		return IO.readInt();
	}

}