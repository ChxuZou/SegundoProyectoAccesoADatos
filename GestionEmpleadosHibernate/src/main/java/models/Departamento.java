package models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Departamento")
@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@OneToOne(targetEntity = Empleado.class, cascade = CascadeType.MERGE)
	private Empleado jefe;
	
	public void setJefeYDepartamento(Empleado jefe) {
		this.jefe = jefe;
		this.jefe.setDepartamento(this);
	}
	
	@Builder.Default
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER, targetEntity = Empleado.class, cascade = CascadeType.MERGE) 
	private Set<Empleado> empleados = new HashSet<>();
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		Departamento dep=(Departamento) o;
		return dep.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		 return Objects.hashCode(this.id);
	}

	@Override
	public String toString() {
		return "id: " + this.getId() + ", nombre: " + this.getNombre() + ", jefe: " +(this.jefe!=null?"[id: " + jefe.getId()+", nombre: "+jefe.getNombre()+"]\n":null+"\n");
	}

}
