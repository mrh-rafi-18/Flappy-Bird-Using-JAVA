package cse202_flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;



public class CSE202_FlappyBird  implements MouseListener ,ActionListener {
 public static CSE202_FlappyBird fbird;
 public GraphicsCreate gc;
 public boolean start,over;
 int height=800,width=800,cheight=650;
 static Rectangle bird,bonus;
 static Rectangle obs[]=new Rectangle[4];
 static int points;
 static int move=10;
 static int fall=10;
 static int fly=10;
 Random rand;
 static boolean shift;
    
    public CSE202_FlappyBird (){
        
        JFrame jf=new JFrame();
        Timer t=new Timer(20,this);
        rand=new Random();
        gc=new GraphicsCreate();
        jf.add(gc);
        jf.setSize(width,height);
        
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.getContentPane().setBackground( new Color(135, 206, 235) );
        jf.setLayout(null);
        jf.setResizable(false);
        
        jf.addMouseListener(this);
        
        bird=new Rectangle(width-510,height-400,30,30);
        
        createObstacle(false);
       
        t.start();
      
    }
    
    void createObstacle(boolean run){
        int wids=100;
        int gap=200;
        int x=800;
        int hgt=50+rand.nextInt(gap);
        
        if(!run){
            obs[0]=new Rectangle(x,0,wids,hgt);
            obs[1]=new Rectangle(x,obs[0].height+gap,wids,cheight-hgt-gap);
            obs[2]=new Rectangle(obs[0].x+gap+400,0,wids,hgt+100);
            obs[3]=new Rectangle(obs[0].x+gap+400,obs[2].height+gap,wids,cheight-obs[2].height-gap);
            bonus=new Rectangle(obs[0].x+300,hgt+50,20,20);
        }
        else if(run){
            obs[0]=new Rectangle(obs[0].x-move,0,wids,obs[0].height);
            obs[1]=new Rectangle(obs[1].x-move,obs[0].height+gap,wids,obs[1].height);
            obs[2]=new Rectangle(obs[2].x-move,0,wids,obs[2].height);
            obs[3]=new Rectangle(obs[3].x-move,obs[2].height+gap,wids,obs[3].height);
            bonus=new Rectangle(bonus.x-move,bonus.y,20,20);
        }
        
    }
    
    void moveObstacle(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(obs[0].x, obs[0].y, obs[0].width, obs[0].height);
        g.fillRect(obs[1].x, obs[1].y, obs[1].width, obs[1].height);
        g.fillRect(obs[2].x, obs[2].y, obs[2].width, obs[2].height);
        g.fillRect(obs[3].x, obs[3].y, obs[3].width, obs[3].height);
        g.setColor(Color.red);
        g.fillRect(bonus.x, bonus.y, bonus.width, bonus.height);
        
    }
    
   
    void Fly_bird(){
        shift=true;
        j=1;
       if(!start|| over)
       {
           start=true;
           over=false;
           points=0;
           
       }
        
    }
    
    
    void startWindow(Graphics g){
       
        g.setColor(Color.GREEN);
        g.fillRect(0,650,width,150);
        
        g.setColor(Color.WHITE);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);
        
        
        moveObstacle(g);
        
        
        
        g.setColor(Color.darkGray);
        g.setFont(new Font("Times New Roman", 1, 70));
        if(!start){
        g.drawString("Click mouse to start", width-700, height-450);
        }
        else if(start){
            g.drawString(String.valueOf(points), width-400, height-750);
        }
        
        if(over){
            g.drawString(String.valueOf(points), width-400, height-750);
            g.drawString("Game over", width-550, height-450);
            g.setFont(new Font("Times New Roman", 1, 20));
            g.drawString("Click to start again", width-470, height-430);
        }
        
     
      
        
         
       
    }

    public static void main(String[] args) {
       fbird=  new CSE202_FlappyBird();
      
    }

  int i=0;
  int j=1;
    @Override
    public void actionPerformed(ActionEvent ae) {
   
    int h=rand.nextInt(200);
     
     if(obs[1].x==200&&i<2){
         i++;
     }    
     
      if(obs[2].x==200){
        obs[0].x=800;
        obs[1].x=800;
        obs[0].height=h;
        obs[1].height=cheight-h-200;
      }
      
      if(obs[1].x==200&&i==2){
        obs[2].x=800;
        obs[3].x=800;
        obs[2].height=h+100;
        obs[3].height=cheight-obs[2].height-200;
      }
      
      if(obs[0].x==-320){
        bonus.x=obs[2].x+371;
        bonus.y=h+50;
      }
      
      if(obs[2].x==-320){
        bonus.x=obs[0].x+370;
        bonus.y=h+50;  
      }
      
      if(obs[0].intersects(bird)||obs[1].intersects(bird)||obs[2].intersects(bird)||obs[3].intersects(bird)){
          over=true;
          bird.y=400;
      }
      
      
      if(!over&&start){
        createObstacle(true);
      }
      else{
          createObstacle(false);
      }
     
     if(bird.y!=620&&start&& !over && !shift ){
     bird.y=bird.y+fall;
     }
     
     if(bird.intersects(bonus)){
         
         points+=3;
         bonus.y=800;
     }
     

   
     
     
       if(j<=15&&shift&&!over){
            
            bird.y=bird.y-fly;
            j++;
            if(j==15||bird.y==00){
                j=1;
                shift=false;
            }
        }
     
     
     
     
     if((obs[0].x+110==bird.x||obs[2].x+110==bird.x&&move==10)||(obs[0].x+105==bird.x||obs[2].x+105==bird.x&&move==10)){
         points++;       
     }
         
         
     gc.repaint();
}

    @Override
    public void mouseClicked(MouseEvent me) {
        
        Fly_bird();
        
  }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void mousePressed(MouseEvent me) {
 }

    @Override
    public void mouseReleased(MouseEvent me) {
  }

    @Override
    public void mouseEntered(MouseEvent me) {
  }

    @Override
    public void mouseExited(MouseEvent me) {
   }
}
