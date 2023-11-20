package view;

import java.util.List;
import java.util.Optional;

import io.IO;
import models.Proyecto;

public class ProyectoView {
	final List<String> opciones = List.of("0.- Salir", "1.- Mostrar proyectos", "2.- Crear proyecto",
			"3.- Modificar proyecto", "4.- Eliminar proyecto");

	public int getOpcion() {
		IO.println("proyecto: " + opciones);
		return IO.readInt();
	}

	public void mostrarProyectos(List<Proyecto> proyecto) {
		proyecto.stream().forEach(pro -> IO.print(pro));
	}

	public Proyecto anadir() {
		IO.println("Nombre: ");
		String nombre = IO.readString();
		Proyecto pro = Proyecto.builder().nombre(nombre).build();
		return pro;
	}

	public Proyecto update() {
		IO.println("Id: ");
		Integer id = IO.readInt();
		IO.println("Nombre?");
		String nombre = IO.readString();
		Proyecto pro = Proyecto.builder().id(id).nombre(nombre).build();
		return pro;
	}

	public Proyecto eliminar() {
		Proyecto pro;
		Integer id;
		IO.println("Introduce el id del proyecto");
		id = IO.readInt();
		pro = Proyecto.builder().id(id).build();
		return pro;
	}

	public int findById() {
		IO.println("id del proyecto?");
		return IO.readInt();
	}

	public void mostrar(String mensaje) {
		IO.println(mensaje);
	}

	public void mostrar(Optional<Proyecto> depart) {
		IO.println(depart);

	}

}