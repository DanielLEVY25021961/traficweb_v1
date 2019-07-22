package levy.daniel.application.model.persistence.metier.anneegestion.entities.jpa;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.anneegestion.IAnneeGestion;

/**
 * CLASSE AnneeGestionEntityJPA :<br/>
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
 * @since 20 juin 2019
 *
 */
@Entity(name="AnneeGestionEntityJPA")
@Table(name="ANNEESGESTION", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_ANNEEGESTION"
, columnNames={"ANNEEGESTION"})
, indexes={@Index(name="INDEX_ANNEEGESTION", columnList="ANNEEGESTION")})
public class AnneeGestionEntityJPA implements IAnneeGestion {

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
	 * année de gestion sur 4 chiffres ("2018" par exemple).
	 */
	private String anneeGestion;

	/**
	 * année de gestion sous forme de LocalDate 
	 * fixée au 1er janvier de l'année.
	 */
	private transient LocalDate anneeGestionDate;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AnneeGestionEntityJPA.class);

	
	// ************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public AnneeGestionEntityJPA() {
		this(null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 *  @param pAnneeGestion : String : 
	 * année de gestion sur 4 chiffres ("2018" par exemple). 
	 */
	public AnneeGestionEntityJPA(final String pAnneeGestion) {
		this(null, pAnneeGestion);
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 * 
	 * @param pId : Long : id en base.
	 * @param pAnneeGestion : String : 
	 * année de gestion sur 4 chiffres ("2018" par exemple). 
	 */
	public AnneeGestionEntityJPA(final Long pId, final String pAnneeGestion) {
		
		super();
		
		this.id = pId;
		this.anneeGestion = pAnneeGestion;
		this.anneeGestionDate 
			= this.fournirDateAuPremierJanvier(this.anneeGestion);
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________


	
	/**
	 * retourne une LocalDate 
	 * fixée au 1er janvier de l'année pAnnee.<br/>
	 * <br/>
	 * - retourne null si pAnnee est blank.<br/>
	 * - retourne null si pAnnee ne peut être parsée en entier.<br/>
	 * <br/>
	 *
	 * @param pAnnee : String : année sur 4 caractères ("2018" par exemple).
	 * 
	 * @return : LocalDate : 2018-01-01 par exemple.<br/>
	 */
	private LocalDate fournirDateAuPremierJanvier(
			final String pAnnee) {
		
		/* retourne null si pAnnee est blank. */
		if (StringUtils.isBlank(pAnnee)) {
			return null;
		}
		
		LocalDate resultat = null;
		Integer annee = null;
		
		try {
			
			annee = Integer.parseInt(pAnnee);
			resultat = LocalDate.of(annee, 01, 01);
		
		/* retourne null si pAnnee ne peut être parsée en entier. */
		} catch (NumberFormatException e) {
			resultat = null;
		}
		
		return resultat;
		
	} // Fin de fournirDateAuPremierJanvier(...).__________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.getAnneeGestion());
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
		
		if (!(pObjet instanceof IAnneeGestion)) {
			return false;
		}
		
		final IAnneeGestion other = (IAnneeGestion) pObjet;
		
		return Objects.equals(
				this.getAnneeGestion(), other.getAnneeGestion());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final IAnneeGestion pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareAnneeGestion = 0;
		
		/* anneeGestion. */
		if (this.getAnneeGestion() == null) {
			if (pObjet.getAnneeGestion() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getAnneeGestion() == null) {
			return -1;
		}
		
		compareAnneeGestion 
			= this.getAnneeGestion()
				.compareToIgnoreCase(pObjet.getAnneeGestion());
		
		return compareAnneeGestion;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final AnneeGestionEntityJPA clone() throws CloneNotSupportedException {
		
		final IAnneeGestion clone 
			= (IAnneeGestion) super.clone();
				
		clone.setId(this.getId());
		clone.setAnneeGestion(this.getAnneeGestion());
	
		return (AnneeGestionEntityJPA) clone;

	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("AnneeGestionEntityJPA [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(this.getId());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeGestion=");
		if (this.getAnneeGestion() != null) {
			stb.append(this.getAnneeGestion());
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
		
		return "id;anneeGestion;";
		
	} // Fin de fournirEnTeteCsv().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAnneeGestion());
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
			valeur = String.valueOf(this.getId());
			break;

		case 1:
			valeur = this.getAnneeGestion();
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
	@Column(name="ANNEEGESTION"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getAnneeGestion() {
		return this.anneeGestion;
	} // Fin de getAnneeGestion()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeGestion(
			final String pAnneeGestion) {
		
		this.anneeGestion = pAnneeGestion;
		
		this.anneeGestionDate 
			= this.fournirDateAuPremierJanvier(this.anneeGestion);
		
	} // Fin de setAnneeGestion(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public final LocalDate getAnneeGestionDate() {
		return this.anneeGestionDate;
	} // Fin de getAnneeGestionDate()._____________________________________
	
	
	
} // FIN DE LA CLASSE AnneeGestionEntityJPA.---------------------------------
