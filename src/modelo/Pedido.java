package modelo;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * POR IMPLEMENTAR: guardarFactura()
 * Desviacion del modelo en getPrecioIVAPedido() y getPrecioTotalPedido()
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
	
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
	
	
	private int getPrecioNetoPedido()
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
	
	
	private int getPrecioIVAPedido(int precio_neto)
	{
		/*
		 * SE RECIBE EL PRECIO NETO POR PARAMETRO PARA NO RECALCULARLO
		 * CAMBIO DE TIPO DE RETORNO DE INT A DOUBLE?
		 * REDONDEAR RESPUESTA?
		 */
		
		float iva = precio_neto*0.19F;
		
		return Math.round(iva);
	}
	
	
	private int getPrecioTotalPedido(int precio_neto, int precio_IVA)
	{
		/*
		 * SE RECIBEN PRECIO NETO E IVA POR PARAMETRO
		 * PARA NO RECALCULARLOS
		 */
		return precio_neto + precio_IVA;
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
		
		int p_neto = getPrecioNetoPedido();
		int p_iva = getPrecioIVAPedido(p_neto);
		int precio_total = getPrecioTotalPedido(p_neto, p_iva);
		
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
		
		
		return factura;
	}
	
	
	public void guardarFactura(File archivo)
	{
		String factura = generarTextoFactura();
	}
	
}
