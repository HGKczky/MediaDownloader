import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MediaDownloader{
    static JLabel WarningTag;
    
    public static void main(String[] args) {
        MakeMainWindow();
    }
    public static void MakeMainWindow(){
	
	System.out.println("Hello World");
        ImageIcon backgroundImage = new ImageIcon("background.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        JFrame frame = new JFrame("Media Downloader");
        frame.add(backgroundLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        frame.setResizable(false);

        JLabel ProgramName = new JLabel("Media Downloader");
        ProgramName.setBounds(((backgroundImage.getIconWidth() - 600) / 2), 100, 600, 100);
        ProgramName.setFont(new Font("Arial", Font.BOLD, 60));
        ProgramName.setHorizontalAlignment(SwingConstants.CENTER); 

        JTextField LinkField = new JTextField();
        LinkField.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 300, 300, 50);
        LinkField.setFont(new Font("Arial", Font.PLAIN, 20));

        JTextField NameField = new JTextField();
        NameField.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 400, 300, 50);
        NameField.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel nameLabel = new JLabel("Enter Link");
        nameLabel.setBounds(((backgroundImage.getIconWidth() - 250) / 2), 260, 250, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); 

        JLabel linkLabel = new JLabel("Enter Filename");
        linkLabel.setBounds(((backgroundImage.getIconWidth() - 250) / 2), 360, 250, 30);
        linkLabel.setFont(new Font("Arial", Font.BOLD, 25));
        linkLabel.setHorizontalAlignment(SwingConstants.CENTER); 

        WarningTag = new JLabel(" ");
        WarningTag.setBounds(((backgroundImage.getIconWidth() - 600) / 2), 560, 600, 30);
        WarningTag.setFont(new Font("Arial", Font.BOLD, 15));
        WarningTag.setForeground(Color.RED);
        WarningTag.setHorizontalAlignment(SwingConstants.CENTER); 

        JButton DownloadButton = new JButton("Dowload Media"); 
        DownloadButton.setBounds(((backgroundImage.getIconWidth() - 300) / 2), 500, 300, 50);
        DownloadButton.setBackground(Color.white);
        DownloadButton.setFont(new Font("Arial", Font.BOLD, 25));
        backgroundLabel.add(DownloadButton);
        DownloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String linkname = LinkField.getText();
                String Filename = NameField.getText();
                if (Filename == null){
                    System.out.println("Please Enter a filename");
                }
                DownloadMedia(linkname, Filename);
            }       
        });

        backgroundLabel.add(ProgramName);
        backgroundLabel.add(WarningTag);
        backgroundLabel.add(linkLabel);
        backgroundLabel.add(nameLabel);
        backgroundLabel.add(LinkField);
        backgroundLabel.add(NameField);
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
        catch (Exception exception ){
            WarningTag.setText("Error: " + exception.getMessage() + " is not a valid link.");
        };
    }
}
