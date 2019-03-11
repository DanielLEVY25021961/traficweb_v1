package levy.daniel.application.vues.desktop.metier.utilisateur;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.IUtilisateurCerbereModelObs;
import levy.daniel.application.vues.desktop.metier.utilisateur.modelobs.impl.UtilisateurCerbereModelObs;

/**
 * CLASSE UtilisateurCerbereEditionVue :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 28 févr. 2019
 *
 */
public class UtilisateurCerbereEditionVueKO extends AnchorPane {

	// ************************ATTRIBUTS************************************/

	/**
	 * largeur preferred de tous les labels de l'IHM.<br/>
	 * 150d.<br/>
	 */
	public static final double LARGEUR_LABELS = 150d;
	
	/**
	 * largeur preferred de tous les TextField de l'IHM.<br/>
	 * 200d.<br/>
	 */
	public static final double LARGEUR_TEXTES = 200d;

	/**
	 * largeur preferred de tous les labels ERROR de l'IHM.<br/>
	 * 250d.<br/>
	 */
	public static final double LARGEUR_ERROR_LABELS = 250d;

	/**
	 * largeur minimum de tous les TextField de l'IHM.<br/>
	 * 200d.<br/>
	 */
	public static final double LARGEUR_MIN_TEXTES = 200d;
	
	/**
	 * hauteur preferred de tous les labels et zones de texte de l'IHM.<br/>
	 * 25d.<br/>
	 */
	public static final double HAUTEUR_LABELS_TEXTES = 25d;
	
	/**
	 * "-fx-border-color: black;-fx-background-color: WHITE;".
	 */
	public static final String BORDER_TEXTES 
		= "-fx-border-color: black;-fx-background-color: WHITE;";

	/**
	 * marge par défaut autout d'un composant.<br/>
	 * 2d<br/>
	 */
	public static final double MARGIN = 2d;
	
	/**
	 * Chaine vide.<br/>
	 * ""
	 */
	public static final String BLANK = "";
	
	/**
	 * label pour la civilité.
	 */
	private final transient Label civiliteLabel = new Label("Civilité : ");
	
	/**
	 * zone de texte de la civilite.
	 */
	private final transient TextField civiliteText = new TextField();
	
	/**
	 * label pour les erreurs sur la civilité.
	 */
	private final transient Label civiliteErrorLabel = new Label();
	
	/**
	 * label pour le prénom.
	 */
	private final transient Label prenomLabel = new Label("Prénom : ");
	
	/**
	 * zone de texte du prénom.
	 */
	private final transient TextField prenomText = new TextField();
	
	/**
	 * label pour les erreurs sur le prénom.
	 */
	private final transient Label prenomErrorLabel = new Label();
	
	/**
	 * label pour le nom.
	 */
	private final transient Label nomLabel = new Label("Nom : ");
	
	/**
	 * zone de texte du nom.
	 */
	private final transient TextField nomText = new TextField();
	
	/**
	 * label pour les erreurs sur le nom.
	 */
	private final transient Label nomErrorLabel = new Label();
	
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
	 * label pour les erreurs sur le téléphone.
	 */
	private final transient Label telErrorLabel = new Label();
	
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
	 * label pour les erreurs sur le email.
	 */
	private final transient Label emailErrorLabel = new Label();
	
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
	 * label pour les erreurs sur le service.
	 */
	private final transient Label serviceErrorLabel = new Label();
	
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
	 * label pour les erreurs sur l'unité.
	 */
	private final transient Label uniteErrorLabel = new Label();
	
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
	 * label pour les erreurs sur le profil.
	 */
	private final transient Label profilErrorLabel = new Label();
	
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
	 * label pour les erreurs sur la portée.
	 */
	private final transient Label porteeErrorLabel = new Label();
	
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
	 * label pour les erreurs sur la restriction.
	 */
	private final transient Label restrictionErrorLabel = new Label();

