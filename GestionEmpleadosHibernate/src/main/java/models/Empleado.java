package models;

import jakarta.persistence.Entity;
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
public class Empleado {
	private Integer id;
	private String nombre;
	private double salario;
	private Departamento departamento;

}
