package pruebapractica1.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pruebapractica1.entidades.Proyecto;

@Repository
@Transactional(rollbackFor = NegocioException.class)
public class ProyectoRepo implements IProyectoRepo{

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean insertar(Proyecto proyecto) throws NegocioException {

		try {
			Proyecto encontrado = em.find(Proyecto.class, proyecto.getCodigo());
			
			if(encontrado!=null) {
				return false;
			}else {
				em.persist(proyecto);

				return true;
			}
		} catch (Exception e) {
			throw new NegocioException("Error al insertar Proyecto", e);
		}
	}

	@Override
	@Transactional(readOnly=false)
	public Proyecto buscarPorId(Integer id) throws NegocioException {
		try {
			return em.find(Proyecto.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("No existe el Proyecto", e);
		}
	}

	
}
