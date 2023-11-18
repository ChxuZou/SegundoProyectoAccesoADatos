package view;

import java.util.List;
import io.IO;
import models.Empleado;

public class EmpleadoView {

	final List<String> opciones = List.of("0.- Salir", "1.- Mostrar departamentos", "2.- Crear departamento",
			"3.- Modificar departamento", "4.- Eliminar departamento");

	public int getOpcion() {
		IO.println("Departamentos: " + opciones);
		return IO.readInt();
	}
	
	public void mostrarEmpleados (List<Empleado> lista) {
		for (Empleado depart : lista) {
			IO.println(depart);
		}
	}
	
	public int findById() {
		IO.println("Id ?");
		return IO.readInt();
	}

	public void anadir() {
		
	}

	public Empleado modificar() {
		IO.println("Id: ");
		Integer id = IO.readInt();
		String nombre = IO.readString();
		Empleado emple = Empleado.builder().nombre(nombre).id(id).build();
		return emple;
	}
	
	public void asignarProyecto() {
		
	}
	
	public void asignarDepartamento() {
		
	}
	
	public void eliminar () {
		
		
	}
	
	public void mostrar (String msg) {
		IO.println(msg);
	}
}