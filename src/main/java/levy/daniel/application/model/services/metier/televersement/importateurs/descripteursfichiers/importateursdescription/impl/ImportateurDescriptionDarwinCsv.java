package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions.ConfigurationDescriptionsFichiersManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampDarwinCsv;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescription;

/**
 * CLASSE ImportateurDescriptionDarwinCsv :
 * <p>
 * Importateur concret pour les DESCRIPTIONS EN CSV des DARWIN_CSV.<br/>
 * </p>
 * 
 * <p>
 * Chargé de lire une description de fichier DARWIN_CSV 
 * au format csv avec séparateur ';'
 * et de la rendre disponible pour toute l'application 
 * via une SortedMap&lt;Integer,IDescriptionChamp&gt; 
 * <code><b>this.specificationChampsMap</b></code>.<br/>
 * </p>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * La description d'un fichier DARWIN_CSV commence par :
 * </span></b>
 * </p>
 * <p>
 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;<br/>
 * 1;Identifiant de la section;Identifiant de la section;objetId;Integer;false;false;<br/>
 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;false;false;<br/>
 * 3;Département du PR Origine;Département du PR Origine ('début');depPrd;String;false;false;<br/>
 * 4;Code Concession du PR Origine;Code Concession du PR Origine ('début');concessionPrd;String;false;true;<br/>
 * 5;PR Origine;PR Origine ('début');prd;Integer;false;false;<br/>
 * .......................................................<br/>
 * </p>
 * 
 * <p>
 * <table border="1">
 * <tr>
 * <th>ordreChamps</th> <th>intitule</th> <th>nomenclature</th> <th>champJava</th> 
 * <th>typeJava</th> <th>aNomenclature</th> <th>aLexique</th> 
 * </tr>
 * <tr>
 * <td>1</td> <td>Identifiant de la section</td> <td>Identifiant de la section</td> <td>objetId</td> 
 * <td>Integer</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>2</td> <td>route</td> <td>Route au format Isidor (ex : A0034b1 ou A0006)</td> <td>route</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>3</td> <td>Département du PR Origine</td> <td>Département du PR Origine ('début')</td> <td>depPrd</td> 
 * <td>String</td> <td>false</td> <td>false</td> 
 * </tr>
 * <tr>
 * <td>4</td> <td>Code Concession du PR Origine</td> <td>Code Concession du PR Origine ('début')</td> <td>concessionPrd</td> 
 * <td>String</td> <td>false</td> <td>true</td> 
 * </tr>
 * <tr>
 * <td>5</td> <td>PR Origine</td> <td>PR Origine ('début')</td> <td>prd</td> 
 * <td>Integer</td> <td>false</td> <td>false</td> 
 * </tr>
 * </table>
 * </p>
 * 
 *   
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ImportateurDescription : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/importateursdescription/diagramme_de_classes_ImportateurDescription_1.png" 
 * alt="Diagramme de classe du ImportateurDescription" />
 * <img src="../../../../../../../../../../../../../../javadoc/images/model/services/metier/televersement/importateurs/descripteursfichiers/importateursdescription/diagramme_de_classes_ImportateurDescription_2.png" 
 * alt="Diagramme de classe du ImportateurDescription" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // instanciation d'un ImportateurDescription.</code><br/>
 * <code><b>IImportateurDescription importateur = new ImportateurDescriptionDarwinCsv();</b></code><br/>
 * <code>// IMPORT de la bonne description de fichier encodée en UTF-8 (contenue sous classpath/resources).</code><br/>
 * <code><b>importateur.importerDescriptionUtf8();</b></code><br/>
 * <code> // Retourne l'en-tête de la 2ème colonne d'un fichier de description du DARWIN_CSV ("intitule")</code><br/>
 * <code><b>String fournirEnteteparColonne2 = importateur.fournirEnteteparColonne(2);</b></code><br/>
 * <code> // Retourne la ligne d'en-tête CSV du fichier de description du DARWIN_CSV ("ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;")</code><br/>
 * <code><b>String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();</b></code><br/>
 * <code> // retourne la description du 1er champ de la description du DARWIN_CSV ("1;identifiant de la section;identifiant de la section;objetId;Integer;false;false;")</code><br/>
 * <code><b>String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);</b></code><br/>
 * <code> // retourne la valeur de la 28ème ligne et de la 6ème colonne de la description du fichier DARWIN_CSV ("false")</code><br/>
 * <code><b>String valeurl28c6 = importateur.fournirValeurparLigneColonne(28, 6);</b></code><br/>
 * <code> // génère en UTF-8 la description de fichier précédemment importée dans le fichier descriptionGeneree</code><br/>
 * <code><b>Path decriptionGenereePath = PATH_ABSOLU_REPERTOIRE_TEMP.resolve("description_DARWIN_CSV_generee_UTF-8.csv");</b></code></br>
 * <code><b>File descriptionGeneree = decriptionGenereePath.toFile();</b></code></br>
 * <code><b>importateur.genererDescriptionCsvFileUtf8(descriptionGeneree);</b></code></br>
 * <code> // Retourne la description précédemment importée et regénérée sous forme de String.</code><br/>
 * <code><b>String resultat = importateur.genererDescriptionCsvString(true);</b></code></br>
 * <code> // Retourne la IDescriptionChamp correspondant au 2ème champ de la description du DARWIN_CSV (route)</code><br/>
 * <code><b>IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);</b></code></br>
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
	 * "Classe ImportateurDescriptionDarwinCsv".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV 
	= "Classe ImportateurDescriptionDarwinCsv";
	
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
	// définis dans IConstantesSautsLigne
	
		
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionDarwinCsv.class);

	// *************************METHODES************************************/
	
	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>passe automatiquement la <b>description de fichier du DARWIN_CSV</b>
	 * <code><b>ConfigurationDescriptionsFichiersManager.getFichierDescriptionDarwinCsv()</b></code> 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>délègue à un 
	 * <code><b>ConfigurationDescriptionsFichiersManager</b></code> 
	 * le soin de fournir la bonne description de fichier.</li>
	 * <li>passe automatiquement une <b>description de champ DARWIN_CSV</b>
	 * <code><b>DescriptionChampDarwinCsv</b></code> à 
	 * <code><b>this.descriptionChamp</b></code>.</li>
	 * <li>alimente <code><b>this.logImportDescription</b></code> 
	 * avec la valeur contenue dans 
	 * <code>ressources_externes/messages_techniques.properties</code> 
	 * pour savoir si il faut logger les rapports d'import.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public ImportateurDescriptionDarwinCsv() throws Exception {
		
		this(ConfigurationDescriptionsFichiersManager.getFichierDescriptionDarwinCsv());
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <ul>
	 * <li>passe pDescriptionDuFichierFile 
	 * à <code><b>this.descriptionDuFichierFile</b></code> de la classe.</li>
	 * <li>passe automatiquement une <b>description de champ DARWIN_CSV</b>
	 * <code><b>DescriptionChampDarwinCsv</b></code> à 
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
	public ImportateurDescriptionDarwinCsv(
			final File pDescriptionDuFichierFile) throws Exception {
		
		super(new DescriptionChampDarwinCsv(), pDescriptionDuFichierFile);
		
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
		
	} // Fin de importerDescriptionUtf8()._________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescriptionUtf8(
			final File pFileDescription) 
					throws Exception {
		
		return this.importerDescription(
				pFileDescription, StandardCharsets.UTF_8);
		
	} // Fin de importerDescriptionUtf8(...).______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescriptionLatin9(
			final File pFileDescription) 
					throws Exception {
		
		return this.importerDescription(
				pFileDescription, Charset.forName("ISO-8859-15"));
		
	} // Fin de importerDescriptionLatin9(...);____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedMap<Integer, IDescriptionChamp> importerDescription(
			final File pFileDescription
				, final Charset pCharset)
						throws Exception {
		
		File fileDescription = null;
		
		/* DETERMINATION DU FICHIER DE DESCRIPTION A LIRE. ************/
		/* utilise automatiquement la description this.descriptionDuFichierFile 
		 * si pFileDescription est null ou ne convient pas. */
		if (pFileDescription == null 
				|| pFileDescription.length() == 0 
					|| !pFileDescription.exists() 
						|| !pFileDescription.isFile()) {
			
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
		
		/* véfifie que le fichier de description possède l'extension CSV. */
		this.traiterFichierNonCsv();
				
		// **************PARAMETRES VALIDES****************************/		

		/* choisit automatiquement le Charset UTF-8 si pCharset == null 
		 * ou pCharset ne peut pas encoder. */
		Charset charset = null;
		
		if (pCharset == null || !pCharset.canEncode()) {
			charset = StandardCharsets.UTF_8;
		} else {
			charset = pCharset;
		}
		
		/* Instanciation du tableau des longueurs maxi. */
		this.instancierTableauLongueursMaxi();
		
		/* INSTANCIATION de la SortedMap specificationChampsMap. */
		this.specificationChampsMap = new TreeMap<Integer, IDescriptionChamp>();
		
		int compteurDeLigne = 0;
		
		/* LECTURE DE fileDescription et 
		 * injection dans la SortedMap this.specificationChampsMap. */
		
		/* Instancie un Pattern chargé de retrouver le 
		 * séparateur ';' dans la ligne. */
		final Pattern patternCsv = Pattern.compile(SEP_PV);
		
		/* OUVERTURE DES FLUX. */
		final FileInputStream fis = new FileInputStream(fileDescription);   
		final InputStreamReader isr = new InputStreamReader(fis, charset);   
		final BufferedReader bfr = new BufferedReader(isr);   
		
		String ligneLue = null;
		
		/* LECTURE DE CHAQUE LIGNE DE LA DESCRIPTION. */
		while ((ligneLue = bfr.readLine()) != null) {
			
			/* Incrémentation du compteur. */
			compteurDeLigne++;
			
			if (compteurDeLigne == 1) {
				
				/* retire un éventuel caractère BOM_UTF_8 à la 
				 * première ligne si charset == UTF-8. */
				final String bomUtf8 = "\uFEFF";
				
				if (charset.equals(StandardCharsets.UTF_8)) {
					if (StringUtils.contains(ligneLue, bomUtf8)) {
						ligneLue = StringUtils.remove(ligneLue, BOM_UTF_8);
					}
				}
				
				/* décompose la première ligne. */
				final String[] tokens 
					= patternCsv.split(ligneLue);
				
				/* saute la ligne d'en-tête le cas échéant en se basant 
				 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête 
				 * et une valeur entière pour toutes les lignes significatives. */
				final String ordreChamps = tokens[0];
				
				if (!StringUtils.isBlank(ordreChamps)) {
					try {
						Integer.parseInt(ordreChamps);
					} catch (NumberFormatException e) {
						continue;
					}
				}
			}
			
							
			/* Injection des valeurs de chaque champ 
			 * de la description de fichier dans un DescriptionChamp. */
			IDescriptionChamp desc = null;
			
			try {
				
				desc = this.descriptionChamp.getClass().newInstance();
				
			} catch (InstantiationException e1) {
				
				/* Fermeture des flux. */
				bfr.close();
				isr.close();
				fis.close();
				
				throw new RuntimeException(e1);
				
			} catch (IllegalAccessException e1) {
				
				/* Fermeture des flux. */
				bfr.close();
				isr.close();
				fis.close();
				
				throw new RuntimeException(e1);
			}
			
			if (desc == null) {
				
				/* Fermeture des flux. */
				bfr.close();
				isr.close();
				fis.close();
				
				return null;
			}
						
			/* Lecture de chaque ligne de la description. */
			try {
				
				/* Saute les lignes null ou vides 
				 * dans la description de fichier. */
				if (StringUtils.isBlank(ligneLue)) {
					continue;
				}
				
				/* décompose la ligne lue. */
				final String[] tokens 
					= patternCsv.split(ligneLue);
				
				/* importe les tokens lus dans la DescriptionChamp. */
				desc.lireChamp(tokens);
				
				/* Rapport d'erreur (provenant du DescriptionChamp). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
								
				/* Rapport d'erreur. */
				if (!StringUtils.isBlank(messageDescripteur)) {
					
					final String message 
					= desc.toString() 
					+ SEPARATEUR_MOINS_AERE
					+ this.getNomClasse()
					+ METHODE_IMPORTERDESCRIPTION 
					+ messageDescripteur;
					
					if (this.logImportDescription) {
						this.rapportImportDescriptionStb.append(message);
						this.rapportImportDescriptionStb.append(NEWLINE);
						
					}
					
				}
			} catch (Exception e) {
				
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				/* Rapport d'erreur. */
				if (!StringUtils.isBlank(messageDescripteur)) {
					
					final String message 
					= "MAUVAIS FICHIER DE DESCRIPTION ???" 
					+ SEPARATEUR_MOINS_AERE
					+ this.getNomClasse()
					+ METHODE_IMPORTERDESCRIPTION 
					+ messageDescripteur;
					
					/* Logge */
					if (LOG.isFatalEnabled()) {
						LOG.fatal(message, e);
					}
					
					if (this.logImportDescription) {
						this.rapportImportDescriptionStb.append(message);
						this.rapportImportDescriptionStb.append(NEWLINE);						
					}
					
					
					/* Fermeture des flux. */
					bfr.close();
					isr.close();
					fis.close();
					
					/* Jette une Exception circonstanciée. */
					throw new ExceptionImport(message, e);
					
				}
				
			}
						
			/* Gestion des longueurs maxi. */			
			this.gererLongueursMaxi(desc);
			
			/* CONTROLES. */
			/* Contrôle de l'unicité des noms Java. */
			this.controlerUniciteNomJava(desc);
			
			/* controle que l'ordre des champs est jointif */
			this.controlerJointif(compteurDeLigne - 1, desc);
			
			/* AJOUT DE LA DESCRIPTION A LA MAP triée 
			 * this.specificationChampsMap. */
			this.specificationChampsMap.put(compteurDeLigne - 1, desc);
		}
		
		/* FERMETURE DES FLUX. */
		bfr.close();
		isr.close();
		fis.close();
		
		return this.specificationChampsMap;		

	} // Fin de importerDescription(...).__________________________________

	

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
	 * {@inheritDoc}
	 */
	@Override
	protected final String getNomClasse() {
		return CLASSE_IMPORTATEURDESCRIPTIONDARWINCSV;
	} // Fin de getNomClasse().____________________________________________



	/**
	 * {@inheritDoc}
	 * "importateurdescriptiondarwincsv.liredescription.mauvaisnomchamp"
	 */
	@Override
	protected final String getCleMauvaisNomChamp() {
		return "importateurdescriptiondarwincsv.liredescription.mauvaisnomchamp";
	} // Fin de getCleMauvaisNomChamp().___________________________________



	/**
	 * {@inheritDoc}
	 * "importateurdescriptiondarwincsv.liredescription.pasjointif"
	 */
	@Override
	protected final String getClePasJointif() {
		return "importateurdescriptiondarwincsv.liredescription.pasjointif";
	} // Fin de getClePasJointif().________________________________________



	/**
	 * {@inheritDoc}
	 * "importateurdescriptiondarwincsv.liredescription.pascsv"
	 */
	@Override
	protected final String getClePasCsv() {
		return "importateurdescriptiondarwincsv.liredescription.pascsv";
	} // Fin de getClePasCsv().____________________________________________


	
	/**
	 * "Description des champs d'un fichier DARWIN_CSV".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getLabelDescriptionFichier() {
		return "Description des champs d'un fichier DARWIN_CSV";
	} // Fin de getNomDescriptionChamp().__________________________________
	


} // Fin de ImportateurDescriptionDarwinCsv.---------------------------------
