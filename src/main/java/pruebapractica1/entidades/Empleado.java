package pruebapractica1.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(schema = "MODELOPROYECTOS", name = "EMPLEADOS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Empleado {
	@Id
	@NonNull
	private String nif;
	
	private String nombre;
	
	private String apellidos;
	
	private String correo;
	
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "empleado")
	@ToString.Exclude
	@JsonIgnore
	private Set<Imputacion> imputaciones = new HashSet<Imputacion>();
	
	@ManyToMany(mappedBy = "empleados")
	@ToString.Exclude
	@JsonIgnore
	private Set<Tarea> tareas = new HashSet<Tarea>();

}
