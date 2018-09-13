import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
class DrawTree extends JComponent {

        final Color bg = Color.white;
        final Color fg = Color.black;
        final Color red = Color.red;
        final Color white = Color.white;
        final BasicStroke stroke = new BasicStroke((float) 2.0);
        final BasicStroke wideStroke = new BasicStroke((float) 8.0);

        Dimension totalSize;
        int height, width;
        Node r = null;

        public void init(Node N, int x) {
            // Initialize drawing colors
            setBackground(bg);
            setForeground(fg);
            r = N;
            width = x;
        }

        Graphics2D g2;

        public void paint(Graphics g) {
            g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            getSize();
            inorder(r, 0, width, 80);
        }

        public void draw(int x1, int x2, int y, String n, int d) {
            g2.setStroke(stroke);

            g2.setPaint(Color.black);
            int x = (x1 + x2) / 2;
            if (d == 1)
                g2.draw(new Line2D.Double(x2, y - 30, x + 15, y));
            else if (d == 2)
                g2.draw(new Line2D.Double(x + 15, y, x1 + 30, y - 30));
            g2.setPaint(Color.blue);
            Shape circle = new Ellipse2D.Double((x1 + x2) / 2, y, 30, 30);
            g2.draw(circle);
            g2.fill(circle);
            g2.setPaint(Color.white);
            g2.drawString(n, x + 10, y + 18);
        }

        int x1 = 500, y1 = 30;

        void inorder(Node r, int x1, int x2, int y) {
            if (r == null)
                return;

            inorder(r.left, x1, (x1 + x2) / 2, y + 40);
            draw(x1, x2, y, String.valueOf(r.value) + "", 0);
            inorder(r.right, (x1 + x2) / 2, x2, y + 40);
        }
    }