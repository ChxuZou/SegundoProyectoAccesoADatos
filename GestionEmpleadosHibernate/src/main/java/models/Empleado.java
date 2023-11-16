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
@Table(name = "Empleado")
@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Double salario;
	
	@OneToOne
	private Departamento departamento;
	
	@OneToMany
	private Set<Proyecto> proyectos;
	
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

}
