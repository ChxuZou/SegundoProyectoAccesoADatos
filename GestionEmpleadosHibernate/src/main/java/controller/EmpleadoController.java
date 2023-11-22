package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import io.IO;
import models.Departamento;
import models.Empleado;
import models.Proyecto;
import repositories.empleado.EmpleadoRepositoryImpl;
import view.EmpleadoView;

public class EmpleadoController {
	Logger logger = Logger.getLogger(EmpleadoController.class.getName());
	private EmpleadoRepositoryImpl empleadorepository;
	private EmpleadoView view;

	public EmpleadoController() {
		empleadorepository = new EmpleadoRepositoryImpl();
		view = new EmpleadoView();
	}

	public void menu() {
		int opc;
		boolean ejecutar = true;
		
		do {
			opc = view.getOpcion();
			switch (opc) {
			case 0:
				ejecutar = false;
				break;
			case 1:
				mostrarEmpleados();
				break;
			case 2: 
				anadirEmpleado();
				break;
			case 3: 
				updateEmpleado();
				break;
			case 4:
				deleteEmpleado();
				break;
			case 5:
				mostrarProyectosDelEmpleado();
				break;
			default:
				IO.println("Valor introducido inválido");
				break;
			}
		} while (ejecutar);
	}

	private void mostrarProyectosDelEmpleado() {
		int idEmpleado = view.findById();
		Optional<Empleado> emp= empleadorepository.findById(idEmpleado);
		view.mostrarProyectosDelEmpleado(emp);
		
	}

	private void mostrarEmpleados() {
		logger.info("Mostrando empleados");
		List<Empleado> lista= empleadorepository.findAll();
		view.mostrarEmpleados(lista);
	}

	public void anadirEmpleado() {
		boolean anadir;
		Empleado empleado = view.anadir();
		logger.info("Crear empleado");
		anadir = empleadorepository.save(empleado);
		view.mostrar(anadir ? "Añadido" : "No se ha añadido");
	}

	public void getEmpleadoById() {
		Optional<Empleado> emple;
		Integer id = view.findById();
		logger.info("Obteninedo el empleado por el id: " + id);
		emple = empleadorepository.findById(id);
		if (emple != null) {
			view.mostrar(emple);
		} else {
			IO.println("No se ha encontrado el empleado");
		}
	}

	public Empleado getEmpleadoByIdForDepartamento() {
		Optional<Empleado> emple;
		Integer id = view.findById();
		logger.info("Obteninedo el empleado por el id: " + id);
		emple = empleadorepository.findById(id);
		if (emple != null) {
			return emple.get();
		} else {
			IO.println("No se ha encontrado el empleado");
			return null;
		}

	}

	public void updateEmpleado() {
		boolean anadir;
		Integer id;
		Departamento dep = new Departamento();
		DepartamentoController dc = new DepartamentoController();
		ProyectoController pc = new ProyectoController();
		Proyecto pro = new Proyecto();
		Optional<Empleado> emple;
		id = view.findById();
		emple = empleadorepository.findById(id);
		if (emple == null) {
			IO.println("No se ha encontrado el empleado");
		}
		dep = dc.getDepartamentoByIdForEmple();
		pro = pc.getProyectoByIdForEmpleado();
		emple.get().setDepartamento(dep);
		logger.info("Actualizando el empleado " + emple.get().getId());
		anadir = empleadorepository.save(emple.get());
		view.mostrar(anadir ? "Actualizado" : "No se ha actualizado");
	}

	public void deleteEmpleado() {
		Integer id = view.findById();
		Optional<Empleado> emple = empleadorepository.findById(id);
		if (emple == null) {
			IO.println("No se ha encontrado el empleado");
			return;
		}
		boolean eliminar = empleadorepository.delete(emple.get());
		IO.println(eliminar ? "Eliminado" : "No se ha podido eliminar");
	}
}