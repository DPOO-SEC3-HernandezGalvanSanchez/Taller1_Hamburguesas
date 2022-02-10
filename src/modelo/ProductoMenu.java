package modelo;

/*
 * POR IMPLEMENTAR: generarTextoFactura()
 */

public class ProductoMenu implements Producto
{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	
	public String getNombre()
	{
		return nombre;
	}

	
	public int getPrecio()
	{
		return precioBase;
	}	
	
	
	public String generarTextoFactura()
	{
		//POR IMPLEMENTAR
		
		String factura = "N/A";
		return factura;
	}
}
