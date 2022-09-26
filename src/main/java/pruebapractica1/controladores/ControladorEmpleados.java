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

import pruebapractica1.entidades.Empleado;
import pruebapractica1.repositorios.IEmpleadoRepo;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleados {

	@Autowired
	IEmpleadoRepo repoEmpleado;
	
	@GetMapping(
			path = "",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String pruebaOk() {
		return "Hola mundo";
	}
	
	@PostMapping(
		path = "/insertar",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> insertar(@RequestBody Empleado empleado) {
		
		try {
			boolean exito = repoEmpleado.insertar(empleado);
			
			if(exito)
				return ResponseEntity.ok(empleado);
			else
				return ResponseEntity.badRequest().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping(
		path = "/buscar/{nif}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> buscarPorNif(
			@PathVariable("nif")
			String nif
	) {
		try {
			Empleado empleado = repoEmpleado.buscarPorId(nif);
			if(empleado == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(empleado);					
		} catch (Exception e) {	
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();			
		}
	}
}
