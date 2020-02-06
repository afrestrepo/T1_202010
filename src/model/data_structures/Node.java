package model.data_structures;

public class Node<T> {
	private T elemento;
	private Node<T> siguiente;
	public Node(T elemento){
		elemento=elemento;
		siguiente = null;
	}
	public void cambiarSiguiente(Node<T> siguiente){
		 siguiente =siguiente;
	}
	public Node<T> darSiguiente(){
		return siguiente;
	}
	public T darElemento(){
		return elemento;
	}
}
