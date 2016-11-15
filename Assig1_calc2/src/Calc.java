import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Date - 9/9/16
 * Created by - rk
 * Dual Thread Implementation of Calculator
 */

class Calc
{
    /**
     * Initializing Jframe, TextField,Buttons for Calculator
     * butPointer for tracking the storing the value of last button clicked
     * num_enter tracking the input of integer (pressing of ENTER)
     * ops_enter tracking the input of operator (pressing of SPACE)
     */
    JFrame f;
    static JTextField t;

    static double a=0,b=0,result=0;
    static int operator=0;
    static JButton[] buttons = new JButton[15];
    static int butPointer = 0;
    static boolean num_enter = true;
    static boolean ops_enter = true;

    Calc()
    {
        f=new JFrame("Calculator");
        t=new JTextField();

        /**
         * Setting the value of Button in calculator
         * Setting the size of frame and buttons
         */
        buttons[0]=new JButton("1");
        buttons[1]=new JButton("2");
        buttons[2]=new JButton("3");
        buttons[3]=new JButton("4");
        buttons[4]=new JButton("5");
        buttons[5]=new JButton("6");
        buttons[6]=new JButton("7");
        buttons[7]=new JButton("8");
        buttons[8]=new JButton("9");
        buttons[9]=new JButton("0");
        buttons[10]=new JButton("/");
        buttons[11]=new JButton("*");
        buttons[12]=new JButton("-");
        buttons[13]=new JButton("=");
        buttons[14]=new JButton("+");

        t.setBounds(30,40,280,30);
        buttons[0].setBounds(40,100,50,40);
        buttons[1].setBounds(110,100,50,40);
        buttons[2].setBounds(180,100,50,40);
        buttons[3].setBounds(250,100,50,40);

        buttons[4].setBounds(40,170,50,40);
        buttons[5].setBounds(110,170,50,40);
        buttons[6].setBounds(180,170,50,40);
        buttons[7].setBounds(250,170,50,40);

        buttons[8].setBounds(40,240,50,40);
        buttons[9].setBounds(110,240,50,40);
        buttons[10].setBounds(180,240,50,40);
        buttons[11].setBounds(250,240,50,40);

        buttons[12].setBounds(40,310,50,40);
        buttons[13].setBounds(110,310,50,40);
        buttons[14].setBounds(180,310,50,40);

        /**
         * Adding elements to the Frame(f)
         */
        f.add(t);
        for(int i=0;i<15;i++){
            f.add(buttons[i]);
        }

        f.setLayout(null);
        f.setVisible(true);
        f.setSize(350,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        f.requestFocus();
        /**
         * KeyListener for ENTER and SPACE
         */

        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                if(keycode==KeyEvent.VK_ENTER){
                    num_enter = false;
                }
                if(keycode==KeyEvent.VK_SPACE){
                    ops_enter = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }

    /**
     * butPointer used to get the value of the Selected Integer or Operator
     * Then Displaying the value(result/value of integer) in TextField after every events which is done in thread.
     */
    public static void func()
    {

        String e = buttons[butPointer].getText();
        //System.out.print(e);
        String str = t.getText();

        if(str.contains(".")){
            t.setText("");
        }
        if(e=="1")
            t.setText(t.getText().concat("1"));

        if(e=="2")
            t.setText(t.getText().concat("2"));

        if(e=="3")
            t.setText(t.getText().concat("3"));

        if(e=="4")
            t.setText(t.getText().concat("4"));

        if(e=="5")
            t.setText(t.getText().concat("5"));

        if(e=="6")
            t.setText(t.getText().concat("6"));

        if(e=="7")
            t.setText(t.getText().concat("7"));

        if(e=="8")
            t.setText(t.getText().concat("8"));

        if(e=="9")
            t.setText(t.getText().concat("9"));

        if(e=="0")
            t.setText(t.getText().concat("0"));

        if(e=="+")
        {
            a=Double.parseDouble(t.getText());
            operator=1;
            t.setText("");
        }

        if(e=="-")
        {
            a=Double.parseDouble(t.getText());
            operator=2;
            t.setText("");
        }

        if(e=="*")
        {
            a=Double.parseDouble(t.getText());
            operator=3;
            t.setText("");
        }

        if(e=="/")
        {
            a=Double.parseDouble(t.getText());
            operator=4;
            t.setText("");
        }

        if(e=="=")
        {
//            System.out.print(e);
            b=Double.parseDouble(t.getText());

            switch(operator)
            {
                case 1: result=a+b;
                    break;

                case 2: result=a-b;
                    break;

                case 3: result=a*b;
                    break;

                case 4: if(b!=0)
                            result=a/b;
                        else result = 0.0;
                    break;

                default: result=0;
            }

            t.setText(""+result);
        }


    }

    /**
     * Function for Changing the Color of Buttons
     * @param index
     * @param color
     */

    public static void setColor(int index,int color){
        if(color == 0){
            buttons[index].setBackground(null);
        }
        if(color == 1){
            buttons[index].setBackground(Color.RED);
        }
        if(color == 2){
            buttons[index].setBackground(Color.CYAN);
        }
    }

    /**
     * Generating two threads
     * 1st thread will be handling the input of Integers from ENTER Button
     * 2nd thread will be handling the operator as an input from SPACE Button
     */
    public static void run(){
        new Calc();
        /**
         * Thread 1
         */
        Thread t_num = new Thread(() -> {
            int i = 0;
            while (true) {
                    setColor(i,1);
                    while(num_enter) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        setColor(i,0);
                        butPointer = i;
                        i = (i + 1) % 10;
                        setColor(i,1);

                    }
                setColor((butPointer+1)%10,0);
                func();
                num_enter = true;
            }

        });
        /**
         * Thread 2
         */
        Thread t_ops = new Thread(() -> {
            int i = 10;
            while(true){
                setColor(i,2);

                while (ops_enter) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setColor(i,0);
                    butPointer = i;
                    if (i == 14) {
                        i = 9;
                    }
                    i = (i + 1) % 15;
                    setColor(i,2);

                }
                func();
                ops_enter =true;
            }
        });
        t_num.start();
        t_ops.start();
    }

    /**
     * Calling the function run to start the threads
     * @param s
     */
    public static void main(String...s)
    {
        run();
    }
}
