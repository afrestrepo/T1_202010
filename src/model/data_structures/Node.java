package model.data_structures;

public class Node<AtributosComparendos> {
	private AtributosComparendos elemento;
	private Node<AtributosComparendos> siguiente;
	public Node(AtributosComparendos pelemento){
		elemento=pelemento;
		siguiente = null;
	}
	public void cambiarSiguiente(Node<AtributosComparendos> psiguiente){
		 siguiente =psiguiente;
	}
	public Node<AtributosComparendos> darSiguiente(){
		return siguiente;
	}
	public AtributosComparendos darElemento(){
		return elemento;
	}
}
