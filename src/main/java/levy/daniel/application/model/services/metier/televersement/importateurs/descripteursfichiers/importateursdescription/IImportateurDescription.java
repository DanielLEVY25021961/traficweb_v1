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
 * INTERFACE IImportateurDescription :<br/>
 * <p>
 * RESPONSABILITE : IMPORT D'UN FICHIER DE DESCRIPTION.
 * </p>
 * 
 * <p>
 * Interface factorisant les méthodes des ImportateurDescription.
 * </p>
 * 
 * <p>
 * Tous les ImportateurDescription possèdent une 
 * méthode <code><b>importerDescription(File pFileDescription)</b></code> 
 * où pFileDescription encapsule la description en csv du fichier 
 * (HIT, HISTO_F08, HISTO_F07, DARWIN_CSV, FEOR XML, Histonat, ...) 
 * à servir.<br/>
 * La description est servie sous forme de 
 * <code>SortedMap&lt;Integer, IDescriptionChamp&gt; 
 * <b>this.specificationChampsMap</b></code> 
 * retournée par la méthode 
 * <code><b>importerDescription(File pFileDescription)</b></code>.
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * 
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
	 * "Méthode ImporterDescription(File)".<br/>
	 */
	String METHODE_IMPORTERDESCRIPTION 
		= "Méthode ImporterDescription(File)";

	
	
	/**
	 * <b>lit une description de fichier pFileDescription 
	 * au format CSV avec séparateur ';' 
	 * encodée en UTF-8 et l'importe sous forme de 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code></b>
	 * <ul>
	 * <li>utilise automatiquement la description 
	 * <code><b>this.descriptionDuFichierFile</b></code> 
	 * si pFileDescription est null ou ne convient pas.</li>
	 * <li>retire un éventuel caractère BOM_UTF_8 à la première ligne.</li>
	 * <li>saute une éventuelle ligne d'en-tête dans la description de fichier.</li>
	 * <li>Saute les lignes null ou vides dans la description de fichier.</li>
	 * <li>Retourne la SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>Gère le tableau des longueurs maxi 
	 * pour affichage formaté de la description à la console.</li>
	 * </ul>
	 * 
	 * - Contrôle :<br/>
	 * <ul>
	 * <li>la validité du fichier de description csv par rapport 
	 * à l'ImportateurDescription (LOG, rapport et ExceptionImport).</li>
	 * <li>l'unicité des noms java des champs dans la description 
	 * (LOG, rapport et ExceptionImport).</li>
	 * <li>Autres contrôles.</li>
	 * </ul>
	 * - <i>saute la ligne d'en-tête le cas échéant</i>.<br/>
	 * - <i>retire un éventuel BOM-UTF-8 au début 
	 * de la description de fichier</i>.<br/>
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
	 * @param pFileDescription : File : 
	 * Le File encapsulant la description de fichier.<br/>
	 * @param pCharset : Charset : 
	 * charset à utiliser pour lire la description de fichier
	 * 
	 * @return SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de DARWIN_CSV au lieu de HISTO_F07 par exemple).<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescriptionUtf8(
			File pFileDescription) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;
	

	
	/**
	 * <b>lit une description de fichier pFileDescription 
	 * au format CSV avec séparateur ';' 
	 * encodée en ISO-8859-15 et l'importe sous forme de 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code></b>
	 * <ul>
	 * <li>utilise automatiquement la description 
	 * <code><b>this.descriptionDuFichierFile</b></code> 
	 * si pFileDescription est null ou ne convient pas.</li>
	 * <li>saute une éventuelle ligne d'en-tête dans la description de fichier.</li>
	 * <li>Saute les lignes null ou vides dans la description de fichier.</li>
	 * <li>Retourne la SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>Gère le tableau des longueurs maxi 
	 * pour affichage formaté de la description à la console.</li>
	 * </ul>
	 * 
	 * - Contrôle :<br/>
	 * <ul>
	 * <li>la validité du fichier de description csv par rapport 
	 * à l'ImportateurDescription (LOG, rapport et ExceptionImport).</li>
	 * <li>l'unicité des noms java des champs dans la description 
	 * (LOG, rapport et ExceptionImport).</li>
	 * <li>Autres contrôles.</li>
	 * </ul>
	 * - <i>saute la ligne d'en-tête le cas échéant</i>.<br/>
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
	 * @param pFileDescription : File : 
	 * Le File encapsulant la description de fichier.<br/>
	 * @param pCharset : Charset : 
	 * charset à utiliser pour lire la description de fichier
	 * 
	 * @return SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de DARWIN_CSV au lieu de HISTO_F07 par exemple).<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescriptionLatin9(
			File pFileDescription) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;
	
	
	
	/**
	 * <b>lit une description de fichier pFileDescription 
	 * au format CSV avec séparateur ';' 
	 * encodée en pCharset et l'importe sous forme de 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code></b>
	 * <ul>
	 * <li>utilise automatiquement la description 
	 * <code><b>this.descriptionDuFichierFile</b></code> 
	 * si pFileDescription est null ou ne convient pas.</li>
	 * <li>choisit automatiquement le Charset UTF-8 si pCharset == null 
	 * ou pCharset ne peut pas encoder.</li>
	 * <li>retire un éventuel caractère BOM_UTF_8 à la première ligne 
	 * si charset == UTF-8.</li>
	 * <li>saute une éventuelle ligne d'en-tête dans la description de fichier.</li>
	 * <li>Saute les lignes null ou vides dans la description de fichier.</li>
	 * <li>Retourne la SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>Gère le tableau des longueurs maxi 
	 * pour affichage formaté de la description à la console.</li>
	 * </ul>
	 * 
	 * - Contrôle :<br/>
	 * <ul>
	 * <li>la validité du fichier de description csv par rapport 
	 * à l'ImportateurDescription (LOG, rapport et ExceptionImport).</li>
	 * <li>l'unicité des noms java des champs dans la description 
	 * (LOG, rapport et ExceptionImport).</li>
	 * <li>Autres contrôles.</li>
	 * </ul>
	 * - <i>saute la ligne d'en-tête le cas échéant</i>.<br/>
	 * - <i>retire un éventuel BOM-UTF-8 au début 
	 * de la description de fichier</i>.<br/>
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
	 * @param pFileDescription : File : 
	 * Le File encapsulant la description de fichier.<br/>
	 * @param pCharset : Charset : 
	 * charset à utiliser pour lire la description de fichier
	 * 
	 * @return SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * 
	 * @throws FichierNullException : 
	 * si pFile et this.descriptionDuFichierFile sont null ou inexistants.<br/>
	 * @throws IOException lorsque : problème d'entrée sortie.<br/>
	 * @throws ExceptionImport lorsque :<br/>
	 * le fichier de description passé en paramètre pFileDescription 
	 * n'est pas le bon 
	 * (description de DARWIN_CSV au lieu de HISTO_F07 par exemple).<br/>
	 * un nom de champ java existe en doublon dans la description.<br/>
	 * @throws TableauVideException : 
	 * si une ligne de la description est null.<br/>
	 * @throws TableauNullException : 
	 * si une ligne de la description est vide.<br/> 
	 * 
	 * @throws Exception 
	 */
	SortedMap<Integer, IDescriptionChamp> importerDescription(
			File pFileDescription
				, Charset pCharset) 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;
	
	

	/**
	 * Fabrique une chaine de caractères comportant tous
	 * les éléments de description de l'en-tête de la description de fichier 
	 * séparés par des tabulations 
	 * et avec un saut de ligne \n à la fin.<br/>
	 * <ul>
	 * <li>utilise le IDescriptionChamp 
	 * <code><b>this.this.descriptionChamp</b></code> pour connaitre 
	 * l'en-tête de la description de fichier.</li>
	 * </ul>
	 * - retourne null si <code><b>this.descriptionChamp</b></code> 
	 * est null.<br/>
	 * <br/>
	 *
	* @return String : une chaine de caractères décrivant la ligne d'en-têtes 
	* en exposant toutes ses valeurs séparées par des tabulations 
	* et avec un saut de ligne \n à la fin.<br/>
	 */
	String fournirLigneEnTetestoString();
	
	
	
	/**
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
	IDescriptionChamp getDescriptionChamp(Integer pOrdre) 
				throws MapNullException
					, MapVideException, Exception;
	

	
	/**
	 * Getter de l'Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HISTO_F07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de DARWIN_CSV.<br/>
	 * <br/>
	 *
	 * @return descriptionChamp : IDescriptionChamp.<br/>
	 */
	IDescriptionChamp getDescriptionChamp();



	/**
	 * Setter de l'Encapsulation permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier.<br/>
	 * Par exemple :<br/>
	 * [ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee] 
	 * pour une description de HISTO_F07.<br/>
	 * [ordreChamps, intitule, nomenclature, champJava, typeJava, aNomenclature] 
	 * pour une description de DARWIN_CSV.<br/>
	 * <br/>
	 *
	 * @param pDescriptionChamp : IDescriptionChamp : 
	 * valeur à passer à descriptionChamp.<br/>
	 */
	void setDescriptionChamp(IDescriptionChamp pDescriptionChamp);

	
	
	/**
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
	 * Setter du File encapsulant un fichier comprenant la
	 * description du fichier à lire (spécification).<br/>
	 * Précise par exemple que les colonnes 1 à 3 comprennent le
	 * Numéro du Département, les colonnes 4-9 le numéro
	 * de section, etc...<br/>
	 * <br/>
	 * 
	 * @param pDescriptionDuFichierFile : File.<br/>
	 */
	void setDescriptionDuFichierFile(File pDescriptionDuFichierFile);



	/**
	 * Getter de la Description du fichier importée 
	 * par le présent ImportateurDescription
	 * et fournie sous forme de Map triée contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HISTO_F07),<br/>
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
	 * Setter de la Description du fichier importée 
	 * par le présent ImportateurDescription
	 * et fournie sous forme de Map triée contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HISTO_F07),<br/>
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
					SortedMap<Integer, IDescriptionChamp> 
										pSpecificationChampsMap);


	
	/**
	 * Fournit le nom de la description des champs.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * "Description des champs d'un fichier HIT".<br/>
	 *
	 * @return : String.<br/>
	 */
	String getNomDescriptionChamp();



} // FIN DE L'INTERFACE IImportateurDescription.-----------------------------
