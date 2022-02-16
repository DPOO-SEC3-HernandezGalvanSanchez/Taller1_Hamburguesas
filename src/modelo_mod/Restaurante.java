package modelo_mod;

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
	private HashMap<String, Bebida> bebidas;
	
	public Restaurante()
	{
		pedidos = new ArrayList<Pedido>();
		menuBase = new HashMap<String, ProductoMenu>();
		ingredientes = new HashMap<String, Ingrediente>();
		combos = new HashMap<String, Combo>();	
		bebidas = new HashMap<String, Bebida>();
	}
	
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		boolean sePuede = !hayPedidoEnCurso();
		
		if (sePuede)
		{
			pedidoEnCurso = 1;
			Pedido elPedido = new Pedido(nombreCliente, direccionCliente);
			pedidos.add(elPedido);	
		}
	}
	
	
	public ArrayList<Pedido> consultarPedidos()
	{
		return pedidos;
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
	
	
	public ArrayList<Producto> getBebidas()
	{
		ArrayList<Producto> newList = new ArrayList<>(bebidas.values());
		return newList;
	}
	
	
	public void cargarInformacionRestaurante(File archivoIngredientes,
											 File archivoMenu,
											 File archivoCombos,
											 File archivoBebidas) throws FileNotFoundException
	{
		cargarIngredientes(archivoIngredientes);
		System.out.print("cargaron ing\n");
		cargarMenu(archivoMenu);
		System.out.print("cargaron menu\n");
		cargarBebidas(archivoBebidas);
		System.out.print("cargaron bebidas\n");
		cargarCombos(archivoCombos);
		System.out.print("cargaron combos\n");
	}
	
	
	private void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoIngredientes);
		while (Reader.hasNextLine()) {
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreIngrediente = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			int calorias = Integer.parseInt(partes[2]);
			
			Ingrediente ingr = new Ingrediente(nombreIngrediente, costoAdicional, calorias);
			ingredientes.put(nombreIngrediente, ingr);
		}
		Reader.close();
	}
	
	
	private void cargarMenu(File archivoMenu) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoMenu);
		while (Reader.hasNextLine()) 
		{
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreProducto = partes[0];
			int precio = Integer.parseInt(partes[1]);
			int calorias = Integer.parseInt(partes[2]);
			
			ProductoMenu Producto = new ProductoMenu(nombreProducto, precio, calorias);
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
			descuento = descuento/100;

			Combo combo = new Combo(nombreCombo, descuento);
			
			for(int i=2; i<(partes.length-1) ; i++) {
				String nombreProducto = partes[i];
				ProductoMenu nProducto = menuBase.get(nombreProducto);
				combo.agregarItemACombo(nProducto);
			}
			Bebida labebida = bebidas.get(partes[partes.length-1]);
			combo.agregarBebidaACombo(labebida);
			combos.put(nombreCombo, combo);
		}
		Reader.close();
	}
	
	
	private void cargarBebidas(File archivoBebidas) throws FileNotFoundException
	{
		Scanner Reader = new Scanner(archivoBebidas);
		while (Reader.hasNextLine())
		{
			String Line = Reader.nextLine();
			String[] partes = Line.split(";");
			String nombreBebida = partes[0];
			int precio = Integer.parseInt(partes[1]);
			int calorias = Integer.parseInt(partes[2]);
			
			Bebida bebida = new Bebida(nombreBebida, precio, calorias);
			bebidas.put(nombreBebida, bebida);
		}
		Reader.close();
	}
	
	
	public boolean hayPedidoEnCurso()
	{
		boolean resp = false;
		
		if (pedidoEnCurso==1)
			resp = true;

		return resp;
	}
}
