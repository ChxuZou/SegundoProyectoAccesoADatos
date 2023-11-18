package view;

import java.util.List;

import io.IO;
import models.Departamento;
import repositories.departamento.DepartamentoRepositoryImpl;
import repositories.empleado.EmpleadoRepositoryImpl;

public class DepartamentoView {
	DepartamentoRepositoryImpl dri = new DepartamentoRepositoryImpl();

	final List<String> opciones = List.of("0.- Salir", "1.- Mostrar departamentos", "2.- Crear departamento",
			"3.- Modificar departamento", "4.- Eliminar departamento");

	public int getOpcion() {
		IO.println("Departamentos: " + opciones);
		return IO.readInt();
	}
	
	public void mostrarDepartamentos () {
		for (Departamento depart : dri.findAll()) {
			IO.println(depart);
		}
	}

	public void anadir() {
		IO.println("Nombre: ");
		String nombre = IO.readString();
		Departamento depart = Departamento.builder().nombre(nombre).build();
		boolean anadido = dri.save(depart);
		IO.println(anadido ? "Departamento añadido" : "El departamento no se ha podido añadir" );
	}

	public void modificar(DepartamentoRepositoryImpl dri) {
		IO.println("Id: ");
		Integer id = IO.readInt();
		Departamento depart = dri.findById(id);
		if (dri.findById(id) == null) {
			IO.println("No se ha encontrado el departamento");
			return;
		}
		IO.println("Nombre: " + depart.getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			depart.setNombre(nombre);
		}
		IO.println("Jefe: " + depart.getJefe());
		Integer jefe = IO.readInt();
		if (jefe != null) {
			EmpleadoRepositoryImpl eri = new EmpleadoRepositoryImpl();
			depart.setJefe(eri.findById(jefe));
		}
		boolean anadido = dri.save(depart);
		IO.println(anadido ? "Modificado" : "No se ha podido modificar");
		
	}
	
	public void eliminar () {
		IO.println("Id: ");
		Integer id = IO.readInt();
		Departamento depart = dri.findById(id);
		if (dri.findById(id) == null) {
			IO.println("No se ha encontrado el departamento");
			return;
		}
		boolean eliminar = dri.delete(depart);
		IO.println(eliminar ? "Eliminado" : "No se ha podido eliminar");
		
	}
	

}