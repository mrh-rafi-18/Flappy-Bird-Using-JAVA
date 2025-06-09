package cse202_flappybird;

import java.awt.Graphics;
import javax.swing.JComponent;

public class GraphicsCreate extends JComponent{
    
    
    @Override
    protected void paintComponent(Graphics g){
       
       CSE202_FlappyBird.fbird.startWindow(g);
    }
}
