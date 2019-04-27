package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

import java.io.IOException;
import java.util.Set;
import java.util.SortedMap;

import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierPasNormalException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideException;

/**
 * class IFactoryNomenclature :<br/>
 * RESPONSABILITE : UTILISER LA BONNE INSTANCE DE IMPORTEURNOMENCLATURE 
 * ET FOURNIR LA NOMENCLATURE SOUS FORME DE MAP 
 * ET L'ENSEMBLE DES VALEURS POSSIBLES.<br/>
 * Interface factorisant les comportements communs 
 * à toutes les Factory de nomenclatures.<br/>
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
	 * method getClesPossiblesSet(
	 * int pNumeroChamp) :<br/>
	 * Fournit les valeurs possibles d'un champ 
	 * pNumeroChamp à nomenclature.<br/>
	 * <br/>
	 * - LOG.FATAL et jette une RunTimeException 
	 * si la nomenclature est absente 
	 * pour le champ pNumeroChamp.<br/>
	 * <br/>
	 *
	 * @param pNumeroChamp : int : 
	 * numéro du champ dans une description de fichier.<br/>
	 * 
	 * @return : Set&lt;Integer&gt; : ensemble des valeurs possibles.<br/>
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
	 * method getNomenclatureMap(
	 * int pNumeroChamp) :<br/>
	 * Fournit la nomenclature d'un champ 
	 * pNumeroChamp à nomenclature sous forme de 
	 * SortedMap&lt;Integer, String&gt; triée avec : <br/>
	 * - Integer : la clé dans la nomenclature.<br/>
	 * - String : le libellé dans la nomenclature.<br/>.<br/>
	 * <br/>
	 * - LOG.FATAL et jette une RunTimeException 
	 * si la nomenclature est absente 
	 * pour le champ pNumeroChamp.<br/>
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
	
	
	
} // FIN DE LA CLASSE IFactoryNomenclature.----------------------------------
