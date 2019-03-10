package levy.daniel.application.vues.desktop.metier.utilisateur;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import levy.daniel.application.controllers.desktop.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.IUtilisateurCerbereModelObs;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.UtilisateurCerbereConvertisseurObservableDTO;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.impl.ZoneRechercheModelObs;

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
	 * Menu de premier niveau pour la CREATION de l'objet métier.
	 */
	private final transient Menu menuCreer = new Menu("créer");
	
	/**
	 * Sous-Menu de second niveau pour la CREATION de l'objet métier.
	 */
	private final transient MenuItem menuItemCreer 
		= new MenuItem("créer un utilisateur");
	
	/**
	 * AnchorPane pour la recherche d'un objet en base.
	 */
	private final transient AnchorPane panneauRecherche = new AnchorPane();
	
	/**
	 * Label pour la zone de recherche.
	 */
	private final transient Label rechercheLabel 
		= new Label("zone de recherche instantanée : "); 
	
	/**
	 * TextField (zone de texte) pour la recherche.
	 */
	private final transient TextField rechercheTextField 
		= new TextField();
	
	/**
	 * Button de reset pour la recherche.
	 */
	private final transient Button rechercheResetButton = new Button("Clear");
	
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
	 * CONTROLLER pour l'objet métier.<br/>
	 * passé par l'application dans le CONSTRUCTEUR de la présente 
	 * car instancié par SPRING dans le THREAD MAIN <i>avant</i> 
	 * le lancement du THREAD JAVAFX.<br/>
	 */
	private final transient IUtilisateurCerbereController utilisateurCerbereController;
	
	/**
	 * .<br/>
	 */
	private final transient ZoneRechercheModelObs zoneRechercheModelObs 
		= new ZoneRechercheModelObs();
	
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
	 * 
	 * @throws Exception 
	 */
	public UtilisateurCerbereAccueilVue() throws Exception {
		this(null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR A CONTROLLER.<br/>
	 * 
	 * @param pUtilisateurCerbereController : 
	 * IUtilisateurCerbereController
	 * 
	 * @throws Exception 
	 */
	public UtilisateurCerbereAccueilVue(
			final IUtilisateurCerbereController pUtilisateurCerbereController) 
					throws Exception {
		
		super();
		
		this.utilisateurCerbereController = pUtilisateurCerbereController;
		
		this.initialize();
		
	} // Fin de CONSTRUCTEUR A CONTROLLER._________________________________
	

	
	/**
	 * initialise le présent AnchorPane.<br/>
	 * <ul>
	 * <li>initialise le MODELE à afficher.</li>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		/* initialise le MODELE à afficher. */
		this.initialiserModel();
		
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
	 * initialise le MODELE à afficher.<br/>
	 * <ul>
	 * <li>récupère la liste des objets métier dans le stockage 
	 * auprès du CONTROLLER métier.</li>
	 * <li>convertit la liste d'objets metier en 
	 * liste de DTO Observable.</li>
	 * <li>injecte la liste de DTO Observable 
	 * dans le TableView pour affichage.</li>
	 * </ul>
	 * @throws Exception 
	 */
	private void initialiserModel() throws Exception {
		
		if (this.utilisateurCerbereController != null) {
			
			/* récupère la liste des objets métier dans le stockage 
			 * auprès du CONTROLLER métier. */
			final List<IUtilisateurCerbere> listeObjets 
				= this.utilisateurCerbereController.findAll();
					
			/* convertit la liste d'objets metier en liste 
			 * de DTO Observable. */
			final ObservableList<IUtilisateurCerbereModelObs> listDTO 
				= UtilisateurCerbereConvertisseurObservableDTO
					.convertirListObjetsEnObservableList(listeObjets);
			
			/* injecte la liste de DTO Observable 
			 * dans le TableView pour affichage. */
			this.listeobjetsAnchorPane.injecterModelDansTableView(listDTO);
			
		}
		
	} // Fin de initialiserModel().________________________________________
	
	
	
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
	 * <ul>
	 * <li>dimensionne la barre de menus.</li>
	 * <li>ajoute un Listener à this.menuItemCreer chargé d'ouvrir la VUE de CREATION.</li>
	 * <li>ajoute les menuItems à leurs Menus.</li>
	 * <li>ajoute les Menus à la MenuBar.</li>
	 * </ul>
	 */
	private void configurerBarreMenus() {
		
		/* dimensionne la barre de menus. */
		this.barreMenus.setPrefWidth(1200d);
		this.barreMenus.setPrefHeight(35d);
		
		/* ajoute un Listener à this.menuItemCreer chargé d'ouvrir la VUE de CREATION. */
		this.ajouterListenerAMenuItemCreer();
		
		/* ajoute les menuItems à leurs Menus. */
		this.menuCreer.getItems().add(this.menuItemCreer);
		
		final Menu menuExporterCsv 
			= new Menu("exporter la liste des utilisateurs en CSV");
		
		/* ajoute les Menus à la MenuBar. */
		this.barreMenus.getMenus().addAll(this.menuCreer, menuExporterCsv);
		
	} // Fin de configurerBarreMenus().____________________________________
	

	
	/**
	 * ajoute un Listener à this.menuItemCreer 
	 * chargé d'ouvrir la VUE de CREATION.<br/>
	 * <br/>
	 */
	private void ajouterListenerAMenuItemCreer() {
		
		this.menuItemCreer.setOnAction(
				new EventHandler<ActionEvent>() {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void handle(
					final ActionEvent pEvent) {
				
				/* instancie un nouveau THEATRE (stage). */
				final Stage stage = new Stage();
								
				/* Instancie une VUE CREATION. */
				UtilisateurCerbereCreationVue vueCreation 
					= new UtilisateurCerbereCreationVue(
							stage, UtilisateurCerbereAccueilVue.this);
				
				final Scene scene = new Scene(vueCreation);
			       
		        /* ajoute la feuille de style à la Scene. */
		        scene.getStylesheets().add("static/css/dan_style.css");
		        
		        /* passe la SCENE au THEATRE (stage). */
		        stage.setScene(scene);
		        
		        /* affiche le THEATRE. */
		        stage.show();
				
				
			} // Fin de handle(...).__________
			
		}); // Fin de new EventHandler._______________
		
	} // Fin de ajouterListenerAMenuItemCreer().___________________________
	
	
	
	/**
	 * configure le panneau de recherche.<br/>
	 * <ul>
	 * <li></li>
	 * <li>ajoute un ChangeListener sur la textProperty 
	 * de la zone de recherche.</li>
	 * <li>ajoute un EventHandler sur le bouton 
	 * this.rechercheResetButton.</li>
	 * </ul>
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
				this.rechercheResetButton				
					, 2, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		gridPaneRecherche.getChildren().addAll(this.rechercheLabel);
		gridPaneRecherche.getChildren().addAll(this.rechercheTextField);
		gridPaneRecherche.getChildren().addAll(this.rechercheResetButton);
		
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
		
		/* ajoute un ChangeListener sur la textProperty 
		 * de la zone de recherche. */
		this.ajouterListenerAZoneRecherche();
		
		/* ajoute un EventHandler sur le bouton this.rechercheResetButton. */
		this.ajouterListenerARechercheResetButton();
		
	} // Fin de configurerPanneauRecherche().______________________________
	
	
	
	/**
	 * ajoute un ChangeListener sur la textProperty 
	 * de la zone de recherche.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void ajouterListenerAZoneRecherche() {
		
		this.rechercheTextField.textProperty()
			.addListener(new ChangeListener<String>() {
				
				/* récupération du CONTROLLER. */
				private IUtilisateurCerbereController 
							utilisateurCerbereControllerLocal 
				= UtilisateurCerbereAccueilVue.this
					.getUtilisateurCerbereController();

				/**
				 * {@inheritDoc}
				 */
				@Override
				public void changed(
						final ObservableValue<? extends String> pObservable
							, final String pOldValue
								, final String pNewValue) {

					List<IUtilisateurCerbere> resultat = null;
					
					try {
						
						/* recherche rapide dans la base. */
						resultat 
							= this.utilisateurCerbereControllerLocal
								.rechercherRapide(pNewValue);
						
						/* convertit la liste d'objets metier resultat 
						 * en liste de DTO Observable. */
						final ObservableList<IUtilisateurCerbereModelObs> listDTO 
							= UtilisateurCerbereConvertisseurObservableDTO
								.convertirListObjetsEnObservableList(resultat);
						
						/* injecte la liste de DTO Observable 
						 * dans le TableView pour affichage. */
						UtilisateurCerbereAccueilVue.this
							.getListeobjetsAnchorPane()
								.injecterModelDansTableView(listDTO);
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
				} // Fin de changed(...)._____________________
							
			}); // Fin de new ChangeListener.________________________
		
	} // Fin de ajouterListenerAZoneRecherche().___________________________
	

	
	/**
	 * ajoute un EventHandler sur le bouton this.rechercheResetButton.<br/>
	 * <ul>
	 * <li>repasse le contenu de la zone de recherche à blank ("") 
	 * lors d'un appui sur le bouton de RESET.</li>
	 * <li>Il est indispensable de passer le contenu de la zone de recherche 
	 * à blank et pas à null du fait de la concaténation 
	 * avec des JOKERS dans le DAO. </li>
	 * </ul>
	 */
	private void ajouterListenerARechercheResetButton() {
		
		this.rechercheResetButton.setOnAction(
				new EventHandler<ActionEvent>() {
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void handle(
					final ActionEvent pEvent) {
				
				/* repasse le contenu de la zone de recherche à blank ("") 
				 * lors d'un appui sur le bouton de RESET.*/
				UtilisateurCerbereAccueilVue.this
					.getRechercheTextField()
						.setText("");
				
			} // Fin de handle(...).__________
			
		}); // Fin de new EventHandler<ActionEvent>().____
		
	} // Fin de ajouterListenerARechercheResetButton().____________________
	

	
	/**
	 * configure le panneau listant les objets en base.<br/>
	 */
	private void configurerPanneauListeObjets() {
		
		this.panneauListeObjets.setPrefWidth(1200d);
		this.panneauListeObjets.setPrefHeight(400d);
		
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


	
	/**
	 * Getter du CONTROLLER pour l'objet métier.<br/>
	 * passé par l'application dans le CONSTRUCTEUR de la présente 
	 * car instancié par SPRING dans le THREAD MAIN <i>avant</i> 
	 * le lancement du THREAD JAVAFX.<br/>
	 *
	 * @return this.utilisateurCerbereController : 
	 * IUtilisateurCerbereController.<br/>
	 */
	public final IUtilisateurCerbereController 
								getUtilisateurCerbereController() {
		return this.utilisateurCerbereController;
	} // Fin de getUtilisateurCerbereController()._________________________


	
	/**
	 * Getter du UtilisateurCerbereListAffichageVue (AnchorPane) 
	 * encapsulant un TableView pour l'affichage 
	 * de tous les objets en base.
	 *
	 * @return this.listeobjetsAnchorPane : 
	 * UtilisateurCerbereListAffichageVue.<br/>
	 */
	public final UtilisateurCerbereListAffichageVue 
										getListeobjetsAnchorPane() {
		return this.listeobjetsAnchorPane;
	} // Fin de getListeobjetsAnchorPane().________________________________


	
	/**
	 * Getter de la TextField (zone de texte) pour la recherche.
	 *
	 * @return this.rechercheTextField : 
	 * TextField.<br/>
	 */
	public final TextField getRechercheTextField() {
		return this.rechercheTextField;
	} // Fin de getRechercheTextField().___________________________________ 

		
	
} // FIN DE LA CLASSE UtilisateurCerbereAccueilVue.--------------------------
