package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Empleado")
@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Double salario;
	
	@ManyToOne(targetEntity=Departamento.class, cascade = CascadeType.MERGE)
	//@JoinColumn(name = "departamento_FK")
	private Departamento departamento;
	
	public void setDepartamentoRecursivo(Departamento departamento) {
		this.departamento = departamento;
		this.departamento.addEmpleado(this);
	}
	
	public void addProyectoRecursivo(Proyecto proyecto) {
		this.getProyectos().add(proyecto);
		proyecto.getEmpleados().add(this);
	}
	
	public void removeProyectoRecursivo(Proyecto proyecto) {
		this.getProyectos().remove(proyecto);
		proyecto.getEmpleados().remove(this);
	}
	
	@Builder.Default
	@ManyToMany(mappedBy = "empleados", fetch = FetchType.EAGER, targetEntity = Proyecto.class)
	private Set<Proyecto> proyectos = new HashSet<>();
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado emp = (Empleado) obj;
		return emp.getId().equals(this.getId());
	}
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
	@Override
	public String toString() {
		return "id=" + this.getId()+", nombre: "+this.getNombre() +", salario=" + this.getSalario() + ", departamento: "+ (departamento!=null ? "[id: "+this.departamento.getId()+", nombre: "+this.departamento.getNombre()+"]" : null);
	}

}
