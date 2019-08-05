package levy.daniel.application.model.persistence.metier.televersementdelotsections.entities.jpa;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.televersementdelotsections.ITeleversementDeLotSectionsHit;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * CLASSE TeleversementDeLotSectionsHitEntityJPA :<br/>
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
@Entity(name="TeleversementDeLotSectionsHitEntityJPA")
@Table(name="TELEVERSEMENTSDELOTSECTIONS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_TELEVERSEMENT"
, columnNames={"ID_TELEVERSEMENT"}))
public class TeleversementDeLotSectionsHitEntityJPA 
					implements ITeleversementDeLotSectionsHit {

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
	 * id en base.
	 */
	private Long id;
	
	/**
	 * televersement (EntityJPA).
	 */
	private ITeleversement televersement;
	
	/**
	 * lot de sections HIT (EntityJPA).<br/>
	 */
	private Map<Integer, ISectionHit> lotSections;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementDeLotSectionsHitEntityJPA.class);

	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public TeleversementDeLotSectionsHitEntityJPA() {
		
		this(null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 *
	 * @param pTeleversement : ITeleversement : televersement. 
	 * @param pLotSections : Map&lt;Integer, ISectionHit&gt; : 
	 * lot de sections HIT.
	 */
	public TeleversementDeLotSectionsHitEntityJPA(
			final ITeleversement pTeleversement
					, final Map<Integer, ISectionHit> pLotSections) {
		
		this(null, pTeleversement, pLotSections);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.
	 *
	 * @param pId : Long : ID en base.
	 * @param pTeleversement : ITeleversement : televersement. 
	 * @param pLotSections : Map&lt;Integer, ISectionHit&gt; : 
	 * lot de sections HIT.
	 */
	public TeleversementDeLotSectionsHitEntityJPA(
			final Long pId
				, final ITeleversement pTeleversement
					, final Map<Integer, ISectionHit> pLotSections) {
		
		super();
		
		this.id = pId;
		this.televersement = pTeleversement;
		this.lotSections = pLotSections;
		
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
				this.getTeleversement()
				, this.getLotSections());
		
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
		
		if (!(pObjet instanceof ITeleversementDeLotSectionsHit)) {
			return false;
		}
		
		final ITeleversementDeLotSectionsHit other 
			= (ITeleversementDeLotSectionsHit) pObjet;
		
		return Objects.equals(this.getTeleversement(), other.getTeleversement()) 
				&& Objects.equals(this.getLotSections(), other.getLotSections());
		
	} // Fin de equals(...)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final ITeleversementDeLotSectionsHit pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareAnneeGestion = 0;
		int compareGestionnaire = 0;
		int compareDateTeleversement = 0;
		
		
		if (this.getTeleversement() != null 
				&& pObjet.getTeleversement() != null) {
			
			/* anneeGestion. */
			if (this.getTeleversement().getAnneeGestion() == null) {
									
				if (pObjet.getTeleversement().getAnneeGestion() != null) {
					return +1;					
				}
				
			} else {
									
				if (pObjet.getTeleversement().getAnneeGestion() == null) {
					return -1;
				}
								
				compareAnneeGestion 
					= this.getTeleversement().getAnneeGestion()
						.compareTo(pObjet.getTeleversement().getAnneeGestion());
			
				if (compareAnneeGestion != 0) {
					return compareAnneeGestion;
				}
			}

			/* gestionnaire. */
			if (this.getTeleversement().getGestionnaire() == null) {
				if (pObjet.getTeleversement().getGestionnaire() != null) {
					return +1;
				}
			} else {
				
				if (pObjet.getTeleversement().getGestionnaire() == null) {
					return -1;
				}
				
				compareGestionnaire 
					= this.getTeleversement().getGestionnaire()
						.compareTo(pObjet.getTeleversement().getGestionnaire());
			
				if (compareGestionnaire != 0) {
					return compareGestionnaire;
				}
			}
			
			/* dateTeleversement. */
			if (this.getTeleversement().getDateTeleversement() == null) {
				if (pObjet.getTeleversement().getDateTeleversement() != null) {
					return +1;
				}
				
				return 0;
			}
			
			if (pObjet.getTeleversement().getDateTeleversement() == null) {
				return -1;
			}
			
			compareDateTeleversement 
				= this.getTeleversement().getDateTeleversement()
					.compareTo(pObjet.getTeleversement().getDateTeleversement());
			
			return compareDateTeleversement;

		}
			
		if (this.getTeleversement() == null && pObjet.getTeleversement() != null) {
			return +1;
		}
		
		if (this.getTeleversement() != null && pObjet.getTeleversement() == null) {
			return -1;
		}
		
		return 0;
		
	} // Fin de compareTo(...).____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ITeleversementDeLotSectionsHit clone() 
				throws CloneNotSupportedException {
		
		final ITeleversementDeLotSectionsHit clone 
			= (ITeleversementDeLotSectionsHit) super.clone();
		
		/* CLONAGE PROFOND. */
		Map<Integer, ISectionHit> lotSectionsClone = null;
		
		if (this.getLotSections() != null) {
			lotSectionsClone 
				= new ConcurrentHashMap<Integer, ISectionHit>(this.getLotSections());
		}
				
		clone.setId(this.getId());
		
		if (this.getTeleversement() != null) {
			clone.setTeleversement(this.getTeleversement().clone());
		}
		
		clone.setLotSections(lotSectionsClone);
		
		return (TeleversementDeLotSectionsHitEntityJPA) clone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("TeleversementDeLotSectionsHitEntityJPA [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("dateTeleversement=");
		
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getDateTeleversement() != null) {
				stb.append(this.formaterLocalDateTimeEnString(
						this.getTeleversement().getDateTeleversement()));
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("utilisateur=");
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getUtilisateur() != null) {
				stb.append(this.getTeleversement().getUtilisateur().getNom());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("gestionnaire=");
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getGestionnaire() != null) {
				stb.append(this.getTeleversement().getGestionnaire().getNomCourt());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
				
		stb.append(VIRGULE_ESPACE);

		stb.append("typeFichier=");
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getTypeFichier() != null) {
				stb.append(this.getTeleversement().getTypeFichier().getNomCourt());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("nomFichierTeleverse=");
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getNomFichierTeleverse() != null) {
				stb.append(this.getTeleversement().getNomFichierTeleverse());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("fichierStockeServeur=");
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getFichierStockeServeur() != null) {
				stb.append(this.getTeleversement().getFichierStockeServeur().getAbsolutePath());
			} else {
				stb.append(NULL);
			}
		} else {
			stb.append(NULL);
		}
		
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeGestion=");
		if (this.getTeleversement() != null) {
			if (this.getTeleversement().getAnneeGestion() != null) {
				stb.append(this.getTeleversement().getAnneeGestion().getAnneeGestion());
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
		return "id;dateTeleversement;utilisateur;gestionnaire;"
				+ "typeFichier;nomFichierTeleverse;fichierStockeServeur;"
				+ "anneeGestion;";
	} // Fin de fournirEnTeteCsv().________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			
			final LocalDateTime dateTeleversementLocal 
			= this.getTeleversement().getDateTeleversement();
		
			if (dateTeleversementLocal != null) {
				stb.append(this.formaterLocalDateTimeEnString(dateTeleversementLocal));
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			
			final IUtilisateurCerbere utilisateurLocal 
			= this.getTeleversement().getUtilisateur();
		
			if (utilisateurLocal != null) {
				stb.append(utilisateurLocal.getNom());
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			final EnumGestionnaire gestionnaireLocal 
			= this.getTeleversement().getGestionnaire();
		
			if (gestionnaireLocal != null) {
				stb.append(gestionnaireLocal.getNomCourt());
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			final EnumTypeFichierDonnees typeFichierLocal 
			= this.getTeleversement().getTypeFichier();
		
			if (typeFichierLocal != null) {
				stb.append(typeFichierLocal.getNomCourt());
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			stb.append(this.getTeleversement().getNomFichierTeleverse());
		} else {
			stb.append(NULL);
		}
		
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			
			final File fichierStockeServeurLocal 
			= this.getTeleversement().getFichierStockeServeur();
			
			if (fichierStockeServeurLocal != null) {
				stb.append(fichierStockeServeurLocal.getName());
			} else {
				stb.append(NULL);
			}	
		} else {
			stb.append(NULL);
		}
			
		stb.append(POINT_VIRGULE);
		
		if (this.getTeleversement() != null) {
			
			final IAnneeGestion anneeGestionLocal 
			= this.getTeleversement().getAnneeGestion();
		
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
			entete = "id";
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
			final Long idLocal = this.getId();
			if (idLocal != null) {
				valeur = String.valueOf(this.getId());
			}			
			break;

		case 1:
			
			if (this.getTeleversement() != null) {
				
				final LocalDateTime dateTeleversementLocal 
					= this.getTeleversement().getDateTeleversement();
			
				valeur 
					= this.formaterLocalDateTimeEnString(dateTeleversementLocal);
			}
			
			break;

		case 2:
			
			if (this.getTeleversement() != null) {
				
				final IUtilisateurCerbere utilisateurLocal 
					= this.getTeleversement().getUtilisateur();
			
				if (utilisateurLocal != null) {
					valeur = utilisateurLocal.getNom();
				}

			}
			
			break;

		case 3:
			
			if (this.getTeleversement() != null) {
				
				final EnumGestionnaire gestionnaireLocal 
					= this.getTeleversement().getGestionnaire();
			
				if (gestionnaireLocal != null) {
					valeur = gestionnaireLocal.getNomCourt();
				}			

			}
			
			break;

		case 4:
			
			if (this.getTeleversement() != null) {
				
				final EnumTypeFichierDonnees typeFichierLocal 
					= this.getTeleversement().getTypeFichier();
			
				if (typeFichierLocal != null) {
					valeur = typeFichierLocal.getNomCourt();
				}	
			}
					
			break;

		case 5:
			
			if (this.getTeleversement() != null) {
				valeur = this.getTeleversement().getNomFichierTeleverse();
			}
			
			break;

		case 6:
			
			if (this.getTeleversement() != null) {
				
				final File fichierStockeServeurLocal 
				= this.getTeleversement().getFichierStockeServeur();
			
				if (fichierStockeServeurLocal != null) {
					valeur = fichierStockeServeurLocal.getName();
				}	
			}
					
			break;

		case 7:
			
			if (this.getTeleversement() != null) {
				
				final IAnneeGestion anneeGestionLocal 
				= this.getTeleversement().getAnneeGestion();
			
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
		return this.afficherMapObjet(this.lotSections);
	} // Fin de afficherLotSections()._____________________________________
	

	
	/**
	 * fournit une String pour l'affichage d'une 
	 * Map&lt;Integer,ISectionHit&gt;<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer,ISectionHit&gt;
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	private String afficherMapObjet(
			final Map<Integer, ISectionHit> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, ISectionHit>> entrySet = pMap.entrySet();
		final Iterator<Entry<Integer, ISectionHit>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, ISectionHit> entry = ite.next();
			
			final Integer numeroLigne = entry.getKey();
			final ISectionHit objet = entry.getValue();
			
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@Override
	public Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	@Column(name="ID_TELEVERSEMENT"
			, insertable = true, updatable = true
			, nullable = false, unique = false)
	@Override
	public ITeleversement getTeleversement() {
		return this.televersement;
	} // Fin de getTeleversement().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTeleversement(
			final ITeleversement pTeleversement) {
		this.televersement = pTeleversement;
	} // Fin de setTeleversement(...)._____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	name = "TELEVERSEMENTSDELOTSECTIONS_SECTIONHTS"
	, joinColumns= {@JoinColumn(name = "FK_TELEVERSEMENT", referencedColumnName = "ID")}
	, inverseJoinColumns = {@JoinColumn(name = "FK_SECTIONHIT", referencedColumnName = "ID")}
	)
	@MapKeyColumn (name = "numeroligne")
	@Override
	public Map<Integer, ISectionHit> getLotSections() {
		return this.lotSections;
	} // Fin de getLotSections().__________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLotSections(
			final Map<Integer, ISectionHit> pLotSections) {
		this.lotSections = pLotSections;
	} // Fin de setLotSections(...)._______________________________________


	
} // FIN DE LA CLASSE TeleversementDeLotSectionsHitEntityJPA.----------------
