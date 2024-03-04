package Tool;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.accessibility.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

public class RoundedCornerBorder extends AbstractBorder {
  protected static final int ARC = 12;
  @Override public void paintBorder(
      Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    int r = ARC;
    int w = width  - 1;
    int h = height - 1;

    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
    if (c instanceof JPopupMenu) {
      g2.setPaint(c.getBackground());
      g2.fill(round);
    } else {
      Container parent = c.getParent();
      if (Objects.nonNull(parent)) {
        g2.setPaint(parent.getBackground());
        Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
        corner.subtract(round);
        g2.fill(corner);
      }
    }
    g2.setPaint(c.getForeground());
    g2.draw(round);
    g2.dispose();
  }
  @Override public Insets getBorderInsets(Component c) {
    return new Insets(4, 8, 4, 8);
  }
  @Override public Insets getBorderInsets(Component c, Insets insets) {
    insets.set(4, 8, 4, 8);
    return insets;
  }
}

class RoundedCornerBorder1 extends RoundedCornerBorder {
  //http://ateraimemo.com/Swing/RoundedComboBox.html
  @Override public void paintBorder(
      Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    int r = ARC;
    int w = width  - 1;
    int h = height - 1;

    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
    Rectangle b = round.getBounds();
    b.setBounds(b.x, b.y + r, b.width, b.height - r);
    round.add(new Area(b));

    Container parent = c.getParent();
    if (Objects.nonNull(parent)) {
      g2.setPaint(parent.getBackground());
      Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
      corner.subtract(round);
      g2.fill(corner);
    }

    g2.setPaint(c.getForeground());
    g2.draw(round);
    g2.dispose();
  }
}

class RoundedCornerBorder2 extends RoundedCornerBorder {
  @Override public void paintBorder(
      Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    int r = ARC;
    int w = width  - 1;
    int h = height - 1;

    Path2D.Float p = new Path2D.Float();
    p.moveTo(x, y);
    p.lineTo(x, y + h - r);
    p.quadTo(x, y + h, x + r, y + h);
    p.lineTo(x + w - r, y + h);
    p.quadTo(x + w, y + h, x + w, y + h - r);
    p.lineTo(x + w, y);
    p.closePath();
    Area round = new Area(p);

    g2.setPaint(c.getBackground());
    g2.fill(round);

    g2.setPaint(c.getForeground());
    g2.draw(round);
    g2.setPaint(c.getBackground());
    g2.drawLine(x + 1, y, x + width - 2, y);
    g2.dispose();
  }
}