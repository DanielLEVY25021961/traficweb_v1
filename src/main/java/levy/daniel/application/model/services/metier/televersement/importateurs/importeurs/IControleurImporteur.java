package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;



/**
 * INTERFACE IControleurImporteur :<br/>
 * RESPONSABILITE : CONTROLER LA VALIDITE DES CHAMPS DU FICHIER IMPORTE 
 * (HIT, HistonatF07, Darwin csv, ...) ET GENERER UN RAPPORT DE CONTROLE 
 * DE LA VALIDITE DES CHAMPS SUR DISQUE (Fichier sur disque).<br/>
 * Interface garantissant que tous les objets l'implémentant 
 * effectueront un contrôle de validité 
 * lors de l'import d'un fichier (HIT, HistonatF07, Darwin csv, ...).<br/>
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
 * @since 22 juil. 2014
 *
 */
public interface IControleurImporteur {
	

		
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN ISO-8859-15 (Latin9) DANS LE FICHIER 
	 * <code><b>this.fichierAImporter_RapportControle_ISO-8859-15.csv</b></code> 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format CSV (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ISO-8859-15 (Latin9).</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_ISO-8859-15.csv.</li>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.rapportControleStb</b></code> 
	 * est null.<br/>
	 * - retourne null si <code><b>this.fichierAImporter</b></code> 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleLatin9() throws IOException;
	

	
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN ANSI (Windows-1252) DANS LE FICHIER 
	 * <code><b>this.fichierAImporter_RapportControle_ANSI.csv</b></code> 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format CSV (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ANSI (Windows-1252).</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_ANSI.csv.</li>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.rapportControleStb</b></code> 
	 * est null.<br/>
	 * - retourne null si <code><b>this.fichierAImporter</b></code> 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleANSI() throws IOException;
	
	
	
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN UTF-8 DANS LE FICHIER 
	 * <code><b>this.fichierAImporter_RapportControle_UTF-8.csv</b></code> 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en UTF-8.</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_UTF-8.csv.</li>
	 * <li>Rajoute un BOM-UTF-8 à la première ligne 
	 * pour forcer Excel à détecter l'UTF-8.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.rapportControleStb</b></code> 
	 * est null.<br/>
	 * - retourne null si <code><b>this.fichierAImporter</b></code> 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleUtf8() throws IOException;
	
	
	
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN ISO-8859-15 (Latin9) DANS LE FICHIER 
	 * <code><b>pFile</b></code> 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format CSV (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ISO-8859-15 (Latin9).</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_ISO-8859-15.csv si pFile est null.</li>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.rapportControleStb</b></code> 
	 * est null.<br/>
	 * - retourne null si <code><b>this.fichierAImporter</b></code> 
	 * est introuvable.<br/>
	 * <br/>
	 * 
	 * @param pFile : File : le fichier généré.<br/>
	 *
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleLatin9(File pFile) throws IOException;
	

	
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN ANSI (Windows-1252) DANS LE FICHIER 
	 * <code><b>pFile</b></code> 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format CSV (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ANSI (Windows-1252).</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_ANSI.csv si pFile est null.</li>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.rapportControleStb</b></code> 
	 * est null.<br/>
	 * - retourne null si <code><b>this.fichierAImporter</b></code> 
	 * est introuvable.<br/>
	 * <br/>
	 * 
	 * @param pFile : File : le fichier généré.<br/>
	 *
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleANSI(File pFile) throws IOException;
	

	
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN UTF-8 DANS LE FICHIER 
	 * <code><b>pFile</b></code> 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en UTF-8.</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_UTF-8.csv si pFile est null.</li>
	 * <li>Rajoute un BOM-UTF-8 à la première ligne 
	 * pour forcer Excel à détecter l'UTF-8.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.rapportControleStb</b></code> 
	 * est null.<br/>
	 * - retourne null si <code><b>this.fichierAImporter</b></code> 
	 * est introuvable.<br/>
	 * <br/>
	 * 
	 * @param pFile : File : le fichier généré.<br/>
	 *
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleUtf8(File pFile) throws IOException;
	
	
	
	/**
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN pCharset DANS LE FICHIER pFile 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans <code><b>this.rapportControleStb</b></code>.</li>
	 * <li>retourne le rapport de contrôle généré sur disque (File).</li>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li>
	 * <li>choisit automatiquement UTF-8 si pCharset == null.</li>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en pCharset.</li>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _RapportControle_Charset.csv si pFile est null.</li>
	 * <li>Rajoute un BOM-UTF-8 à la première ligne si 
	 * pCharset == CHARSET_UTF8 pour forcer Excel à détecter l'UTF-8.</li>
	 * </ul>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportControleStb est null.<br/>
	 * - retourne null si pFile est null et this.fichierAImporter 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le fichier généré.<br/>
	 * @param pCharset : Charset : le Charset à utiliser 
	 * pour l'encodage du fichier généré.<br/>
	 * 
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControle(
			File pFile
					, Charset pCharset) throws IOException;
	
	

	/**
	 * String pour l'affichage du rapport de contrôle 
	 * de validité des champs contenu 
	 * dans this.rapportControleMap.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportControleMap est null.<br/>
	 * - retourne vide si this.rapportControleMap est vide.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String afficherRapportControleMap();
	
	
	
	/**
	 * String pour affichage des lignes du rapport de contrôle
	 * de validité des champs (contenu dans this.rapportControleMap) 
	 * relatives à la ligne pNumeroLigne 
	 * (1-based) du fichier importé (HIT, DARWIN, ..).<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportControleMap est null.<br/>
	 * - retourne vide si this.rapportControleMap est vide.<br/>
	 * - retourne null si aucun contrôle n'a matché pour cette ligne 
	 * ou si cette ligne n'existe pas dans le fichier importé 
	 * (HIT, DARWIN, ...).<br/>
	 * <br/>
	 *
	 * @param pNumeroLigne : int : numero de la ligne (1-based) 
	 * dans le fichier importé (HIT, DARWIN, ...).<br/>
	 * 
	 * @return : String.<br/>
	 */
	String afficherRapportControleMapLigne(int pNumeroLigne);
	
	
	
	/**
	 * Getter du boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport d'erreurs de format lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @return logControle : boolean.<br/>
	 */
	boolean isLogControle();



	/**
	 * Setter du boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport d'erreurs de format lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @param pLogControle : boolean : 
	 * valeur à passer à logControle.<br/>
	 */
	void setLogControle(boolean pLogControle);

	

	/**
	 * Fournit la clé du log des controles
	 * stocké dans ressource_externes/messagescontroles.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logControle à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleLogControle();
	
	
	
	/**
	 * Getter du StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier (format csv, en-tête inclus).<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * <b>Tester si il est vide</b>.<br/>
	 *
	 * @return rapportControleStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportControleStb();



	/**
	 * Setter du StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier (format csv, en-tête inclus).<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * <b>Tester si il est vide</b>.<br/>
	 * <br/>
	 *
	 * @param pRapportControleStb : StringBuffer : 
	 * valeur à passer à rapportControleStb.<br/>
	 */
	void setRapportControleStb(StringBuffer pRapportControleStb);


	
	/**
	 * Getter de la 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer
	 * , Set&lt;IMessageControleImport&gt;&gt;&gt; :<br/>
	 * Rapport de contrôle de validité sous forme de map triée avec :<br/>
	 * - Integer : le numéro de la ligne.<br/>
	 * - SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; 
	 * map triée avec :<br/>
	 * - Integer : le numéro d'ordre du champ dans la ligne.<br/>
	 * - Set&lt;IMessageControleImport&gt; : 
	 * ensemble des messages de contrôle pour ce champ.<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @return rapportControleMap : 
	 * SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>>.<br/>
	 */
//	SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> getRapportControleMap();



	/**
	 * Setter de la 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer
	 * , Set&lt;IMessageControleImport&gt;&gt;&gt; :<br/>
	 * Rapport de contrôle de validité sous forme de map triée avec :<br/>
	 * - Integer : le numéro de la ligne.<br/>
	 * - SortedMap&lt;Integer, Set&lt;IMessageControleImport&gt;&gt; 
	 * map triée avec :<br/>
	 * - Integer : le numéro d'ordre du champ dans la ligne.<br/>
	 * - Set&lt;IMessageControleImport&gt; : 
	 * ensemble des messages de contrôle pour ce champ.<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @param pRapportControleMap : 
	 * SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>> : 
	 * valeur à passer à rapportControleMap.<br/>
	 */
//	void setRapportControleMap(
//			final SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> 
//			pRapportControleMap);

	

	/**
	 * method getControleurImport() :<br/>
	 * Getter du Controleur de validité des champs.
	 * <br/>
	 *
	 * @return controleurImport : IControleurImport.<br/>
	 */
//	IControleurImport getControleurImport();
	


	/**
	 * method setControleurImport(
	 * IControleurImport pControleurImport) :<br/>
	 * Setter du Controleur de validité des champs.
	 * <br/>
	 *
	 * @param pControleurImport : IControleurImport : 
	 * valeur à passer à controleurImport.<br/>
	 */
//	void setControleurImport(
//			final IControleurImport pControleurImport);



} // FIN DE L'INTERFACE IControleurImporteur.--------------------------------
