package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampDarwinCsv;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescription;

/**
 * class ImportateurDescriptionDarwinCsv :<br/>
 * Importateur concret pour les DESCRIPTIONS EN CSV des Darwin.csv.<br/>
 * Chargé de lire une description de fichier Darwin.csv 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * specificationChampsMap.<br/>
 * <br/>
 * La description d'un Darwin.csv commence par :<br/>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine  ('début');depPrd;String;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;<br/>
 * 5;PR Origine;PR Origine  ('début');prd;Integer;false;<br/>
 * .......................................................<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 * <code>
 * \\ Instanciation d'un Importateur de description.<br/>
 * final ImportateurDescriptionDarwinCsv impoDesc 
 * = new ImportateurDescriptionDarwinCsv();<br/>
 * <br/>
 * try {<br/>
 * <br/>
 * \\<i>Fournit la description sous forme de 
 * SortedMap&lt;Integer,IDescriptionChamp&gt; specificationChampsMap.</i><br/>
 * final SortedMap&lt;Integer,IDescriptionChamp&gt; specificationChampsMap 
 * = impoDesc.importerDescription(DESCRIPTION Darwin.csv en csv);<br/>
 * <br/>
 * } catch (Exception e) {<br/>
 * 			System.out.println("RAPPORT : \n" <br/>
 * 				+ impoDesc.getRapportImportDescriptionStb().toString());<br/>
 * }<br/></code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Pattern.split(";"), Décomposition d'une ligne csv, Regex<br/>
 * , Expression régulière,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 2 juil. 2014
 *
 */
