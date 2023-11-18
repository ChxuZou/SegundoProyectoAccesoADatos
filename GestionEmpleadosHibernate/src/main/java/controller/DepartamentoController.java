package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import models.Departamento;
import repositories.departamento.DepartamentoRepositoryImpl;

public class DepartamentoController {
	private final Logger logger = Logger.getLogger(DepartamentoController.class.getName());
	private final DepartamentoRepositoryImpl departamentoRepositoryImpl;

	public DepartamentoController(DepartamentoRepositoryImpl departamentoRepositoryImpl) {
		this.departamentoRepositoryImpl = departamentoRepositoryImpl;
	}

	public List<Departamento> getDepartamentorepository() {
		logger.info("Obteniendo toods los proyectos");
		return departamentoRepositoryImpl.findAll();
	}

	public Departamento createDepartamento(Departamento departamento) {
		logger.info("Craer proyecto");
		return departamentoRepositoryImpl.save(departamento);
	}

	public Optional<Departamento> getDepartamentoById(Integer id) {
		logger.info("Obteninedo el proyecto por el id: " + id);
		return departamentoRepositoryImpl.findById(id);
	}

	public Departamento updateDepartamento(Departamento departamento) {
		logger.info("Actualizando el proyecto " + departamento.getId());
		return departamentoRepositoryImpl.save(departamento);
	}

	public Boolean deleteDepartamento(Departamento departamento) {
		logger.info("eliminando el proyecto " + departamento.getId() + " con nombre " + departamento.getNombre());
		return departamentoRepositoryImpl.delete(departamento);
	}
}