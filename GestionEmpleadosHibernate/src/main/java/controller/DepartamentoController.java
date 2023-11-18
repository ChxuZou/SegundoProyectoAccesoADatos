package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import models.Departamento;
import models.Empleado;
import repositories.departamento.DepartamentoRepositoryImpl;
import view.DepartamentoView;

public class DepartamentoController {
	private Logger logger = Logger.getLogger(DepartamentoController.class.getName());
	private DepartamentoRepositoryImpl departRepoImpl;
	private DepartamentoView departView;
	
	public DepartamentoController() {
		departRepoImpl = new DepartamentoRepositoryImpl();
		departView = new DepartamentoView();
	}

	public void createDepartamento() {
		boolean anadido;
		Departamento depart;
		logger.info("Crear proyecto");
		depart = departView.anadir();
		anadido = departRepoImpl.save(depart);
		departView.mostrar(anadido ? "Añadido" : "No se ha añadido");
	}

	public void getDepartamentoById() {
		Integer id = departView.findById();
		logger.info("Obteninedo el proyecto por el id: " + id);
		Optional<Departamento> depart = departRepoImpl.findById(id);
		departView.mostrar(depart);

	}
	public void mostrarDepartamentos() {
		List<Departamento> listaAMostrar = departRepoImpl.findAll();
		departView.mostrarDepartamentos(listaAMostrar);
	}
	
	public void updateDepartamento() {
		logger.info("Actualizando el departamento");
		Departamento depart = departView.update();
		EmpleadoController controller = new EmpleadoController();
		Empleado emp= controller.getEmpleadoByIdForDepartamento();
		depart.setJefe(emp);
		boolean modificado = departRepoImpl.save(depart);
		departView.mostrar(modificado? "Modificado": "No se ha modificado");
	}

	public void deleteDepartamento() {
		logger.info("Borrando departamento");
		Departamento depart = departView.eliminar();
		boolean borrado= departRepoImpl.delete(depart);
		departView.mostrar(borrado ? "Borrado": "No se ha podido borrar");
	}
}