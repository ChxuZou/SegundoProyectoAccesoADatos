package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import models.Departamento;
import models.Empleado;
import repositories.departamento.DepartamentoRepositoryImpl;
import repositories.empleado.EmpleadoRepositoryImpl;
import view.DepartamentoView;

public class DepartamentoController {
	private Logger logger = Logger.getLogger(DepartamentoController.class.getName());
	private DepartamentoRepositoryImpl departamentoRepositoryImpl;
	private DepartamentoView departView;
	private EmpleadoRepositoryImpl empRI;
	
	public DepartamentoController() {
		departamentoRepositoryImpl = new DepartamentoRepositoryImpl();
		departView = new DepartamentoView();
		empRI = new EmpleadoRepositoryImpl();
		
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
	public void mostrarDepartamentos() {
		List<Departamento> listaAMostrar = departamentoRepositoryImpl.findAll();
		departView.mostrarDepartamentos(listaAMostrar);
		
	}

	public void updateDepartamento() {
		logger.info("Actualizando el departamento");
		Departamento depart = departView.update();
		Empleado emp = empRI.findById();
		
	}

	public void deleteDepartamento() {
		logger.info("");
	}
}