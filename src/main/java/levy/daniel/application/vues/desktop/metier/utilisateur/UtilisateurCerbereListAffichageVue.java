package levy.daniel.application.vues.desktop.metier.utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.IUtilisateurCerbereModelObs;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.impl.UtilisateurCerbereModelObs;

/**
 * CLASSE UtilisateurCerbereListAffichageVue :<br/>
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
 * @since 27 févr. 2019
 *
 */
public class UtilisateurCerbereListAffichageVue extends AnchorPane {

	// ************************ATTRIBUTS************************************/

	/**
	 * marge par défaut autout d'un composant.
	 */
	public static final double MARGIN = 5d;
	
	/**
	 * TableView.
	 */
	private final transient TableView<IUtilisateurCerbereModelObs> tableView 
		= new TableView<IUtilisateurCerbereModelObs>();
	
	/**
	 * TableColumn pour l'ID en base.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> idTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("ID");
	
	/**
	 * TableColumn pour la civilité.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> civiliteTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Civilité");
	
	/**
	 * TableColumn pour le prénom.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> prenomTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Prénom");
	
	/**
	 * TableColumn pour le nom.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> nomTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Nom");
	
	/**
	 * TableColumn pour le numéro de téléphone.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> telTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Téléphone");
	
	/**
	 * TableColumn pour l'e-mail.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> emailTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("e-Mail");
	
	/**
	 * TableColumn pour le service.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> serviceTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Service");
	
	/**
	 * TableColumn pour l'unité.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> uniteTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Unité");
	
	/**
	 * TableColumn pourle profil.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> profilTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Profil");
	
	/**
	 * TableColumn pour la portée.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> porteeTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Portée");
	
	/**
	 * TableColumn pour la restriction.
	 */
	private final transient TableColumn<IUtilisateurCerbereModelObs, String> restrictionTableColumn 
		= new TableColumn<IUtilisateurCerbereModelObs, String>("Restriction");

	
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
		= LogFactory.getLog(UtilisateurCerbereListAffichageVue.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereListAffichageVue() {
		
		super();
		
		this.initialize();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * injecte un "MODELE" (ObservableList javafx) dans le TableView.<br/>
	 * le MODELE contient les données que la table doit afficher.<br/>
	 *
	 * @param pList : 
	 * javafx.collections.ObservableList&lt;IUtilisateurCerbereModelObs&gt; : 
	 * MODELE A AFFICHER DANS LA TABLE.<br/>
	 */
	public final void injecterModelDansTableView(
			final ObservableList<IUtilisateurCerbereModelObs> pList) {
		
		if (pList != null) {
			this.tableView.setItems(pList);
		}
		
	} // Fin de injecterModelDansTableView(...).___________________________
	

	
	/**
	 * initialise le TableView.<br/>
	 * <ul>
	 * <li>réalise le binding entre chaque TableColumn du TableView 
	 * et la Property du modèle à afficher.</li>
	 * <li>affecte les TableColumn voulus au TableView.</li>
	 * <li></li>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 */
	private void initialize() {
				
		// réalise le binding entre chaque TableColumn du TableView 
		// et la Property du modèle à afficher.
		this.binderColonnesEtModele();
		
		// affecte les TableColumn voulus au TableView.
		this.affecterColonnesATable();
		
		/**/
		this.injecterModelDansTableView(this.fournirModeleFake());
		
		/* configure la présente vue (AnchorPane). */
		this.configurerVue();
		
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
	
	} // Fin de configurerComposants().____________________________________
	
	
	
	/**
	 * configure tous les composants (labels, TableView, ...).<br/>
	 */
	private void configurerComposants() {
		
		this.tableView.setPrefWidth(1200d);
		this.tableView.setPrefHeight(400d);
		
	} // Fin de configurerComposants().____________________________________

	
	
	/**
	 * positionne chaque composants dans un Constraints de GridPane.<br/>
	 */
	private void positionnerComposantsDansConstraints() {
		
		GridPane.setConstraints(
				this.tableView
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
	} // Fin de positionnerComposantsDansConstraints().____________________
	

	
	/**
	 * ajoute chaque composant dans le GridPane.<br/>
	 */
	private void ajouterComposantsDansGridPane() {
		
		this.gridPane.getChildren().addAll(this.tableView);
		
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
	 * réalise le binding entre chaque TableColumn du TableView
	 *  et la Property du modèle à afficher.<br/>
	 */
	private void binderColonnesEtModele() {
		
		/* stipule que la colonne this.idTableColumn doit afficher la StringProperty 'id' de chaque enregistrement. */
		this.idTableColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
		this.civiliteTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCiviliteProperty());
		this.prenomTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPrenomProperty());
		this.nomTableColumn.setCellValueFactory(cellData -> cellData.getValue().getNomProperty());
		this.telTableColumn.setCellValueFactory(cellData -> cellData.getValue().getTelProperty());
		this.emailTableColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
		this.serviceTableColumn.setCellValueFactory(cellData -> cellData.getValue().getServiceProperty());
		this.uniteTableColumn.setCellValueFactory(cellData -> cellData.getValue().getUniteProperty());
		this.profilTableColumn.setCellValueFactory(cellData -> cellData.getValue().getProfilProperty());
		this.porteeTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPorteeProperty());
		this.restrictionTableColumn.setCellValueFactory(cellData -> cellData.getValue().getRestrictionProperty());
		
	} // Fin de binderColonnesEtModele().__________________________________
	

	
	/**
	 * affecte les TableColumn voulus au TableView.<br/>
	 */
	private void affecterColonnesATable() {
		
		this.tableView.getColumns().add(this.idTableColumn);
		this.tableView.getColumns().add(this.civiliteTableColumn);
		this.tableView.getColumns().add(this.prenomTableColumn);
		this.tableView.getColumns().add(this.nomTableColumn);
		this.tableView.getColumns().add(this.telTableColumn);
		this.tableView.getColumns().add(this.emailTableColumn);
		this.tableView.getColumns().add(this.serviceTableColumn);
		this.tableView.getColumns().add(this.uniteTableColumn);
		this.tableView.getColumns().add(this.profilTableColumn);
		this.tableView.getColumns().add(this.porteeTableColumn);
		this.tableView.getColumns().add(this.restrictionTableColumn);
		
	} // Fin de affecterColonnesATable().__________________________________
	

	
	/**
	 * retourne un "MODELE" fake pour simuler le stockage.<br/>
	 * Ce modèle sera affiché dans le TableView<br/>
	 *
	 * @return : ObservableList&lt;IUtilisateurCerbereModelObs&gt;.<br/>
	 */
	private ObservableList<IUtilisateurCerbereModelObs> fournirModeleFake() {
		
		final ObservableList<IUtilisateurCerbereModelObs> listFake 
			= FXCollections.observableArrayList();
		
		final IUtilisateurCerbereModelObs objet1 
			= new UtilisateurCerbereModelObs(
					"1"
					, "M.", "Rahan", "Gougniaffiel"
					, "06 07 08 09 70", "rahan.gougniaffiel@yahoo.com"
					, "CEREMA", "CEREMA/DTecITM/DIR"
					, "ADMINISTRATEUR NATIONAL", "CEREMA", "France entière");
		
		final IUtilisateurCerbereModelObs objet2 
		= new UtilisateurCerbereModelObs(
				"2"
				, "Mlle", "Stacy", "Pompounou"
				, "01 07 08 09 70", "stacy.pompounou@yahoo.com"
				, "CEREMA", "CEREMA/DTecITM/DIR/SE"
				, "ADMINISTRATEUR REGIONAL", "CEREMA", "France métropolitaine");
		
		listFake.add(objet1);
		listFake.add(objet2);
		
		return listFake;
		
	} // Fin e fournirModeleFake().________________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereListAffichageVue .-------------------
