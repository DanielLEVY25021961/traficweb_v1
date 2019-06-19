package levy.daniel.application.model.dto.metier.anneegestion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.anneegestion.impl.AnneeGestionDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.anneegestion.impl.AnneeGestion;

/**
 * CLASSE AnneeGestionConvertisseurMetierDTO :<br/>
 * classe <b>utilitaire</b> chargée de <b>convertir 
 * un DTO en OBJET METIER</b> et de <b>convertir un
 * OBJET METIER en DTO</b>.<br/>
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
 * @since 19 juin 2019
 *
 */
public final class AnneeGestionConvertisseurMetierDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AnneeGestionConvertisseurMetierDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private AnneeGestionConvertisseurMetierDTO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <b>convertit un DTO en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pDTO == null.</li>
	 * <li>récupère les valeurs String dans le DTO.</li>
	 * <li>convertit les String du DTO en types de l'Objet métier.</li>
	 * <li>injecte les valeurs typées dans un OBJET METIER 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pDTO : IAnneeGestionDTO.<br/>
	 * 
	 * @return : IAnneeGestion : Objet métier.<br/>
	 */
	public static IAnneeGestion convertirDTOEnObjetMetier(
			final IAnneeGestionDTO pDTO) {
		
		synchronized (AnneeGestionConvertisseurMetierDTO.class) {
			
			IAnneeGestion resultat = null;
			
			if (pDTO != null) {
				
				/* récupère les valeurs String dans le DTO. */
				final String idString = pDTO.getId();
				final String anneeGestionString = pDTO.getAnneeGestion();
				
				/* convertit les String du DTO en types de l'Objet métier. */
				/* id */
				Long id = null;
				
				if (!StringUtils.isBlank(idString)) {
					try {
						id = Long.valueOf(idString);
					} catch (NumberFormatException e) {
						id = null;
					}
				}
				
				/* anneeGestion */
				final String anneeGestion = anneeGestionString;
				
				/* injecte les valeurs typées dans un OBJET METIER. */
				resultat 
					= new AnneeGestion(
							id
							, anneeGestion);
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirDTOEnObjetMetier(...).____________________________
	

	
	/**
	 * <b>convertit un OBJET METIER en DTO</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>récupère les valeurs typées dans l'objet métier.</li>
	 * <li>convertit les types de l'Objet métier en String du DTO.</li>
	 * <li>injecte les valeurs String dans un DTO 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pObject : IAnneeGestion : 
	 * Objet métier.<br/>
	 * 
	 * @return : IAnneeGestionDTO : DTO.<br/>
	 */
	public static IAnneeGestionDTO convertirObjetMetierEnDTO(
			final IAnneeGestion pObject) {
		
		synchronized (AnneeGestionConvertisseurMetierDTO.class) {
			
			IAnneeGestionDTO resultat = null;
			
			if (pObject != null) {
				
				/* récupère les valeurs typées dans l'objet métier. */
				final Long id = pObject.getId();
				final String anneeGestion = pObject.getAnneeGestion();
				
				/* convertit les types de l'Objet métier en String du DTO. */
				final String idString = String.valueOf(id);
				final String anneeGestionString = anneeGestion;
					
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new AnneeGestionDTO(
							idString
							, anneeGestionString);
				
			}
						
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnDTO(...).____________________________
	
	
	
	/**
	 * convertit une liste d'OBJETS METIER en liste 
	 * de DTOs.<br/>
	 * <br/>
	 * - retourne null si pListeObjets == null.<br/>
	 * <br/>
	 *
	 * @param pListeObjets : List&lt;IAnneeGestion&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * List&lt;IAnneeGestionDTO&gt; : 
	 * Liste de DTOs.<br/>
	 */
	public static List<IAnneeGestionDTO> convertirListeObjetEnListeDTO(
			final List<IAnneeGestion> pListeObjets) {
		
		synchronized (AnneeGestionConvertisseurMetierDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final List<IAnneeGestionDTO> resultat 
				= new ArrayList<IAnneeGestionDTO>();
			
			for (final IAnneeGestion objet : pListeObjets) {
				
				final IAnneeGestionDTO dto 
					= convertirObjetMetierEnDTO(objet);
				
				resultat.add(dto);
				
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirListeObjetEnListeDTO(...).________________________

	
	
	/**
	 * retourne une String de la forme 
	 * <code>[annee-mois-jour_heure_minute_seconde]</code> 
	 * à partir d'une LocalDateTime pDate.<br/>
	 * Par exemple, retourne <b>2019-06-13_21_03_57</b> 
	 * pour le 13 juin 2019 à 21h 03 minutes et 57 secondes.<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDateTime : date à formater.
	 * 
	 * @return : String : affichage de la date formatée.<br/>
	 */
	private static String formaterLocalDateTimeEnString(
			final LocalDateTime pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		/* formateur de type 2019-06-13_21_03_57 
		 * (13 juin 2019 à 21h 03 minutes et 57 secondes)*/
		final DateTimeFormatter formatter 
			= DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
	
		final String resultat = pDate.format(formatter);
				
		return resultat;
		
	} // Fin de formaterLocalDateTimeEnString(...).________________________

	
	
} // FIN DE LA CLASSE AnneeGestionConvertisseurMetierDTO.--------------------
