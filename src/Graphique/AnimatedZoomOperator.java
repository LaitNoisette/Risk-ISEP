package Graphique;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Zoom d'un node sur la position de la souris
 */
public class AnimatedZoomOperator {

    private Timeline timeline;

    /**
     * Constructeur
     */
    public AnimatedZoomOperator() {  
    	// Définition de la timeline (animation) à 60 fps
        this.timeline = new Timeline(60);
    }

    public void zoom(Node node, double factor, double x, double y) {    
        // Déterminiation de la nouvelle taille du node
        double oldScale = node.getScaleX();
        double scale = oldScale * factor;
        double f = (scale / oldScale) - 1;

        // Détermination de l'offset utilisé pour bouger le node
        Bounds bounds = node.localToScene(node.getBoundsInLocal());
        double dx = (x - (bounds.getWidth() / 2 + bounds.getMinX()));
        double dy = (y - (bounds.getHeight() / 2 + bounds.getMinY()));

        // Configuration de la timeline qui rescale et bouge le node sur la souris
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.millis(200), new KeyValue(node.translateXProperty(), node.getTranslateX() - f * dx)),
            new KeyFrame(Duration.millis(200), new KeyValue(node.translateYProperty(), node.getTranslateY() - f * dy)),
            new KeyFrame(Duration.millis(200), new KeyValue(node.scaleXProperty(), scale)),
            new KeyFrame(Duration.millis(200), new KeyValue(node.scaleYProperty(), scale))
        );
        
        // Lancement de la timeline
        timeline.play();
    }
}
