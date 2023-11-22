package view;

import java.util.List;
import java.util.Optional;
import io.IO;
import models.Departamento;

public class DepartamentoView {

	final List<String> opciones = List.of("0.- Salir", "1.- Mostrar departamentos", "2.- Crear departamento",
			"3.- Modificar departamento", "4.- Eliminar departamento", "5.- Buscar por id");

	public int getOpcion() {
		IO.println("Departamentos: " + opciones);
		return IO.readInt();
	}
	
	public void mostrarDepartamentos (List<Departamento> departamento) {
		for (Departamento depart : departamento) {
			IO.println(depart);
		}
	}

	public Departamento anadir() {
		IO.println("Nombre: ");
		String nombre = IO.readString();
		Departamento depart = Departamento.builder().nombre(nombre).build();
		return depart;
	}

	public Departamento update() {
		IO.println("Id del que se quiere cambiar: ");
		Integer id = IO.readInt();
		
		IO.println("Nuevo nombre: ");
		String nombre = IO.readString();
		
		Departamento depart = Departamento.builder().id(id).nombre(nombre).build();
		
		return depart;
		
	}
	
	public int findById() {
		IO.print("Id del departamento:");
		return IO.readInt();
	}
	
	public Departamento eliminar () {
		IO.println("Id del departamento a eliminar:");
		Integer id = IO.readInt();
		
		Departamento depart = Departamento.builder().id(id).build();
		
		return depart;
	}
	
	public void mostrar(String mensaje) {
		IO.println(mensaje);
	}
	
	public void mostrar(Optional<Departamento> depart) {
		IO.println(depart);
	}
	

}