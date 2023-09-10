package cs3500.pa05;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.PlannerController;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.PlannerView;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The application class for the program.
 */
public class ApplicationMaker extends Application {
  private static final Duration SPLASH_SCREEN_DURATION = Duration.seconds(1.5);
  private static final String IMAGE_PATH = "splashScreen.png"; // Replace with your image path

  /**
   * The start method for the splash screen.
   *
   * @param primaryStage the stage for the splash screen
   */
  @Override
  public void start(Stage primaryStage) {
    // Load the image
    Image splashImage = new Image(getClass().getClassLoader().getResourceAsStream(IMAGE_PATH));

    // Create an ImageView to display the image
    ImageView imageView = new ImageView(splashImage);

    // Create a StackPane as the root of the scene
    StackPane root = new StackPane(imageView);
    Scene scene = new Scene(root);

    // Show the splash screen stage
    primaryStage.setScene(scene);
    primaryStage.show();

    // Fade-in transition
    FadeTransition fadeInTransition = new FadeTransition(SPLASH_SCREEN_DURATION, root);
    fadeInTransition.setFromValue(0.0);
    fadeInTransition.setToValue(1.0);
    fadeInTransition.setOnFinished(event -> {
      try {
        showMainApplication(primaryStage);
      } catch (IOException e) {
        System.out.println("Oops!");
      }
    });
    fadeInTransition.play();
  }

  /**
   * The main application stage
   *
   * @param primaryStage the stage for the main gui
   * @throws IOException if the file is not found
   */
  private void showMainApplication(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Java Journal");

    JournalController plannerController = new PlannerController(primaryStage, getHostServices());
    JournalView plannerView = new PlannerView(plannerController);

    primaryStage.setScene(plannerView.load());
    plannerController.run();
    primaryStage.show();
  }
}