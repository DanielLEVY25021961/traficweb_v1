package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.nomenclatures;

/**
 * class IFournisseurJTableNomenclature :<br/>
 * RESPONSABILITE : AFFICHER UNE NOMENCLATURE DANS UNE JTABLE.<br/>
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
 * @since 16 juil. 2014
 *
 */
public interface IFournisseurJTableNomenclature {

	/**
	 * method getEnteteParColonne(
	 * int pI) :<br/>
	 * retourne l'en-tête de la p-Ième colonne (1-based).<br/>
	 * <br/>
	 * - retourne null si la p-Ième colonne (1-based) n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pI : int : numéro (1-based) de la colonne.<br/>
	 * 
	 * @return : String : en-tête de la p-Ième colonne (1-based).<br/>
	 */
	String getEnteteParColonne(int pI);
	

	
	/**
	 * method getValeurParLigneColonne(
	 * int pL
	 * , int pC) :<br/>
	 * retourne la valeur à la pL-ième ligne (1-based) 
	 * et pC-ième colonne (1-based).<br/>
	 * - retourne null si this.nomenclatureMap est null.<br/>
	 * ATTENTION : faire importerNomenclature(...) 
	 * AVANT d'utiliser cette méthode.<br/>
	 * <br/>
	 * - retourne null si la pL-ième ligne (1-based) n'existe pas.<br/>
	 * - retourne null si la pC-ième colonne (1-based) n'existe pas.<br/> 
	 * <br/>
	 *
	 * @param pL : int : numéro de ligne (1-based).<br/>
	 * @param pC : int : numéro de colonne (1-based).<br/>
	 * 
	 * @return : String : valeur à la pL-ième ligne (1-based) 
	 * et pC-ième colonne (1-based).<br/>
	 */
	String getValeurParLigneColonne(int pL, int pC);
	
	
	
} // FIN DE L'INTERFACE IFournisseurJTableNomenclature.----------------------
