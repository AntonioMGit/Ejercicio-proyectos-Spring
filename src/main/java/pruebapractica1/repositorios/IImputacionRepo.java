package pruebapractica1.repositorios;

import pruebapractica1.entidades.Imputacion;

public interface IImputacionRepo {
	boolean insertar(Imputacion imputacion) throws NegocioException;
	Imputacion buscarPorId(Integer id) throws NegocioException;
}
