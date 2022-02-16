package modelo_mod;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * Desviacion del modelo en getPrecioIVAPedido() y
 * getPrecioTotalPedido() por los parametros que
 * reciben
 */

public class Pedido
{
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private LinkedList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		numeroPedidos ++;
		idPedido = numeroPedidos;
		itemsPedido = new LinkedList<Producto>();
	}
	
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	public String getNombreCliente() 
	{
		return nombreCliente;	
	}
	
	public String getDireccionCliente()
	{
		return direccionCliente;
	}
	
	public LinkedList<Producto> getItemsPedido()
	{
		return itemsPedido;
	}
	
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
	
	
	private int getPrecioTotalPedido()
	{
		int precio = 0;
		Iterator<Producto> iter_items = itemsPedido.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			precio += item.getPrecio();
		}
		
		return precio;
	}
	
	
	private int getPrecioNetoPedido(int precio_total)
	{
		/*
		 * SE RECIBEN PRECIO TOTAL POR PARAMETRO PARA NO
		 * RECALCULARLO
		 */
		
		float precio_neto = precio_total/1.19F;
		
		return Math.round(precio_neto);
	}
	
	
	private int getPrecioIVAPedido(int precio_neto)
	{
		/*
		 * SE RECIBE EL PRECIO NETO POR PARAMETRO PARA
		 * NO RECALCULARLO
		 */
		
		float iva = precio_neto*0.19F;
		
		return Math.round(iva);
	}
	
	
	private int getCaloriasTotal()
	{
		int calorias = 0;
		Iterator<Producto> iter_items = itemsPedido.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			calorias += item.getCalorias();
		}
		
		return calorias;
	}
	private String generarTextoFactura()
	{
		/*
		 *"Las facturas de los pedidos deben discriminar
		 * el valor de cada elemento, el valor neto total,
		 * el IVA (19%) y el valor total (neto + IVA)."
		 *  
		 * POR IMPLEMENTAR
		 */
		
		int precio_total = getPrecioTotalPedido();
		int p_neto = getPrecioNetoPedido(precio_total);
		int p_iva = getPrecioIVAPedido(p_neto);
		int calorias = getCaloriasTotal();
		
		String factura = "Cliente: " + nombreCliente;
		factura += "\nDireccion: " + direccionCliente;
		factura += "\nNumero de Pedido: " + Integer.toString(idPedido);
		factura += "\n\n-----------------------------------";
		factura += "\nPRODUCTOS";
		factura += "\n-----------------------------------";
		
		Iterator<Producto> iter_items = itemsPedido.iterator();
		
		while (iter_items.hasNext())
		{
			Producto item = iter_items.next();
			String linea = item.generarTextoFactura();
			factura += "\n" + linea;
		}
		
		factura += "\n\n-----------------------------------";
		factura += "\nNETO: " + Integer.toString(p_neto);
		factura += "\nIVA: " + Integer.toString(p_iva);
		factura += "\n-----------------------------------";
		factura += "\nTOTAL: " + Integer.toString(precio_total);	
		factura += "\nTOTAL CALORIAS: " + Integer.toString(calorias);
		
		
		return factura;
	}
	
	
	public void guardarFactura(File archivo)
	{
		String factura = generarTextoFactura();
		FileWriter fw;
		try {
			fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			 bw.write(factura);
	         bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
