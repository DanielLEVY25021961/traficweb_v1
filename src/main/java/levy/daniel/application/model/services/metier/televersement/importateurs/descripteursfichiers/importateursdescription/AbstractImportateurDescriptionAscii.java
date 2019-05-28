package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

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
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.AbstractDescriptionChampAscii;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

/**
 * CLASSE AbstractImportateurDescriptionAscii :<br/>
 * <p>
 * Classe abstraite factorisant les attributs et méthodes 
 * des ImportateurDescription des FICHIERS ASCII 
 * (HIT, HISTO_F07, HISTO_F08).<br/>
 * ETEND {@link AbstractImportateurDescription}.
 * </p>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // instanciation d'un ImportateurDescription.</code><br/>
 * <code><b>IImportateurDescription importateur = new ImportateurDescriptionHit();</b></code><br/>
 * <code>// IMPORT de la bonne description de fichier encodée en UTF-8 (contenue sous classpath/resources).</code><br/>
 * <code><b>importateur.importerDescriptionUtf8();</b></code><br/>
 * <code> // Retourne l'en-tête de la 2ème colonne d'un HIT ("colonnes")</code><br/>
 * <code><b>String fournirEnteteparColonne2 = importateur.fournirEnteteparColonne(2);</b></code><br/>
 * <code> // Retourne la ligne d'en-tête CSV du HIT ("ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;")</code><br/>
 * <code><b>String ligneEnTetesCsv = importateur.fournirLigneEnTetesCsv();</b></code><br/>
 * <code> // retourne la description du 1er champ de la description du HIT ("1;1-3;3;Numéro de Département;Exactement 3 Chiffres. Complété par un 0 à droite si dep < 3 chiffres et complété par un 0 à gauche si dep < 2 chiffres. Exemples : (Ain) 1 = 010, (Allier) 3 = 030, (Bouches-du-Rhône) 13 = 130, (Dordogne) 24 = 240, (Guadeloupe) 971 = 971;numDepartement;Integer;false;false;1;3;3;")</code><br/>
 * <code><b>String ligneValeursCsv1 = importateur.fournirLigneValeursCsv(1);</b></code><br/>
 * <code> // retourne la valeur de la 28ème ligne et de la 6ème colonne de la description du fichier HIT ("longueurRaseCampagne")</code><br/>
 * <code><b>String valeurl28c6 = importateur.fournirValeurparLigneColonne(28, 6);</b></code><br/>
 * <code> // génère en UTF-8 la description de fichier précédemment importée dans le fichier descriptionGeneree</code><br/>
 * <code><b>Path decriptionGenereePath = PATH_ABSOLU_REPERTOIRE_TEMP.resolve("description_HIT_generee_UTF-8.csv");</b></code></br>
 * <code><b>File descriptionGeneree = decriptionGenereePath.toFile();</b></code></br>
 * <code><b>importateur.genererDescriptionCsvFileUtf8(descriptionGeneree);</b></code></br>
 * <code> // Retourne la description précédemment importée et regénérée sous forme de String.</code><br/>
 * <code><b>String resultat = importateur.genererDescriptionCsvString(true);</b></code></br>
 * <code> // Retourne la IDescriptionChamp correspondant au 2ème champ de la description du HIT (numéro de section)</code><br/>
 * <code><b>IDescriptionChamp descriptionChamp2 = importateur.getDescriptionChamp(2);</b></code></br>
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
 * @since 19 juil. 2014
 *
 */
