package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* 
 * Desviacion del modelo en getMenuBase(), getIngredientes()
 */

public class Restaurante
{
	private ArrayList<Pedido> pedidos;
	private int pedidoEnCurso; 
	private HashMap<String, ProductoMenu> menuBase;
	private HashMap<String, Ingrediente> ingredientes;
	private HashMap<String, Combo> combos;
	
	public Restaurante()
	{
		pedidos = new ArrayList<Pedido>();
		menuBase = new HashMap<String, ProductoMenu>();
		ingredientes = new HashMap<String, Ingrediente>();
		combos = new HashMap<String, Combo>();	
	}
	
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		pedidoEnCurso = 1;
		Pedido elPedido = new Pedido(nombreCliente, direccionCliente);
		pedidos.add(elPedido);		
	}
	
	
	public void cerrarYGuardarPedido()
	{
		pedidoEnCurso = 0;
	}
	
	
	public Pedido getPedidoEnCurso()
	{
		return pedidos.get(pedidos.size() - 1);
	}
	
	
	public HashMap<String, ProductoMenu> getMenuBase()
	{
		return menuBase;
	}
	
	
	public HashMap<String, Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	
	
	public void cargarInformacionRestaurante(File archivoIngredientes,
											 File archivoMenu,
											 File archivoCombos)
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	
	private void cargarIngredientes(File archivoIngredientes)
	{
		
	}
	
	
	private void cargarMenu(File archivoMenu)
	{
		
	}
	
	
	private void cargarCombos(File archivoCombos)
	{
		
	}
}
