package levy.daniel.application.vues.desktop.metier.utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * CLASSE UtilisateurCerbereAffichageVue :<br/>
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
 * @since 25 févr. 2019
 *
 */
public class UtilisateurCerbereAffichageVue extends AnchorPane {

	// ************************ATTRIBUTS************************************/

	/**
	 * label de l'ID en base.
	 */
	private final transient Label idLabel = new Label("ID en base : ");
	
	/**
	 * zone de texte de l'ID en base.
	 */
	private final transient TextField idText = new TextField();
	
	/**
	 * label pour la civilité.
	 */
	private final transient Label civiliteLabel = new Label("Civilité : ");
	
	/**
	 * zone de texte de la civilite.
	 */
	private final transient TextField civiliteText = new TextField();
	
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
		= LogFactory.getLog(UtilisateurCerbereAffichageVue.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereAffichageVue() {
		
		super();
		
		this.configurerVue();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * .<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li>ajoute le gridPane au présent AnchorPane.</li>
	 * </ul>
	 */
	private void configurerVue() {
		
		/* configure le label pour l'ID. */
		this.configurerIdLabel();
		
		/* configure la zone de texte pour l'ID. */
		this.configurerIdText();
		
		/* configure le label pour la civilite. */
		this.configurerCiviliteLabel();
		
		/* configure la zone de texte pour la civilite. */
		this.configurerCiviliteText();
		
		GridPane.setConstraints(
				this.idLabel
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.idText
					, 1, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.civiliteLabel
					, 0, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.civiliteText
					, 1, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		this.gridPane.getChildren().addAll(this.idLabel);
		this.gridPane.getChildren().addAll(this.idText);
		this.gridPane.getChildren().addAll(this.civiliteLabel);
		this.gridPane.getChildren().addAll(this.civiliteText);
		
		/* ajoute le gridPane au présent AnchorPane. */
		this.getChildren().add(this.gridPane);
		
		AnchorPane.setTopAnchor(this.gridPane, 0D);
		AnchorPane.setLeftAnchor(this.gridPane, 0D);
		AnchorPane.setBottomAnchor(this.gridPane, 0D);
		AnchorPane.setRightAnchor(this.gridPane, 0D);
		
		this.setPrefWidth(USE_COMPUTED_SIZE);
		this.setPrefHeight(USE_COMPUTED_SIZE);
		
		
	} // Fin de configurerVue().___________________________________________
	
	
	
	/**
	 * configure le label pour l'ID.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerIdLabel() {
		
		this.idLabel.setPrefWidth(400);
		this.idLabel.setPrefHeight(100);
		this.idLabel.setAlignment(Pos.CENTER_RIGHT);
		this.idLabel.setStyle("-fx-border-color: black;-fx-background-color: WHITE;");
		
	} // Fin de configurerIdLabel().________________________________________
	
	
	
	/**
	 * configure la zone de texte pour l'ID.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerIdText() {
		
		this.idText.setPrefWidth(400);
		this.idText.setPrefHeight(100);
		this.idText.setDisable(true);
		this.idText.setAlignment(Pos.CENTER_LEFT);
		this.idText.setStyle("-fx-border-color: black;-fx-background-color: WHITE;");
		
		this.idText.setText("137");
		
	} // Fin de configurerIdText().________________________________________
	
	
	
	/**
	 * configure le label pour la civilite.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerCiviliteLabel() {
		
		this.civiliteLabel.setPrefWidth(400);
		this.civiliteLabel.setPrefHeight(100);
		this.civiliteLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.civiliteLabel.setStyle("-fx-border-color: black;-fx-background-color: WHITE;");
		
	} // Fin de configurerCiviliteLabel()._________________________________
	
	
	
	/**
	 * configure la zone de texte pour la civilite.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerCiviliteText() {
		
		this.civiliteText.setPrefWidth(400);
		this.civiliteText.setPrefHeight(100);
		this.civiliteText.setDisable(false);
		this.civiliteText.setAlignment(Pos.CENTER_LEFT);
		this.civiliteText.setStyle("-fx-border-color: black;-fx-background-color: WHITE;");
		
		this.civiliteText.setText("Mlle");
		
	} // Fin de configurerCiviliteText().__________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereAffichageVue.------------------------
