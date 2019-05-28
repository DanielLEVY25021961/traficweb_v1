package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions.ConfigurationDescriptionsFichiersManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampMapping;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescription;

/**
 * class ImportateurDescriptionMapping :<br/>
 * <p>
 * Importateur concret pour les DESCRIPTIONS EN CSV 
 * des MAPPING Histonat-HIT-Darwin-ISIDOR (écrit en csv avc séparateur ';').
 * </p>
 * Chargé de lire une description de fichier de 
 * Mapping Histonat-HIT-Darwin-ISIDOR 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * specificationChampsMap.<br/>
 * <br/>
 * Un fichier de description d'un Mapping Histonat-HIT-Darwin-ISIDOR formatée en csv (';') 
 * commence par :<br/>
 * <br/>
 * ordreChampsHistonat;intituleHistonat;champJavaHistonat;colonneDebut;colonneFin;longueurCalculee;ordreChampsHit;intituleHit;champJavaHit;ordreChampDarwin;intituleDarwin;champJavaDarwin;baliseIsidor;isLocalisant;<br/>
 * 1;Numéro de Département;numDepartement;1;3;3;1;Numéro de Département;numDepartement;null;null;null;numDepartement;metier;<br/>
 * 2;Numéro de Section ;numSectionTrafic;4;9;6;2;Numéro de Section;numSection;12;Numéro de la Section de Trafic;numSectionTrafic;numSectionTrafic;metier;<br/>
 * 3;Sens;sensSectionTrafic;10;10;1;3;Sens;sens;13;Sens de la Section de Trafic;sensSectionTrafic;sensSectionTrafic;metier;<br/>
 * 4;Nature ;nature;11;11;1;4;Nature;nature;null;null;null;nature;metier;<br/>
 * 5;Classe;classe;12;13;2;5;Classe;classe;null;null;null;classe;metier;<br/>
 * 6;Année de traitement;anneeMesureTrafic;14;15;2;6;Année de traitement;anneeTraitement;11;Année de la mesure de trafic;anneeMesureTrafic;anneeMesureTrafic;metier;<br/>
 * 7;Zone libre;sans objet;16;16;1;7;Zone libre;sans objet;null;null;null;sans objet;sans objet;<br/>
 * 8;Route;route;17;23;7;8;Route;route;2;route;route;route;metier;<br/>
 * ......................................................................<br/>
 * <br/>
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
 * @since 6 juil. 2014
 *
 */
