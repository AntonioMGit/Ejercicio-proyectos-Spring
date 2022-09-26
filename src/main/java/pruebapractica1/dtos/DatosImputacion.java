package pruebapractica1.dtos;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pruebapractica1.entidades.Imputacion;
import pruebapractica1.entidades.Tarea;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DatosImputacion {
	//private Integer codigo;
	private Integer tarea;
	private String empleado;
	private Integer numHoras;
	private Date fecha;
	private String descripcion;
}
