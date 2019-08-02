package levy.daniel.application.model.dto.metier.televersementdelotsections;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.sections.SectionHitConvertisseurMetierDTO;
import levy.daniel.application.model.dto.metier.televersement.ITeleversementDTO;
import levy.daniel.application.model.dto.metier.televersement.TeleversementConvertisseurMetierDTO;
import levy.daniel.application.model.dto.metier.televersementdelotsections.impl.TeleversementDeLotSectionsHitDTO;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.metier.televersementdelotsections.impl.TeleversementDeLotSectionsHit;

/**
 * CLASSE TeleversementDeLotSectionsHitConvertisseurMetierDTO :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 2 août 2019
 *
 */
public final class TeleversementDeLotSectionsHitConvertisseurMetierDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitConvertisseurMetierDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private TeleversementDeLotSectionsHitConvertisseurMetierDTO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <b>convertit un DTO en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pDTO == null.</li>
	 * <li>récupère les valeurs DTO ou String dans le DTO.</li>
	 * <li>convertit les String du DTO en types de l'Objet métier.</li>
	 * <li>injecte les valeurs typées dans un OBJET METIER 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pDTO : ITeleversementDeLotSectionsHitDTO.<br/>
	 * 
	 * @return : ITeleversementDeLotSectionsHit : Objet métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ITeleversementDeLotSectionsHit convertirDTOEnObjetMetier(
			final ITeleversementDeLotSectionsHitDTO pDTO) throws Exception {
		
		synchronized (TeleversementConvertisseurMetierDTO.class) {
			
			ITeleversementDeLotSectionsHit resultat = null;
			
			if (pDTO != null) {
				
				/* récupère les valeurs String dans le DTO. */
				final String idString = pDTO.getIdString();
				final ITeleversementDTO televersementDTO 
					= pDTO.getTeleversementDTO();
				final Map<Integer, ISectionHitDTO> lotSectionsDTO 
					= pDTO.getLotSectionsDTO();
				
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
				
				/* televersement */
				ITeleversement televersement = null;
				
				if (televersementDTO != null) {
					
					televersement 
					= TeleversementConvertisseurMetierDTO
						.convertirDTOEnObjetMetier(televersementDTO);
					
				}
				
				
				/* lotSection. */
				Map<Integer, ISectionHit> lotSections = null;
				
				if (lotSectionsDTO != null) {
					
					lotSections = new ConcurrentHashMap<>();
					
					final Set<Entry<Integer, ISectionHitDTO>> entrySet 
						= lotSectionsDTO.entrySet();
					
					final Iterator<Entry<Integer, ISectionHitDTO>> ite 
						= entrySet.iterator();
					
					while (ite.hasNext()) {
						
						final Entry<Integer, ISectionHitDTO> entry = ite.next();
						final Integer key = entry.getKey();
						final ISectionHitDTO value = entry.getValue();
						
						final ISectionHit objet 
							= SectionHitConvertisseurMetierDTO
								.convertirDTOEnObjetMetier(value);
						
						lotSections.put(key, objet);
					}
						
					

				}
			
				/* injecte les valeurs typées dans un OBJET METIER. */
				resultat 
					= new TeleversementDeLotSectionsHit(
							id
							, televersement
							, lotSections);
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
	 * @param pObject : ITeleversementDeLotSectionsHit : 
	 * Objet métier.<br/>
	 * 
	 * @return : ITeleversementDeLotSectionsHitDTO : DTO.<br/>
	 */
	public static ITeleversementDeLotSectionsHitDTO convertirObjetMetierEnDTO(
			final ITeleversementDeLotSectionsHit pObject) {
		
		synchronized (TeleversementConvertisseurMetierDTO.class) {
			
			ITeleversementDeLotSectionsHitDTO resultat = null;
			
			if (pObject != null) {
				
				/* récupère les valeurs typées dans l'objet métier. */
				final Long id = pObject.getId();
				final ITeleversement televersement = pObject.getTeleversement();
				final Map<Integer, ISectionHit> lotSections 
					= pObject.getLotSections();
				
				/* convertit les types de l'Objet métier en String du DTO. */
				final String idString = String.valueOf(id);
				
				ITeleversementDTO televersementDTO = null;
				
				if (televersement != null) {
					
					televersementDTO 
					= TeleversementConvertisseurMetierDTO
						.convertirObjetMetierEnDTO(televersement);
					
				}
								
				Map<Integer, ISectionHitDTO> lotSectionsDTO = null;
				
				if (lotSections != null) {
					
					lotSectionsDTO 
						= new ConcurrentHashMap<Integer, ISectionHitDTO>();
					
					final Set<Entry<Integer, ISectionHit>> entrySet 
						= lotSections.entrySet();
					
					final Iterator<Entry<Integer, ISectionHit>> ite 
						= entrySet.iterator();
					
					while (ite.hasNext()) {
						
						final Entry<Integer, ISectionHit> entry = ite.next();
						final Integer key = entry.getKey();
						final ISectionHit value = entry.getValue();
						
						final ISectionHitDTO dto 
							= SectionHitConvertisseurMetierDTO
								.convertirObjetMetierEnDTO(value);
						
						lotSectionsDTO.put(key, dto);
					}
				}
								
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new TeleversementDeLotSectionsHitDTO(
							idString
							, televersementDTO
							, lotSectionsDTO);
				
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
	 * @param pListeObjets : List&lt;ITeleversementDeLotSectionsHit&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * List&lt;ITeleversementDeLotSectionsHitDTO&gt; : 
	 * Liste de DTOs.<br/>
	 */
	public static List<ITeleversementDeLotSectionsHitDTO> convertirListeObjetEnListeDTO(
			final List<ITeleversementDeLotSectionsHit> pListeObjets) {
		
		synchronized (TeleversementConvertisseurMetierDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final List<ITeleversementDeLotSectionsHitDTO> resultat 
				= new ArrayList<ITeleversementDeLotSectionsHitDTO>();
			
			for (final ITeleversementDeLotSectionsHit objet : pListeObjets) {
				
				final ITeleversementDeLotSectionsHitDTO dto 
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
	
	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitConvertisseurMetierDTO.---
