package levy.daniel.application.model.dto.metier.televersementdelotsections.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.model.dto.metier.anneegestion.IAnneeGestionDTO;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.televersement.ITeleversementDTO;
import levy.daniel.application.model.dto.metier.televersementdelotsections.ITeleversementDeLotSectionsHitDTO;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;

/**
 * CLASSE TeleversementDeLotSectionsHitDTO :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 1 août 2019
 *
 */
public class TeleversementDeLotSectionsHitDTO 
			implements ITeleversementDeLotSectionsHitDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * idString en base (sous forme de String).
	 */
	private String idString;
	
	/**
	 * televersementDTO.
	 */
	private ITeleversementDTO televersementDTO;
	
	/**
	 * lot de DTO des sections HIT.<br/>
	 */
	private Map<Integer, ISectionHitDTO> lotSectionsDTO;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitDTO.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public TeleversementDeLotSectionsHitDTO() {
		
		this(null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 *
	 * @param pTeleversementDTO : ITeleversementDTO : televersementDTO. 
	 * @param pLotSectionsDTO : Map&lt;Integer, ISectionHitDTO&gt; : 
	 * lot de DTO des sections HIT.
	 */
	public TeleversementDeLotSectionsHitDTO(
			final ITeleversementDTO pTeleversementDTO
					, final Map<Integer, ISectionHitDTO> pLotSectionsDTO) {
		
		this(null, pTeleversementDTO, pLotSectionsDTO);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.
	 *
	 * @param pIdString : String : ID en base (sous forme de String).
	 * @param pTeleversementDTO : ITeleversementDTO : televersementDTO. 
	 * @param pLotSectionsDTO : Map&lt;Integer, ISectionHitDTO&gt; : 
	 * lot de DTO des sections HIT.
	 */
	public TeleversementDeLotSectionsHitDTO(
			final String pIdString
				, final ITeleversementDTO pTeleversementDTO
					, final Map<Integer, ISectionHitDTO> pLotSectionsDTO) {
		
		super();
		
		this.idString = pIdString;
		this.televersementDTO = pTeleversementDTO;
		this.lotSectionsDTO = pLotSectionsDTO;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________

	
	
	/**
	 * retourne une String de la forme 
	 * <code>[annee-mois-jour_heure_minute_seconde]</code> 
	 * à partir d'une LocalDateTime pDate.<br/>
	 * Par exemple, retourne <b>"2019-06-13_21_03_57"</b> 
	 * pour le 13 juin 2019 à 21h 03 minutes et 57 secondes.<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : LocalDateTime : date à formater.
	 * 
	 * @return : String : affichage de la date formatée.<br/>
	 */
	private String formaterLocalDateTimeEnString(
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

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(
				this.getTeleversementDTO()
				, this.getLotSectionsDTO());
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObjet) {
		
		if (this == pObjet) {
			return true;
		}
		
		if (pObjet == null) {
			return false;
		}
		
		if (!(pObjet instanceof TeleversementDeLotSectionsHitDTO)) {
			return false;
		}
		
		final TeleversementDeLotSectionsHitDTO other 
			= (TeleversementDeLotSectionsHitDTO) pObjet;
		
		return Objects.equals(this.getTeleversementDTO(), other.getTeleversementDTO()) 
				&& Objects.equals(this.getLotSectionsDTO(), other.getLotSectionsDTO());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final ITeleversementDeLotSectionsHitDTO pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareAnneeGestion = 0;
		int compareGestionnaire = 0;
		int compareDateTeleversement = 0;
		
		
		if (this.getTeleversementDTO() != null 
				&& pObjet.getTeleversementDTO() != null) {
			
			/* anneeGestion. */
			if (this.getTeleversementDTO().getAnneeGestion() == null) {
									
				if (pObjet.getTeleversementDTO().getAnneeGestion() != null) {
					return +1;					
				}
				
			} else {
									
				if (pObjet.getTeleversementDTO().getAnneeGestion() == null) {
					return -1;
				}
								
				compareAnneeGestion 
					= this.getTeleversementDTO().getAnneeGestion()
						.compareTo(pObjet.getTeleversementDTO().getAnneeGestion());
			
				if (compareAnneeGestion != 0) {
					return compareAnneeGestion;
				}
			}

			/* gestionnaire. */
			if (this.getTeleversementDTO().getGestionnaire() == null) {
				if (pObjet.getTeleversementDTO().getGestionnaire() != null) {
					return +1;
				}
			} else {
				
				if (pObjet.getTeleversementDTO().getGestionnaire() == null) {
					return -1;
				}
				
				compareGestionnaire 
					= this.getTeleversementDTO().getGestionnaire()
						.compareTo(pObjet.getTeleversementDTO().getGestionnaire());
			
				if (compareGestionnaire != 0) {
					return compareGestionnaire;
				}
			}
			
			/* dateTeleversement. */
			if (this.getTeleversementDTO().getDateTeleversement() == null) {
				if (pObjet.getTeleversementDTO().getDateTeleversement() != null) {
					return +1;
				}
				
				return 0;
			}
			
			if (pObjet.getTeleversementDTO().getDateTeleversement() == null) {
				return -1;
			}
			
			compareDateTeleversement 
				= this.getTeleversementDTO().getDateTeleversement()
					.compareTo(pObjet.getTeleversementDTO().getDateTeleversement());
			
			return compareDateTeleversement;

		}
			
		if (this.getTeleversementDTO() == null && pObjet.getTeleversementDTO() != null) {
			return +1;
		}
		
		if (this.getTeleversementDTO() != null && pObjet.getTeleversementDTO() == null) {
			return -1;
		}
		
		return 0;
		
	} // Fin de compareTo(...).____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ITeleversementDeLotSectionsHitDTO clone() 
				throws CloneNotSupportedException {
		
		final ITeleversementDeLotSectionsHitDTO clone 
			= (ITeleversementDeLotSectionsHitDTO) super.clone();
		
		/* CLONAGE PROFOND. */
		Map<Integer, ISectionHitDTO> lotSectionsClone = null;
		
		if (this.getLotSectionsDTO() != null) {
			lotSectionsClone 
				= new ConcurrentHashMap<Integer, ISectionHitDTO>(this.getLotSectionsDTO());
		}
				
		clone.setIdString(this.getIdString());
		
		if (this.getTeleversementDTO() != null) {
			clone.setTeleversementDTO(this.getTeleversementDTO().clone());
		}
		
		clone.setLotSectionsDTO(lotSectionsClone);
		
		return clone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("TeleversementDeLotSectionsHitDTO [");

		stb.append("idString=");
		if (this.getIdString() != null) {
			stb.append(this.getIdString());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("dateTeleversement=");
		
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getDateTeleversement() != null) {
				stb.append(
						this.getTeleversementDTO().getDateTeleversement());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("utilisateur=");
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getUtilisateur() != null) {
				stb.append(this.getTeleversementDTO().getUtilisateur().getNom());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("gestionnaire=");
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getGestionnaire() != null) {
				stb.append(this.getTeleversementDTO().getGestionnaire());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
				
		stb.append(VIRGULE_ESPACE);

		stb.append("typeFichier=");
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getTypeFichier() != null) {
				stb.append(this.getTeleversementDTO().getTypeFichier());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("nomFichierTeleverse=");
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getNomFichierTeleverse() != null) {
				stb.append(this.getTeleversementDTO().getNomFichierTeleverse());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("fichierStockeServeur=");
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getFichierStockeServeur() != null) {
				stb.append(this.getTeleversementDTO().getFichierStockeServeur());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeGestion=");
		if (this.getTeleversementDTO() != null) {
			if (this.getTeleversementDTO().getAnneeGestion() != null) {
				stb.append(this.getTeleversementDTO().getAnneeGestion().getAnneeGestion());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(']');

		return stb.toString();
		
	} // Fin de toString().________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		return "idString;dateTeleversement;utilisateur;gestionnaire;"
				+ "typeFichier;nomFichierTeleverse;fichierStockeServeur;"
				+ "anneeGestion;";
	} // Fin de fournirEnTeteCsv().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getIdString());
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			
			final String dateTeleversementLocal 
			= this.getTeleversementDTO().getDateTeleversement();
		
			if (dateTeleversementLocal != null) {
				stb.append(dateTeleversementLocal);
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			
			final IUtilisateurCerbereDTO utilisateurLocal 
			= this.getTeleversementDTO().getUtilisateur();
		
			if (utilisateurLocal != null) {
				stb.append(utilisateurLocal.getNom());
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			
			final String gestionnaireLocal 
			= this.getTeleversementDTO().getGestionnaire();
		
			if (gestionnaireLocal != null) {
				stb.append(gestionnaireLocal);
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			
			final String typeFichierLocal 
			= this.getTeleversementDTO().getTypeFichier();
		
			if (typeFichierLocal != null) {
				stb.append(typeFichierLocal);
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			stb.append(this.getTeleversementDTO().getNomFichierTeleverse());
		} else {
			stb.append(NULL);
		}
		
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			
			final String fichierStockeServeurLocal 
			= this.getTeleversementDTO().getFichierStockeServeur();
			
			if (fichierStockeServeurLocal != null) {
				stb.append(fichierStockeServeurLocal);
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversementDTO() != null) {
			
			final IAnneeGestionDTO anneeGestionLocal 
			= this.getTeleversementDTO().getAnneeGestion();
		
			if (anneeGestionLocal != null) {
				stb.append(anneeGestionLocal.getAnneeGestion());
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);

		return stb.toString();

	} // Fin de fournirStringCsv().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteColonne(
			final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "idString";
			break;

		case 1:
			entete = "dateTeleversement";
			break;

		case 2:
			entete = "utilisateur";
			break;

		case 3:
			entete = "gestionnaire";
			break;

		case 4:
			entete = "typeFichier";
			break;

		case 5:
			entete = "nomFichierTeleverse";
			break;

		case 6:
			entete = "fichierStockeServeur";
			break;

		case 7:
			entete = "anneeGestion";
			break;

		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de fournirEnTeteColonne(...)._________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object fournirValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			
			valeur = this.getIdString();
						
			break;

		case 1:
			
			if (this.getTeleversementDTO() != null) {
				
				final String dateTeleversementLocal 
					= this.getTeleversementDTO().getDateTeleversement();
			
				valeur 
					= dateTeleversementLocal;
			}
			
			break;

		case 2:
			
			if (this.getTeleversementDTO() != null) {
				
				final IUtilisateurCerbereDTO utilisateurLocal 
					= this.getTeleversementDTO().getUtilisateur();
			
				if (utilisateurLocal != null) {
					valeur = utilisateurLocal.getNom();
				}

			}
			
			break;

		case 3:
			
			if (this.getTeleversementDTO() != null) {
				
				final String gestionnaireLocal 
					= this.getTeleversementDTO().getGestionnaire();
			
				if (gestionnaireLocal != null) {
					valeur = gestionnaireLocal;
				}			

			}
			
			break;

		case 4:
			
			if (this.getTeleversementDTO() != null) {
				
				final String typeFichierLocal 
					= this.getTeleversementDTO().getTypeFichier();
			
				if (typeFichierLocal != null) {
					valeur = typeFichierLocal;
				}	
			}
					
			break;

		case 5:
			
			if (this.getTeleversementDTO() != null) {
				valeur = this.getTeleversementDTO().getNomFichierTeleverse();
			}
			
			break;

		case 6:
			
			if (this.getTeleversementDTO() != null) {
				
				final String fichierStockeServeurLocal 
				= this.getTeleversementDTO().getFichierStockeServeur();
			
				if (fichierStockeServeurLocal != null) {
					valeur = fichierStockeServeurLocal;
				}	
			}
					
			break;

		case 7:
			
			if (this.getTeleversementDTO() != null) {
				
				final IAnneeGestionDTO anneeGestionLocal 
				= this.getTeleversementDTO().getAnneeGestion();
			
				if (anneeGestionLocal != null) {
					valeur = anneeGestionLocal.getAnneeGestion();
				}	
			}
					
			break;

		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de fournirValeurColonne(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherLotSections() {
		return this.afficherMapObjet(this.lotSectionsDTO);
	} // Fin de afficherLotSections()._____________________________________
	

	
	/**
	 * fournit une String pour l'affichage d'une 
	 * Map&lt;Integer,ISectionHitDTO&gt;<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer,ISectionHit&gt;
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public final String afficherMapObjet(
			final Map<Integer, ISectionHitDTO> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, ISectionHitDTO>> entrySet = pMap.entrySet();
		final Iterator<Entry<Integer, ISectionHitDTO>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, ISectionHitDTO> entry = ite.next();
			
			final Integer numeroLigne = entry.getKey();
			final ISectionHitDTO objet = entry.getValue();
			
			final String ligne 
				= String.format(
						LocaleManager.getLocaleApplication()
						, "Ligne %1$-7d : %2$s"
						, numeroLigne
						, objet.toString());
			
			stb.append(ligne);
			stb.append(NEWLINE);
		}
		
		return stb.toString();
	
	} // Fin de afficherMapObjet(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdString() {	
		return this.idString;
	} // Fin de getIdString()._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIdString(
			final String pIdString) {	
		this.idString = pIdString;
	} // Fin de setIdString(...).__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITeleversementDTO getTeleversementDTO() {
		return this.televersementDTO;
	} // Fin de getTeleversementDTO()._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTeleversementDTO(
			final ITeleversementDTO pTeleversementDTO) {
		this.televersementDTO = pTeleversementDTO;
	} // Fin de setTeleversementDTO(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Integer, ISectionHitDTO> getLotSectionsDTO() {
		return this.lotSectionsDTO;
	} // Fin de getLotSectionsDTO()._______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLotSectionsDTO(
			final Map<Integer, ISectionHitDTO> pLotSectionsDTO) {
		this.lotSectionsDTO = pLotSectionsDTO;
	} // Fin de setLotSectionsDTO(...).____________________________________


	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitDTO.-------------------------
