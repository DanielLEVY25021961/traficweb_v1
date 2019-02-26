package levy.daniel.application.vues.desktop.metier.utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * CLASSE UtilisateurCerbereAffichageVueMain :<br/>
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
public class UtilisateurCerbereAffichageVueMain extends Application {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereAffichageVueMain.class);

	
	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereAffichageVueMain() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(
			final Stage pPrimaryStage) throws Exception {
		
		/* vue à afficher. */
		final UtilisateurCerbereAffichageVue vue 
			= new UtilisateurCerbereAffichageVue();
		
		final BorderPane borderPaneRoot = new BorderPane();
		borderPaneRoot.setPrefSize(800d, 500d);
		borderPaneRoot.setCenter(vue);
		borderPaneRoot.setStyle("-fx-background-color: #F0591E;");

        borderPaneRoot.setPadding(new Insets(20, 20, 20, 20));
 
        /* Titre du théâtre. */
        pPrimaryStage.setTitle("UtilisateurCerbereAffichageVue");
        
        final Scene scene = new Scene(borderPaneRoot);
               
        pPrimaryStage.setScene(scene);
        
        pPrimaryStage.show();

	} // Fin de start(...).________________________________________________

	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[].<br/>
	 */
	public static void main(
			final String... pArgs) {
		 Application.launch(pArgs);
    } // Fin de main(...)._________________________________________________
	


} // FIN DE LA CLASSE UtilisateurCerbereAffichageVueMain.--------------------
