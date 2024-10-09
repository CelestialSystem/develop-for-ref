package org.filestack.transforms.tasks;

import static org.junit.Assert.assertTrue;

import org.filestack.transforms.TransformTask;
import org.filestack.transforms.tasks.FlipTask;
import org.junit.Test;

public class TestFlipTask {

  @Test
  public void testToString() {
    String correct = "flip";

    TransformTask task = new FlipTask();

    String output = task.toString();

    String message = String.format("Task string malformed\nCorrect: %s\nOutput: %s",
        correct, output);
    assertTrue(message, output.equals(correct));
  }
}
