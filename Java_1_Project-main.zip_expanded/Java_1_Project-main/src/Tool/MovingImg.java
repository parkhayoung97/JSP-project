package Tool;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovingImg extends JLabel{
	 //움직이는 그림
	
	 	public ImageIcon backIc = new ImageIcon("Image\\Airplane3.png");
		public Image backImg = backIc.getImage();
		int backY = 0;
		int backX = 0;
	
		public MovingImg() {			
			setFocusable(true);
			new Thread(new Runnable() {
	
				@Override
				public void run() {
					while (true) {
						backY--;
						backX--;
						repaint();
	
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backImg, backX, backY, this);
		}
	}

