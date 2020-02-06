package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		int id = 0;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nRealizar la carga de los comparendos de la ciudad de Bogot� para el periodo 2018. ");				    
				    modelo = new Modelo(); 
				    view.printMessage("Elementos agregados");
				    view.printMessage("Primercomparendo:" + modelo.darTamano() + "\nDar capacidad inicial del arreglo"+modelo.darTamano() + "\n---------");						
					break;

				case 2:
					view.printMessage("--------- \nConsultar la informaci�n b�sica de un comparendo dado su OBJECTID.: ");
					id = lector.nextInt();
					modelo.buscar(id);
					view.printMessage("Dato agregado");
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 3:
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
