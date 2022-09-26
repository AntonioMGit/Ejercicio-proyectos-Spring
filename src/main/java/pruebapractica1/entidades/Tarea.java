package pruebapractica1.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "MODELOPROYECTOS", name = "TAREAS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Tarea {
	@Id
	//funciona todo menos el generated value...
	//metiendo la id a la fuerza va bien (comentando la liena de generated value)
	//id to load is required for loading
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tareasSeq")
	//@SequenceGenerator(schema = "MODELOPROYECTOS", sequenceName = "TAREAS_CODIGO_SEQ", name = "tareasSeq")
	//@NonNull
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(
		name = "PROYECTO", referencedColumnName = "CODIGO"
	)
	private Proyecto proyecto;
	
	private String nombre;
	
	@Column(name = "FECHA_INICIO")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@Column(name = "HORAS_ESTIMADAS")
	private Integer horasEstimadas;
	
	@Column(name = "HORAS_REALIZADAS")
	private Integer horasRealizadas;
	
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "tarea")
	@ToString.Exclude
	@JsonIgnore
	private Set<Imputacion> imputaciones = new HashSet<Imputacion>();
	
	@ManyToMany
	@JoinTable(
		schema = "CAMPUS", name = "ASIGNACIONES",
		joinColumns = {
			@JoinColumn(name = "TAREA", referencedColumnName = "CODIGO"),
		},
		inverseJoinColumns = {
			@JoinColumn(name = "EMPLEADO", referencedColumnName = "NIF")	
		}
	)
	@ToString.Exclude
	@JsonIgnore
	private Set<Empleado> empleados;
	
}
