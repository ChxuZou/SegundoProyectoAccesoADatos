package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import models.Proyecto;
import repositories.proyectos.ProyectoRepository;

public class ProyectoController {

	private final Logger logger = Logger.getLogger(ProyectoController.class.getName());

	private final ProyectoRepository proyectorepository;

	public ProyectoController(ProyectoRepository proyectorepository) {
		this.proyectorepository = proyectorepository;
	}

	public List<Proyecto> getProyectorepository() {
		logger.info("Obteniendo toods los proyectos");
		return proyectorepository.findAll();
	}

	public Proyecto createProyecto(Proyecto proyecto) {
		logger.info("Craer proyecto");
		return proyectorepository.save(proyecto);
	}

	public Optional<Proyecto> getProyectoById(Integer id) {
		logger.info("Obteninedo el proyecto por el id: " + id);
		return proyectorepository.findById(id);
	}

	public Proyecto updateProyecto(Proyecto proyecto) {
		logger.info("Actualizando el proyecto " + proyecto.getId());
		return proyectorepository.save(proyecto);
	}

	public Boolean deleteProyecto(Proyecto proyecto) {
		logger.info("eliminando el proyecto " + proyecto.getId() + " con nombre " + proyecto.getNombre());
		return proyectorepository.delete(proyecto);
	}
}