public class ImportateurDescriptionMapping extends
		AbstractImportateurDescription {

	// ************************ATTRIBUTS************************************/

	/* CONSTANTES. */
	/**
	 * "CLASSE ImportateurDescriptionMapping".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONMAPPING 
		= "CLASSE ImportateurDescriptionMapping";
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * Séparateur point virgule pour les CSV.<br/>
	 * ";"
	 */
	public static final String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
		
	/**
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne généré par les éditeurs Unix.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).
	 */
	public static final String SAUTDELIGNE_UNIX = "\n";

	
	/**
	 * Saut de ligne généré par les éditeurs Mac.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR))
	 */
	public static final String SAUTDELIGNE_MAC = "\r";
	
	/**
	 * Saut de ligne généré par les éditeurs DOS/Windows.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne Line Feed LF).
	 */
	public static final String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionMapping.class);

	
	// *************************METHODES************************************/
	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>passe automatiquement la <b>description de fichier du MAPPING</b>
	 * <code><b>ConfigurationDescriptionsFichiersManager.getFichierDescriptionMapping()</b></code> 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>délègue à un 
	 * <code><b>ConfigurationDescriptionsFichiersManager</b></code> 
	 * le soin de fournir la bonne description de fichier.</li>
	 * <li>passe automatiquement une <b>description de champ MAPPING</b>
	 * <code><b>DescriptionChampMapping</b></code> à 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * <li>alimente <code><b>this.logImportDescription</b></code> 
	 * avec la valeur contenue dans 
	 * <code>ressources_externes/messages_techniques.properties</code> 
	 * pour savoir si il faut logger les rapports d'import.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionMapping() throws Exception {
		
		this(ConfigurationDescriptionsFichiersManager.getFichierDescriptionMapping());
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <ul>
	 * <li>passe pDescriptionDuFichierFile 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>passe automatiquement une <b>description de champ MAPPING</b>
	 * <code><b>DescriptionChampMapping</b></code> à 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * <li>alimente <code><b>this.logImportDescription</b></code> 
	 * avec la valeur contenue dans 
	 * <code>ressources_externes/messages_techniques.properties</code> 
	 * pour savoir si il faut logger les rapports d'import.</li>
	 * </ul>
	 *
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionMapping(
			final File pDescriptionDuFichierFile) throws Exception {
		
		super(new DescriptionChampMapping(), pDescriptionDuFichierFile);
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR ARCHICOMPLET._________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescriptionUtf8() 
					throws Exception {
		
		return this.importerDescription(
				null, StandardCharsets.UTF_8);
		
	} // Fin de importerDescriptionUtf8(...).______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescriptionUtf8(
			final File pFileDescription) 
					throws FichierNullException
					, TableauNullException
					, TableauVideException
					, ExceptionImport
					, IOException {
		
		return this.importerDescription(
				pFileDescription, StandardCharsets.UTF_8);
		
	} // Fin de importerDescriptionUtf8(...).______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescriptionLatin9(
			final File pFileDescription) 
					throws FichierNullException
					, TableauNullException
					, TableauVideException
					, ExceptionImport
					, IOException {
		
		return this.importerDescription(
				pFileDescription, Charset.forName("ISO-8859-15"));
		
	} // Fin de importerDescriptionLatin9(...);____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescription(
			final File pFileDescription
				, final Charset pCharset)
			throws FichierNullException
			, TableauNullException
			, TableauVideException
			, ExceptionImport
			, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/**
	 * Vérifie que les colonnes dans la description sont jointives.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * les colonnes ne sont pas jointives.<br/>
	 * <br/>
	 * ne fait rien si pDesc est null.<br/>
	 * <br/>
	 *
	 * @param pCompteurDeLigne : int : 
	 * numéro de ligne lue dans la description.<br/>
	 * @param pDesc : IDescriptionChamp : 
	 * "Ligne" de la description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	private void controlerColonnesJointives(
			final int pCompteurDeLigne
				, final IDescriptionChamp pDesc) 
								throws Exception {
		
		/* ne fait rien si pDesc est null. */
		if (pDesc == null) {
			return;
		}
		
		if (pCompteurDeLigne > 1) {
			
			/* Cast en DescriptionChampHistoF07. */
			final DescriptionChampMapping desc 
				= (DescriptionChampMapping) pDesc;
			
			/* COLONNES JOINTIVES. ****/
			/* Récupération de la colonne de début du champ en cours. */
			final Integer debutChampEnCours 
				= desc.getColonneDebut();
			
			/* Récupération de la description précédente. */
			final DescriptionChampMapping descPrecedent 
			= (DescriptionChampMapping) 
					this.specificationChampsMap.get(pCompteurDeLigne -1);
			
	
			/* Récupération de l'ordre du champ précédent. */
			if (descPrecedent != null) {
				
				/* Récupération de la colonne de fin du champ précédent
				 * sous forme de String. */
				final Integer finChampPrecedent 
					= descPrecedent.getColonneFin();
								
				/* Si les colonnes ne sont pas jointives. */
				if (!debutChampEnCours.equals(finChampPrecedent + 1)) {
					
					final String clePasJointif 
					= "importateurdescriptionf07" 
					+ ".liredescription.colonnespasjointif";
	
					final String messagePasJointif
					= ConfigurationApplicationManager
						.getBundleMessagesTechnique()
							.getString(clePasJointif);
	
					final String message 
					= "Ligne " 
					+ pCompteurDeLigne 
					+ SEPARATEUR_MOINS_AERE 
					+ desc.getIntitule() 
					+ SEPARATEUR_MOINS_AERE 
					+ CLASSE_IMPORTATEURDESCRIPTIONMAPPING
					+ METHODE_IMPORTERDESCRIPTION
					+ messagePasJointif
					+ debutChampEnCours
					+ " alors que la dernière colonne du champ précédent est : "
					+ finChampPrecedent;
	
					/* Logge. */
					if (LOG.isFatalEnabled()) {
						LOG.fatal(message);
					}
					
					/* Rapport éventuel. */
					if (this.logImportDescription) {
						this.rapportImportDescriptionStb.append(message);
					}
					
					/* Jette une Exception circonstanciée. */
					throw new ExceptionImport(message);
					
				} // Fin de colonnes pas jointives.__________________
			}			
		}
	} // Fin de controlerColonnesJointives(
	 // int pCompteurDeLigne
	 // , IDescriptionChamp pDesc).________________________________________
	
	

	/**
	 * {@inheritDoc}
	 * "importateurdescriptionmapping.niveau.anomalie".<br/>
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importateurdescriptionmapping.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________

	
	
	/**
	 * {@inheritDoc}
	 * "importateurdescriptionmapping.log.erreur".<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importateurdescriptionmapping.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final String getNomClasse() {
		return CLASSE_IMPORTATEURDESCRIPTIONMAPPING;
	} // Fin de getNomClasse().____________________________________________



	/**
	 * {@inheritDoc}
	 * "importateurdescriptionmapping.liredescription.mauvaisnomchamp"
	 */
	@Override
	protected final String getCleMauvaisNomChamp() {
		return "importateurdescriptionmapping.liredescription.mauvaisnomchamp";
	} // Fin de getCleMauvaisNomChamp().___________________________________



	/**
	 * {@inheritDoc}
	 * "importateurdescriptionmapping.liredescription.pasjointif"
	 */
	@Override
	protected final String getClePasJointif() {
		return "importateurdescriptionmapping.liredescription.pasjointif";
	} // Fin de getClePasJointif().________________________________________



	/**
	 * {@inheritDoc}
	 * "importateurdescriptionmapping.liredescription.pascsv"
	 */
	@Override
	protected final String getClePasCsv() {
		return "importateurdescriptionmapping.liredescription.pascsv";
	} // Fin de getClePasCsv().____________________________________________

	
	
	/**
	 * "Description des champs d'un fichier de Mapping HIT-HISTO_F07-DARWIN".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getLabelDescriptionFichier() {
		return "Description des champs d'un fichier de Mapping HIT-HISTO_F07-DARWIN";
	} // Fin de getNomDescriptionChamp().__________________________________
	

	
} // FIN DE LA CLASSE ImportateurDescriptionMapping.-------------------------
