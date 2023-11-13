package models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
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
	private Integer id;
	private String nombre;
	private Empleado jefe;
	// lista de departamentos
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
		return this.id;
	}

}
