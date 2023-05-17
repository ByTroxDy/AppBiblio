package app;

import java.time.LocalDate;

public class Reserva {
	private String usuario;
	private String tituloDocumento;
	private String tipoDocumento;
	private LocalDate fechaReserva;

	public Reserva(String usuario, String tituloDocumento, String tipoDocumento, LocalDate fechaReserva) {
		this.usuario = usuario;
		this.tituloDocumento = tituloDocumento;
		this.tipoDocumento = tipoDocumento;
		this.fechaReserva = fechaReserva;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTituloDocumento() {
		return tituloDocumento;
	}

	public void setTituloDocumento(String tituloDocumento) {
		this.tituloDocumento = tituloDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
}
