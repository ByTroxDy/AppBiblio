package app;

public class Socios {
	
	protected String nombre;
	protected String passwd;
	
	public Socios(String nombre, String passwd) {
		this.nombre = nombre;
		this.passwd = passwd;
	}
	
	public boolean editarPerfil(String nombre, String passwd) {
		
		try {
			/* .,mz.mcz.xm*/
			/* Obrir connexió BD */
			
			/* Modificar les BD --> UPDATE */
			
			this.nombre = nombre;
			this.passwd = passwd;
			// UPDATE 
			
			return true;
		} catch(Exception ex) {
			return false;
		}
		
	}

}