	/**
	 * Label contenant les éventuelles erreurs <b>globales</b> 
	 * lors de l'édition d'un objet métier (doublon, ...).
	 */
	private final transient Label erreursGlobalesLabel 
		= new Label();
	
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
		= LogFactory.getLog(UtilisateurCerbereEditionVueKO.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>configure la présente vue (AnchorPane).</li>
	 * </ul>
	 */
	public UtilisateurCerbereEditionVueKO() {
		
		super();
		
		/* configure la présente vue (AnchorPane). */
		this.configurerVue();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * affiche les valeurs d'un DTO dans la présente VUE.<br/>
	 * <br/>
	 * - ne fait rien si pDto == null.<br/>
	 * <br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : 
	 * DTO de l'objet métier.<br/>
	 */
	public final void afficherDTO(
			final IUtilisateurCerbereDTO pDto) {
		
		/* ne fait rien si pDto == null. */
		if (pDto == null) {
			return;
		}
		
		this.civiliteText.setText(pDto.getCivilite());
		this.prenomText.setText(pDto.getPrenom());
		this.nomText.setText(pDto.getNom());
		this.telText.setText(pDto.getTel());
		this.emailText.setText(pDto.getEmail());
		this.serviceText.setText(pDto.getService());
		this.uniteText.setText(pDto.getUnite());
		this.profilText.setText(pDto.getProfil());
		this.porteeText.setText(pDto.getPortee());
		this.restrictionText.setText(pDto.getRestriction());
		
	} // Fin de afficherDTO(...).__________________________________________

	
	
	/**
	 * affiche les valeurs d'un MODELE dans la présente VUE.<br/>
	 * <br/>
	 * - ne fait rien si pModel == null.<br/>
	 * <br/>
	 *
	 * @param pModel : IUtilisateurCerbereModelObs : 
	 * DTO de l'objet métier.<br/>
	 */
	public final void afficherModelObs(
			final IUtilisateurCerbereModelObs pModel) {
		
		/* ne fait rien si pModel == null. */
		if (pModel == null) {
			return;
		}
		
		this.civiliteText.setText(pModel.getCivilite());
		this.prenomText.setText(pModel.getPrenom());
		this.nomText.setText(pModel.getNom());
		this.telText.setText(pModel.getTel());
		this.emailText.setText(pModel.getEmail());
		this.serviceText.setText(pModel.getService());
		this.uniteText.setText(pModel.getUnite());
		this.profilText.setText(pModel.getProfil());
		this.porteeText.setText(pModel.getPortee());
		this.restrictionText.setText(pModel.getRestriction());
		
	} // Fin de afficherModelObs(...)._____________________________________
	

	
	/**
	 * Lit le contenu de la présente VUE et retourne un MODELE.<br/>
	 *
	 * @return : IUtilisateurCerbereModelObs :  MODELE.<br/>
	 */
	public final IUtilisateurCerbereModelObs lireVue() {
		
		final IUtilisateurCerbereModelObs model 
			= new UtilisateurCerbereModelObs(null
					, this.civiliteText.getText()
					, this.prenomText.getText(), this.nomText.getText()
					, this.telText.getText(), this.emailText.getText()
					, this.serviceText.getText(), this.uniteText.getText()
					, this.profilText.getText()
					, this.porteeText.getText()
					, this.restrictionText.getText());
		
		return model;
		
	} // Fin de lireVue()._________________________________________________
	

	
	/**
	 * remet toutes les valeurs dans les zones de texte 
	 * de la présente vue à blank ("").<br/>
	 */
	public final void resetVue() {
		
		this.civiliteText.setText(BLANK);
		this.civiliteErrorLabel.setText(BLANK);
		this.prenomText.setText(BLANK);
		this.prenomErrorLabel.setText(BLANK);
		this.nomText.setText(BLANK);
		this.nomErrorLabel.setText(BLANK);
		this.telText.setText(BLANK);
		this.telErrorLabel.setText(BLANK);
		this.emailText.setText(BLANK);
		this.emailErrorLabel.setText(BLANK);
		this.serviceText.setText(BLANK);
		this.serviceErrorLabel.setText(BLANK);
		this.uniteText.setText(BLANK);
		this.uniteErrorLabel.setText(BLANK);
		this.profilText.setText(BLANK);
		this.profilErrorLabel.setText(BLANK);
		this.porteeText.setText(BLANK);
		this.porteeErrorLabel.setText(BLANK);
		this.restrictionText.setText(BLANK);
		this.restrictionErrorLabel.setText(BLANK);
		
	} // Fin de resetVue().________________________________________________
	

	
	/**
	 * injecte les messages d'ERROR dans le Label d'ERROR 
	 * de chaque attribut.<br/>
	 * Les message d'ereur sont contenus dans une Map&lt;String, String&gt; 
	 * pErrorsMap définie par le SERVICE de VALIDATION.<br/>
	 * <br/>
	 * - ne fait rien si pErrorsMap est null ou vide.<br/>
	 * <br/>
	 *
	 * @param pErrorsMap : Map&lt;String, String&gt; : 
	 * Map d'erreurs avec les messages d'erreur pour chaque attribut.<br/>
	 */
	public void injecterErrorsMapDansLabels(
			final Map<String, String> pErrorsMap) {
		
		/* ne fait rien si pErrorsMap est null ou vide. */
		if (pErrorsMap == null || pErrorsMap.isEmpty()) {
			return;
		}
		
		/* civilite. */
		final String messageErrorCivilite = pErrorsMap.get("civilite");
		if (!StringUtils.isBlank(messageErrorCivilite)) {
			this.injecterMessageDansLabelError(
					this.civiliteErrorLabel, messageErrorCivilite);
		}
		
		/* prenom. */
		final String messageErrorPrenom = pErrorsMap.get("prenom");
		if (!StringUtils.isBlank(messageErrorPrenom)) {
			this.injecterMessageDansLabelError(
					this.prenomErrorLabel, messageErrorPrenom);
		}
		
		/* nom. */
		final String messageErrorNom = pErrorsMap.get("nom");
		if (!StringUtils.isBlank(messageErrorNom)) {
			this.injecterMessageDansLabelError(
					this.nomErrorLabel, messageErrorNom);
		}
		
		
		final String messageErrorTel = pErrorsMap.get("tel");
		final String messageErrorEmail = pErrorsMap.get("email");
		final String messageErrorService = pErrorsMap.get("service");
		final String messageErrorUnite = pErrorsMap.get("unite");
		final String messageErrorProfil = pErrorsMap.get("profil");
		final String messageErrorPortee = pErrorsMap.get("portee");
		final String messageErrorRestriction = pErrorsMap.get("restriction");
		
	} // Fin de injecterErrorsMapDansLabels(...).__________________________
	
	
	
	/**
	 * injecte un message d'ERROR dans un Label d'ERROR.<br/>
	 * <ul>
	 * <li>rend le Label d'ERROR <b>visible</b> si nécessaire.</li>
	 * </ul>
	 * - ne fait rien si pLabel == null.<br/>
	 * <br/>
	 *
	 * @param pLabel : Label : Label d'ERROR.<br/>
	 * @param pString : String : message d'ERROR.<br/>
	 */
	public void injecterMessageDansLabelError(
			final Label pLabel, final String pString) {
		
		/* ne fait rien si pLabel == null. */
		if (pLabel == null) {
			return;
		}
		
		pLabel.setText(pString);
		
		/* rend le Label d'ERROR visible si nécessaire. */
		this.configurerVisibiliteLabelError(pLabel);
		
	} // Fin de injecterMessageDansLabelError(...).________________________
	

	
	/**
	 * positionne le curseur sur le 1er champ (civilité) 
	 * du formulaire d'édition.<br/>
	 */
	public void positionnerCurseur() {
		this.civiliteText.requestFocus();
	} // Fin de positionnerCurseur().______________________________________

	
	
	/**
	 * rend un Label d'erreur visible si il contient du texte
	 * , invisible sinon.<br/>
	 * <br/>
	 * - ne fait rien si pLabel == null.<br/>
	 * <br/>
	 *
	 * @param pLabel : Label : Label d'ERROR.<br/>
	 */
	private void configurerVisibiliteLabelError(
			final Label pLabel) {
		
		/* ne fait rien si pLabel == null. */
		if (pLabel == null) {
			return;
		}
		
		if (StringUtils.isBlank(pLabel.getText())) {
			pLabel.setVisible(false);
		} else {
			pLabel.setVisible(true);
		}
		
	} // Fin de configurerVisibiliteLabelError(...)._______________________

	
	
	/**
	 * configure la présente vue (AnchorPane).<br/>
	 * <ul>
	 * <li>configure tous les composants (labels, zones de texte, ...).</li>
	 * <li>positionne chaque composants dans un Constraints de GridPane.</li>
	 * <li>ajoute chaque composant dans le GridPane.</li>
	 * <li>ajoute le gridPane au présent AnchorPane.</li>
	 * </ul>
	 */
	private void configurerVue() {
		
		/* configure tous les composants (labels, zones de texte, ...). */
		this.configurerComposants();
		
		/* positionne chaque composants dans un Constraints de GridPane. */
		this.positionnerComposantsDansConstraints();
		
		/* ajoute chaque composant dans le GridPane. */
		this.gridPane.getChildren().addAll(
				this.civiliteLabel, this.civiliteText, this.civiliteErrorLabel);
		this.gridPane.getChildren().addAll(
				this.prenomLabel, this.prenomText, this.prenomErrorLabel);
		this.gridPane.getChildren().addAll(
				this.nomLabel, this.nomText, this.nomErrorLabel);
		this.gridPane.getChildren().addAll(
				this.telLabel, this.telText, this.telErrorLabel);
		this.gridPane.getChildren().addAll(
				this.emailLabel, this.emailText, this.emailErrorLabel);
		this.gridPane.getChildren().addAll(
				this.serviceLabel, this.serviceText, this.serviceErrorLabel);
		this.gridPane.getChildren().addAll(
				this.uniteLabel, this.uniteText, this.uniteErrorLabel);
		this.gridPane.getChildren().addAll(
				this.profilLabel, this.profilText, this.profilErrorLabel);
		this.gridPane.getChildren().addAll(
				this.porteeLabel, this.porteeText, this.porteeErrorLabel);
		this.gridPane.getChildren().addAll(
				this.restrictionLabel, this.restrictionText, this.restrictionErrorLabel);		
		this.gridPane.getChildren().addAll(this.erreursGlobalesLabel);
				
		
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
		
		/* ajoute le gridPane au présent AnchorPane. */
		this.getChildren().add(this.gridPane);
		
		/* fixe les distances entre le contenu (this.gridPane) 
		 * et le présent conteneur AnchorPane. */
		AnchorPane.setTopAnchor(this.gridPane, 10D);
		AnchorPane.setLeftAnchor(this.gridPane, 10D);
		AnchorPane.setBottomAnchor(this.gridPane, 10D);
		AnchorPane.setRightAnchor(this.gridPane, 10D);
		
		
		/* ajoute une classe CSS au présent AnchorPane 
		 * (les AnchorPanes n'ont pas de classe CSS par défaut). */
		this.getStyleClass().add("anchorpane");
		
	} // Fin de configurerVue().___________________________________________
	

	
	/**
	 * configure tous les composants (labels, zones de texte, ...).<br/>
	 */
	private void configurerComposants() {
		
		/* configure le label pour la civilite. */
		this.configurerCiviliteLabel();
		
		/* configure la zone de texte pour la civilite. */
		this.configurerCiviliteText();
		this.configurerCiviliteErrorLabel();
		
		/* configure le label pour le prénom. */
		this.configurerPrenomLabel();
		
		/* configure la zone de texte pour le prénom. */
		this.configurerPrenomText();
		this.configurerPrenomErrorLabel();
		
		/* configure le label pour le nom. */
		this.configurerNomLabel();
		
		/* configure la zone de texte pour le nom. */
		this.configurerNomText();
		this.configurerNomErrorLabel();
		
		/* configure le label pour le tel. */
		this.configurerTelLabel();
		
		/* configure la zone de texte pour le tel. */
		this.configurerTelText();
		this.configurerTelErrorLabel();
		
		/* configure le label pour le email. */
		this.configurerEmailLabel();
		
		/* configure la zone de texte pour le email. */
		this.configurerEmailText();
		this.configurerEmailErrorLabel();
		
		/* configure le label pour le service. */
		this.configurerServiceLabel();
		
		/* configure la zone de texte pour le service. */
		this.configurerServiceText();
		this.configurerServiceErrorLabel();
		
		/* configure le label pour l'unité. */
		this.configurerUniteLabel();
		
		/* configure la zone de texte pour l'unité. */
		this.configurerUniteText();
		this.configurerUniteErrorLabel();
		
		/* configure le label pour le profil. */
		this.configurerProfilLabel();
		
		/* configure la zone de texte pour le profil. */
		this.configurerProfilText();
		this.configurerProfilErrorLabel();
		
		/* configure le label pour la portée. */
		this.configurerPorteeLabel();
		
		/* configure la zone de texte pour la portée. */
		this.configurerPorteeText();
		this.configurerPorteeErrorLabel();
		
		/* configure le label pour la restriction. */
		this.configurerRestrictionLabel();
		
		/* configure la zone de texte pour la restriction. */
		this.configurerRestrictionText();
		this.configurerRestrictionErrorLabel();
		
		/* configure la TextArea pour les erreurs globales. */
		this.configurerErreursGlobalesLabel();

	} // Fin de configurerComposants().____________________________________
	

	
	/**
	 * positionne chaque composants dans un Constraints de GridPane.<br/>
	 */
	private void positionnerComposantsDansConstraints() {
		
		GridPane.setConstraints(
				this.civiliteLabel
					, 0, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.civiliteText
					, 1, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.civiliteErrorLabel
					, 2, 0
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.prenomLabel
					, 0, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.prenomText
					, 1, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.prenomErrorLabel
					, 2, 1
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.nomLabel
					, 0, 2
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.nomText
					, 1, 2
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.nomErrorLabel
					, 2, 2
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.telLabel
					, 0, 3
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.telText
					, 1, 3
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.telErrorLabel
					, 2, 3
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.emailLabel
					, 0, 4
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.emailText
					, 1, 4
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.emailErrorLabel
					, 2, 4
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.serviceLabel
					, 0, 5
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.serviceText
					, 1, 5
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.serviceErrorLabel
					, 2, 5
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.uniteLabel
					, 0, 6
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.uniteText
					, 1, 6
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.uniteErrorLabel
					, 2, 6
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.profilLabel
					, 0, 7
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.profilText
					, 1, 7
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.profilErrorLabel
					, 2, 7
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.porteeLabel
					, 0, 8
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.porteeText
					, 1, 8
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.porteeErrorLabel
					, 2, 8
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.restrictionLabel
					, 0, 9
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.NEVER, Priority.SOMETIMES
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.restrictionText
					, 1, 9
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.restrictionErrorLabel
					, 2, 9
						, 1, 1
							, HPos.CENTER, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
		GridPane.setConstraints(
				this.erreursGlobalesLabel
					, 0, 10
						, 3, 1
							, HPos.LEFT, VPos.CENTER
								, Priority.ALWAYS, Priority.ALWAYS
									, new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
		
	} // Fin de positionnerComposantsDansConstraints().____________________
	
	
	
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
		this.civiliteLabel.setMinWidth(LARGEUR_LABELS);
		this.civiliteLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.civiliteLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.civiliteText.setPrefWidth(LARGEUR_TEXTES);
		this.civiliteText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.civiliteText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.civiliteText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.civiliteText.setDisable(false);
		this.civiliteText.setAlignment(Pos.CENTER_LEFT);
		this.civiliteText.setStyle(BORDER_TEXTES);
		
		this.civiliteText.setText(BLANK);
		
	} // Fin de configurerCiviliteText().__________________________________

	
	
	/**
	 * configure le label pour les erreurs sur la civilite.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerCiviliteErrorLabel() {

		this.civiliteErrorLabel.setPrefWidth(LARGEUR_TEXTES);
		this.civiliteErrorLabel.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.civiliteErrorLabel.setMinWidth(LARGEUR_MIN_TEXTES);
		this.civiliteErrorLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.civiliteErrorLabel.setAlignment(Pos.CENTER_LEFT);
		this.civiliteErrorLabel.setWrapText(true);		
		this.civiliteErrorLabel.getStyleClass().add("error");		
		this.civiliteErrorLabel.setVisible(false);
		
	} // Fin de configurerCiviliteErrorLabel().____________________________	
	
	
	
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
		this.prenomLabel.setMinWidth(LARGEUR_LABELS);
		this.prenomLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.prenomLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.prenomText.setPrefWidth(LARGEUR_TEXTES);
		this.prenomText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.prenomText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.prenomText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.prenomText.setDisable(false);
		this.prenomText.setAlignment(Pos.CENTER_LEFT);
		this.prenomText.setStyle(BORDER_TEXTES);
		
		this.prenomText.setText(BLANK);
		
	} // Fin de configurerPrenomText().____________________________________
	

	
	/**
	 * configure le label pour les erreurs sur le prénom.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerPrenomErrorLabel() {
		
		this.prenomErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.prenomErrorLabel.setWrapText(true);
		
		this.prenomErrorLabel.getStyleClass().add("error");
		
		this.prenomErrorLabel.setVisible(false);

	} // Fin de configurerPrenomErrorLabel().______________________________
	
	
	
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
		this.nomLabel.setMinWidth(LARGEUR_LABELS);
		this.nomLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.nomLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.nomText.setPrefWidth(LARGEUR_TEXTES);
		this.nomText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.nomText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.nomText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.nomText.setDisable(false);
		this.nomText.setAlignment(Pos.CENTER_LEFT);
		this.nomText.setStyle(BORDER_TEXTES);
		
		this.nomText.setText(BLANK);
		
	} // Fin de configurerNomText()._______________________________________
	

	
	/**
	 * configure le label pour les erreurs sur le nom.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerNomErrorLabel() {
		
		this.nomErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.nomErrorLabel.setWrapText(true);
		
		this.nomErrorLabel.getStyleClass().add("error");

		this.nomErrorLabel.setVisible(false);
		
	} // Fin de configurerNomErrorLabel().______________________________
	
	
	
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
		this.telLabel.setMinWidth(LARGEUR_LABELS);
		this.telLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.telLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.telText.setPrefWidth(LARGEUR_TEXTES);
		this.telText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.telText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.telText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.telText.setDisable(false);
		this.telText.setAlignment(Pos.CENTER_LEFT);
		this.telText.setStyle(BORDER_TEXTES);
		
		this.telText.setText(BLANK);
		
	} // Fin de configurerTelText()._______________________________________
	

	
	/**
	 * configure le label pour les erreurs sur le tel.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerTelErrorLabel() {
		
		this.telErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.telErrorLabel.setWrapText(true);
		
		this.telErrorLabel.getStyleClass().add("error");
		
		this.telErrorLabel.setVisible(false);

	} // Fin de configurerTelErrorLabel()._________________________________
	
	
	
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
		this.emailLabel.setMinWidth(LARGEUR_LABELS);
		this.emailLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.emailLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.emailText.setPrefWidth(LARGEUR_TEXTES);
		this.emailText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.emailText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.emailText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.emailText.setDisable(false);
		this.emailText.setAlignment(Pos.CENTER_LEFT);
		this.emailText.setStyle(BORDER_TEXTES);
		
		this.emailText.setText(BLANK);
		
	} // Fin de configurerEmailText()._____________________________________
	

	
	/**
	 * configure le label pour les erreurs sur le email.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerEmailErrorLabel() {
		
		this.emailErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.emailErrorLabel.setWrapText(true);
		
		this.emailErrorLabel.getStyleClass().add("error");

		this.emailErrorLabel.setVisible(false);
		
	} // Fin de configurerEmailErrorLabel()._______________________________
	
	
	
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
		this.serviceLabel.setMinWidth(LARGEUR_LABELS);
		this.serviceLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.serviceLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.serviceText.setPrefWidth(LARGEUR_TEXTES);
		this.serviceText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.serviceText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.serviceText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.serviceText.setDisable(false);
		this.serviceText.setAlignment(Pos.CENTER_LEFT);
		this.serviceText.setStyle(BORDER_TEXTES);
		
		this.serviceText.setText(BLANK);
		
	} // Fin de configurerServiceText().___________________________________
	

	
	/**
	 * configure le label pour les erreurs sur le service.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerServiceErrorLabel() {
		
		this.serviceErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.serviceErrorLabel.setWrapText(true);
		
		this.serviceErrorLabel.getStyleClass().add("error");

		this.serviceErrorLabel.setVisible(false);
		
	} // Fin de configurerServiceErrorLabel()._____________________________
	
	
	
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
		this.uniteLabel.setMinWidth(LARGEUR_LABELS);
		this.uniteLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.uniteLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.uniteText.setPrefWidth(LARGEUR_TEXTES);
		this.uniteText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.uniteText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.uniteText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.uniteText.setDisable(false);
		this.uniteText.setAlignment(Pos.CENTER_LEFT);
		this.uniteText.setStyle(BORDER_TEXTES);
		
		this.uniteText.setText(BLANK);
		
	} // Fin de configurerUniteText()._____________________________________
	

	
	/**
	 * configure le label pour les erreurs sur l'unité.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerUniteErrorLabel() {
		
		this.uniteErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.uniteErrorLabel.setWrapText(true);
		
		this.uniteErrorLabel.getStyleClass().add("error");

		this.uniteErrorLabel.setVisible(false);
		
	} // Fin de configurerUniteErrorLabel()._______________________________
	
	
	
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
		this.profilLabel.setMinWidth(LARGEUR_LABELS);
		this.profilLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.profilLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.profilText.setPrefWidth(LARGEUR_TEXTES);
		this.profilText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.profilText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.profilText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.profilText.setDisable(false);
		this.profilText.setAlignment(Pos.CENTER_LEFT);
		this.profilText.setStyle(BORDER_TEXTES);
		
		this.profilText.setText(BLANK);
		
	} // Fin de configurerProfilText().____________________________________
	

	
	/**
	 * configure le label pour les erreurs sur le profil.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerProfilErrorLabel() {
		
		this.profilErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.profilErrorLabel.setWrapText(true);
		
		this.profilErrorLabel.getStyleClass().add("error");

		this.profilErrorLabel.setVisible(false);
		
	} // Fin de configurerProfilErrorLabel().______________________________
	
	
	
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
		this.porteeLabel.setMinWidth(LARGEUR_LABELS);
		this.porteeLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.porteeLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.porteeText.setPrefWidth(LARGEUR_TEXTES);
		this.porteeText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.porteeText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.porteeText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.porteeText.setDisable(false);
		this.porteeText.setAlignment(Pos.CENTER_LEFT);
		this.porteeText.setStyle(BORDER_TEXTES);
		
		this.porteeText.setText(BLANK);
		
	} // Fin de configurerPorteeText().____________________________________
	

	
	/**
	 * configure le label pour les erreurs sur la portée.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerPorteeErrorLabel() {
		
		this.porteeErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.porteeErrorLabel.setWrapText(true);
		
		this.porteeErrorLabel.getStyleClass().add("error");

		this.porteeErrorLabel.setVisible(false);
		
	} // Fin de configurerPorteeErrorLabel().______________________________
	
	
	
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
		this.restrictionLabel.setMinWidth(LARGEUR_LABELS);
		this.restrictionLabel.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.restrictionLabel.setAlignment(Pos.CENTER_RIGHT);
		
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
		
		this.restrictionText.setPrefWidth(LARGEUR_TEXTES);
		this.restrictionText.setPrefHeight(HAUTEUR_LABELS_TEXTES);
		this.restrictionText.setMinWidth(LARGEUR_MIN_TEXTES);
		this.restrictionText.setMinHeight(HAUTEUR_LABELS_TEXTES);
		
		this.restrictionText.setDisable(false);
		this.restrictionText.setAlignment(Pos.CENTER_LEFT);
		this.restrictionText.setStyle(BORDER_TEXTES);
		
		this.restrictionText.setText(BLANK);
		
	} // Fin de configurerRestrictionText()._______________________________
	

	
	/**
	 * configure le label pour les erreurs sur la restriction.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerRestrictionErrorLabel() {
		
		this.restrictionErrorLabel.setAlignment(Pos.CENTER_LEFT);
		
		this.restrictionErrorLabel.setWrapText(true);
		
		this.restrictionErrorLabel.getStyleClass().add("error");
		
		this.restrictionErrorLabel.setVisible(false);

	} // Fin de configurerRestrictionErrorLabel()._________________________


	
	/**
	 * configure le Label contenant les erreurs globales.<br/>
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 */
	private void configurerErreursGlobalesLabel() {
		
		this.restrictionErrorLabel.setAlignment(Pos.TOP_LEFT);
		
		this.erreursGlobalesLabel.setWrapText(true);
		
		this.erreursGlobalesLabel.getStyleClass().add("error");
		
		this.erreursGlobalesLabel.setVisible(false);
		
	} // Fin de configurerErreursGlobalesLabel().__________________________
	
	
	
	/**
	 * Getter de la zone de texte de la civilite.
	 *
	 * @return this.civiliteText : TextField.<br/>
	 */
	public final TextField getCiviliteText() {
		return this.civiliteText;
	} // Fin de getCiviliteText()._________________________________________


	
	/**
	 * Getter du label pour les erreurs sur la civilité.
	 *
	 * @return this.civiliteErrorLabel : Label.<br/>
	 */
	public final Label getCiviliteErrorLabel() {
		return this.civiliteErrorLabel;
	} // Fin de getCiviliteErrorLabel().___________________________________


	
	/**
	 * Getter de la zone de texte du prénom.
	 *
	 * @return this.prenomText : TextField.<br/>
	 */
	public final TextField getPrenomText() {
		return this.prenomText;
	} // Fin de getPrenomText().___________________________________________


	
	/**
	 * Getter du label pour les erreurs sur le prénom.
	 *
	 * @return this.prenomErrorLabel : Label.<br/>
	 */
	public final Label getPrenomErrorLabel() {
		return this.prenomErrorLabel;
	} // Fin de getPrenomErrorLabel()._____________________________________


	
	/**
	 * Getter de la zone de texte du nom.
	 *
	 * @return this.nomText : TextField.<br/>
	 */
	public final TextField getNomText() {
		return this.nomText;
	} // Fin de getNomText().______________________________________________


	
	/**
	 * Getter du label pour les erreurs sur le nom.
	 *
	 * @return this.nomErrorLabel : Label.<br/>
	 */
	public final Label getNomErrorLabel() {
		return this.nomErrorLabel;
	} // Fin de getNomErrorLabel().________________________________________


	
	/**
	 * Getter de la zone de texte du tel.
	 *
	 * @return this.telText : TextField.<br/>
	 */
	public final TextField getTelText() {
		return this.telText;
	} // Fin de getTelText().______________________________________________


	
	/**
	 * Getter du label pour les erreurs sur le téléphone.
	 *
	 * @return this.telErrorLabel : Label.<br/>
	 */
	public final Label getTelErrorLabel() {
		return this.telErrorLabel;
	} // Fin de getTelErrorLabel().________________________________________


	
	/**
	 * Getter de la zone de texte du email.
	 *
	 * @return this.emailText : TextField.<br/>
	 */
	public final TextField getEmailText() {
		return this.emailText;
	} // Fin de getEmailText().____________________________________________


	
	/**
	 * Getter du label pour les erreurs sur le email.
	 *
	 * @return this.emailErrorLabel : Label.<br/>
	 */
	public final Label getEmailErrorLabel() {
		return this.emailErrorLabel;
	} // Fin de getEmailErrorLabel().______________________________________


	
	/**
	 * Getter de la zone de texte du service.
	 *
	 * @return this.serviceText : TextField.<br/>
	 */
	public final TextField getServiceText() {
		return this.serviceText;
	} // Fin de getServiceText().__________________________________________


	
	/**
	 * Getter du label pour les erreurs sur le service.
	 *
	 * @return this.serviceErrorLabel : Label.<br/>
	 */
	public final Label getServiceErrorLabel() {
		return this.serviceErrorLabel;
	} // Fin de getServiceErrorLabel().____________________________________


	
	/**
	 * Getter de la zone de texte de l'unité.
	 *
	 * @return this.uniteText : TextField.<br/>
	 */
	public final TextField getUniteText() {
		return this.uniteText;
	} // Fin de getUniteText().____________________________________________


	
	/**
	 * Getter du label pour les erreurs sur l'unité.
	 *
	 * @return this.uniteErrorLabel : Label.<br/>
	 */
	public final Label getUniteErrorLabel() {
		return this.uniteErrorLabel;
	} // Fin de getUniteErrorLabel().______________________________________


	
	/**
	 * Getter de la zone de texte pour le profil.
	 *
	 * @return this.profilText : TextField.<br/>
	 */
	public final TextField getProfilText() {
		return this.profilText;
	} // Fin de getProfilText().___________________________________________


	
	/**
	 * Getter du label pour les erreurs sur le profil.
	 *
	 * @return this.profilErrorLabel : Label.<br/>
	 */
	public final Label getProfilErrorLabel() {
		return this.profilErrorLabel;
	} // Fin de getProfilErrorLabel()._____________________________________


	
	/**
	 * Getter de la zone de texte pour la portée.
	 *
	 * @return this.porteeText : TextField.<br/>
	 */
	public final TextField getPorteeText() {
		return this.porteeText;
	} // Fin de getPorteeText().___________________________________________


	
	/**
	 * Getter du label pour les erreurs sur la portée.
	 *
	 * @return this.porteeErrorLabel : Label.<br/>
	 */
	public final Label getPorteeErrorLabel() {
		return this.porteeErrorLabel;
	} // Fin de getPorteeErrorLabel()._____________________________________



	/**
	 * Getter de la zone de texte pour la restriction.
	 *
	 * @return this.restrictionText : TextField.<br/>
	 */
	public final TextField getRestrictionText() {
		return this.restrictionText;
	} // Fin de getRestrictionText().______________________________________


	
	/**
	 * Getter du label pour les erreurs sur la restriction.
	 *
	 * @return this.restrictionErrorLabel : Label.<br/>
	 */
	public final Label getRestrictionErrorLabel() {
		return this.restrictionErrorLabel;
	} // Fin de getRestrictionErrorLabel().________________________________


	
	/**
	 * Getter du Label contenant les éventuelles erreurs 
	 * <b>globales</b> lors de l'édition d'un objet métier (doublon, ...).
	 *
	 * @return this.erreursGlobalesLabel : Label.<br/>
	 */
	public final Label getErreursGlobalesLabel() {
		return this.erreursGlobalesLabel;
	} // Fin de getErreursGlobalesLabel()._________________________________

	
	
} // FIN DE LA CLASSE UtilisateurCerbereEditionVue.--------------------------
