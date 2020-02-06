package model.data_structures;

public interface IListaOrdenada<AtributosComparendos> {
	
	void agregar(AtributosComparendos dato);
	
	AtributosComparendos buscar(int id);
	
	int darLongitud();
	
	Node<AtributosComparendos> darUltimo();
	Node<AtributosComparendos> darPrimero();

}
