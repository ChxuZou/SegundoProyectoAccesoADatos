package repositories.departamento;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import db.HibernateManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.Departamento;

public class DepartamentoRepositoryImpl implements DepartamentoRepository {
	private final Logger logger = Logger.getLogger(DepartamentoRepositoryImpl.class.getName());
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Departamento");

	@Override
	public List<Departamento> findAll() {
		logger.info("findAll()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		TypedQuery<Departamento> query = hb.getManager().createNamedQuery("Departamento.findAll", Departamento.class);
		List<Departamento> listdep = query.getResultList();
		hb.close();
		return listdep;
	}

	@Override
	public Optional<Departamento> findById(Integer id) {
		logger.info("findById()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		Optional<Departamento> dep = Optional.ofNullable(hb.getManager().find(Departamento.class, id));// TODO
		hb.close();
		return dep;
	}

	@Override
	public Departamento save(Departamento entity) {
		logger.info("save()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		hb.getTransaction().begin();
		try {
			hb.getManager().merge(entity);
			hb.getTransaction().commit();
			hb.close();
			return entity;
		} catch (Exception e) {
			System.out.println("Error al salvar el departamento ");
		} finally {
			if (hb.getTransaction().isActive()) {
				hb.getTransaction().rollback();
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Departamento entity) {
		logger.info("delete()");
		HibernateManager hb=HibernateManager.getInstance();
		hb.open();
		try {
			hb.getTransaction().begin();
			entity=hb.getManager().find(Departamento.class, entity.getId());
			hb.getManager().remove(entity);
			hb.getTransaction().commit();
			hb.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error al eliminar el departamento");
		}finally {
			if (hb.getTransaction().isActive()) {
				hb.getTransaction().rollback();
			}
		}
		return null;
	}
}