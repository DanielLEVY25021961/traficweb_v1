package levy.daniel.application.metier.importateurs.descripteursfichiers.descripteurschamps;

/**
 * class IFournisseurCsv :<br/>
 * RESPONSABILITE : AFFICHER UNE LIGNE DE DESCRIPTION AU FORMAT CSV.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'écrire sous forme de fichier csv 
 * avec séparateur ';'.<br/>
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
public interface IFournisseurCsv {
	
	
	/**
	 * method fournirLigneEnTetesCsv() :<br/>
	 * Fournit une ligne csv avec un séparateur ';' pour les en-têtes
	 * contenant les valeurs stockées dans entetesDescriptionMap.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;colonneDebut;colonneFin;longueurCalculee; 
	 * pour un DescripteurChampHistoF07.<br/>
	 * ordreChamps;intitule;nomenclature;champJava;typeJava;aNomenclature; 
	 * pour un DescripteurChampDarwinCsv.<br/>
	 * <br/>
	 * - retourne "" si this.entetesDescriptionMap est null.<br/>
	 * - retourne "" si this.entetesDescriptionMap est vide.<br/>
	 * <br/>
	 *
	 * @return String : ligne d'en-têtes csv avec séparateur ';'.<br/>
	 */
	String fournirLigneEnTetesCsv();
	
	
	
	/**
	 * method fournirLigneValeursCsv() :<br/>
	 * Fournit une ligne csv avec un séparateur ';'
	 * contenant les valeurs de description d'un champ 
	 * stockées dans valeursDescriptionMap.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * 1;1-3;3;Numéro de Département;calé à gauche;numDepartment;
	 * Integer;false;1;3;3; pour le champ 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HistoNatF07.<br/>
	 * 2;route;Route au format Isidor (ex : A0034b1 ou A0006);
	 * route;String;false; 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier Darwin csv.<br/>
	 * <br/>
	 * - retourne "" si valeursDescriptionMap est null.<br/>
	 * - retourne "" si valeursDescriptionMap est vide.<br/>
	 * <br/>
	 *
	 * @return String : ligne csv contenant la description du champ 
	 * avec séparateur ';'.<br/>
	 */
	String fournirLigneValeursCsv();
	


} // FIN DE L'INTERFACE IFournisseurCsv.-------------------------------------
