package models;

import jakarta.persistence.Entity;
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
public class Proyecto {
	private Integer id;
	private String nombre;
}
