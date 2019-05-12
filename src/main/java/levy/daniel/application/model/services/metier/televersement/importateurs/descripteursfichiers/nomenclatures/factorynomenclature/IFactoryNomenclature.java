package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures.factorynomenclature;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;

/**
 * INTERFACE IFactoryNomenclature :<br/>
 * <p>
 * RESPONSABILITE : FOURNIR LA BONNE NOMENCLATURE (MAP 
 * ET ENSEMBLE DES VALEURS POSSIBLES) EN FONCTION 
 * DU TYPE DE FICHIER ET DU NUMERO D'ORDRE DU CHAMP 
 * DANS LA DESCRIPTION DE FICHIER.<br/>
 * Interface factorisant les comportements communs 
 * à toutes les Factory de nomenclatures.<br/>
 * </p>
 * 
 * <div>
 * <ul>
 * <li>
 * fournit pour chaque champ à valeurs contraintes (nomenclature ou lexique) 
 * d'un fichier un <b>SINGLETON</b> de la nomenclature sous forme de Map 
 * ainsi que l'ensemble de ses valeurs possibles.
 * </li>
 * <li>
 * La méthode <b><code>getClesPossiblesSet(int pNumeroOrdre)</code></b> fournit
 * l'ensemble des clés possibles en localisant le champ en fonction de son 
 * numéro d'ordre pNumeroOrdre dans la description du fichier.
 * </li>
 * <li>
 * La méthode <b><code>getNomenclatureMap(int pNumeroOrdre)</code></b> fournit
 * la nomenclature sous forme de Map en localisant le champ en fonction de son 
 * numéro d'ordre pNumeroOrdre dans la description du fichier.
 * </li>
 * </ul>
 * </div>
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
 * @since 2 août 2014
 *
 */
public interface IFactoryNomenclature {

	
	
	/**
	 * "Méthode getClesPossiblesSet(int pNumeroChamp)".<br/>
	 */
	String METHODE_GETCLESPOSSIBLESSET 
		= "Méthode getClesPossiblesSet(int pNumeroChamp)";
	
	/**
	 * "Méthode getNomenclatureMap(int pNumeroChamp)".<br/>
	 */
	String METHODE_GETNOMENCLATUREMAP 
		= "Méthode getNomenclatureMap(int pNumeroChamp)";

	
	/**
	 * Fournit les <b>valeurs possibles</b> 
	 * d'un champ à <i>valeurs contraintes</i> 
	 * (nomenclature) d'<b>ordre pNumeroChamp</b> (1-based).<br/>
	 * <br/>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * - retourne null si le champ d'ordre pNumeroChamp 
	 * n'est pas un champ à valeurs contraintes (nomenclature).<br/>
	 * - LOG.FATAL et jette une RunTimeException 
	 * si la nomenclature est déficiente (null, vide, inexistante, répertoire) 
	 * pour le champ d'ordre pNumeroChamp.<br/>
	 * <br/>
	 *
	 * @param pNumeroChamp : int : 
	 * numéro du champ dans une description de fichier (1-based).<br/>
	 * 
	 * @return : Set&lt;Integer&gt; : 
	 * ensemble des valeurs possibles pour la clé dans la nomenclature.<br/>
	 * 
	 * @throws FichierNullException : si pNomenclature est null.<br/>
	 * @throws FichierVideException  : si pNomenclature est vide.<br/>
	 * @throws FichierInexistantException  : si pNomenclature n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pNomenclature est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws Exception 
	 */
	Set<Integer> getClesPossiblesSet(int pNumeroChamp) 
					throws FichierNullException
					, FichierVideException
					, FichierInexistantException
					, FichierPasNormalException
					, IOException, Exception;
	
	
	
	/**
	 * Fournit la nomenclature d'un champ à valeurs contraintes 
	 * d'ordre pNumeroChamp sous forme de 
	 * SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>
	 * <br/>
	 * <b>SINGLETON</b>.
	 * <br/><br/>
	 * - retourne null si le champ d'ordre pNumeroChamp 
	 * n'est pas un champ à valeurs contraintes (nomenclature).<br/>
	 * - LOG.FATAL et jette une RunTimeException 
	 * si la nomenclature est déficiente (null, vide, inexistante, répertoire) 
	 * pour le champ d'ordre pNumeroChamp.<br/>
	 * <br/>
	 *
	 * @param pNumeroChamp : int : 
	 * numéro du champ dans une description de fichier.<br/>
	 * 
	 * @return : SortedMap&lt;Integer, String&gt;.<br/>
	 * 
	 * @throws FichierNullException : si pNomenclature est null.<br/>
	 * @throws FichierVideException  : si pNomenclature est vide.<br/>
	 * @throws FichierInexistantException  : si pNomenclature n'existe pas.<br/>
	 * @throws FichierPasNormalException : si pNomenclature est un répertoire.<br/>
	 * @throws IOException : si problème d'entrée/sortie.<br/>
	 * @throws Exception 
	 */
	SortedMap<Integer, String> getNomenclatureMap(int pNumeroChamp) 
					throws FichierNullException
					, FichierVideException
					, FichierInexistantException
					, FichierPasNormalException
					, IOException, Exception;
	
	
	
	/**
	 * retourne une String pour l'affichage 
	 * à la console d'une Map&lt;Integer, String&gt;.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 * 
	 *
	 * @param pMap : Map&lt;Integer, String&gt;
	 * 
	 * @return : String : String pour affichage.<br/>
	 */
	String afficherMapIntegerString(Map<Integer, String> pMap);
	
	
	
} // FIN DE LA CLASSE IFactoryNomenclature.----------------------------------
