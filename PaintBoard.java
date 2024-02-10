import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class PaintBoard extends JFrame {
    static JFrame paintBoard;
    static JPanel choicePanel = new JPanel(new BorderLayout());
    static JPanel colorChoicePanel;
    static JPanel shapeChoicePanel;
    static JPanel drawPanel;
    static JButton rectangular;
    static JButton oval;
    static JButton withPen;
    static int w = 900;
    static int h = 600;
    static int x;
    static int x2;
    static int x3;
    static int y;
    static int y2;
    static int y3;
    static boolean drawRectangular=false;
    static boolean drawOval=false;
    static boolean drawWithPen=false;
    static Color color=Color.BLACK;//default color
    static ArrayList<Rectangular> rectangulars= new ArrayList<>();
    static ArrayList<Oval> ovals= new ArrayList<>();
    static Color color2=Color.BLACK;;


    public PaintBoard(){
        setTitle("Paint Board");
        setSize(w,h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        setLayout(new BorderLayout());

    }

    static class ColorChoice extends JPanel{
        public ColorChoice(){
            setBackground(Color.WHITE);
            setLayout(new FlowLayout());
        }

    }
    static class ShapeChoice extends JPanel{
        public ShapeChoice(){
            setBackground(Color.WHITE);
            setLayout(new FlowLayout());
        }
    }
    static class Rectangular{
        int valueOfX;
        int valueOfY;
        int width;
        int height;
        Color c;


        public Rectangular(){
        }
        public Rectangular(int valueOfX, int valueOfY, int width, int height, Color c){
            this.valueOfX=valueOfX;
            this.valueOfY=valueOfY;
            this.width=width;
            this.height=height;
            this.c=c;
        }
        public void drawRect(Graphics g){
            //g.setColor(color);
            g.setColor(c);
            g.fillRect(x,y,width,height);
        }
    }
    static class Oval extends Rectangle{
        int valueOfX;
        int valueOfY;
        int width;
        int height;
        Color c;
        public Oval(){
        }
        public Oval(int valueOfX, int valueOfY, int width, int height, Color c){
            this.valueOfX=valueOfX;
            this.valueOfY=valueOfY;
            this.width=width;
            this.height=height;
            this.c=c;
        }
        public void drawOval(Graphics g){
            g.setColor(c);
            g.fillOval(valueOfX,valueOfY,width,height);
        }
    }
    static class Draw extends JPanel implements MouseInputListener{
        public Draw(){
            setBackground(Color.cyan);
            setLayout(new BorderLayout());
            addMouseListener(this);
            addMouseMotionListener(this);
        }
        public void paint(Graphics g){
            g.setColor(color);
            if(drawRectangular){
                Rectangular r = new Rectangular();
                r=new Rectangular(Math.min(x,x2),Math.min(y,y2),Math.abs(x2-x),Math.abs(y2-y),color);
                g.setColor(r.c);
                r.drawRect(g);
                rectangulars.add(r);
            }
            if(drawOval){
                Oval o = new Oval(Math.min(x,x2),Math.min(y,y2),Math.abs(x2-x),Math.abs(y2-y),color);
                g.setColor(o.c);
                o.drawOval(g);
                ovals.add(o);
            }
            if(drawWithPen){
                g.fillOval(x3,y3,6,6);
            }

        }
        @Override
        public void mouseClicked(MouseEvent e) {
            x3=e.getX();
            y3=e.getY();
            paint(getGraphics());
        }
        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            x2=e.getX();
            y2=e.getY();
            paint(getGraphics());
            //repaint();
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            x3=e.getX();
            y3=e.getY();
            if(drawWithPen){
                paint(getGraphics());
            }
        }
        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
    static class Button extends JButton implements ActionListener {
        public Button(){
            super();
        }
        public Button(String s){
            super(s, null);
            setActionCommand(s);
            setBackground(Color.lightGray);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if(command.equals("Dikdortgen Ciz")){
                drawRectangular = true;
                drawOval=false;
                drawWithPen=false;
            }
            if(command.equals("Oval Ciz")){
                drawRectangular = false;
                drawOval=true;
                drawWithPen=false;
            }
            if(command.equals("Kalemle Ciz")) {
                drawRectangular = false;
                drawOval = false;
                drawWithPen = true;

            }
        }
    }

    static class Colors  extends JPanel implements MouseInputListener {
        public Colors(){
            super();
        }
        public Colors(Color c){
            setBackground(c);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            color=e.getComponent().getBackground();
            color2=e.getComponent().getBackground();

        }
        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        paintBoard = new PaintBoard();
        colorChoicePanel = new ColorChoice();
        shapeChoicePanel = new ShapeChoice();
        drawPanel = new Draw();

        rectangular = new Button("Dikdortgen Ciz");
        rectangular.addActionListener(new Button());
        oval = new Button("Oval Ciz");
        oval.addActionListener(new Button());
        withPen = new Button("Kalemle Ciz");
        withPen.addActionListener(new Button());
        shapeChoicePanel.add(rectangular);
        shapeChoicePanel.add(oval);
        shapeChoicePanel.add(withPen);

        Colors blue = new Colors(Color.BLUE);
        blue.addMouseListener(new Colors());
        Colors red = new Colors(Color.RED);
        red.addMouseListener(new Colors());
        Colors green = new Colors(Color.GREEN);
        green.addMouseListener(new Colors());
        Colors yellow = new Colors(Color.YELLOW);
        yellow.addMouseListener(new Colors());
        Colors orange = new Colors(Color.ORANGE);
        orange.addMouseListener(new Colors());
        Colors purple = new Colors(Color.magenta);
        purple.addMouseListener(new Colors());
        Colors black = new Colors(Color.BLACK);
        black.addMouseListener(new Colors());
        colorChoicePanel.add(blue);
        colorChoicePanel.add(red);
        colorChoicePanel.add(green);
        colorChoicePanel.add(yellow);
        colorChoicePanel.add(orange);
        colorChoicePanel.add(purple);
        colorChoicePanel.add(black);

        choicePanel.add(colorChoicePanel, BorderLayout.NORTH);
        choicePanel.add(shapeChoicePanel, BorderLayout.SOUTH);

        paintBoard.add(choicePanel,BorderLayout.NORTH);
        paintBoard.getContentPane().add(drawPanel,BorderLayout.CENTER);

        paintBoard.setVisible(true);
    }
}

