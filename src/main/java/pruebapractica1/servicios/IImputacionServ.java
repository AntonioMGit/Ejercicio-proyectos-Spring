package pruebapractica1.servicios;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pruebapractica1.dtos.DatosImputacion;
import pruebapractica1.repositorios.NegocioException;

@Transactional(rollbackFor = NegocioException.class, propagation = Propagation.REQUIRED)
public interface IImputacionServ {

	public boolean imputar(DatosImputacion datos) throws NegocioException;
	
}
