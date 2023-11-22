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
	private final DepartamentoRepositoryImpl departRepoImpl;
	private final DepartamentoView departView;

	public DepartamentoController() {
		departRepoImpl = new DepartamentoRepositoryImpl();
		departView = new DepartamentoView();
	}

	public void menu() {
		boolean fin = false;
		Integer opcion;

		do {
			opcion = departView.getOpcion();

			switch (opcion) {
			case 1:
				mostrarDepartamentos();
				break;
			case 2:
				createDepartamento();
				break;
			case 3:
				updateDepartamento();
				break;
			case 4:
				deleteDepartamento();
				break;
			case 5:
				findDepartamentoById();
				break;
			case 0:
				fin= true;
				break;

			default:
				break;
			}
		} while (fin == false);

	}

	private void createDepartamento() {
		boolean anadido;
		Departamento depart;
		logger.info("Crear proyecto");
		depart = departView.anadir();
		anadido = departRepoImpl.save(depart);
		departView.mostrar(anadido ? "Añadido" : "No se ha añadido");
	}

	private void findDepartamentoById() {
		Integer id = departView.findById();
		logger.info("Obteninedo el proyecto por el id: " + id);
		Optional<Departamento> depart = departRepoImpl.findById(id);
		departView.mostrar(depart);

	}

	private void mostrarDepartamentos() {
		List<Departamento> listaAMostrar = departRepoImpl.findAll();
		departView.mostrarDepartamentos(listaAMostrar);
	}

	private void updateDepartamento() {
		logger.info("Actualizando el departamento");
		Departamento depart = departView.update();
		EmpleadoController controller = new EmpleadoController();
		Empleado emp = controller.getEmpleadoByIdForDepartamento();
		depart.setJefe(emp);
		boolean modificado = departRepoImpl.save(depart);
		departView.mostrar(modificado ? "Modificado" : "No se ha modificado");
	}

	private void deleteDepartamento() {
		logger.info("Borrando departamento");
		Departamento depart = departView.eliminar();
		boolean borrado = departRepoImpl.delete(depart);
		departView.mostrar(borrado ? "Borrado" : "No se ha podido borrar");
	}
	
	public Departamento getDepartamentoByIdForEmple() {
		Integer id = departView.findById();
		logger.info("Obteninedo el departamento por el id: " + id);
		Optional<Departamento> depart = departRepoImpl.findById(id);
		if (depart != null) {
			departView.mostrar(depart);
			return depart.get();
		}else {
			return null;
		}

	}
}