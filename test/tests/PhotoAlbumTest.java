package tests;

import com.hw09.controller.IController;
import com.hw09.controller.PhotoAlbumController;
import com.hw09.model.IModel;
import com.hw09.model.ISnapshot;
import com.hw09.model.PhotoAlbumModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoAlbumTest {
  IModel model;

  IController controller;

  @BeforeEach
  void setUp() {
    model = new PhotoAlbumModel();
    controller = new PhotoAlbumController();
    controller.setModel(model);
  }

  // test whether model can create the shape after controller read through the command txt
  @Test
  void takeSnapshotsFromFile() {
    controller.takeSnapshotsFromFile("demo_input.txt");
    ISnapshot s = model.getAllSnapshots().getFirst();
    assertEquals("myrect",s.getCurrentShapes().getFirst().getName());
    assertEquals(50,s.getCurrentShapes().getFirst().getXWidth());
    assertEquals(100, s.getCurrentShapes().getFirst().getYHeight());
    assertEquals(200,s.getCurrentShapes().getFirst().getCenterPoint().getX());
    assertEquals(200,s.getCurrentShapes().getFirst().getCenterPoint().getY());
    assertEquals(255,s.getCurrentShapes().getFirst().getColor().getRed());
    assertEquals(0,s.getCurrentShapes().getFirst().getColor().getGreen());
    assertEquals(0,s.getCurrentShapes().getFirst().getColor().getBlue());
    assertEquals("myoval",s.getCurrentShapes().get(1).getName());
    assertEquals(60,s.getCurrentShapes().get(1).getXWidth());
    assertEquals(30, s.getCurrentShapes().get(1).getYHeight());
    assertEquals(500,s.getCurrentShapes().get(1).getCenterPoint().getX());
    assertEquals(100,s.getCurrentShapes().get(1).getCenterPoint().getY());
    assertEquals(0,s.getCurrentShapes().get(1).getColor().getRed());
    assertEquals(255,s.getCurrentShapes().get(1).getColor().getGreen());
    assertEquals(1,s.getCurrentShapes().get(1).getColor().getBlue());
    // total number of snapshots from txt file
    assertEquals(4,model.getAllSnapshots().size());
  }
}