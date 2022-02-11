package modelo;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * FALTA DEFINIR COMO AGREGAR ELEMENTOS A LAS LISTAS DE
 * AGREGADOS Y ELIMINADOS
 */

public class ProductoAjustado implements Producto
{
	private ProductoMenu base;
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
		String nombre = getNombre();
		String precio_base = Integer.toString(base.getPrecio());
		String precio_total = Integer.toString(getPrecio());
		String linea = "\nPRODUCTO AJUSTADO";
		linea += "\n" + "Producto Base: " + nombre + " -- " + precio_base;
		
		
		Iterator<Ingrediente> iter_ag = agregados.iterator();
		Iterator<Ingrediente> iter_el = eliminados.iterator();
		
		while (iter_ag.hasNext())
		{
			Ingrediente i_agregado = iter_ag.next();
			String ag_nombre = i_agregado.getNombre();
			String adicional = Integer.toString(i_agregado.getCostoAdicional());
			linea += "\n  Adicion de " + ag_nombre + " --- " + adicional;
		}
		
		while (iter_el.hasNext())
		{
			Ingrediente i_eliminado = iter_el.next();
			String el_nombre = i_eliminado.getNombre();
			linea += "\n  Sin " + el_nombre;
		}
		
		linea += "\nTotal " + nombre + " ----- " + precio_total;
		
		return linea;
	}
}
