package app;

import java.time.LocalDate;

public class Reserva {
	private int idDocumento;
	private String tituloDocumento;
	private String tipoDocumento;
	private LocalDate fechaReserva;

	public Reserva(int idDocumento, String tituloDocumento, String tipoDocumento, LocalDate fechaReserva) {
		this.idDocumento = idDocumento;
		this.tituloDocumento = tituloDocumento;
		this.tipoDocumento = tipoDocumento;
		this.fechaReserva = fechaReserva;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
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
