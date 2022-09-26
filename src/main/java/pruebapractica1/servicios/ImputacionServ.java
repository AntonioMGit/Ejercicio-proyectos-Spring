package pruebapractica1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pruebapractica1.dtos.DatosImputacion;
import pruebapractica1.entidades.Empleado;
import pruebapractica1.entidades.Imputacion;
import pruebapractica1.entidades.Tarea;
import pruebapractica1.repositorios.IEmpleadoRepo;
import pruebapractica1.repositorios.IImputacionRepo;
import pruebapractica1.repositorios.ITareaRepo;
import pruebapractica1.repositorios.NegocioException;

@Service
public class ImputacionServ implements IImputacionServ{

	@Autowired
	IEmpleadoRepo repoEmpleado;
	
	@Autowired
	ITareaRepo repoTarea;
	
	@Autowired
	IImputacionRepo repoImputacion;
	
	@Override
	public boolean imputar(DatosImputacion datos) throws NegocioException {
		try {
			boolean exito = false;
			Empleado empleado = repoEmpleado.buscarPorId(datos.getEmpleado());
			Tarea tarea = repoTarea.buscarPorId(datos.getTarea());
			
			if(empleado != null && tarea != null) {
				//comprobación de que el empleado tenía asignada esa tarea
				if(empleado.getTareas().contains(tarea)) {
					//comprobando que la fecha de imputación está dentro del plazo de fecha estimada para la tarea.
					if(tarea.getFechaInicio().after(datos.getFecha())&&
							tarea.getFechaInicio().before(datos.getFecha())) {
						Imputacion imputacion = new Imputacion();
						//imputacion.setCodigo(datos.getCodigo());
						imputacion.setEmpleado(empleado);
						imputacion.setTarea(tarea);
						imputacion.setNumeroHoras(datos.getNumHoras());
						imputacion.setDescripcion(datos.getDescripcion());
						
						repoImputacion.insertar(imputacion);
						
						exito = true;
					}
				}
			}
			
			return exito;
		} catch (Exception e) {
			throw new NegocioException("No se ha podido imputar", e);
		}
	}
}
