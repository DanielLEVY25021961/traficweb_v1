package levy.daniel.application.model.services.metier.televersement.importateurs.descripteursfichiers.descripteurschamps;

/**
 * class IFournisseurCsv :<br/>
 * <p>
 * RESPONSABILITE : AFFICHER UNE LIGNE DE DESCRIPTION AU FORMAT CSV.<br/>
 * Interface factorisant les méthodes garantissant 
 * qu'un objet qui l'implémente saura s'écrire sous forme de fichier csv 
 * avec séparateur ';'.<br/>
 * </p>
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
 * @since 29 juin 2014
 *
 */
public interface IFournisseurCsv {
	
	
	/**
	 * Fournit une ligne csv avec un séparateur ';' pour les en-têtes
	 * contenant les valeurs stockées dans entetesDescriptionMap.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code>ordreChamps;colonnes;longueur;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;aLexique;colonneDebut;colonneFin;longueurCalculee;</code> 
	 * pour un DescripteurChampHistoF07.<br/>
	 * <code>ordreChamps;intitule;nomenclature;champJava;typeJava;
	 * aNomenclature;aLexique;</code> 
	 * pour un DescripteurChampDarwinCsv.<br/>
	 * <br/>
	 * - retourne "" si <code>this.entetesDescriptionMap</code> est null.<br/>
	 * - retourne "" si <code>this.entetesDescriptionMap</code> est vide.<br/>
	 * <br/>
	 *
	 * @return String : ligne d'en-têtes csv avec séparateur ';'.<br/>
	 */
	String fournirLigneEnTetesCsv();
	
	
	
	/**
	 * Fournit une ligne csv avec un séparateur ';'
	 * contenant les valeurs de description d'un champ 
	 * stockées dans valeursDescriptionMap.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * <code>1;1-3;3;Numéro de Département;calé à gauche;numDepartment;
	 * Integer;false;false;1;3;3;</code> pour le champ 'Numéro de Département' 
	 * (1ère ligne) de la description d'un HISTO_F07.<br/>
	 * <code>2;route;Route au format Isidor (ex : A0034b1 ou A0006);
	 * route;String;false;false;</code> 
	 * pour le champ 'route' 
	 * (2ème ligne) dans la description du fichier DARWIN_CSV.<br/>
	 * <br/>
	 * - retourne "" si <code>this.valeursDescriptionMap</code> est null.<br/>
	 * - retourne "" si <code>this.valeursDescriptionMap</code> est vide.<br/>
	 * <br/>
	 * NECESSITE D'AVOIR FAIT desc.lireChamp(pTokens) 
	 * APRES L'INSTANCIATION DU Descripteur 
	 * pour que <code>this.valeursDescriptionMap</code> SOIT INSTANCIEE.<br/>
	 * <br/>
	 *
	 * @return String : ligne csv contenant la description du champ 
	 * avec séparateur ';'.<br/>
	 */
	String fournirLigneValeursCsv();
	


} // FIN DE L'INTERFACE IFournisseurCsv.-------------------------------------
