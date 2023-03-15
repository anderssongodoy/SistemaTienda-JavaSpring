package com.idat.springboot.sistematienda.entity;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

// CREACION DE LA CLASE QUE GENERARA LOS CODIGOS
public class GeneradorCodigo extends SequenceStyleGenerator{

	public static final String VALUE_PREFIX_PARAMETER = "valuePrefix"; // EL PREFIJO PARA LOS CODIGOS
	public static final String VALUE_PREFIX_DEFAULT = ""; // PREFIJO POR DEFECTO
	
	private String valuePrefix; // ATRIBUTO PREFIJO
	
	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat"; // EL FORMATO PARA LOS NUMEROS
	public static final String NUMBER_FORMAT_DEFAULT = "%d"; // FORMATO POR DEFECTO
	private String numberFormat; // ATRIBUTO FORMATO
	
	// GENERAMOS LOS METODOS OVERRIDE ( CONFIGURE Y GENERATE )
	
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		
		// TENEMOS LA CONFIGURACION DEL PREFIJO Y DEL FORMATO DE LOS NUMEROS
		super.configure(LongType.INSTANCE, params, serviceRegistry);
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
	}
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		// RETORNAMOS EN ORDEN EL PREFIJO Y EL FORMATO PARA LA GENERACION DE CODIGO
		return valuePrefix + String.format(numberFormat, super.generate(session, object));
	}
}
