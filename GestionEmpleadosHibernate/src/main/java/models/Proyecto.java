package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Proyecto")
@NamedQuery(name = "Proyecto.findAll", query = "SELECT d FROM Proyecto d")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proyecto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Empleado.class)
    @JoinTable(
        name = "proyecto_empleado",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
	private Set<Empleado> empleados = new HashSet<Empleado>();
	
	public void addEmpleado(Empleado empleado) {
		if(empleado!=null) {
			this.getEmpleados().add(empleado);
			empleado.getProyectos().add(this);
		}
		
	}
	
	public void removeEmpleado(Empleado empleado) {
		if(empleado != null) {
			this.getEmpleados().remove(empleado);
			empleado.getProyectos().remove(this);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto pro = (Proyecto) obj;
		return pro.getId().equals(this.getId());
	}
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
	@Override
	public String toString() {
		return "id: " + this.getId() + ", nombre: " + this.getNombre();
	}
}
