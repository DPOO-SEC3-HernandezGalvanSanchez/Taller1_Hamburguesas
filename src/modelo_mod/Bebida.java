package modelo_mod;

public class Bebida implements Producto {
	
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public Bebida(String nombre, int precioBase, int calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getPrecio() {
		return precioBase;
	}
	
	public int getCalorias() {
		return calorias;
	}
	
	public String generarTextoFactura() {
		String precio = Integer.toString(getPrecio());
		String linea = nombre + "---- " + precio;
		return linea;
	}
}