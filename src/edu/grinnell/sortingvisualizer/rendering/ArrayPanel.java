package edu.grinnell.sortingvisualizer.rendering;

import java.awt.*;
import javax.swing.JPanel;
import edu.grinnell.sortingvisualizer.audio.NoteIndices;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

  private NoteIndices notes;

  /**
   * Constructs a new ArrayPanel that renders the given note indices to the screen.
   * 
   * @param notes the indices to render
   * @param width the width of the panel
   * @param height the height of the panel
   */
  public ArrayPanel(NoteIndices notes, int width, int height) {
    this.notes = notes;
    this.setPreferredSize(new Dimension(width, height));
  }

  @Override
  public void paintComponent(Graphics g) {
    // TODO: fill me in
    g.setColor(Color.blue);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    int width = this.getWidth() / this.notes.arr.length;
    int height = this.getHeight() / this.notes.arr.length;
    g.setColor(Color.white);
    for (int i = 0; i < this.notes.arr.length; i++) {
      if (this.notes.isHighlighted(i)) {
        g.setColor(Color.orange);
      }
      g.fillRect(i * width, this.getHeight() - height * this.notes.arr[i], width,
          height * this.notes.arr[i]);
    } // for
  }// paintComponent

}
