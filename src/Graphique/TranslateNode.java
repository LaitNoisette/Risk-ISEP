package Graphique;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * "Listeners" qui permettent de bouger une node de type "AnchorPane" en entr�e avec le clic gauche souris
 */
class TranslateNode {

    //private DragContext nodeDragContext = new DragContext();
    private double mouseAnchorX;
    private double mouseAnchorY;

    private double translateAnchorX;
    private double translateAnchorY;

    public AnchorPane anchorpane;

    /**
     * Constructeur
     */
    public TranslateNode(AnchorPane anchorpane) {
        this.anchorpane = anchorpane;
    }
    
    /**
     * "Listener" appel� dans le filtre en cas de MOUSE_PRESSED
     */
    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return onMousePressedEventHandler;
    }

    /**
     * "Listener" appel� dans le filtre en cas MOUSE_DRAGGED
     */
    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return onMouseDraggedEventHandler;
    }

    /**
     * Event appel� par le Listener getOnMousePressedEventHandler()
     */
    private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            // Si un clic gauche est detect�, on peut r�cuperer les coordonn�es
            if(!event.isPrimaryButtonDown())
                return;

            // On r�cup�re la position de la souris dans le node o� a lieu l'event
            mouseAnchorX = event.getSceneX();
            mouseAnchorY = event.getSceneY();

            // On r�cup�re le "translate" actuel du node o� a lieu l'event
            Node node = (Node) event.getSource();  
            translateAnchorX = node.getTranslateX();
            translateAnchorY = node.getTranslateY();
        }
    };

    /**
     * Event appel� par le Listener getOnMouseDraggedEventHandler()
     */
    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
        	// Valeur maximale de translate, � impl�menter plus tard
        	//int maxTranslateValue = 200;
        	
        	// Si un clic gauche est detect� pour le drag, on peut translate
            if( !event.isPrimaryButtonDown())
                return;

            Node node = (Node) event.getSource();          
            
            // Nouveau translate = translate actuel + (nouvelle position de la souris - position de la souris avant drag)
            double translateX = translateAnchorX + (( event.getSceneX() - mouseAnchorX));
            double translateY = translateAnchorY + (( event.getSceneY() - mouseAnchorY));
            
            // On applique le translate
            node.setTranslateX(translateX);     
            node.setTranslateY(translateY);

            event.consume();
        }
    };
}