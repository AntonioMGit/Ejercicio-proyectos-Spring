package pruebapractica1.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pruebapractica1.entidades.Imputacion;

@Repository
@Transactional(rollbackFor = NegocioException.class)
public class ImputacionRepo implements IImputacionRepo{

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean insertar(Imputacion imputacion) throws NegocioException {

		try {
			Imputacion encontrado = em.find(Imputacion.class, imputacion.getCodigo());
			
			if(encontrado!=null) {
				return false;
			}else {
				em.persist(imputacion);
				return true;
			}
		} catch (Exception e) {
			throw new NegocioException("Error al insertar Imputacion", e);
		}
	}

	@Override
	public Imputacion buscarPorId(Integer id) throws NegocioException {
		try {
			return em.find(Imputacion.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("No existe el Imputacion", e);
		}
	}

	
}
