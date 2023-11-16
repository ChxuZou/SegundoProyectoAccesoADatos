package models;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
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
@Table(name = "departamento")
@NamedQuery(name = "departamento.findAll", query = "SELECT d FROM Departamento d")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@OneToOne
	private Empleado jefe;
	
	@OneToMany
	private Set<Empleado> empleados;	

	@Override
	public boolean equals(Object o) {
		if(this==o)return true;
		if(!(o instanceof Departamento))return false;
		Departamento dep=(Departamento) o;
		return dep.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		 return Objects.hashCode(this.id);
	}

}
