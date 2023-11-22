package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import models.Proyecto;
import repositories.proyectos.ProyectoRepositoryImpl;
import view.ProyectoView;

public class ProyectoController {

	private final Logger logger = Logger.getLogger(ProyectoController.class.getName());

	private final ProyectoRepositoryImpl proyectoRepositoryImpl;
	private final ProyectoView proyectoView;

	public ProyectoController() {
		proyectoRepositoryImpl = new ProyectoRepositoryImpl();
		proyectoView = new ProyectoView();
	}

	public void menu() {
		boolean fin = false;
		Integer opcion;

		do {
			opcion = proyectoView.getOpcion();
			switch (opcion) {
			case 0:
				fin = true;
				break;
			case 1:
				getProyectoRepository();
				break;
			case 2:
				createProyecto();
				break;
			case 3:
				updateProyecto();
				break;
			case 4:
				deleteProyecto();
				break;
			case 5:
				findProyectoById();
				break;
			case 6:
				mostrarEmpleadosDelProyecto();
				break;
			case 7:
				addEmpleAProyecto();
				break;
			default:
				break;
			}
		} while (fin == false);

	}

	private void addEmpleAProyecto() {
		
	}

	private void mostrarEmpleadosDelProyecto() {
		int idPro = proyectoView.findById();
		Optional<Proyecto> pro = proyectoRepositoryImpl.findById(idPro);
		proyectoView.mostrarEmpleadosDelProyecto(pro);
	}

	private void getProyectoRepository() {
		logger.info("Obteniendo todos los proyectos");
		List<Proyecto> lista = proyectoRepositoryImpl.findAll();
		proyectoView.mostrarProyectos(lista);
	}

	private void createProyecto() {
		boolean anadido;
		Proyecto proyecto;
		logger.info("Crear proyecto");
		proyecto = proyectoView.anadir();
		anadido = proyectoRepositoryImpl.save(proyecto);
		proyectoView.mostrar(anadido ? "Añadido" : "No se ha añadido");

	}

	private void findProyectoById() {
		Integer id = proyectoView.findById();
		logger.info("Obteninedo el proyecto por el id: " + id);
		Optional<Proyecto> proyecto = proyectoRepositoryImpl.findById(id);
		proyectoView.mostrar(proyecto);
	}

	private void updateProyecto() {
		Proyecto proyecto;
		boolean modificado;
		logger.info("Actualizando el proyecto ");
		proyecto = proyectoView.update();
		modificado = proyectoRepositoryImpl.save(proyecto);
		proyectoView.mostrar(modificado ? "Proyecto modificado" : "No se ha podido modiifcar el proyecto");
	}

	private void deleteProyecto() {
		Proyecto proyecto;
		Boolean borrado;
		logger.info("eliminando el proyecto ");
		proyecto = proyectoView.eliminar();
		borrado = proyectoRepositoryImpl.delete(proyecto);
		proyectoView.mostrar(borrado ? "Proyecto borrado" : "No se ha podido eliminar ese proyecto");
	}

	public Proyecto getProyectoByIdForEmpleado() {
		Integer id = proyectoView.findById();
		logger.info("Obteninedo el proyecto por el id: " + id);
		Optional<Proyecto> proyecto = proyectoRepositoryImpl.findById(id);
		if (proyecto != null) {
			proyectoView.mostrar(proyecto);
			return proyecto.get();
		} else {
			return null;
		}
	}
}