public class ImportateurDescriptionDarwinCsv extends
		AbstractImportateurDescription {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV : String :<br/>
	 * "Classe ImportateurDescriptionDarwinCsv - ".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
	= "Classe ImportateurDescriptionDarwinCsv - ";
	
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
			.getLog(ImportateurDescriptionDarwinCsv.class);

	// *************************METHODES************************************/
	
	
	/**
	 * method CONSTRUCTEUR ImportateurDescriptionDarwinCsv() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionDarwinCsv() throws Exception {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampDarwinCsv();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * method CONSTRUCTEUR ImportateurDescriptionDarwinCsv(
	 * File pDescriptionDuFichierFile) :<br/>
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <br/>
	 *
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionDarwinCsv(
			final File pDescriptionDuFichierFile) throws Exception {
		
		super(new DescriptionChampDarwinCsv(), pDescriptionDuFichierFile);
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR ARCHICOMPLET._________________________________
	

	
	
	/**
	 * method determinerSiLogErreurs() :<br/>
	 * - Va chercher dans messages_techniques.properties 
	 * si il faut créer des rapports d'erreur d'import des descriptions.<br/>
	 * - Instancie le cas échéant le rapport d'erreur.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private void determinerSiLogErreurs() throws Exception {
		
		final String cleLogImport = this.recupererCleLogErreur();

		final String logImportString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechnique()
				.getString(cleLogImport);
		
		if (StringUtils.containsIgnoreCase(logImportString, "true")) {
			this.logImportDescription = true;
		}
		else {
			this.logImportDescription = false;
		}
		
		/* Instanciation du rapportImportDescription
		 * si logImportDescription == true. */
		if (this.logImportDescription) {
			this.rapportImportDescriptionStb = new StringBuffer();
		}
		
	} // Fin de determinerSiLogErreurs().__________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescription(
			final File pFileDescription) 
					throws Exception {
		
		File fileDescription = null;
		
		/* DETERMINATION DU FICHIER DE DESCRIPTION A LIRE. ************/
		if (pFileDescription == null 
				|| !pFileDescription.exists()) {
			
			/* Si this.descriptionDuFichierFile est aussi absent. */
			/* LOG.fatal, rapporte et jette une FichierNullException. */
			this.traiterDescriptionNull();
			
			/* sinon : */
			fileDescription = this.descriptionDuFichierFile;
		}
		else {
			
			fileDescription = pFileDescription;
			
			/* Passage du paramètre aux attributs. */
			this.descriptionDuFichierFile = fileDescription;
			
		} /* FIN DETERMINATION DU FICHIER DE DESCRIPTION A LIRE. ********/

		
		/* Logge, rapporte et jette une ExceptionImport 
		 * si this.descriptionDuFichierFile n'a pas l'extension csv. */
		this.traiterFichierNonCsv();
		
		
		
		// **************PARAMETRES VALIDES****************************/		
			
		/* Instanciation du tableau des longueurs maxi. */
		this.instancierTableauLongueursMaxi();
		
		/* INSTANCIATION de la SortedMap specificationChampsMap. */
		this.specificationChampsMap = new TreeMap<Integer, IDescriptionChamp>();
		
		int compteurDeLigne = 0;
		
		/* LECTURE DE fileDescription et 
		 * injection dans la SortedMap this.specificationChampsMap. */
		
		/* Ouverture des flux. */
		final FileReader fr = new FileReader(fileDescription);
		final BufferedReader bfr = new BufferedReader(fr);
		
		String ligneLue = null;
		
		/* LECTURE DE CHAQUE LIGNE DE LA DESCRIPTION. */
		while ((ligneLue = bfr.readLine()) != null) {
			
			/* Instancie un Pattern chargé de retrouver le 
			 * séparateur ';' dans la ligne. */
			final String[] tokens 
				= Pattern.compile(SEP_PV).split(ligneLue);
			
			/* saute la ligne d'en-tête le cas échéant en se basant 
			 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête DarwinCsv 
			 * et une valeur entière pour toutes les lignes significatives. */
			final String ordreChamps = tokens[0];
			
			if (!StringUtils.isBlank(ordreChamps)) {
				try {
					Integer.parseInt(ordreChamps);
				} catch (NumberFormatException e) {
					continue;
				}
			}
			
			
			/* Incrémentation du compteur. */
			compteurDeLigne++;
			
			/* Injection des valeurs de chaque champ 
			 * de la description de fichier dans un DescriptionChamp. */
			final IDescriptionChamp desc = new DescriptionChampDarwinCsv();
			
			/* Lecture de chaque ligne de la description. */
			try {
				
				desc.lireChamp(tokens);
								
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				final String message 
				= desc.toString() 
				+ SEPARATEUR_MOINS_AERE
				+ CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
				+ METHODE_IMPORTERDESCRIPTION 
				+ messageDescripteur;
												
				/* Rapport d'erreur. */
				if (!StringUtils.isBlank(messageDescripteur)) {
										
					if (this.logImportDescription) {
						this.rapportImportDescriptionStb.append(message);
						this.rapportImportDescriptionStb.append(NEWLINE);
						
					}
					
				}
			} catch (Exception e) {
				
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				final String message 
				= "MAUVAIS FICHIER DE DESCRIPTION ???" 
				+ SEPARATEUR_MOINS_AERE
				+ CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
				+ METHODE_IMPORTERDESCRIPTION 
				+ messageDescripteur;
								
				/* Logge */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e);
				}
				
				/* Rapport d'erreur. */
				if (!StringUtils.isBlank(messageDescripteur)) {
										
					if (this.logImportDescription) {
						this.rapportImportDescriptionStb.append(message);
						this.rapportImportDescriptionStb.append(NEWLINE);
						
					}
					
				}
				
				/* Fermeture des flux; */
				fr.close();
				bfr.close();
				
				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(message, e);
			}
						
			/* Gestion des longueurs maxi. */			
			this.gererLongueursMaxi(desc);
			
			/* CONTROLES. */
			/* Contrôle de l'unicité des noms Java. */
			this.controlerUniciteNomJava(desc);
			
			/* controle que l'ordre des champs est jointif */
			this.controlerJointif(compteurDeLigne, desc);
			
			/* AJOUT DE LA DESCRIPTION A LA MAP triée 
			 * this.specificationChampsMap. */
			this.specificationChampsMap.put(compteurDeLigne, desc);
		}
		
		/* FERMETURE DES FLUX. */
		fr.close();
		bfr.close();
		
		return this.specificationChampsMap;
		
	} // Fin de importerDescription(
	// File pFileDescription)._____________________________________________

	
	
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
		// TODO Auto-generated method stub
		return null;
	}



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
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<Integer, IDescriptionChamp> importerDescription(
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
	 * method traiterDescriptionNull() :<br/>
	 * LOG.fatal, rapporte et jette une FichierNullException 
	 * si pFile et this.descriptionDuFichierFile 
	 * sont null ou inexistants.<br/>
	 * <br/>
	 * 
	 * @throws FichierNullException  : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 */
	private void traiterDescriptionNull() throws FichierNullException {
		
		if (this.descriptionDuFichierFile == null 
				|| !this.descriptionDuFichierFile.exists()) {
			
			final String message 
			= CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV
			+ METHODE_IMPORTERDESCRIPTION 
			+ "Le fichier de description à importer est null ou inexistant";
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new FichierNullException(message);
			
		} // Fin de pFile et descriptionDuFichierFile absents.______
		
	} // Fin de traiterDescriptionNull().__________________________________
	

	
	/**
	 * method traiterFichierNonCsv() :<br/>
	 * LOG.fatal, rapporte et jette une ExceptionImport 
	 * si la description de fichier n'a pas l'extension csv.<br/>
	 * <br/>
	 * 
	 * @throws Exception 
	 */
	private void traiterFichierNonCsv() throws Exception {
		
		/* Fichier lu non csv. */
		if (!this.descriptionDuFichierFile.getPath().endsWith("csv")) {
			
			final String clePasCSV 
			= "importateurdescriptiondarwincsv.liredescription.pascsv";

			final String messagePasCsv
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(clePasCSV);

			final String message 
			= CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
			+ METHODE_IMPORTERDESCRIPTION
			+ messagePasCsv
			+ this.descriptionDuFichierFile.getAbsolutePath();

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(NEWLINE);
			}
			
			/* Jette une Exception circonstanciée. */
			throw new ExceptionImport(message);
			
		} // Fin de if (!pDescription.getPath().endsWith("csv")).________
		
	} // Fin de traiterFichierNonCsv().____________________________________
	
	
	
	/**
	 * method controlerUniciteNomJava(
	 * IDescriptionChamp pDesc) :<br/>
	 * Contrôle l'unicité des noms de champ java dans la description.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * un nom de champ java 
	 * existe en doublon dans la description.<br/>
	 * <br/>
	 * ne fait rien si pDesc est null.<br/>
	 * <br/>
	 *
	 * @param pDesc : IDescriptionChamp : 
	 * "Ligne" de la description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	private void controlerUniciteNomJava(
			final IDescriptionChamp pDesc) throws Exception {
		
		/* ne fait rien si pDesc est null. */
		if (pDesc == null) {
			return;
		}
		
		/* Cast en DescriptionChampDarwinCsv. */
		final DescriptionChampDarwinCsv desc 
			= (DescriptionChampDarwinCsv) pDesc;
				
		/* Controle de l'UNICITE de nomChampJava. */		
		final String nomChampJava = desc.getChampJava();
		
		if (!(this.nomsChampsJavaSet.contains(nomChampJava))) {
			this.nomsChampsJavaSet.add(nomChampJava);
		} else {
			
			if (!(StringUtils.equalsIgnoreCase(nomChampJava, "sans objet"))) {
	
				final String cleMauvaisNomChamp 
				= "importateurdescriptionf07.liredescription.mauvaisnomchamp";
	
				final String messageMauvaisNomChamp 
				= ConfigurationApplicationManager
					.getBundleMessagesTechnique()
						.getString(cleMauvaisNomChamp);
	
				final String message 
				= desc.toString() 
				+ SEPARATEUR_MOINS_AERE
				+ CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
				+ METHODE_IMPORTERDESCRIPTION
				+ messageMauvaisNomChamp 
				+ nomChampJava;
	
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
			}				
			
		} // Fin du contrôle d'unicité des noms de champ._____________
			
	} // Fin de controlerUniciteNomJava(
	 // IDescriptionChamp pDesc).__________________________________________
	
	
	
	/**
	 * method controlerJointif(
	 * int pCompteurDeLigne
	 * , IDescriptionChamp pDesc) :<br/>
	 * Vérifie que l'ordre des champs dans la description est jointif.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * l'ordre des champs n'est pas jointif.<br/>
	 * <br/>
	 * ne fait rien si pDesc est null.<br/>
	 * <br/>
	 *
	 * @param pCompteurDeLigne : int : 
	 * numéro de ligne lue dans la description.<br/
	 * @param pDesc : IDescriptionChamp : 
	 * "Ligne" de la description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	private void controlerJointif(
			final int pCompteurDeLigne
				, final IDescriptionChamp pDesc) throws Exception {
		
		/* ne fait rien si pDesc est null. */
		if (pDesc == null) {
			return;
		}
		
		
		/* ORDRE DES CHAMPS CONTINU. ****/
		if (pCompteurDeLigne > 1) {
			
			/* Cast en DescriptionChampDarwinCsv. */
			final DescriptionChampDarwinCsv desc 
				= (DescriptionChampDarwinCsv) pDesc;
			
			/* Récupération de l'ordre du champ en cours. */
			final Integer ordreChampEnCours 
				= desc.getOrdreChamps();
					
			/* Récupération de la description précédente. */
			final DescriptionChampDarwinCsv descPrecedent 
			= (DescriptionChampDarwinCsv) 
					this.specificationChampsMap.get(pCompteurDeLigne -1);
			
	
			/* Récupération de l'ordre du champ précédent. */
			if (descPrecedent != null) {
				
				final Integer ordreChampPrecedent 
				= descPrecedent.getOrdreChamps();
				
	
				/* Si l'ordre des champs n'est pas jointif. */
				if (ordreChampEnCours != ordreChampPrecedent + 1) {
					
					final String clePasJointif 
					= "importateurdescriptionf07.liredescription.pasjointif";
	
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
					+ CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
					+ METHODE_IMPORTERDESCRIPTION
					+ messagePasJointif
					+ ordreChampEnCours
					+ " alors que l'ordre du champ précédent est : "
					+ ordreChampPrecedent;
	
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
					
				} // Fin de ordre pas jointif._______________________
			}			
		}
	} // Fin de controlerJointif(
	 // int pCompteurDeLigne
	 // , IDescriptionChamp pDesc).________________________________________
	

	/**
	 * {@inheritDoc}
	 * "importateurdescriptiondarwincsv.niveau.anomalie".<br/>
	 */
	@Override
	public final String recupererCleNiveauAnomalie() {
		return "importateurdescriptiondarwincsv.niveau.anomalie";
	} // Fin de recupererCleNiveauAnomalie().______________________________
	
	

	/**
	 * {@inheritDoc}
	 * "importateurdescriptiondarwincsv.log.erreur = true".<br/>
	 */
	@Override
	public final String recupererCleLogErreur() {
		return "importateurdescriptiondarwincsv.log.erreur";
	} // Fin de recupererCleLogErreur().___________________________________


	
	/**
	 * "Description des champs d'un fichier Darwin csv".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier Darwin csv";
	} // Fin de getNomDescriptionChamp().__________________________________
	


} // Fin de ImportateurDescriptionDarwinCsv.---------------------------------
