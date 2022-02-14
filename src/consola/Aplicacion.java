package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import modelo.Restaurante;

public class Aplicacion
{
	public void ejecutarOpcion()
	{
		System.out.println("Restaurante El Corral\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					cargarInformacion();
				else if (opcion_seleccionada == 2)
					iniciarPedido();
				else if (opcion_seleccionada == 3)
					agregarElemento();
				else if (opcion_seleccionada == 4)
					cerrarPedido();
				else if (opcion_seleccionada == 5)
					consultarInformacion();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (false)
				{
					System.out.println("Para poder ejecutar esta opción primero deben cargar los archivos");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
			
		}
	}
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido y guardar la factura");
		System.out.println("5. Consultar la información de un pedido dado su id");
		System.out.println("6. Salir de la aplicación\n");
	}
	
// 1. Mostrar el menú
	public void cargarInformacion()
	{
		System.out.println("Cargando archivos...");
		Restaurante restaurante = new Restaurante();
		
	}
	
// 2. Iniciar un nuevo pedido	
	public void iniciarPedido()
	{
		
	}
	
// 3. Agregar un elemento a un pedido
	public void agregarElemento()
	{
		
	}
	
// 	4. Cerrar un pedido y guardar la factura
	public void cerrarPedido()
	{
		
	}
	
// 5. Consultar la información de un pedido dado su id
	public void consultarInformacion()
	{
		
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