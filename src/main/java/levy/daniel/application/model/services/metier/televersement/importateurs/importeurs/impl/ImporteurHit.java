package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.model.dto.metier.sections.ISectionHitDTO;
import levy.daniel.application.model.dto.metier.sections.impl.SectionHitDTO;
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
		
		final SortedMap<Integer, SortedMap<Integer, String>> mapDescriptionsChamps 
			= this.importer(pFile, pCharset);
		
		this.fichierMapDTO 
			= this.convertirMapDescriptionEnMapDTO(mapDescriptionsChamps);
		
		return this.fichierMapDTO;
		
	} // Fin de importerDTO(...).__________________________________________
	
	
	
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
	 * <b>instancie et retourne un DTO à partir 
	 * d'une description de champ SortedMap&lt;Integer, String&gt;</b>.<br/>
	 * <br/>
	 * - retourne null si pDescriptionChamp == null.<br/>
	 * <br/>
	 *
	 * @param pDescriptionChamp : SortedMap&lt;Integer, String&gt;
	 * 
	 * @return : ISectionHitDTO : DTO.<br/>
	 */
	private ISectionHitDTO fournirDTO(
			final SortedMap<Integer, String> pDescriptionChamp) {
		
		/* retourne null si pDescriptionChamp == null. */
		if (pDescriptionChamp == null) {
			return null;
		}
		
		final ISectionHitDTO sectionDTO = new SectionHitDTO();

		sectionDTO.setId(null);
		sectionDTO.setNumDepartement(pDescriptionChamp.get(1));
		sectionDTO.setNumSection(pDescriptionChamp.get(2));
		sectionDTO.setSens(pDescriptionChamp.get(3));
		sectionDTO.setNature(pDescriptionChamp.get(4));
		sectionDTO.setClasse(pDescriptionChamp.get(5));
		sectionDTO.setAnneeTraitement(pDescriptionChamp.get(6));
		sectionDTO.setZoneLibre1(pDescriptionChamp.get(7));
		sectionDTO.setNumRoute(pDescriptionChamp.get(8));
		sectionDTO.setIndiceNumRoute(pDescriptionChamp.get(9));
		sectionDTO.setIndiceLettreRoute(pDescriptionChamp.get(10));
		sectionDTO.setCategorieAdminRoute(pDescriptionChamp.get(11));
		sectionDTO.setTypeComptage(pDescriptionChamp.get(12));
		sectionDTO.setClassementRoute(pDescriptionChamp.get(13));
		sectionDTO.setClasseLargeurChausseeU(pDescriptionChamp.get(14));
		sectionDTO.setClasseLargeurChausseesS(pDescriptionChamp.get(15));
		sectionDTO.setTypeReseau(pDescriptionChamp.get(16));
		sectionDTO.setPRoupK(pDescriptionChamp.get(17));
		sectionDTO.setLieuDitOrigine(pDescriptionChamp.get(18));
		sectionDTO.setPrOrigine(pDescriptionChamp.get(19));
		sectionDTO.setAbsOrigine(pDescriptionChamp.get(20));
		sectionDTO.setLieuDitExtremite(pDescriptionChamp.get(21));
		sectionDTO.setPrExtremite(pDescriptionChamp.get(22));
		sectionDTO.setAbsExtremite(pDescriptionChamp.get(23));
		sectionDTO.setLieuDitComptage(pDescriptionChamp.get(24));
		sectionDTO.setPrComptage(pDescriptionChamp.get(25));
		sectionDTO.setAbsComptage(pDescriptionChamp.get(26));
		sectionDTO.setLongueurSection(pDescriptionChamp.get(27));
		sectionDTO.setLongueurRaseCampagne(pDescriptionChamp.get(28));
		sectionDTO.setNumDepartementRattachement(pDescriptionChamp.get(29));
		sectionDTO.setNumSectionRattachement(pDescriptionChamp.get(30));
		sectionDTO.setSensRattachement(pDescriptionChamp.get(31));
		sectionDTO.setNumDepartementLimitrophe(pDescriptionChamp.get(32));
		sectionDTO.setNumSectionLimitrophe(pDescriptionChamp.get(33));
		sectionDTO.setSensLimitrophe(pDescriptionChamp.get(34));
		sectionDTO.setMoisSectionnement(pDescriptionChamp.get(35));
		sectionDTO.setAnneeSectionnement(pDescriptionChamp.get(36));
		sectionDTO.setZoneLibre2(pDescriptionChamp.get(37));
		sectionDTO.setMjaN(pDescriptionChamp.get(38));
		sectionDTO.setModeCalculN(pDescriptionChamp.get(39));
		sectionDTO.setPcPLN(pDescriptionChamp.get(40));
		sectionDTO.setEvaluationPLN(pDescriptionChamp.get(41));
		sectionDTO.setPcNuitAnnuelN(pDescriptionChamp.get(42));
		sectionDTO.setIndiceFiabiliteMjaN(pDescriptionChamp.get(43));
		sectionDTO.setMjmNmois01(pDescriptionChamp.get(44));
		sectionDTO.setPcNuitNmois01(pDescriptionChamp.get(45));
		sectionDTO.setMjmNmois02(pDescriptionChamp.get(46));
		sectionDTO.setPcNuitNmois02(pDescriptionChamp.get(47));
		sectionDTO.setMjmNmois03(pDescriptionChamp.get(48));
		sectionDTO.setPcNuitNmois03(pDescriptionChamp.get(49));
		sectionDTO.setMjmNmois04(pDescriptionChamp.get(50));
		sectionDTO.setPcNuitNmois04(pDescriptionChamp.get(51));
		sectionDTO.setMjmNmois05(pDescriptionChamp.get(52));
		sectionDTO.setPcNuitNmois05(pDescriptionChamp.get(53));
		sectionDTO.setMjmNmois06(pDescriptionChamp.get(54));
		sectionDTO.setPcNuitNmois06(pDescriptionChamp.get(55));
		sectionDTO.setMjmNmois07(pDescriptionChamp.get(56));
		sectionDTO.setPcNuitNmois07(pDescriptionChamp.get(57));
		sectionDTO.setMjmNmois08(pDescriptionChamp.get(58));
		sectionDTO.setPcNuitNmois08(pDescriptionChamp.get(59));
		sectionDTO.setMjmNmois09(pDescriptionChamp.get(60));
		sectionDTO.setPcNuitNmois09(pDescriptionChamp.get(61));
		sectionDTO.setMjmNmois10(pDescriptionChamp.get(62));
		sectionDTO.setPcNuitNmois10(pDescriptionChamp.get(63));
		sectionDTO.setMjmNmois11(pDescriptionChamp.get(64));
		sectionDTO.setPcNuitNmois11(pDescriptionChamp.get(65));
		sectionDTO.setMjmNmois12(pDescriptionChamp.get(66));
		sectionDTO.setPcNuitNmois12(pDescriptionChamp.get(67));
		sectionDTO.setZoneLibre3(pDescriptionChamp.get(68));
		sectionDTO.setAnneeNmoins1(pDescriptionChamp.get(69));
		sectionDTO.setMjaNmoins1(pDescriptionChamp.get(70));
		sectionDTO.setTypeComptageNmoins1(pDescriptionChamp.get(71));
		sectionDTO.setModeCalculNmoins1(pDescriptionChamp.get(72));
		sectionDTO.setPcPLNmoins1(pDescriptionChamp.get(73));
		sectionDTO.setEvaluationPLNmoins1(pDescriptionChamp.get(74));
		sectionDTO.setPcNuitAnnuelNmoins1(pDescriptionChamp.get(75));
		sectionDTO.setIndiceFiabiliteMjaNmoins1(pDescriptionChamp.get(76));
		sectionDTO.setAnneeNmoins2(pDescriptionChamp.get(77));
		sectionDTO.setMjaNmoins2(pDescriptionChamp.get(78));
		sectionDTO.setTypeComptageNmoins2(pDescriptionChamp.get(79));
		sectionDTO.setModeCalculNmoins2(pDescriptionChamp.get(80));
		sectionDTO.setPcPLNmoins2(pDescriptionChamp.get(81));
		sectionDTO.setEvaluationPLNmoins2(pDescriptionChamp.get(82));
		sectionDTO.setPcNuitAnnuelNmoins2(pDescriptionChamp.get(83));
		sectionDTO.setIndiceFiabiliteMjaNmoins2(pDescriptionChamp.get(84));
		sectionDTO.setAnneeNmoins3(pDescriptionChamp.get(85));
		sectionDTO.setMjaNmoins3(pDescriptionChamp.get(86));
		sectionDTO.setTypeComptageNmoins3(pDescriptionChamp.get(87));
		sectionDTO.setModeCalculNmoins3(pDescriptionChamp.get(88));
		sectionDTO.setPcPLNmoins3(pDescriptionChamp.get(89));
		sectionDTO.setEvaluationPLNmoins3(pDescriptionChamp.get(90));
		sectionDTO.setPcNuitAnnuelNmoins3(pDescriptionChamp.get(91));
		sectionDTO.setIndiceFiabiliteMjaNmoins3(pDescriptionChamp.get(92));
		sectionDTO.setAnneeNmoins4(pDescriptionChamp.get(93));
		sectionDTO.setMjaNmoins4(pDescriptionChamp.get(94));
		sectionDTO.setTypeComptageNmoins4(pDescriptionChamp.get(95));
		sectionDTO.setModeCalculNmoins4(pDescriptionChamp.get(96));
		sectionDTO.setPcPLNmoins4(pDescriptionChamp.get(97));
		sectionDTO.setEvaluationPLNmoins4(pDescriptionChamp.get(98));
		sectionDTO.setPcNuitAnnuelNmoins4(pDescriptionChamp.get(99));
		sectionDTO.setIndiceFiabiliteMjaNmoins4(pDescriptionChamp.get(100));
		sectionDTO.setAnneeNmoins5(pDescriptionChamp.get(101));
		sectionDTO.setMjaNmoins5(pDescriptionChamp.get(102));
		sectionDTO.setTypeComptageNmoins5(pDescriptionChamp.get(103));
		sectionDTO.setModeCalculNmoins5(pDescriptionChamp.get(104));
		sectionDTO.setPcPLNmoins5(pDescriptionChamp.get(105));
		sectionDTO.setEvaluationPLNmoins5(pDescriptionChamp.get(106));
		sectionDTO.setPcNuitAnnuelNmoins5(pDescriptionChamp.get(107));
		sectionDTO.setIndiceFiabiliteMjaNmoins5(pDescriptionChamp.get(108));
		sectionDTO.setMjmNmoins1mois01(pDescriptionChamp.get(109));
		sectionDTO.setPcNuitNmoins1mois01(pDescriptionChamp.get(110));
		sectionDTO.setMjmNmoins1mois02(pDescriptionChamp.get(111));
		sectionDTO.setPcNuitNmoins1mois02(pDescriptionChamp.get(112));
		sectionDTO.setMjmNmoins1mois03(pDescriptionChamp.get(113));
		sectionDTO.setPcNuitNmoins1mois03(pDescriptionChamp.get(114));
		sectionDTO.setMjmNmoins1mois04(pDescriptionChamp.get(115));
		sectionDTO.setPcNuitNmoins1mois04(pDescriptionChamp.get(116));
		sectionDTO.setMjmNmoins1mois05(pDescriptionChamp.get(117));
		sectionDTO.setPcNuitNmoins1mois05(pDescriptionChamp.get(118));
		sectionDTO.setMjmNmoins1mois06(pDescriptionChamp.get(119));
		sectionDTO.setPcNuitNmoins1mois06(pDescriptionChamp.get(120));
		sectionDTO.setMjmNmoins1mois07(pDescriptionChamp.get(121));
		sectionDTO.setPcNuitNmoins1mois07(pDescriptionChamp.get(122));
		sectionDTO.setMjmNmoins1mois08(pDescriptionChamp.get(123));
		sectionDTO.setPcNuitNmoins1mois08(pDescriptionChamp.get(124));
		sectionDTO.setMjmNmoins1mois09(pDescriptionChamp.get(125));
		sectionDTO.setPcNuitNmoins1mois09(pDescriptionChamp.get(126));
		sectionDTO.setMjmNmoins1mois10(pDescriptionChamp.get(127));
		sectionDTO.setPcNuitNmoins1mois10(pDescriptionChamp.get(128));
		sectionDTO.setMjmNmoins1mois11(pDescriptionChamp.get(129));
		sectionDTO.setPcNuitNmoins1mois11(pDescriptionChamp.get(130));
		sectionDTO.setMjmNmoins1mois12(pDescriptionChamp.get(131));
		sectionDTO.setPcNuitNmoins1mois12(pDescriptionChamp.get(132));
		sectionDTO.setZoneLibre4(pDescriptionChamp.get(133));

		return sectionDTO;
		
	} // Fin de fournirDTO(...).___________________________________________

	
	
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
	public final ISectionHitDTO retournerLigneNumero(final int pI) {
		
		/* retourne null si this.fichierMapDTO == null. */
		if (this.fichierMapDTO == null) {
			return null;
		}
		
		return this.fichierMapDTO.get(pI);
		
	} // Fin de retournerLigneNumero(...)._________________________________
	
	
	
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
	
	
	
} // FIN DE LA CLASSE ImporteurHit.------------------------------------------
