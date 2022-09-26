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
import pruebapractica1.entidades.Proyecto;
import pruebapractica1.entidades.Tarea;
import pruebapractica1.repositorios.IEmpleadoRepo;
import pruebapractica1.repositorios.IProyectoRepo;
import pruebapractica1.repositorios.ITareaRepo;

@RestController
@RequestMapping("/tareas")
public class ControladorTareas {

	@Autowired
	ITareaRepo repoTarea;
	
	@GetMapping(
			path = "",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String pruebaOk() {
		return "Hola mundo - tareas";
	}
	
	@PostMapping(
		path = "/insertar",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> insertar(@RequestBody Tarea tarea) {
		
		try {
			boolean exito = repoTarea.insertar(tarea);
			
			if(exito)
				return ResponseEntity.ok(tarea);
			else
				return ResponseEntity.badRequest().build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping(
		path = "/buscar/{cod}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> buscarPorNif(
			@PathVariable("cod")
			Integer cod
	) {
		try {
			Tarea tarea = repoTarea.buscarPorId(cod);
			if(tarea == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(tarea);					
		} catch (Exception e) {	
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();			
		}
	}
}
