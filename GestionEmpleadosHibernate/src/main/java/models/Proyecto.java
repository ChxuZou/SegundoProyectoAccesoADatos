package models;

import java.util.Objects;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
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
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@OneToMany
	private Set<Empleado> empleados;
	
	
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
}
