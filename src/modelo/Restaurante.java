package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/* 
 * Metodo adicional: getCombos()
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
	
	
	public ArrayList<Producto> getMenuBase()
	{
		ArrayList<Producto> newList = new ArrayList<>(menuBase.values());
		return newList;
	}
	
	
	public ArrayList<Producto> getCombos()
	{
		ArrayList<Producto> newList = new ArrayList<>(combos.values());
		return newList;
	}
	
	
	public ArrayList<Ingrediente> getIngredientes()
	{
		ArrayList<Ingrediente> newList = new ArrayList<>(ingredientes.values());
		return newList;
	}
	
	
	public void cargarInformacionRestaurante(File archivoIngredientes,
											 File archivoMenu,
											 File archivoCombos) throws FileNotFoundException
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	
	private void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoIngredientes);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreIngrediente = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			
			Ingrediente ingr = new Ingrediente(nombreIngrediente, costoAdicional);
			ingredientes.put(nombreIngrediente, ingr);
		}
		Reader.close();
	}
	
	
	private void cargarMenu(File archivoMenu) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoMenu);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreProducto = partes[0];
			int precio = Integer.parseInt(partes[1]);
			
			ProductoMenu Producto = new ProductoMenu(nombreProducto, precio);
			menuBase.put(nombreProducto, Producto);
		}
		Reader.close();
	}
	
	
	private void cargarCombos(File archivoCombos) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoCombos);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreCombo = partes[0];
			double descuento = Double.parseDouble(partes[1].replace("%", ""));

			Combo combo = new Combo(nombreCombo, descuento);
			
			for(int i=2; i<partes.length ; i++) {
				String nombreProducto = partes[i];
				ProductoMenu nProducto = menuBase.get(nombreProducto);
				combo.agregarItemACombo(nProducto);
			}
			combos.put(nombreCombo, combo);
		}
		Reader.close();
	}
}
