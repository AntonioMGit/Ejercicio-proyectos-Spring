package pruebapractica1.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(schema = "MODELOPROYECTOS", name = "PROYECTOS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Proyecto {
	@Id
	//funciona todo menos el generated value...
	//metiendo la id a la fuerza va bien (comentando la liena de generated value)
	//id to load is required for loading
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proyectosSeq")
	//@SequenceGenerator(schema = "MODELOPROYECTOS", sequenceName = "PROYECTOS_CODIGO_SEQ", name = "proyectosSeq")
	//@NonNull
	private Integer codigo;
	
	private String nombre;
	
	@Column(name = "HORAS_ESTIMADAS")
	private Integer horasEstimadas;
	
	@Column(name = "HORAS_CONSUMIDAS")
	private Integer horasConsumidas;
	
	@Column(name = "FECHA_ESTIMADA_CIERRE")
	@Temporal(TemporalType.DATE)
	private Date fechaEstimadaCierre;
	
	@Column(name = "FECHA_CIERRE")
	@Temporal(TemporalType.DATE)
	private Date fechaCierre;
	
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "proyecto")
	@ToString.Exclude
	@JsonIgnore
	private Set<Tarea> tareas = new HashSet<Tarea>();
}
