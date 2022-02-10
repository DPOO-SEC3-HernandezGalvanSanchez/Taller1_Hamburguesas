package modelo;

import java.util.LinkedList;

public class Pedido
{
	public static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private LinkedList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
	}
	
	public int getIdPedido()
	{
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem)
	{
		itemsPedido.add(nuevoItem);
	}
	
	
	
}