public abstract class AbstractImportateurDescriptionAscii extends
		AbstractImportateurDescription {

	// ************************ATTRIBUTS************************************/
	
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
	
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(AbstractImportateurDescriptionAscii.class);

	
	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractImportateurDescriptionAscii() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractImportateurDescriptionAscii() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractImportateurDescriptionAscii() :<br/>
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <br/>
	 *
	 * @param pDescriptionChamp : IDescriptionChamp.<br/>
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 */
	public AbstractImportateurDescriptionAscii(
			final IDescriptionChamp pDescriptionChamp
				, final File pDescriptionDuFichierFile) {
		
		super();
		this.descriptionChamp = pDescriptionChamp;
		this.descriptionDuFichierFile = pDescriptionDuFichierFile;
		
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
		
	} // Fin de importerDescriptionUtf8(
	 // File pFileDescription).____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, IDescriptionChamp> importerDescriptionLatin9(
			final File pFileDescription) 
					throws Exception {
		
		return this.importerDescription(
				pFileDescription, Charset.forName("ISO-8859-15"));
		
	} // Fin de importerDescriptionLatin9(
	 // File pFileDescription).____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
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
			
			/* Contrôle de la longueur fournie. */
			this.controlerLongueur(desc);
			
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
		
	} // Fin de importerDescription(
	// File pFileDescription
	// , Charset pCharset).________________________________________________
	
	
	
	/**
	 * Vérifie que la longueur fournie pour un champ 
	 * de la description de fichier est égale à la longueur calculée.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte lorsqu'une longueur founie 
	 * diffère de la Longueur calculée dans la description.<br/>
	 * <br/>
	 * ne fait rien si pDesc est null.<br/>
	 * <br/>
	 *
	 * @param pDesc : IDescriptionChamp : 
	 * "Ligne" de la description du fichier.<br/>
	 * 
	 * @throws Exception 
	 */
	private void controlerLongueur(
			final IDescriptionChamp pDesc) throws Exception {
		
		/* ne fait rien si pDesc est null. */
		if (pDesc == null) {
			return;
		}
		
		/* Cast en DescriptionChampHistoF07. */
		final AbstractDescriptionChampAscii desc 
			= (AbstractDescriptionChampAscii) pDesc;
		
		/* Contrôle de la LONGUEUR. */
		/* Longueur fournie String. */
		final Integer longueurFournie = desc.getLongueur();
		
		/* Longueur calculée. */
		final Integer longueurCalculee = desc.getLongueurCalculee();
				
		/* Prévient si les longueurs calculées
		 * et fournies diffèrent. */
		if (!longueurCalculee.equals(longueurFournie)) {
			
			final String cleBadLongueur 
				= this.getCleBadLongueur();

			final String messageBadLongueur
			= ConfigurationApplicationManager
				.getBundleMessagesTechnique()
					.getString(cleBadLongueur);

			final String message 
			= desc.toString() 
			+ SEPARATEUR_MOINS_AERE 
			+ this.getNomClasse()
			+ METHODE_IMPORTERDESCRIPTION
			+ messageBadLongueur
			+ longueurCalculee
			+ " alors que la longueur fournie est : "
			+ longueurFournie;

			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport éventuel. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
			}
			
		} // Fin du contrôle de longueur.________________________
		
	} // Fin de controlerLongueur(
	 // IDescriptionChamp pDesc).__________________________________________
	
		

	
	/**
	 * Vérifie que les colonnes dans la 
	 * description de fichier sont jointives.<br/>
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
			
			/* Cast en AbstractDescriptionChampAscii. */
			final AbstractDescriptionChampAscii desc 
				= (AbstractDescriptionChampAscii) pDesc;
			
			/* COLONNES JOINTIVES. ****/
			/* Récupération de la colonne de début du champ en cours. */
			final Integer debutChampEnCours 
				= desc.getColonneDebut();
			
			/* Récupération de la description précédente. */
			final AbstractDescriptionChampAscii descPrecedent 
			= (AbstractDescriptionChampAscii) 
					this.specificationChampsMap.get(pCompteurDeLigne -1);
			

			/* Récupération de l'ordre du champ précédent. */
			if (descPrecedent != null) {
				
				/* Récupération de la colonne de fin du champ précédent
				 * sous forme de String. */
				final Integer finChampPrecedent 
					= descPrecedent.getColonneFin();
								
				/* Si les colonnes ne sont pas jointives. */
				if (!debutChampEnCours.equals(finChampPrecedent + 1)) {
					
					final String cleColonnesPasJointives 
						= this.getCleColonnesPasJointives();

					final String messagePasJointif
					= ConfigurationApplicationManager
						.getBundleMessagesTechnique()
							.getString(cleColonnesPasJointives);

					final String message 
					= "Ligne " 
					+ pCompteurDeLigne 
					+ SEPARATEUR_MOINS_AERE 
					+ desc.getIntitule() 
					+ SEPARATEUR_MOINS_AERE 
					+ this.getNomClasse()
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
	 * method getCleBadLongueur() :<br/>
	 * Retourne la clé comprise dans messagestechniques.properties 
	 * en cas mauvaise longueur d'un champ.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String getCleBadLongueur();
	

		
	/**
	 * method getCleColonnesPasJointives() :<br/>
	 * Retourne la clé comprise dans messagestechniques.properties 
	 * en cas de colonnes non jointives dans une description.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String getCleColonnesPasJointives();
	
	
	
} // FIN DE LA CLASSE AbstractImportateurDescriptionAscii.-------------------
