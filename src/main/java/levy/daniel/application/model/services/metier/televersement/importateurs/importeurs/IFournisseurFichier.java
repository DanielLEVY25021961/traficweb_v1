package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * INTERFACE IFournisseurFichier :<br/>
 * RESPONSABILITE : PERMETTRE DE GENERER à partir du résultat de l'import 
 * sous forme de SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
 * UN FICHIER IDENTIQUE AU FICHIER IMPORTE (HIT, HISTO_F07, DARWIN_CSV, ...) 
 * EVENTUELLEMENT EN CHANGEANT SON ENCODAGE.<br/>
 * Interface garantissant que tous les objets l'implémentant 
 * sauront reconstituer un fichier à partir d'une 
 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
 * encapsulant le fichier (HIT, Darwin, ...) importé avec :<br/>
 * - Integer : le numéro d'ordre de la ligne dans le fichier.<br/>
 * - Integer : le numéro d'un champ dans une ligne.<br/>
 * - String : la valeur du champ sous forme de String UTF-8 
 * (valeur par défaut de la plateforme).<br/>
 * et la description du fichier.<br/>
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
 * @since 21 juil. 2014
 *
 */
public interface IFournisseurFichier {

	
	
	/**
	 * Fournit la ligne pI du fichier importé 
	 * (telle qu'elle est stockée dans 
	 * <code><b>this.fichierImporteMap</b></code>, 
	 * donc avec l'encodage par défaut de la plateforme UTF-8).<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 *
	 * @param pI : int : numéro (1-based) de la ligne du fichier.<br/>
	 * 
	 * @return : String : pI-ème ligne du fichier.<br/>
	 */
	String reconstituerLigne(int pI);
	
	
	
	/**
	 * Reconstitue le fichier d'origine (HIT, DARWIN_CSV, ...) 
	 * <b>en ISO-8859-15 (LATIN9)</b>  à partir 
	 * de <code><b>this.fichierImporteMap</b></code>.<br/>
	 * <ul>
	 * <li>Encode le fichier de sortie en Latin9.</li>
	 * <li>Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _ISO-8859-15.extension.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 * 
	 * @return : File : Le fichier généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	File reconstituerFichierLatin9() throws IOException;
	
	
	
	/**
	 * Reconstitue le fichier d'origine (HIT, DARWIN_CSV, ...) 
	 * <b>en ANSI (Windows-1252)</b>  à partir 
	 * de <code><b>this.fichierImporteMap</b></code>.<br/>
	 * <ul>
	 * <li>Encode le fichier de sortie en Ansi.</li>
	 * <li>Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que <code><b>this.fichierAImporter</b></code> 
	 * avec l'extension _Windows-1252.extension.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 * 
	 * @return : File : Le fichier généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	File reconstituerFichierAnsi() throws IOException;
	


	/**
	 * Reconstitue le fichier d'origine (HIT, DARWIN_CSV, ...) 
	 * <b>en UTF-8</b> à partir 
	 * de <code><b>this.fichierImporteMap</b></code>.<br/>
	 * <ul>
	 * <li>Encode le fichier de sortie en UTF-8.</li>
	 * <li>Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _UTF-8.extension.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> 
	 * est null.<br/>
	 * <br/>
	 * 
	 * @return : File : Le fichier généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	File reconstituerFichierUtf8() throws IOException;

	

	
	/**
	 * Reconstitue le fichier d'origine (HIT, DARWIN_CSV, ...) à partir 
	 * de la SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt; 
	 * <code><b>this.fichierImporteMap</b></code>
	 * encapsulant le fichier (HIT, DARWIN_CSV, ...) importé avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro d'ordre de la ligne dans le fichier.</li>
	 * <li>SortedMap&lt;Integer, String&gt; avec :</li>
	 * <ul>
	 * <li>Integer : le numéro d'un champ dans une ligne.</li>
	 * <li>String : la valeur du champ sous forme de String encodée 
	 * avec l'encodage par défaut de la plateforme (UTF-8).</li>
	 * </ul>
	 * </ul>
	 * <ul>
	 * <li>Encode le fichier de sortie en pCharset.</li>
	 * <li>Encode automatiquement le fichier de sortie en UTF-8 
	 * si pCharset est null.</li>
	 * <li>Génère automatiquement le fichier de sortie dans le 
	 * même répertoire que this.fichierAImporter 
	 * avec l'extension _charset.extension 
	 * si pFile est null.</li>
	 * </ul>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si <code><b>this.fichierImporteMap</b></code> est null.<br/>
	 * <br/>
	 *
	 * @param pFichierImporteMap : 
	 * SortedMap&lt;Integer, SortedMap&lt;Integer, String&gt;&gt;.<br/>
	 * @param pCharset : Charset.<br/>
	 * @param pLigneEntete : boolean : rajoute une en-tête csv si true.<br/>
	 * @param pFile : File : le fichier à générer.<br/>
	 * 
	 * @return : File : Le fichier généré.<br/>
	 * 
	 * @throws IOException<br/>
	 */
	File reconstituerFichier(
			SortedMap<Integer, SortedMap<Integer, String>> pFichierImporteMap
				, Charset pCharset
					, boolean pLigneEntete
						, File pFile) throws IOException;


	
} // FIN DE L'INTERFACE IFournisseurFichier.---------------------------------
