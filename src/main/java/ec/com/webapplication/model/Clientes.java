package ec.com.webapplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ec.com.webapplication.globals.VariablesGlobales.Estado;

@Entity
@Table(name = "Cliente")
public class Clientes {

	private long idCliente;
	private String nombre;
	private String identificacion;
	private String direccion;
	private Date ingreso;
	private Estado estado = Estado.ACTIVO;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCliente", unique = true, nullable = false)
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Column(name="Nombre", nullable = false, length = 80)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="Identificacion", nullable = false, length = 13)
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	@Column(name="Direccion", nullable = false, length = 180)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Column(name="Ingreso", nullable = false)
	public Date getIngreso() {
		return ingreso;
	}
	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="Estado", nullable = false)
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
