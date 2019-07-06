package levy.daniel.application.model.dto.metier.sections.localisations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.sections.localisations.impl.LocalisationHitDTO;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.metier.sections.localisations.impl.LocalisationHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;

/**
 * CLASSE LocalisationHitConvertisseurMetierDTO :<br/>
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
 * @since 6 juil. 2019
 *
 */
public final class LocalisationHitConvertisseurMetierDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LocalisationHitConvertisseurMetierDTO".
	 */
	public static final String CLASSE_LOCALISATION_HIT_CONVERTISSEUR_METIER_DTO 
		= "Classe LocalisationHitConvertisseurMetierDTO";
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LocalisationHitConvertisseurMetierDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private LocalisationHitConvertisseurMetierDTO() {
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
	 * @param pDTO : ILocalisationHitDTO.<br/>
	 * 
	 * @return : ILocalisationHit : Objet métier.<br/>
	 * 
	 * @throws Exception 
	 */
	public static ILocalisationHit convertirDTOEnObjetMetier(
			final ILocalisationHitDTO pDTO) throws Exception {
		
		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			ILocalisationHit objet = null;
			
			if (pDTO != null) {
				
				// **********************************************
				/* récupère les valeurs String dans le DTO. */
				final String idString = pDTO.getId();
				final String numDepartementString = pDTO.getNumDepartement();
				final String numRouteString = pDTO.getNumRoute();
				final String indiceNumRouteString = pDTO.getIndiceNumRoute();
				final String indiceLettreRouteString = pDTO.getIndiceLettreRoute();
				final String categorieAdminRouteString = pDTO.getCategorieAdminRoute();
				final String lieuDitOrigineString = pDTO.getLieuDitOrigine();
				final String prOrigineString = pDTO.getPrOrigine();
				final String absOrigineString = pDTO.getAbsOrigine();
				final String lieuDitExtremiteString = pDTO.getLieuDitExtremite();
				final String prExtremiteString = pDTO.getPrExtremite();
				final String absExtremiteString = pDTO.getAbsExtremite();
				final String lieuDitComptageString = pDTO.getLieuDitComptage();
				final String prComptageString = pDTO.getPrComptage();
				final String absComptageString = pDTO.getAbsComptage();
				
				// ***********************************************************
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


				final String numDepartement = numDepartementString;
				final String numRoute = numRouteString;
				final String indiceNumRoute = indiceNumRouteString;
				final String indiceLettreRoute = indiceLettreRouteString;
				final String categorieAdminRoute = categorieAdminRouteString;
				final String lieuDitOrigine = lieuDitOrigineString;
				
				Integer prOrigine = null;

				if (!StringUtils.isBlank(prOrigineString)) {
					try {
						prOrigine = Integer.valueOf(prOrigineString);
					} catch (Exception e) {
						prOrigine = null;
					}
				}

				Integer absOrigine = null;

				if (!StringUtils.isBlank(absOrigineString)) {
					try {
						absOrigine = Integer.valueOf(absOrigineString);
					} catch (Exception e) {
						absOrigine = null;
					}
				}

				final String lieuDitExtremite = lieuDitExtremiteString;
				
				Integer prExtremite = null;

				if (!StringUtils.isBlank(prExtremiteString)) {
					try {
						prExtremite = Integer.valueOf(prExtremiteString);
					} catch (Exception e) {
						prExtremite = null;
					}
				}

				Integer absExtremite = null;

				if (!StringUtils.isBlank(absExtremiteString)) {
					try {
						absExtremite = Integer.valueOf(absExtremiteString);
					} catch (Exception e) {
						absExtremite = null;
					}
				}

				final String lieuDitComptage = lieuDitComptageString;
				
				Integer prComptage = null;

				if (!StringUtils.isBlank(prComptageString)) {
					try {
						prComptage = Integer.valueOf(prComptageString);
					} catch (Exception e) {
						prComptage = null;
					}
				}

				Integer absComptage = null;

				if (!StringUtils.isBlank(absComptageString)) {
					try {
						absComptage = Integer.valueOf(absComptageString);
					} catch (Exception e) {
						absComptage = null;
					}
				}
				
				
				// ***************************************************
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet = new LocalisationHit();
				
				objet.setId(id);
				objet.setNumDepartement(numDepartement);
				objet.setNumRoute(numRoute);
				objet.setIndiceNumRoute(indiceNumRoute);
				objet.setIndiceLettreRoute(indiceLettreRoute);
				objet.setCategorieAdminRoute(categorieAdminRoute);
				objet.setLieuDitOrigine(lieuDitOrigine);
				objet.setPrOrigine(prOrigine);
				objet.setAbsOrigine(absOrigine);
				objet.setLieuDitExtremite(lieuDitExtremite);
				objet.setPrExtremite(prExtremite);
				objet.setAbsExtremite(absExtremite);
				objet.setLieuDitComptage(lieuDitComptage);
				objet.setPrComptage(prComptage);
				objet.setAbsComptage(absComptage);
				
			} // Fin de if (pDTO != null).___________________________
			
			return objet;
			
		} // Fin de synchronized._______________________________________
		
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
	 * @param pObject : ILocalisationHit : 
	 * Objet métier.<br/>
	 * 
	 * @return : ILocalisationHitDTO : DTO.<br/>
	 */
	public static ILocalisationHitDTO convertirObjetMetierEnDTO(
			final ILocalisationHit pObject) {
		
		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			ILocalisationHitDTO dto = null;
			
			if (pObject != null) {
				
				// ****************************************************
				/* récupère les valeurs typées dans l'objet métier. */
				final Long id = pObject.getId();
				final String numDepartement = pObject.getNumDepartement();
				final String numRoute = pObject.getNumRoute();
				final String indiceNumRoute = pObject.getIndiceNumRoute();
				final String indiceLettreRoute = pObject.getIndiceLettreRoute();
				final String categorieAdminRoute = pObject.getCategorieAdminRoute();
				final String lieuDitOrigine = pObject.getLieuDitOrigine();
				final Integer prOrigine = pObject.getPrOrigine();
				final Integer absOrigine = pObject.getAbsOrigine();
				final String lieuDitExtremite = pObject.getLieuDitExtremite();
				final Integer prExtremite = pObject.getPrExtremite();
				final Integer absExtremite = pObject.getAbsExtremite();
				final String lieuDitComptage = pObject.getLieuDitComptage();
				final Integer prComptage = pObject.getPrComptage();
				final Integer absComptage = pObject.getAbsComptage();

				
				// ****************************************************
				/* convertit les types de l'Objet métier en String du DTO. */
				
				String idString = null;

				try {
					idString = String.valueOf(id);
				} catch (Exception e) {
					idString = null;
				}

				final String numDepartementString = numDepartement;				
				final String numRouteString = numRoute;
				final String indiceNumRouteString = indiceNumRoute;
				final String indiceLettreRouteString = indiceLettreRoute;
				final String categorieAdminRouteString = categorieAdminRoute;
				final String lieuDitOrigineString = lieuDitOrigine;
				
				String prOrigineString = null;

				try {
					
					final String prOrigineStringIncomplet 
						= String.valueOf(prOrigine);
					
					prOrigineString 
						= completerAvecZerosAGauche(
								prOrigineStringIncomplet, fournirLongueurChamp(19));
					
				} catch (Exception e) {
					prOrigineString = null;
				}

				
				String absOrigineString = null;

				try {
					
					final String absOrigineStringIncomplet 
						= String.valueOf(absOrigine);
					
					absOrigineString 
						= completerAvecZerosAGauche(
							absOrigineStringIncomplet, fournirLongueurChamp(20));
					
				} catch (Exception e) {
					absOrigineString = null;
				}

				final String lieuDitExtremiteString = lieuDitExtremite;
				
				String prExtremiteString = null;

				try {
					
					final String prExtremiteStringIncomplet 
						= String.valueOf(prExtremite);
				
					prExtremiteString 
						= completerAvecZerosAGauche(
							prExtremiteStringIncomplet, fournirLongueurChamp(22));
					
				} catch (Exception e) {
					prExtremiteString = null;
				}

				
				String absExtremiteString = null;

				try {
					
					final String absExtremiteStringIncomplet 
						= String.valueOf(absExtremite);
					
					absExtremiteString 
						= completerAvecZerosAGauche(
							absExtremiteStringIncomplet, fournirLongueurChamp(23));
					
				} catch (Exception e) {
					absExtremiteString = null;
				}

				final String lieuDitComptageString = lieuDitComptage;
				
				String prComptageString = null;

				try {
					
					final String prComptageStringIncomplet 
						= String.valueOf(prComptage);
				
					prComptageString 
						= completerAvecZerosAGauche(
							prComptageStringIncomplet, fournirLongueurChamp(25));
					
				} catch (Exception e) {
					prComptageString = null;
				}

				
				String absComptageString = null;

				try {
					
					final String absComptageStringIncomplet 
						= String.valueOf(absComptage);
					
					absComptageString 
						= completerAvecZerosAGauche(
							absComptageStringIncomplet, fournirLongueurChamp(26));
					
				} catch (Exception e) {
					absComptageString = null;
				}

				
				// ****************************************************
				/* injecte les valeurs String dans un DTO. */
				dto = new LocalisationHitDTO();

				dto.setId(idString);
				dto.setNumDepartement(numDepartementString);
				dto.setNumRoute(numRouteString);
				dto.setIndiceNumRoute(indiceNumRouteString);
				dto.setIndiceLettreRoute(indiceLettreRouteString);
				dto.setCategorieAdminRoute(categorieAdminRouteString);
				dto.setLieuDitOrigine(lieuDitOrigineString);
				dto.setPrOrigine(prOrigineString);
				dto.setAbsOrigine(absOrigineString);
				dto.setLieuDitExtremite(lieuDitExtremiteString);
				dto.setPrExtremite(prExtremiteString);
				dto.setAbsExtremite(absExtremiteString);
				dto.setLieuDitComptage(lieuDitComptageString);
				dto.setPrComptage(prComptageString);
				dto.setAbsComptage(absComptageString);
			
			}
						
			return dto;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnDTO(...).____________________________
	
	
	
	/**
	 * convertit une liste d'OBJETS METIER en liste 
	 * de DTOs.<br/>
	 * <br/>
	 * - retourne null si pListeObjets == null.<br/>
	 * <br/>
	 *
	 * @param pListeObjets : List&lt;ILocalisationHit&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * List&lt;ILocalisationHitDTO&gt; : 
	 * Liste de DTOs.<br/>
	 */
	public static List<ILocalisationHitDTO> convertirListeObjetEnListeDTO(
			final List<ILocalisationHit> pListeObjets) {
		
		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final List<ILocalisationHitDTO> resultat 
				= new ArrayList<ILocalisationHitDTO>();
			
			for (final ILocalisationHit objet : pListeObjets) {
				
				final ILocalisationHitDTO dto 
					= convertirObjetMetierEnDTO(objet);
				
				resultat.add(dto);
				
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirListeObjetEnListeDTO(...).________________________

	
	
	/**
	 * retourne une LocalDate positionnée au 1er Janvier 20AA 
	 * ou AA est l'année sur deux chiffres fournie dans pString.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * - LOG.fatal et retourne null si pString n'est pas homogène 
	 * à une année sur deux chiffres.<br/>
	 * <br/>
	 *
	 * @param pString : String : année sur deux chiffres comme "19" pour 2019.
	 * 
	 * @return : LocalDate : 1er Janvier 20AA.<br/>
	 */
	private static LocalDate fournirDateAvecAnneeSurDeuxChiffres(
			final String pString) {

		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			LocalDate resultat = null;
			
			final String motifAnneeSurDeuxChiffres = "\\d\\d";
			final Pattern patternAnneeSurDeuxChiffres 
				= Pattern.compile(motifAnneeSurDeuxChiffres); 
			final Matcher matcher = patternAnneeSurDeuxChiffres.matcher(pString);
			
			/* retourne null si pString n'est pas homogène 
			 * à une année sur deux chiffres. */
			if (!matcher.matches()) {
				
				final String message 
					= CLASSE_LOCALISATION_HIT_CONVERTISSEUR_METIER_DTO
					+ MOINS_ESPACE
					+ "méthode fournirDateAvecAnneeSurDeuxChiffres(String)"
					+ MOINS_ESPACE 
					+ "pString passé en paramètre n'est pas homogène à une année sur deux chiffres : " 
					+ pString;
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				return null;
			}
			
			final String anneeString = "20" + pString;
			
			final Integer annee = Integer.valueOf(anneeString);
			
			resultat = LocalDate.of(annee, 1, 1);
			
			return resultat;

		} // Fin de synchronized.____________________________________
		
	} // Fin de fournirDateAvecAnneeSurDeuxChiffres(...).__________________

	
	
	/**
	 * retourne un Integer à partir de la chaine fournie dans pString.<br/>
	 * <br/>
	 * - retire d'éventuels espaces avant et/ou après la valeur entière.<br/>
	 * - retourne null si pString est blank.<br/>
	 * - LOG.fatal et retourne null si pString n'est pas homogène 
	 * à un entier.<br/>
	 * <br/>
	 *
	 * @param pString : String : String homogène à un Integer.
	 * 
	 * @return : Integer.<br/>
	 */
	private static Integer fournirInteger(final String pString) {
		
		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			Integer resultat = null;
			
			final String motifInteger = "\\s*(\\d+)\\s*";
			final Pattern patternInteger 
				= Pattern.compile(motifInteger); 
			final Matcher matcher = patternInteger.matcher(pString);
			
			/* retourne null si pString n'est pas homogène 
			 * à un entier. */
			if (!matcher.matches()) {
				
				final String message 
					= CLASSE_LOCALISATION_HIT_CONVERTISSEUR_METIER_DTO
					+ MOINS_ESPACE
					+ "méthode fournirInteger(String)"
					+ MOINS_ESPACE 
					+ "pString passé en paramètre n'est pas homogène à un entier : " 
					+ pString;
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				return null;
			}

			/* retire d'éventuels espaces avant et/ou après la valeur entière. */
			final String valeur = matcher.group(1);
			
			resultat = Integer.valueOf(valeur);
			
			return resultat;

		} // Fin de synchronized._________________________________
		
	} // Fin de fournirInteger(...)._______________________________________

	
	
	/**
	 * retourne sous forme de String les deux derniers chiffres 
	 * de l'année d'une LocalDate.<br/>
	 * Par exemple : "19" pour 2019.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDate.
	 * 
	 * @return : String : 
	 * deux derniers chiffres de l'année de la LocalDate.<br/>
	 */
	private static String fournirAnneeDeuxChiffresAPartirDate(
			final LocalDate pDate) {
		
		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pDate == null. */
			if (pDate == null) {
				return null;
			}
			
			String resultat = null;
			
			final String motifFormatAnneeSurDeuxChiffres = "yy";
			
			final DateTimeFormatter formatterAnneeSurDeuxChiffres 
				= DateTimeFormatter.ofPattern(motifFormatAnneeSurDeuxChiffres);
			
			resultat = pDate.format(formatterAnneeSurDeuxChiffres);
			
			return resultat;

		} // Fin de synchronized._____________________________________
		
	} // Fin de fournirAnneeDeuxChiffresAPartirDate(...).__________________


	
	/**
	 * <b>retourne la chaine de caractères pString complétée 
	 * avec des zéros à gauche pour atteindre pTaille</b>.<br/>
	 * <ul>
	 * <li>Par exemple : retourne "0025" 
	 * si pString == "25" et pTaille == 4.</li>
	 * <li>retourne pString inchangée si sa longueur >= pTaille.</li>
	 * </ul>
	 * - retourne null si pTaille == 0.<br/>
	 * - retourne null si pString == null.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chaine de caractères à compléter avec des zéros à gauche.
	 * @param pTaille : 
	 * taille finale de la chaine complétée avec des zéros à gauche.
	 * 
	 * @return : String : 
	 * chaine de caractère pString complétée avec des zéros à gauche 
	 * pour atteindre pTaille.<br/>
	 */
	private static String completerAvecZerosAGauche(
			final String pString, final int pTaille) {

		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			/* retourne null si pTaille == 0. */
			if (pTaille == 0) {
				return null;
			}
			
			/* retourne null si pString == null. */
			if (pString == null) {
				return null;
			}
			
			final int tailleString = pString.length();
			
			/* retourne pString inchangée si sa longueur >= pTaille. */
			if (tailleString >= pTaille) {
				return pString;
			}
			
			String resultat = null;
			
			final int nombreZeros = pTaille - tailleString;
			
			resultat = pString;
			
			for (int i = 0; i < nombreZeros; i++) {
				resultat = "0" + resultat;
			}
			
			return resultat;

		} // Fin de synchronized.__________________________________
		
	} // Fin de completerAvecZerosAGauche(...).____________________________
	

	
	/**
	 * retourne la longueur du champ de numéro d'ordre pNumeroChamp 
	 * dans la description de fichier 
	 * <code><b>FactoryDescription.getImportateurHit()</b></code>.<br/>
	 * <br/>
	 * Par exemple : <code><b>fournirlongueurChamp(20)</b></code> 
	 * retourne 4 pour le champ absOrigine du HIT.
	 *
	 * @param pNumeroChamp : int : numéro d'ordre du champ dans la description.
	 * 
	 * @return : int : 
	 * longueur du champ d'ordre pNumeroChamp dans la description du fichier.
	 * 
	 * @throws Exception 
	 */
	private static int fournirLongueurChamp(
			final int pNumeroChamp) throws Exception {
		
		synchronized (LocalisationHitConvertisseurMetierDTO.class) {
			
			FactoryDescription.getDecriptionHitMap();		
			final IImportateurDescription descriptionFichier 
				= FactoryDescription.getImportateurHit();
			
			DescriptionChampHit description = null;
			int longueurChamp = 0;
			
			try {
				
				description 
				= (DescriptionChampHit) 
					descriptionFichier.getDescriptionChamp(pNumeroChamp);
				
				longueurChamp = description.getLongueur();
				
			} catch (Exception e) {

				e.printStackTrace();
			}

			return longueurChamp;

		} // Fin de synchronized._______________________________________
		
	} // Fin de fournirLongueurChamp(...)._________________________________

		
	
} // FIN DE LA CLASSE LocalisationHitConvertisseurMetierDTO.-----------------
