package modelo_mod;

import java.util.Iterator;
import java.util.LinkedList;


public class Combo implements Producto
{
	private String nombreCombo;
	private double descuento;
	private LinkedList<ProductoMenu> itemsCombo;
	private Bebida bebida;
	
	public Combo(String nombre, double descuento)
	{
		this.nombreCombo = nombre;
		this.descuento = descuento;
		itemsCombo = new LinkedList<ProductoMenu>();
	}
	
	
	public void agregarItemACombo(ProductoMenu itemCombo)
	{
		itemsCombo.add(itemCombo);
	}
	
	
	public void agregarBebidaACombo(Bebida bebida)
	{
		this.bebida = bebida;
	}
	public String getNombre()
	{
		return nombreCombo;
	}

	
	public int getPrecio()
	{		
		int precio = 0;
		Iterator<ProductoMenu> iter_items = itemsCombo.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			precio += item.getPrecio();
		}
		precio += bebida.getPrecio();
		
		double con_descuento = precio - (precio*descuento);
		float precio_final = (float) con_descuento;
		
		return Math.round(precio_final);
	}	
	
	
	public int getCalorias()
	{
		int calorias = 0;
		Iterator<ProductoMenu> iter_items = itemsCombo.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			calorias += item.getCalorias();
		}
		calorias += bebida.getPrecio();
		
		return calorias;
	}
	
	public String generarTextoFactura()
	{
		String precio = Integer.toString(getPrecio());
		String linea = nombreCombo + " ----- " + precio;
		
		return linea;
	}
}
