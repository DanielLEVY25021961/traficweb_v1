package levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.IConstantes;
import levy.daniel.application.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;
import levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps.impl.DescriptionChampMapping;
import levy.daniel.application.metier.importateurs.descripteursfichiers.importateursdescription.AbstractImportateurDescription;

/**
 * class ImportateurDescriptionMapping :<br/>
 * Importateur concret pour les DESCRIPTIONS EN CSV 
 * des Mapping Histonat-HIT-Darwin-ISIDOR (écrit en csv avc séparateur ';').<br/>
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
	 * CLASSE_IMPORTATEURDESCRIPTIONMAPPING : String : <br/>
	 * "CLASSE ImportateurDescriptionMapping - ".<br/>
	 */
	public static final String CLASSE_IMPORTATEURDESCRIPTIONMAPPING 
		= "CLASSE ImportateurDescriptionMapping - ";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
			.getLog(ImportateurDescriptionMapping.class);

	
	// *************************METHODES************************************/
	
	/**
	 * method CONSTRUCTEUR ImportateurDescriptionMapping() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ImportateurDescriptionMapping() {
		
		super();
		
		/* Passe le bon DescriptionChamp. */
		this.descriptionChamp = new DescriptionChampMapping();
		
		/* Détermination de la valeur du boolean qui
		 * stipule si il faut logger l'import de la description
		 * ou pas. */
		this.determinerSiLogErreurs();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
		
	 /**
	 * method CONSTRUCTEUR ImportateurDescriptionMapping(
	 * File pDescriptionDuFichierFile) :<br/>
	 * CONSTRUCTEUR ARCHICOMPLET.<br/>
	 * <br/>
	 *
	 * @param pDescriptionDuFichierFile : File : 
	 * la description de fichier à mettre 
	 * à la disposition de l'application.<br/>
	 */
	public ImportateurDescriptionMapping(
			final File pDescriptionDuFichierFile) {
		
		super(new DescriptionChampMapping(), pDescriptionDuFichierFile);
		
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
	 */
	private void determinerSiLogErreurs() {
		
		final String cleLogImport = this.recupererCleLogErreur();

		final String logImportString 
		= ConfigurationApplicationManager
			.getBundleMessagesTechniques()
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
	 * method importerDescription(
	 * File pFileDescription) :<br/>
	 * - Lit un File encapsulant la description du fichier 
	 * et stocke le résultat dans la 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt; specificationChampsMap.<br/>
	 * - Retourne la SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * specificationChampsMap.<br/>
	 * - Gère le tableau des longueurs maxi 
	 * pour affichage formatté de la description à la console.<br/>
	 * <br/>
	 * - Contrôle :<br/>
	 * <li>la validité du fichier de description csv par rapport 
	 * à l'ImportateurDescription (LOG, rapport et ExceptionImport).<br/>
	 * <li>l'unicité des noms java des champs dans la description 
	 * (LOG, rapport et ExceptionImport).<br/>
	 * <li>l'ordre jointif des champs (LOG, rapport est ExceptionImport).<br/>
	 * <li>le fait que les colonnes sont jointives dans la description 
	 * (LOG, rapport est ExceptionImport).<br/>
	 * <br/>
	 * - saute la ligne d'en-tête le cas échéant en se basant 
	 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête du Mapping 
	 * et une valeur entière pour toutes les lignes significatives.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte et jette une FichierNullException 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * le fichier csv de description pFileDescription n'est pas le bon 
	 * (description darwin csv au lieu de Histonat csv par exemple).<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * un nom de champ java 
	 * existe en doublon dans la description.<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * l'ordre des champs n'est pas jointif<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * les colonnes ne sont pas jointives.<br/>
	 * <br/>
	 *
	 * @param pFileDescription : File : Le File encapsulant la description.<br/>
	 * 
	 * @return SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de Darwin csv au lieu de HistonatF07 par exemple).<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * l'ordre des champs n'est pas jointif.<br/>
	 * les colonnes ne sont pas jointives.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 */
	@Override
	public SortedMap<Integer, IDescriptionChamp> importerDescription(
			final File pFileDescription) 
					throws FichierNullException
					, TableauNullException
						, TableauVideException
							, ExceptionImport
								, IOException {
		
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
				= IConstantes.PATTERN_SEPARATEUR_CSV.split(ligneLue);
			
			/* saute la ligne d'en-tête le cas échéant en se basant 
			 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête du Mapping 
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
			final IDescriptionChamp desc = new DescriptionChampMapping();
			
			/* Lecture de chaque ligne de la description. */
			try {
				
				desc.lireChamp(tokens);
				
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				final String message 
				= desc.toString() 
				+ IConstantes.SEPARATEUR_MOINS_AERE
				+ CLASSE_IMPORTATEURDESCRIPTIONMAPPING
				+ METHODE_IMPORTERDESCRIPTION 
				+ messageDescripteur;
								
				/* Rapport d'erreur. */
				if (!StringUtils.isBlank(messageDescripteur)) {
										
					if (this.logImportDescription) {
						this.rapportImportDescriptionStb.append(message);
						this.rapportImportDescriptionStb.append(IConstantes.SAUT_LIGNE);
						
					}
					
				}
			} catch (Exception e) {
				
				/* Rapport d'erreur (provenant du Descripteur). */
				final String messageDescripteur 
					= desc.getRapportDescriptionStb().toString();
				
				final String message 
				= "MAUVAIS FICHIER DE DESCRIPTION ???" 
				+ IConstantes.SEPARATEUR_MOINS_AERE
				+ CLASSE_IMPORTATEURDESCRIPTIONMAPPING 
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
						this.rapportImportDescriptionStb.append(IConstantes.SAUT_LIGNE);
						
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
			= CLASSE_IMPORTATEURDESCRIPTIONMAPPING 
			+ METHODE_IMPORTERDESCRIPTION 
			+ "Le fichier de description à importer est null ou inexistant";
			
			/* Logge. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			/* Rapport d'erreur. */
			if (this.logImportDescription) {
				this.rapportImportDescriptionStb.append(message);
				this.rapportImportDescriptionStb.append(IConstantes.SAUT_LIGNE);
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
	 * @throws ExceptionImport lorsque : un nom de champ java 
	 * existe en doublon dans la description.<br/>
	 */
	private void controlerUniciteNomJava(
			final IDescriptionChamp pDesc) throws ExceptionImport {
		
		/* ne fait rien si pDesc est null. */
		if (pDesc == null) {
			return;
		}
		
		/* Cast en DescriptionChampHistoF07. */
		final DescriptionChampMapping desc 
			= (DescriptionChampMapping) pDesc;
				
		/* Controle de l'UNICITE de nomChampJava. */		
		final String nomChampJava = desc.getChampJavaHisto();
		
		if (!(this.nomsChampsJavaSet.contains(nomChampJava))) {
			this.nomsChampsJavaSet.add(nomChampJava);
		} else {
			
			if (!(StringUtils.equalsIgnoreCase(nomChampJava, "sans objet"))) {
	
				final String cleMauvaisNomChamp 
				= "importateurdescriptionf07.liredescription.mauvaisnomchamp";
	
				final String messageMauvaisNomChamp 
				= ConfigurationApplicationManager
					.getBundleMessagesTechniques()
						.getString(cleMauvaisNomChamp);
	
				final String message 
				= desc.toString() 
				+ IConstantes.SEPARATEUR_MOINS_AERE
				+ CLASSE_IMPORTATEURDESCRIPTIONMAPPING 
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
	 * @throws ExceptionImport lorsque : 
	 * l'ordre des champs n'est pas jointif.<br/>
	 */
	private void controlerJointif(
			final int pCompteurDeLigne
				, final IDescriptionChamp pDesc) throws ExceptionImport {
		
		/* ne fait rien si pDesc est null. */
		if (pDesc == null) {
			return;
		}
		
		
		/* ORDRE DES CHAMPS CONTINU. ****/
		if (pCompteurDeLigne > 1) {
			
			/* Cast en DescriptionChampHistoF07. */
			final DescriptionChampMapping desc 
				= (DescriptionChampMapping) pDesc;
			
			/* Récupération de l'ordre du champ en cours. */
			final Integer ordreChampEnCours 
				= desc.getOrdreChampsHisto();
					
			/* Récupération de la description précédente. */
			final DescriptionChampMapping descPrecedent 
			= (DescriptionChampMapping) 
					this.specificationChampsMap.get(pCompteurDeLigne -1);
			
	
			/* Récupération de l'ordre du champ précédent. */
			if (descPrecedent != null) {
				
				final Integer ordreChampPrecedent 
				= descPrecedent.getOrdreChampsHisto();
				
	
				/* Si l'ordre des champs n'est pas jointif. */
				if (ordreChampEnCours != ordreChampPrecedent + 1) {
					
					final String clePasJointif 
					= "importateurdescriptionf07.liredescription.pasjointif";
	
					final String messagePasJointif
					= ConfigurationApplicationManager
						.getBundleMessagesTechniques()
							.getString(clePasJointif);
	
					final String message 
					= "Ligne " 
					+ pCompteurDeLigne 
					+ IConstantes.SEPARATEUR_MOINS_AERE 
					+ desc.getIntitule() 
					+ IConstantes.SEPARATEUR_MOINS_AERE 
					+ CLASSE_IMPORTATEURDESCRIPTIONMAPPING 
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
	 * @throws ExceptionImport lorsque : 
	 * les colonnes ne sont pas jointives.<br/>
	 */
	private void controlerColonnesJointives(
			final int pCompteurDeLigne
				, final IDescriptionChamp pDesc) 
								throws ExceptionImport {
		
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
						.getBundleMessagesTechniques()
							.getString(clePasJointif);
	
					final String message 
					= "Ligne " 
					+ pCompteurDeLigne 
					+ IConstantes.SEPARATEUR_MOINS_AERE 
					+ desc.getIntitule() 
					+ IConstantes.SEPARATEUR_MOINS_AERE 
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
	 * "Description des champs d'un fichier de Mapping HIT-HISTO_F07-DARWIN".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomDescriptionChamp() {
		return "Description des champs d'un fichier de Mapping HIT-HISTO_F07-DARWIN";
	} // Fin de getNomDescriptionChamp().__________________________________
	

	
} // FIN DE LA CLASSE ImportateurDescriptionMapping.-------------------------
