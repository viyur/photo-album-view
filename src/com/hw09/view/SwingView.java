package com.hw09.view;

import com.hw09.controller.IController;
import com.hw09.model.IShape;
import com.hw09.model.ISnapshot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Interactive view type.
 * JScrollPane can make sure the graph is completely drawn when oversize.
 */
public class SwingView extends JFrame implements IView {

  /**
   * Tailored panel dedicated to draw the graph part of each snapshot.
   */
  class DrawingPanel extends JPanel {
    private final List<IShape> shapes = new ArrayList<IShape>();

    public void drawShapes(List<IShape> shapes) {
      // Receive all the shapes data stored in single snapshot
      this.shapes.clear();
      this.shapes.addAll(shapes);
      // start to draw all the shapes in single snapshot
      repaint();
    }

    /**
     * Draw the shape according to its type.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      List<IShape> copies;
      // make sure there will not be ConcurrentModificationException
      synchronized (shapes) {
        copies = new ArrayList<>(shapes);
      }
      for (IShape shape : copies) {
        // First find out the color information of the shape
        Color color = new Color(
                (int) shape.getColor().getRed(),
                (int) shape.getColor().getGreen(),
                (int) shape.getColor().getBlue());
        g.setColor(color);

        switch (shape.getClass().getSimpleName().toLowerCase()) {
          case "rectangle":
            // draw a rectangle when the shape is rectangle
            g.fillRect((int) shape.getCenterPoint().getX(),
                    (int) shape.getCenterPoint().getY(),
                    (int) shape.getXWidth(),
                    (int) shape.getYHeight());
            break;
          case "oval":
            // draw an oval when the shape is oval
            g.fillOval((int) shape.getCenterPoint().getX(),
                    (int) shape.getCenterPoint().getY(),
                    (int) shape.getXWidth(),
                    (int) shape.getYHeight());
            break;
        }
      }
    }
  }

  private IController controller;

  private JPanel buttonPanel; // panel to hold all the buttons
  private DrawingPanel drawingPanel; // panel to show the graphics
  private JTextArea snapshotDescriptionArea; // area to show the description and time stamp
  private JComboBox<String> snapshotSelector; // selection area allows you to select according to time stamp
  private List<ISnapshot> snapshots; // all the snapshots in one list

  // buttons to navigate through the album
  private JButton prevButton;
  private JButton nextButton;
  private JButton selectButton;
  private JButton quitButton;

  // current visited snapshot in the list
  private int currentSnapshotIndex = 0;

  /**
   * Constructor for the swing view
   *
   * @param width  the width of the view
   * @param height the height of the view
   */
  public SwingView(int width, int height) {
    createInterface(width, height);
  }

  @Override
  public void setController(IController controller) {
    this.controller = controller;
  }

  /**
   * Drawing all the shapes in single snapshot and show it to the screen.
   *
   * @param snapshot the snapshot to be displayed
   */
  @Override
  public void displaySingleSnapshot(ISnapshot snapshot) {
    // Receive all the shapes data stored in single snapshot
    List<IShape> currentShapesInSnapshot = snapshot.getCurrentShapes();
    // tell drawing panel to draw all the shapes
    drawingPanel.drawShapes(currentShapesInSnapshot);
    // show the time stamp and description of this snapshot
    snapshotDescriptionArea.setText(snapshot.getTimestamp().toString() + "\n" + snapshot.getDescription());
    setTitle("CS5004 Photo Album Viewer ");
    // let the time stamp selector update as well
    snapshotSelector.setSelectedIndex(currentSnapshotIndex);

  }

  /**
   * Show first snapshot as default view.
   */
  @Override
  public void showView() {
    if (!snapshots.isEmpty()) {
      displaySingleSnapshot(snapshots.getFirst());
    }
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Create the size and view of the frame.
   *
   * @param width  int.
   * @param height int.
   */
  private void createInterface(int width, int height) {
    setTitle("Photo Album");
    setSize(width, height);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Description area placed in the top
    snapshotDescriptionArea = new JTextArea(2, 40);
    snapshotDescriptionArea.setEditable(false);
    add(new JScrollPane(snapshotDescriptionArea), BorderLayout.NORTH);

    // Drawing panel placed in the middle
    drawingPanel = new DrawingPanel();
    add(new JScrollPane((drawingPanel)), BorderLayout.CENTER);


    // Buttons
    buttonPanel = new JPanel();
    prevButton = new JButton("Prev");
    nextButton = new JButton("Next");
    snapshotSelector = new JComboBox<>();
    selectButton = new JButton("Select");
    quitButton = new JButton("Quit");

    // all buttons placed in the bottom
    buttonPanel.add(prevButton);
    buttonPanel.add(snapshotSelector);
    buttonPanel.add(selectButton);
    buttonPanel.add(nextButton);
    buttonPanel.add(quitButton);
    add(buttonPanel, BorderLayout.SOUTH);
    snapshotSelector.setVisible(false);

    // Event Listeners
    // previous button event listener
    prevButton.addActionListener(e -> {
      if (currentSnapshotIndex > 0) {
        currentSnapshotIndex--;
        displaySingleSnapshot(snapshots.get(currentSnapshotIndex));
      } else {
        showMessage("No previous snapshot.");
      }
    });
    // next button event listener
    nextButton.addActionListener(e -> {
      if (currentSnapshotIndex < snapshots.size() - 1) {
        currentSnapshotIndex++;
        displaySingleSnapshot(snapshots.get(currentSnapshotIndex));

      } else {
        showMessage("End of the photo album. No snapshots to show.");
      }
    });
    // time stamp selector button event listener
    selectButton.addActionListener(e -> snapshotSelector.setVisible(true));
    snapshotSelector.addActionListener(e -> {
      int selectedIndex = snapshotSelector.getSelectedIndex();
      if (selectedIndex != -1) {
        currentSnapshotIndex = selectedIndex;
        displaySingleSnapshot(snapshots.get(currentSnapshotIndex));
      }
    });
    // quit button event listener
    quitButton.addActionListener(e -> {
      System.exit(0);
    });

    setVisible(true);
  }

  /**
   * Store all the snapshots information locally in the view.
   *
   * @param snapshots the list of snapshots
   */
  @Override
  public void setSnapshots(List<ISnapshot> snapshots) {
    this.snapshots = snapshots;
    snapshotSelector.removeAllItems();
    // let the snapshotSelector menu add all the time stamps as the identifier
    // of the snapshots
    snapshots.forEach(snapshot
            -> snapshotSelector.addItem(snapshot.getTimestamp().toString()));
  }
}