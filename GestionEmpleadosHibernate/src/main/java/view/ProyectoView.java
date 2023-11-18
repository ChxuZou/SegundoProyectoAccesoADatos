package view;

import java.util.Optional;

import controller.ProyectoController;
import io.IO;
import lombok.var;
import models.Proyecto;
import repositories.proyectos.Proyectorepositoryimpl;

public class ProyectoView {
	ProyectoController proyectoController = new ProyectoController(new Proyectorepositoryimpl());

	public void add() {
		String nombre;
		Proyecto pro;
		IO.println("Introduce el nombre");
		nombre = IO.readString();
		pro = Proyecto.builder().nombre(nombre).build();
		proyectoController.createProyecto(pro);
	}

	public void modificar(Proyecto pro) {
		String nombre;
		if (pro == null) {
			IO.println("No se ha encontrado ningun departamento");
		}
		IO.println("Si no quiere modificar una variable del departamento dele a enter");
		IO.printf("Nombre [%s] ? ", pro.getNombre());
		nombre = IO.readString();
		if (!nombre.isBlank()) {
			pro.setNombre(nombre);
		}
		proyectoController.updateProyecto(pro);
	}

	public void mostrar() {
		var proyectos = proyectoController.getProyectorepository();
		proyectos.forEach(System.out::println);
	}
	public void eliminar(Proyecto pro) {
		proyectoController.deleteProyecto(pro);
	}
	public Optional<Proyecto> findById(Integer id) {
		Optional<Proyecto> pro=proyectoController.getProyectoById(id);
		return pro;
	}
}