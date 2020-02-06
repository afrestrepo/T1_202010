package model.data_structures;

public class ListaOrdenada<T> implements IListaOrdenada<T> {
	private int longitud;
	
	private Node<T> primero;
	
	private Node<T> ultimo;
	
	public ListaOrdenada(){
		longitud=0;
		primero=null;
		ultimo=null;		
	}

	
	public void agregar(T dato) {
		
		if(primero==null){
			primero = new Node<T>(dato);
			ultimo=primero;
			longitud++;
		}
		else{
			Node<T> agregar = new Node<T>(dato);
			Node<T> actual = darUltimo();
			actual.cambiarSiguiente(agregar);
			ultimo=agregar;
			longitud++;		
		}
	}

	
	public T buscar(int id) {
		T encontrar = null;
		if(primero==null){
			return encontrar;
		}
		else{
			Node<T> actual = primero;
			for(int i =0;i!=id;i++){
				actual = actual.darSiguiente();
			}
			encontrar = actual.darElemento();
			return encontrar;
		}
	}

	
	public int darLongitud() {
		
		return longitud;
	}
	
	public Node<T> darUltimo(){
		return ultimo;
	}
	
	public Node<T> darPrimero(){
		return primero;
	}

	
	

}
