package consola_mod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import modelo_mod.Bebida;
import modelo_mod.Ingrediente;
import modelo_mod.Pedido;
import modelo_mod.Producto;
import modelo_mod.Restaurante;

public class Aplicacion
{
	private Restaurante restaurante;
	
	
	public void ejecutarOpcion()
	{
		System.out.println("Restaurante El Corral\n");

		boolean continuar = true;
		cargarInformacion();
		
		while (continuar)
		{
			try
			{
				mostrarOpciones();
				
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				
				if (opcion_seleccionada == 1)
					iniciarPedido();
				else if (opcion_seleccionada == 2)
					mostrarMenu();
				else if (opcion_seleccionada == 3)
					agregarElemento();
				else if (opcion_seleccionada == 4)
					cerrarPedido();
				else if (opcion_seleccionada == 5)
					consultarInformacion();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicacion ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opcion valida.");
				}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los numeros de las opciones.");
		}
			
		}
	}
	
	
	public void mostrarOpciones()
	{
		System.out.println("\n--------------------------------");
		System.out.println("Opciones de la aplicacion\n");
		System.out.println("1. Iniciar un nuevo pedido");
		System.out.println("2. Mostrar el menu del restaurante");
		System.out.println("3. Agregar un producto al pedido");
		System.out.println("4. Cerrar el pedido y guardar la factura");
		System.out.println("5. Consultar la informacion de un pedido antiguo dado su id");
		System.out.println("6. Salir de la aplicacion\n");
	}
	
	
	public void cargarInformacion()
	{
		System.out.println("Cargando archivos...");
		restaurante = new Restaurante();
		File ingredientes = new File("./data/ingredientes_calorias.txt");
		File menu = new File("./data/menu_calorias.txt"); 
		File combos = new File("./data/combos.txt");
		File bebidas = new File("./data/bebidas.txt");
		try {
			restaurante.cargarInformacionRestaurante(ingredientes,menu,combos,bebidas);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
// 1. Mostrar el menu
	public void mostrarMenu()
	{
		System.out.println("\nA continuacion, el menu base:");
		ArrayList<Producto> menuBase = restaurante.getMenuBase();
		int numProductos = menuBase.size();
		
		for (int i1 = 0; i1<numProductos; i1++)
		{
			Producto elProducto = menuBase.get(i1);
			String nombreProducto = elProducto.getNombre();
			System.out.println("P" + i1 + ": " + nombreProducto);
		}
		
		
		System.out.println("\nA continuacion, los combos:");
		ArrayList<Producto> combos = restaurante.getCombos();
		int numCombos = combos.size();
		
		for (int i2 = 0; i2<numCombos; i2++)
		{
			Producto elCombo = combos.get(i2);
			String nombreCombo = elCombo.getNombre();
			System.out.println("C" + i2 + ": " + nombreCombo);
		}
		
		
		System.out.println("\n A continuacion, las bebidas disponibles:");
		ArrayList<Producto> bebidas = restaurante.getBebidas();
		int numBebidas = bebidas.size();
		
		for (int i3= 0; i3<numBebidas; i3++)
		{
			Producto laBebida = bebidas.get(i3);
			String nombreBebida = laBebida.getNombre();
			System.out.println("B"+i3+": "+nombreBebida);
		}
		/*
		System.out.println("\nA continuacion, los ingredientes:");
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		int numIngredientes = ingredientes.size();
		
		for (int i3 = 0; i3<numIngredientes; i3++)
		{
			Ingrediente elIngrediente = ingredientes.get(i3);
			String nombreIngrediente = elIngrediente.getNombre();
			System.out.println("I" + i3 + ": " + nombreIngrediente);
		}
		*/
	}
	
	
	
// 2. Iniciar un nuevo pedido	
	public void iniciarPedido()
	{
		String nombreCliente = input("Ingrese el nombre del cliente ");
		String direccionCliente = input("Ingrese la direccion del cliente ");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
	}
	
	
	
// 3. Agregar un elemento al pedido
	public void agregarElemento()
	{
		Pedido pedidoActual = restaurante.getPedidoEnCurso();
		ArrayList<Producto> menuBase = restaurante.getMenuBase();
		ArrayList<Producto> combos = restaurante.getCombos();
		ArrayList<Producto> bebidas = restaurante.getBebidas();
		//ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		
		String numProducto = input("Ingrese el numero del producto que desea agregar como aparece en el menu");
		String typeIndex = numProducto.substring(0,1);
		Integer lstIndex = Integer.parseInt(numProducto.substring(1));
		
		if (typeIndex.equals("P"))
		{
			Producto elProducto = menuBase.get(lstIndex);
			pedidoActual.agregarProducto(elProducto);
			
			System.out.println("El producto '" + elProducto.getNombre() + "' fue agregado al pedido");
		}
		
		else if (typeIndex.equals("C"))
		{
			Producto elCombo = combos.get(lstIndex);
			pedidoActual.agregarProducto(elCombo);
			
			System.out.println("El combo '" + elCombo.getNombre() + "' fue agregado al pedido");
		}
		
		else if (typeIndex.equals("B"))
		{
			Producto laBebida = bebidas.get(lstIndex);
			pedidoActual.agregarProducto(laBebida);
			System.out.println("La bebida '" + laBebida.getNombre()+ "' fue agregada al pedido");
		}
	}
	
	
	
// 	4. Cerrar un pedido y guardar la factura
	public void cerrarPedido()
	{
		Pedido pedidoActual = restaurante.getPedidoEnCurso();
		File factura = new File("./pedidos/"+ pedidoActual.getIdPedido() + ".txt");
		try {
			factura.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pedidoActual.guardarFactura(factura);
		restaurante.cerrarYGuardarPedido();
		System.out.println("Archivo creado");
	}
	
	
	
// 5. Consultar la informacion de un pedido dado su id
	public void consultarInformacion()
	{
		Scanner leer = new Scanner(System.in);
		System.out.println("Escribe el id del pedido: ");
		int id = leer.nextInt();
		ArrayList<Pedido> pedidos = restaurante.consultarPedidos();
		boolean elementoExiste = false;
		
		Iterator<Pedido> nombreIterator = pedidos.iterator();
		while(nombreIterator.hasNext()){
			Pedido elemento = nombreIterator.next();
			if (id == elemento.getIdPedido()) {
				String items = "";
				
				LinkedList<Producto> listaProductos = elemento.getItemsPedido();
				Iterator<Producto> iter_pedidos = listaProductos.iterator();
				while(iter_pedidos.hasNext())
				{
					Producto unProducto = iter_pedidos.next();
					
					if (listaProductos.indexOf(unProducto)>0)
						items += ", ";
					items += unProducto.getNombre();
				}
				
				System.out.println("id: " + elemento.getIdPedido());
				System.out.println("Cliente: " + elemento.getNombreCliente());
				System.out.println("Direccion: " + elemento.getDireccionCliente());
				System.out.println("Items Pedido: " + items);
				System.out.println("\nPara mas informacion busque la factura en la carpeta data que tenga como nombre el id");
				elementoExiste = true;
				break;
				
			}
		}
		if (elementoExiste == false) {
			System.out.println("No se encontro ningun pedido con el id especificado");
			}
		leer.close();
	}
	
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args)
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}

}