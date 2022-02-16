package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Ingrediente;
import modelo.Pedido;
import modelo.Producto;
import modelo.Restaurante;

public class Aplicacion
{
	private Restaurante restaurante;
	
	public void ejecutarOpcion()
	{
		System.out.println("Restaurante El Corral\n");

		boolean continuar = true;
		Pedido pedido = null;
		cargarInformacion();
		while (continuar)
		{
			try
			{
				mostrarOpciones();
				
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opcion"));
				
				if (opcion_seleccionada == 1)
					mostrarMenu();
				else if (opcion_seleccionada == 2)
				 pedido = iniciarPedido();
				else if (opcion_seleccionada == 3)
					agregarElemento();
				else if (opcion_seleccionada == 4)
					cerrarPedido(pedido);
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
		System.out.println("\nOpciones de la aplicacion\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la informacion de un pedido dado su id");
		System.out.println("6. Salir de la aplicacion\n");
	}
	
	public void cargarInformacion()
	{
		System.out.println("Cargando archivos...");
		restaurante = new Restaurante();
		File ingredientes = new File("./data/ingredientes.txt");
		File menu = new File("./data/menu.txt"); 
		File combos = new File("./data/combos.txt");
		try {
			restaurante.cargarInformacionRestaurante(ingredientes,menu,combos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// 1. Mostrar el menu
	public void mostrarMenu()
	{
		System.out.println("A continuacion, el menu base:");
		ArrayList<Producto> menuBase = restaurante.getMenuBase();
		int numProductos = menuBase.size();
		
		for (int i1 = 0; i1<numProductos; i1++)
		{
			Producto elProducto = menuBase.get(i1);
			String nombreProducto = elProducto.getNombre();
			System.out.println("P" + i1 + ": " + nombreProducto);
		}
		
		
		System.out.println("A continuacion, los combos:");
		ArrayList<Producto> combos = restaurante.getCombos();
		int numCombos = combos.size();
		
		for (int i2 = 0; i2<numCombos; i2++)
		{
			Producto elCombo = combos.get(i2);
			String nombreCombo = elCombo.getNombre();
			System.out.println("C" + i2 + ": " + nombreCombo);
		}
		
		
		System.out.println("A continuacion, los ingredientes:");
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		int numIngredientes = ingredientes.size();
		
		for (int i3 = 0; i3<numIngredientes; i3++)
		{
			Ingrediente elIngrediente = ingredientes.get(i3);
			String nombreIngrediente = elIngrediente.getNombre();
			System.out.println("I" + i3 + ": " + nombreIngrediente);
		}
	}
	
	
// 2. Iniciar un nuevo pedido	
	public Pedido iniciarPedido()
	{
		String nombreCliente = input("Ingrese el nombre del cliente ");
		String direccionCliente = input("Ingrese la direccion del cliente ");
		Pedido pedidoActual = restaurante.iniciarPedido(nombreCliente, direccionCliente);
		
		return pedidoActual;
	}
	
	
	
// 3. Agregar un elemento a un pedido
	public void agregarElemento()
	{
		//Pedido pedidoActual = restaurante.getPedidoEnCurso();
		ArrayList<Producto> menuBase = restaurante.getMenuBase();
		ArrayList<Producto> combos = restaurante.getCombos();
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		
		String numProducto = input("Ingrese el numero del producto que desea agregar como aparece en el menu: ");
		String typeIndex = numProducto.substring(0,1);
		Integer lstIndex = Integer.parseInt(numProducto.substring(1));
		
		if (typeIndex.equals("P"))
		{
			Producto elProducto = menuBase.get(lstIndex);
		}
	}
	
	
// 	4. Cerrar un pedido y guardar la factura
	public void cerrarPedido(Pedido pedido)
	{
		File factura = new File("./data/"+ pedido.getIdPedido() + ".txt");
		try {
			factura.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pedido.guardarFactura(factura);
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
				System.out.println("id: " + elemento.getIdPedido());
				System.out.println("Cliente: " + elemento.getNombreCliente());
				System.out.println("Direccion: " + elemento.getDireccionCliente());
				System.out.println("Items Pedidos: " + elemento.getItemsPedido().toString());
				System.out.println("Para mas informacion busque la factura en la carpeta data que tenga como nombre el id");
				elementoExiste = true;
				break;
				
			}
		}
		if (elementoExiste == false) {
			System.out.println("No se encontro ningun pedido con el id especificado");
			}
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