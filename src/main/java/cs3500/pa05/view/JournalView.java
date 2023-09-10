package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * The interface for the view.
 */
public interface JournalView {

  /**
   * The method to load the view.
   *
   * @return the scene for the view
   * @throws IllegalStateException if the view cannot be loaded
   */
  Scene load() throws IllegalStateException;
}
