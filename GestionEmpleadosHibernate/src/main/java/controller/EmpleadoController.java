package controller;

import java.util.Optional;
import java.util.logging.Logger;

import models.Empleado;
import repositories.empleado.EmpleadoRepository;
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

	public EmpleadoRepository getEmpleadorepository() {
		return empleadorepository;
	}

	

	public void getEmpleadoById() {
		Optional<Empleado> emple;
		Integer id = view.findById();
		logger.info("Obteninedo el empleado por el id: " + id);
		emple = empleadorepository.findById(id);
		if (emple != null) {
			view.mostrar(emple.toString());
		}
	}
	
	public Empleado getEmpleadoByIdForDepartamento() {
		Optional<Empleado> emple;
		Integer id = view.findById();
		logger.info("Obteninedo el empleado por el id: " + id);
		emple = empleadorepository.findById(id);
		if (emple != null) {
			return emple.get();
		}
		return null;
	}



	public Boolean deleteProyecto(Empleado empleado) {
		logger.info("eliminando el empleado " + empleado.getId() + " con nombre " + empleado.getNombre());
		return empleadorepository.delete(empleado);
	}
}