package pruebapractica1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pruebapractica1.dtos.DatosImputacion;
import pruebapractica1.entidades.Empleado;
import pruebapractica1.repositorios.IEmpleadoRepo;
import pruebapractica1.servicios.IImputacionServ;

@RestController
@RequestMapping("/imputaciones")
public class ControladorImputaciones {

	@Autowired
	IImputacionServ servImputacion;
	
	@GetMapping(
			path = "",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String pruebaOk() {
		return "Hola mundo - imputacion";
	}
	
	@PostMapping(
		path = "/insertar",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> insertar(@RequestBody DatosImputacion datos) {
		
		try {
			boolean exito = servImputacion.imputar(datos);
			
			if(exito)
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.badRequest().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
