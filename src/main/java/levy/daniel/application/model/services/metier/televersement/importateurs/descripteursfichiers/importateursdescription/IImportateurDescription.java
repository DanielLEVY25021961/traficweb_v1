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
	 * <b>lit une description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code> 
	 * au format CSV avec séparateur ';' 
	 * encodée en UTF-8 et l'importe sous forme de 
	 * SortedMap&lt;Integer, IDescriptionChamp&gt; 
	 * <code><b>this.specificationChampsMap</b></code></b>
	 * <ul>
	 * <li>utilise automatiquement la description 
	 * <code><b>this.descriptionDuFichierFile</b></code>.</li>
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
	 * si this.descriptionDuFichierFile est null ou inexistant.<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * le fichier csv de description pFileDescription n'est pas le bon 
	 * (description darwin csv au lieu de Histonat csv par exemple).<br/>
	 * - LOG.fatal, rapporte et jette une ExceptionImport lorsque 
	 * un nom de champ java 
	 * existe en doublon dans la description.<br/>
	 * <br/>
	 *
	 * @param pCharset : Charset : 
	 * charset à utiliser pour lire la description de fichier
	 * 
	 * @return SortedMap&lt;Integer, IDescriptionChamp&gt;.<br/>
	 * 
	 * @throws FichierNullException : 
	 * this.descriptionDuFichierFile est null ou inexistant.<br/>
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
	SortedMap<Integer, IDescriptionChamp> importerDescriptionUtf8() 
					throws FichierNullException
						, TableauNullException
							, TableauVideException
								, ExceptionImport
									, IOException, Exception;

	
	
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
	 * si pFile <i>et</i> this.descriptionDuFichierFile 
	 * sont null ou inexistants.<br/>
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
	 * si pFile <i>et</i> this.descriptionDuFichierFile 
	 * sont null ou inexistants.<br/>
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
	 * si pFile <i>et</i> this.descriptionDuFichierFile sont null ou inexistants.<br/>
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
	 * si pFile <i>et</i> this.descriptionDuFichierFile 
	 * sont null ou inexistants.<br/>
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
	 * <li>l'extension csv du fichier de description.</li>
	 * <li>la validité du fichier de description csv par rapport 
	 * à l'ImportateurDescription (LOG, rapport et ExceptionImport).</li>
	 * <li>l'unicité des noms java des champs dans la description 
	 * (LOG, rapport et ExceptionImport).</li>
	 * <li>Autres contrôles (ordre des champs jointifs, 
	 * colonnes jointives pour les fichiers ASCII, ...).</li>
	 * </ul>
	 * - <i>saute la ligne d'en-tête le cas échéant</i>.<br/>
	 * - <i>retire un éventuel BOM-UTF-8 au début 
	 * de la description de fichier</i>.<br/>
	 * <br/>
	 * - LOG.fatal, rapporte et jette une FichierNullException 
	 * si pFile <i>et</i> this.descriptionDuFichierFile sont null ou inexistants.<br/>
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
	 * si pFile <i>et</i> this.descriptionDuFichierFile sont null ou inexistants.<br/>
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
	 * <b>Fabrique une chaine de caractères comportant tous
	 * les éléments de description de l'en-tête de la description de fichier 
	 * séparés par des tabulations 
	 * et avec un saut de ligne \n à la fin</b>.<br/>
	 * Par exemple :<br/>
	 * <pre>ordreChamps	colonnes	longueur	intitule	nomenclature	champJava	typeJava	aNomenclature	aLexique	colonneDebut	colonneFin	longueurCalculee	</pre>
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
	 * <b>Transforme String[] en ligne CSV</b>.<br/>
	 * Affiche une ligne de la description de fichier
	 * - décomposée sous forme de tableau String[] de tokens - 
	 * sous forme de ligne CSV avec séparateur ';'.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * 1;1-3;3;Numéro de Département;calé à gauche;numDepartment;Integer;false;
	 *  false;1;3;3; pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;
	 * false; pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne "" si pTokens est null.<br/>
	 * <br/>
	 * 
	 * @param pTokens : String[] : une ligne de la description
	 * de fichier décomposée en tokens.<br/>
	 * 
	 * @return String : ligne csv avec séparateur ';'.<br/>
	 */
	String tokensToString(String[] pTokens);
	

	
	/**
	 * <b>Transforme ligne CSV en String[]</b>.<br/>
	 * décompose une ligne CSV à séparateur ';' 
	 * et retourne un tableau de tokens.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code>1;1-3;3;Numéro de Département;calé à gauche;numDepartment;Integer;false;
	 *  false;1;3;3;</code> pour le champ 'Numéro de Département' 
	 * (1ère ligne) dans la description du fichier HISTO_F07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);route;String;
	 * false; pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne null si pStringCsv est blank.<br/>
	 * <br/>
	 *
	 * @param pStringCsv : String : ligne CSV
	 * 
	 * @return : String[] : tableau de tokens.<br/>
	 */
	String[] stringCsvToTokens(String pStringCsv);
	
	
	
	/**
	 * <b>Retourne IDescriptionChamp d'ordre pOrdre,
	 * c'est à dire le pOrdre-ième champ (1-based) de la description
	 * de fichier</b> (pOrdre-ième ligne de la description).<br/>
	 * <ul>
	 * <li>utilise <code><b>this.specificationChampsMap</b></code>.</li>
	 * <li>pOrdre est 1-based.</li> 
	 * <li>1 correspond à la première <i>ligne de données</i> (sans l'en-tête) 
	 * de la description de fichier, etc...</li>
	 * <li>Ne retourne pas de IDescriptionChamp si pOrdre == 0 
	 * (retourne null).</li>
	 * </ul>
	 * ATTENTION : faire importerDescription(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - LOG.fatal et jette une MapNullException si 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * - LOG.fatal et jette une MapVideException 
	 * si <code><b>this.specificationChampsMap</b></code> est vide.<br/> 
	 * - Retourne null si la description de champ n'est pas trouvée
	 * (pOrdre inexistant dans la Map 
	 * <code><b>this.specificationChampsMap</b></code>).<br/>
	 * <br/>
	 * 
	 * @param pOrdre : Integer : 
	 * l'ordre (1-based) du champ dans la description de fichier.<br/>
	 * 
	 * @return IDescriptionChamp : la description d'ordre
	 * pOrdre (pOrdre-ième ligne de la description de fichier).<br/>
	 * 
	 * @throws MapNullException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est null.<br/>
	 * @throws MapVideException lorsque : la Map 
	 * <code><b>this.specificationChampsMap</b></code> est vide.<br/>
	 * @throws Exception 
	 */
	IDescriptionChamp getDescriptionChamp(Integer pOrdre) 
				throws MapNullException
					, MapVideException, Exception;
	

	
	/**
	 * Getter de l'Encapsulation <code><b>this.descriptionChamp</b></code> 
	 * permettant de stocker toutes les valeurs 
	 * décrivant un champ dans une description de fichier et 
	 * utilisé pour obtenir l'en-tête du fichier de description.<br/>
	 * Par exemple :<br/>
	 * <code>[ordreChamps, colonnes, longueur, intitule, nomenclature
	 * , champJava, typeJava, aNomenclature
	 * , colonneDebut, colonneFin, longueurCalculee]</code> 
	 * pour une description de HISTO_F07.<br/>
	 * <code>[ordreChamps, intitule, nomenclature, champJava, typeJava
	 * , aNomenclature]</code> 
	 * pour une description de DARWIN_CSV.<br/>
	 * <br/>
	 *
	 * @return this.descriptionChamp : IDescriptionChamp.<br/>
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
	 * valeur à passer à this.descriptionChamp.<br/>
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
	 * de section, etc...
	 * <ul>
	 * <li>LOG.fatal, rapporte et jette une FichierNullException 
	 * si pDescriptionDuFichierFile est null.</li>
	 * <li>LOG.fatal, rapporte et jette une FichierVideException 
	 * si pDescriptionDuFichierFile est vide.</li>
	 * <li>LOG.fatal, rapporte et jette une FichierInexistantException 
	 * si pDescriptionDuFichierFile est inexistant.</li>
	 * <li>LOG.fatal, rapporte et jette une FichierPasNormalException 
	 * si pDescriptionDuFichierFile n'est pas un File 
	 * normal (répertoire).</li>
	 * <li>passe le paramètre à l'attribut 
	 * <code><b>this.descriptionDuFichierFile</b></code>.</li>
	 * <li>LOG.fatal, rapporte et jette une ExceptionImport 
	 * si la description de fichier 
	 * <code><b>this.descriptionDuFichierFile</b></code>
	 * n'a pas l'extension ".csv".</li>
	 * </ul>
	 * 
	 * @param pDescriptionDuFichierFile : File.<br/>
	 * 
	 * @throws Exception
	 */
	void setDescriptionDuFichierFile(File pDescriptionDuFichierFile) 
			throws Exception ;



	/**
	 * <b>Getter de la Description du fichier</b> importée 
	 * par le présent ImportateurDescription
	 * et fournie sous forme de SortedMap&lt;Integer,IDescriptionChamp&gt; 
	 * triée <code><b>this.specificationChampsMap</b></code> contenant :<br/>
	 * - Integer : le numéro du champ (rang de la ligne dans la description
	 * du fichier comme '3' pour 'sens' dans la description de l'HISTO_F07),<br/>
	 * - IDescriptionChamp : les valeurs dans la description du champ 
	 * (N° champ, colonne début, colonne fin...).<br/>
	 * <br/>
	 * specificationChampsMap ne contient pas les en-têtes 
	 * du fichier de description.<br/>
	 * <br/>
	 *
	 * @return this.specificationChampsMap : 
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
	 * Fournit le nom de la description de fichier.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * "Description des champs d'un fichier HIT".<br/>
	 *
	 * @return : String.<br/>
	 */
	String getLabelDescriptionFichier();



} // FIN DE L'INTERFACE IImportateurDescription.-----------------------------
