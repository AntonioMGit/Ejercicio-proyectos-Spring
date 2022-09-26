package pruebapractica1.repositorios;

import pruebapractica1.entidades.Proyecto;

public interface IProyectoRepo {
	boolean insertar(Proyecto proyecto) throws NegocioException;
	Proyecto buscarPorId(Integer id) throws NegocioException;
}
