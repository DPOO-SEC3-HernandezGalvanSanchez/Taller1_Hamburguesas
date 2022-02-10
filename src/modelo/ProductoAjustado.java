package modelo;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * POR IMPLEMENTAR: generarTextoFactura()
 */

public class ProductoAjustado implements Producto
{
	ProductoMenu base;
	private LinkedList<Ingrediente> agregados;
	private LinkedList<Ingrediente> eliminados;
	
	
	public ProductoAjustado(ProductoMenu base)
	{
		this.base = base;
	}

	
	public String getNombre()
	{
		String nombre = base.getNombre();
		return nombre;
	}
	
	
	public int getPrecio()
	{
		/*
		 * Agregar ingredientes aumenta el precio
		 * Eliminar ingredientes no reduce el precio
		 */
		
		int precio = base.getPrecio();
		Iterator<Ingrediente> iter_ag = agregados.iterator();
		
		while (iter_ag.hasNext())
		{
			Ingrediente i_agregado = iter_ag.next();
			precio += i_agregado.getCostoAdicional();
		}
		
		return precio;
	}
	
	
	public String generarTextoFactura()
	{
		/*
		 *"Las facturas de los pedidos deben discriminar
		 * el valor de cada elemento, el valor neto total,
		 * el IVA (19%) y el valor total (neto + IVA)."
		 *  
		 * POR IMPLEMENTAR
		 */
		
		String factura = "N/A";
		return factura;
	}
}
