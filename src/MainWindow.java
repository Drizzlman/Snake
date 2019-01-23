import javax.swing.*;

public class MainWindow extends JFrame {
public MainWindow(){
    setTitle("Snake");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400,425);
    setLocation(400,400);
    add(new Field());
    setVisible(true);
}
public static void main(String [] args){
    MainWindow window = new MainWindow();
}
}
