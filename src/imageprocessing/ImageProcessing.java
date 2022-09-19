package imageprocessing;

import imageprocessing.controller.ImageProcessingController;
import imageprocessing.controller.ImageProcessingGuiController;
import imageprocessing.controller.SimpleIPController;
import imageprocessing.controller.SimpleIPGuiController;
import imageprocessing.view.ImageProcessingGuiView;
import imageprocessing.view.ImageProcessingTextView;
import imageprocessing.view.ImageProcessingView;
import imageprocessing.view.SimpleIPGuiView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Represents an image processing program, which can load and save images and perform image
 * processing on them. Currently, supports ASCII PPM images.
 */
public class ImageProcessing {

  /**
   * The main method of the program. Allows the user to provide a path to a script file as a command
   * line argument, or defaults to System.in if no script file is provided.
   *
   * @param args the arguments to the program.
   * @throws IllegalArgumentException if a file is not found for the fileReader.
   */
  public static void main(String[] args) throws IllegalArgumentException {
    Readable input = null;

    for (int i = 0; i < args.length; i++) { // determine input type if optional args are provided
      if (args[i].equalsIgnoreCase("-file")) {
        try {
          input = new FileReader(args[i + 1]); // take in the provided file as the input source
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
          throw new IllegalArgumentException("File path was not provided.");
        }
      } else if (args[i].equalsIgnoreCase("-text")) {
        input = new InputStreamReader(System.in); // have user type inputs
      }
    }

    // create the controller and run the program
    if (args.length != 0) { // -file and -text modes
      ImageProcessingView view = new ImageProcessingTextView(System.out);
      ImageProcessingController controller = new SimpleIPController(input, view);
      controller.processImage();
    } else { // gui (default) mode
      ImageProcessingGuiView view = new SimpleIPGuiView();
      ImageProcessingGuiController controller = new SimpleIPGuiController(view);
    }
  }
}
