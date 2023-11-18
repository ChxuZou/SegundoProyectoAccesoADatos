package controller;

import java.util.Optional;
import java.util.logging.Logger;
import models.Departamento;
import repositories.departamento.DepartamentoRepositoryImpl;
import view.DepartamentoView;

public class DepartamentoController {
	private Logger logger = Logger.getLogger(DepartamentoController.class.getName());
	private DepartamentoRepositoryImpl departamentoRepositoryImpl;
	private DepartamentoView departView;

	public DepartamentoController() {
		departamentoRepositoryImpl = new DepartamentoRepositoryImpl();
		departView = new DepartamentoView();
	}

	public void createDepartamento() {
		boolean anadido;
		Departamento depart;

		logger.info("Crear proyecto");
		depart = departView.anadir();
		anadido = departamentoRepositoryImpl.save(depart);
		departView.mostrar(anadido ? "Añadido" : "No se ha añadido");
	}

	public void getDepartamentoById() {
		Integer id = departView.findById();
		logger.info("Obteninedo el proyecto por el id: " + id);
		Optional<Departamento> depart = departamentoRepositoryImpl.findById(id);
		departView.mostrar(depart);

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