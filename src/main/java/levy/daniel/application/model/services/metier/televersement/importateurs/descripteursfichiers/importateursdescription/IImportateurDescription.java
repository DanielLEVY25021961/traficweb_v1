package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.importateursdescription;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.TableauVideException;
import levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps.IDescriptionChamp;

/**
 * class IImportateurDescription :<br/>
 * RESPONSABILITE : IMPORT D'UN FICHIER DE DESCRIPTION.<br/>
 * Interface factorisant les méthodes des ImportateurDescription.<br/>
 * <br/>
 * Tous les ImportateurDescription possèdent une 
 * méthode importerDescription(File pFileDescription) 
 * où pFileDescription encapsule la description en csv du fichier 
 * (HIT, Histonat, Darwin.csv, FEOR XML, ...) à servir.<br/>
 * La description est servie sous forme de 
 * SortedMap&lt;Integer, IDescriptionChamp&gt; specificationChampsMap 
 * retournée par importerDescription(File pFileDescription).<br/>
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
 * @since 29 juin 2014
 *
 */
public interface IImportateurDescription 
					extends IRapporteurImpoDesc
						, IFormatteurLongueursImpoDesc
							, IFournisseurJTableImpoDesc
								, IFournisseurCsvImpoDesc {
	
	
	
	/**
	 * METHODE_IMPORTERDESCRIPTION : String :<br/>
	 * "Méthode ImporterDescription(File) - ".<br/>
	 */
	String METHODE_IMPORTERDESCRIPTION 
		= "Méthode ImporterDescription(File) - ";


		
	/**
	 * method importerDescription(
	 * File pFileDescription) :<br/>
	 * - Lit un File encodé en UTF-8 encapsulant la description du fichier 
	 * et stocke le résultat dans la 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt; specificationChampsMap.<br/>
	 * - Retourne la SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * specificationChampsMap.<br/>
	 * - Saute les lignes null ou vides.<br/>
	 * - Gère le tableau des longueurs maxi 
	 * pour affichage formatté de la description à la console.<br/>
	 * <br/>
	 * - Contrôle :<br/>
	 * <li>la validité du fichier de description csv par rapport 
	 * à l'ImportateurDescription (LOG, rapport et ExceptionImport).<br/>
	 * <li>l'unicité des noms java des champs dans la description 
	 * (LOG, rapport et ExceptionImport).<br/>
	 * <li>Autres contrôles.<br/>
	 * <br/>
	 * - saute la ligne d'en-tête le cas échéant.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte et jette une FichierNullException 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * le fichier csv de description pFileDescription n'est pas le bon 
	 * (description darwin csv au lieu de Histonat csv par exemple).<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * un nom de champ java 
	 * existe en doublon dans la description.<br/>
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
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescription(
			final File pFileDescription) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;

	
	
	/**
	 * method importerDescriptionUtf8(
	 * File pFileDescription) :<br/>
	 * - Lit un File encodé en UTF-8 encapsulant la description du fichier 
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
	 * <li>la validité des longueurs fournies (LOG, rapport).<br/>
	 * <li>l'ordre jointif des champs (LOG, rapport est ExceptionImport).<br/>
	 * <li>le fait que les colonnes sont jointives dans la description 
	 * (LOG, rapport est ExceptionImport).<br/>
	 * <br/>
	 * - saute la ligne d'en-tête le cas échéant en se basant 
	 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête HistonatF07 
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
	 * - LOG.fatal, rapporte lorsqu'une longueur founie 
	 * diffère de la Longueur calculée dans la description.<br/>
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
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescriptionUtf8(
			final File pFileDescription) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;
	

	
	/**
	 * method importerDescriptionLatin9(
	 * File pFileDescription) :<br/>
	 * - Lit un File encodé en LATIN9 encapsulant la description du fichier 
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
	 * <li>la validité des longueurs fournies (LOG, rapport).<br/>
	 * <li>l'ordre jointif des champs (LOG, rapport est ExceptionImport).<br/>
	 * <li>le fait que les colonnes sont jointives dans la description 
	 * (LOG, rapport est ExceptionImport).<br/>
	 * <br/>
	 * - saute la ligne d'en-tête le cas échéant en se basant 
	 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête HistonatF07 
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
	 * - LOG.fatal, rapporte lorsqu'une longueur founie 
	 * diffère de la Longueur calculée dans la description.<br/>
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
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescriptionLatin9(
			final File pFileDescription) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;
	
	
	
	/**
	 * method importerDescription(
	 * File pFileDescription
	 * , Charset pCharset) :<br/>
	 * - Lit un File encodé en pCharset encapsulant la description du fichier 
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
	 * <li>la validité des longueurs fournies (LOG, rapport).<br/>
	 * <li>l'ordre jointif des champs (LOG, rapport est ExceptionImport).<br/>
	 * <li>le fait que les colonnes sont jointives dans la description 
	 * (LOG, rapport est ExceptionImport).<br/>
	 * <br/>
	 * - saute la ligne d'en-tête le cas échéant en se basant 
	 * sur le fait qu'on aura 'ordreChamps' pour l'en-tête HistonatF07 
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
	 * - LOG.fatal, rapporte lorsqu'une longueur founie 
	 * diffère de la Longueur calculée dans la description.<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * l'ordre des champs n'est pas jointif<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * les colonnes ne sont pas jointives.<br/>
	 * <br/>
	 *
	 * @param pFileDescription : File : Le File encapsulant la description.<br/>
	 * @param pCharset : Charset : Charset dans lequel 
	 * la description est encodée.<br/>
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
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescription(
			final File pFileDescription
				, final Charset pCharset) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;
	
	

	/**
	 * method fournirLigneEnTetestoString() :<br/>
	 * Fabrique une chaine de caractères comportant tous
	 * les éléments de description de l'en-tête de la description 
	 * séparés par des tabulations 
	 * et avec un saut de ligne \n à la fin.<br/>
	 * <br/>
	 * - retourne null si this.descriptionChamp est null.<br/>
	 * <br/>
	 *
	* @return String : une chaine de caractères décrivant la ligne d'en-têtes 
	* en exposant toutes ses valeurs séparées par des tabulations 
	* et avec un saut de ligne \n à la fin.<br/>
	 */
	String fournirLigneEnTetestoString();
	
	
	
	/**
	 * method getDescriptionChamp(
	 * Integer pOrdre) :<br/>
	 * - Retourne IDescriptionChamp d'ordre pOrdre,
	 * c'est à dire le pOrdre-ième champ de la description
	 * de fichier (pOrdre-ième ligne de la description).<br/>
	 * <br/>
	 * pOrdre est 1-based.<br/> 
	 * 1 correspond à la première ligne de données de la description, etc...<br/>
	 * Ne retourne pas l'en-tête si pOrdre == 0.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * this.specificationChampsMap est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si this.specificationChampsMap est vide.<br/> 
	 * <br/>
	 * - Retourne null si la description de champ n'est pas trouvée
	 * (pOrdre inexistant dans la Map).<br/>
	 * <br/>
	 * 
	 * @param pOrdre : Integer : 
	 * l'ordre du champ dans la description de fichier.<br/>
	 * 
	 * @return IDescriptionChamp : la description d'ordre
	 * pOrdre (pOrdre-ième ligne de la description de fichier).<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * 'specificationChampsMap' est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * 'specificationChampsMap' est vide.<br/>
	 * @throws Exception 
	 */
	IDescriptionChamp getDescriptionChamp(
			final Integer pOrdre) 
				throws MapNullException
					, MapVideException, Exception;
	

	
	/**
	 * method getDescriptionChamp() :<br/>
	 * Getter de l'Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HistonatF07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de Darwin csv.<br/>
	 * <br/>
	 *
	 * @return descriptionChamp : IDescriptionChamp.<br/>
	 */
	IDescriptionChamp getDescriptionChamp();



	/**
	 * method setDescriptionChamp(
	 * IDescriptionChamp pDescriptionChamp) :<br/>
	 * Setter de l'Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HistonatF07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de Darwin csv.<br/>
	 * <br/>
	 *
	 * @param pDescriptionChamp : IDescriptionChamp : 
	 * valeur à passer à descriptionChamp.<br/>
	 */
	void setDescriptionChamp(
			final IDescriptionChamp pDescriptionChamp);

	
	
	/**
	 * method getDescriptionDuFichierFile() :
	 * Getter du File encapsulant un fichier comprenant la
	 * description du fichier à lire (spécification).<br/>
	 * Précise par exemple que les colonnes 1 à 3 comprennent le
	 * Numéro du Département, les colonnes 4-9 le numéro
	 * de section, etc...<br/>
	 * <br/>
	 * 
	 * @return  the descriptionDuFichierFile : File.<br/>
	 */
	File getDescriptionDuFichierFile();



	/**
	 * method setDescriptionDuFichierFile(
	 * File pDescriptionDuFichierFile) :<br/>
	 * Setter du File encapsulant un fichier comprenant la
	 * description du fichier à lire (spécification).<br/>
	 * Précise par exemple que les colonnes 1 à 3 comprennent le
	 * Numéro du Département, les colonnes 4-9 le numéro
	 * de section, etc...<br/>
	 * <br/>
	 * 
	 * @param pDescriptionDuFichierFile : File.<br/>
	 */
	void setDescriptionDuFichierFile(
			final File pDescriptionDuFichierFile);



	/**
	 * method getSpecificationChampsMap() :<br/>
	 * Getter de la Description du fichier importée 
	 * par le présent ImportateurDescription
	 * et fournie sous forme de Map triée contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HistonatF07),<br/>
	 * - IDescriptionChamp : les valeurs dans la description du champ 
	 * (N° champ, colonne début, colonne fin...).<br/>
	 * <br/>
	 * specificationChampsMap ne contient pas les en-têtes 
	 * du fichier de description.<br/>
	 * <br/>
	 *
	 * @return the specificationChampsMap : 
	 * SortedMap&lt;Integer,IDescriptionChamp&gt;.<br/>
	 */
	SortedMap<Integer, IDescriptionChamp> getSpecificationChampsMap();
	


	/**
	 * method setSpecificationChampsMap(
	 * SortedMap&lt;Integer,IDescriptionChamp&gt; pSpecificationChampsMap) :<br/>
	 * Setter de la Description du fichier importée 
	 * par le présent ImportateurDescription
	 * et fournie sous forme de Map triée contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HistonatF07),<br/>
	 * - IDescriptionChamp : les valeurs dans la description du champ 
	 * (N° champ, colonne début, colonne fin...).<br/>
	 * <br/>
	 * specificationChampsMap ne contient pas les en-têtes 
	 * du fichier de description.<br/>
	 * <br/>
	 *
	 * @param pSpecificationChampsMap : SortedMap&lt;Integer,IDescriptionChamp&gt; : 
	 * valeur à passer à specificationChampsMap.<br/>
	 */
	void setSpecificationChampsMap(
			final SortedMap<Integer, IDescriptionChamp> 
										pSpecificationChampsMap);


	
	/**
	 * method getNomDescriptionChamp() :<br/>
	 * Fournit le nom de la description des champs.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * "Description des champs d'un fichier HIT".<br/>
	 *
	 * @return : String.<br/>
	 */
	String getNomDescriptionChamp();



} // FIN DE L'INTERFACE IImportateurDescription.-----------------------------
