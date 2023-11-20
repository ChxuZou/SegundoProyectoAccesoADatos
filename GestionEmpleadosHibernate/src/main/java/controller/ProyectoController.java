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

	public List<Proyecto> getProyectorepository() {
		logger.info("Obteniendo toods los proyectos");
		return proyectoRepositoryImpl.findAll();
	}

	public void createProyecto() {
		boolean anadido;
		Proyecto proyecto;
		logger.info("Crear proyecto");
		proyecto = proyectoView.anadir();
		anadido = proyectoRepositoryImpl.save(proyecto);
		proyectoView.mostrar(anadido ? "Añadido" : "No se ha añadido");

	}

	public void getProyectoById() {
		Integer id = proyectoView.findById();
		logger.info("Obteninedo el proyecto por el id: " + id);
		Optional<Proyecto> proyecto = proyectoRepositoryImpl.findById(id);
		proyectoView.mostrar(proyecto);
	}

	public void updateProyecto(Proyecto proyecto) {
		logger.info("Actualizando el proyecto " + proyecto.getId());
		proyectoRepositoryImpl.save(proyecto);
	}

	public void deleteProyecto() {
		Proyecto proyecto;
		Boolean borrado;
		logger.info("eliminando el proyecto ");
		proyecto = proyectoView.eliminar();
		borrado = proyectoRepositoryImpl.delete(proyecto);
		proyectoView.mostrar(borrado ?"Proyecto borrado":"No se ha podido eliminar ese proyecto");
	}
}