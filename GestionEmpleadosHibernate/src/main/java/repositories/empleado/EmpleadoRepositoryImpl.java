package repositories.empleado;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import db.HibernateManager;
import jakarta.persistence.TypedQuery;
import models.Empleado;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
	private final Logger logger = Logger.getLogger(EmpleadoRepositoryImpl.class.getName());

	@Override
	public List<Empleado> findAll() {
		logger.info("finadAll()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		TypedQuery<Empleado> query = hb.getManager().createNamedQuery("Empleado.findAll", Empleado.class);
		List<Empleado> listemp = query.getResultList();
		hb.close();
		return listemp;
	}

	@Override
	public Optional<Empleado> findById(Integer id) {
		logger.info("findById()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		Optional<Empleado> emp = Optional.ofNullable(hb.getManager().find(Empleado.class, id));// TODO
		hb.close();
		return emp;
	}

	@Override
	public boolean save(Empleado entity) {
		logger.info("save()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		hb.getTransaction().begin();
		try {
			hb.getManager().merge(entity);
			hb.getTransaction().commit();
			hb.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error al salvar el departamento ");
		} finally {
			if (hb.getTransaction().isActive()) {
				hb.getTransaction().rollback();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Empleado entity) {
		logger.info("delete()");
		HibernateManager hb=HibernateManager.getInstance();
		hb.open();
		try {
			hb.getTransaction().begin();
			entity=hb.getManager().find(Empleado.class, entity.getId());
			entity.getDepartamento().setJefeYDepartamento(null);
			hb.getManager().remove(entity);
			hb.getTransaction().commit();
			hb.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error al eliminar el empleado");
		}finally {
			if (hb.getTransaction().isActive()) {
				hb.getTransaction().rollback();
			}
		}
		return false;
	}


}