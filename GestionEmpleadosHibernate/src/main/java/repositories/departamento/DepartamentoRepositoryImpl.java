package repositories.departamento;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import db.HibernateManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import models.Departamento;

public class DepartamentoRepositoryImpl implements DepartamentoRepository {
	private final Logger logger = Logger.getLogger(DepartamentoRepositoryImpl.class.getName());

	@Override
	public List<Departamento> findAll() {
		logger.info("Buscando todos los departamentos");
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
	@Transactional
	public boolean save(Departamento entity) {
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
	@Transactional
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