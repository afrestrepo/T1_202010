package model.logic;

import model.data_structures.ListaOrdenada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;

import com.google.gson.stream.JsonReader;

import model.data_structures.AtributosComparendos;
import model.data_structures.IListaOrdenada;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	private final static String comparendos_small_GEOJSON_FILE = "./data/comparendos_dei_2018_small.geojson"; 
	/**
	 * Atributos del modelo del mundo
	 */
	private IListaOrdenada datos;
	
	private String rutaArchivoJSON;
	private boolean inicioArrayComparendos;  
	private boolean leyendoPropiedades;      
	private boolean leyendoGeometria;        
	private boolean crearObjComparendo;     
	
	private String propiedad;
	private boolean identificarObjectId;	
	private int objectId;		
	private boolean identificarFechaHora;  
	private String fechaHora;	
	private boolean identificarClase;  
	private String claseVehi;	
	private boolean identificarTipoServi;  
	private String tipoServi;	
	private boolean identificarInfraccion;  
	private String infraccion;	
	private boolean identificarDescInfra;  
	private String descInfra;	
	private boolean identificarLocalidad;  
	private String localidad;				
	
	private boolean identificarLongitud;    
	private boolean identificarLatitud;     
	private double longitud;				
	private double latitud;		
	
	
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ListaOrdenada();
		rutaArchivoJSON = comparendos_small_GEOJSON_FILE;
		inicioArrayComparendos = false;
		leyendoPropiedades = false;
		leyendoGeometria = false;
		crearObjComparendo = false;
		
		propiedad = "";
		identificarObjectId = false;
		objectId = -1;
		identificarLocalidad = false;
		localidad = "";
		identificarFechaHora = false;
		fechaHora = "";
		identificarClase = false;
		claseVehi = "";
		identificarTipoServi = false;
		tipoServi = "";
		identificarInfraccion = false;
		localidad = "";
		identificarDescInfra = false;
		descInfra = "";
		identificarLongitud = false;
		identificarLatitud = false;		
		longitud = 0.0;
		latitud = 0.0;
	}
	
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darLongitud();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar()
	{	
		
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(int id)
	{
		AtributosComparendos buscar = (AtributosComparendos) datos.buscar(id);
		if(buscar!=null){
			return "OBJECTID:" + buscar.darId() + "\n FECHA_HORA" + buscar.darFechaHora()+"\n INFRACCION " + buscar.darInfraccion()+"\n CLASE_VEHI" + buscar.darClase()+"\n TIPO_SERVI " + buscar.darTipo()+"\n LOCALIDAD " + buscar.darLocalidad();
			
		}
		else{
			return "No existe el archivo";
		}
	}
	
	public void processingJSONFile( ) 
	{
		try
		{
			BufferedReader rd = null;
			StringReader srd = null;
			
			rd = new BufferedReader(new FileReader(rutaArchivoJSON));
			String inputLine = null;
			StringBuilder builder = new StringBuilder();

			//Store the contents of the file to the StringBuilder.
			while((inputLine = rd.readLine()) != null)
			{
				builder.append(inputLine);
			}
			srd = new StringReader(builder.toString());

			JsonReader reader = new JsonReader( srd );

			if ( rutaArchivoJSON.equals(comparendos_small_GEOJSON_FILE))  // Definido como un JSON_OBJECT
			{
				// we call the handle object method to handle the full json object. This
				// implies that the first token in JsonToken.BEGIN_OBJECT, which is
				// always true.

				// Reading Test of a JSON object
				System.out.println("Reading the JSON Object File: " + rutaArchivoJSON);
				handleObject(reader);
			}
			else
			{
				// Reading Test of a JSON Array
				System.out.println("Reading the JSON Array File: " + rutaArchivoJSON);
				handleArray(reader);
			}
			System.out.println("End Test Handle JSON processing");

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	


}
