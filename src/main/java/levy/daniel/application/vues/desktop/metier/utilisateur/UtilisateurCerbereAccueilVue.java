package levy.daniel.application.vues.desktop.metier.utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * CLASSE UtilisateurCerbereAccueilVue :<br/>
 * .<br/>
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
 * @since 1 mars 2019
 *
 */
public class UtilisateurCerbereAccueilVue extends AnchorPane {

	// ************************ATTRIBUTS************************************/

	/**
	 * marge par défaut autout d'un composant.
	 */
	public static final double MARGIN = 5d;

	/**
	 * barre de menus tout en haut de la présente vue.
	 */
	private final transient MenuBar barreMenus = new MenuBar();
	
	/**
	 * AnchorPane pour la recherche d'un objet en base.
	 */
	private final transient AnchorPane panneauRecherche = new AnchorPane();
	
	/**
	 * Label pour la zone de recherche.
	 */
	private final transient Label rechercheLabel 
		= new Label("zone de recherche : "); 
	
	/**
	 * TextField pour la recherche.
	 */
	private final transient TextField rechercheTextField 
		= new TextField();
	
	/**
	 * Button pour la recherche.
	 */
	private final transient Button rechercheButton = new Button("GO");
	
	/**
	 * AnchorPane pour la liste des objets en base.
	 */
	private final transient AnchorPane panneauListeObjets = new AnchorPane();
	
	/**
	 * label pour le titre de la liste des objets.
	 */
	private final transient Label listeLabel 
		= new Label("liste des utilisateurs en base : ");
	
	/**
	 * UtilisateurCerbereListAffichageVue (AnchorPane) 
	 * encapsulant un TableView pour l'affichage 
	 * de tous les objets en base.
	 */
	private final transient UtilisateurCerbereListAffichageVue listeobjetsAnchorPane 
		= new UtilisateurCerbereListAffichageVue();
	
