package pruebapractica1.repositorios;

import pruebapractica1.entidades.Empleado;

public interface IEmpleadoRepo {
	boolean insertar(Empleado empleado) throws NegocioException;
	Empleado buscarPorId(String id) throws NegocioException;
}
