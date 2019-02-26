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
	 * largeur preferred de tous les labels de l'IHM.<br/>
	 * 200d.<br/>
	 */
	public static final double LARGEUR_LABELS = 150d;
	
	/**
	 * hauteur preferred de tous les labels et zones de texte de l'IHM.<br/>
	 * 50d.<br/>
	 */
	public static final double HAUTEUR_LABELS_TEXTES = 50d;
	
	/**
	 * "-fx-border-color: black;-fx-background-color: WHITE;".
	 */
	public static final String BORDER_TEXTES 
		= "-fx-border-color: black;-fx-background-color: WHITE;";
	
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
	 * label pour le prénom.
	 */
	private final transient Label prenomLabel = new Label("Prénom : ");
	
	/**
	 * zone de texte du prénom.
	 */
	private final transient TextField prenomText = new TextField();
	
	/**
	 * label pour le nom.
	 */
	private final transient Label nomLabel = new Label("Nom : ");
	
	/**
	 * zone de texte du nom.
	 */
	private final transient TextField nomText = new TextField();
	
	/**
	 * label pour le tel.
	 */
	private final transient Label telLabel 
		= new Label("Numéro de Téléphone : ");
	
	/**
	 * zone de texte du tel.
	 */
	private final transient TextField telText = new TextField();
	
	/**
	 * label pour le email.
	 */
	private final transient Label emailLabel 
		= new Label("e-mail : ");
	
	/**
	 * zone de texte du email.
	 */
	private final transient TextField emailText = new TextField();
	
	/**
	 * label pour le service.
	 */
	private final transient Label serviceLabel 
		= new Label("Service : ");
	
	/**
	 * zone de texte du service.
	 */
	private final transient TextField serviceText = new TextField();
	
	/**
	 * label pour l'unité.
	 */
	private final transient Label uniteLabel 
		= new Label("Unité : ");
	
	/**
	 * zone de texte de l'unité.
	 */
	private final transient TextField uniteText = new TextField();
	
	/**
	 * label pour le profil.
	 */
	private final transient Label profilLabel 
		= new Label("PROFIL : ");
	
	/**
	 * zone de texte pour le profil.
	 */
	private final transient TextField profilText = new TextField();
	
	/**
	 * label pour la portée.
	 */
	private final transient Label porteeLabel 
		= new Label("Portée : ");
	
	/**
	 * zone de texte pour la portée.
	 */
	private final transient TextField porteeText = new TextField();
	
	/**
	 * label pour la restriction.
	 */
	private final transient Label restrictionLabel 
		= new Label("Restriction : ");
	
	/**
	 * zone de texte pour la restriction.
	 */
	private final transient TextField restrictionText = new TextField();

	
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
		
		/* configure le label pour le prénom. */
		this.configurerPrenomLabel();
		
		/* configure la zone de texte pour le prénom. */
		this.configurerPrenomText();
		
		/* configure le label pour le nom. */
		this.configurerNomLabel();
		
		/* configure la zone de texte pour le nom. */
		this.configurerNomText();
		
		/* configure le label pour le tel. */
		this.configurerTelLabel();
		
		/* configure la zone de texte pour le tel. */
		this.configurerTelText();
		
		/* configure le label pour le email. */
		this.configurerEmailLabel();
		
		/* configure la zone de texte pour le email. */
		this.configurerEmailText();
		
		/* configure le label pour le service. */
		this.configurerServiceLabel();
		
		/* configure la zone de texte pour le service. */
		this.configurerServiceText();
		
		/* configure le label pour l'unité. */
		this.configurerUniteLabel();
		
		/* configure la zone de texte pour l'unité. */
		this.configurerUniteText();
		
		/* configure le label pour le profil. */
		this.configurerProfilLabel();
		
		/* configure la zone de texte pour le profil. */
		this.configurerProfilText();
		
		/* configure le label pour la portée. */
		this.configurerPorteeLabel();
		
		/* configure la zone de texte pour la portée. */
		this.configurerPorteeText();
		
		/* configure le label pour la restriction. */
		this.configurerRestrictionLabel();
		
		/* configure la zone de texte pour la restriction. */
		this.configurerRestrictionText();
		
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
		
		GridPane.setConstraints(
				this.prenomLabel
					, 0, 2
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.prenomText
					, 1, 2
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.nomLabel
					, 0, 3
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.nomText
					, 1, 3
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.telLabel
					, 0, 4
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.telText
					, 1, 4
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.emailLabel
					, 0, 5
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.emailText
					, 1, 5
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.serviceLabel
					, 0, 6
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.serviceText
					, 1, 6
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.uniteLabel
					, 0, 7
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.uniteText
					, 1, 7
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.profilLabel
					, 0, 8
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.profilText
					, 1, 8
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.porteeLabel
					, 0, 9
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.porteeText
					, 1, 9
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.restrictionLabel
					, 0, 10
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		GridPane.setConstraints(
				this.restrictionText
					, 1, 10
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(12, 12, 12, 12));
		
		this.gridPane.getChildren().addAll(this.idLabel, this.idText);
		this.gridPane.getChildren().addAll(this.civiliteLabel, this.civiliteText);
		this.gridPane.getChildren().addAll(this.prenomLabel, this.prenomText);
		this.gridPane.getChildren().addAll(this.nomLabel, this.nomText);
		this.gridPane.getChildren().addAll(this.telLabel, this.telText);
		this.gridPane.getChildren().addAll(this.emailLabel, this.emailText);
		this.gridPane.getChildren().addAll(this.serviceLabel, this.serviceText);
		this.gridPane.getChildren().addAll(this.uniteLabel, this.uniteText);
		this.gridPane.getChildren().addAll(this.profilLabel, this.profilText);
		this.gridPane.getChildren().addAll(this.porteeLabel, this.porteeText);
		this.gridPane.getChildren().addAll(this.restrictionLabel, this.restrictionText);
		
		/* change la couleur de fond du GridPane. */
		/* surcharge la couleur dans la CSS. */
		this.gridPane.setStyle("-fx-background-color: #C0C0C0;");
		
		/* affiche les lignes de la grille du GridPane. */
		this.gridPane.setGridLinesVisible(true);
		
		/* ajoute une classe CSS à this.gridPane 
		 * (les GridPanes n'ont pas de classe CSS par défaut). */
		this.gridPane.getStyleClass().add("gridpane");
		
		/* ajoute le gridPane au présent AnchorPane. */
		this.getChildren().add(this.gridPane);
		
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
		
		this.idLabel.setPrefWidth(LARGEUR_LABELS);
		this.idLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.idLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.idLabel.setStyle(BORDER_TEXTES);
		
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
		this.idText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.idText.setDisable(true);
		this.idText.setAlignment(Pos.CENTER_LEFT);
		this.idText.setStyle(BORDER_TEXTES);
		
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
		
		this.civiliteLabel.setPrefWidth(LARGEUR_LABELS);
		this.civiliteLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.civiliteLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.civiliteLabel.setStyle(BORDER_TEXTES);
		
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
		this.civiliteText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.civiliteText.setDisable(false);
		this.civiliteText.setAlignment(Pos.CENTER_LEFT);
		this.civiliteText.setStyle(BORDER_TEXTES);
		
		this.civiliteText.setText("Mlle");
		
	} // Fin de configurerCiviliteText().__________________________________
	
	
	
	/**
	 * configure le label pour le prénom.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerPrenomLabel() {
		
		this.prenomLabel.setPrefWidth(LARGEUR_LABELS);
		this.prenomLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.prenomLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.prenomLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerPrenomLabel().___________________________________
	
	
	
	/**
	 * configure la zone de texte pour le prénom.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerPrenomText() {
		
		this.prenomText.setPrefWidth(400);
		this.prenomText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.prenomText.setDisable(false);
		this.prenomText.setAlignment(Pos.CENTER_LEFT);
		this.prenomText.setStyle(BORDER_TEXTES);
		
		this.prenomText.setText("Oscar");
		
	} // Fin de configurerPrenomText().____________________________________
	
	
	
	/**
	 * configure le label pour le nom.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerNomLabel() {
		
		this.nomLabel.setPrefWidth(LARGEUR_LABELS);
		this.nomLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.nomLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.nomLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerNomLabel().______________________________________
	
	
	
	/**
	 * configure la zone de texte pour le nom.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerNomText() {
		
		this.nomText.setPrefWidth(400);
		this.nomText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.nomText.setDisable(false);
		this.nomText.setAlignment(Pos.CENTER_LEFT);
		this.nomText.setStyle(BORDER_TEXTES);
		
		this.nomText.setText("Peterson");
		
	} // Fin de configurerNomText()._______________________________________
	
	
	
	/**
	 * configure le label pour le tel.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerTelLabel() {
		
		this.telLabel.setPrefWidth(LARGEUR_LABELS);
		this.telLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.telLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.telLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerTelLabel().______________________________________
	
	
	
	/**
	 * configure la zone de texte pour le tel.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerTelText() {
		
		this.telText.setPrefWidth(400);
		this.telText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.telText.setDisable(false);
		this.telText.setAlignment(Pos.CENTER_LEFT);
		this.telText.setStyle(BORDER_TEXTES);
		
		this.telText.setText("01 02 03 04 05");
		
	} // Fin de configurerTelText()._______________________________________
	
	
	
	/**
	 * configure le label pour le email.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerEmailLabel() {
		
		this.emailLabel.setPrefWidth(LARGEUR_LABELS);
		this.emailLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.emailLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.emailLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerEmailLabel().____________________________________
	
	
	
	/**
	 * configure la zone de texte pour le email.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerEmailText() {
		
		this.emailText.setPrefWidth(400);
		this.emailText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.emailText.setDisable(false);
		this.emailText.setAlignment(Pos.CENTER_LEFT);
		this.emailText.setStyle(BORDER_TEXTES);
		
		this.emailText.setText("oscar.peterson@yahoo.fr");
		
	} // Fin de configurerEmailText()._____________________________________
	
	
	
	/**
	 * configure le label pour le service.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerServiceLabel() {
		
		this.serviceLabel.setPrefWidth(LARGEUR_LABELS);
		this.serviceLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.serviceLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.serviceLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerServiceLabel().__________________________________
	
	
	
	/**
	 * configure la zone de texte pour le service.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerServiceText() {
		
		this.serviceText.setPrefWidth(400);
		this.serviceText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.serviceText.setDisable(false);
		this.serviceText.setAlignment(Pos.CENTER_LEFT);
		this.serviceText.setStyle(BORDER_TEXTES);
		
		this.serviceText.setText("CEREMA");
		
	} // Fin de configurerServiceText().___________________________________
	
	
	
	/**
	 * configure le label pour l'unité.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerUniteLabel() {
		
		this.uniteLabel.setPrefWidth(LARGEUR_LABELS);
		this.uniteLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.uniteLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.uniteLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerUniteLabel().____________________________________
	
	
	
	/**
	 * configure la zone de texte pour l'unité.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerUniteText() {
		
		this.uniteText.setPrefWidth(400);
		this.uniteText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.uniteText.setDisable(false);
		this.uniteText.setAlignment(Pos.CENTER_LEFT);
		this.uniteText.setStyle(BORDER_TEXTES);
		
		this.uniteText.setText("CEREMA/DTerMed/DCEDI/GTIE");
		
	} // Fin de configurerUniteText()._____________________________________
	
	
	
	/**
	 * configure le label pour le profil.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerProfilLabel() {
		
		this.profilLabel.setPrefWidth(LARGEUR_LABELS);
		this.profilLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.profilLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.profilLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerProfilLabel().___________________________________
	
	
	
	/**
	 * configure la zone de texte pour le profil.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerProfilText() {
		
		this.profilText.setPrefWidth(400);
		this.profilText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.profilText.setDisable(false);
		this.profilText.setAlignment(Pos.CENTER_LEFT);
		this.profilText.setStyle(BORDER_TEXTES);
		
		this.profilText.setText("ADMINISTRATEUR NATIONAL");
		
	} // Fin de configurerProfilText().____________________________________
	
	
	
	/**
	 * configure le label pour la portée.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerPorteeLabel() {
		
		this.porteeLabel.setPrefWidth(LARGEUR_LABELS);
		this.porteeLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.porteeLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.porteeLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerPorteeLabel().___________________________________
	
	
	
	/**
	 * configure la zone de texte pour la portée.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerPorteeText() {
		
		this.porteeText.setPrefWidth(400);
		this.porteeText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.porteeText.setDisable(false);
		this.porteeText.setAlignment(Pos.CENTER_LEFT);
		this.porteeText.setStyle(BORDER_TEXTES);
		
		this.porteeText.setText("CEREMA");
		
	} // Fin de configurerPorteeText().____________________________________
	
	
	
	/**
	 * configure le label pour la restriction.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerRestrictionLabel() {
		
		this.restrictionLabel.setPrefWidth(LARGEUR_LABELS);
		this.restrictionLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.restrictionLabel.setAlignment(Pos.CENTER_RIGHT);
//		this.restrictionLabel.setStyle(BORDER_TEXTES);
		
	} // Fin de configurerRestrictionLabel().______________________________
	
	
	
	/**
	 * configure la zone de texte pour la restriction.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerRestrictionText() {
		
		this.restrictionText.setPrefWidth(400);
		this.restrictionText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.restrictionText.setDisable(false);
		this.restrictionText.setAlignment(Pos.CENTER_LEFT);
		this.restrictionText.setStyle(BORDER_TEXTES);
		
		this.restrictionText.setText("France métropolitaine");
		
	} // Fin de configurerRestrictionText()._______________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereAffichageVue.------------------------