	/**
	 * GridPane pour placer les composants dans la présente vue.<br/>
	 */
	private final transient GridPane gridPane = new GridPane();

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereAccueilVue.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereAccueilVue() {
		
		super();
		
		this.initialize();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * initialise le présent AnchorPane.<br/>
	 * <ul>
	 * <li></li>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 */
	private void initialize() {
		
		/* configure la présente vue (AnchorPane). */
		this.configurerVue();
		
		/* fixe les distances entre le contenu (this.gridPane) 
		 * et le présent conteneur AnchorPane. */
		AnchorPane.setTopAnchor(this.gridPane, 10D);
		AnchorPane.setLeftAnchor(this.gridPane, 10D);
		AnchorPane.setBottomAnchor(this.gridPane, 10D);
		AnchorPane.setRightAnchor(this.gridPane, 10D);
		
		this.setPrefWidth(USE_COMPUTED_SIZE);
		this.setPrefHeight(USE_COMPUTED_SIZE);
		
		/* ajoute une classe CSS au présent AnchorPane 
		 * (les AnchorPanes n'ont pas de classe CSS par défaut). */
		this.getStyleClass().add("anchorpane");

	} // Fin de initialize().______________________________________________
	
	
	
	/**
	 * configure la présente vue (AnchorPane).<br/>
	 * <ul>
	 * <li>configure tous les composants (labels, TableView, ...).</li>
	 * <li>positionne chaque composants dans un Constraints de GridPane.</li>
	 * <li>ajoute chaque composant dans le GridPane.</li>
	 * <li>configure this.gridPane.</li>
	 * <li>encapsule this.gridPane dans la présente VUE (AnchorPane).</li>
	 * </ul>
	 */
	private void configurerVue() {
		
		/* configure tous les composants (labels, TableView, ...). */
		this.configurerComposants();
		
		/* positionne chaque composants dans un Constraints de GridPane. */
		this.positionnerComposantsDansConstraints();
		
		/* ajoute chaque composant dans le GridPane. */
		this.ajouterComposantsDansGridPane();
		
		/* configure this.gridPane. */
		this.configurerGridPaneEnfant();
		
		/* encapsule this.gridPane dans la présente VUE (AnchorPane). */
		this.encapsulerGridPaneEnfant();
		
	} // Fin de configurerComposants().____________________________________
	
	
	
	/**
	 * configure tous les composants (labels, TableView, ...).<br/>
	 * <ul>
	 * <li>configure la barre des menus.</li>
	 * <li>configure le panneau de recherche.</li>
	 * </ul>
	 */
	private void configurerComposants() {
		
		/* configure la barre des menus. */
		this.configurerBarreMenus();
		
		/* configure le panneau de recherche. */
		this.configurerPanneauRecherche();
		
		/* configure le panneau listant les objets en base. */
		this.configurerPanneauListeObjets();
		
	} // Fin de configurerComposants().____________________________________

	
	
	/**
	 * configure la barre des menus.<br/>
	 */
	private void configurerBarreMenus() {
		
		this.barreMenus.setPrefWidth(1200d);
		this.barreMenus.setPrefHeight(35d);
		
		final Menu menuCreer = new Menu("créer un utilisateur");
		final Menu menuExporterCsv 
			= new Menu("exporter la liste des utilisateurs en CSV");
		
		this.barreMenus.getMenus().addAll(menuCreer, menuExporterCsv);
		
	} // Fin de configurerBarreMenus().____________________________________
	
	
	
	/**
	 * configure le panneau de recherche.<br/>
	 */
	private void configurerPanneauRecherche() {
		
		this.panneauRecherche.setPrefWidth(1200d);
		this.panneauRecherche.setPrefHeight(50d);
		
		final GridPane gridPaneRecherche = new GridPane();
		
		this.rechercheLabel.setPrefWidth(200d);
		this.rechercheLabel.setPrefHeight(40d);
		
		this.rechercheTextField.setPrefWidth(800d);
		this.rechercheTextField.setPrefHeight(40d);
		
		GridPane.setConstraints(
				this.rechercheLabel
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.rechercheTextField				
					, 1, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.rechercheButton				
					, 2, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		gridPaneRecherche.getChildren().addAll(this.rechercheLabel);
		gridPaneRecherche.getChildren().addAll(this.rechercheTextField);
		gridPaneRecherche.getChildren().addAll(this.rechercheButton);
		
		AnchorPane.setTopAnchor(gridPaneRecherche, MARGIN);
		AnchorPane.setLeftAnchor(gridPaneRecherche, MARGIN);
		AnchorPane.setBottomAnchor(gridPaneRecherche, MARGIN);
		AnchorPane.setRightAnchor(gridPaneRecherche, MARGIN);
		
		this.panneauRecherche.getChildren().add(gridPaneRecherche);
		
		/* change la couleur de fond du Panneau de recherche. */
		/* surcharge la couleur dans la CSS. */
		/* #ececec */
		/* #C0C0C0 */
		this.panneauRecherche.setStyle("-fx-background-color: #e4f0f5;");
		
	} // Fin de configurerPanneauRecherche().______________________________
	
	
	
	/**
	 * configure le panneau listant les objets en base.<br/>
	 */
	private void configurerPanneauListeObjets() {
		
		this.panneauListeObjets.setPrefWidth(1200d);
		this.panneauListeObjets.setPrefHeight(1000d);
		
		final GridPane gridPaneListeObjets = new GridPane();
		
		this.listeLabel.setPrefWidth(1200d);
		this.listeLabel.setPrefHeight(20d);
		
		GridPane.setConstraints(
				this.listeLabel
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.listeobjetsAnchorPane			
					, 0, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		gridPaneListeObjets.getChildren().addAll(this.listeLabel);
		gridPaneListeObjets.getChildren().addAll(this.listeobjetsAnchorPane);
		
		AnchorPane.setTopAnchor(gridPaneListeObjets, 10d);
		AnchorPane.setLeftAnchor(gridPaneListeObjets, 10d);
		AnchorPane.setBottomAnchor(gridPaneListeObjets, 10d);
		AnchorPane.setRightAnchor(gridPaneListeObjets, 10d);
		
		this.panneauListeObjets.getChildren().add(gridPaneListeObjets);

		/* change la couleur de fond du Panneau listant les objets. */
		/* surcharge la couleur dans la CSS. */
		/* #ececec */
		/* #C0C0C0 */
		this.panneauListeObjets.setStyle("-fx-background-color: #ddffcc;");
		
	} // Fin de configurerPanneauListeObjets().____________________________
	
	
	
	/**
	 * positionne chaque composants dans un Constraints de GridPane.<br/>
	 */
	private void positionnerComposantsDansConstraints() {
		
		GridPane.setConstraints(
				this.barreMenus
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.panneauRecherche
					, 0, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.panneauListeObjets
					, 0, 2
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
	} // Fin de positionnerComposantsDansConstraints().____________________
	

	
	/**
	 * ajoute chaque composant dans le GridPane.<br/>
	 */
	private void ajouterComposantsDansGridPane() {
		
		this.gridPane.getChildren().addAll(this.barreMenus);
		this.gridPane.getChildren().addAll(this.panneauRecherche);
		this.gridPane.getChildren().addAll(this.panneauListeObjets);
		
	} // Fin de ajouterComposantsDansGridPane().___________________________
	
	
	
	/**
	 * configure this.gridPane.<br/>
	 */
	private void configurerGridPaneEnfant() {
		
		/* change la couleur de fond du GridPane. */
		/* surcharge la couleur dans la CSS. */
		/* #ececec */
		/* #C0C0C0 */
		this.gridPane.setStyle("-fx-background-color: #ececec;");
		
		/* affiche les lignes de la grille du GridPane. */
		this.gridPane.setGridLinesVisible(false);
		
		/* ajoute une classe CSS à this.gridPane 
		 * (les GridPanes n'ont pas de classe CSS par défaut). */
		this.gridPane.getStyleClass().add("gridpane");

	} // Fin de configurerGridPaneEnfant().________________________________
	

	
	/**
	 * encapsule this.gridPane dans la présente VUE (AnchorPane).<br/>
	 */
	private void encapsulerGridPaneEnfant() {
		this.getChildren().add(this.gridPane);
	} // Fin de encapsulerGridPaneEnfant().________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereAccueilVue.--------------------------
