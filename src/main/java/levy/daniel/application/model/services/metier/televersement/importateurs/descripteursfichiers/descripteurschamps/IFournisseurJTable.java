package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

/**
 * class IFournisseurJTable :<br/>
 * RESPONSABILITE : AFFICHER UNE LIGNE DE DESCRIPTION DANS UNE JTABLE.<br/>
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
 * @since 29 juin 2014
 *
 */
public interface IFournisseurJTable {
	
		
	/**
	* method getEnteteparColonne(
	* int pI) :<br/>
	* Getter de l'en-tête de la p-ième colonne (1-based).<br/>
	* Methode utile pour l'affichage dans une JTable par exemple.<br/>
	* <br/>
	* Par exemple :<br/>
	* desc.getEnteteparColonne(2) == 'colonnes' 
	* pour un DescripteurChampHistoF07.<br/>
	* desc.getEnteteparColonne(2 )== 'intitule' 
	* pour un DescripteurChampDarwinCsv.<br/>
	* <br/>
	* - retourne null si entetesDescriptionMap est null.<br/>
	* - retourne null si la p-ième colonne (1-based) n'existe pas.<br/>
	* <br/>
	* 
	* @param pI : int : le numéro (1-based) 
	* de la colonne dans le fichier de 
	* description dont on veut connaitre l'en-tête.<br/>
	* 
	* @return String : en-tête pour ce champ 
	* (ligne du fichier de description) à la
	* p-ième colonne (1-based).<br/>
	*/
	String getEnteteparColonne(
		final int pI);
	
	
	
	/**
	 * method getValeurparColonne(
	 * int pI) :<br/>
	 * Getter du contenu (valeur) de la p-ième colonne (1-based).<br/>
	 * Methode utile pour l'affichage dans une JTable par exemple.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * desc.getValeurparColonne(2) == '1-3' pour le champ 
	 * 'Numéro de Département' (1ère ligne) de la description 
	 * d'un HistoNatF07.<br/>
	 * desc.getValeurparColonne(2) == 'route' pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne null si valeursDescriptionMap est null.<br/>
	 * - retourne null si la p-ième colonne (1-based) n'existe pas.<br/>
	 * <br/>
	 * 
	 * @param pI : int : le numéro (1-based) 
	 * de la colonne dans le fichier de 
	 * description dont on veut connaitre le contenu.<br/>
	 * 
	 * @return String : contenu pour ce champ 
	 * (ligne du fichier de description) à la
	 * p-ième colonne (1-based).<br/>
	 */
	String getValeurparColonne(
		final int pI);
	


} // Fin de IFournisseurJTable.----------------------------------------------