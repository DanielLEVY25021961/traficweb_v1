package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;
import levy.daniel.application.model.metier.sections.ISectionHit;
import levy.daniel.application.model.metier.sections.impl.SectionHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl.ImportateurDescriptionHit;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.AbstractImporteurAscii;

/**
 * CLASSE ImporteurHit :<br/>
 * <p>
 * <b>Importeur de fichiers HIT</b>.
 * </p>
 * 
 * <p>
 * ImporteurHit lit un fichier HIT à l'aide de la 
 * Description de fichier {@link ImportateurDescriptionHit} et 
 * la restitue en mémoire sous forme de 
 * <code><b>SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt;</b></code> 
 * avec :
 * <ul>
 * <li>Integer : le numéro (1-based) de la ligne dans le fichier importé.</li>
 * <li>Integer : le numéro d'ordre (1-based) du champ dans la description 
 * du fichier importé.</li>
 * <li>String : la valeur lue pour ce champ.</li>
 * </ul>
 * </p>
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code></code><br/>
 * <code><b></b></code><br/>
 * <code></code><br/>
 * <code><b>SortedMap<Integer, SortedMap<Integer, String>> fichierMap = importeur.importer(fichierDonnees, charsetAnsi);</b></code><br/>
 * <code></code><br/>
 * <code><b></b></code><br/>
 * <code></code><br/>
 * <code><b></b></code><br/>
 * <code></code><br/>
 * <code><b></b></code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * String.format, formatage de chaine de caractères,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 7 juil. 2014
 *
 */
public class ImporteurHit extends AbstractImporteurAscii {

	// ************************ATTRIBUTS************************************/
	/* CONSTANTES. */
	/**
	 * "Classe ImporteurHit".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_HIT 
		= "Classe ImporteurHit";
	
	/**
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";

	/**
	 * fichier importé sous forme de Map&lt;Integer, ISectionHitDTO&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionHitDTO : le DTO encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * ATTENTION : 
	 * faire importerDTO(File, Charset) AVANT pour alimenter cette map.
	 */
	private transient Map<Integer, ISectionHitDTO> fichierMapDTO;

