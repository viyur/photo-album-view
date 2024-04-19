package com.hw09.controller;

import com.hw09.model.IModel;
import com.hw09.view.IView;

/**
 * Interface for the controller component of the application.
 * The controller is responsible for managing the interaction between the model and the view.
 */
public interface IController {

  /**
   * Sets the view component of the controller.
   *
   * @param view the view component to be set
   */
  void setView(IView view);

  /**
   * Sets the model component of the controller.
   *
   * @param model the model component to be set
   */
  void setModel(IModel model);


  /**
   * Takes all the snapshots from the specified file and stores them in the model.
   *
   * @param fileName the name of the file containing the commands to take snapshots.
   */
  void takeSnapshotsFromFile(String fileName);

  /**
   * Updates the view with the snapshots stored in the model.
   * view component know how to show graphs in terms of their own mechanism.
   */
  void produceViewFromSnapshots();
}
