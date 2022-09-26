package pruebapractica1.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pruebapractica1.entidades.Empleado;

@Repository
@Transactional(rollbackFor = NegocioException.class)
public class EmpleadoRepo implements IEmpleadoRepo{

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean insertar(Empleado empleado) throws NegocioException {

		try {
			Empleado encontrado = em.find(Empleado.class, empleado.getNif());
			
			if(encontrado!=null) {
				return false;
			}else {
				em.persist(empleado);
				return true;
			}
		} catch (Exception e) {
			throw new NegocioException("Error al insertar empleado", e);
		}
	}

	@Override
	public Empleado buscarPorId(String id) throws NegocioException {
		try {
			return em.find(Empleado.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException("No existe el empleado", e);
		}
	}

	
}
