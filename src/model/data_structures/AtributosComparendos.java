package model.data_structures;

public class AtributosComparendos {
	int OBJECTID;
	String FECHA_HORA;
	String CLASE_VEHI;
	String TIPO_SERVI;
	String INFRACCION;
	String DES_INFRAC;
	String LOCALIDAD;

	public AtributosComparendos(int id,String fechaHora,String clase,String tipo,String infraccion,String descInfra,String localidad ){
		OBJECTID=id;
		FECHA_HORA = fechaHora;
		CLASE_VEHI=clase;
		TIPO_SERVI=tipo;
		INFRACCION=infraccion;
		DES_INFRAC=descInfra;
		LOCALIDAD=localidad;

	}
	public int darId(){
		return  OBJECTID;
	}
	public String darFechaHora(){
		return FECHA_HORA;

	}
	public String darClase(){
		return CLASE_VEHI;

	}
	public String darTipo(){
		return TIPO_SERVI;

	}
	public String darInfraccion(){
		return INFRACCION;

	}
	public String darDescInfra(){
		return DES_INFRAC;

	}
	public String darLocalidad(){
		return LOCALIDAD;

	}
	public String darDatos(){
		return  OBJECTID+FECHA_HORA+CLASE_VEHI+TIPO_SERVI+INFRACCION+DES_INFRAC+LOCALIDAD;
	}

}
