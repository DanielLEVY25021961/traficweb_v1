package levy.daniel.application.model.dto.metier.televersement;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.anneegestion.AnneeGestionConvertisseurMetierDTO;
import levy.daniel.application.model.dto.metier.anneegestion.IAnneeGestionDTO;
import levy.daniel.application.model.dto.metier.televersement.impl.TeleversementDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.UtilisateurCerbereConvertisseurMetierDTO;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersement.impl.Televersement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * CLASSE TeleversementConvertisseurMetierDTO :<br/>
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
public final class TeleversementConvertisseurMetierDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementConvertisseurMetierDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private TeleversementConvertisseurMetierDTO() {
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
	 * @param pDTO : ITeleversementDTO.<br/>
	 * 
	 * @return : ITeleversement : Objet métier.<br/>
	 */
	public static ITeleversement convertirDTOEnObjetMetier(
			final ITeleversementDTO pDTO) {
		
		synchronized (TeleversementConvertisseurMetierDTO.class) {
			
			ITeleversement resultat = null;
			
			if (pDTO != null) {
				
				/* récupère les valeurs String dans le DTO. */
				final String idString = pDTO.getId();
				final String dateTeleversementString = pDTO.getDateTeleversement();
				final IUtilisateurCerbereDTO utilisateurDTO = pDTO.getUtilisateur();
				final String gestionnaireString = pDTO.getGestionnaire();
				final String typeFichierString = pDTO.getTypeFichier();
				final String nomFichierTeleverseString = pDTO.getNomFichierTeleverse();
				final String fichierStockeServeurString = pDTO.getFichierStockeServeur();
				final IAnneeGestionDTO anneeGestionDTO = pDTO.getAnneeGestion();
				
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
				
				/* dateTeleversement */
				LocalDateTime dateTeleversement = null;
				
				try {
					
					dateTeleversement 
						= LocalDateTime.parse(dateTeleversementString);
					
				} catch (Exception e) {
					dateTeleversement = null;
				}
				
				/* utilisateur */
				final IUtilisateurCerbere utilisateur 
					= UtilisateurCerbereConvertisseurMetierDTO
						.convertirDTOEnObjetMetier(utilisateurDTO);
				
				/* gestionnaire */
				EnumGestionnaire gestionnaire = null;
				
				if (!StringUtils.isBlank(gestionnaireString)) {
					gestionnaire 
						= EnumGestionnaire.valueOf(gestionnaireString);
				}
				
				/* typeFichier */
				EnumTypeFichierDonnees typeFichier = null;
				
				if (!StringUtils.isBlank(typeFichierString)) {
					typeFichier = EnumTypeFichierDonnees.valueOf(typeFichierString);
				}
				
				/* nomFichier */
				final String nomFichierTeleverse = nomFichierTeleverseString;
				
				/* fichierStockeServeur */
				File fichierStockeServeur = null;
				
				if (!StringUtils.isBlank(fichierStockeServeurString)) {
					fichierStockeServeur = new File(fichierStockeServeurString);
				}
				
				/* anneeGestion*/
				IAnneeGestion anneeGestion = null;
				
				if (anneeGestionDTO != null) {
					anneeGestion 
						= AnneeGestionConvertisseurMetierDTO
							.convertirDTOEnObjetMetier(anneeGestionDTO);
				}
				
				/* injecte les valeurs typées dans un OBJET METIER. */
				resultat 
					= new Televersement(
							id
							, dateTeleversement
							, utilisateur
							, gestionnaire
							, typeFichier
							, nomFichierTeleverse
							, fichierStockeServeur
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
	 * @param pObject : ITeleversement : 
	 * Objet métier.<br/>
	 * 
	 * @return : ITeleversementDTO : DTO.<br/>
	 */
	public static ITeleversementDTO convertirObjetMetierEnDTO(
			final ITeleversement pObject) {
		
		synchronized (TeleversementConvertisseurMetierDTO.class) {
			
			ITeleversementDTO resultat = null;
			
			if (pObject != null) {
				
				/* récupère les valeurs typées dans l'objet métier. */
				final Long id = pObject.getId();
				final LocalDateTime dateTeleversement = pObject.getDateTeleversement();
				final IUtilisateurCerbere utilisateur = pObject.getUtilisateur();
				final EnumGestionnaire gestionnaire = pObject.getGestionnaire();				
				final EnumTypeFichierDonnees typeFichier = pObject.getTypeFichier();
				final String nomFichierTeleverse = pObject.getNomFichierTeleverse();
				final File fichierStockeServeur = pObject.getFichierStockeServeur();
				final IAnneeGestion anneeGestion = pObject.getAnneeGestion();
				
				/* convertit les types de l'Objet métier en String du DTO. */
				final String idString = String.valueOf(id);
				final String dateTeleversementString 
					= formaterLocalDateTimeEnString(dateTeleversement);
				final IUtilisateurCerbereDTO utilisateurDTO 
					= UtilisateurCerbereConvertisseurMetierDTO
						.convertirObjetMetierEnDTO(utilisateur);
				final String gestionnaireString = gestionnaire.getNomCourt();				
				final String typeFichierString = typeFichier.getNomCourt();
				final String nomFichierTeleverseString = nomFichierTeleverse;
				final String fichierStockeServeurString 
					= fichierStockeServeur.getAbsolutePath();
				final IAnneeGestionDTO anneeGestionDTO 
					= AnneeGestionConvertisseurMetierDTO
						.convertirObjetMetierEnDTO(anneeGestion);
					
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new TeleversementDTO(
							idString
							, dateTeleversementString
							, utilisateurDTO
							, gestionnaireString
							, typeFichierString
							, nomFichierTeleverse
							, fichierStockeServeurString
							, anneeGestionDTO);
				
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
	 * @param pListeObjets : List&lt;ITeleversement&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * List&lt;ITeleversementDTO&gt; : 
	 * Liste de DTOs.<br/>
	 */
	public static List<ITeleversementDTO> convertirListeObjetEnListeDTO(
			final List<ITeleversement> pListeObjets) {
		
		synchronized (TeleversementConvertisseurMetierDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final List<ITeleversementDTO> resultat 
				= new ArrayList<ITeleversementDTO>();
			
			for (final ITeleversement objet : pListeObjets) {
				
				final ITeleversementDTO dto 
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

	
	
} // FIN DE LA CLASSE TeleversementConvertisseurMetierDTO.-------------------