	/**
	 * fichier importé sous forme de Map&lt;Integer, ISectionHit&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionHit : l'OBJET METIER encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * ATTENTION : 
	 * faire importerObjet(File, Charset) AVANT pour alimenter cette map.
	 */
	private transient Map<Integer, ISectionHit> fichierMapObjet;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(ImporteurHit.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>passe le bon <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>Passe le bon importateur de description 
	 * <code><b>this.importateurDescription</b></code>.</li>
	 * <li>Passe le bon DescriptionChamp 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public ImporteurHit() 
			throws Exception {
		
		super();
		
		/* passe le bon this.specificationChampsMap. */
		this.specificationChampsMap 
			= FactoryDescription.getDecriptionHitMap();
		
		/* Passe le bon importateur de description 
		 * this.importateurDescription. */
		this.importateurDescription 
			= FactoryDescription.getImportateurHit();
		
		/* Passe le bon DescriptionChamp this.descriptionChamp. */
		this.descriptionChamp 
			= FactoryDescription.getDescriptionChampHit();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________	
	
	
	
	/**
	 * <ul>
	 * <li>Importe un fichier de données HIT
	 * <b>encodé en pCharset</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * Map&lt;Integer, ISectionHitDTO&gt; 
	 * <code><b>this.fichierMapDTO</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionHitDTO : le DTO encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Alimente <code><b>this.fichierMapDTO</b></code>.</li>
	 * <li>Choisit automatiquement le Charset UTF-8 
	 * si pCharset == null.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier HIT
	 * à importer.<br/>
	 * @param pCharset : Charset : Jeu de caractères dans lequel 
	 * le fichier à importer a été encodé.<br/>
	 * 
	 * @return this.fichierMapDTO : Map&lt;Integer, ISectionHitDTO&gt;
	 * 
	 * @throws Exception
	 */
	public Map<Integer, ISectionHitDTO> importerDTO(
			final File pFile
				, final Charset pCharset) 
							throws Exception {
		
		final SortedMap<Integer, SortedMap<Integer, String>> mapDescriptionsLignes 
			= this.importer(pFile, pCharset);
		
		this.fichierMapDTO 
			= this.convertirMapDescriptionEnMapDTO(mapDescriptionsLignes);
		
		return this.fichierMapDTO;
		
	} // Fin de importerDTO(...).__________________________________________
	
	
	
	/**
	 * <ul>
	 * <li>Importe un fichier de données HIT
	 * <b>encodé en pCharset</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * Map&lt;Integer, ISectionHit&gt; 
	 * <code><b>this.fichierMapObjet</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionHit : l'OBJET METIER encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * </li>
	 * <li>utilise <code>Files.readAllLines(
	 * this.fichierAImporter.toPath(), charset);</code> 
	 * pour lire les lignes du fichier.<br/>
	 * jette donc une Exception de décodage <code>malformedInputException</code> 
	 * si le charset ne correspond pas à celui du fichier de données.</li>
	 * <li>Alimente <code><b>this.fichierImporteMap</b></code>.</li>
	 * <li>Alimente <code><b>this.fichierMapObjet</b></code>.</li>
	 * <li>Choisit automatiquement le Charset UTF-8 
	 * si pCharset == null.</li>
	 * <li>Retire un éventuel caractère BOM-UTF-8 
	 * en début de chaque ligne.</li>
	 * <li>saute une éventuelle ligne vide dans le fichier à importer.</li>
	 * <li>Génère un rapport dans <code><b>this.rapportImportStb</b></code> 
	 * si souci d'import 
	 * (fichier null, inexistant, répertoire, vide ou I/O Exception) 
	 * et si <code><b>this.logImport</b></code> vaut true.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pFile : File : le fichier HIT
	 * à importer.<br/>
	 * @param pCharset : Charset : Jeu de caractères dans lequel 
	 * le fichier à importer a été encodé.<br/>
	 * 
	 * @return this.fichierMapDTO : Map&lt;Integer, ISectionHitDTO&gt;
	 * 
	 * @throws Exception
	 */
	public Map<Integer, ISectionHit> importerObjet(
			final File pFile
				, final Charset pCharset) 
							throws Exception {
		
		final SortedMap<Integer, SortedMap<Integer, String>> mapDescriptionsLignes 
			= this.importer(pFile, pCharset);
		
		this.fichierMapObjet 
			= this.convertirMapDescriptionEnMapObjet(mapDescriptionsLignes);
		
		return this.fichierMapObjet;
		
	} // Fin de importerObjet(...).________________________________________
	
	
	
	/**
	 * <b>retourne une Map de DTO à partir d'une 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * construite après import 
	 * d'un fichier de données à l'aide de sa description de fichier</b> 
	 * avec : 
	 * <ul>
	 * <li>Integer : le numéro de la ligne dans le fichier de données</li>
	 * <li>ISectionHitDTO : DTO encapsulant la ligne du fichier de données.</li>
	 * </ul>
	 *
	 * @param pMap : 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map construite après import d'un fichier de données à l'aide 
	 * de sa description de fichier
	 * 
	 * @return : Map&lt;Integer,ISectionHitDTO&gt; :  
	 * Map des DTO.<br/>
	 */
	private Map<Integer, ISectionHitDTO> convertirMapDescriptionEnMapDTO(
			final SortedMap<Integer, SortedMap<Integer, String>> pMap) {
		
		if (pMap == null) {
			return null;
		}
		
		final Map<Integer, ISectionHitDTO> resultat 
			= new LinkedHashMap<Integer, ISectionHitDTO>();
		
		final Set<Entry<Integer, SortedMap<Integer, String>>> entrySet 
			= pMap.entrySet();
		
		final Iterator<Entry<Integer, SortedMap<Integer, String>>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, SortedMap<Integer, String>> entry = ite.next();
			
			final Integer numeroLigne 
				= entry.getKey();
			final SortedMap<Integer, String> descriptionChamp 
				= entry.getValue();
			
			resultat.put(numeroLigne, fournirDTO(descriptionChamp));
		}
		
		return resultat;
		
	} // Fin de convertirMapDescriptionEnMapDTO(...).______________________
	
	
	
	/**
	 * <b>retourne une Map d'OBJETS METIER à partir d'une 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * construite après import 
	 * d'un fichier de données à l'aide de sa description de fichier</b> 
	 * avec : 
	 * <ul>
	 * <li>Integer : le numéro de la ligne dans le fichier de données</li>
	 * <li>ISectionHit : 
	 * OBJET METIER encapsulant la ligne du fichier de données.</li>
	 * </ul>
	 *
	 * @param pMap : 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map construite après import d'un fichier de données à l'aide 
	 * de sa description de fichier
	 * 
	 * @return : Map&lt;Integer,ISectionHit&gt; :  
	 * Map des OBJETS METIER.<br/>
	 */
	private Map<Integer, ISectionHit> convertirMapDescriptionEnMapObjet(
			final SortedMap<Integer, SortedMap<Integer, String>> pMap) {
		
		if (pMap == null) {
			return null;
		}
		
		final Map<Integer, ISectionHit> resultat 
			= new LinkedHashMap<Integer, ISectionHit>();
		
		final Set<Entry<Integer, SortedMap<Integer, String>>> entrySet 
			= pMap.entrySet();
		
		final Iterator<Entry<Integer, SortedMap<Integer, String>>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, SortedMap<Integer, String>> entry = ite.next();
			
			final Integer numeroLigne 
				= entry.getKey();
			final SortedMap<Integer, String> descriptionChamp 
				= entry.getValue();
			
			resultat.put(numeroLigne, fournirObjetMetier(descriptionChamp));
		}
		
		return resultat;
		
	} // Fin de convertirMapDescriptionEnMapObjet(...).____________________
	
	
	
