package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * The view for the planner.
 */
public class PlannerView implements JournalView {
  private final FXMLLoader loader;

  @FXML
  private Button buttonAddEvent;

  /**
   * The constructor for the view.
   *
   * @param controller the controller for the view
   */
  public PlannerView(JournalController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("Planner.fxml"));
    this.loader.setController(controller);
  }

  /**
   * The method to load the view.
   *
   * @return the scene for the view
   * @throws IllegalStateException if the view cannot be loaded
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Could not load Planner.fxml");
    }
  }
}
