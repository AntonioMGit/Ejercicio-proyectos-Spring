package pruebapractica1.entidades;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "MODELOPROYECTOS", name = "IMPUTACIONES")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Imputacion {
	@Id
	//funciona todo menos el generated value...
	//metiendo la id a la fuerza va bien (comentando la liena de generated value)
	//id to load is required for loading
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imputacionesSeq")
	//@SequenceGenerator(schema = "MODELOPROYECTOS", sequenceName = "IMPUTACIONES_CODIGO_SEQ", name = "imputacionesSeq")
	@NonNull
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(
		name = "EMPLEADO", referencedColumnName = "NIF"
	)
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(
		name = "TAREA", referencedColumnName = "CODIGO"
	)
	private Tarea tarea;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(name = "NUMERO_HORAS")
	private Integer numeroHoras;

	private String descripcion;
	
}
