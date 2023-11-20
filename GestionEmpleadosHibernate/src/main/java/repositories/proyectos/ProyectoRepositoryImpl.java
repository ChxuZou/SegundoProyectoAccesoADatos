package repositories.proyectos;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import db.HibernateManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.Proyecto;

public class ProyectoRepositoryImpl implements ProyectoRepository {
	private final Logger logger = Logger.getLogger(ProyectoRepositoryImpl.class.getName());
	EntityManager emf = Persistence.createEntityManagerFactory("default").createEntityManager();

	@Override
	public List<Proyecto> findAll() {
		logger.info("finadAll()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		TypedQuery<Proyecto> query = hb.getManager().createNamedQuery("Proyecto.findAll", Proyecto.class);
		List<Proyecto> listemp = query.getResultList();
		hb.close();
		return listemp;
	}

	@Override
	public Optional<Proyecto> findById(Integer id) {
		logger.info("findById()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		Optional<Proyecto> dep = Optional.ofNullable(hb.getManager().find(Proyecto.class, id));// TODO
		hb.close();
		return dep;
	}

	@Override
	public boolean save(Proyecto entity) {
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
			System.out.println("Error al salvar el proyecto ");
		} finally {
			if (hb.getTransaction().isActive()) {
				hb.getTransaction().rollback();
			}
		}
		return false;
	}

	@Override
	public Boolean delete(Proyecto entity) {
		logger.info("delete()");
		HibernateManager hb = HibernateManager.getInstance();
		hb.open();
		try {
			hb.getTransaction().begin();
			entity = hb.getManager().find(Proyecto.class, entity.getId());
			hb.getManager().remove(entity);
			hb.getTransaction().commit();
			hb.close();
			return true;
		} catch (Exception e) {
			System.out.println("Erorr al eliminar el proyecto");
		} finally {
			if (hb.getTransaction().isActive()) {
				hb.getTransaction().rollback();
			}
		}
		return null;
	}

}