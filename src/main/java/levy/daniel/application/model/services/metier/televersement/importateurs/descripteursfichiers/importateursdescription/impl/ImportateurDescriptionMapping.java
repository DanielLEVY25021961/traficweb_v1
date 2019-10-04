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

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions.ConfigurationDescriptionsFichiersManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
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
	// définis dans IConstantesSautsLigne
	
	
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
	 * {@inheritDoc}<br/>
	 * contrôle que les colonnes sont jointives.<br/>
	 * <br/>
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescription(
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
			
			/* contrôle que les colonnes sont jointives. */
			this.controlerColonnesJointives(compteurDeLigne - 1, desc);
			
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
