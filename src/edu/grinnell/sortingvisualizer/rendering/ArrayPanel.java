package edu.grinnell.sortingvisualizer.rendering;

import java.awt.*;
import javax.swing.JPanel;
import edu.grinnell.sortingvisualizer.audio.NoteIndices;

@SuppressWarnings("serial")
public class ArrayPanel extends JPanel {

    private NoteIndices notes;
    
    /**
     * Constructs a new ArrayPanel that renders the given note indices to
     * the screen.
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
      Graphics2D g2 = (Graphics2D) g;
      super.paintComponent(g);
      g2.setBackground(Color.black);
      int width = this.getWidth() / this.notes.arr.length;
      int height = this.getHeight() / this.notes.arr.length;
      for (int i = 0; i < this.notes.n; i++) {
        g2.fillRect(i*width, 0, width, height*this.notes.arr[i]);
      }//for
    }//paintComponent
    
}