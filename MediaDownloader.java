import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MediaDownloader{
    public static void main(String[] args) {
        MakeMainWindow();
    }

    public static void MakeMainWindow(){

        ImageIcon backgroundImage = new ImageIcon("background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        JFrame frame = new JFrame("Media Downloader");
        frame.add(backgroundLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        frame.setResizable(false);

        JTextField TextField = new JTextField();
        TextField.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 400, 300, 50);
        TextField.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField MediaName = new JTextField();
        MediaName.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 300, 300, 50);
        MediaName.setFont(new Font("Arial", Font.PLAIN, 20));


        JButton DownloadButton = new JButton("Dowload Media");
        DownloadButton.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 500, 300, 50);
        DownloadButton.setBackground(Color.white);
        DownloadButton.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(DownloadButton);
        DownloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String linkname = TextField.getText();
                String Filename = MediaName.getText();
                if (Filename == null){
                    System.out.println("Please Enter a filename");
                }
                DownloadMedia(linkname, Filename);
            }       
        });
        JLabel linkLabel = new JLabel("Enter Link:");
        linkLabel.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 260, 250, 30);
        linkLabel.setFont(new Font("Arial", Font.BOLD, 30));
        backgroundLabel.add(linkLabel);

        JLabel nameLabel = new JLabel("Enter Filename:");
        nameLabel.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 360, 250, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));

        backgroundLabel.add(linkLabel);
        backgroundLabel.add(nameLabel);
        backgroundLabel.add(TextField);
        backgroundLabel.add(MediaName);
        backgroundLabel.add(DownloadButton);
        frame.setVisible(true);
    }

    public static void DownloadMedia(String linkname, String Filename){
        try{
            URL MediaLink = new URL(linkname);
            InputStream inputstr = MediaLink.openStream();
            OutputStream outputstr = new FileOutputStream(Filename);
            byte[] Stream = new byte[2048];
            int length;
            while ((length = inputstr.read(Stream)) != -1) {
                outputstr.write(Stream, 0, length);
            }
            inputstr.close();
            outputstr.close();
            
        }
        catch (Exception exception){
            System.out.println(exception);
        };
    }
}