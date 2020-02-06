package model.data_structures;

public class ListaOrdenada<AtributosComparendos> implements IListaOrdenada<AtributosComparendos> {
	private int longitud;

	private Node<AtributosComparendos> primero;

	private Node<AtributosComparendos> ultimo;

	public ListaOrdenada(){
		longitud=0;
		primero=null;
		ultimo=null;		
	}


	public void agregar(AtributosComparendos dato) {

		if(primero==null){
			primero = new Node<AtributosComparendos>(dato);
			ultimo=primero;
			longitud++;
		}
		else{
			Node<AtributosComparendos> agregar = new Node<AtributosComparendos>(dato);
			Node<AtributosComparendos> actual = darUltimo();
			actual.cambiarSiguiente(agregar);
			ultimo=agregar;
			longitud++;		
		}
	}


	public AtributosComparendos buscar(int id) {
		AtributosComparendos encontrar = null;

		try{
			if(primero==null){
				return encontrar;
			}
			else{
				Node<AtributosComparendos> actual = primero;
				for(int i =0;i<id;i++){
					actual = actual.darSiguiente();
				}
				encontrar = actual.darElemento();
				return encontrar;
			}
		}
		catch(Exception e){
			System.out.println("No existe la informacion de ese comparendo");
			return encontrar=null;
		}

	}


	public int darLongitud() {

		return longitud;
	}

	public Node<AtributosComparendos> darUltimo(){
		return ultimo;
	}

	public Node<AtributosComparendos> darPrimero(){
		return primero;
	}




}
