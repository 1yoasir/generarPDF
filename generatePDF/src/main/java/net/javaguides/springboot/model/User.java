package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name  = "name")
	private String nombre;
	
	@Column(name  = "document")
	private String dni;
	
	@Column(name = "center")
	private String centro;
	
	@Column(name = "language")
	private String idiomas;
	
	@Column(name = "telephone")
	private int telefono;
	
	@Column(name =  "province")
	private String provincia;
}
