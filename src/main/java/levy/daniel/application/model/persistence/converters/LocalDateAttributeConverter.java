package levy.daniel.application.model.persistence.converters;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE LocalDateAttributeConverter :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 21 juil. 2019
 *
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter 
					implements AttributeConverter<LocalDate, Date> {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LocalDateAttributeConverter.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LocalDateAttributeConverter() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Date convertToDatabaseColumn(
			final LocalDate pLocalDate) {
		
		if (pLocalDate == null) {
			return null;
		}
		
		Date dateSql = Date.valueOf(pLocalDate);
		
		return dateSql;
		
	} // Fin de convertToDatabaseColumn(...).______________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final LocalDate convertToEntityAttribute(
			final Date pSqlDate) {
		
		if (pSqlDate == null) {
			return null;
		}
		
		final LocalDate localDate = pSqlDate.toLocalDate();
		
		return localDate;
		
	} // Fin de convertToEntityAttribute(...)._____________________________
	
	
	
} // FIN DE LA CLASSE LocalDateAttributeConverter.---------------------------