	/**
	 * <b>instancie et retourne un DTO à partir 
	 * d'une description de ligne SortedMap&lt;Integer, String&gt;</b>.<br/>
	 * <br/>
	 * - retourne null si pDescriptionLigne == null.<br/>
	 * <br/>
	 *
	 * @param pDescriptionLigne : SortedMap&lt;Integer, String&gt;
	 * 
	 * @return : ISectionHitDTO : DTO.<br/>
	 */
	private ISectionHitDTO fournirDTO(
			final SortedMap<Integer, String> pDescriptionLigne) {
		
		/* retourne null si pDescriptionLigne == null. */
		if (pDescriptionLigne == null) {
			return null;
		}
		
		final ISectionHitDTO sectionDTO = new SectionHitDTO();

		sectionDTO.setId(null);
		sectionDTO.setNumDepartement(pDescriptionLigne.get(1));
		sectionDTO.setNumSection(pDescriptionLigne.get(2));
		sectionDTO.setSens(pDescriptionLigne.get(3));
		sectionDTO.setNature(pDescriptionLigne.get(4));
		sectionDTO.setClasse(pDescriptionLigne.get(5));
		sectionDTO.setAnneeTraitement(pDescriptionLigne.get(6));
		sectionDTO.setZoneLibre1(pDescriptionLigne.get(7));
		sectionDTO.setNumRoute(pDescriptionLigne.get(8));
		sectionDTO.setIndiceNumRoute(pDescriptionLigne.get(9));
		sectionDTO.setIndiceLettreRoute(pDescriptionLigne.get(10));
		sectionDTO.setCategorieAdminRoute(pDescriptionLigne.get(11));
		sectionDTO.setTypeComptage(pDescriptionLigne.get(12));
		sectionDTO.setClassementRoute(pDescriptionLigne.get(13));
		sectionDTO.setClasseLargeurChausseeU(pDescriptionLigne.get(14));
		sectionDTO.setClasseLargeurChausseesS(pDescriptionLigne.get(15));
		sectionDTO.setTypeReseau(pDescriptionLigne.get(16));
		sectionDTO.setPRoupK(pDescriptionLigne.get(17));
		sectionDTO.setLieuDitOrigine(pDescriptionLigne.get(18));
		sectionDTO.setPrOrigine(pDescriptionLigne.get(19));
		sectionDTO.setAbsOrigine(pDescriptionLigne.get(20));
		sectionDTO.setLieuDitExtremite(pDescriptionLigne.get(21));
		sectionDTO.setPrExtremite(pDescriptionLigne.get(22));
		sectionDTO.setAbsExtremite(pDescriptionLigne.get(23));
		sectionDTO.setLieuDitComptage(pDescriptionLigne.get(24));
		sectionDTO.setPrComptage(pDescriptionLigne.get(25));
		sectionDTO.setAbsComptage(pDescriptionLigne.get(26));
		sectionDTO.setLongueurSection(pDescriptionLigne.get(27));
		sectionDTO.setLongueurRaseCampagne(pDescriptionLigne.get(28));
		sectionDTO.setNumDepartementRattachement(pDescriptionLigne.get(29));
		sectionDTO.setNumSectionRattachement(pDescriptionLigne.get(30));
		sectionDTO.setSensRattachement(pDescriptionLigne.get(31));
		sectionDTO.setNumDepartementLimitrophe(pDescriptionLigne.get(32));
		sectionDTO.setNumSectionLimitrophe(pDescriptionLigne.get(33));
		sectionDTO.setSensLimitrophe(pDescriptionLigne.get(34));
		sectionDTO.setMoisSectionnement(pDescriptionLigne.get(35));
		sectionDTO.setAnneeSectionnement(pDescriptionLigne.get(36));
		sectionDTO.setZoneLibre2(pDescriptionLigne.get(37));
		sectionDTO.setMjaN(pDescriptionLigne.get(38));
		sectionDTO.setModeCalculN(pDescriptionLigne.get(39));
		sectionDTO.setPcPLN(pDescriptionLigne.get(40));
		sectionDTO.setEvaluationPLN(pDescriptionLigne.get(41));
		sectionDTO.setPcNuitAnnuelN(pDescriptionLigne.get(42));
		sectionDTO.setIndiceFiabiliteMjaN(pDescriptionLigne.get(43));
		sectionDTO.setMjmNmois01(pDescriptionLigne.get(44));
		sectionDTO.setPcNuitNmois01(pDescriptionLigne.get(45));
		sectionDTO.setMjmNmois02(pDescriptionLigne.get(46));
		sectionDTO.setPcNuitNmois02(pDescriptionLigne.get(47));
		sectionDTO.setMjmNmois03(pDescriptionLigne.get(48));
		sectionDTO.setPcNuitNmois03(pDescriptionLigne.get(49));
		sectionDTO.setMjmNmois04(pDescriptionLigne.get(50));
		sectionDTO.setPcNuitNmois04(pDescriptionLigne.get(51));
		sectionDTO.setMjmNmois05(pDescriptionLigne.get(52));
		sectionDTO.setPcNuitNmois05(pDescriptionLigne.get(53));
		sectionDTO.setMjmNmois06(pDescriptionLigne.get(54));
		sectionDTO.setPcNuitNmois06(pDescriptionLigne.get(55));
		sectionDTO.setMjmNmois07(pDescriptionLigne.get(56));
		sectionDTO.setPcNuitNmois07(pDescriptionLigne.get(57));
		sectionDTO.setMjmNmois08(pDescriptionLigne.get(58));
		sectionDTO.setPcNuitNmois08(pDescriptionLigne.get(59));
		sectionDTO.setMjmNmois09(pDescriptionLigne.get(60));
		sectionDTO.setPcNuitNmois09(pDescriptionLigne.get(61));
		sectionDTO.setMjmNmois10(pDescriptionLigne.get(62));
		sectionDTO.setPcNuitNmois10(pDescriptionLigne.get(63));
		sectionDTO.setMjmNmois11(pDescriptionLigne.get(64));
		sectionDTO.setPcNuitNmois11(pDescriptionLigne.get(65));
		sectionDTO.setMjmNmois12(pDescriptionLigne.get(66));
		sectionDTO.setPcNuitNmois12(pDescriptionLigne.get(67));
		sectionDTO.setZoneLibre3(pDescriptionLigne.get(68));
		sectionDTO.setAnneeNmoins1(pDescriptionLigne.get(69));
		sectionDTO.setMjaNmoins1(pDescriptionLigne.get(70));
		sectionDTO.setTypeComptageNmoins1(pDescriptionLigne.get(71));
		sectionDTO.setModeCalculNmoins1(pDescriptionLigne.get(72));
		sectionDTO.setPcPLNmoins1(pDescriptionLigne.get(73));
		sectionDTO.setEvaluationPLNmoins1(pDescriptionLigne.get(74));
		sectionDTO.setPcNuitAnnuelNmoins1(pDescriptionLigne.get(75));
		sectionDTO.setIndiceFiabiliteMjaNmoins1(pDescriptionLigne.get(76));
		sectionDTO.setAnneeNmoins2(pDescriptionLigne.get(77));
		sectionDTO.setMjaNmoins2(pDescriptionLigne.get(78));
		sectionDTO.setTypeComptageNmoins2(pDescriptionLigne.get(79));
		sectionDTO.setModeCalculNmoins2(pDescriptionLigne.get(80));
		sectionDTO.setPcPLNmoins2(pDescriptionLigne.get(81));
		sectionDTO.setEvaluationPLNmoins2(pDescriptionLigne.get(82));
		sectionDTO.setPcNuitAnnuelNmoins2(pDescriptionLigne.get(83));
		sectionDTO.setIndiceFiabiliteMjaNmoins2(pDescriptionLigne.get(84));
		sectionDTO.setAnneeNmoins3(pDescriptionLigne.get(85));
		sectionDTO.setMjaNmoins3(pDescriptionLigne.get(86));
		sectionDTO.setTypeComptageNmoins3(pDescriptionLigne.get(87));
		sectionDTO.setModeCalculNmoins3(pDescriptionLigne.get(88));
		sectionDTO.setPcPLNmoins3(pDescriptionLigne.get(89));
		sectionDTO.setEvaluationPLNmoins3(pDescriptionLigne.get(90));
		sectionDTO.setPcNuitAnnuelNmoins3(pDescriptionLigne.get(91));
		sectionDTO.setIndiceFiabiliteMjaNmoins3(pDescriptionLigne.get(92));
		sectionDTO.setAnneeNmoins4(pDescriptionLigne.get(93));
		sectionDTO.setMjaNmoins4(pDescriptionLigne.get(94));
		sectionDTO.setTypeComptageNmoins4(pDescriptionLigne.get(95));
		sectionDTO.setModeCalculNmoins4(pDescriptionLigne.get(96));
		sectionDTO.setPcPLNmoins4(pDescriptionLigne.get(97));
		sectionDTO.setEvaluationPLNmoins4(pDescriptionLigne.get(98));
		sectionDTO.setPcNuitAnnuelNmoins4(pDescriptionLigne.get(99));
		sectionDTO.setIndiceFiabiliteMjaNmoins4(pDescriptionLigne.get(100));
		sectionDTO.setAnneeNmoins5(pDescriptionLigne.get(101));
		sectionDTO.setMjaNmoins5(pDescriptionLigne.get(102));
		sectionDTO.setTypeComptageNmoins5(pDescriptionLigne.get(103));
		sectionDTO.setModeCalculNmoins5(pDescriptionLigne.get(104));
		sectionDTO.setPcPLNmoins5(pDescriptionLigne.get(105));
		sectionDTO.setEvaluationPLNmoins5(pDescriptionLigne.get(106));
		sectionDTO.setPcNuitAnnuelNmoins5(pDescriptionLigne.get(107));
		sectionDTO.setIndiceFiabiliteMjaNmoins5(pDescriptionLigne.get(108));
		sectionDTO.setMjmNmoins1mois01(pDescriptionLigne.get(109));
		sectionDTO.setPcNuitNmoins1mois01(pDescriptionLigne.get(110));
		sectionDTO.setMjmNmoins1mois02(pDescriptionLigne.get(111));
		sectionDTO.setPcNuitNmoins1mois02(pDescriptionLigne.get(112));
		sectionDTO.setMjmNmoins1mois03(pDescriptionLigne.get(113));
		sectionDTO.setPcNuitNmoins1mois03(pDescriptionLigne.get(114));
		sectionDTO.setMjmNmoins1mois04(pDescriptionLigne.get(115));
		sectionDTO.setPcNuitNmoins1mois04(pDescriptionLigne.get(116));
		sectionDTO.setMjmNmoins1mois05(pDescriptionLigne.get(117));
		sectionDTO.setPcNuitNmoins1mois05(pDescriptionLigne.get(118));
		sectionDTO.setMjmNmoins1mois06(pDescriptionLigne.get(119));
		sectionDTO.setPcNuitNmoins1mois06(pDescriptionLigne.get(120));
		sectionDTO.setMjmNmoins1mois07(pDescriptionLigne.get(121));
		sectionDTO.setPcNuitNmoins1mois07(pDescriptionLigne.get(122));
		sectionDTO.setMjmNmoins1mois08(pDescriptionLigne.get(123));
		sectionDTO.setPcNuitNmoins1mois08(pDescriptionLigne.get(124));
		sectionDTO.setMjmNmoins1mois09(pDescriptionLigne.get(125));
		sectionDTO.setPcNuitNmoins1mois09(pDescriptionLigne.get(126));
		sectionDTO.setMjmNmoins1mois10(pDescriptionLigne.get(127));
		sectionDTO.setPcNuitNmoins1mois10(pDescriptionLigne.get(128));
		sectionDTO.setMjmNmoins1mois11(pDescriptionLigne.get(129));
		sectionDTO.setPcNuitNmoins1mois11(pDescriptionLigne.get(130));
		sectionDTO.setMjmNmoins1mois12(pDescriptionLigne.get(131));
		sectionDTO.setPcNuitNmoins1mois12(pDescriptionLigne.get(132));
		sectionDTO.setZoneLibre4(pDescriptionLigne.get(133));

		return sectionDTO;
		
	} // Fin de fournirDTO(...).___________________________________________
	
	
	
