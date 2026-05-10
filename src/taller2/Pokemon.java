package taller2;

public class Pokemon 
{
	private String nombre;
	private String habitat;
	private double porcentaje;
	private int totalStats;
	private String tipo;
	private boolean vivo = true;
	
	public Pokemon(String nombre, String habitat, double porcentaje, int totalStats, String tipo) {
		this.nombre = nombre;
		this.habitat = habitat;
		this.porcentaje = porcentaje;
		this.totalStats = totalStats;
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getHabitat() {
		return habitat;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public int getTotalStats() {
		return totalStats;
	}
	public String getTipo() {
		return tipo;
	}
	public String getEstado() {
		if (this.vivo) {
			
			return "vivo";
		}else {
			
			return "Muerto";
		}
	}
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	@Override 
	public String toString()
	{
		return this.nombre + "|" + this.tipo + "|" + this.totalStats + "|" + this.getEstado();
	}
	
	public boolean isVivo() {
		return vivo;
	}
	
	

	
	
}
