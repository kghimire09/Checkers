//Checkers: This exercise is a slightly modified version of Exercise 4.14 from Roberts. Create a program to:
//        Create a background rectangle so it fills the graphics window in the smaller dimension i.e. select
//        the smaller of width and height of the window for the size of the blocks (making the checkerboard square),
//        then center the checkerboard in the larger dimension.Alternate between two colors (e.g. red and black), on an 8 by 8 board.
//        You may choose other colors if you wish, as long as they are different and the checkers show up.Display checkers in the first
//        three and last three rows in alternating colors, as illustrated in the textbook. Again, you may choose your own colors, as long
//        as the checkers display correctly. Make your checkers centered in their square, and 80% of the square size. It may be helpful to
//        notice the checkers always begin on black squares, in the first three and last three rows of the board.
//        Note that the Checker Board should resize itself as the windows is resized i.e. the number of rows and columns will stay fixed,
//        but the squares will change size. The board should always be the largest centered square possible in the frame.


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Checkers extends JFrame{
    private final DrawingPanel drawingPanel=new DrawingPanel();
    public class DrawingPanel extends JPanel{
        private int COLUMNS=8;
        private int ROWS=8;
        private Rectangle Rect;
        private double Width, Height;

        public DrawingPanel(){
            setBackground(Color.gray);
        }
        @Override
        protected void paintComponent(Graphics graphic) {
            super.paintComponent(graphic);
            rectangleDrawing(graphic);
            checkersDrawing(graphic);
        }
        double x;
        double y;
        public void rectangleDrawing(Graphics graphic){
            Graphics2D graphic2D = (Graphics2D) graphic.create();
            if (getHeight()>getWidth()){
                Rect = new Rectangle(0,0,drawingPanel.getWidth(),drawingPanel.getWidth());
                Rectangle2D.Double rect = new Rectangle2D.Double(0,(float)(drawingPanel.getHeight()-drawingPanel.getWidth())/2,drawingPanel.getWidth(),drawingPanel.getWidth());
                graphic2D.fill(rect);
                x = 0;
                y = (float)(getHeight() - getWidth()) / 2;
            } else if (getWidth()>getHeight()){
                Rect = new Rectangle(0, 0, drawingPanel.getHeight(), drawingPanel.getHeight());
                Rectangle2D.Double rect = new Rectangle2D.Double((float)(drawingPanel.getWidth()-drawingPanel.getHeight())/2,0,drawingPanel.getHeight(),drawingPanel.getHeight());
                graphic2D.fill(rect);
                x=(float)(getWidth()-getHeight())/2;
                y=0;
            }
            Height=(float)Rect.getHeight()/ROWS;
            Width=(float)Rect.getWidth()/COLUMNS;
        }
        public Color color1 = Color.WHITE;
        public Color color2 = Color.BLACK;
        public void checkersDrawing(Graphics g){
            Graphics2D graphic2D = (Graphics2D) g.create();

            double a=x+Width*0.1;
            double b=y+Height*0.1;
            for(int i=0;i<ROWS;i++){
                for (int j=0;j<COLUMNS;j++){
                    if ((i%2==0&&j%2==0)||(i%2==1 && j%2==1)){
                        graphic2D.setColor(color1);
                        Rectangle2D.Double rect = new Rectangle2D.Double(x,y,Width,Height);
                        graphic2D.fill(rect);
                    }
                    else
                    {
                        graphic2D.setColor(color2);
                        Rectangle2D.Double rect = new Rectangle2D.Double(x,y,Width,Height);
                        graphic2D.fill(rect);
                        // checkers
                        if (i<3||i>4) {
                            graphic2D.setColor(color1);
                            Ellipse2D.Double circle = new Ellipse2D.Double((a), (b), (Width * 0.8), (Height * 0.8));
                            graphic2D.fill(circle);
                        }
                    }
                    x+=Width;
                    a+=Width;
                }
                b+=Height;
                if (getHeight() > getWidth()) {
                    x = 0;
                }
                else{
                    x = (float)(getWidth()-getHeight())/2 ;
                }
                a = x + Width*0.1;
                y+=Height;
            }
            repaint();
        }
    }
    public Checkers(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,640);
        add(drawingPanel);
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Checkers().setVisible(true);
            }
        });
    }
}

