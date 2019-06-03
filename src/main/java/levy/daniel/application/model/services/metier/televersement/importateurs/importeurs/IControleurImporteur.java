package levy.daniel.application.metier.importateurs.importeurs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.metier.importateurs.controleursimport.IControleurImport;
import levy.daniel.application.metier.importateurs.controleursimport.messagescontrolesimport.IMessageControleImport;

/**
 * class IControleurImporteur :<br/>
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
	 * method genererCsvRapportControleLatin9() :<br/>
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN ISO-8859-15 (Latin9) DANS LE FICHIER 
	 * 'this.fichierAImporter_RapportControle_ISO-8859-15.csv' 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ISO-8859-15 (Latin9).</li><br/>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que this.fichierAImporter 
	 * avec l'extension _RapportControle_ISO-8859-15.csv.</li><br/>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li><br/>
	 * </ul>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportControleStb est null.<br/>
	 * - retourne null si pFile est null et this.fichierAImporter 
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
	 * method genererCsvRapportControleANSI() :<br/>
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN ANSI (Windows-1252) DANS LE FICHIER 
	 * 'this.fichierAImporter_RapportControle_ANSI.csv' 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ANSI (Windows-1252).</li><br/>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que this.fichierAImporter 
	 * avec l'extension _RapportControle_ANSI.csv.</li><br/>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li><br/>
	 * </ul>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportControleStb est null.<br/>
	 * - retourne null si pFile est null et this.fichierAImporter 
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
	 * method genererCsvRapportControleUtf8() :<br/>
	 * <li>ECRIT SUR DISQUE EN UTF-8 DANS LE FICHIER 
	 * 'this.fichierAImporter_RapportControle_UTF-8.csv' 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en UTF-8.</li><br/>
	 * <li>Génère automatiquement le fichier du rapport 
	 * de contrôle de validité des champs 
	 * dans le MEME REPERTOIRE que this.fichierAImporter 
	 * avec l'extension _RapportControle_UTF-8.csv.</li><br/>
	 * <li>Rajoute un BOM-UTF-8 à la première ligne 
	 * pour forcer Excel à détecter l'UTF-8.</li><br/>
	 * </ul>
	 * <br/>
	 * 
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.rapportControleStb est null.<br/>
	 * - retourne null si pFile est null et this.fichierAImporter 
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
	 * method genererCsvRapportControleLatin9(
	 * File pFile) :<br/>
	 * <ul>
	 * ECRIT SUR DISQUE EN ISO-8859-15 (Latin9) DANS LE FICHIER pFile 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ISO-8859-15 (Latin9).</li><br/>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li><br/>
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
	 * 
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleLatin9(
			final File pFile) throws IOException;
	

	
	/**
	 * method genererCsvRapportControleANSI(
	 * File pFile) :<br/>
	 * <ul>
	 * ECRIT SUR DISQUE EN ANSI (Windows-1252) DANS LE FICHIER pFile 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en ANSI (Windows-1252).</li><br/>
	 * <li>Ne Rajoute pas de BOM-UTF-8 à la première ligne.</li><br/>
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
	 * 
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleANSI(
			final File pFile) throws IOException;
	

	
	/**
	 * method genererCsvRapportControleUtf8(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN UTF-8 DANS LE FICHIER pFile 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en UTF-8.</li><br/>
	 * <li>Rajoute un BOM-UTF-8 à la première ligne 
	 * pour forcer Excel à détecter l'UTF-8.</li><br/>
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
	 * 
	 * @return File : File : le fichier contenant 
	 * le rapport de contrôle de validité des champs écrit sur disque.<br/>
	 * 
	 * @throws IOException : Si problème d'entrée/sortie.<br/>
	 */
	File genererCsvRapportControleUtf8(
			final File pFile) throws IOException;
	
	
	
	/**
	 * method genererCsvRapportControle(
	 * File pFile
	 * , Charset pCharset) :<br/>
	 * <ul>
	 * <li>ECRIT SUR DISQUE EN pCharset DANS LE FICHIER pFile 
	 * LE RAPPORT DE CONTROLE DE VALIDITE DES CHAMPS 
	 * au format csv (avec séparateur ';') 
	 * généré lors d'un import de fichier (HIT, DARWIN, ...) 
	 * et contenu dans this.rapportControleStb.</li><br/>
	 * <li>Contient automatiquement la ligne d'en-tête csv 
	 * du rapport de contrôle de validité des champs.</li><br/>
	 * <li>Encode le fichier (rapport de contrôle de validité des champs) 
	 * en pCharset.</li><br/>
	 * <li>Rajoute un BOM-UTF-8 à la première ligne si 
	 * pCharset == CHARSET_UTF8 pour forcer Excel à détecter l'UTF-8.</li><br/>
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
			final File pFile
					, final Charset pCharset) throws IOException;
	
	

	/**
	 * method afficherRapportControleMap() :<br/>
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
	 * method afficherRapportControleMapLigne(
	 * int pNumeroLigne) :<br/>
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
	String afficherRapportControleMapLigne(
			final int pNumeroLigne);
	
	
	
	/**
	 * method isLogControle() :<br/>
	 * Getter du boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport d'erreurs de format lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @return logControle : boolean.<br/>
	 */
	boolean isLogControle();



	/**
	 * method setLogControle(
	 * boolean pLogControle) :<br/>
	 * Setter du boolean qui stipule si les Importeur
	 * doivent rapporter ou pas les contrôles de validité 
	 * (rapport d'erreurs de format lors de la lecture de fichiers).<br/>
	 * <br/>
	 *
	 * @param pLogControle : boolean : 
	 * valeur à passer à logControle.<br/>
	 */
	void setLogControle(
			final boolean pLogControle);

	

	/**
	 * method recupererCleLogControle() :<br/>
	 * Fournit la clé du log des controles
	 * stocké dans messagescontroles.properties.<br/>
	 * <br/>
	 * Sert à passer le boolean this.logControle à true ou false.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String recupererCleLogControle();
	
	
	
	/**
	 * method getRapportControleStb() :<br/>
	 * Getter du StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier (format csv, en-tête inclus).<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @return rapportControleStb : StringBuffer.<br/>
	 */
	StringBuffer getRapportControleStb();



	/**
	 * method setRapportControleStb(
	 * StringBuffer pRapportControleStb) :<br/>
	 * Setter du StringBuffer chargé de contenir le rapport des
	 * contrôles de validité des champs 
	 * lors de l'import du fichier (format csv, en-tête inclus).<br/>
	 * <br/>
	 * Ce rapport n'est null que si this.logControle vaut false. 
	 * Tester si il est vide.<br/>
	 * <br/>
	 *
	 * @param pRapportControleStb : StringBuffer : 
	 * valeur à passer à rapportControleStb.<br/>
	 */
	void setRapportControleStb(
			final StringBuffer pRapportControleStb);


	

	/**
	 * method getRapportControleMap() :<br/>
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
	SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> 
													getRapportControleMap();



	/**
	 * method setRapportControleMap(
	 * SortedMap<Integer,SortedMap<Integer,Set<IMessageControleImport>>> 
	 * pRapportControleMap) :<br/>
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
	void setRapportControleMap(
			final SortedMap<Integer, SortedMap<Integer, Set<IMessageControleImport>>> 
			pRapportControleMap);

	

	/**
	 * method getControleurImport() :<br/>
	 * Getter du Controleur de validité des champs.
	 * <br/>
	 *
	 * @return controleurImport : IControleurImport.<br/>
	 */
	IControleurImport getControleurImport();
	


	/**
	 * method setControleurImport(
	 * IControleurImport pControleurImport) :<br/>
	 * Setter du Controleur de validité des champs.
	 * <br/>
	 *
	 * @param pControleurImport : IControleurImport : 
	 * valeur à passer à controleurImport.<br/>
	 */
	void setControleurImport(
			final IControleurImport pControleurImport);



} // Fin de IControleurImporteur.--------------------------------------------
