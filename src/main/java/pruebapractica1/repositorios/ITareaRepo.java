package pruebapractica1.repositorios;

import pruebapractica1.entidades.Proyecto;
import pruebapractica1.entidades.Tarea;

public interface ITareaRepo {
	boolean insertar(Tarea tarea) throws NegocioException;
	Tarea buscarPorId(Integer id) throws NegocioException;
}
