package com.hw09.view;

import com.hw09.controller.IController;
import com.hw09.model.ISnapshot;

import java.util.List;


/**
 * Interface for the view component of the application.
 */
public interface IView {

  /**
   * Displays a single snapshot on the view.
   *
   * @param snapshot the snapshot to be displayed
   */
  void displaySingleSnapshot(ISnapshot snapshot);

  /**
   * Shows the view to the user. Either interactive or static.
   */
  void showView();

  /**
   * Sets the list of snapshots to be displayed on the view.
   *
   * @param snapshots the list of snapshots
   */
  void setSnapshots(List<ISnapshot> snapshots);

  /**
   * Sets the controller for the view.
   *
   * @param controller the controller to be set
   */
  void setController(IController controller);

  /**
   * Displays a message to the user.
   *
   * @param message the message to be displayed
   */
  void showMessage(String message);
}
