package models;

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
import jakarta.persistence.Transient;
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
	
	@OneToOne(targetEntity = Empleado.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Empleado jefe;
	
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER) 
	@Transient
	private Set<Empleado> empleados;

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
		return "Departamento [id=" + id + ", nombre=" + nombre + ", jefe=" + jefe+"\n";
	}

}
