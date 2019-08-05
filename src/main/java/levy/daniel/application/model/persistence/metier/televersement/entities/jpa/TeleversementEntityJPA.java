package levy.daniel.application.model.persistence.metier.televersement.entities.jpa;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;
import levy.daniel.application.model.metier.televersement.EnumTypeFichierDonnees;
import levy.daniel.application.model.metier.televersement.ITeleversement;
import levy.daniel.application.model.metier.utilisateur.EnumGestionnaire;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.anneegestion.entities.jpa.AnneeGestionEntityJPA;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;


/**
 * CLASSE TeleversementEntityJPA :<br/>
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
 * @since 21 juin 2019
 *
 */
@Entity(name="TeleversementEntityJPA")
@Table(name="TELEVERSEMENTS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_DATETELEVERSEMENT_UTILISATEUR_NOMFICHIERTELEVERSE"
, columnNames={"DATETELEVERSEMENT", "ID_UTILISATEUR", "NOMFICHIERTELEVERSE"})
, indexes={@Index(name="INDEX_ANNEEGESTION_GESTIONNAIRE_DATETELEVERSEMENT"
, columnList="ID_ANNEEGESTION, GESTIONNAIRE, DATETELEVERSEMENT")})
public class TeleversementEntityJPA implements ITeleversement {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
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
	 * date-heure du téléversement.
	 */
	private LocalDateTime dateTeleversement;
	
	/**
	 * utilisateur réalisant le téléversement.
	 */
	private IUtilisateurCerbere utilisateur;
	
	/**
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 */
	private EnumGestionnaire gestionnaire;
	
	/**
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 */
	private EnumTypeFichierDonnees typeFichier;
	
	/**
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 */
	private String nomFichierTeleverse;

	/**
	 * fichier résultant du téléversement stocké sur le serveur.
	 */
	private File fichierStockeServeur;
	
	/**
	 * chemin sous forme de String du 
	 * fichier résultant du téléversement stocké sur le serveur.<br/>
	 * Utile pour la mise en stockage sous forme de String 
	 * (pas de mapping JPA pour File).
	 */
	private String fichierStockeServeurString;
	
