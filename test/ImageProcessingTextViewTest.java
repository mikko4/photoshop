import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import imageprocessing.view.ImageProcessingTextView;
import imageprocessing.view.ImageProcessingView;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the image processing text view.
 */
public class ImageProcessingTextViewTest {

  private Appendable out;
  private Appendable badOut;
  private ImageProcessingView view;

  @Before
  public void init() {
    out = new StringBuilder();
    badOut = new BadAppendable();
    view = new ImageProcessingTextView(out);
  }

  @Test
  public void testInvalidConstructor() {
    try {
      view = new ImageProcessingTextView(null);
      fail("did not throw exception when given null appendable destination");
    } catch (IllegalArgumentException e) {
      assertEquals("destination cannot be null", e.getMessage());
    }
  }

  @Test
  public void testRenderMessage() {
    view.renderMessage("a cool message");
    assertEquals("a cool message", out.toString());
    view.renderMessage("\nanother cool message!");
    assertEquals("a cool message\nanother cool message!", out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testRenderMessageException() {
    view = new ImageProcessingTextView(badOut);
    view.renderMessage("i wonder what will happen when i render this message");
  }

  @Test
  public void testRenderHistogram() {
    //    try {
    //      ManipulableImage img = new GenericFileHandler().load("nyc.png");
    //      Map<Integer, Integer> histogram = img.histogram(pixel -> pixel.getChannels()[0]);
    //      ImageProcessingTextView view1 = new ImageProcessingTextView(out);
    //      view1.renderHistogram(img, (pixel -> pixel.getChannels()[0]), );
    //    } catch (IOException e) {
    //      fail();
    //    }

  }
}