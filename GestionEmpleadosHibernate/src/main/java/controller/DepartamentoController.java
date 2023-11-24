package controller;

import java.util.List;
import java.util.NoSuchElementException;
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
				mostrarDepartamentoById();
				break;
			case 6:
				mostrarEmpleadosDeDepartamento();
				break;
			case 7:
				addEmpleadoADepartamento();
				break;
			case 8:
				removeEmpleadoDeDepartamento();
				break;
			case 0:
				fin= true;
				break;
			default:
				break;
			}
		} while (fin == false);

	}

	private void removeEmpleadoDeDepartamento() {
		int idDepart = departView.findById();
		EmpleadoController controller = new EmpleadoController();
		Optional <Empleado> emple = controller.findById();
		try {
			Optional<Departamento> depart = departRepoImpl.findById(idDepart);
			depart.get().removeEmpleado(emple.get());
			boolean borrado = departRepoImpl.save(depart.get());
			departView.mostrar(borrado ? "Empleado eliminado del departamento": "No se ha podido eliminar el empleado del departamento");
		} catch (NoSuchElementException e) {
			departView.mostrar("No se ha encontrado el departamento con id "+idDepart);
		}
		
	}

	private void addEmpleadoADepartamento() {
		int idDepart = departView.findById();
		Optional<Departamento> depart = departRepoImpl.findById(idDepart);
		EmpleadoController controller = new EmpleadoController();
		Optional<Empleado> empleado = controller.findById();
		depart.get().addEmpleado(empleado.get());
		boolean add = departRepoImpl.save(depart.get());
		departView.mostrar(add? "Añadido": "No se ha añadido");
		
	}

	private void mostrarEmpleadosDeDepartamento() {
		int idDepart = departView.findById();
		Optional<Departamento> depart = departRepoImpl.findById(idDepart);
		departView.mostrarEmpleadosDelDepartamento(depart);
	}

	private void createDepartamento() {
		boolean anadido;
		Departamento depart;
		logger.info("Crear proyecto");
		depart = departView.anadir();
		anadido = departRepoImpl.save(depart);
		departView.mostrar(anadido ? "Añadido" : "No se ha añadido");
	}

	private void mostrarDepartamentoById() {
		departView.mostrar(findById());
		
	}

	private void mostrarDepartamentos() {
		List<Departamento> listaAMostrar = departRepoImpl.findAll();
		departView.mostrarDepartamentos(listaAMostrar);
	}

	private void updateDepartamento() {
		logger.info("Actualizando el departamento");
		Departamento depart = departView.update();
		EmpleadoController controller = new EmpleadoController();
		Optional<Empleado> emp = controller.findById();
		depart.setJefe(emp.get());
		boolean modificado = departRepoImpl.save(depart);
		departView.mostrar(modificado ? "Modificado" : "No se ha modificado");
	}

	private void deleteDepartamento() {
		logger.info("Borrando departamento");
		Departamento depart = departView.eliminar();
		boolean borrado = departRepoImpl.delete(depart);
		departView.mostrar(borrado ? "Borrado" : "No se ha podido borrar");
	}
	
	public Optional<Departamento> findById() {
		Integer id = departView.findById();
		logger.info("Obteninedo el departamento por el id: " + id);
		Optional<Departamento> depart = departRepoImpl.findById(id).or(null);
		
		return depart;
	}
}