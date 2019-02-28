package levy.daniel.application.vues.desktop.metier.utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * CLASSE UtilisateurCerbereListAffichageVueMain :<br/>
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
public class UtilisateurCerbereListAffichageVueMain extends Application {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereListAffichageVueMain.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereListAffichageVueMain() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(
			final Stage pPrimaryStage) throws Exception {
		
		/* vue à afficher. */
		final UtilisateurCerbereListAffichageVue vue 
			= new UtilisateurCerbereListAffichageVue();
		
 
        /* Titre du théâtre. */
        pPrimaryStage.setTitle("UtilisateurCerbereListAffichageVue");
        
        /* PASSE LA VUE à afficher à la SCENE 
         * et dimensionne la SCENE. */
        final Scene scene = new Scene(vue, 1000d, 800d);
        
        /* ajoute la feuille de style à la Scene. */
        scene.getStylesheets().add("static/css/dan_style.css");
        
        /* passe la SCENE au THEATRE (stage). */
        pPrimaryStage.setScene(scene);
        
        /* affiche le THEATRE. */
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


	
} // FIN DE LA CLASSE UtilisateurCerbereListAffichageVueMain.----------------