	/**
	 * année de gestion concernée par le téléversement.
	 */
	private IAnneeGestion anneeGestion;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(TeleversementEntityJPA.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * <br/>
	 * calcule automatiquement 
	 * <code><b>this.fichierStockeServeurString</b></code>.<br/>
	 * <br/>
	 */
	public TeleversementEntityJPA() {
		
		this(null, null, null, null, null, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 * calcule automatiquement 
	 * <code><b>this.fichierStockeServeurString</b></code>.<br/>
	 * <br/>
	 * 
	 * @param pDateTeleversement : LocalDateTime : 
	 * date-heure du téléversement.
	 * @param pUtilisateur : IUtilisateurCerbere : 
	 * utilisateur réalisant le téléversement.
	 * @param pGestionnaire : EnumGestionnaire : 
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 * @param pTypeFichier : EnumTypeFichierDonnees : 
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 * @param pNomFichierTeleverse : String : 
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 * @param pFichierStockeServeur : File : 
	 * fichier résultant du téléversement stocké sur le serveur. 
	 * @param pAnneeGestion : IAnneeGestion : 
	 * année de gestion concernée par le téléversement.
	 */
	public TeleversementEntityJPA(
			final LocalDateTime pDateTeleversement
			, final IUtilisateurCerbere pUtilisateur
			, final EnumGestionnaire pGestionnaire
			, final EnumTypeFichierDonnees pTypeFichier
			, final String pNomFichierTeleverse
			, final File pFichierStockeServeur
			, final IAnneeGestion pAnneeGestion) {
		
		this(null, pDateTeleversement, pUtilisateur, pGestionnaire
				, pTypeFichier, pNomFichierTeleverse, pFichierStockeServeur
				, pAnneeGestion);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * <br/>
	 * calcule automatiquement 
	 * <code><b>this.fichierStockeServeurString</b></code>.<br/>
	 * <br/>
	 * 
	 * @param pId : Long : id en base.
	 * @param pDateTeleversement : LocalDateTime : 
	 * date-heure du téléversement.
	 * @param pUtilisateur : IUtilisateurCerbere : 
	 * utilisateur réalisant le téléversement.
	 * @param pGestionnaire : EnumGestionnaire : 
	 * gestionnaire (DIRA, DARWIN, ...) 
	 * pour le compte duquel l'utilisateur agit.
	 * @param pTypeFichier : EnumTypeFichierDonnees : 
	 * type du fichier (HIT, DARWIN_CSV) téléversé par l'utilisateur.
	 * @param pNomFichierTeleverse : String : 
	 * nom du fichier soumis au téléversement ("HITDIRA2018" par exemple).
	 * @param pFichierStockeServeur : File : 
	 * fichier résultant du téléversement stocké sur le serveur. 
	 * @param pAnneeGestion : IAnneeGestion : 
	 * année de gestion concernée par le téléversement.
	 */
	public TeleversementEntityJPA(
			final Long pId
			, final LocalDateTime pDateTeleversement
			, final IUtilisateurCerbere pUtilisateur
			, final EnumGestionnaire pGestionnaire
			, final EnumTypeFichierDonnees pTypeFichier
			, final String pNomFichierTeleverse
			, final File pFichierStockeServeur
			, final IAnneeGestion pAnneeGestion) {
		
		super();
		
		this.id = pId;
		this.dateTeleversement = pDateTeleversement;
		this.utilisateur = pUtilisateur;
		this.gestionnaire = pGestionnaire;
		this.typeFichier = pTypeFichier;
		this.nomFichierTeleverse = pNomFichierTeleverse;
		this.fichierStockeServeur = pFichierStockeServeur;
		
		/* calcule automatiquement this.fichierStockeServeurString. */
		this.fichierStockeServeurString 
			= this.deduireCheminFichier(this.fichierStockeServeur);
		
		this.anneeGestion = pAnneeGestion;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________

	
	
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
	 * retourne le path absolu sous forme de String de pFile.<br/>
	 * <br/>
	 * - retourne null si pFile == null.<br/>
	 * </br/>
	 *
	 * @param pFile : File.
	 * 
	 * @return : String : path absolu sous forme de String de pFile.<br/>
	 */
	private String deduireCheminFichier(final File pFile) {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		final String resultat = pFile.getAbsolutePath();
		
		return resultat;
		
	} // Fin de deduireCheminFichier(...)._________________________________
	

	
	/**
	 * retourne un File à partir du chemin absolu pString.<br/>
	 * <br/>
	 * - retourne null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : chemin absolu d'un File.
	 * 
	 * @return : File : new File(pString).<br/>
	 */
	private File deduireFileAPartirCheminFichier(final String pString) {
		
		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		final File resultat = new File(pString);
		
		return resultat;
		
	} // Fin de deduireFileAPartirCheminFichier(...).______________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(
				this.getDateTeleversement()
					, this.getUtilisateur()
						, this.getNomFichierTeleverse());
		
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
		
		if (!(pObjet instanceof ITeleversement)) {
			return false;
		}
		
		final ITeleversement other = (ITeleversement) pObjet;
		
		return Objects
				.equals(this.getDateTeleversement(), other.getDateTeleversement())
				&& Objects.equals(this.getUtilisateur(), other.getUtilisateur())
				&& Objects.equals(this.getNomFichierTeleverse(), other.getNomFichierTeleverse());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final ITeleversement pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareAnneeGestion = 0;
		int compareGestionnaire = 0;
		int compareDateTeleversement = 0;
		
		/* anneeGestion. */
		if (this.getAnneeGestion() == null) {
			if (pObjet.getAnneeGestion() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getAnneeGestion() == null) {
				return -1;
			}
			
			compareAnneeGestion 
				= this.getAnneeGestion().compareTo(pObjet.getAnneeGestion());
		
			if (compareAnneeGestion != 0) {
				return compareAnneeGestion;
			}
		}

		/* gestionnaire. */
		if (this.getGestionnaire() == null) {
			if (pObjet.getGestionnaire() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getGestionnaire() == null) {
				return -1;
			}
			
			compareGestionnaire 
				= this.getGestionnaire().compareTo(pObjet.getGestionnaire());
		
			if (compareGestionnaire != 0) {
				return compareGestionnaire;
			}
		}
		
		/* dateTeleversement. */
		if (this.getDateTeleversement() == null) {
			if (pObjet.getDateTeleversement() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getDateTeleversement() == null) {
			return -1;
		}
		
		compareDateTeleversement 
			= this.getDateTeleversement()
				.compareTo(pObjet.getDateTeleversement());
		
		return compareDateTeleversement;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ITeleversement clone() 
				throws CloneNotSupportedException {
		
		final ITeleversement clone 
			= (ITeleversement) super.clone();
		
		/* CLONAGE PROFOND. */
		IUtilisateurCerbere utilisateurCerbereClone = null;
		if (this.getUtilisateur() != null) {
			utilisateurCerbereClone 
			= this.getUtilisateur().clone();
		}
		
		IAnneeGestion anneeGestionClone = null;
		if (this.getAnneeGestion() != null) {
			anneeGestionClone 
			= this.getAnneeGestion().clone();
		}
						
		clone.setId(this.getId());
		clone.setDateTeleversement(this.getDateTeleversement());
		clone.setUtilisateur(utilisateurCerbereClone);
		clone.setGestionnaire(this.getGestionnaire());
		clone.setTypeFichier(this.getTypeFichier());
		clone.setNomFichierTeleverse(this.getNomFichierTeleverse());
		clone.setFichierStockeServeur(this.getFichierStockeServeur());
		clone.setAnneeGestion(anneeGestionClone);
		
		return (TeleversementEntityJPA) clone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("TeleversementEntityJPA [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("dateTeleversement=");
		
		if (this.getDateTeleversement() != null) {
			stb.append(this.formaterLocalDateTimeEnString(
					this.getDateTeleversement()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("utilisateur=");
		if (this.getUtilisateur() != null) {
			stb.append(this.getUtilisateur().getNom());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("gestionnaire=");
		if (this.getGestionnaire() != null) {
			stb.append(this.getGestionnaire().getNomCourt());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeFichier=");
		if (this.getTypeFichier() != null) {
			stb.append(this.getTypeFichier().getNomCourt());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nomFichierTeleverse=");
		if (this.getNomFichierTeleverse() != null) {
			stb.append(this.getNomFichierTeleverse());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("fichierStockeServeur=");
		if (this.getFichierStockeServeur() != null) {
			stb.append(this.getFichierStockeServeur().getName());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeGestion=");
		if (this.getAnneeGestion() != null) {
			stb.append(this.getAnneeGestion().getAnneeGestion());
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
		
		final LocalDateTime dateTeleversementLocal = this.getDateTeleversement();
		if (dateTeleversementLocal != null) {
			stb.append(this.formaterLocalDateTimeEnString(dateTeleversementLocal));
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final IUtilisateurCerbere utilisateurLocal = this.getUtilisateur();
		if (utilisateurLocal != null) {
			stb.append(utilisateurLocal.getNom());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final EnumGestionnaire gestionnaireLocal = this.getGestionnaire();
		if (gestionnaireLocal != null) {
			stb.append(gestionnaireLocal.getNomCourt());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final EnumTypeFichierDonnees typeFichierLocal = this.getTypeFichier();
		if (typeFichierLocal != null) {
			stb.append(typeFichierLocal.getNomCourt());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		stb.append(this.getNomFichierTeleverse());
		stb.append(POINT_VIRGULE);
		
		final File fichierStockeServeurLocal = this.getFichierStockeServeur();
		if (fichierStockeServeurLocal != null) {
			stb.append(fichierStockeServeurLocal.getName());
		} else {
			stb.append(NULL);
		}		
		stb.append(POINT_VIRGULE);
		
		final IAnneeGestion anneeGestionLocal = this.getAnneeGestion();
		if (anneeGestionLocal != null) {
			stb.append(anneeGestionLocal.getAnneeGestion());
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
			final LocalDateTime dateTeleversementLocal 
				= this.getDateTeleversement();
			valeur 
				= this.formaterLocalDateTimeEnString(dateTeleversementLocal);
			break;

		case 2:
			final IUtilisateurCerbere utilisateurLocal = this.getUtilisateur();
			if (utilisateurLocal != null) {
				valeur = this.getUtilisateur().getNom();
			}			
			break;

		case 3:
			final EnumGestionnaire gestionnaireLocal = this.getGestionnaire();
			if (gestionnaireLocal != null) {
				valeur = this.getGestionnaire().getNomCourt();
			}			
			break;

		case 4:
			final EnumTypeFichierDonnees typeFichierLocal = this.getTypeFichier();
			if (typeFichierLocal != null) {
				valeur = this.getTypeFichier().getNomCourt();
			}			
			break;

		case 5:
			valeur = this.getNomFichierTeleverse();
			break;

		case 6:
			final File fichierStockeServeurLocal = this.getFichierStockeServeur();
			if (fichierStockeServeurLocal != null) {
				valeur = this.getFichierStockeServeur().getName();
			}			
			break;

		case 7:
			final IAnneeGestion anneeGestionLocal = this.getAnneeGestion();
			if (anneeGestionLocal != null) {
				valeur = anneeGestionLocal.getAnneeGestion();
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
	@Column(name="DATETELEVERSEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public LocalDateTime getDateTeleversement() {
		return this.dateTeleversement;
	} // Fin de getDateTeleversement().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDateTeleversement(
			final LocalDateTime pDateTeleversement) {
		this.dateTeleversement = pDateTeleversement;
	} // Fin de setDateTeleversement(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@ManyToOne(targetEntity = UtilisateurCerbereEntityJPA.class
			, fetch = FetchType.EAGER)	
			@JoinColumn(name = "ID_UTILISATEUR", referencedColumnName = "ID"
			, foreignKey = @ForeignKey(name = "FK_TELEVERSEMENT_UTILISATEUR")
			, insertable = true, updatable = true
			, nullable = false, unique = false)			
	@Override
	public IUtilisateurCerbere getUtilisateur() {
		return this.utilisateur;
	} // Fin de getUtilisateur().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUtilisateur(
			final IUtilisateurCerbere pUtilisateur) {
		this.utilisateur = pUtilisateur;
	} // Fin de setUtilisateur(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="GESTIONNAIRE"
	, unique = false, updatable = true
	, insertable = true, nullable = false
	, length = 10)
	@Override
	public EnumGestionnaire getGestionnaire() {
		return this.gestionnaire;
	} // Fin de getGestionnaire()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setGestionnaire(
			final EnumGestionnaire pGestionnaire) {
		this.gestionnaire = pGestionnaire;
	} // Fin de setGestionnaire(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="TYPEFICHIER"
	, unique = false, updatable = true
	, insertable = true, nullable = false
	, length = 10)
	@Override
	public EnumTypeFichierDonnees getTypeFichier() {
		return this.typeFichier;
	} // Fin de getTypeFichier().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeFichier(
			final EnumTypeFichierDonnees pTypeFichier) {
		this.typeFichier = pTypeFichier;
	} // Fin de setTypeFichier(...)._______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Column(name="NOMFICHIERTELEVERSE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getNomFichierTeleverse() {
		return this.nomFichierTeleverse;
	} // Fin de getNomFichierTeleverse().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNomFichierTeleverse(
			final String pNomFichierTeleverse) {
		this.nomFichierTeleverse = pNomFichierTeleverse;
	} // Fin de setNomFichierTeleverse(...)._______________________________



	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public File getFichierStockeServeur() {
		return this.fichierStockeServeur;
	} // Fin de getFichierStockeServeur()._________________________________


	
	/**
	 * {@inheritDoc}<br/>
	 * <br/>
	 * calcule automatiquement 
	 * <code><b>this.fichierStockeServeurString</b></code>.<br/>
	 * <br/>
	 */
	@Override
	public void setFichierStockeServeur(
			final File pFichierStockeServeur) {
		
		this.fichierStockeServeur = pFichierStockeServeur;
		
		/* calcule automatiquement this.fichierStockeServeurString. */
		this.fichierStockeServeurString 
			= this.deduireCheminFichier(this.fichierStockeServeur);
		
	} // Fin de setFichierStockeServeur(...).______________________________


		
	/**
	 * Getter du chemin sous forme de String du 
	 * fichier résultant du téléversement stocké sur le serveur.<br/>
	 * Utile pour la mise en stockage sous forme de String 
	 * (pas de mapping JPA pour File).
	 *
	 * @return this.fichierStockeServeurString : String.<br/>
	 */
	@Column(name="FICHIERSTOCKESERVEURSTRING"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	public String getFichierStockeServeurString() {
		return this.fichierStockeServeurString;
	} // Fin de getFichierStockeServeurString().___________________________


	
	/**
	* Setter du chemin sous forme de String du 
	* fichier résultant du téléversement stocké sur le serveur.<br/>
	* Utile pour la mise en stockage sous forme de String 
	* (pas de mapping JPA pour File).<br/>
	* - calcule automatiquement 
	* <code><b>this.fichierStockeServeur</b></code>.<br/>
	*
	* @param pFichierStockeServeurString : String : 
	* valeur à passer à this.fichierStockeServeurString.<br/>
	*/
	public void setFichierStockeServeurString(
			final String pFichierStockeServeurString) {
		
		this.fichierStockeServeurString = pFichierStockeServeurString;
		
		/* calcule automatiquement this.fichierStockeServeur. */
		this.fichierStockeServeur 
			= this.deduireFileAPartirCheminFichier(
					this.fichierStockeServeurString);
		
	} // Fin de setFichierStockeServeurString(...).__________________________



	/**
	 * {@inheritDoc}
	 */
	@ManyToOne(targetEntity = AnneeGestionEntityJPA.class
			, fetch = FetchType.EAGER)	
			@JoinColumn(name = "ID_ANNEEGESTION", referencedColumnName = "ID"
			, foreignKey = @ForeignKey(name = "FK_TELEVERSEMENT_ANNEEGESTION")
			, insertable = true, updatable = true
			, nullable = false, unique = false)			
	@Override
	public IAnneeGestion getAnneeGestion() {
		return this.anneeGestion;
	} // Fin de getAnneeGestion()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeGestion(
			final IAnneeGestion pAnneeGestion) {
		this.anneeGestion = pAnneeGestion;
	} // Fin de setAnneeGestion(...).______________________________________
	
	
	
} // FIN DE LA CLASSE TeleversementEntityJPA.--------------------------------
