package levy.daniel.application.model.persistence.metier.sections.entities.jpa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.localisations.ILocalisationHit;
import levy.daniel.application.model.persistence.metier.sections.localisations.entities.jpa.LocalisationHitEntityJPA;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.IImportateurDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;

/**
 * CLASSE SectionHitEntityJPA :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * parser une Date, parser une LocalDate, fournir une année à 2 chiffres,<br/>
 * fournir une annee à 2 chiffres, afficher une date sur deux chiffres, <br/>
 * afficher une date sur deux caractères,<br/>
 * Regex, REGEX, Regex 2 chiffres, Regex annee sur deux chiffres, <br/>
 * motif Regex année sur 2 chiffres, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 29 juin 2019
 *
 */
@Entity
@Table(name="SECTIONHITS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_EQUALS_SECTIONHITS"
, columnNames={"ID_LOCALISATION_HIT", "NUMSECTION", "SENS", "NATURE"
		, "CLASSE"
		, "ANNEETRAITEMENT"
		, "MJAN", "PCPLN", "PCNUITANNUELN"
		, "MJMNMOIS01"
		, "MJMNMOIS02"
		, "MJMNMOIS03"
		, "MJMNMOIS04"
		, "MJMNMOIS05"
		, "MJMNMOIS06"
		, "MJMNMOIS07"
		, "MJMNMOIS08"
		, "MJMNMOIS09"
		, "MJMNMOIS10"
		, "MJMNMOIS11"
		, "MJMNMOIS12"})
, indexes={@Index(name="INDEX_ID_LOCALISATION_HIT_ANNEETRAITEMENT_SENS"
, columnList="ID_LOCALISATION_HIT, ANNEETRAITEMENT, SENS")})
public class SectionHitEntityJPA implements ISectionHit {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * "Classe SectionHitEntityJPA".
	 */
	public static final String CLASSE_SECTION_HIT 
		= "Classe SectionHitEntityJPA";
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * Importateur de la description du fichier HIT.
	 */
	private final transient IImportateurDescription descriptionFichier;
	
	/**
	 * id en base sous forme de Long.<br/>
	 */
	private Long id;
	
	/**
	 * numéro de département.<br/>
	 */
	private String numDepartement;
	
	/**
	 * numéro de section.<br/>
	 */
	private String numSection;

	/**
	 * sens de trafic.<br/>
	 */
	private String sens;
	
	/**
	 * nature du comptage.<br/>
	 */
	private String nature;
	
	/**
	 * classe du trafic.<br/>
	 */
	private String classe;
	
	/**
	 * année de traitement.<br/>
	 */
	private LocalDate anneeTraitement;
	
	/**
	 * zone libre 1.<br/>
	 */
	private String zoneLibre1;
	
	/**
	 * numero de la route.
	 */
	private String numRoute;
	
	/**
	 * indice numérique de la route.
	 */
	private String indiceNumRoute;
	
	/**
	 * indice lettre de la route.
	 */
	private String indiceLettreRoute;
	
	/**
	 * catégorie administrative de la route.
	 */
	private String categorieAdminRoute;
	
	/**
	 * type de comptage.
	 */
	private String typeComptage;
	
	/**
	 * classement de la route (agglomération).
	 */
	private String classementRoute;
	
	/**
	 * classe de largeur de chaussée unique.
	 */
	private String classeLargeurChausseeU;
	
	/**
	 * classe de largeur de chaussées séparées.
	 */
	private String classeLargeurChausseesS;
	
	/**
	 * type de réseau.
	 */
	private String typeReseau;
	
	/**
	 * type de localisation (PR ou PK).
	 */
	private String pRoupK;  // NOPMD by daniel.levy on 15/07/19 09:26
	
	/**
	 * libellé du lieu-dit origine.
	 */
	private String lieuDitOrigine;
	
	/**
	 * PR Origine.
	 */
	private Integer prOrigine;
	
	/**
	 * abscisse du point origine.
	 */
	private Integer absOrigine;
	
	/**
	 * libellé du lieu-dit extremité.
	 */
	private String lieuDitExtremite;
	
	/**
	 * PR Extremité.
	 */
	private Integer prExtremite;
	
	/**
	 * abscisse du point extremité.
	 */
	private Integer absExtremite;
	
	/**
	 * libellé du lieu-dit du point de comptage.
	 */
	private String lieuDitComptage;
	
	/**
	 * PR du point de comptage.
	 */
	private Integer prComptage;
	
	/**
	 * abscisse du point de comptage.
	 */
	private Integer absComptage;
	
	/**
	 * longueur de la section en mètres.
	 */
	private Integer longueurSection;
	
	/**
	 * longueur en rase campagne en mètres.
	 */
	private Integer longueurRaseCampagne;
	
	/**
	 * numéro de département de la section de Rattachement.
	 */
	private String numDepartementRattachement;
	
	/**
	 * numéro de la section de Rattachement.
	 */
	private String numSectionRattachement;
	
	/**
	 * sens de la section de Rattachement.
	 */
	private String sensRattachement;
	
	/**
	 * numéro de département de la section Limitrophe.
	 */
	private String numDepartementLimitrophe;
	
	/**
	 * numéro de la section Limitrophe.
	 */
	private String numSectionLimitrophe;
	
	/**
	 * sens de la section Limitrophe.
	 */
	private String sensLimitrophe;
	
	/**
	 * mois de sectionnement.
	 */
	private String moisSectionnement;
	
	/**
	 * annee de sectionnement.
	 */
	private String anneeSectionnement;
	
	/**
	 * zone libre 2.
	 */
	private String zoneLibre2;
	
	/**
	 * trafic moyen journalier annuel de l'année de traitement N.
	 */
	private Integer mjaN;
	
	/**
	 * mode de calcul des trafics de l'année de traitement N.
	 */
	private String modeCalculN;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année de traitement N.
	 */
	private String pcPLN;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année de traitement N.
	 */
	private String evaluationPLN;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année de traitement N.
	 */
	private String pcNuitAnnuelN;
	
