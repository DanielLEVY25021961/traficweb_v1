package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
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
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.AbstractDescriptionChampAscii;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

/**
 * class AbstractImportateurDescriptionAscii :<br/>
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
			 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête HistonatF07 
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
			IDescriptionChamp desc = null;
			try {
				desc = this.descriptionChamp.getClass().newInstance();
			} catch (InstantiationException e1) {
				
				/* Fermeture des flux; */
				fr.close();
				bfr.close();
				
				throw new RuntimeException(e1);
				
			} catch (IllegalAccessException e1) {
				
				/* Fermeture des flux; */
				fr.close();
				bfr.close();
				
				throw new RuntimeException(e1);
			}

			
			if (desc == null) {
				
				/* Fermeture des flux; */
				fr.close();
				bfr.close();
				
				return null;
			}
			
			
			/* Lecture de chaque ligne de la description. */
			try {
				
				desc.lireChamp(tokens);
				
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				final String message 
				= desc.toString() 
				+ SEPARATEUR_MOINS_AERE
				+ this.getNomClasse()
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
				+ this.getNomClasse()
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
			
			/* Contrôle de la longueur fournie. */
			this.controlerLongueur(desc);
			
			/* controle que l'ordre des champs est jointif */
			this.controlerJointif(compteurDeLigne, desc);
			
			/* contrôle que les colonnes sont jointives. */
			this.controlerColonnesJointives(compteurDeLigne, desc);
			
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
					throws Exception {
		
		return this.importerDescription(
				pFileDescription, Charset.forName("UTF-8"));
		
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
		
		
		// **************PARAMETRES VALIDES****************************/		
		
		Charset charset = null;
		
		if (pCharset == null) {
			charset = Charset.forName("UTF-8");
		}
		else {
			charset = pCharset;
		}
		
		/* Instanciation du tableau des longueurs maxi. */
		this.instancierTableauLongueursMaxi();
		
		/* INSTANCIATION de la SortedMap specificationChampsMap. */
		this.specificationChampsMap = new TreeMap<Integer, IDescriptionChamp>();
		
		int compteurDeLigne = 0;
		
		/* LECTURE DE fileDescription et 
		 * injection dans la SortedMap this.specificationChampsMap. */
		
		/* OUVERTURE DES FLUX. */
		final FileInputStream fis = new FileInputStream(fileDescription);
		final InputStreamReader isr = new InputStreamReader(fis, charset);
		final BufferedReader bfr = new BufferedReader(isr);
		
		String ligneLue = null;
		
		/* LECTURE DE CHAQUE LIGNE DE LA DESCRIPTION. */
		while ((ligneLue = bfr.readLine()) != null) {
						
			/* Instancie un Pattern chargé de retrouver le 
			 * séparateur ';' dans la ligne. */
			final String[] tokens 
				= Pattern.compile(SEP_PV).split(ligneLue);
			
			/* saute la ligne d'en-tête le cas échéant en se basant 
			 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête HistonatF07 
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
				
				desc.lireChamp(tokens);
				
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				final String message 
				= desc.toString() 
				+ SEPARATEUR_MOINS_AERE
				+ this.getNomClasse()
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
				+ this.getNomClasse()
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
				
				/* Fermeture des flux. */
				bfr.close();
				isr.close();
				fis.close();
				
				/* Jette une Exception circonstanciée. */
				throw new ExceptionImport(message, e);
			}
						
			/* Gestion des longueurs maxi. */			
			this.gererLongueursMaxi(desc);
			
			/* CONTROLES. */
			/* Contrôle de l'unicité des noms Java. */
			this.controlerUniciteNomJava(desc);
			
			/* Contrôle de la longueur fournie. */
			this.controlerLongueur(desc);
			
			/* controle que l'ordre des champs est jointif */
			this.controlerJointif(compteurDeLigne, desc);
			
			/* contrôle que les colonnes sont jointives. */
			this.controlerColonnesJointives(compteurDeLigne, desc);
			
			/* AJOUT DE LA DESCRIPTION A LA MAP triée 
			 * this.specificationChampsMap. */
			this.specificationChampsMap.put(compteurDeLigne, desc);
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
			= this.getNomClasse() 
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
		

		/* Controle de l'UNICITE de nomChampJava. */		
		final String nomChampJava = pDesc.getNomChampJava();
		
		if (!(this.nomsChampsJavaSet.contains(nomChampJava))) {
			this.nomsChampsJavaSet.add(nomChampJava);
		} else {
			
			if (!(StringUtils.equalsIgnoreCase(nomChampJava, "sans objet"))) {

				final String cleMauvaisNomChamp 
					= this.getCleMauvaisNomChamp();

				final String messageMauvaisNomChamp 
				= ConfigurationApplicationManager
					.getBundleMessagesTechnique()
						.getString(cleMauvaisNomChamp);

				final String message 
				= pDesc.toString() 
				+ SEPARATEUR_MOINS_AERE
				+ this.getNomClasse() 
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
	 * method controlerLongueur(
	 * IDescriptionChamp pDesc) :<br/>
	 * Vérifie que la longueur fournie est égale à la longueur calculée.<br/>
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
			
			/* Récupération de l'ordre du champ en cours. */
			final Integer ordreChampEnCours 
				= pDesc.getOrdreChamps();
					
			/* Récupération de la description précédente. */
			final IDescriptionChamp descPrecedent 
			= this.specificationChampsMap.get(pCompteurDeLigne -1);
			

			/* Récupération de l'ordre du champ précédent. */
			if (descPrecedent != null) {
				
				final Integer ordreChampPrecedent 
				= descPrecedent.getOrdreChamps();
				

				/* Si l'ordre des champs n'est pas jointif. */
				if (ordreChampEnCours != ordreChampPrecedent + 1) {
					
					final String clePasJointif 
						= this.getClePasJointif();

					final String messagePasJointif
					= ConfigurationApplicationManager
						.getBundleMessagesTechnique()
							.getString(clePasJointif);

					final String message 
					= "Ligne " 
					+ pCompteurDeLigne 
					+ SEPARATEUR_MOINS_AERE 
					+ pDesc.getIntitule() 
					+ SEPARATEUR_MOINS_AERE 
					+ this.getNomClasse()
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
	 * method controlerColonnesJointives(
	 * int pCompteurDeLigne
	 * , IDescriptionChamp pDesc) :<br/>
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
	protected void controlerColonnesJointives(
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
	 * method getNomClasse() :<br/>
	 * Retourne le nom de la Classe.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String getNomClasse();
	

		
	/**
	 * method getCleMauvaisNomChamp() :<br/>
	 * Retourne la clé comprise dans messagestechniques.properties 
	 * en cas de doublon d'un nom de champ Java.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String getCleMauvaisNomChamp();
	
	
	
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
	 * method getClePasJointif() :<br/>
	 * Retourne la clé comprise dans messagestechniques.properties 
	 * en cas d'ordre des champs non jointif dans une description.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	protected abstract String getClePasJointif();
	

		
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
