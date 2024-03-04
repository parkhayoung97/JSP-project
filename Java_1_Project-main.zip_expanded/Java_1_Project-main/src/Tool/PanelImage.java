package Tool;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelImage extends JPanel{
	
		 //움직이는 그림
		
		 	public ImageIcon backIc = new ImageIcon("Image\\Main2.png");
			public Image backImg = backIc.getImage();
			

			
		
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backImg, -125, 0, this);
			}
		}



