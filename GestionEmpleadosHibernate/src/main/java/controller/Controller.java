package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import models.Departamento;
import models.Empleado;
import models.Proyecto;
import repositories.departamento.Departamentorepository;
import repositories.empleado.Empleadorepository;
import repositories.proyectos.Proyectorepository;

public class Controller {
	private final Logger logger = Logger.getLogger(Controller.class.getName());

	private final Proyectorepository proyectorepository;
	private final Departamentorepository departamentorepository;
	private final Empleadorepository empleadorepository;

	public Controller(Departamentorepository departamentorepository, Empleadorepository empleadorepository,
			Proyectorepository proyectorepository) {
		this.departamentorepository = departamentorepository;
		this.empleadorepository = empleadorepository;
		this.proyectorepository = proyectorepository;
	}

	// Departamento
	public List<Departamento> getDepartamentorepository() {
		logger.info("Obteniendo toods los proyectos");
		return departamentorepository.findAll();
	}

	public Departamento createDepartamento(Departamento departamento) {
		logger.info("Craer proyecto");
		return departamentorepository.save(departamento);
	}

	public Optional<Departamento> getDepartamentoById(Integer id) {
		logger.info("Obteninedo el proyecto por el id: " + id);
		return departamentorepository.findById(id);
	}

	public Departamento updateDepartamento(Departamento departamento) {
		logger.info("Actualizando el proyecto " + departamento.getId());
		return departamentorepository.save(departamento);
	}

	public Boolean deleteDepartamento(Departamento departamento) {
		logger.info("eliminando el proyecto " + departamento.getId() + " con nombre " + departamento.getNombre());
		return departamentorepository.delete(departamento);
	}

	// Empleado
	public Empleadorepository getEmpleadorepository() {
		return empleadorepository;
	}

	public Empleado createProyecto(Empleado empleado) {
		logger.info("Craer empleado");
		return empleadorepository.save(empleado);
	}

	public Optional<Proyecto> getEmpleadoById(Integer id) {
		logger.info("Obteninedo el proyecto por el id: " + id);
		return proyectorepository.findById(id);
	}

	public Empleado updateProyecto(Empleado empleado) {
		logger.info("Actualizando el proyecto " + empleado.getId());
		return empleadorepository.save(empleado);
	}

	public Boolean deleteProyecto(Empleado empleado) {
		logger.info("eliminando el proyecto " + empleado.getId() + " con nombre " + empleado.getNombre());
		return empleadorepository.delete(empleado);
	}

	// Proyecto
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
