package controller;

import java.util.Optional;
import java.util.logging.Logger;

import models.Empleado;
import repositories.empleado.EmpleadoRepository;

public class EmpleadoController {
	Logger logger = Logger.getLogger(EmpleadoController.class.getName());
	private final EmpleadoRepository empleadorepository;

	public EmpleadoController(EmpleadoRepository empleadorepository) {
		this.empleadorepository = empleadorepository;
	}

	public EmpleadoRepository getEmpleadorepository() {
		return empleadorepository;
	}

	public Empleado createProyecto(Empleado empleado) {
		logger.info("Craer empleado");
		return empleadorepository.save(empleado);
	}

	public Optional<Empleado> getEmpleadoById(Integer id) {
		logger.info("Obteninedo el empleado por el id: " + id);
		return empleadorepository.findById(id);
	}

	public Empleado updateProyecto(Empleado empleado) {
		logger.info("Actualizando el empleado " + empleado.getId());
		return empleadorepository.save(empleado);
	}

	public Boolean deleteProyecto(Empleado empleado) {
		logger.info("eliminando el empleado " + empleado.getId() + " con nombre " + empleado.getNombre());
		return empleadorepository.delete(empleado);
	}
}