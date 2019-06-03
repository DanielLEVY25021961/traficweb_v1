package levy.daniel.application.model.services.metier.televersement.importateurs.importeurs;

import levy.daniel.application.apptechnic.exceptions.technical.impl.MapNullException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MapVideException;

/**
 * INTERFACE IFournisseurJTableImporteur :<br/>
 * RESPONSABILITE : AFFICHER LE FICHIER IMPORTE 
 * (HIT, HISTO_F07, DARWIN_CSV, ...) DANS UNE JTABLE.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'afficher dans une JTable.<br/>
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
 * @since 30 juin 2014
 *
 */
public interface IFournisseurJTableImporteur {


	
	/**
	* Getter de l'en-tête de la p-ième colonne (1-based).<br/>
	* Methode utile pour l'affichage dans une JTable par exemple.<br/>
	* <br/>
	* ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	* - retourne null si this.importateurDescription est null.<br/>
	* - retourne null si this.importateurDescription.getDescriptionChamp(pI) 
	* est null, c'est à dire si la colonne pI n'existe pas.<br/
	* <br/>
	* 
	* @param pI : int : le numéro (1-based) 
	* de la colonne dans le fichier dont on veut connaitre l'en-tête.<br/>
	* 
	* @return String : en-tête pour ce champ à la
	* p-ième colonne (1-based).<br/>
	* 
	 * @throws MapNullException lorsque : la Map 'specificationChampsMap' 
	 * est null dans l'ImportateurDescription.<br/>
	 * @throws MapVideException lorsque : la Map 'specificationChampsMap' 
	 * est vide dans l'ImportateurDescription.<br/>
	*/
	String fournirEnteteparColonne(
			int pI) throws MapVideException, MapNullException;

	
	
	/**
	 * Getter du contenu (valeur) de la p-ième colonne (1-based) 
	 * à la l-ième ligne.<br/>
	 * si pL=0, retourne un en-tête.<br/>
	 * Methode utile pour l'affichage dans une JTable par exemple.<br/>
	 * <br/>
	 * ATTENTION : FAIRE importer(File) AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si this.fichierImporteMap est null.<br/>
	 * - retourne null si la ligne pL n'existe pas.<br/>
	 * <br/>
	 * <br/>
	 * 
	 * @param pL : int : le numéro (1-based) 
	 * de la ligne dans le fichier dont on veut connaitre le contenu.<br/>
	 * @param pI : int : le numéro (1-based) 
	 * de la colonne dans le fichier dont on veut connaitre le contenu.<br/>
	 * 
	 * @return String : contenu pour ce champ à la
	 * l-ième ligne (1-based) et la p-ième colonne (1-based).<br/>
	 */
	String fournirValeurparLigneColonne(
		int pL
			, int pI);
	
	

} // FIN DE L'INTERFACE IFournisseurJTableImporteur.-------------------------
