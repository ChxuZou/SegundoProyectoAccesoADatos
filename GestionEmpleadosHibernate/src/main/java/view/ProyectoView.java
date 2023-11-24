package view;

import java.util.List;
import java.util.Optional;

import io.IO;
import models.Empleado;
import models.Proyecto;

public class ProyectoView {
	final List<String> opciones = List.of("0.- Salir", "1.- Mostrar proyectos", "2.- Crear proyecto",
			"3.- Modificar proyecto", "4.- Eliminar proyecto", "5.- Mostrar por id",
			"6.- Mostrar empleados del proyecto", "7.- Añadir empleado al proyecto");

	public int getOpcion() {
		IO.println("proyecto: ");
		for (String opcion : opciones) {
			IO.println(opcion);
		}
		return IO.readInt();
	}

	public void mostrarProyectos(List<Proyecto> proyecto) {
		proyecto.stream().forEach(pro -> IO.println(pro));
	}

	public Proyecto anadir() {
		IO.println("Nombre del proyecto: ");
		String nombre = IO.readNombre();
		Proyecto pro = Proyecto.builder().nombre(nombre).build();
		return pro;
	}

	public Proyecto update() {
		IO.println("Id del proyecto a modificar: ");
		Integer id = IO.readInt();
		IO.println("Nuevo nombre: ");
		String nombre = IO.readNombre();
		Proyecto pro = Proyecto.builder().id(id).nombre(nombre).build();
		return pro;
	}

	public Proyecto eliminar() {
		Proyecto pro;
		Integer id;
		IO.println("Introduce el id del proyecto a eliminar:");
		id = IO.readInt();
		pro = Proyecto.builder().id(id).build();
		return pro;
	}

	public int findById() {
		IO.println("Id del proyecto a buscar:");
		return IO.readInt();
	}

	public void mostrar(String mensaje) {
		IO.println(mensaje);
	}

	public void mostrar(Optional<Proyecto> depart) {
		IO.println(depart);
	}

	public void mostrarEmpleadosDelProyecto(Optional<Proyecto> pro) {
		for (Empleado empleado : pro.get().getEmpleados()) {
			IO.println(empleado);
		}
	}

}