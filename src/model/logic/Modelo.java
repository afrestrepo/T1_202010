package model.logic;

import model.data_structures.ListaOrdenada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

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
	private IListaOrdenada<AtributosComparendos> datos;

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
		datos = new ListaOrdenada<AtributosComparendos>();
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
	public String agregar()
	{	
		try
		{
			BufferedReader rd = null;
			StringReader srd = null;
			
			rd = new BufferedReader(new FileReader(rutaArchivoJSON));
			String inputLine = null;
			StringBuilder builder = new StringBuilder();
			
			while((inputLine = rd.readLine()) != null)
			{
				builder.append(inputLine);
			}
			srd = new StringReader(builder.toString());

			JsonReader reader = new JsonReader( srd );

			if ( rutaArchivoJSON.equals(comparendos_small_GEOJSON_FILE))  
			{								
				handleObject(reader);
			}
			else
			{		
				handleArray(reader);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		AtributosComparendos primero = (AtributosComparendos) datos.darPrimero().darElemento();
		AtributosComparendos segundo = (AtributosComparendos) datos.darUltimo().darElemento();
		return "Datos primer comparendo:\n" + primero.darDatos() + "\nDatos segundo comparendo:\n"+ segundo.darDatos() ;
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
			return "OBJECTID:\n" + buscar.darId() + "\n FECHA_HORA:\n" + buscar.darFechaHora()+"\n INFRACCION \n" + buscar.darInfraccion()+"\n CLASE_VEHI\n" + buscar.darClase()+"\n TIPO_SERVI\n " + buscar.darTipo()+"\n LOCALIDAD \n" + buscar.darLocalidad();

		}
		else{
			return "No existe el archivo";
		}
	}
	
	private void handleObject(JsonReader reader) throws IOException
	{		
		reader.beginObject();
		while (reader.hasNext()) {
			JsonToken token = reader.peek();
			if (token.equals(JsonToken.BEGIN_ARRAY))
			{
				handleArray(reader);
			}
			else if (token.equals(JsonToken.BEGIN_OBJECT)) {
				handleObject(reader);				
				reader.endObject();
				if ( crearObjComparendo )
				{
					crearComparendo();
				}
			}			
			else
			{
				handleNonArrayToken(reader, token);
			}
		}
	}
	public void handleArray(JsonReader reader) throws IOException
	{
		boolean finish = false;		
		reader.beginArray();
		while (!finish) {
			JsonToken token = reader.peek();
			if (token.equals(JsonToken.END_ARRAY)) {				
				reader.endArray();
				finish = true;
			} else if (token.equals(JsonToken.BEGIN_OBJECT)) {
				handleObject(reader);
			} else if (token.equals(JsonToken.END_OBJECT)) {				
				reader.endObject();
				if ( crearObjComparendo )
				{
					crearComparendo();
				}

			} else
				handleNonArrayToken(reader, token);
		}
	}
	public void handleNonArrayToken(JsonReader reader, JsonToken token) throws IOException
	{
		if (token.equals(JsonToken.NAME))
		{
			propiedad = reader.nextName();				
			if (propiedad.equalsIgnoreCase("features"))
			{  
				inicioArrayComparendos = true;				
			}
			if (inicioArrayComparendos)
			{
				if ( propiedad.equalsIgnoreCase("properties") )
				{  
					leyendoPropiedades = true;					
				}
				else if ( propiedad.equalsIgnoreCase("geometry") )
				{  
					leyendoGeometria= true;					
				}	            

				if ( leyendoPropiedades )
				{
					if ( propiedad.equalsIgnoreCase("OBJECTID"))
					{ 
						identificarObjectId = true;						
					}
					else if ( propiedad.equalsIgnoreCase("FECHA_HORA"))
					{	
						identificarFechaHora = true;												
					}
					else if ( propiedad.equalsIgnoreCase("CLASE_VEHI"))
					{	
						identificarClase = true;						 						
					}
					else if ( propiedad.equalsIgnoreCase("TIPO_SERVI"))
					{	
						identificarTipoServi = true;												
					}
					else if ( propiedad.equalsIgnoreCase("INFRACCION"))
					{	
						identificarInfraccion = true;												
					}
					else if ( propiedad.equalsIgnoreCase("DES_INFRAC"))
					{	
						identificarDescInfra = true;												
					}
					else if ( propiedad.equalsIgnoreCase("LOCALIDAD"))
					{	
						identificarLocalidad = true;
						leyendoPropiedades = false; 						
					}
				}
				else if ( leyendoGeometria )
				{
					if ( propiedad.equalsIgnoreCase("coordinates"))
					{  
						identificarLongitud = true;
						identificarLatitud = true;						
						leyendoGeometria = false;
						crearObjComparendo = true;
					}
					
				}
				
			}
		}
		else if (token.equals(JsonToken.STRING))
		{
			String valorString = reader.nextString();
			if ( identificarFechaHora )
			{
				fechaHora = valorString;
				identificarFechaHora = false;							
			}	
			else if ( identificarClase )
			{
				claseVehi = valorString;
				identificarClase = false;							
			}	
			else if ( identificarTipoServi )
			{
				tipoServi = valorString;
				identificarTipoServi = false;							
			}	
			else if (identificarInfraccion )
			{
				infraccion = valorString;
				identificarInfraccion = false;							
			}	
			else if ( identificarDescInfra)
			{
				descInfra = valorString;
				identificarDescInfra = false;							
			}	
			else if ( identificarLocalidad )
			{
				localidad = valorString;
				identificarLocalidad = false;							
			}			
		}
		else if (token.equals(JsonToken.NUMBER))
		{
			double valorNumerico = reader.nextDouble();
			if ( identificarObjectId )
			{
				objectId = (int) valorNumerico;
				identificarObjectId = false;				
			}
			else if ( identificarLongitud )
			{
				longitud = valorNumerico;
				identificarLongitud = false;								
			}	
			else if ( identificarLatitud )
			{
				latitud = valorNumerico;
				identificarLatitud = false;								
			}
		}
		else if (token.equals(JsonToken.BOOLEAN))
		{
			boolean valorBool = reader.nextBoolean();			
		}		
		else
		{			
			reader.skipValue();
		}
	}
	
	public void crearComparendo()
	{   
		AtributosComparendos nuevo = new AtributosComparendos(objectId,fechaHora,claseVehi,tipoServi,infraccion,descInfra,localidad);	
		datos.agregar(nuevo);
		leyendoPropiedades = false;
		leyendoGeometria = false;
		crearObjComparendo = false;
	}





}