	/**
	 * <b>instancie et retourne un OBJET METIER à partir 
	 * d'une description de ligne SortedMap&lt;Integer, String&gt;</b>.<br/>
	 * <br/>
	 * - retourne null si pDescriptionLigne == null.<br/>
	 * <br/>
	 *
	 * @param pDescriptionLigne : SortedMap&lt;Integer, String&gt;
	 * 
	 * @return : ISectionHit : OBJET METIER.<br/>
	 */
	private ISectionHit fournirObjetMetier(
			final SortedMap<Integer, String> pDescriptionLigne) {
		
		/* retourne null si pDescriptionLigne == null. */
		if (pDescriptionLigne == null) {
			return null;
		}
		
		final ISectionHit objet = new SectionHit();

		objet.setId(null);
		objet.setNumDepartement(pDescriptionLigne.get(1));
		objet.setNumSection(pDescriptionLigne.get(2));
		objet.setSens(pDescriptionLigne.get(3));
		objet.setNature(pDescriptionLigne.get(4));
		objet.setClasse(pDescriptionLigne.get(5));
		objet.setAnneeTraitement(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(6)));
		objet.setZoneLibre1(pDescriptionLigne.get(7));
		objet.setNumRoute(pDescriptionLigne.get(8));
		objet.setIndiceNumRoute(pDescriptionLigne.get(9));
		objet.setIndiceLettreRoute(pDescriptionLigne.get(10));
		objet.setCategorieAdminRoute(pDescriptionLigne.get(11));
		objet.setTypeComptage(pDescriptionLigne.get(12));
		objet.setClassementRoute(pDescriptionLigne.get(13));
		objet.setClasseLargeurChausseeU(pDescriptionLigne.get(14));
		objet.setClasseLargeurChausseesS(pDescriptionLigne.get(15));
		objet.setTypeReseau(pDescriptionLigne.get(16));
		objet.setPRoupK(pDescriptionLigne.get(17));
		objet.setLieuDitOrigine(pDescriptionLigne.get(18));
		objet.setPrOrigine(
				this.fournirInteger(pDescriptionLigne.get(19)));
		objet.setAbsOrigine(
				this.fournirInteger(pDescriptionLigne.get(20)));
		objet.setLieuDitExtremite(pDescriptionLigne.get(21));
		objet.setPrExtremite(
				this.fournirInteger(pDescriptionLigne.get(22)));
		objet.setAbsExtremite(
				this.fournirInteger(pDescriptionLigne.get(23)));
		objet.setLieuDitComptage(pDescriptionLigne.get(24));
		objet.setPrComptage(
				this.fournirInteger(pDescriptionLigne.get(25)));
		objet.setAbsComptage(
				this.fournirInteger(pDescriptionLigne.get(26)));
		objet.setLongueurSection(
				this.fournirInteger(pDescriptionLigne.get(27)));
		objet.setLongueurRaseCampagne(
				this.fournirInteger(pDescriptionLigne.get(28)));
		objet.setNumDepartementRattachement(pDescriptionLigne.get(29));
		objet.setNumSectionRattachement(pDescriptionLigne.get(30));
		objet.setSensRattachement(pDescriptionLigne.get(31));
		objet.setNumDepartementLimitrophe(pDescriptionLigne.get(32));
		objet.setNumSectionLimitrophe(pDescriptionLigne.get(33));
		objet.setSensLimitrophe(pDescriptionLigne.get(34));
		objet.setMoisSectionnement(pDescriptionLigne.get(35));
		objet.setAnneeSectionnement(pDescriptionLigne.get(36));
		objet.setZoneLibre2(pDescriptionLigne.get(37));
		objet.setMjaN(this.fournirInteger(pDescriptionLigne.get(38)));
		objet.setModeCalculN(pDescriptionLigne.get(39));
		objet.setPcPLN(pDescriptionLigne.get(40));
		objet.setEvaluationPLN(pDescriptionLigne.get(41));
		objet.setPcNuitAnnuelN(pDescriptionLigne.get(42));
		objet.setIndiceFiabiliteMjaN(pDescriptionLigne.get(43));
		objet.setMjmNmois01(
				this.fournirInteger(pDescriptionLigne.get(44)));
		objet.setPcNuitNmois01(pDescriptionLigne.get(45));
		objet.setMjmNmois02(
				this.fournirInteger(pDescriptionLigne.get(46)));
		objet.setPcNuitNmois02(pDescriptionLigne.get(47));
		objet.setMjmNmois03(
				this.fournirInteger(pDescriptionLigne.get(48)));
		objet.setPcNuitNmois03(pDescriptionLigne.get(49));
		objet.setMjmNmois04(
				this.fournirInteger(pDescriptionLigne.get(50)));
		objet.setPcNuitNmois04(pDescriptionLigne.get(51));
		objet.setMjmNmois05(
				this.fournirInteger(pDescriptionLigne.get(52)));
		objet.setPcNuitNmois05(pDescriptionLigne.get(53));
		objet.setMjmNmois06(
				this.fournirInteger(pDescriptionLigne.get(54)));
		objet.setPcNuitNmois06(pDescriptionLigne.get(55));
		objet.setMjmNmois07(
				this.fournirInteger(pDescriptionLigne.get(56)));
		objet.setPcNuitNmois07(pDescriptionLigne.get(57));
		objet.setMjmNmois08(
				this.fournirInteger(pDescriptionLigne.get(58)));
		objet.setPcNuitNmois08(pDescriptionLigne.get(59));
		objet.setMjmNmois09(
				this.fournirInteger(pDescriptionLigne.get(60)));
		objet.setPcNuitNmois09(pDescriptionLigne.get(61));
		objet.setMjmNmois10(
				this.fournirInteger(pDescriptionLigne.get(62)));
		objet.setPcNuitNmois10(pDescriptionLigne.get(63));
		objet.setMjmNmois11(
				this.fournirInteger(pDescriptionLigne.get(64)));
		objet.setPcNuitNmois11(pDescriptionLigne.get(65));
		objet.setMjmNmois12(
				this.fournirInteger(pDescriptionLigne.get(66)));
		objet.setPcNuitNmois12(pDescriptionLigne.get(67));
		objet.setZoneLibre3(pDescriptionLigne.get(68));
		objet.setAnneeNmoins1(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(69)));
		objet.setMjaNmoins1(
				this.fournirInteger(pDescriptionLigne.get(70)));
		objet.setTypeComptageNmoins1(pDescriptionLigne.get(71));
		objet.setModeCalculNmoins1(pDescriptionLigne.get(72));
		objet.setPcPLNmoins1(pDescriptionLigne.get(73));
		objet.setEvaluationPLNmoins1(pDescriptionLigne.get(74));
		objet.setPcNuitAnnuelNmoins1(pDescriptionLigne.get(75));
		objet.setIndiceFiabiliteMjaNmoins1(pDescriptionLigne.get(76));
		objet.setAnneeNmoins2(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(77)));
		objet.setMjaNmoins2(
				this.fournirInteger(pDescriptionLigne.get(78)));
		objet.setTypeComptageNmoins2(pDescriptionLigne.get(79));
		objet.setModeCalculNmoins2(pDescriptionLigne.get(80));
		objet.setPcPLNmoins2(pDescriptionLigne.get(81));
		objet.setEvaluationPLNmoins2(pDescriptionLigne.get(82));
		objet.setPcNuitAnnuelNmoins2(pDescriptionLigne.get(83));
		objet.setIndiceFiabiliteMjaNmoins2(pDescriptionLigne.get(84));
		objet.setAnneeNmoins3(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(85)));
		objet.setMjaNmoins3(
				this.fournirInteger(pDescriptionLigne.get(86)));
		objet.setTypeComptageNmoins3(pDescriptionLigne.get(87));
		objet.setModeCalculNmoins3(pDescriptionLigne.get(88));
		objet.setPcPLNmoins3(pDescriptionLigne.get(89));
		objet.setEvaluationPLNmoins3(pDescriptionLigne.get(90));
		objet.setPcNuitAnnuelNmoins3(pDescriptionLigne.get(91));
		objet.setIndiceFiabiliteMjaNmoins3(pDescriptionLigne.get(92));
		objet.setAnneeNmoins4(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(93)));
		objet.setMjaNmoins4(
				this.fournirInteger(pDescriptionLigne.get(94)));
		objet.setTypeComptageNmoins4(pDescriptionLigne.get(95));
		objet.setModeCalculNmoins4(pDescriptionLigne.get(96));
		objet.setPcPLNmoins4(pDescriptionLigne.get(97));
		objet.setEvaluationPLNmoins4(pDescriptionLigne.get(98));
		objet.setPcNuitAnnuelNmoins4(pDescriptionLigne.get(99));
		objet.setIndiceFiabiliteMjaNmoins4(pDescriptionLigne.get(100));
		objet.setAnneeNmoins5(
				this.fournirDateAvecAnneeSurDeuxChiffres(
						pDescriptionLigne.get(101)));
		objet.setMjaNmoins5(
				this.fournirInteger(pDescriptionLigne.get(102)));
		objet.setTypeComptageNmoins5(pDescriptionLigne.get(103));
		objet.setModeCalculNmoins5(pDescriptionLigne.get(104));
		objet.setPcPLNmoins5(pDescriptionLigne.get(105));
		objet.setEvaluationPLNmoins5(pDescriptionLigne.get(106));
		objet.setPcNuitAnnuelNmoins5(pDescriptionLigne.get(107));
		objet.setIndiceFiabiliteMjaNmoins5(pDescriptionLigne.get(108));
		objet.setMjmNmoins1mois01(
				this.fournirInteger(pDescriptionLigne.get(109)));
		objet.setPcNuitNmoins1mois01(pDescriptionLigne.get(110));
		objet.setMjmNmoins1mois02(
				this.fournirInteger(pDescriptionLigne.get(111)));
		objet.setPcNuitNmoins1mois02(pDescriptionLigne.get(112));
		objet.setMjmNmoins1mois03(
				this.fournirInteger(pDescriptionLigne.get(113)));
		objet.setPcNuitNmoins1mois03(pDescriptionLigne.get(114));
		objet.setMjmNmoins1mois04(
				this.fournirInteger(pDescriptionLigne.get(115)));
		objet.setPcNuitNmoins1mois04(pDescriptionLigne.get(116));
		objet.setMjmNmoins1mois05(
				this.fournirInteger(pDescriptionLigne.get(117)));
		objet.setPcNuitNmoins1mois05(pDescriptionLigne.get(118));
		objet.setMjmNmoins1mois06(
				this.fournirInteger(pDescriptionLigne.get(119)));
		objet.setPcNuitNmoins1mois06(pDescriptionLigne.get(120));
		objet.setMjmNmoins1mois07(
				this.fournirInteger(pDescriptionLigne.get(121)));
		objet.setPcNuitNmoins1mois07(pDescriptionLigne.get(122));
		objet.setMjmNmoins1mois08(
				this.fournirInteger(pDescriptionLigne.get(123)));
		objet.setPcNuitNmoins1mois08(pDescriptionLigne.get(124));
		objet.setMjmNmoins1mois09(
				this.fournirInteger(pDescriptionLigne.get(125)));
		objet.setPcNuitNmoins1mois09(pDescriptionLigne.get(126));
		objet.setMjmNmoins1mois10(
				this.fournirInteger(pDescriptionLigne.get(127)));
		objet.setPcNuitNmoins1mois10(pDescriptionLigne.get(128));
		objet.setMjmNmoins1mois11(
				this.fournirInteger(pDescriptionLigne.get(129)));
		objet.setPcNuitNmoins1mois11(pDescriptionLigne.get(130));
		objet.setMjmNmoins1mois12(
				this.fournirInteger(pDescriptionLigne.get(131)));
		objet.setPcNuitNmoins1mois12(pDescriptionLigne.get(132));
		objet.setZoneLibre4(pDescriptionLigne.get(133));

		return objet;
		
	} // Fin de fournirObjetMetier(...).___________________________________

	
	
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
				= CLASSE_IMPORTEUR_HIT 
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
				= CLASSE_IMPORTEUR_HIT
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
	 * fournit une String pour l'affichage d'une 
	 * Map&lt;Integer,ISectionHitDTO&gt;<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer,ISectionHitDTO&gt;
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public final String afficherMapDTO(
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
			final ISectionHitDTO dto = entry.getValue();
			
			final String ligne 
				= String.format(
						LocaleManager.getLocaleApplication()
						, "Ligne %1$-7d : %2$s"
						, numeroLigne
						, dto.toString());
			
			stb.append(ligne);
			stb.append(NEWLINE);
		}
		
		return stb.toString();
	
	} // Fin de afficherMapDTO(...)._______________________________________

	
	
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
	public final String afficherMapObjet(
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
	 * retourne la pI-ème ligne (1-based) du fichier importé 
	 * sous forme de DTO.<br/>
	 * <br/>
	 * - retourne null si this.fichierMapDTO == null.<br/>
	 * - retourne null si pI est hors indexes.<br/>
	 * <br/>
	 *
	 * @param pI : int : numéro (1-based) de la ligne dans le fichier.<br/>
	 * @return : ISectionHitDTO : 
	 * pI-ème ligne (1-based) du fichier importé.<br/>
	 */
	public final ISectionHitDTO retournerDTOLigneNumero(final int pI) {
		
		/* retourne null si this.fichierMapDTO == null. */
		if (this.fichierMapDTO == null) {
			return null;
		}
		
		return this.fichierMapDTO.get(pI);
		
	} // Fin de retournerDTOLigneNumero(...)._______________________________

	
	
	/**
	 * retourne la pI-ème ligne (1-based) du fichier importé 
	 * sous forme d'OBJET METIER.<br/>
	 * <br/>
	 * - retourne null si this.fichierMapObjet == null.<br/>
	 * - retourne null si pI est hors indexes.<br/>
	 * <br/>
	 *
	 * @param pI : int : numéro (1-based) de la ligne dans le fichier.<br/>
	 * @return : ISectionHit : 
	 * pI-ème ligne (1-based) du fichier importé.<br/>
	 */
	public final ISectionHit retournerObjetLigneNumero(final int pI) {
		
		/* retourne null si this.fichierMapObjet == null. */
		if (this.fichierMapObjet == null) {
			return null;
		}
		
		return this.fichierMapObjet.get(pI);
		
	} // Fin de retournerObjetLigneNumero(...).____________________________
	
	
	
	/**
	 * retourne le nombre de lignes dans le fichier importé.<br/>
	 * <br/>
	 * - retourne 0 si <code>this.fichierMapDTO</code> == null.<br/>
	 * <br>
	 *
	 * @return : int :  nombre de lignes dans le fichier importé.<br/>
	 */
	public final int fournirNombreDeLignes() {
		
		/* retourne 0 si this.fichierMapDTO == null. */
		if (this.fichierMapDTO == null) {
			return 0;
		}
		
		return this.fichierMapDTO.size();
		
	} // Fin de fournirNombreDeLignes().___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 * "importeurhit.niveau.anomalie".<br/>
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurhit.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * {@inheritDoc}
	 * "importeurhit.log.erreur".<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurhit.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurHit".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_HIT;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurhit.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurhit.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurhit.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurhit.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurhit.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurhit.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurhit.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurhit.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 


	
	/**
	 * Getter du fichier importé sous forme de Map&lt;Integer, ISectionHitDTO&gt; 
	 * avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionHitDTO : le DTO encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * ATTENTION : 
	 * faire importerDTO(File, Charset) AVANT pour alimenter cette map.
	 *
	 * @return this.fichierMapDTO : Map<Integer,ISectionHitDTO>.<br/>
	 */
	public final Map<Integer, ISectionHitDTO> getFichierMapDTO() {
		return this.fichierMapDTO;
	} // Fin de getFichierMapDTO().________________________________________


	
	/**
	 * Getter du fichier importé sous forme de Map&lt;Integer, ISectionHit&gt; 
	 * avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionHit : l'OBJET METIER encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * ATTENTION : 
	 * faire importerObjet(File, Charset) AVANT pour alimenter cette map.
	 *
	 * @return this.fichierMapObjet : Map<Integer,ISectionHit>.<br/>
	 */
	public final Map<Integer, ISectionHit> getFichierMapObjet() {
		return this.fichierMapObjet;
	} // Fin de getFichierMapObjet()().____________________________________
	
	
	
} // FIN DE LA CLASSE ImporteurHit.------------------------------------------
