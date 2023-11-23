package view;

import java.util.List;
import java.util.Optional;
import io.IO;
import models.Empleado;
import models.Proyecto;

public class EmpleadoView {

	final List<String> opciones = List.of("0.- Salir", "1.- Mostrar empleados", "2.- Crear empleado",
			"3.- Modificar empleado", "4.- Eliminar empleado", "5.- Mostrar proyectos de un empleado");

	public int getOpcion() {
		IO.println("Empleados: ");
		for (String opcion : opciones) {
			IO.println(opcion);
		}
		return IO.readInt();
	}


	public int findById() {
		IO.println("Id del empleado:");
		return IO.readInt();
	}

	public Empleado anadir() {
		IO.println("Nombre: ");
		String nombre = IO.readString();
		Empleado emp = Empleado.builder().nombre(nombre).build();
		return emp;
	}

	public Empleado modificar() {
		IO.println("Id del empleado a modificar: ");
		Integer id = IO.readInt();
		IO.println("Nuevo nombre? ");
		String nombre = IO.readString();
		IO.println("Nuevo salario? ");
		double salario = IO.readSalario();
		Empleado emple = Empleado.builder().nombre(nombre).id(id).salario(salario).build();
		return emple;
	}

	public void mostrar(String msg) {
		IO.println(msg);
	}

	public void mostrar(Optional<Empleado> emple) {
		IO.println(emple);
	}

	public void mostrarEmpleados(List<Empleado> lista) {
		for (Empleado empleado : lista) {
			IO.println(empleado);
		}
	}
	
	public void mostrarProyectosDelEmpleado(Optional<Empleado> emp) {
		for (Proyecto proyecto : emp.get().getProyectos()) {
			IO.println(proyecto);
		}
	}
}
