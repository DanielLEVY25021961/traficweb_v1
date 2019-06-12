package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.model.dto.metier.sections.ISectionDarwinDTO;
import levy.daniel.application.model.dto.metier.sections.impl.SectionDarwinDTO;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.factorydescription.FactoryDescription;
import levy.daniel.application.model.services.metier.televersement.importateurs.importeurs.AbstractImporteurNonAscii;

/**
 * CLASSE ImporteurDarwinCsv :<br/>
 * Importeur de fichiers DARWIN_CSV.<br/>
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
 * @since 31 juil. 2014
 *
 */
public class ImporteurDarwinCsv extends AbstractImporteurNonAscii {

	// ************************ATTRIBUTS************************************/

	/* CONSTANTES. */
	/**
	 * "Classe ImporteurDarwinCsv".<br/>
	 */
	public static final String CLASSE_IMPORTEUR_DARWINCSV 
		= "Classe ImporteurDarwinCsv";

	/**
	 * fichier importé sous forme de Map&lt;Integer, ISectionDarwinDTO&gt; avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionDarwinDTO : le DTO encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * ATTENTION : 
	 * faire importerDTO(File, Charset) AVANT pour alimenter cette map.
	 */
	private transient Map<Integer, ISectionDarwinDTO> fichierMapDTO;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ImporteurDarwinCsv.class);

	
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
	public ImporteurDarwinCsv() 
			throws Exception {
		
		super();
		
		/* passe le bon this.specificationChampsMap. */
		this.specificationChampsMap 
			= FactoryDescription.getDecriptionDarwinCsvMap();
		
		/* Passe le bon importateur de description 
		 * this.importateurDescription. */
		this.importateurDescription 
			= FactoryDescription.getImportateurDarwinCsv();
		
		/* Passe le bon DescriptionChamp this.descriptionChamp. */
		this.descriptionChamp 
			= FactoryDescription.getDescriptionChampDarwinCsv();
				
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <ul>
	 * <li>Importe un fichier de données DARWIN_CSV
	 * <b>encodé en pCharset</b> 
	 * en utilisant sa <b>description de fichier</b> et le retourne 
	 * sous forme de 
	 * Map&lt;Integer, ISectionDarwinDTO&gt; 
	 * <code><b>this.fichierMapDTO</b></code> avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionDarwinDTO : le DTO encapsulant une ligne 
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
	 * @param pFile : File : le fichier DARWIN_CSV
	 * à importer.<br/>
	 * @param pCharset : Charset : Jeu de caractères dans lequel 
	 * le fichier à importer a été encodé.<br/>
	 * 
	 * @return this.fichierMapDTO : Map&lt;Integer, ISectionDarwinDTO&gt;
	 * 
	 * @throws Exception
	 */
	public Map<Integer, ISectionDarwinDTO> importerDTO(
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
	 * <b>retourne une Map de DTO à partir d'une 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * construite après import 
	 * d'un fichier de données à l'aide de sa description de fichier</b> 
	 * avec : 
	 * <ul>
	 * <li>Integer : le numéro de la ligne dans le fichier de données</li>
	 * <li>ISectionDarwinDTO : DTO encapsulant la ligne du fichier de données.</li>
	 * </ul>
	 *
	 * @param pMap : 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; : 
	 * Map construite après import d'un fichier de données à l'aide 
	 * de sa description de fichier
	 * 
	 * @return : Map&lt;Integer,ISectionDarwinDTO&gt; :  
	 * Map des DTO.<br/>
	 */
	private Map<Integer, ISectionDarwinDTO> convertirMapDescriptionEnMapDTO(
			final SortedMap<Integer, SortedMap<Integer, String>> pMap) {
		
		if (pMap == null) {
			return null;
		}
		
		final Map<Integer, ISectionDarwinDTO> resultat 
			= new LinkedHashMap<Integer, ISectionDarwinDTO>();
		
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
	 * d'une description de ligne SortedMap&lt;Integer, String&gt;</b>.<br/>
	 * <br/>
	 * - retourne null si pDescriptionLigne == null.<br/>
	 * <br/>
	 *
	 * @param pDescriptionLigne : SortedMap&lt;Integer, String&gt;
	 * 
	 * @return : ISectionDarwinDTO : DTO.<br/>
	 */
	private ISectionDarwinDTO fournirDTO(
			final SortedMap<Integer, String> pDescriptionLigne) {
		
		/* retourne null si pDescriptionLigne == null. */
		if (pDescriptionLigne == null) {
			return null;
		}
		
		final ISectionDarwinDTO sectionDTO = new SectionDarwinDTO();

		sectionDTO.setId(null);
		sectionDTO.setObjetID(pDescriptionLigne.get(1));
		sectionDTO.setRoute(pDescriptionLigne.get(2));
		sectionDTO.setDepPrD(pDescriptionLigne.get(3));
		sectionDTO.setConcessionPrD(pDescriptionLigne.get(4));
		sectionDTO.setPrD(pDescriptionLigne.get(5));
		sectionDTO.setAbsD(pDescriptionLigne.get(6));
		sectionDTO.setDepPrF(pDescriptionLigne.get(7));
		sectionDTO.setConcessionPrF(pDescriptionLigne.get(8));
		sectionDTO.setPrF(pDescriptionLigne.get(9));
		sectionDTO.setAbsF(pDescriptionLigne.get(10));
		sectionDTO.setAnneeMesureTrafic(pDescriptionLigne.get(11));
		sectionDTO.setNumSectionTrafic(pDescriptionLigne.get(12));
		sectionDTO.setSensSectionTrafic(pDescriptionLigne.get(13));
		sectionDTO.setTypeComptageTrafic(pDescriptionLigne.get(14));
		sectionDTO.setMjaN(pDescriptionLigne.get(15));
		sectionDTO.setPcPLN(pDescriptionLigne.get(16));
		sectionDTO.setPcNuitAnnuelN(pDescriptionLigne.get(17));
		sectionDTO.setMjaNmoins1(pDescriptionLigne.get(18));
		sectionDTO.setPcPLNmoins1(pDescriptionLigne.get(19));
		sectionDTO.setPcNuitAnnuelNmoins1(pDescriptionLigne.get(20));
		sectionDTO.setMjaNmoins2(pDescriptionLigne.get(21));
		sectionDTO.setPcPLNmoins2(pDescriptionLigne.get(22));
		sectionDTO.setPcNuitAnnuelNmoins2(pDescriptionLigne.get(23));
		sectionDTO.setMjaNmoins3(pDescriptionLigne.get(24));
		sectionDTO.setPcPLNmoins3(pDescriptionLigne.get(25));
		sectionDTO.setPcNuitAnnuelNmoins3(pDescriptionLigne.get(26));
		sectionDTO.setMjaNmoins4(pDescriptionLigne.get(27));
		sectionDTO.setPcPLNmoins4(pDescriptionLigne.get(28));
		sectionDTO.setPcNuitAnnuelNmoins4(pDescriptionLigne.get(29));
		sectionDTO.setMjaNmoins5(pDescriptionLigne.get(30));
		sectionDTO.setPcPLNmoins5(pDescriptionLigne.get(31));
		sectionDTO.setPcNuitAnnuelNmoins5(pDescriptionLigne.get(32));
		sectionDTO.setMjmNmois01(pDescriptionLigne.get(33));
		sectionDTO.setPcNuitNmois01(pDescriptionLigne.get(34));
		sectionDTO.setMjmNmois02(pDescriptionLigne.get(35));
		sectionDTO.setPcNuitNmois02(pDescriptionLigne.get(36));
		sectionDTO.setMjmNmois03(pDescriptionLigne.get(37));
		sectionDTO.setPcNuitNmois03(pDescriptionLigne.get(38));
		sectionDTO.setMjmNmois04(pDescriptionLigne.get(39));
		sectionDTO.setPcNuitNmois04(pDescriptionLigne.get(40));
		sectionDTO.setMjmNmois05(pDescriptionLigne.get(41));
		sectionDTO.setPcNuitNmois05(pDescriptionLigne.get(42));
		sectionDTO.setMjmNmois06(pDescriptionLigne.get(43));
		sectionDTO.setPcNuitNmois06(pDescriptionLigne.get(44));
		sectionDTO.setMjmNmois07(pDescriptionLigne.get(45));
		sectionDTO.setPcNuitNmois07(pDescriptionLigne.get(46));
		sectionDTO.setMjmNmois08(pDescriptionLigne.get(47));
		sectionDTO.setPcNuitNmois08(pDescriptionLigne.get(48));
		sectionDTO.setMjmNmois09(pDescriptionLigne.get(49));
		sectionDTO.setPcNuitNmois09(pDescriptionLigne.get(50));
		sectionDTO.setMjmNmois10(pDescriptionLigne.get(51));
		sectionDTO.setPcNuitNmois10(pDescriptionLigne.get(52));
		sectionDTO.setMjmNmois11(pDescriptionLigne.get(53));
		sectionDTO.setPcNuitNmois11(pDescriptionLigne.get(54));
		sectionDTO.setMjmNmois12(pDescriptionLigne.get(55));
		sectionDTO.setPcNuitNmois12(pDescriptionLigne.get(56));
		sectionDTO.setClassementRoute(pDescriptionLigne.get(57));
		sectionDTO.setProfilTraversSicre(pDescriptionLigne.get(58));
		sectionDTO.setOrigineSection(pDescriptionLigne.get(59));
		sectionDTO.setExtremiteSection(pDescriptionLigne.get(60));
		sectionDTO.setLieuDitComptage(pDescriptionLigne.get(61));
		sectionDTO.setPrComptage(pDescriptionLigne.get(62));
		sectionDTO.setAbsComptage(pDescriptionLigne.get(63));
		sectionDTO.setItiEuropeen1(pDescriptionLigne.get(64));
		sectionDTO.setSousReseauIndice(pDescriptionLigne.get(65));

		return sectionDTO;
		
	} // Fin de fournirDTO(...).___________________________________________

	
	
	/**
	 * retourne la pI-ème ligne (1-based) du fichier importé 
	 * sous forme de DTO.<br/>
	 * <br/>
	 * - retourne null si this.fichierMapDTO == null.<br/>
	 * - retourne null si pI est hors indexes.<br/>
	 * <br/>
	 *
	 * @param pI : int : numéro (1-based) de la ligne dans le fichier.<br/>
	 * @return : ISectionDarwinDTO : 
	 * pI-ème ligne (1-based) du fichier importé.<br/>
	 */
	public final ISectionDarwinDTO retournerLigneNumero(final int pI) {
		
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
	 * fournit une String pour l'affichage d'une 
	 * Map&lt;Integer,ISectionDarwinDTO&gt;<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Integer,ISectionDarwinDTO&gt;
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public final String afficherMapDTO(
			final Map<Integer, ISectionDarwinDTO> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		final Set<Entry<Integer, ISectionDarwinDTO>> entrySet = pMap.entrySet();
		final Iterator<Entry<Integer, ISectionDarwinDTO>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<Integer, ISectionDarwinDTO> entry = ite.next();
			
			final Integer numeroLigne = entry.getKey();
			final ISectionDarwinDTO dto = entry.getValue();
			
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
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean estLigneEntete(
			final String pLigne) {
		
		if (StringUtils.isBlank(pLigne)) {
			return false;
		}
		
		if (StringUtils.startsWithIgnoreCase(pLigne, "objetid")) {
			return true;
		}
		
		return false;
		
	} // Fin de estLigneEntete(...)._______________________________________
	
	
	
	/**
	 * "importeurdarwincsv.niveau.anomalie".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importeurdarwincsv.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
		
	/**
	 * "importeurdarwincsv.log.erreur".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importeurdarwincsv.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Classe ImporteurDarwinCsv".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererNomClasse() {
		return CLASSE_IMPORTEUR_DARWINCSV;
	} // Fin de recupererNomClasse().______________________________________
	
	
	
	/**
	 * "importeurdarwincsv.importer.filenull".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileNull() {
		return "importeurdarwincsv.importer.filenull";
	} // Fin de upererCleImporterFileNull()._______________________________



	/**
	 * "importeurdarwincsv.importer.filevide".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileVide() {
		return "importeurdarwincsv.importer.filevide";
	} // Fin de recupererCleImporterFileVide().____________________________



	/**
	 * "importeurdarwincsv.importer.fileinexistant".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFileInexistant() {
		return "importeurdarwincsv.importer.fileinexistant";
	} // Fin de recupererCleImporterFileInexistant().______________________


	
	/**
	 * "importeurdarwincsv.importer.filepasnormal".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String recupererCleImporterFilePasNormal() {
		return "importeurdarwincsv.importer.filepasnormal";
	} // Fin de recupererCleImporterFilePasNormal()._______________________ 


	
	/**
	 * Getter du fichier importé sous forme de Map&lt;Integer, ISectionDarwinDTO&gt; 
	 * avec :
	 * <ul>
	 * <li>Integer : le numéro (1-based) de la ligne 
	 * dans le fichier de données</li>
	 * <li>ISectionDarwinDTO : le DTO encapsulant une ligne 
	 * du fichier de données</li>
	 * </ul>
	 * ATTENTION : 
	 * faire importerDTO(File, Charset) AVANT pour alimenter cette map.
	 *
	 * @return this.fichierMapDTO : Map<Integer,ISectionDarwinDTO>.<br/>
	 */
	public final Map<Integer, ISectionDarwinDTO> getFichierMapDTO() {
		return this.fichierMapDTO;
	} // Fin de getFichierMapDTO().________________________________________
	

	
} // FIN DE LA CLASSE ImporteurDarwinCsv.------------------------------------