	/**
	 * indice de fiabilité de la TMJA de l'année de traitement N.
	 */
	private String indiceFiabiliteMjaN;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) de l'année de traitement N.
	 */
	private Integer mjmNmois01;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de janvier (01) de l'année de traitement N.
	 */
	private String pcNuitNmois01;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) de l'année de traitement N.
	 */
	private Integer mjmNmois02;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de février (02) de l'année de traitement N.
	 */
	private String pcNuitNmois02;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) de l'année de traitement N.
	 */
	private Integer mjmNmois03;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mars (03) de l'année de traitement N.
	 */
	private String pcNuitNmois03;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) de l'année de traitement N.
	 */
	private Integer mjmNmois04;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de avril (04) de l'année de traitement N.
	 */
	private String pcNuitNmois04;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) de l'année de traitement N.
	 */
	private Integer mjmNmois05;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de mai (05) de l'année de traitement N.
	 */
	private String pcNuitNmois05;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) de l'année de traitement N.
	 */
	private Integer mjmNmois06;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juin (06) de l'année de traitement N.
	 */
	private String pcNuitNmois06;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) de l'année de traitement N.
	 */
	private Integer mjmNmois07;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de juillet (07) de l'année de traitement N.
	 */
	private String pcNuitNmois07;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) de l'année de traitement N.
	 */
	private Integer mjmNmois08;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de août (08) de l'année de traitement N.
	 */
	private String pcNuitNmois08;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) de l'année de traitement N.
	 */
	private Integer mjmNmois09;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de septembre (09) de l'année de traitement N.
	 */
	private String pcNuitNmois09;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) de l'année de traitement N.
	 */
	private Integer mjmNmois10;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de octobre (10) de l'année de traitement N.
	 */
	private String pcNuitNmois10;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) de l'année de traitement N.
	 */
	private Integer mjmNmois11;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de novembre (11) de l'année de traitement N.
	 */
	private String pcNuitNmois11;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) de l'année de traitement N.
	 */
	private Integer mjmNmois12;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel
	 * du mois de décembre (12) de l'année de traitement N.
	 */
	private String pcNuitNmois12;
	
	/**
	 * zone libre 3.
	 */
	private String zoneLibre3;
	
	/**
	 * année n-1.
	 */
	private LocalDate anneeNmoins1;
	
	/**
	 * trafic moyen journalier annuel de l'année n-1.
	 */
	private Integer mjaNmoins1;
	
	/**
	 * type de comptage de l'année n-1.
	 */
	private String typeComptageNmoins1;
	
	/**
	 * mode de calcul des trafics de l'année n-1.
	 */
	private String modeCalculNmoins1;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-1.
	 */
	private String pcPLNmoins1;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-1.
	 */
	private String evaluationPLNmoins1;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-1.
	 */
	private String pcNuitAnnuelNmoins1;
	
	/**
	 * indice de fiabilité de la TMJA 
	 * de l'année n-1.
	 */
	private String indiceFiabiliteMjaNmoins1;
	
	/**
	 * année n-2.
	 */
	private LocalDate anneeNmoins2;
	
	/**
	 * trafic moyen journalier annuel de l'année n-2.
	 */
	private Integer mjaNmoins2;
	
	/**
	 * type de comptage de l'année n-2.
	 */
	private String typeComptageNmoins2;
	
	/**
	 * mode de calcul des trafics de l'année n-2.
	 */
	private String modeCalculNmoins2;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-2.
	 */
	private String pcPLNmoins2;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-2.
	 */
	private String evaluationPLNmoins2;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-2.
	 */
	private String pcNuitAnnuelNmoins2;
	
	/**
	 * indice de fiabilité de la TMJA 
	 * de l'année n-2.
	 */
	private String indiceFiabiliteMjaNmoins2;
	
	/**
	 * année n-3.
	 */
	private LocalDate anneeNmoins3;
	
	/**
	 * trafic moyen journalier annuel de l'année n-3.
	 */
	private Integer mjaNmoins3;
	
	/**
	 * type de comptage de l'année n-3.
	 */
	private String typeComptageNmoins3;
	
	/**
	 * mode de calcul des trafics de l'année n-3.
	 */
	private String modeCalculNmoins3;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-3.
	 */
	private String pcPLNmoins3;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-3.
	 */
	private String evaluationPLNmoins3;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-3.
	 */
	private String pcNuitAnnuelNmoins3;
	
	/**
	 * indice de fiabilité de la TMJA 
	 * de l'année n-3.
	 */
	private String indiceFiabiliteMjaNmoins3;
	
	/**
	 * année n-4.
	 */
	private LocalDate anneeNmoins4;
	
	/**
	 * trafic moyen journalier annuel de l'année n-4.
	 */
	private Integer mjaNmoins4;
	
	/**
	 * type de comptage de l'année n-4.
	 */
	private String typeComptageNmoins4;
	
	/**
	 * mode de calcul des trafics de l'année n-4.
	 */
	private String modeCalculNmoins4;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-4.
	 */
	private String pcPLNmoins4;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-4.
	 */
	private String evaluationPLNmoins4;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-4.
	 */
	private String pcNuitAnnuelNmoins4;
	
	/**
	 * indice de fiabilité de la TMJA 
	 * de l'année n-4.
	 */
	private String indiceFiabiliteMjaNmoins4;
	
	/**
	 * année n-5.
	 */
	private LocalDate anneeNmoins5;
	
	/**
	 * trafic moyen journalier annuel de l'année n-5.
	 */
	private Integer mjaNmoins5;
	
	/**
	 * type de comptage de l'année n-5.
	 */
	private String typeComptageNmoins5;
	
	/**
	 * mode de calcul des trafics de l'année n-5.
	 */
	private String modeCalculNmoins5;
	
	/**
	 * pourcentage de trafic poids lourds annuel de l'année n-5.
	 */
	private String pcPLNmoins5;
	
	/**
	 * moyen d'évaluation du pourcentage de trafic poids lourds annuel 
	 * de l'année n-5.
	 */
	private String evaluationPLNmoins5;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules annuel 
	 * de l'année n-5.
	 */
	private String pcNuitAnnuelNmoins5;
	
	/**
	 * indice de fiabilité de la TMJA 
	 * de l'année n-5.
	 */
	private String indiceFiabiliteMjaNmoins5;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de janvier (01) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois01;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de janvier (01) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois01;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de février (02) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois02;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de février (02) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois02;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mars (03) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois03;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de mars (03) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois03;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de avril (04) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois04;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de avril (04) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois04;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de mai (05) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois05;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de mai (05) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois05;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juin (06) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois06;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de juin (06) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois06;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de juillet (07) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois07;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de juillet (07) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois07;
	
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de août (08) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois08;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de août (08) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois08;
		
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de septembre (09) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois09;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de septembre (09) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois09;
		
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de octobre (10) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois10;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de octobre (10) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois10;
		
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de novembre (11) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois11;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de novembre (11) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois11;
		
	/**
	 * moyenne journalière mensuelle en véhicules/jour 
	 * du mois de décembre (12) 
	 * de l'année précédent l'année de traitement N.
	 */
	private Integer mjmNmoins1mois12;
	
	/**
	 * pourcentage de trafic de nuit tous véhicules mensuel 
	 * du mois de décembre (12) 
	 * de l'année précédent l'année de traitement N.
	 */
	private String pcNuitNmoins1mois12;

	/**
	 * zone libre 4.
	 */
	private String zoneLibre4;

	/**
	 * Localisation de la section.<br/>
	 * COMPOSANT.<br/>
	 */
	private ILocalisationHit localisation = new LocalisationHitEntityJPA();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SectionHitEntityJPA.class);

	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * <ul>
	 * <li>alimente <code><b>this.descriptionFichier</b></code>.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public SectionHitEntityJPA() throws Exception {
		
		super();
		
		/* alimente this.descriptionFichier. */
		FactoryDescription.getDecriptionHitMap();		
		this.descriptionFichier = FactoryDescription.getImportateurHit();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
		
	 /**
	 * CONSTRUCTEUR CONVERTISSEUR.<br/>
	 * Instancie un OBJET METIER à partir d'une SortedMap&lt;Integer, String&gt; 
	 * description de ligne d'un fichier HIT.<br/>
	 * <ul>
	 * <li>alimente <code><b>this.descriptionFichier</b></code>.</li>
	 * </ul>
	 * - LOG.fatal et jette une RunTimeException 
	 * si pDescriptionLigne == null.<br/>
	 * </br/>
	 * 
	 * 
	 * @param pDescriptionLigne : SortedMap&lt;Integer, String&gt;
	 * 
	 * @throws Exception 
	 */
	public SectionHitEntityJPA(final SortedMap<Integer, String> pDescriptionLigne) 
			throws Exception {
		
		super();
		
		/* LOG.fatal et jette une RunTimeException 
		 * si pDescriptionLigne == null. */
		if (pDescriptionLigne == null) {
			
			final String message 
				= "Impossible d'instancier un SectionHitEntityJPA à partir "
						+ "d'une SortedMap<Integer, String> "
						+ "pDescriptionLigne null";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			throw new RuntimeException(message);
		}
		
		/* alimente this.descriptionFichier. */
		FactoryDescription.getDecriptionHitMap();		
		this.descriptionFichier = FactoryDescription.getImportateurHit();

		this.setId(null);
		this.setNumDepartement(pDescriptionLigne.get(1));
		this.setNumSection(pDescriptionLigne.get(2));
		this.setSens(pDescriptionLigne.get(3));
		this.setNature(pDescriptionLigne.get(4));
		this.setClasse(pDescriptionLigne.get(5));
		this.setAnneeTraitement(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(6)));
		this.setZoneLibre1(pDescriptionLigne.get(7));
		this.setNumRoute(pDescriptionLigne.get(8));
		this.setIndiceNumRoute(pDescriptionLigne.get(9));
		this.setIndiceLettreRoute(pDescriptionLigne.get(10));
		this.setCategorieAdminRoute(pDescriptionLigne.get(11));
		this.setTypeComptage(pDescriptionLigne.get(12));
		this.setClassementRoute(pDescriptionLigne.get(13));
		this.setClasseLargeurChausseeU(pDescriptionLigne.get(14));
		this.setClasseLargeurChausseesS(pDescriptionLigne.get(15));
		this.setTypeReseau(pDescriptionLigne.get(16));
		this.setpRoupK(pDescriptionLigne.get(17));
		this.setLieuDitOrigine(pDescriptionLigne.get(18));
		this.setPrOrigine(this.fournirInteger(pDescriptionLigne.get(19)));
		this.setAbsOrigine(this.fournirInteger(pDescriptionLigne.get(20)));
		this.setLieuDitExtremite(pDescriptionLigne.get(21));
		this.setPrExtremite(this.fournirInteger(pDescriptionLigne.get(22)));
		this.setAbsExtremite(this.fournirInteger(pDescriptionLigne.get(23)));
		this.setLieuDitComptage(pDescriptionLigne.get(24));
		this.setPrComptage(this.fournirInteger(pDescriptionLigne.get(25)));
		this.setAbsComptage(this.fournirInteger(pDescriptionLigne.get(26)));
		this.setLongueurSection(
				this.fournirInteger(pDescriptionLigne.get(27)));
		this.setLongueurRaseCampagne(
				this.fournirInteger(pDescriptionLigne.get(28)));
		this.setNumDepartementRattachement(pDescriptionLigne.get(29));
		this.setNumSectionRattachement(pDescriptionLigne.get(30));
		this.setSensRattachement(pDescriptionLigne.get(31));
		this.setNumDepartementLimitrophe(pDescriptionLigne.get(32));
		this.setNumSectionLimitrophe(pDescriptionLigne.get(33));
		this.setSensLimitrophe(pDescriptionLigne.get(34));
		this.setMoisSectionnement(pDescriptionLigne.get(35));
		this.setAnneeSectionnement(pDescriptionLigne.get(36));
		this.setZoneLibre2(pDescriptionLigne.get(37));
		this.setMjaN(this.fournirInteger(pDescriptionLigne.get(38)));
		this.setModeCalculN(pDescriptionLigne.get(39));
		this.setPcPLN(pDescriptionLigne.get(40));
		this.setEvaluationPLN(pDescriptionLigne.get(41));
		this.setPcNuitAnnuelN(pDescriptionLigne.get(42));
		this.setIndiceFiabiliteMjaN(pDescriptionLigne.get(43));
		this.setMjmNmois01(this.fournirInteger(pDescriptionLigne.get(44)));
		this.setPcNuitNmois01(pDescriptionLigne.get(45));
		this.setMjmNmois02(this.fournirInteger(pDescriptionLigne.get(46)));
		this.setPcNuitNmois02(pDescriptionLigne.get(47));
		this.setMjmNmois03(this.fournirInteger(pDescriptionLigne.get(48)));
		this.setPcNuitNmois03(pDescriptionLigne.get(49));
		this.setMjmNmois04(this.fournirInteger(pDescriptionLigne.get(50)));
		this.setPcNuitNmois04(pDescriptionLigne.get(51));
		this.setMjmNmois05(this.fournirInteger(pDescriptionLigne.get(52)));
		this.setPcNuitNmois05(pDescriptionLigne.get(53));
		this.setMjmNmois06(this.fournirInteger(pDescriptionLigne.get(54)));
		this.setPcNuitNmois06(pDescriptionLigne.get(55));
		this.setMjmNmois07(this.fournirInteger(pDescriptionLigne.get(56)));
		this.setPcNuitNmois07(pDescriptionLigne.get(57));
		this.setMjmNmois08(this.fournirInteger(pDescriptionLigne.get(58)));
		this.setPcNuitNmois08(pDescriptionLigne.get(59));
		this.setMjmNmois09(this.fournirInteger(pDescriptionLigne.get(60)));
		this.setPcNuitNmois09(pDescriptionLigne.get(61));
		this.setMjmNmois10(this.fournirInteger(pDescriptionLigne.get(62)));
		this.setPcNuitNmois10(pDescriptionLigne.get(63));
		this.setMjmNmois11(this.fournirInteger(pDescriptionLigne.get(64)));
		this.setPcNuitNmois11(pDescriptionLigne.get(65));
		this.setMjmNmois12(this.fournirInteger(pDescriptionLigne.get(66)));
		this.setPcNuitNmois12(pDescriptionLigne.get(67));
		this.setZoneLibre3(pDescriptionLigne.get(68));
		this.setAnneeNmoins1(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(69)));
		this.setMjaNmoins1(this.fournirInteger(pDescriptionLigne.get(70)));
		this.setTypeComptageNmoins1(pDescriptionLigne.get(71));
		this.setModeCalculNmoins1(pDescriptionLigne.get(72));
		this.setPcPLNmoins1(pDescriptionLigne.get(73));
		this.setEvaluationPLNmoins1(pDescriptionLigne.get(74));
		this.setPcNuitAnnuelNmoins1(pDescriptionLigne.get(75));
		this.setIndiceFiabiliteMjaNmoins1(pDescriptionLigne.get(76));
		this.setAnneeNmoins2(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(77)));
		this.setMjaNmoins2(this.fournirInteger(pDescriptionLigne.get(78)));
		this.setTypeComptageNmoins2(pDescriptionLigne.get(79));
		this.setModeCalculNmoins2(pDescriptionLigne.get(80));
		this.setPcPLNmoins2(pDescriptionLigne.get(81));
		this.setEvaluationPLNmoins2(pDescriptionLigne.get(82));
		this.setPcNuitAnnuelNmoins2(pDescriptionLigne.get(83));
		this.setIndiceFiabiliteMjaNmoins2(pDescriptionLigne.get(84));
		this.setAnneeNmoins3(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(85)));
		this.setMjaNmoins3(this.fournirInteger(pDescriptionLigne.get(86)));
		this.setTypeComptageNmoins3(pDescriptionLigne.get(87));
		this.setModeCalculNmoins3(pDescriptionLigne.get(88));
		this.setPcPLNmoins3(pDescriptionLigne.get(89));
		this.setEvaluationPLNmoins3(pDescriptionLigne.get(90));
		this.setPcNuitAnnuelNmoins3(pDescriptionLigne.get(91));
		this.setIndiceFiabiliteMjaNmoins3(pDescriptionLigne.get(92));
		this.setAnneeNmoins4(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(93)));
		this.setMjaNmoins4(this.fournirInteger(pDescriptionLigne.get(94)));
		this.setTypeComptageNmoins4(pDescriptionLigne.get(95));
		this.setModeCalculNmoins4(pDescriptionLigne.get(96));
		this.setPcPLNmoins4(pDescriptionLigne.get(97));
		this.setEvaluationPLNmoins4(pDescriptionLigne.get(98));
		this.setPcNuitAnnuelNmoins4(pDescriptionLigne.get(99));
		this.setIndiceFiabiliteMjaNmoins4(pDescriptionLigne.get(100));
		this.setAnneeNmoins5(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(101)));
		this.setMjaNmoins5(this.fournirInteger(pDescriptionLigne.get(102)));
		this.setTypeComptageNmoins5(pDescriptionLigne.get(103));
		this.setModeCalculNmoins5(pDescriptionLigne.get(104));
		this.setPcPLNmoins5(pDescriptionLigne.get(105));
		this.setEvaluationPLNmoins5(pDescriptionLigne.get(106));
		this.setPcNuitAnnuelNmoins5(pDescriptionLigne.get(107));
		this.setIndiceFiabiliteMjaNmoins5(pDescriptionLigne.get(108));
		this.setMjmNmoins1mois01(this.fournirInteger(pDescriptionLigne.get(109)));
		this.setPcNuitNmoins1mois01(pDescriptionLigne.get(110));
		this.setMjmNmoins1mois02(this.fournirInteger(pDescriptionLigne.get(111)));
		this.setPcNuitNmoins1mois02(pDescriptionLigne.get(112));
		this.setMjmNmoins1mois03(this.fournirInteger(pDescriptionLigne.get(113)));
		this.setPcNuitNmoins1mois03(pDescriptionLigne.get(114));
		this.setMjmNmoins1mois04(this.fournirInteger(pDescriptionLigne.get(115)));
		this.setPcNuitNmoins1mois04(pDescriptionLigne.get(116));
		this.setMjmNmoins1mois05(this.fournirInteger(pDescriptionLigne.get(117)));
		this.setPcNuitNmoins1mois05(pDescriptionLigne.get(118));
		this.setMjmNmoins1mois06(this.fournirInteger(pDescriptionLigne.get(119)));
		this.setPcNuitNmoins1mois06(pDescriptionLigne.get(120));
		this.setMjmNmoins1mois07(this.fournirInteger(pDescriptionLigne.get(121)));
		this.setPcNuitNmoins1mois07(pDescriptionLigne.get(122));
		this.setMjmNmoins1mois08(this.fournirInteger(pDescriptionLigne.get(123)));
		this.setPcNuitNmoins1mois08(pDescriptionLigne.get(124));
		this.setMjmNmoins1mois09(this.fournirInteger(pDescriptionLigne.get(125)));
		this.setPcNuitNmoins1mois09(pDescriptionLigne.get(126));
		this.setMjmNmoins1mois10(this.fournirInteger(pDescriptionLigne.get(127)));
		this.setPcNuitNmoins1mois10(pDescriptionLigne.get(128));
		this.setMjmNmoins1mois11(this.fournirInteger(pDescriptionLigne.get(129)));
		this.setPcNuitNmoins1mois11(pDescriptionLigne.get(130));
		this.setMjmNmoins1mois12(this.fournirInteger(pDescriptionLigne.get(131)));
		this.setPcNuitNmoins1mois12(pDescriptionLigne.get(132));
		this.setZoneLibre4(pDescriptionLigne.get(133));

	} // Fin de CONSTRUCTEUR CONVERTISSEUR.________________________________

	
	
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
	private LocalDate fournirDateAvecAnneeSurDeuxChiffres(
			final String pString) {
		
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
				= CLASSE_SECTION_HIT 
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
	private Integer fournirInteger(final String pString) {
		
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
				= CLASSE_SECTION_HIT
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
	private String fournirAnneeDeuxChiffresAPartirDate(final LocalDate pDate) {
		
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
	private String completerAvecZerosAGauche(
			final String pString, final int pTaille) {
		
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
		
	} // Fin de completerAvecZerosAGauche(...).____________________________
	

	
	/**
	 * retourne la longueur du champ de numéro d'ordre pNumeroChamp 
	 * dans la description de fichier 
	 * <code><b>this.descriptionFichier</b></code>.<br/>
	 * <br/>
	 * Par exemple : <code><b>fournirlongueurChamp(20)</b></code> 
	 * retourne 4 pour le champ absOrigine du HIT.
	 *
	 * @param pNumeroChamp : int : numéro d'ordre du champ dans la description.
	 * 
	 * @return : int : 
	 * longueur du champ d'ordre pNumeroChamp dans la description du fichier.
	 */
	private int fournirLongueurChamp(final int pNumeroChamp) {
		
		DescriptionChampHit description = null;
		int longueurChamp = 0;
		
		try {
			
			description 
			= (DescriptionChampHit) 
				this.descriptionFichier.getDescriptionChamp(pNumeroChamp);
			
			longueurChamp = description.getLongueur();
			
		} catch (Exception e) {

			final String message 
				= CLASSE_SECTION_HIT 
				+ MOINS_ESPACE 
				+ "Méthode fournirLongueurChamp(int pNumeroChamp)"
				+ MOINS_ESPACE
				+ "Impossible d'obtenir la description du champ d'ordre "
				+ pNumeroChamp;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}
			
			throw new RuntimeException(message, e);
		}

		return longueurChamp;
		
	} // Fin de fournirLongueurChamp(...)._________________________________
	

	
	/**
	 * alimente les attributs de la présente classe 
	 * concernés par le COMPOSANT METIER
	 * avec les mêmes attributs contenus dans le COMPOSANT METIER.<br/>
	 * <br/>
	 *
	 * @param pObject : ILocalisationHit : COMPOSANT METIER.<br/>
	 */
	private void alimenterAttributsLocalisation(
			final ILocalisationHit pObject) {
		
		if (pObject == null) {
			return;
		}
		
		this.setNumRoute(pObject.getNumRoute());
		this.setIndiceNumRoute(pObject.getIndiceNumRoute());
		this.setIndiceLettreRoute(pObject.getIndiceLettreRoute());
		this.setCategorieAdminRoute(pObject.getCategorieAdminRoute());
		this.setNumDepartement(pObject.getNumDepartement());
		this.setLieuDitOrigine(pObject.getLieuDitOrigine());
		this.setPrOrigine(pObject.getPrOrigine());
		this.setAbsOrigine(pObject.getAbsOrigine());
		this.setLieuDitExtremite(pObject.getLieuDitExtremite());
		this.setPrExtremite(pObject.getPrExtremite());
		this.setAbsExtremite(pObject.getAbsExtremite());
		this.setLieuDitComptage(pObject.getLieuDitComptage());
		this.setPrComptage(pObject.getPrComptage());
		this.setAbsComptage(pObject.getAbsComptage());
		
	} // Fin de alimenterAttributsLocalisation(...)._______________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {

		return Objects.hash(
			this.getNumDepartement(), this.getNumSection()
			, this.getSens(), this.getNature(), this.getClasse()
			, this.getAnneeTraitement(), this.getZoneLibre1(), this.getNumRoute()
			, this.getIndiceNumRoute(), this.getIndiceLettreRoute(), this.getCategorieAdminRoute()
			, this.getTypeComptage(), this.getClassementRoute(), this.getClasseLargeurChausseeU()
			, this.getClasseLargeurChausseesS(), this.getTypeReseau(), this.getpRoupK()
			, this.getLieuDitOrigine(), this.getPrOrigine(), this.getAbsOrigine()
			, this.getLieuDitExtremite(), this.getPrExtremite(), this.getAbsExtremite()
			, this.getLieuDitComptage(), this.getPrComptage(), this.getAbsComptage()
			, this.getLongueurSection(), this.getLongueurRaseCampagne(), this.getNumDepartementRattachement()
			, this.getNumSectionRattachement(), this.getSensRattachement(), this.getNumDepartementLimitrophe()
			, this.getNumSectionLimitrophe(), this.getSensLimitrophe(), this.getMoisSectionnement()
			, this.getAnneeSectionnement(), this.getZoneLibre2(), this.getMjaN()
			, this.getModeCalculN(), this.getPcPLN(), this.getEvaluationPLN()
			, this.getPcNuitAnnuelN(), this.getIndiceFiabiliteMjaN(), this.getMjmNmois01()
			, this.getPcNuitNmois01(), this.getMjmNmois02(), this.getPcNuitNmois02()
			, this.getMjmNmois03(), this.getPcNuitNmois03(), this.getMjmNmois04()
			, this.getPcNuitNmois04(), this.getMjmNmois05(), this.getPcNuitNmois05()
			, this.getMjmNmois06(), this.getPcNuitNmois06(), this.getMjmNmois07()
			, this.getPcNuitNmois07(), this.getMjmNmois08(), this.getPcNuitNmois08()
			, this.getMjmNmois09(), this.getPcNuitNmois09(), this.getMjmNmois10()
			, this.getPcNuitNmois10(), this.getMjmNmois11(), this.getPcNuitNmois11()
			, this.getMjmNmois12(), this.getPcNuitNmois12(), this.getZoneLibre3()
			, this.getAnneeNmoins1(), this.getMjaNmoins1(), this.getTypeComptageNmoins1()
			, this.getModeCalculNmoins1(), this.getPcPLNmoins1(), this.getEvaluationPLNmoins1()
			, this.getPcNuitAnnuelNmoins1(), this.getIndiceFiabiliteMjaNmoins1(), this.getAnneeNmoins2()
			, this.getMjaNmoins2(), this.getTypeComptageNmoins2(), this.getModeCalculNmoins2()
			, this.getPcPLNmoins2(), this.getEvaluationPLNmoins2(), this.getPcNuitAnnuelNmoins2()
			, this.getIndiceFiabiliteMjaNmoins2(), this.getAnneeNmoins3(), this.getMjaNmoins3()
			, this.getTypeComptageNmoins3(), this.getModeCalculNmoins3(), this.getPcPLNmoins3()
			, this.getEvaluationPLNmoins3(), this.getPcNuitAnnuelNmoins3(), this.getIndiceFiabiliteMjaNmoins3()
			, this.getAnneeNmoins4(), this.getMjaNmoins4(), this.getTypeComptageNmoins4()
			, this.getModeCalculNmoins4(), this.getPcPLNmoins4(), this.getEvaluationPLNmoins4()
			, this.getPcNuitAnnuelNmoins4(), this.getIndiceFiabiliteMjaNmoins4(), this.getAnneeNmoins5()
			, this.getMjaNmoins5(), this.getTypeComptageNmoins5(), this.getModeCalculNmoins5()
			, this.getPcPLNmoins5(), this.getEvaluationPLNmoins5(), this.getPcNuitAnnuelNmoins5()
			, this.getIndiceFiabiliteMjaNmoins5(), this.getMjmNmoins1mois01(), this.getPcNuitNmoins1mois01()
			, this.getMjmNmoins1mois02(), this.getPcNuitNmoins1mois02(), this.getMjmNmoins1mois03()
			, this.getPcNuitNmoins1mois03(), this.getMjmNmoins1mois04(), this.getPcNuitNmoins1mois04()
			, this.getMjmNmoins1mois05(), this.getPcNuitNmoins1mois05(), this.getMjmNmoins1mois06()
			, this.getPcNuitNmoins1mois06(), this.getMjmNmoins1mois07(), this.getPcNuitNmoins1mois07()
			, this.getMjmNmoins1mois08(), this.getPcNuitNmoins1mois08(), this.getMjmNmoins1mois09()
			, this.getPcNuitNmoins1mois09(), this.getMjmNmoins1mois10(), this.getPcNuitNmoins1mois10()
			, this.getMjmNmoins1mois11(), this.getPcNuitNmoins1mois11(), this.getMjmNmoins1mois12()
			, this.getPcNuitNmoins1mois12(), this.getZoneLibre4());

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
		if (!(pObjet instanceof ISectionHit)) {
			return false;
		}
		
		final ISectionHit other = (ISectionHit) pObjet;

		return Objects
			.equals(this.getNumDepartement(), other.getNumDepartement())
			&& Objects.equals(this.getNumSection(), other.getNumSection())
			&& Objects.equals(this.getSens(), other.getSens())
			&& Objects.equals(this.getNature(), other.getNature())
			&& Objects.equals(this.getClasse(), other.getClasse())
			&& Objects.equals(this.getAnneeTraitement(), other.getAnneeTraitement())
			&& Objects.equals(this.getZoneLibre1(), other.getZoneLibre1())
			&& Objects.equals(this.getNumRoute(), other.getNumRoute())
			&& Objects.equals(this.getIndiceNumRoute(), other.getIndiceNumRoute())
			&& Objects.equals(this.getIndiceLettreRoute(), other.getIndiceLettreRoute())
			&& Objects.equals(this.getCategorieAdminRoute(), other.getCategorieAdminRoute())
			&& Objects.equals(this.getTypeComptage(), other.getTypeComptage())
			&& Objects.equals(this.getClassementRoute(), other.getClassementRoute())
			&& Objects.equals(this.getClasseLargeurChausseeU(), other.getClasseLargeurChausseeU())
			&& Objects.equals(this.getClasseLargeurChausseesS(), other.getClasseLargeurChausseesS())
			&& Objects.equals(this.getTypeReseau(), other.getTypeReseau())
			&& Objects.equals(this.getpRoupK(), other.getpRoupK())
			&& Objects.equals(this.getLieuDitOrigine(), other.getLieuDitOrigine())
			&& Objects.equals(this.getPrOrigine(), other.getPrOrigine())
			&& Objects.equals(this.getAbsOrigine(), other.getAbsOrigine())
			&& Objects.equals(this.getLieuDitExtremite(), other.getLieuDitExtremite())
			&& Objects.equals(this.getPrExtremite(), other.getPrExtremite())
			&& Objects.equals(this.getAbsExtremite(), other.getAbsExtremite())
			&& Objects.equals(this.getLieuDitComptage(), other.getLieuDitComptage())
			&& Objects.equals(this.getPrComptage(), other.getPrComptage())
			&& Objects.equals(this.getAbsComptage(), other.getAbsComptage())
			&& Objects.equals(this.getLongueurSection(), other.getLongueurSection())
			&& Objects.equals(this.getLongueurRaseCampagne(), other.getLongueurRaseCampagne())
			&& Objects.equals(this.getNumDepartementRattachement(), other.getNumDepartementRattachement())
			&& Objects.equals(this.getNumSectionRattachement(), other.getNumSectionRattachement())
			&& Objects.equals(this.getSensRattachement(), other.getSensRattachement())
			&& Objects.equals(this.getNumDepartementLimitrophe(), other.getNumDepartementLimitrophe())
			&& Objects.equals(this.getNumSectionLimitrophe(), other.getNumSectionLimitrophe())
			&& Objects.equals(this.getSensLimitrophe(), other.getSensLimitrophe())
			&& Objects.equals(this.getMoisSectionnement(), other.getMoisSectionnement())
			&& Objects.equals(this.getAnneeSectionnement(), other.getAnneeSectionnement())
			&& Objects.equals(this.getZoneLibre2(), other.getZoneLibre2())
			&& Objects.equals(this.getMjaN(), other.getMjaN())
			&& Objects.equals(this.getModeCalculN(), other.getModeCalculN())
			&& Objects.equals(this.getPcPLN(), other.getPcPLN())
			&& Objects.equals(this.getEvaluationPLN(), other.getEvaluationPLN())
			&& Objects.equals(this.getPcNuitAnnuelN(), other.getPcNuitAnnuelN())
			&& Objects.equals(this.getIndiceFiabiliteMjaN(), other.getIndiceFiabiliteMjaN())
			&& Objects.equals(this.getMjmNmois01(), other.getMjmNmois01())
			&& Objects.equals(this.getPcNuitNmois01(), other.getPcNuitNmois01())
			&& Objects.equals(this.getMjmNmois02(), other.getMjmNmois02())
			&& Objects.equals(this.getPcNuitNmois02(), other.getPcNuitNmois02())
			&& Objects.equals(this.getMjmNmois03(), other.getMjmNmois03())
			&& Objects.equals(this.getPcNuitNmois03(), other.getPcNuitNmois03())
			&& Objects.equals(this.getMjmNmois04(), other.getMjmNmois04())
			&& Objects.equals(this.getPcNuitNmois04(), other.getPcNuitNmois04())
			&& Objects.equals(this.getMjmNmois05(), other.getMjmNmois05())
			&& Objects.equals(this.getPcNuitNmois05(), other.getPcNuitNmois05())
			&& Objects.equals(this.getMjmNmois06(), other.getMjmNmois06())
			&& Objects.equals(this.getPcNuitNmois06(), other.getPcNuitNmois06())
			&& Objects.equals(this.getMjmNmois07(), other.getMjmNmois07())
			&& Objects.equals(this.getPcNuitNmois07(), other.getPcNuitNmois07())
			&& Objects.equals(this.getMjmNmois08(), other.getMjmNmois08())
			&& Objects.equals(this.getPcNuitNmois08(), other.getPcNuitNmois08())
			&& Objects.equals(this.getMjmNmois09(), other.getMjmNmois09())
			&& Objects.equals(this.getPcNuitNmois09(), other.getPcNuitNmois09())
			&& Objects.equals(this.getMjmNmois10(), other.getMjmNmois10())
			&& Objects.equals(this.getPcNuitNmois10(), other.getPcNuitNmois10())
			&& Objects.equals(this.getMjmNmois11(), other.getMjmNmois11())
			&& Objects.equals(this.getPcNuitNmois11(), other.getPcNuitNmois11())
			&& Objects.equals(this.getMjmNmois12(), other.getMjmNmois12())
			&& Objects.equals(this.getPcNuitNmois12(), other.getPcNuitNmois12())
			&& Objects.equals(this.getZoneLibre3(), other.getZoneLibre3())
			&& Objects.equals(this.getAnneeNmoins1(), other.getAnneeNmoins1())
			&& Objects.equals(this.getMjaNmoins1(), other.getMjaNmoins1())
			&& Objects.equals(this.getTypeComptageNmoins1(), other.getTypeComptageNmoins1())
			&& Objects.equals(this.getModeCalculNmoins1(), other.getModeCalculNmoins1())
			&& Objects.equals(this.getPcPLNmoins1(), other.getPcPLNmoins1())
			&& Objects.equals(this.getEvaluationPLNmoins1(), other.getEvaluationPLNmoins1())
			&& Objects.equals(this.getPcNuitAnnuelNmoins1(), other.getPcNuitAnnuelNmoins1())
			&& Objects.equals(this.getIndiceFiabiliteMjaNmoins1(), other.getIndiceFiabiliteMjaNmoins1())
			&& Objects.equals(this.getAnneeNmoins2(), other.getAnneeNmoins2())
			&& Objects.equals(this.getMjaNmoins2(), other.getMjaNmoins2())
			&& Objects.equals(this.getTypeComptageNmoins2(), other.getTypeComptageNmoins2())
			&& Objects.equals(this.getModeCalculNmoins2(), other.getModeCalculNmoins2())
			&& Objects.equals(this.getPcPLNmoins2(), other.getPcPLNmoins2())
			&& Objects.equals(this.getEvaluationPLNmoins2(), other.getEvaluationPLNmoins2())
			&& Objects.equals(this.getPcNuitAnnuelNmoins2(), other.getPcNuitAnnuelNmoins2())
			&& Objects.equals(this.getIndiceFiabiliteMjaNmoins2(), other.getIndiceFiabiliteMjaNmoins2())
			&& Objects.equals(this.getAnneeNmoins3(), other.getAnneeNmoins3())
			&& Objects.equals(this.getMjaNmoins3(), other.getMjaNmoins3())
			&& Objects.equals(this.getTypeComptageNmoins3(), other.getTypeComptageNmoins3())
			&& Objects.equals(this.getModeCalculNmoins3(), other.getModeCalculNmoins3())
			&& Objects.equals(this.getPcPLNmoins3(), other.getPcPLNmoins3())
			&& Objects.equals(this.getEvaluationPLNmoins3(), other.getEvaluationPLNmoins3())
			&& Objects.equals(this.getPcNuitAnnuelNmoins3(), other.getPcNuitAnnuelNmoins3())
			&& Objects.equals(this.getIndiceFiabiliteMjaNmoins3(), other.getIndiceFiabiliteMjaNmoins3())
			&& Objects.equals(this.getAnneeNmoins4(), other.getAnneeNmoins4())
			&& Objects.equals(this.getMjaNmoins4(), other.getMjaNmoins4())
			&& Objects.equals(this.getTypeComptageNmoins4(), other.getTypeComptageNmoins4())
			&& Objects.equals(this.getModeCalculNmoins4(), other.getModeCalculNmoins4())
			&& Objects.equals(this.getPcPLNmoins4(), other.getPcPLNmoins4())
			&& Objects.equals(this.getEvaluationPLNmoins4(), other.getEvaluationPLNmoins4())
			&& Objects.equals(this.getPcNuitAnnuelNmoins4(), other.getPcNuitAnnuelNmoins4())
			&& Objects.equals(this.getIndiceFiabiliteMjaNmoins4(), other.getIndiceFiabiliteMjaNmoins4())
			&& Objects.equals(this.getAnneeNmoins5(), other.getAnneeNmoins5())
			&& Objects.equals(this.getMjaNmoins5(), other.getMjaNmoins5())
			&& Objects.equals(this.getTypeComptageNmoins5(), other.getTypeComptageNmoins5())
			&& Objects.equals(this.getModeCalculNmoins5(), other.getModeCalculNmoins5())
			&& Objects.equals(this.getPcPLNmoins5(), other.getPcPLNmoins5())
			&& Objects.equals(this.getEvaluationPLNmoins5(), other.getEvaluationPLNmoins5())
			&& Objects.equals(this.getPcNuitAnnuelNmoins5(), other.getPcNuitAnnuelNmoins5())
			&& Objects.equals(this.getIndiceFiabiliteMjaNmoins5(), other.getIndiceFiabiliteMjaNmoins5())
			&& Objects.equals(this.getMjmNmoins1mois01(), other.getMjmNmoins1mois01())
			&& Objects.equals(this.getPcNuitNmoins1mois01(), other.getPcNuitNmoins1mois01())
			&& Objects.equals(this.getMjmNmoins1mois02(), other.getMjmNmoins1mois02())
			&& Objects.equals(this.getPcNuitNmoins1mois02(), other.getPcNuitNmoins1mois02())
			&& Objects.equals(this.getMjmNmoins1mois03(), other.getMjmNmoins1mois03())
			&& Objects.equals(this.getPcNuitNmoins1mois03(), other.getPcNuitNmoins1mois03())
			&& Objects.equals(this.getMjmNmoins1mois04(), other.getMjmNmoins1mois04())
			&& Objects.equals(this.getPcNuitNmoins1mois04(), other.getPcNuitNmoins1mois04())
			&& Objects.equals(this.getMjmNmoins1mois05(), other.getMjmNmoins1mois05())
			&& Objects.equals(this.getPcNuitNmoins1mois05(), other.getPcNuitNmoins1mois05())
			&& Objects.equals(this.getMjmNmoins1mois06(), other.getMjmNmoins1mois06())
			&& Objects.equals(this.getPcNuitNmoins1mois06(), other.getPcNuitNmoins1mois06())
			&& Objects.equals(this.getMjmNmoins1mois07(), other.getMjmNmoins1mois07())
			&& Objects.equals(this.getPcNuitNmoins1mois07(), other.getPcNuitNmoins1mois07())
			&& Objects.equals(this.getMjmNmoins1mois08(), other.getMjmNmoins1mois08())
			&& Objects.equals(this.getPcNuitNmoins1mois08(), other.getPcNuitNmoins1mois08())
			&& Objects.equals(this.getMjmNmoins1mois09(), other.getMjmNmoins1mois09())
			&& Objects.equals(this.getPcNuitNmoins1mois09(), other.getPcNuitNmoins1mois09())
			&& Objects.equals(this.getMjmNmoins1mois10(), other.getMjmNmoins1mois10())
			&& Objects.equals(this.getPcNuitNmoins1mois10(), other.getPcNuitNmoins1mois10())
			&& Objects.equals(this.getMjmNmoins1mois11(), other.getMjmNmoins1mois11())
			&& Objects.equals(this.getPcNuitNmoins1mois11(), other.getPcNuitNmoins1mois11())
			&& Objects.equals(this.getMjmNmoins1mois12(), other.getMjmNmoins1mois12())
			&& Objects.equals(this.getPcNuitNmoins1mois12(), other.getPcNuitNmoins1mois12())
			&& Objects.equals(this.getZoneLibre4(), other.getZoneLibre4());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final ISectionHit pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareAnneeTraitement = 0;
		int compareNumDepartement = 0;
		int compareNumRoute = 0;
		int compareIndiceNumRoute = 0;
		int compareIndiceLettreRoute = 0;
		int compareCategorieAdminRoute = 0;
		int comparePrOrigine = 0;
		int compareAbsOrigine = 0;
		
		/* anneeTraitement. */
		if (this.getAnneeTraitement() == null) {
			if (pObjet.getAnneeTraitement() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getAnneeTraitement() == null) {
				return -1;
			}
			
			compareAnneeTraitement 
			= this.getAnneeTraitement()
				.compareTo(pObjet.getAnneeTraitement());
		
			if (compareAnneeTraitement != 0) {
				return compareAnneeTraitement;
			}
		}
		
		/* numDepartement. */
		if (this.getNumDepartement() == null) {
			if (pObjet.getNumDepartement() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getNumDepartement() == null) {
				return -1;
			}
			
			compareNumDepartement 
			= this.getNumDepartement()
				.compareToIgnoreCase(pObjet.getNumDepartement());
		
			if (compareNumDepartement != 0) {
				return compareNumDepartement;
			}
		}
		
		/* numeroRoute. */
		if (this.getNumRoute() == null) {
			if (pObjet.getNumRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getNumRoute() == null) {
				return -1;
			}
			
			compareNumRoute 
			= this.getNumRoute()
				.compareToIgnoreCase(pObjet.getNumRoute());
		
			if (compareNumRoute != 0) {
				return compareNumRoute;
			}
		}
		
		/* indiceNumRoute. */
		if (this.getIndiceNumRoute() == null) {
			if (pObjet.getIndiceNumRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getIndiceNumRoute() == null) {
				return -1;
			}
			
			compareIndiceNumRoute 
			= this.getIndiceNumRoute()
				.compareToIgnoreCase(pObjet.getIndiceNumRoute());
		
			if (compareIndiceNumRoute != 0) {
				return compareIndiceNumRoute;
			}
		}
		
		/* indiceLettreRoute. */
		if (this.getIndiceLettreRoute() == null) {
			if (pObjet.getIndiceLettreRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getIndiceLettreRoute() == null) {
				return -1;
			}
			
			compareIndiceLettreRoute 
			= this.getIndiceLettreRoute()
				.compareToIgnoreCase(pObjet.getIndiceLettreRoute());
		
			if (compareIndiceLettreRoute != 0) {
				return compareIndiceLettreRoute;
			}
		}
		
		/* categorieAdminRoute. */
		if (this.getCategorieAdminRoute() == null) {
			if (pObjet.getCategorieAdminRoute() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getCategorieAdminRoute() == null) {
				return -1;
			}
			
			compareCategorieAdminRoute 
			= this.getCategorieAdminRoute()
				.compareToIgnoreCase(pObjet.getCategorieAdminRoute());
		
			if (compareCategorieAdminRoute != 0) {
				return compareCategorieAdminRoute;
			}
		}
		
		/* prOrigine. */
		if (this.getPrOrigine() == null) {
			if (pObjet.getPrOrigine() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getPrOrigine() == null) {
				return -1;
			}
			
			comparePrOrigine 
			= this.getPrOrigine()
				.compareTo(pObjet.getPrOrigine());
		
			if (comparePrOrigine != 0) {
				return comparePrOrigine;
			}
		}
		
		/* absOrigine. */
		if (this.getAbsOrigine() == null) {
			if (pObjet.getAbsOrigine() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getAbsOrigine() == null) {
			return -1;
		}
		
		compareAbsOrigine 
			= this.getAbsOrigine()
				.compareTo(pObjet.getAbsOrigine());
		
		return compareAbsOrigine;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ISectionHit clone() throws CloneNotSupportedException {
		
		final ISectionHit clone 
			= (ISectionHit) super.clone();
				
		clone.setId(this.getId());
		clone.setNumDepartement(this.getNumDepartement());
		clone.setNumSection(this.getNumSection());
		clone.setSens(this.getSens());
		clone.setNature(this.getNature());
		clone.setClasse(this.getClasse());
		clone.setAnneeTraitement(this.getAnneeTraitement());
		clone.setZoneLibre1(this.getZoneLibre1());
		clone.setNumRoute(this.getNumRoute());
		clone.setIndiceNumRoute(this.getIndiceNumRoute());
		clone.setIndiceLettreRoute(this.getIndiceLettreRoute());
		clone.setCategorieAdminRoute(this.getCategorieAdminRoute());
		clone.setTypeComptage(this.getTypeComptage());
		clone.setClassementRoute(this.getClassementRoute());
		clone.setClasseLargeurChausseeU(this.getClasseLargeurChausseeU());
		clone.setClasseLargeurChausseesS(this.getClasseLargeurChausseesS());
		clone.setTypeReseau(this.getTypeReseau());
		clone.setpRoupK(this.getpRoupK());
		clone.setLieuDitOrigine(this.getLieuDitOrigine());
		clone.setPrOrigine(this.getPrOrigine());
		clone.setAbsOrigine(this.getAbsOrigine());
		clone.setLieuDitExtremite(this.getLieuDitExtremite());
		clone.setPrExtremite(this.getPrExtremite());
		clone.setAbsExtremite(this.getAbsExtremite());
		clone.setLieuDitComptage(this.getLieuDitComptage());
		clone.setPrComptage(this.getPrComptage());
		clone.setAbsComptage(this.getAbsComptage());
		clone.setLongueurSection(this.getLongueurSection());
		clone.setLongueurRaseCampagne(this.getLongueurRaseCampagne());
		clone.setNumDepartementRattachement(this.getNumDepartementRattachement());
		clone.setNumSectionRattachement(this.getNumSectionRattachement());
		clone.setSensRattachement(this.getSensRattachement());
		clone.setNumDepartementLimitrophe(this.getNumDepartementLimitrophe());
		clone.setNumSectionLimitrophe(this.getNumSectionLimitrophe());
		clone.setSensLimitrophe(this.getSensLimitrophe());
		clone.setMoisSectionnement(this.getMoisSectionnement());
		clone.setAnneeSectionnement(this.getAnneeSectionnement());
		clone.setZoneLibre2(this.getZoneLibre2());
		clone.setMjaN(this.getMjaN());
		clone.setModeCalculN(this.getModeCalculN());
		clone.setPcPLN(this.getPcPLN());
		clone.setEvaluationPLN(this.getEvaluationPLN());
		clone.setPcNuitAnnuelN(this.getPcNuitAnnuelN());
		clone.setIndiceFiabiliteMjaN(this.getIndiceFiabiliteMjaN());
		clone.setMjmNmois01(this.getMjmNmois01());
		clone.setPcNuitNmois01(this.getPcNuitNmois01());
		clone.setMjmNmois02(this.getMjmNmois02());
		clone.setPcNuitNmois02(this.getPcNuitNmois02());
		clone.setMjmNmois03(this.getMjmNmois03());
		clone.setPcNuitNmois03(this.getPcNuitNmois03());
		clone.setMjmNmois04(this.getMjmNmois04());
		clone.setPcNuitNmois04(this.getPcNuitNmois04());
		clone.setMjmNmois05(this.getMjmNmois05());
		clone.setPcNuitNmois05(this.getPcNuitNmois05());
		clone.setMjmNmois06(this.getMjmNmois06());
		clone.setPcNuitNmois06(this.getPcNuitNmois06());
		clone.setMjmNmois07(this.getMjmNmois07());
		clone.setPcNuitNmois07(this.getPcNuitNmois07());
		clone.setMjmNmois08(this.getMjmNmois08());
		clone.setPcNuitNmois08(this.getPcNuitNmois08());
		clone.setMjmNmois09(this.getMjmNmois09());
		clone.setPcNuitNmois09(this.getPcNuitNmois09());
		clone.setMjmNmois10(this.getMjmNmois10());
		clone.setPcNuitNmois10(this.getPcNuitNmois10());
		clone.setMjmNmois11(this.getMjmNmois11());
		clone.setPcNuitNmois11(this.getPcNuitNmois11());
		clone.setMjmNmois12(this.getMjmNmois12());
		clone.setPcNuitNmois12(this.getPcNuitNmois12());
		clone.setZoneLibre3(this.getZoneLibre3());
		clone.setAnneeNmoins1(this.getAnneeNmoins1());
		clone.setMjaNmoins1(this.getMjaNmoins1());
		clone.setTypeComptageNmoins1(this.getTypeComptageNmoins1());
		clone.setModeCalculNmoins1(this.getModeCalculNmoins1());
		clone.setPcPLNmoins1(this.getPcPLNmoins1());
		clone.setEvaluationPLNmoins1(this.getEvaluationPLNmoins1());
		clone.setPcNuitAnnuelNmoins1(this.getPcNuitAnnuelNmoins1());
		clone.setIndiceFiabiliteMjaNmoins1(this.getIndiceFiabiliteMjaNmoins1());
		clone.setAnneeNmoins2(this.getAnneeNmoins2());
		clone.setMjaNmoins2(this.getMjaNmoins2());
		clone.setTypeComptageNmoins2(this.getTypeComptageNmoins2());
		clone.setModeCalculNmoins2(this.getModeCalculNmoins2());
		clone.setPcPLNmoins2(this.getPcPLNmoins2());
		clone.setEvaluationPLNmoins2(this.getEvaluationPLNmoins2());
		clone.setPcNuitAnnuelNmoins2(this.getPcNuitAnnuelNmoins2());
		clone.setIndiceFiabiliteMjaNmoins2(this.getIndiceFiabiliteMjaNmoins2());
		clone.setAnneeNmoins3(this.getAnneeNmoins3());
		clone.setMjaNmoins3(this.getMjaNmoins3());
		clone.setTypeComptageNmoins3(this.getTypeComptageNmoins3());
		clone.setModeCalculNmoins3(this.getModeCalculNmoins3());
		clone.setPcPLNmoins3(this.getPcPLNmoins3());
		clone.setEvaluationPLNmoins3(this.getEvaluationPLNmoins3());
		clone.setPcNuitAnnuelNmoins3(this.getPcNuitAnnuelNmoins3());
		clone.setIndiceFiabiliteMjaNmoins3(this.getIndiceFiabiliteMjaNmoins3());
		clone.setAnneeNmoins4(this.getAnneeNmoins4());
		clone.setMjaNmoins4(this.getMjaNmoins4());
		clone.setTypeComptageNmoins4(this.getTypeComptageNmoins4());
		clone.setModeCalculNmoins4(this.getModeCalculNmoins4());
		clone.setPcPLNmoins4(this.getPcPLNmoins4());
		clone.setEvaluationPLNmoins4(this.getEvaluationPLNmoins4());
		clone.setPcNuitAnnuelNmoins4(this.getPcNuitAnnuelNmoins4());
		clone.setIndiceFiabiliteMjaNmoins4(this.getIndiceFiabiliteMjaNmoins4());
		clone.setAnneeNmoins5(this.getAnneeNmoins5());
		clone.setMjaNmoins5(this.getMjaNmoins5());
		clone.setTypeComptageNmoins5(this.getTypeComptageNmoins5());
		clone.setModeCalculNmoins5(this.getModeCalculNmoins5());
		clone.setPcPLNmoins5(this.getPcPLNmoins5());
		clone.setEvaluationPLNmoins5(this.getEvaluationPLNmoins5());
		clone.setPcNuitAnnuelNmoins5(this.getPcNuitAnnuelNmoins5());
		clone.setIndiceFiabiliteMjaNmoins5(this.getIndiceFiabiliteMjaNmoins5());
		clone.setMjmNmoins1mois01(this.getMjmNmoins1mois01());
		clone.setPcNuitNmoins1mois01(this.getPcNuitNmoins1mois01());
		clone.setMjmNmoins1mois02(this.getMjmNmoins1mois02());
		clone.setPcNuitNmoins1mois02(this.getPcNuitNmoins1mois02());
		clone.setMjmNmoins1mois03(this.getMjmNmoins1mois03());
		clone.setPcNuitNmoins1mois03(this.getPcNuitNmoins1mois03());
		clone.setMjmNmoins1mois04(this.getMjmNmoins1mois04());
		clone.setPcNuitNmoins1mois04(this.getPcNuitNmoins1mois04());
		clone.setMjmNmoins1mois05(this.getMjmNmoins1mois05());
		clone.setPcNuitNmoins1mois05(this.getPcNuitNmoins1mois05());
		clone.setMjmNmoins1mois06(this.getMjmNmoins1mois06());
		clone.setPcNuitNmoins1mois06(this.getPcNuitNmoins1mois06());
		clone.setMjmNmoins1mois07(this.getMjmNmoins1mois07());
		clone.setPcNuitNmoins1mois07(this.getPcNuitNmoins1mois07());
		clone.setMjmNmoins1mois08(this.getMjmNmoins1mois08());
		clone.setPcNuitNmoins1mois08(this.getPcNuitNmoins1mois08());
		clone.setMjmNmoins1mois09(this.getMjmNmoins1mois09());
		clone.setPcNuitNmoins1mois09(this.getPcNuitNmoins1mois09());
		clone.setMjmNmoins1mois10(this.getMjmNmoins1mois10());
		clone.setPcNuitNmoins1mois10(this.getPcNuitNmoins1mois10());
		clone.setMjmNmoins1mois11(this.getMjmNmoins1mois11());
		clone.setPcNuitNmoins1mois11(this.getPcNuitNmoins1mois11());
		clone.setMjmNmoins1mois12(this.getMjmNmoins1mois12());
		clone.setPcNuitNmoins1mois12(this.getPcNuitNmoins1mois12());
		clone.setZoneLibre4(this.getZoneLibre4());
	
		return (SectionHitEntityJPA) clone;

	} // Fin de clone().___________________________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder stb = new StringBuilder();

		stb.append("SectionHitEntityJPA [");

		stb.append("id=");
		if (this.getId() != null) {
			stb.append(Long.valueOf(this.getId()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numDepartement=");
		if (this.getNumDepartement() != null) {
			stb.append(this.getNumDepartement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numSection=");
		if (this.getNumSection() != null) {
			stb.append(this.getNumSection());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("sens=");
		if (this.getSens() != null) {
			stb.append(this.getSens());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("nature=");
		if (this.getNature() != null) {
			stb.append(this.getNature());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("classe=");
		if (this.getClasse() != null) {
			stb.append(this.getClasse());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeTraitement=");
		if (this.getAnneeTraitement() != null) {
			stb.append(
					this.fournirAnneeDeuxChiffresAPartirDate(
							this.getAnneeTraitement()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("zoneLibre1=");
		if (this.getZoneLibre1() != null) {
			stb.append(this.getZoneLibre1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numRoute=");
		if (this.getNumRoute() != null) {
			stb.append(this.getNumRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceNumRoute=");
		if (this.getIndiceNumRoute() != null) {
			stb.append(this.getIndiceNumRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceLettreRoute=");
		if (this.getIndiceLettreRoute() != null) {
			stb.append(this.getIndiceLettreRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("categorieAdminRoute=");
		if (this.getCategorieAdminRoute() != null) {
			stb.append(this.getCategorieAdminRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptage=");
		if (this.getTypeComptage() != null) {
			stb.append(this.getTypeComptage());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("classementRoute=");
		if (this.getClassementRoute() != null) {
			stb.append(this.getClassementRoute());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("classeLargeurChausseeU=");
		if (this.getClasseLargeurChausseeU() != null) {
			stb.append(this.getClasseLargeurChausseeU());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("classeLargeurChausseesS=");
		if (this.getClasseLargeurChausseesS() != null) {
			stb.append(this.getClasseLargeurChausseesS());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeReseau=");
		if (this.getTypeReseau() != null) {
			stb.append(this.getTypeReseau());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pRoupK=");
		if (this.getpRoupK() != null) {
			stb.append(this.getpRoupK());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("lieuDitOrigine=");
		if (this.getLieuDitOrigine() != null) {
			stb.append(this.getLieuDitOrigine());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prOrigine=");
		if (this.getPrOrigine() != null) {
			stb.append(String.valueOf(this.getPrOrigine()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absOrigine=");
		if (this.getAbsOrigine() != null) {
			stb.append(String.valueOf(this.getAbsOrigine()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("lieuDitExtremite=");
		if (this.getLieuDitExtremite() != null) {
			stb.append(this.getLieuDitExtremite());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prExtremite=");
		if (this.getPrExtremite() != null) {
			stb.append(String.valueOf(this.getPrExtremite()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absExtremite=");
		if (this.getAbsExtremite() != null) {
			stb.append(String.valueOf(this.getAbsExtremite()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("lieuDitComptage=");
		if (this.getLieuDitComptage() != null) {
			stb.append(this.getLieuDitComptage());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("prComptage=");
		if (this.getPrComptage() != null) {
			stb.append(String.valueOf(this.getPrComptage()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("absComptage=");
		if (this.getAbsComptage() != null) {
			stb.append(String.valueOf(this.getAbsComptage()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("longueurSection=");
		if (this.getLongueurSection() != null) {
			stb.append(String.valueOf(this.getLongueurSection()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("longueurRaseCampagne=");
		if (this.getLongueurRaseCampagne() != null) {
			stb.append(String.valueOf(this.getLongueurRaseCampagne()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numDepartementRattachement=");
		if (this.getNumDepartementRattachement() != null) {
			stb.append(this.getNumDepartementRattachement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numSectionRattachement=");
		if (this.getNumSectionRattachement() != null) {
			stb.append(this.getNumSectionRattachement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("sensRattachement=");
		if (this.getSensRattachement() != null) {
			stb.append(this.getSensRattachement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numDepartementLimitrophe=");
		if (this.getNumDepartementLimitrophe() != null) {
			stb.append(this.getNumDepartementLimitrophe());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("numSectionLimitrophe=");
		if (this.getNumSectionLimitrophe() != null) {
			stb.append(this.getNumSectionLimitrophe());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("sensLimitrophe=");
		if (this.getSensLimitrophe() != null) {
			stb.append(this.getSensLimitrophe());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("moisSectionnement=");
		if (this.getMoisSectionnement() != null) {
			stb.append(this.getMoisSectionnement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeSectionnement=");
		if (this.getAnneeSectionnement() != null) {
			stb.append(this.getAnneeSectionnement());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("zoneLibre2=");
		if (this.getZoneLibre2() != null) {
			stb.append(this.getZoneLibre2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaN=");
		if (this.getMjaN() != null) {
			stb.append(String.valueOf(this.getMjaN()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modeCalculN=");
		if (this.getModeCalculN() != null) {
			stb.append(this.getModeCalculN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLN=");
		if (this.getPcPLN() != null) {
			stb.append(this.getPcPLN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("evaluationPLN=");
		if (this.getEvaluationPLN() != null) {
			stb.append(this.getEvaluationPLN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelN=");
		if (this.getPcNuitAnnuelN() != null) {
			stb.append(this.getPcNuitAnnuelN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceFiabiliteMjaN=");
		if (this.getIndiceFiabiliteMjaN() != null) {
			stb.append(this.getIndiceFiabiliteMjaN());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois01=");
		if (this.getMjmNmois01() != null) {
			stb.append(String.valueOf(this.getMjmNmois01()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois01=");
		if (this.getPcNuitNmois01() != null) {
			stb.append(this.getPcNuitNmois01());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois02=");
		if (this.getMjmNmois02() != null) {
			stb.append(String.valueOf(this.getMjmNmois02()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois02=");
		if (this.getPcNuitNmois02() != null) {
			stb.append(this.getPcNuitNmois02());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois03=");
		if (this.getMjmNmois03() != null) {
			stb.append(String.valueOf(this.getMjmNmois03()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois03=");
		if (this.getPcNuitNmois03() != null) {
			stb.append(this.getPcNuitNmois03());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois04=");
		if (this.getMjmNmois04() != null) {
			stb.append(String.valueOf(this.getMjmNmois04()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois04=");
		if (this.getPcNuitNmois04() != null) {
			stb.append(this.getPcNuitNmois04());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois05=");
		if (this.getMjmNmois05() != null) {
			stb.append(String.valueOf(this.getMjmNmois05()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois05=");
		if (this.getPcNuitNmois05() != null) {
			stb.append(this.getPcNuitNmois05());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois06=");
		if (this.getMjmNmois06() != null) {
			stb.append(String.valueOf(this.getMjmNmois06()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois06=");
		if (this.getPcNuitNmois06() != null) {
			stb.append(this.getPcNuitNmois06());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois07=");
		if (this.getMjmNmois07() != null) {
			stb.append(String.valueOf(this.getMjmNmois07()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois07=");
		if (this.getPcNuitNmois07() != null) {
			stb.append(this.getPcNuitNmois07());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois08=");
		if (this.getMjmNmois08() != null) {
			stb.append(String.valueOf(this.getMjmNmois08()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois08=");
		if (this.getPcNuitNmois08() != null) {
			stb.append(this.getPcNuitNmois08());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois09=");
		if (this.getMjmNmois09() != null) {
			stb.append(String.valueOf(this.getMjmNmois09()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois09=");
		if (this.getPcNuitNmois09() != null) {
			stb.append(this.getPcNuitNmois09());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois10=");
		if (this.getMjmNmois10() != null) {
			stb.append(String.valueOf(this.getMjmNmois10()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois10=");
		if (this.getPcNuitNmois10() != null) {
			stb.append(this.getPcNuitNmois10());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois11=");
		if (this.getMjmNmois11() != null) {
			stb.append(String.valueOf(this.getMjmNmois11()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois11=");
		if (this.getPcNuitNmois11() != null) {
			stb.append(this.getPcNuitNmois11());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmois12=");
		if (this.getMjmNmois12() != null) {
			stb.append(String.valueOf(this.getMjmNmois12()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmois12=");
		if (this.getPcNuitNmois12() != null) {
			stb.append(this.getPcNuitNmois12());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("zoneLibre3=");
		if (this.getZoneLibre3() != null) {
			stb.append(this.getZoneLibre3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeNmoins1=");
		if (this.getAnneeNmoins1() != null) {
			stb.append(
					this.fournirAnneeDeuxChiffresAPartirDate(
							this.getAnneeNmoins1()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins1=");
		if (this.getMjaNmoins1() != null) {
			stb.append(String.valueOf(this.getMjaNmoins1()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptageNmoins1=");
		if (this.getTypeComptageNmoins1() != null) {
			stb.append(this.getTypeComptageNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modeCalculNmoins1=");
		if (this.getModeCalculNmoins1() != null) {
			stb.append(this.getModeCalculNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins1=");
		if (this.getPcPLNmoins1() != null) {
			stb.append(this.getPcPLNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("evaluationPLNmoins1=");
		if (this.getEvaluationPLNmoins1() != null) {
			stb.append(this.getEvaluationPLNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins1=");
		if (this.getPcNuitAnnuelNmoins1() != null) {
			stb.append(this.getPcNuitAnnuelNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceFiabiliteMjaNmoins1=");
		if (this.getIndiceFiabiliteMjaNmoins1() != null) {
			stb.append(this.getIndiceFiabiliteMjaNmoins1());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeNmoins2=");
		if (this.getAnneeNmoins2() != null) {
			stb.append(
					this.fournirAnneeDeuxChiffresAPartirDate(
							this.getAnneeNmoins2()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins2=");
		if (this.getMjaNmoins2() != null) {
			stb.append(String.valueOf(this.getMjaNmoins2()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptageNmoins2=");
		if (this.getTypeComptageNmoins2() != null) {
			stb.append(this.getTypeComptageNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modeCalculNmoins2=");
		if (this.getModeCalculNmoins2() != null) {
			stb.append(this.getModeCalculNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins2=");
		if (this.getPcPLNmoins2() != null) {
			stb.append(this.getPcPLNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("evaluationPLNmoins2=");
		if (this.getEvaluationPLNmoins2() != null) {
			stb.append(this.getEvaluationPLNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins2=");
		if (this.getPcNuitAnnuelNmoins2() != null) {
			stb.append(this.getPcNuitAnnuelNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceFiabiliteMjaNmoins2=");
		if (this.getIndiceFiabiliteMjaNmoins2() != null) {
			stb.append(this.getIndiceFiabiliteMjaNmoins2());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeNmoins3=");
		if (this.getAnneeNmoins3() != null) {
			stb.append(
					this.fournirAnneeDeuxChiffresAPartirDate(
							this.getAnneeNmoins3()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins3=");
		if (this.getMjaNmoins3() != null) {
			stb.append(String.valueOf(this.getMjaNmoins3()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptageNmoins3=");
		if (this.getTypeComptageNmoins3() != null) {
			stb.append(this.getTypeComptageNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modeCalculNmoins3=");
		if (this.getModeCalculNmoins3() != null) {
			stb.append(this.getModeCalculNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins3=");
		if (this.getPcPLNmoins3() != null) {
			stb.append(this.getPcPLNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("evaluationPLNmoins3=");
		if (this.getEvaluationPLNmoins3() != null) {
			stb.append(this.getEvaluationPLNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins3=");
		if (this.getPcNuitAnnuelNmoins3() != null) {
			stb.append(this.getPcNuitAnnuelNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceFiabiliteMjaNmoins3=");
		if (this.getIndiceFiabiliteMjaNmoins3() != null) {
			stb.append(this.getIndiceFiabiliteMjaNmoins3());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeNmoins4=");
		if (this.getAnneeNmoins4() != null) {
			stb.append(
					this.fournirAnneeDeuxChiffresAPartirDate(
							this.getAnneeNmoins4()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins4=");
		if (this.getMjaNmoins4() != null) {
			stb.append(String.valueOf(this.getMjaNmoins4()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptageNmoins4=");
		if (this.getTypeComptageNmoins4() != null) {
			stb.append(this.getTypeComptageNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modeCalculNmoins4=");
		if (this.getModeCalculNmoins4() != null) {
			stb.append(this.getModeCalculNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins4=");
		if (this.getPcPLNmoins4() != null) {
			stb.append(this.getPcPLNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("evaluationPLNmoins4=");
		if (this.getEvaluationPLNmoins4() != null) {
			stb.append(this.getEvaluationPLNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins4=");
		if (this.getPcNuitAnnuelNmoins4() != null) {
			stb.append(this.getPcNuitAnnuelNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceFiabiliteMjaNmoins4=");
		if (this.getIndiceFiabiliteMjaNmoins4() != null) {
			stb.append(this.getIndiceFiabiliteMjaNmoins4());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("anneeNmoins5=");
		if (this.getAnneeNmoins5() != null) {
			stb.append(
					this.fournirAnneeDeuxChiffresAPartirDate(
							this.getAnneeNmoins5()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjaNmoins5=");
		if (this.getMjaNmoins5() != null) {
			stb.append(String.valueOf(this.getMjaNmoins5()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("typeComptageNmoins5=");
		if (this.getTypeComptageNmoins5() != null) {
			stb.append(this.getTypeComptageNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("modeCalculNmoins5=");
		if (this.getModeCalculNmoins5() != null) {
			stb.append(this.getModeCalculNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcPLNmoins5=");
		if (this.getPcPLNmoins5() != null) {
			stb.append(this.getPcPLNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("evaluationPLNmoins5=");
		if (this.getEvaluationPLNmoins5() != null) {
			stb.append(this.getEvaluationPLNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitAnnuelNmoins5=");
		if (this.getPcNuitAnnuelNmoins5() != null) {
			stb.append(this.getPcNuitAnnuelNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("indiceFiabiliteMjaNmoins5=");
		if (this.getIndiceFiabiliteMjaNmoins5() != null) {
			stb.append(this.getIndiceFiabiliteMjaNmoins5());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois01=");
		if (this.getMjmNmoins1mois01() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois01()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois01=");
		if (this.getPcNuitNmoins1mois01() != null) {
			stb.append(this.getPcNuitNmoins1mois01());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois02=");
		if (this.getMjmNmoins1mois02() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois02()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois02=");
		if (this.getPcNuitNmoins1mois02() != null) {
			stb.append(this.getPcNuitNmoins1mois02());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois03=");
		if (this.getMjmNmoins1mois03() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois03()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois03=");
		if (this.getPcNuitNmoins1mois03() != null) {
			stb.append(this.getPcNuitNmoins1mois03());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois04=");
		if (this.getMjmNmoins1mois04() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois04()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois04=");
		if (this.getPcNuitNmoins1mois04() != null) {
			stb.append(String.valueOf(this.getPcNuitNmoins1mois04()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois05=");
		if (this.getMjmNmoins1mois05() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois05()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois05=");
		if (this.getPcNuitNmoins1mois05() != null) {
			stb.append(this.getPcNuitNmoins1mois05());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois06=");
		if (this.getMjmNmoins1mois06() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois06()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois06=");
		if (this.getPcNuitNmoins1mois06() != null) {
			stb.append(this.getPcNuitNmoins1mois06());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois07=");
		if (this.getMjmNmoins1mois07() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois07()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois07=");
		if (this.getPcNuitNmoins1mois07() != null) {
			stb.append(this.getPcNuitNmoins1mois07());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois08=");
		if (this.getMjmNmoins1mois08() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois08()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois08=");
		if (this.getPcNuitNmoins1mois08() != null) {
			stb.append(this.getPcNuitNmoins1mois08());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois09=");
		if (this.getMjmNmoins1mois09() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois09()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois09=");
		if (this.getPcNuitNmoins1mois09() != null) {
			stb.append(this.getPcNuitNmoins1mois09());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois10=");
		if (this.getMjmNmoins1mois10() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois10()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois10=");
		if (this.getPcNuitNmoins1mois10() != null) {
			stb.append(this.getPcNuitNmoins1mois10());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois11=");
		if (this.getMjmNmoins1mois11() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois11()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois11=");
		if (this.getPcNuitNmoins1mois11() != null) {
			stb.append(this.getPcNuitNmoins1mois11());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("mjmNmoins1mois12=");
		if (this.getMjmNmoins1mois12() != null) {
			stb.append(String.valueOf(this.getMjmNmoins1mois12()));
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("pcNuitNmoins1mois12=");
		if (this.getPcNuitNmoins1mois12() != null) {
			stb.append(this.getPcNuitNmoins1mois12());
		} else {
			stb.append(NULL);
		}
		stb.append(VIRGULE_ESPACE);

		stb.append("zoneLibre4=");
		if (this.getZoneLibre4() != null) {
			stb.append(this.getZoneLibre4());
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
	public final String toStringASCII() {

		final StringBuilder stb = new StringBuilder();

		stb.append(this.getNumDepartement());
		stb.append(this.getNumSection());
		stb.append(this.getSens());
		stb.append(this.getNature());
		stb.append(this.getClasse());
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeTraitement()));
		stb.append(this.getZoneLibre1());
		stb.append(this.getNumRoute());
		stb.append(this.getIndiceNumRoute());
		stb.append(this.getIndiceLettreRoute());
		stb.append(this.getCategorieAdminRoute());
		stb.append(this.getTypeComptage());
		stb.append(this.getClassementRoute());
		stb.append(this.getClasseLargeurChausseeU());
		stb.append(this.getClasseLargeurChausseesS());
		stb.append(this.getTypeReseau());
		stb.append(this.getpRoupK());
		stb.append(this.getLieuDitOrigine());
		
		final String prOrigineString = String.valueOf(this.getPrOrigine());
		final String prOrigineComplete 
			= this.completerAvecZerosAGauche(
					prOrigineString, this.fournirLongueurChamp(19));		
		stb.append(prOrigineComplete);
		
		final String absOrigineString = String.valueOf(this.getAbsOrigine()); 
		final String absOrigineComplete 
			= this.completerAvecZerosAGauche(
					absOrigineString, this.fournirLongueurChamp(20));
		stb.append(absOrigineComplete);
		
		stb.append(this.getLieuDitExtremite());
		
		final String prExtremiteString = String.valueOf(this.getPrExtremite());
		final String prExtremiteComplete 
			= this.completerAvecZerosAGauche(
					prExtremiteString, this.fournirLongueurChamp(22));
		stb.append(prExtremiteComplete);
		
		final String absExtremiteString = String.valueOf(this.getAbsExtremite());
		final String absExtremiteComplete 
			= this.completerAvecZerosAGauche(
					absExtremiteString, this.fournirLongueurChamp(23));
		stb.append(absExtremiteComplete);
		
		stb.append(this.getLieuDitComptage());
		
		final String prComptageString = String.valueOf(this.getPrComptage());
		final String prComptageComplete 
			= this.completerAvecZerosAGauche(
					prComptageString, this.fournirLongueurChamp(25));
		stb.append(prComptageComplete);
		
		final String absComptageString = String.valueOf(this.getAbsComptage());
		final String absComptageComplete 
			= this.completerAvecZerosAGauche(
					absComptageString, this.fournirLongueurChamp(26));
		stb.append(absComptageComplete);

		final String longueurSectionString = String.valueOf(this.getLongueurSection());
		final String longueurSectionComplete 
			= this.completerAvecZerosAGauche(
					longueurSectionString, this.fournirLongueurChamp(27));
		stb.append(longueurSectionComplete);
		
		final String longueurRaseCampagneString = String.valueOf(this.getLongueurRaseCampagne());
		final String longueurRaseCampagneComplete 
			= this.completerAvecZerosAGauche(
					longueurRaseCampagneString, this.fournirLongueurChamp(28));
		stb.append(longueurRaseCampagneComplete);
		
		stb.append(this.getNumDepartementRattachement());
		stb.append(this.getNumSectionRattachement());
		stb.append(this.getSensRattachement());
		stb.append(this.getNumDepartementLimitrophe());
		stb.append(this.getNumSectionLimitrophe());
		stb.append(this.getSensLimitrophe());
		stb.append(this.getMoisSectionnement());
		stb.append(this.getAnneeSectionnement());
		stb.append(this.getZoneLibre2());
		
		final String mjaNString = String.valueOf(this.getMjaN());
		final String mjaNStringComplete 
			= this.completerAvecZerosAGauche(
					mjaNString, this.fournirLongueurChamp(38));
		stb.append(mjaNStringComplete);
		
		stb.append(this.getModeCalculN());
		stb.append(this.getPcPLN());
		stb.append(this.getEvaluationPLN());
		stb.append(this.getPcNuitAnnuelN());
		stb.append(this.getIndiceFiabiliteMjaN());
		
		final String mjmNmois01String = String.valueOf(this.getMjmNmois01());
		final String mjmNmois01Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois01String, this.fournirLongueurChamp(44));
		stb.append(mjmNmois01Complete);
		
		stb.append(this.getPcNuitNmois01());
		
		final String mjmNmois02String = String.valueOf(this.getMjmNmois02());
		final String mjmNmois02Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois02String, this.fournirLongueurChamp(46));
		stb.append(mjmNmois02Complete);

		stb.append(this.getPcNuitNmois02());
		
		final String mjmNmois03String = String.valueOf(this.getMjmNmois03());
		final String mjmNmois03Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois03String, this.fournirLongueurChamp(48));
		stb.append(mjmNmois03Complete);

		stb.append(this.getPcNuitNmois03());
		
		final String mjmNmois04String = String.valueOf(this.getMjmNmois04());
		final String mjmNmois04Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois04String, this.fournirLongueurChamp(50));
		stb.append(mjmNmois04Complete);

		stb.append(this.getPcNuitNmois04());
		
		final String mjmNmois05String = String.valueOf(this.getMjmNmois05());
		final String mjmNmois05Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois05String, this.fournirLongueurChamp(52));
		stb.append(mjmNmois05Complete);

		stb.append(this.getPcNuitNmois05());
		
		final String mjmNmois06String = String.valueOf(this.getMjmNmois06());
		final String mjmNmois06Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois06String, this.fournirLongueurChamp(54));
		stb.append(mjmNmois06Complete);

		stb.append(this.getPcNuitNmois06());
		
		final String mjmNmois07String = String.valueOf(this.getMjmNmois07());
		final String mjmNmois07Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois07String, this.fournirLongueurChamp(56));
		stb.append(mjmNmois07Complete);

		stb.append(this.getPcNuitNmois07());
		
		final String mjmNmois08String = String.valueOf(this.getMjmNmois08());
		final String mjmNmois08Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois08String, this.fournirLongueurChamp(58));
		stb.append(mjmNmois08Complete);

		stb.append(this.getPcNuitNmois08());
		
		final String mjmNmois09String = String.valueOf(this.getMjmNmois09());
		final String mjmNmois09Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois09String, this.fournirLongueurChamp(60));
		stb.append(mjmNmois09Complete);

		stb.append(this.getPcNuitNmois09());
		
		final String mjmNmois10String = String.valueOf(this.getMjmNmois10());
		final String mjmNmois10Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois10String, this.fournirLongueurChamp(62));
		stb.append(mjmNmois10Complete);

		stb.append(this.getPcNuitNmois10());
		
		final String mjmNmois11String = String.valueOf(this.getMjmNmois11());
		final String mjmNmois11Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois11String, this.fournirLongueurChamp(64));
		stb.append(mjmNmois11Complete);

		stb.append(this.getPcNuitNmois11());
		
		final String mjmNmois12String = String.valueOf(this.getMjmNmois12());
		final String mjmNmois12Complete 
			= this.completerAvecZerosAGauche(
					mjmNmois12String, this.fournirLongueurChamp(66));
		stb.append(mjmNmois12Complete);

		stb.append(this.getPcNuitNmois12());
		
		stb.append(this.getZoneLibre3());
		
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins1()));
		
		final String mjaNmoins1String = String.valueOf(this.getMjaNmoins1());
		final String mjaNmoins1Complete 
			= this.completerAvecZerosAGauche(
					mjaNmoins1String, this.fournirLongueurChamp(70));
		stb.append(mjaNmoins1Complete);
		
		stb.append(this.getTypeComptageNmoins1());
		stb.append(this.getModeCalculNmoins1());
		stb.append(this.getPcPLNmoins1());
		stb.append(this.getEvaluationPLNmoins1());
		stb.append(this.getPcNuitAnnuelNmoins1());
		stb.append(this.getIndiceFiabiliteMjaNmoins1());
		
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins2()));
		
		final String mjaNmoins2String = String.valueOf(this.getMjaNmoins2());
		final String mjaNmoins2Complete 
			= this.completerAvecZerosAGauche(
					mjaNmoins2String, this.fournirLongueurChamp(78));
		stb.append(mjaNmoins2Complete);

		stb.append(this.getTypeComptageNmoins2());
		stb.append(this.getModeCalculNmoins2());
		stb.append(this.getPcPLNmoins2());
		stb.append(this.getEvaluationPLNmoins2());
		stb.append(this.getPcNuitAnnuelNmoins2());
		stb.append(this.getIndiceFiabiliteMjaNmoins2());
		
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins3()));
		
		final String mjaNmoins3String = String.valueOf(this.getMjaNmoins3());
		final String mjaNmoins3Complete 
			= this.completerAvecZerosAGauche(
					mjaNmoins3String, this.fournirLongueurChamp(86));
		stb.append(mjaNmoins3Complete);

		stb.append(this.getTypeComptageNmoins3());
		stb.append(this.getModeCalculNmoins3());
		stb.append(this.getPcPLNmoins3());
		stb.append(this.getEvaluationPLNmoins3());
		stb.append(this.getPcNuitAnnuelNmoins3());
		stb.append(this.getIndiceFiabiliteMjaNmoins3());
		
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins4()));
		
		final String mjaNmoins4String = String.valueOf(this.getMjaNmoins4());
		final String mjaNmoins4Complete 
			= this.completerAvecZerosAGauche(
					mjaNmoins4String, this.fournirLongueurChamp(94));
		stb.append(mjaNmoins4Complete);

		stb.append(this.getTypeComptageNmoins4());
		stb.append(this.getModeCalculNmoins4());
		stb.append(this.getPcPLNmoins4());
		stb.append(this.getEvaluationPLNmoins4());
		stb.append(this.getPcNuitAnnuelNmoins4());
		stb.append(this.getIndiceFiabiliteMjaNmoins4());
		
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins5()));
		
		final String mjaNmoins5String = String.valueOf(this.getMjaNmoins5());
		final String mjaNmoins5Complete 
			= this.completerAvecZerosAGauche(
					mjaNmoins5String, this.fournirLongueurChamp(102));
		stb.append(mjaNmoins5Complete);

		stb.append(this.getTypeComptageNmoins5());
		stb.append(this.getModeCalculNmoins5());
		stb.append(this.getPcPLNmoins5());
		stb.append(this.getEvaluationPLNmoins5());
		stb.append(this.getPcNuitAnnuelNmoins5());
		stb.append(this.getIndiceFiabiliteMjaNmoins5());
		
		final String mjmNmoins1mois01String 
			= String.valueOf(this.getMjmNmoins1mois01());
		final String mjmNmoins1mois01Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois01String, this.fournirLongueurChamp(109));
		stb.append(mjmNmoins1mois01Complete);
		
		stb.append(this.getPcNuitNmoins1mois01());
		
		final String mjmNmoins1mois02String 
			= String.valueOf(this.getMjmNmoins1mois02());
		final String mjmNmoins1mois02Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois02String, this.fournirLongueurChamp(111));
		stb.append(mjmNmoins1mois02Complete);

		stb.append(this.getPcNuitNmoins1mois02());
		
		final String mjmNmoins1mois03String 
			= String.valueOf(this.getMjmNmoins1mois03());
		final String mjmNmoins1mois03Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois03String, this.fournirLongueurChamp(113));
		stb.append(mjmNmoins1mois03Complete);

		stb.append(this.getPcNuitNmoins1mois03());
		
		final String mjmNmoins1mois04String 
			= String.valueOf(this.getMjmNmoins1mois04());
		final String mjmNmoins1mois04Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois04String, this.fournirLongueurChamp(115));
		stb.append(mjmNmoins1mois04Complete);

		stb.append(this.getPcNuitNmoins1mois04());
		
		final String mjmNmoins1mois05String 
			= String.valueOf(this.getMjmNmoins1mois05());
		final String mjmNmoins1mois05Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois05String, this.fournirLongueurChamp(117));
		stb.append(mjmNmoins1mois05Complete);

		stb.append(this.getPcNuitNmoins1mois05());
		
		final String mjmNmoins1mois06String 
			= String.valueOf(this.getMjmNmoins1mois06());
		final String mjmNmoins1mois06Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois06String, this.fournirLongueurChamp(119));
		stb.append(mjmNmoins1mois06Complete);

		stb.append(this.getPcNuitNmoins1mois06());
		
		final String mjmNmoins1mois07String 
			= String.valueOf(this.getMjmNmoins1mois07());
		final String mjmNmoins1mois07Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois07String, this.fournirLongueurChamp(121));
		stb.append(mjmNmoins1mois07Complete);

		stb.append(this.getPcNuitNmoins1mois07());
		
		final String mjmNmoins1mois08String 
			= String.valueOf(this.getMjmNmoins1mois08());
		final String mjmNmoins1mois08Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois08String, this.fournirLongueurChamp(123));
		stb.append(mjmNmoins1mois08Complete);

		stb.append(this.getPcNuitNmoins1mois08());
		
		final String mjmNmoins1mois09String 
			= String.valueOf(this.getMjmNmoins1mois09());
		final String mjmNmoins1mois09Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois09String, this.fournirLongueurChamp(125));
		stb.append(mjmNmoins1mois09Complete);

		stb.append(this.getPcNuitNmoins1mois09());
		
		final String mjmNmoins1mois10String 
			= String.valueOf(this.getMjmNmoins1mois10());
		final String mjmNmoins1mois10Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois10String, this.fournirLongueurChamp(127));
		stb.append(mjmNmoins1mois10Complete);

		stb.append(this.getPcNuitNmoins1mois10());
		
		final String mjmNmoins1mois11String 
			= String.valueOf(this.getMjmNmoins1mois11());
		final String mjmNmoins1mois11Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois11String, this.fournirLongueurChamp(129));
		stb.append(mjmNmoins1mois11Complete);

		stb.append(this.getPcNuitNmoins1mois11());
		
		final String mjmNmoins1mois12String 
			= String.valueOf(this.getMjmNmoins1mois12());
		final String mjmNmoins1mois12Complete 
			= this.completerAvecZerosAGauche(
					mjmNmoins1mois12String, this.fournirLongueurChamp(131));
		stb.append(mjmNmoins1mois12Complete);

		stb.append(this.getPcNuitNmoins1mois12());
		
		stb.append(this.getZoneLibre4());

		return stb.toString();
		
	} // Fin de toStringASCII().___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		return "id;numDepartement;numSection;sens;nature;classe;anneeTraitement;"
				+ "zoneLibre1;"
				+ "numRoute;indiceNumRoute;indiceLettreRoute;categorieAdminRoute;"
				+ "typeComptage;classementRoute;classeLargeurChausseeU;"
				+ "classeLargeurChausseesS;typeReseau;pRoupK;"
				+ "lieuDitOrigine;prOrigine;absOrigine;"
				+ "lieuDitExtremite;prExtremite;absExtremite;"
				+ "lieuDitComptage;prComptage;absComptage;"
				+ "longueurSection;longueurRaseCampagne;"
				+ "numDepartementRattachement;numSectionRattachement;"
				+ "sensRattachement;"
				+ "numDepartementLimitrophe;numSectionLimitrophe;"
				+ "sensLimitrophe;"
				+ "moisSectionnement;anneeSectionnement;"
				+ "zoneLibre2;"
				+ "mjaN;modeCalculN;pcPLN;evaluationPLN;pcNuitAnnuelN;"
				+ "indiceFiabiliteMjaN;"
				+ "mjmNmois01;pcNuitNmois01;mjmNmois02;pcNuitNmois02;"
				+ "mjmNmois03;pcNuitNmois03;mjmNmois04;pcNuitNmois04;"
				+ "mjmNmois05;pcNuitNmois05;mjmNmois06;pcNuitNmois06;"
				+ "mjmNmois07;pcNuitNmois07;mjmNmois08;pcNuitNmois08;"
				+ "mjmNmois09;pcNuitNmois09;mjmNmois10;pcNuitNmois10;"
				+ "mjmNmois11;pcNuitNmois11;mjmNmois12;pcNuitNmois12;"
				+ "zoneLibre3;"
				+ "anneeNmoins1;mjaNmoins1;typeComptageNmoins1;"
				+ "modeCalculNmoins1;pcPLNmoins1;evaluationPLNmoins1;"
				+ "pcNuitAnnuelNmoins1;indiceFiabiliteMjaNmoins1;"
				+ "anneeNmoins2;mjaNmoins2;typeComptageNmoins2;"
				+ "modeCalculNmoins2;pcPLNmoins2;evaluationPLNmoins2;"
				+ "pcNuitAnnuelNmoins2;indiceFiabiliteMjaNmoins2;"
				+ "anneeNmoins3;mjaNmoins3;typeComptageNmoins3;"
				+ "modeCalculNmoins3;pcPLNmoins3;evaluationPLNmoins3;"
				+ "pcNuitAnnuelNmoins3;indiceFiabiliteMjaNmoins3;"
				+ "anneeNmoins4;mjaNmoins4;typeComptageNmoins4;"
				+ "modeCalculNmoins4;pcPLNmoins4;evaluationPLNmoins4;"
				+ "pcNuitAnnuelNmoins4;indiceFiabiliteMjaNmoins4;"
				+ "anneeNmoins5;mjaNmoins5;typeComptageNmoins5;"
				+ "modeCalculNmoins5;pcPLNmoins5;evaluationPLNmoins5;"
				+ "pcNuitAnnuelNmoins5;indiceFiabiliteMjaNmoins5;"
				+ "mjmNmoins1mois01;pcNuitNmoins1mois01;mjmNmoins1mois02;"
				+ "pcNuitNmoins1mois02;mjmNmoins1mois03;pcNuitNmoins1mois03;"
				+ "mjmNmoins1mois04;pcNuitNmoins1mois04;mjmNmoins1mois05;"
				+ "pcNuitNmoins1mois05;mjmNmoins1mois06;pcNuitNmoins1mois06;"
				+ "mjmNmoins1mois07;pcNuitNmoins1mois07;mjmNmoins1mois08;"
				+ "pcNuitNmoins1mois08;mjmNmoins1mois09;pcNuitNmoins1mois09;"
				+ "mjmNmoins1mois10;pcNuitNmoins1mois10;mjmNmoins1mois11;"
				+ "pcNuitNmoins1mois11;mjmNmoins1mois12;pcNuitNmoins1mois12;"
				+ "zoneLibre4;";
		
	} // Fin de fournirEnTeteCsv().________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {

		final StringBuilder stb = new StringBuilder();

		stb.append(String.valueOf(this.getId()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumDepartement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumSection());
		stb.append(POINT_VIRGULE);
		stb.append(this.getSens());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNature());
		stb.append(POINT_VIRGULE);
		stb.append(this.getClasse());
		stb.append(POINT_VIRGULE);
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeTraitement()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getZoneLibre1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceNumRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceLettreRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getCategorieAdminRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptage());
		stb.append(POINT_VIRGULE);
		stb.append(this.getClassementRoute());
		stb.append(POINT_VIRGULE);
		stb.append(this.getClasseLargeurChausseeU());
		stb.append(POINT_VIRGULE);
		stb.append(this.getClasseLargeurChausseesS());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeReseau());
		stb.append(POINT_VIRGULE);
		stb.append(this.getpRoupK());
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitOrigine());
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getPrOrigine()));
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getAbsOrigine()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitExtremite());
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getPrExtremite()));
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getAbsExtremite()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getLieuDitComptage());
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getPrComptage()));
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getAbsComptage()));
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getLongueurSection()));
		stb.append(POINT_VIRGULE);
		stb.append(String.valueOf(this.getLongueurRaseCampagne()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumDepartementRattachement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumSectionRattachement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getSensRattachement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumDepartementLimitrophe());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNumSectionLimitrophe());
		stb.append(POINT_VIRGULE);
		stb.append(this.getSensLimitrophe());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMoisSectionnement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getAnneeSectionnement());
		stb.append(POINT_VIRGULE);
		stb.append(this.getZoneLibre2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModeCalculN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEvaluationPLN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceFiabiliteMjaN());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois01());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois01());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois02());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois02());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois03());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois03());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois04());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois04());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois05());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois05());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois06());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois06());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois07());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois07());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois08());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois08());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois09());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois09());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois10());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois10());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois11());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois11());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmois12());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmois12());
		stb.append(POINT_VIRGULE);
		stb.append(this.getZoneLibre3());
		stb.append(POINT_VIRGULE);
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins1()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptageNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModeCalculNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEvaluationPLNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceFiabiliteMjaNmoins1());
		stb.append(POINT_VIRGULE);
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins2()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptageNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModeCalculNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEvaluationPLNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceFiabiliteMjaNmoins2());
		stb.append(POINT_VIRGULE);
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins3()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptageNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModeCalculNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEvaluationPLNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceFiabiliteMjaNmoins3());
		stb.append(POINT_VIRGULE);
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins4()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptageNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModeCalculNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEvaluationPLNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceFiabiliteMjaNmoins4());
		stb.append(POINT_VIRGULE);
		stb.append(
				this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins5()));
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjaNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTypeComptageNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getModeCalculNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcPLNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEvaluationPLNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitAnnuelNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getIndiceFiabiliteMjaNmoins5());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois01());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois01());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois02());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois02());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois03());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois03());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois04());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois04());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois05());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois05());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois06());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois06());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois07());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois07());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois08());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois08());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois09());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois09());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois10());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois10());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois11());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois11());
		stb.append(POINT_VIRGULE);
		stb.append(this.getMjmNmoins1mois12());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPcNuitNmoins1mois12());
		stb.append(POINT_VIRGULE);
		stb.append(this.getZoneLibre4());
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
			entete = "numDepartement";
			break;

		case 2:
			entete = "numSection";
			break;

		case 3:
			entete = "sens";
			break;

		case 4:
			entete = "nature";
			break;

		case 5:
			entete = "classe";
			break;

		case 6:
			entete = "anneeTraitement";
			break;

		case 7:
			entete = "zoneLibre1";
			break;

		case 8:
			entete = "numRoute";
			break;

		case 9:
			entete = "indiceNumRoute";
			break;

		case 10:
			entete = "indiceLettreRoute";
			break;

		case 11:
			entete = "categorieAdminRoute";
			break;

		case 12:
			entete = "typeComptage";
			break;

		case 13:
			entete = "classementRoute";
			break;

		case 14:
			entete = "classeLargeurChausseeU";
			break;

		case 15:
			entete = "classeLargeurChausseesS";
			break;

		case 16:
			entete = "typeReseau";
			break;

		case 17:
			entete = "pRoupK";
			break;

		case 18:
			entete = "lieuDitOrigine";
			break;

		case 19:
			entete = "prOrigine";
			break;

		case 20:
			entete = "absOrigine";
			break;

		case 21:
			entete = "lieuDitExtremite";
			break;

		case 22:
			entete = "prExtremite";
			break;

		case 23:
			entete = "absExtremite";
			break;

		case 24:
			entete = "lieuDitComptage";
			break;

		case 25:
			entete = "prComptage";
			break;

		case 26:
			entete = "absComptage";
			break;

		case 27:
			entete = "longueurSection";
			break;

		case 28:
			entete = "longueurRaseCampagne";
			break;

		case 29:
			entete = "numDepartementRattachement";
			break;

		case 30:
			entete = "numSectionRattachement";
			break;

		case 31:
			entete = "sensRattachement";
			break;

		case 32:
			entete = "numDepartementLimitrophe";
			break;

		case 33:
			entete = "numSectionLimitrophe";
			break;

		case 34:
			entete = "sensLimitrophe";
			break;

		case 35:
			entete = "moisSectionnement";
			break;

		case 36:
			entete = "anneeSectionnement";
			break;

		case 37:
			entete = "zoneLibre2";
			break;

		case 38:
			entete = "mjaN";
			break;

		case 39:
			entete = "modeCalculN";
			break;

		case 40:
			entete = "pcPLN";
			break;

		case 41:
			entete = "evaluationPLN";
			break;

		case 42:
			entete = "pcNuitAnnuelN";
			break;

		case 43:
			entete = "indiceFiabiliteMjaN";
			break;

		case 44:
			entete = "mjmNmois01";
			break;

		case 45:
			entete = "pcNuitNmois01";
			break;

		case 46:
			entete = "mjmNmois02";
			break;

		case 47:
			entete = "pcNuitNmois02";
			break;

		case 48:
			entete = "mjmNmois03";
			break;

		case 49:
			entete = "pcNuitNmois03";
			break;

		case 50:
			entete = "mjmNmois04";
			break;

		case 51:
			entete = "pcNuitNmois04";
			break;

		case 52:
			entete = "mjmNmois05";
			break;

		case 53:
			entete = "pcNuitNmois05";
			break;

		case 54:
			entete = "mjmNmois06";
			break;

		case 55:
			entete = "pcNuitNmois06";
			break;

		case 56:
			entete = "mjmNmois07";
			break;

		case 57:
			entete = "pcNuitNmois07";
			break;

		case 58:
			entete = "mjmNmois08";
			break;

		case 59:
			entete = "pcNuitNmois08";
			break;

		case 60:
			entete = "mjmNmois09";
			break;

		case 61:
			entete = "pcNuitNmois09";
			break;

		case 62:
			entete = "mjmNmois10";
			break;

		case 63:
			entete = "pcNuitNmois10";
			break;

		case 64:
			entete = "mjmNmois11";
			break;

		case 65:
			entete = "pcNuitNmois11";
			break;

		case 66:
			entete = "mjmNmois12";
			break;

		case 67:
			entete = "pcNuitNmois12";
			break;

		case 68:
			entete = "zoneLibre3";
			break;

		case 69:
			entete = "anneeNmoins1";
			break;

		case 70:
			entete = "mjaNmoins1";
			break;

		case 71:
			entete = "typeComptageNmoins1";
			break;

		case 72:
			entete = "modeCalculNmoins1";
			break;

		case 73:
			entete = "pcPLNmoins1";
			break;

		case 74:
			entete = "evaluationPLNmoins1";
			break;

		case 75:
			entete = "pcNuitAnnuelNmoins1";
			break;

		case 76:
			entete = "indiceFiabiliteMjaNmoins1";
			break;

		case 77:
			entete = "anneeNmoins2";
			break;

		case 78:
			entete = "mjaNmoins2";
			break;

		case 79:
			entete = "typeComptageNmoins2";
			break;

		case 80:
			entete = "modeCalculNmoins2";
			break;

		case 81:
			entete = "pcPLNmoins2";
			break;

		case 82:
			entete = "evaluationPLNmoins2";
			break;

		case 83:
			entete = "pcNuitAnnuelNmoins2";
			break;

		case 84:
			entete = "indiceFiabiliteMjaNmoins2";
			break;

		case 85:
			entete = "anneeNmoins3";
			break;

		case 86:
			entete = "mjaNmoins3";
			break;

		case 87:
			entete = "typeComptageNmoins3";
			break;

		case 88:
			entete = "modeCalculNmoins3";
			break;

		case 89:
			entete = "pcPLNmoins3";
			break;

		case 90:
			entete = "evaluationPLNmoins3";
			break;

		case 91:
			entete = "pcNuitAnnuelNmoins3";
			break;

		case 92:
			entete = "indiceFiabiliteMjaNmoins3";
			break;

		case 93:
			entete = "anneeNmoins4";
			break;

		case 94:
			entete = "mjaNmoins4";
			break;

		case 95:
			entete = "typeComptageNmoins4";
			break;

		case 96:
			entete = "modeCalculNmoins4";
			break;

		case 97:
			entete = "pcPLNmoins4";
			break;

		case 98:
			entete = "evaluationPLNmoins4";
			break;

		case 99:
			entete = "pcNuitAnnuelNmoins4";
			break;

		case 100:
			entete = "indiceFiabiliteMjaNmoins4";
			break;

		case 101:
			entete = "anneeNmoins5";
			break;

		case 102:
			entete = "mjaNmoins5";
			break;

		case 103:
			entete = "typeComptageNmoins5";
			break;

		case 104:
			entete = "modeCalculNmoins5";
			break;

		case 105:
			entete = "pcPLNmoins5";
			break;

		case 106:
			entete = "evaluationPLNmoins5";
			break;

		case 107:
			entete = "pcNuitAnnuelNmoins5";
			break;

		case 108:
			entete = "indiceFiabiliteMjaNmoins5";
			break;

		case 109:
			entete = "mjmNmoins1mois01";
			break;

		case 110:
			entete = "pcNuitNmoins1mois01";
			break;

		case 111:
			entete = "mjmNmoins1mois02";
			break;

		case 112:
			entete = "pcNuitNmoins1mois02";
			break;

		case 113:
			entete = "mjmNmoins1mois03";
			break;

		case 114:
			entete = "pcNuitNmoins1mois03";
			break;

		case 115:
			entete = "mjmNmoins1mois04";
			break;

		case 116:
			entete = "pcNuitNmoins1mois04";
			break;

		case 117:
			entete = "mjmNmoins1mois05";
			break;

		case 118:
			entete = "pcNuitNmoins1mois05";
			break;

		case 119:
			entete = "mjmNmoins1mois06";
			break;

		case 120:
			entete = "pcNuitNmoins1mois06";
			break;

		case 121:
			entete = "mjmNmoins1mois07";
			break;

		case 122:
			entete = "pcNuitNmoins1mois07";
			break;

		case 123:
			entete = "mjmNmoins1mois08";
			break;

		case 124:
			entete = "pcNuitNmoins1mois08";
			break;

		case 125:
			entete = "mjmNmoins1mois09";
			break;

		case 126:
			entete = "pcNuitNmoins1mois09";
			break;

		case 127:
			entete = "mjmNmoins1mois10";
			break;

		case 128:
			entete = "pcNuitNmoins1mois10";
			break;

		case 129:
			entete = "mjmNmoins1mois11";
			break;

		case 130:
			entete = "pcNuitNmoins1mois11";
			break;

		case 131:
			entete = "mjmNmoins1mois12";
			break;

		case 132:
			entete = "pcNuitNmoins1mois12";
			break;

		case 133:
			entete = "zoneLibre4";
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
			if (this.getId() != null) {
				valeur = String.valueOf(this.getId());
			}			
			break;

		case 1:
			valeur = this.getNumDepartement();
			break;

		case 2:
			valeur = this.getNumSection();
			break;

		case 3:
			valeur = this.getSens();
			break;

		case 4:
			valeur = this.getNature();
			break;

		case 5:
			valeur = this.getClasse();
			break;

		case 6:
			if (this.getAnneeTraitement() != null) {
				valeur = this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeTraitement());
			}			
			break;

		case 7:
			valeur = this.getZoneLibre1();
			break;

		case 8:
			valeur = this.getNumRoute();
			break;

		case 9:
			valeur = this.getIndiceNumRoute();
			break;

		case 10:
			valeur = this.getIndiceLettreRoute();
			break;

		case 11:
			valeur = this.getCategorieAdminRoute();
			break;

		case 12:
			valeur = this.getTypeComptage();
			break;

		case 13:
			valeur = this.getClassementRoute();
			break;

		case 14:
			valeur = this.getClasseLargeurChausseeU();
			break;

		case 15:
			valeur = this.getClasseLargeurChausseesS();
			break;

		case 16:
			valeur = this.getTypeReseau();
			break;

		case 17:
			valeur = this.getpRoupK();
			break;

		case 18:
			valeur = this.getLieuDitOrigine();
			break;

		case 19:
			if (this.getPrOrigine() != null) {
				valeur = String.valueOf(this.getPrOrigine());
			}			
			break;

		case 20:
			if (this.getAbsOrigine() != null) {
				valeur = String.valueOf(this.getAbsOrigine());
			}			
			break;

		case 21:
			valeur = this.getLieuDitExtremite();
			break;

		case 22:
			if (this.getPrExtremite() != null) {
				valeur = String.valueOf(this.getPrExtremite());
			}			
			break;

		case 23:
			if (this.getAbsExtremite() != null) {
				valeur = String.valueOf(this.getAbsExtremite());
			}			
			break;

		case 24:
			valeur = this.getLieuDitComptage();
			break;

		case 25:
			if (this.getPrComptage() != null) {
				valeur = String.valueOf(this.getPrComptage());
			}			
			break;

		case 26:
			if (this.getAbsComptage() != null) {
				valeur = String.valueOf(this.getAbsComptage());
			}			
			break;

		case 27:
			if (this.getLongueurSection() != null) {
				valeur = String.valueOf(this.getLongueurSection());
			}			
			break;

		case 28:
			if (this.getLongueurRaseCampagne() != null) {
				valeur = String.valueOf(this.getLongueurRaseCampagne());
			}			
			break;

		case 29:
			valeur = this.getNumDepartementRattachement();
			break;

		case 30:
			valeur = this.getNumSectionRattachement();
			break;

		case 31:
			valeur = this.getSensRattachement();
			break;

		case 32:
			valeur = this.getNumDepartementLimitrophe();
			break;

		case 33:
			valeur = this.getNumSectionLimitrophe();
			break;

		case 34:
			valeur = this.getSensLimitrophe();
			break;

		case 35:
			valeur = this.getMoisSectionnement();
			break;

		case 36:
			valeur = this.getAnneeSectionnement();
			break;

		case 37:
			valeur = this.getZoneLibre2();
			break;

		case 38:
			if (this.getMjaN() != null) {
				valeur = String.valueOf(this.getMjaN());
			}			
			break;

		case 39:
			valeur = this.getModeCalculN();
			break;

		case 40:
			valeur = this.getPcPLN();
			break;

		case 41:
			valeur = this.getEvaluationPLN();
			break;

		case 42:
			valeur = this.getPcNuitAnnuelN();
			break;

		case 43:
			valeur = this.getIndiceFiabiliteMjaN();
			break;

		case 44:
			if (this.getMjmNmois01() != null) {
				valeur = String.valueOf(this.getMjmNmois01());
			}			
			break;

		case 45:
			valeur = this.getPcNuitNmois01();
			break;

		case 46:
			if (this.getMjmNmois02() != null) {
				valeur = String.valueOf(this.getMjmNmois02());
			}
			break;

		case 47:
			valeur = this.getPcNuitNmois02();
			break;

		case 48:
			if (this.getMjmNmois03() != null) {
				valeur = String.valueOf(this.getMjmNmois03());
			}
			break;

		case 49:
			valeur = this.getPcNuitNmois03();
			break;

		case 50:
			if (this.getMjmNmois04() != null) {
				valeur = String.valueOf(this.getMjmNmois04());
			}
			break;

		case 51:
			valeur = this.getPcNuitNmois04();
			break;

		case 52:
			if (this.getMjmNmois05() != null) {
				valeur = String.valueOf(this.getMjmNmois05());
			}
			break;

		case 53:
			valeur = this.getPcNuitNmois05();
			break;

		case 54:
			if (this.getMjmNmois06() != null) {
				valeur = String.valueOf(this.getMjmNmois06());
			}
			break;

		case 55:
			valeur = this.getPcNuitNmois06();
			break;

		case 56:
			if (this.getMjmNmois07() != null) {
				valeur = String.valueOf(this.getMjmNmois07());
			}
			break;

		case 57:
			valeur = this.getPcNuitNmois07();
			break;

		case 58:
			if (this.getMjmNmois08() != null) {
				valeur = String.valueOf(this.getMjmNmois08());
			}
			break;

		case 59:
			valeur = this.getPcNuitNmois08();
			break;

		case 60:
			if (this.getMjmNmois09() != null) {
				valeur = String.valueOf(this.getMjmNmois09());
			}
			break;

		case 61:
			valeur = this.getPcNuitNmois09();
			break;

		case 62:
			if (this.getMjmNmois10() != null) {
				valeur = String.valueOf(this.getMjmNmois10());
			}
			break;

		case 63:
			valeur = this.getPcNuitNmois10();
			break;

		case 64:
			if (this.getMjmNmois11() != null) {
				valeur = String.valueOf(this.getMjmNmois11());
			}
			break;

		case 65:
			valeur = this.getPcNuitNmois11();
			break;

		case 66:
			if (this.getMjmNmois12() != null) {
				valeur = String.valueOf(this.getMjmNmois12());
			}
			break;

		case 67:
			valeur = this.getPcNuitNmois12();
			break;

		case 68:
			valeur = this.getZoneLibre3();
			break;

		case 69:
			if (this.getAnneeNmoins1() != null) {
				valeur = this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins1());
			}			
			break;

		case 70:
			if (this.getMjaNmoins1() != null) {
				valeur = String.valueOf(this.getMjaNmoins1());
			}
			break;

		case 71:
			valeur = this.getTypeComptageNmoins1();
			break;

		case 72:
			valeur = this.getModeCalculNmoins1();
			break;

		case 73:
			valeur = this.getPcPLNmoins1();
			break;

		case 74:
			valeur = this.getEvaluationPLNmoins1();
			break;

		case 75:
			valeur = this.getPcNuitAnnuelNmoins1();
			break;

		case 76:
			valeur = this.getIndiceFiabiliteMjaNmoins1();
			break;

		case 77:
			if (this.getAnneeNmoins2() != null) {
				valeur = this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins2());
			}			
			break;

		case 78:
			if (this.getMjaNmoins2() != null) {
				valeur = String.valueOf(this.getMjaNmoins2());
			}
			break;

		case 79:
			valeur = this.getTypeComptageNmoins2();
			break;

		case 80:
			valeur = this.getModeCalculNmoins2();
			break;

		case 81:
			valeur = this.getPcPLNmoins2();
			break;

		case 82:
			valeur = this.getEvaluationPLNmoins2();
			break;

		case 83:
			valeur = this.getPcNuitAnnuelNmoins2();
			break;

		case 84:
			valeur = this.getIndiceFiabiliteMjaNmoins2();
			break;

		case 85:
			if (this.getAnneeNmoins3() != null) {
				valeur = this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins3());
			}			
			break;

		case 86:
			if (this.getMjaNmoins3() != null) {
				valeur = String.valueOf(this.getMjaNmoins3());
			}
			break;

		case 87:
			valeur = this.getTypeComptageNmoins3();
			break;

		case 88:
			valeur = this.getModeCalculNmoins3();
			break;

		case 89:
			valeur = this.getPcPLNmoins3();
			break;

		case 90:
			valeur = this.getEvaluationPLNmoins3();
			break;

		case 91:
			valeur = this.getPcNuitAnnuelNmoins3();
			break;

		case 92:
			valeur = this.getIndiceFiabiliteMjaNmoins3();
			break;

		case 93:
			if (this.getAnneeNmoins4() != null) {
				valeur = this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins4());
			}			
			break;

		case 94:
			if (this.getMjaNmoins4() != null) {
				valeur = String.valueOf(this.getMjaNmoins4());
			}
			break;

		case 95:
			valeur = this.getTypeComptageNmoins4();
			break;

		case 96:
			valeur = this.getModeCalculNmoins4();
			break;

		case 97:
			valeur = this.getPcPLNmoins4();
			break;

		case 98:
			valeur = this.getEvaluationPLNmoins4();
			break;

		case 99:
			valeur = this.getPcNuitAnnuelNmoins4();
			break;

		case 100:
			valeur = this.getIndiceFiabiliteMjaNmoins4();
			break;

		case 101:
			if (this.getAnneeNmoins5() != null) {
				valeur = this.fournirAnneeDeuxChiffresAPartirDate(
						this.getAnneeNmoins5());
			}			
			break;

		case 102:
			if (this.getMjaNmoins5() != null) {
				valeur = String.valueOf(this.getMjaNmoins5());
			}
			break;

		case 103:
			valeur = this.getTypeComptageNmoins5();
			break;

		case 104:
			valeur = this.getModeCalculNmoins5();
			break;

		case 105:
			valeur = this.getPcPLNmoins5();
			break;

		case 106:
			valeur = this.getEvaluationPLNmoins5();
			break;

		case 107:
			valeur = this.getPcNuitAnnuelNmoins5();
			break;

		case 108:
			valeur = this.getIndiceFiabiliteMjaNmoins5();
			break;

		case 109:
			if (this.getMjmNmoins1mois01() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois01());
			}			
			break;

		case 110:
			valeur = this.getPcNuitNmoins1mois01();
			break;

		case 111:
			if (this.getMjmNmoins1mois02() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois02());
			}			
			break;

		case 112:
			valeur = this.getPcNuitNmoins1mois02();
			break;

		case 113:
			if (this.getMjmNmoins1mois03() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois03());
			}			
			break;

		case 114:
			valeur = this.getPcNuitNmoins1mois03();
			break;

		case 115:
			if (this.getMjmNmoins1mois04() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois04());
			}			
			break;

		case 116:
			valeur = this.getPcNuitNmoins1mois04();
			break;

		case 117:
			if (this.getMjmNmoins1mois05() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois05());
			}			
			break;

		case 118:
			valeur = this.getPcNuitNmoins1mois05();
			break;

		case 119:
			if (this.getMjmNmoins1mois06() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois06());
			}			
			break;

		case 120:
			valeur = this.getPcNuitNmoins1mois06();
			break;

		case 121:
			if (this.getMjmNmoins1mois07() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois07());
			}			
			break;

		case 122:
			valeur = this.getPcNuitNmoins1mois07();
			break;

		case 123:
			if (this.getMjmNmoins1mois08() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois08());
			}			
			break;

		case 124:
			valeur = this.getPcNuitNmoins1mois08();
			break;

		case 125:
			if (this.getMjmNmoins1mois09() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois09());
			}			
			break;

		case 126:
			valeur = this.getPcNuitNmoins1mois09();
			break;

		case 127:
			if (this.getMjmNmoins1mois10() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois10());
			}			
			break;

		case 128:
			valeur = this.getPcNuitNmoins1mois10();
			break;

		case 129:
			if (this.getMjmNmoins1mois11() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois11());
			}			
			break;

		case 130:
			valeur = this.getPcNuitNmoins1mois11();
			break;

		case 131:
			if (this.getMjmNmoins1mois12() != null) {
				valeur = String.valueOf(this.getMjmNmoins1mois12());
			}			
			break;

		case 132:
			valeur = this.getPcNuitNmoins1mois12();
			break;

		case 133:
			valeur = this.getZoneLibre4();
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
	@Transient
	@Override
	public String getNumDepartement() {
		return this.numDepartement;
	} // Fin de getNumDepartement()._______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumDepartement(
			final String pNumDepartement) {
		
		this.numDepartement = pNumDepartement;
						
		if (this.localisation != null) {
			this.localisation.setNumDepartement(this.numDepartement);
		}

	} // Fin de setNumDepartement(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NUMSECTION"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getNumSection() {
		return this.numSection;
	} // Fin de getNumSection().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumSection(
			final String pNumSection) {
		this.numSection = pNumSection;
	} // Fin de setNumSection(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="SENS"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getSens() {
		return this.sens;
	} // Fin de getSens()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSens(
			final String pSens) {
		this.sens = pSens;
	} // Fin de setSens(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NATURE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getNature() {
		return this.nature;
	} // Fin de getNature()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNature(
			final String pNature) {
		this.nature = pNature;
	} // Fin de setNature(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="CLASSE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getClasse() {
		return this.classe;
	} // Fin de getClasse()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClasse(
			final String pClasse) {
		this.classe = pClasse;
	} // Fin de setClasse(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
//	@Convert(converter = LocalDateAttributeConverter.class)
	@Column(name="ANNEETRAITEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public LocalDate getAnneeTraitement() {
		return this.anneeTraitement;
	} // Fin de getAnneeTraitement().______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeTraitement(
			final LocalDate pAnneeTraitement) {
		this.anneeTraitement = pAnneeTraitement;
	} // Fin de setAnneeTraitement(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ZONELIBRE1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getZoneLibre1() {
		return this.zoneLibre1;
	} // Fin de getZoneLibre1().___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setZoneLibre1(
			final String pZoneLibre1) {
		this.zoneLibre1 = pZoneLibre1;
	} // Fin de setZoneLibre1(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getNumRoute() {
		return this.numRoute;
	} // Fin de getNumRoute()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumRoute(
			final String pNumRoute) {
		
		this.numRoute = pNumRoute;
		
		if (this.localisation != null) {
			this.localisation.setNumRoute(this.numRoute);
		}
				
	} // Fin de setNumRoute(...).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getIndiceNumRoute() {
		return this.indiceNumRoute;
	} // Fin de getIndiceNumRoute()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceNumRoute(
			final String pIndiceNumRoute) {
		
		this.indiceNumRoute = pIndiceNumRoute;
		
		if (this.localisation != null) {
			this.localisation.setIndiceNumRoute(this.indiceNumRoute);
		}
		
	} // Fin de setIndiceNumRoute(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getIndiceLettreRoute() {
		return this.indiceLettreRoute;
	} // Fin de getIndiceLettreRoute().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceLettreRoute(
			final String pIndiceLettreRoute) {
		
		this.indiceLettreRoute = pIndiceLettreRoute;
		
		if (this.localisation != null) {
			this.localisation.setIndiceLettreRoute(this.indiceLettreRoute);
		}

	} // Fin de setIndiceLettreRoute(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getCategorieAdminRoute() {
		return this.categorieAdminRoute;
	} // Fin de getCategorieAdminRoute().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCategorieAdminRoute(
			final String pCategorieAdminRoute) {
		
		this.categorieAdminRoute = pCategorieAdminRoute;
				
		if (this.localisation != null) {
			this.localisation.setCategorieAdminRoute(this.categorieAdminRoute);
		}

	} // Fin de setCategorieAdminRoute(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPECOMPTAGE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeComptage() {
		return this.typeComptage;
	} // Fin de getTypeComptage()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeComptage(
			final String pTypeComptage) {
		this.typeComptage = pTypeComptage;
	} // Fin de setTypeComptage(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="CLASSEMENTROUTE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getClassementRoute() {
		return this.classementRoute;
	} // Fin de getClassementRoute().______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClassementRoute(
			final String pClassementRoute) {
		this.classementRoute = pClassementRoute;
	} // Fin de setClassementRoute(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="CLASSELARGEURCHAUSSEEU"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getClasseLargeurChausseeU() {
		return this.classeLargeurChausseeU;
	} // Fin de getClasseLargeurChausseeU()._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClasseLargeurChausseeU(
			final String pClasseLargeurChausseeU) {
		this.classeLargeurChausseeU = pClasseLargeurChausseeU;
	} // Fin de setClasseLargeurChausseeU(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="CLASSELARGEURCHAUSSEESS"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getClasseLargeurChausseesS() {
		return this.classeLargeurChausseesS;
	} // Fin de getClasseLargeurChausseesS().______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClasseLargeurChausseesS(
			final String pClasseLargeurChausseesS) {
		this.classeLargeurChausseesS = pClasseLargeurChausseesS;
	} // Fin de setClasseLargeurChausseesS(...).___________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPERESEAU"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeReseau() {
		return this.typeReseau;
	} // Fin de getTypeReseau().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeReseau(
			final String pTypeReseau) {
		this.typeReseau = pTypeReseau;
	} // Fin de setTypeReseau(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PROUPK"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getpRoupK() {
		return this.pRoupK;
	} // Fin de getPRoupK()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setpRoupK(
			final String pPRoupK) {
		this.pRoupK = pPRoupK;
	} // Fin de setPRoupK(...).____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getLieuDitOrigine() {
		return this.lieuDitOrigine;
	} // Fin de getLieuDitOrigine()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLieuDitOrigine(
			final String pLieuDitOrigine) {
		
		this.lieuDitOrigine = pLieuDitOrigine;
				
		if (this.localisation != null) {
			this.localisation.setLieuDitOrigine(this.lieuDitOrigine);
		}

	} // Fin de setLieuDitOrigine(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Integer getPrOrigine() {
		return this.prOrigine;
	} // Fin de getPrOrigine().____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPrOrigine(
			final Integer pPrOrigine) {
		
		this.prOrigine = pPrOrigine;
				
		if (this.localisation != null) {
			this.localisation.setPrOrigine(this.prOrigine);
		}

	} // Fin de setPrOrigine(...)._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Integer getAbsOrigine() {
		return this.absOrigine;
	} // Fin de getAbsOrigine().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAbsOrigine(
			final Integer pAbsOrigine) {
		
		this.absOrigine = pAbsOrigine;
				
		if (this.localisation != null) {
			this.localisation.setAbsOrigine(this.absOrigine);
		}

	} // Fin de setAbsOrigine(...).________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getLieuDitExtremite() {
		return this.lieuDitExtremite;
	} // Fin de getLieuDitExtremite()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLieuDitExtremite(
			final String pLieuDitExtremite) {
		
		this.lieuDitExtremite = pLieuDitExtremite;
				
		if (this.localisation != null) {
			this.localisation.setLieuDitExtremite(this.lieuDitExtremite);
		}

	} // Fin de setLieuDitExtremite(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Integer getPrExtremite() {
		return this.prExtremite;
	} // Fin de getPrExtremite().__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPrExtremite(
			final Integer pPrExtremite) {
		
		this.prExtremite = pPrExtremite;
				
		if (this.localisation != null) {
			this.localisation.setPrExtremite(this.prExtremite);
		}

	} // Fin de setPrExtremite(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Integer getAbsExtremite() {
		return this.absExtremite;
	} // Fin de getAbsExtremite()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAbsExtremite(
			final Integer pAbsExtremite) {
		
		this.absExtremite = pAbsExtremite;
		
		if (this.localisation != null) {
			this.localisation.setAbsExtremite(this.absExtremite);
		}

	} // Fin de setAbsExtremite(...).______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public String getLieuDitComptage() {
		return this.lieuDitComptage;
	} // Fin de getLieuDitComptage().______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLieuDitComptage(
			final String pLieuDitComptage) {
		
		this.lieuDitComptage = pLieuDitComptage;
		
		if (this.localisation != null) {
			this.localisation.setLieuDitComptage(this.lieuDitComptage);
		}

	} // Fin de setLieuDitComptage(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Integer getPrComptage() {
		return this.prComptage;
	} // Fin de getPrComptage().___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPrComptage(
			final Integer pPrComptage) {
		
		this.prComptage = pPrComptage;
		
		if (this.localisation != null) {
			this.localisation.setPrComptage(this.prComptage);
		}

	} // Fin de setPrComptage(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Transient
	@Override
	public Integer getAbsComptage() {
		return this.absComptage;
	} // Fin de getAbsComptage().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAbsComptage(
			final Integer pAbsComptage) {
		
		this.absComptage = pAbsComptage;
		
		if (this.localisation != null) {
			this.localisation.setAbsComptage(this.absComptage);
		}

	} // Fin de setAbsComptage(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="LONGUEURSECTION"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getLongueurSection() {
		return this.longueurSection;
	} // Fin de getLongueurSection().______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLongueurSection(
			final Integer pLongueurSection) {
		this.longueurSection = pLongueurSection;
	} // Fin de setLongueurSection(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="LONGUEURRASECAMPAGNE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getLongueurRaseCampagne() {
		return this.longueurRaseCampagne;
	} // Fin de getLongueurRaseCampagne()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLongueurRaseCampagne(
			final Integer pLongueurRaseCampagne) {
		this.longueurRaseCampagne = pLongueurRaseCampagne;
	} // Fin de setLongueurRaseCampagne(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NUMDEPARTEMENTRATTACHEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getNumDepartementRattachement() {
		return this.numDepartementRattachement;
	} // Fin de getNumDepartementRattachement().___________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumDepartementRattachement(
			final String pNumDepartementRattachement) {
		this.numDepartementRattachement = pNumDepartementRattachement;
	} // Fin de setNumDepartementRattachement(...).________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NUMSECTIONRATTACHEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getNumSectionRattachement() {
		return this.numSectionRattachement;
	} // Fin de getNumSectionRattachement()._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumSectionRattachement(
			final String pNumSectionRattachement) {
		this.numSectionRattachement = pNumSectionRattachement;
	} // Fin de setNumSectionRattachement(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="SENSRATTACHEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getSensRattachement() {
		return this.sensRattachement;
	} // Fin de getSensRattachement()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSensRattachement(
			final String pSensRattachement) {
		this.sensRattachement = pSensRattachement;
	} // Fin de setSensRattachement(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NUMDEPARTEMENTLIMITROPHE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getNumDepartementLimitrophe() {
		return this.numDepartementLimitrophe;
	} // Fin de getNumDepartementLimitrophe()._____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumDepartementLimitrophe(
			final String pNumDepartementLimitrophe) {
		this.numDepartementLimitrophe = pNumDepartementLimitrophe;
	} // Fin de setNumDepartementLimitrophe(...).__________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NUMSECTIONLIMITROPHE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getNumSectionLimitrophe() {
		return this.numSectionLimitrophe;
	} // Fin de getNumSectionLimitrophe()._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumSectionLimitrophe(
			final String pNumSectionLimitrophe) {
		this.numSectionLimitrophe = pNumSectionLimitrophe;
	} // Fin de setNumSectionLimitrophe(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="SENSLIMITROPHE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getSensLimitrophe() {
		return this.sensLimitrophe;
	} // Fin de getSensLimitrophe()._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSensLimitrophe(
			final String pSensLimitrophe) {
		this.sensLimitrophe = pSensLimitrophe;
	} // Fin de setSensLimitrophe(...).____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MOISSECTIONNEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getMoisSectionnement() {
		return this.moisSectionnement;
	} // Fin de getMoisSectionnement().____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMoisSectionnement(
			final String pMoisSectionnement) {
		this.moisSectionnement = pMoisSectionnement;
	} // Fin de setMoisSectionnement(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ANNEESECTIONNEMENT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getAnneeSectionnement() {
		return this.anneeSectionnement;
	} // Fin de getAnneeSectionnement().___________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeSectionnement(
			final String pAnneeSectionnement) {
		this.anneeSectionnement = pAnneeSectionnement;
	} // Fin de setAnneeSectionnement(...).________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ZONELIBRE2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getZoneLibre2() {
		return this.zoneLibre2;
	} // Fin de getZoneLibre2().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setZoneLibre2(
			final String pZoneLibre2) {
		this.zoneLibre2 = pZoneLibre2;
	} // Fin de setZoneLibre2(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJAN"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjaN() {
		return this.mjaN;
	} // Fin de getMjaN()._________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjaN(
			final Integer pMjaN) {
		this.mjaN = pMjaN;
	} // Fin de setMjaN(...).______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MODECALCULN"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getModeCalculN() {
		return this.modeCalculN;
	} // Fin de getModeCalculN().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModeCalculN(
			final String pModeCalculN) {
		this.modeCalculN = pModeCalculN;
	} // Fin de setModeCalculN(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCPLN"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcPLN() {
		return this.pcPLN;
	} // Fin de getPcPLN().________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcPLN(
			final String pPcPLN) {
		this.pcPLN = pPcPLN;
	} // Fin de setPcPLN(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EVALUATIONPLN"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getEvaluationPLN() {
		return this.evaluationPLN;
	} // Fin de getEvaluationPLN().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEvaluationPLN(
			final String pEvaluationPLN) {
		this.evaluationPLN = pEvaluationPLN;
	} // Fin de setEvaluationPLN(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITANNUELN"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitAnnuelN() {
		return this.pcNuitAnnuelN;
	} // Fin de getPcNuitAnnuelN().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitAnnuelN(
			final String pPcNuitAnnuelN) {
		this.pcNuitAnnuelN = pPcNuitAnnuelN;
	} // Fin de setPcNuitAnnuelN(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICEFIABILITEMJAN"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getIndiceFiabiliteMjaN() {
		return this.indiceFiabiliteMjaN;
	} // Fin de getIndiceFiabiliteMjaN().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceFiabiliteMjaN(
			final String pIndiceFiabiliteMjaN) {
		this.indiceFiabiliteMjaN = pIndiceFiabiliteMjaN;
	} // Fin de setIndiceFiabiliteMjaN(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS01"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois01() {
		return this.mjmNmois01;
	} // Fin de getMjmNmois01().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois01(
			final Integer pMjmNmois01) {
		this.mjmNmois01 = pMjmNmois01;
	} // Fin de setMjmNmois01(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS01"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois01() {
		return this.pcNuitNmois01;
	} // Fin de getPcNuitNmois01().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois01(
			final String pPcNuitNmois01) {
		this.pcNuitNmois01 = pPcNuitNmois01;
	} // Fin de setPcNuitNmois01(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS02"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois02() {
		return this.mjmNmois02;
	} // Fin de getMjmNmois02().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois02(
			final Integer pMjmNmois02) {
		this.mjmNmois02 = pMjmNmois02;
	} // Fin de setMjmNmois02(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS02"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois02() {
		return this.pcNuitNmois02;
	} // Fin de getPcNuitNmois02().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois02(
			final String pPcNuitNmois02) {
		this.pcNuitNmois02 = pPcNuitNmois02;
	} // Fin de setPcNuitNmois02(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS03"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois03() {
		return this.mjmNmois03;
	} // Fin de getMjmNmois03().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois03(
			final Integer pMjmNmois03) {
		this.mjmNmois03 = pMjmNmois03;
	} // Fin de setMjmNmois03(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS03"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois03() {
		return this.pcNuitNmois03;
	} // Fin de getPcNuitNmois03().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois03(
			final String pPcNuitNmois03) {
		this.pcNuitNmois03 = pPcNuitNmois03;
	} // Fin de setPcNuitNmois03(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS04"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois04() {
		return this.mjmNmois04;
	} // Fin de getMjmNmois04().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois04(
			final Integer pMjmNmois04) {
		this.mjmNmois04 = pMjmNmois04;
	} // Fin de setMjmNmois04(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS04"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois04() {
		return this.pcNuitNmois04;
	} // Fin de getPcNuitNmois04().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois04(
			final String pPcNuitNmois04) {
		this.pcNuitNmois04 = pPcNuitNmois04;
	} // Fin de setPcNuitNmois04(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS05"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois05() {
		return this.mjmNmois05;
	} // Fin de getMjmNmois05().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois05(
			final Integer pMjmNmois05) {
		this.mjmNmois05 = pMjmNmois05;
	} // Fin de setMjmNmois05(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS05"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois05() {
		return this.pcNuitNmois05;
	} // Fin de getPcNuitNmois05().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois05(
			final String pPcNuitNmois05) {
		this.pcNuitNmois05 = pPcNuitNmois05;
	} // Fin de setPcNuitNmois05(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS06"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois06() {
		return this.mjmNmois06;
	} // Fin de getMjmNmois06().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois06(
			final Integer pMjmNmois06) {
		this.mjmNmois06 = pMjmNmois06;
	} // Fin de setMjmNmois06(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS06"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois06() {
		return this.pcNuitNmois06;
	} // Fin de getPcNuitNmois06().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois06(
			final String pPcNuitNmois06) {
		this.pcNuitNmois06 = pPcNuitNmois06;
	} // Fin de setPcNuitNmois06(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS07"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois07() {
		return this.mjmNmois07;
	} // Fin de getMjmNmois07().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois07(
			final Integer pMjmNmois07) {
		this.mjmNmois07 = pMjmNmois07;
	} // Fin de setMjmNmois07(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS07"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois07() {
		return this.pcNuitNmois07;
	} // Fin de getPcNuitNmois07().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois07(
			final String pPcNuitNmois07) {
		this.pcNuitNmois07 = pPcNuitNmois07;
	} // Fin de setPcNuitNmois07(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS08"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois08() {
		return this.mjmNmois08;
	} // Fin de getMjmNmois08().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois08(
			final Integer pMjmNmois08) {
		this.mjmNmois08 = pMjmNmois08;
	} // Fin de setMjmNmois08(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS08"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois08() {
		return this.pcNuitNmois08;
	} // Fin de getPcNuitNmois08().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois08(
			final String pPcNuitNmois08) {
		this.pcNuitNmois08 = pPcNuitNmois08;
	} // Fin de setPcNuitNmois08(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS09"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois09() {
		return this.mjmNmois09;
	} // Fin de getMjmNmois09().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois09(
			final Integer pMjmNmois09) {
		this.mjmNmois09 = pMjmNmois09;
	} // Fin de setMjmNmois09(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS09"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois09() {
		return this.pcNuitNmois09;
	} // Fin de getPcNuitNmois09().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois09(
			final String pPcNuitNmois09) {
		this.pcNuitNmois09 = pPcNuitNmois09;
	} // Fin de setPcNuitNmois09(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS10"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois10() {
		return this.mjmNmois10;
	} // Fin de getMjmNmois10().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois10(
			final Integer pMjmNmois10) {
		this.mjmNmois10 = pMjmNmois10;
	} // Fin de setMjmNmois10(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS10"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois10() {
		return this.pcNuitNmois10;
	} // Fin de getPcNuitNmois10().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois10(
			final String pPcNuitNmois10) {
		this.pcNuitNmois10 = pPcNuitNmois10;
	} // Fin de setPcNuitNmois10(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS11"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois11() {
		return this.mjmNmois11;
	} // Fin de getMjmNmois11().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois11(
			final Integer pMjmNmois11) {
		this.mjmNmois11 = pMjmNmois11;
	} // Fin de setMjmNmois11(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS11"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois11() {
		return this.pcNuitNmois11;
	} // Fin de getPcNuitNmois11().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois11(
			final String pPcNuitNmois11) {
		this.pcNuitNmois11 = pPcNuitNmois11;
	} // Fin de setPcNuitNmois11(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOIS12"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmois12() {
		return this.mjmNmois12;
	} // Fin de getMjmNmois12().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmois12(
			final Integer pMjmNmois12) {
		this.mjmNmois12 = pMjmNmois12;
	} // Fin de setMjmNmois12(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOIS12"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmois12() {
		return this.pcNuitNmois12;
	} // Fin de getPcNuitNmois12().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmois12(
			final String pPcNuitNmois12) {
		this.pcNuitNmois12 = pPcNuitNmois12;
	} // Fin de setPcNuitNmois12(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ZONELIBRE3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getZoneLibre3() {
		return this.zoneLibre3;
	} // Fin de getZoneLibre3().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setZoneLibre3(
			final String pZoneLibre3) {
		this.zoneLibre3 = pZoneLibre3;
	} // Fin de setZoneLibre3(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ANNEENMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public LocalDate getAnneeNmoins1() {
		return this.anneeNmoins1;
	} // Fin de getAnneeNmoins1()._________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeNmoins1(
			final LocalDate pAnneeNmoins1) {
		this.anneeNmoins1 = pAnneeNmoins1;
	} // Fin de setAnneeNmoins1(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJANMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjaNmoins1() {
		return this.mjaNmoins1;
	} // Fin de getMjaNmoins1().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjaNmoins1(
			final Integer pMjaNmoins1) {
		this.mjaNmoins1 = pMjaNmoins1;
	} // Fin de setMjaNmoins1(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPECOMPTAGENMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeComptageNmoins1() {
		return this.typeComptageNmoins1;
	} // Fin de getTypeComptageNmoins1().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeComptageNmoins1(
			final String pTypeComptageNmoins1) {
		this.typeComptageNmoins1 = pTypeComptageNmoins1;
	} // Fin de setTypeComptageNmoins1(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MODECALCULNMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getModeCalculNmoins1() {
		return this.modeCalculNmoins1;
	} // Fin de getModeCalculNmoins1().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModeCalculNmoins1(
			final String pModeCalculNmoins1) {
		this.modeCalculNmoins1 = pModeCalculNmoins1;
	} // Fin de setModeCalculNmoins1(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCPLNMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcPLNmoins1() {
		return this.pcPLNmoins1;
	} // Fin de getPcPLNmoins1().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcPLNmoins1(
			final String pPcPLNmoins1) {
		this.pcPLNmoins1 = pPcPLNmoins1;
	} // Fin de setPcPLNmoins1(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EVALUATIONPLNMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getEvaluationPLNmoins1() {
		return this.evaluationPLNmoins1;
	} // Fin de getEvaluationPLNmoins1().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEvaluationPLNmoins1(
			final String pEvaluationPLNmoins1) {
		this.evaluationPLNmoins1 = pEvaluationPLNmoins1;
	} // Fin de setEvaluationPLNmoins1(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITANNUELNMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitAnnuelNmoins1() {
		return this.pcNuitAnnuelNmoins1;
	} // Fin de getPcNuitAnnuelNmoins1().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitAnnuelNmoins1(
			final String pPcNuitAnnuelNmoins1) {
		this.pcNuitAnnuelNmoins1 = pPcNuitAnnuelNmoins1;
	} // Fiin de setPcNuitAnnuelNmoins1(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICEFIABILITEMJANMOINS1"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getIndiceFiabiliteMjaNmoins1() {
		return this.indiceFiabiliteMjaNmoins1;
	} // Fin de getIndiceFiabiliteMjaNmoins1().____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceFiabiliteMjaNmoins1(
			final String pIndiceFiabiliteMjaNmoins1) {
		this.indiceFiabiliteMjaNmoins1 = pIndiceFiabiliteMjaNmoins1;
	} // Fin de setIndiceFiabiliteMjaNmoins1(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ANNEENMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public LocalDate getAnneeNmoins2() {
		return this.anneeNmoins2;
	} // Fin de getAnneeNmoins2()._________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeNmoins2(
			final LocalDate pAnneeNmoins2) {
		this.anneeNmoins2 = pAnneeNmoins2;
	} // Fin de setAnneeNmoins2(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJANMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjaNmoins2() {
		return this.mjaNmoins2;
	} // Fin de getMjaNmoins2().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjaNmoins2(
			final Integer pMjaNmoins2) {
		this.mjaNmoins2 = pMjaNmoins2;
	} // Fin de setMjaNmoins2(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPECOMPTAGENMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeComptageNmoins2() {
		return this.typeComptageNmoins2;
	} // Fin de getTypeComptageNmoins2().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeComptageNmoins2(
			final String pTypeComptageNmoins2) {
		this.typeComptageNmoins2 = pTypeComptageNmoins2;
	} // Fin de setTypeComptageNmoins2(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MODECALCULNMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getModeCalculNmoins2() {
		return this.modeCalculNmoins2;
	} // Fin de getModeCalculNmoins2().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModeCalculNmoins2(
			final String pModeCalculNmoins2) {
		this.modeCalculNmoins2 = pModeCalculNmoins2;
	} // Fin de setModeCalculNmoins2(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCPLNMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcPLNmoins2() {
		return this.pcPLNmoins2;
	} // Fin de getPcPLNmoins2().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcPLNmoins2(
			final String pPcPLNmoins2) {
		this.pcPLNmoins2 = pPcPLNmoins2;
	} // Fin de setPcPLNmoins2(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EVALUATIONPLNMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getEvaluationPLNmoins2() {
		return this.evaluationPLNmoins2;
	} // Fin de getEvaluationPLNmoins2().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEvaluationPLNmoins2(
			final String pEvaluationPLNmoins2) {
		this.evaluationPLNmoins2 = pEvaluationPLNmoins2;
	} // Fin de setEvaluationPLNmoins2(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITANNUELNMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitAnnuelNmoins2() {
		return this.pcNuitAnnuelNmoins2;
	} // Fin de getPcNuitAnnuelNmoins2().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitAnnuelNmoins2(
			final String pPcNuitAnnuelNmoins2) {
		this.pcNuitAnnuelNmoins2 = pPcNuitAnnuelNmoins2;
	} // Fiin de setPcNuitAnnuelNmoins2(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICEFIABILITEMJANMOINS2"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getIndiceFiabiliteMjaNmoins2() {
		return this.indiceFiabiliteMjaNmoins2;
	} // Fin de getIndiceFiabiliteMjaNmoins2().____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceFiabiliteMjaNmoins2(
			final String pIndiceFiabiliteMjaNmoins2) {
		this.indiceFiabiliteMjaNmoins2 = pIndiceFiabiliteMjaNmoins2;
	} // Fin de setIndiceFiabiliteMjaNmoins2(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ANNEENMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public LocalDate getAnneeNmoins3() {
		return this.anneeNmoins3;
	} // Fin de getAnneeNmoins3()._________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeNmoins3(
			final LocalDate pAnneeNmoins3) {
		this.anneeNmoins3 = pAnneeNmoins3;
	} // Fin de setAnneeNmoins3(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJANMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjaNmoins3() {
		return this.mjaNmoins3;
	} // Fin de getMjaNmoins3().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjaNmoins3(
			final Integer pMjaNmoins3) {
		this.mjaNmoins3 = pMjaNmoins3;
	} // Fin de setMjaNmoins3(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPECOMPTAGENMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeComptageNmoins3() {
		return this.typeComptageNmoins3;
	} // Fin de getTypeComptageNmoins3().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeComptageNmoins3(
			final String pTypeComptageNmoins3) {
		this.typeComptageNmoins3 = pTypeComptageNmoins3;
	} // Fin de setTypeComptageNmoins3(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MODECALCULNMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getModeCalculNmoins3() {
		return this.modeCalculNmoins3;
	} // Fin de getModeCalculNmoins3().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModeCalculNmoins3(
			final String pModeCalculNmoins3) {
		this.modeCalculNmoins3 = pModeCalculNmoins3;
	} // Fin de setModeCalculNmoins3(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCPLNMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcPLNmoins3() {
		return this.pcPLNmoins3;
	} // Fin de getPcPLNmoins3().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcPLNmoins3(
			final String pPcPLNmoins3) {
		this.pcPLNmoins3 = pPcPLNmoins3;
	} // Fin de setPcPLNmoins3(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EVALUATIONPLNMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getEvaluationPLNmoins3() {
		return this.evaluationPLNmoins3;
	} // Fin de getEvaluationPLNmoins3().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEvaluationPLNmoins3(
			final String pEvaluationPLNmoins3) {
		this.evaluationPLNmoins3 = pEvaluationPLNmoins3;
	} // Fin de setEvaluationPLNmoins3(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITANNUELNMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitAnnuelNmoins3() {
		return this.pcNuitAnnuelNmoins3;
	} // Fin de getPcNuitAnnuelNmoins3().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitAnnuelNmoins3(
			final String pPcNuitAnnuelNmoins3) {
		this.pcNuitAnnuelNmoins3 = pPcNuitAnnuelNmoins3;
	} // Fiin de setPcNuitAnnuelNmoins3(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICEFIABILITEMJANMOINS3"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getIndiceFiabiliteMjaNmoins3() {
		return this.indiceFiabiliteMjaNmoins3;
	} // Fin de getIndiceFiabiliteMjaNmoins3().____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceFiabiliteMjaNmoins3(
			final String pIndiceFiabiliteMjaNmoins3) {
		this.indiceFiabiliteMjaNmoins3 = pIndiceFiabiliteMjaNmoins3;
	} // Fin de setIndiceFiabiliteMjaNmoins3(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ANNEENMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)	
	@Override
	public LocalDate getAnneeNmoins4() {
		return this.anneeNmoins4;
	} // Fin de getAnneeNmoins4()._________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeNmoins4(
			final LocalDate pAnneeNmoins4) {
		this.anneeNmoins4 = pAnneeNmoins4;
	} // Fin de setAnneeNmoins4(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJANMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjaNmoins4() {
		return this.mjaNmoins4;
	} // Fin de getMjaNmoins4().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjaNmoins4(
			final Integer pMjaNmoins4) {
		this.mjaNmoins4 = pMjaNmoins4;
	} // Fin de setMjaNmoins4(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPECOMPTAGENMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeComptageNmoins4() {
		return this.typeComptageNmoins4;
	} // Fin de getTypeComptageNmoins4().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeComptageNmoins4(
			final String pTypeComptageNmoins4) {
		this.typeComptageNmoins4 = pTypeComptageNmoins4;
	} // Fin de setTypeComptageNmoins4(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MODECALCULNMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getModeCalculNmoins4() {
		return this.modeCalculNmoins4;
	} // Fin de getModeCalculNmoins4().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModeCalculNmoins4(
			final String pModeCalculNmoins4) {
		this.modeCalculNmoins4 = pModeCalculNmoins4;
	} // Fin de setModeCalculNmoins4(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCPLNMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcPLNmoins4() {
		return this.pcPLNmoins4;
	} // Fin de getPcPLNmoins4().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcPLNmoins4(
			final String pPcPLNmoins4) {
		this.pcPLNmoins4 = pPcPLNmoins4;
	} // Fin de setPcPLNmoins4(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EVALUATIONPLNMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getEvaluationPLNmoins4() {
		return this.evaluationPLNmoins4;
	} // Fin de getEvaluationPLNmoins4().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEvaluationPLNmoins4(
			final String pEvaluationPLNmoins4) {
		this.evaluationPLNmoins4 = pEvaluationPLNmoins4;
	} // Fin de setEvaluationPLNmoins4(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITANNUELNMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitAnnuelNmoins4() {
		return this.pcNuitAnnuelNmoins4;
	} // Fin de getPcNuitAnnuelNmoins4().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitAnnuelNmoins4(
			final String pPcNuitAnnuelNmoins4) {
		this.pcNuitAnnuelNmoins4 = pPcNuitAnnuelNmoins4;
	} // Fiin de setPcNuitAnnuelNmoins4(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICEFIABILITEMJANMOINS4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getIndiceFiabiliteMjaNmoins4() {
		return this.indiceFiabiliteMjaNmoins4;
	} // Fin de getIndiceFiabiliteMjaNmoins4().____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceFiabiliteMjaNmoins4(
			final String pIndiceFiabiliteMjaNmoins4) {
		this.indiceFiabiliteMjaNmoins4 = pIndiceFiabiliteMjaNmoins4;
	} // Fin de setIndiceFiabiliteMjaNmoins4(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ANNEENMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)	
	@Override
	public LocalDate getAnneeNmoins5() {
		return this.anneeNmoins5;
	} // Fin de getAnneeNmoins5()._________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAnneeNmoins5(
			final LocalDate pAnneeNmoins5) {
		this.anneeNmoins5 = pAnneeNmoins5;
	} // Fin de setAnneeNmoins5(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJANMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjaNmoins5() {
		return this.mjaNmoins5;
	} // Fin de getMjaNmoins5().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjaNmoins5(
			final Integer pMjaNmoins5) {
		this.mjaNmoins5 = pMjaNmoins5;
	} // Fin de setMjaNmoins5(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TYPECOMPTAGENMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTypeComptageNmoins5() {
		return this.typeComptageNmoins5;
	} // Fin de getTypeComptageNmoins5().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeComptageNmoins5(
			final String pTypeComptageNmoins5) {
		this.typeComptageNmoins5 = pTypeComptageNmoins5;
	} // Fin de setTypeComptageNmoins5(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MODECALCULNMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getModeCalculNmoins5() {
		return this.modeCalculNmoins5;
	} // Fin de getModeCalculNmoins5().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setModeCalculNmoins5(
			final String pModeCalculNmoins5) {
		this.modeCalculNmoins5 = pModeCalculNmoins5;
	} // Fin de setModeCalculNmoins5(...)._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCPLNMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcPLNmoins5() {
		return this.pcPLNmoins5;
	} // Fin de getPcPLNmoins5().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcPLNmoins5(
			final String pPcPLNmoins5) {
		this.pcPLNmoins5 = pPcPLNmoins5;
	} // Fin de setPcPLNmoins5(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EVALUATIONPLNMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getEvaluationPLNmoins5() {
		return this.evaluationPLNmoins5;
	} // Fin de getEvaluationPLNmoins5().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEvaluationPLNmoins5(
			final String pEvaluationPLNmoins5) {
		this.evaluationPLNmoins5 = pEvaluationPLNmoins5;
	} // Fin de setEvaluationPLNmoins5(...)._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITANNUELNMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitAnnuelNmoins5() {
		return this.pcNuitAnnuelNmoins5;
	} // Fin de getPcNuitAnnuelNmoins5().__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitAnnuelNmoins5(
			final String pPcNuitAnnuelNmoins5) {
		this.pcNuitAnnuelNmoins5 = pPcNuitAnnuelNmoins5;
	} // Fiin de setPcNuitAnnuelNmoins5(...).______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="INDICEFIABILITEMJANMOINS5"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getIndiceFiabiliteMjaNmoins5() {
		return this.indiceFiabiliteMjaNmoins5;
	} // Fin de getIndiceFiabiliteMjaNmoins5().____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIndiceFiabiliteMjaNmoins5(
			final String pIndiceFiabiliteMjaNmoins5) {
		this.indiceFiabiliteMjaNmoins5 = pIndiceFiabiliteMjaNmoins5;
	} // Fin de setIndiceFiabiliteMjaNmoins5(...)._________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS01"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois01() {
		return this.mjmNmoins1mois01;
	} // Fin de getMjmNmoins1mois01()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois01(
			final Integer pMjmNmoins1mois01) {
		this.mjmNmoins1mois01 = pMjmNmoins1mois01;
	} // Fin de setMjmNmoins1mois01(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS01"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois01() {
		return this.pcNuitNmoins1mois01;
	} // Fin de getPcNuitNmoins1mois01().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois01(
			final String pPcNuitNmoins1mois01) {
		this.pcNuitNmoins1mois01 = pPcNuitNmoins1mois01;
	} // Fin de setPcNuitNmoins1mois01(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS02"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois02() {
		return this.mjmNmoins1mois02;
	} // Fin de getMjmNmoins1mois02()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois02(
			final Integer pMjmNmoins1mois02) {
		this.mjmNmoins1mois02 = pMjmNmoins1mois02;
	} // Fin de setMjmNmoins1mois02(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS02"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois02() {
		return this.pcNuitNmoins1mois02;
	} // Fin de getPcNuitNmoins1mois02().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois02(
			final String pPcNuitNmoins1mois02) {
		this.pcNuitNmoins1mois02 = pPcNuitNmoins1mois02;
	} // Fin de setPcNuitNmoins1mois02(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS03"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois03() {
		return this.mjmNmoins1mois03;
	} // Fin de getMjmNmoins1mois03()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois03(
			final Integer pMjmNmoins1mois03) {
		this.mjmNmoins1mois03 = pMjmNmoins1mois03;
	} // Fin de setMjmNmoins1mois03(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS03"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois03() {
		return this.pcNuitNmoins1mois03;
	} // Fin de getPcNuitNmoins1mois03().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois03(
			final String pPcNuitNmoins1mois03) {
		this.pcNuitNmoins1mois03 = pPcNuitNmoins1mois03;
	} // Fin de setPcNuitNmoins1mois03(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS04"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois04() {
		return this.mjmNmoins1mois04;
	} // Fin de getMjmNmoins1mois04()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois04(
			final Integer pMjmNmoins1mois04) {
		this.mjmNmoins1mois04 = pMjmNmoins1mois04;
	} // Fin de setMjmNmoins1mois04(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS04"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois04() {
		return this.pcNuitNmoins1mois04;
	} // Fin de getPcNuitNmoins1mois04().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois04(
			final String pPcNuitNmoins1mois04) {
		this.pcNuitNmoins1mois04 = pPcNuitNmoins1mois04;
	} // Fin de setPcNuitNmoins1mois04(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS05"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois05() {
		return this.mjmNmoins1mois05;
	} // Fin de getMjmNmoins1mois05()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois05(
			final Integer pMjmNmoins1mois05) {
		this.mjmNmoins1mois05 = pMjmNmoins1mois05;
	} // Fin de setMjmNmoins1mois05(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS05"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois05() {
		return this.pcNuitNmoins1mois05;
	} // Fin de getPcNuitNmoins1mois05().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois05(
			final String pPcNuitNmoins1mois05) {
		this.pcNuitNmoins1mois05 = pPcNuitNmoins1mois05;
	} // Fin de setPcNuitNmoins1mois05(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS06"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois06() {
		return this.mjmNmoins1mois06;
	} // Fin de getMjmNmoins1mois06()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois06(
			final Integer pMjmNmoins1mois06) {
		this.mjmNmoins1mois06 = pMjmNmoins1mois06;
	} // Fin de setMjmNmoins1mois06(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS06"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois06() {
		return this.pcNuitNmoins1mois06;
	} // Fin de getPcNuitNmoins1mois06().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois06(
			final String pPcNuitNmoins1mois06) {
		this.pcNuitNmoins1mois06 = pPcNuitNmoins1mois06;
	} // Fin de setPcNuitNmoins1mois06(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS07"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois07() {
		return this.mjmNmoins1mois07;
	} // Fin de getMjmNmoins1mois07()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois07(
			final Integer pMjmNmoins1mois07) {
		this.mjmNmoins1mois07 = pMjmNmoins1mois07;
	} // Fin de setMjmNmoins1mois07(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS07"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois07() {
		return this.pcNuitNmoins1mois07;
	} // Fin de getPcNuitNmoins1mois07().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois07(
			final String pPcNuitNmoins1mois07) {
		this.pcNuitNmoins1mois07 = pPcNuitNmoins1mois07;
	} // Fin de setPcNuitNmoins1mois07(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS08"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois08() {
		return this.mjmNmoins1mois08;
	} // Fin de getMjmNmoins1mois08()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois08(
			final Integer pMjmNmoins1mois08) {
		this.mjmNmoins1mois08 = pMjmNmoins1mois08;
	} // Fin de setMjmNmoins1mois08(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS08"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois08() {
		return this.pcNuitNmoins1mois08;
	} // Fin de getPcNuitNmoins1mois08().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois08(
			final String pPcNuitNmoins1mois08) {
		this.pcNuitNmoins1mois08 = pPcNuitNmoins1mois08;
	} // Fin de setPcNuitNmoins1mois08(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS09"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois09() {
		return this.mjmNmoins1mois09;
	} // Fin de getMjmNmoins1mois09()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois09(
			final Integer pMjmNmoins1mois09) {
		this.mjmNmoins1mois09 = pMjmNmoins1mois09;
	} // Fin de setMjmNmoins1mois09(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS09"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois09() {
		return this.pcNuitNmoins1mois09;
	} // Fin de getPcNuitNmoins1mois09().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois09(
			final String pPcNuitNmoins1mois09) {
		this.pcNuitNmoins1mois09 = pPcNuitNmoins1mois09;
	} // Fin de setPcNuitNmoins1mois09(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS10"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois10() {
		return this.mjmNmoins1mois10;
	} // Fin de getMjmNmoins1mois10()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois10(
			final Integer pMjmNmoins1mois10) {
		this.mjmNmoins1mois10 = pMjmNmoins1mois10;
	} // Fin de setMjmNmoins1mois10(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS10"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois10() {
		return this.pcNuitNmoins1mois10;
	} // Fin de getPcNuitNmoins1mois10().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois10(
			final String pPcNuitNmoins1mois10) {
		this.pcNuitNmoins1mois10 = pPcNuitNmoins1mois10;
	} // Fin de setPcNuitNmoins1mois10(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS11"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois11() {
		return this.mjmNmoins1mois11;
	} // Fin de getMjmNmoins1mois11()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois11(
			final Integer pMjmNmoins1mois11) {
		this.mjmNmoins1mois11 = pMjmNmoins1mois11;
	} // Fin de setMjmNmoins1mois11(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS11"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois11() {
		return this.pcNuitNmoins1mois11;
	} // Fin de getPcNuitNmoins1mois11().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois11(
			final String pPcNuitNmoins1mois11) {
		this.pcNuitNmoins1mois11 = pPcNuitNmoins1mois11;
	} // Fin de setPcNuitNmoins1mois11(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="MJMNMOINS1MOIS12"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public Integer getMjmNmoins1mois12() {
		return this.mjmNmoins1mois12;
	} // Fin de getMjmNmoins1mois12()._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMjmNmoins1mois12(
			final Integer pMjmNmoins1mois12) {
		this.mjmNmoins1mois12 = pMjmNmoins1mois12;
	} // Fin de setMjmNmoins1mois12(...).__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PCNUITNMOINS1MOIS12"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPcNuitNmoins1mois12() {
		return this.pcNuitNmoins1mois12;
	} // Fin de getPcNuitNmoins1mois12().__________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPcNuitNmoins1mois12(
			final String pPcNuitNmoins1mois12) {
		this.pcNuitNmoins1mois12 = pPcNuitNmoins1mois12;
	} // Fin de setPcNuitNmoins1mois12(...)._______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="ZONELIBRE4"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getZoneLibre4() {
		return this.zoneLibre4;
	} // Fin de getZoneLibre4().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setZoneLibre4(
			final String pZoneLibre4) {
		this.zoneLibre4 = pZoneLibre4;
	} // Fin de setZoneLibre4(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@ManyToOne(targetEntity = LocalisationHitEntityJPA.class
			, fetch = FetchType.EAGER)	
			@JoinColumn(name = "ID_LOCALISATION_HIT", referencedColumnName = "ID"
			, foreignKey = @ForeignKey(name = "FK_SECTIONHIT_LOCALISATIONHIT")
			, insertable = true, updatable = true
			, nullable = false, unique = false)			
	@Override
	public ILocalisationHit getLocalisation() {
		return this.localisation;
	} // Fin de getLocalisation()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLocalisation(final ILocalisationHit pLocalisation) {
		
		this.localisation = pLocalisation;
		
		/* alimente automatiquement les attributs de la présente classe 
		 * avec les attributs correspondants du COMPOSANT. */
		this.alimenterAttributsLocalisation(this.localisation);
		
	} // Fin de setLocalisation(...).______________________________________

	
	
} // FIN DE LA CLASSE SectionHitEntityJPA.-----------------------------------
