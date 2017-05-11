import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Title extends JWindow {
  private int duration;

  public Title(int d) {
    duration = 5000;
  }

 
  public void showSplash() {
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.gray);

    int width = 900;
    int height = 400;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    setBounds(x, y, width, height);

    JLabel copyrt = new JLabel("SPACE RAIDERS",
        JLabel.CENTER);
    copyrt.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
    content.add(copyrt, BorderLayout.CENTER);
    Color borderGray = new Color(225,225,225);
    content.setBackground(new Color(100,0,0));
    content.setBorder(BorderFactory.createLineBorder(borderGray, 10));

    setVisible(true);

    try {
      Thread.sleep(duration);
    } 
    catch (Exception e) {
    }

    setVisible(false);
  }

}
