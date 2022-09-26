package pruebapractica1.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pruebapractica1.entidades.Proyecto;
import pruebapractica1.entidades.Tarea;

@Repository
@Transactional(rollbackFor = NegocioException.class)
public class TareaRepo implements ITareaRepo{

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean insertar(Tarea tarea) throws NegocioException {

		try {
			Tarea encontrado = em.find(Tarea.class, tarea.getCodigo());
			
			if(encontrado!=null) {
				return false;
			}else {
				em.persist(tarea);
				return true;
			}
		} catch (Exception e) {
			throw new NegocioException("Error al insertar Tarea", e);
		}
	}

	@Override
	public Tarea buscarPorId(Integer id) throws NegocioException {
		try {
			return em.find(Tarea.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("No existe el Tarea", e);
		}
	}

	
}
