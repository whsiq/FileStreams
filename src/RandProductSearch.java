import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {
    static final String FILEPATH = "C:\\Users\\Chase\\IdeaProjects\\FileStreams\\src\\ProductTestData2.txt";
    JPanel mainPnl;
    JPanel searchPnl;
    JPanel displayPnl;
    JPanel optionPnl;
    JTextField searchTF;
    JTextArea displayTA;
    JButton searchBtn;
    JButton quitBtn;
    JScrollPane scroller;
    String keyword;

    public RandProductSearch() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createSearchPanel();
        mainPnl.add(searchPnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createOptionPanel();
        mainPnl.add(optionPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(400, 400);
        setTitle("Product Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createSearchPanel() {
        searchPnl = new JPanel();
        searchPnl.setLayout(new BorderLayout());

        searchTF = new JTextField();
        searchBtn = new JButton("Search");
        searchBtn.addActionListener((ActionEvent ae) -> searchFile());

        searchPnl.add(searchTF, BorderLayout.CENTER);
        searchPnl.add(searchBtn, BorderLayout.EAST);
    }

    public void createDisplayPanel() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridLayout(1,1));

        displayTA = new JTextArea();
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }

    public void createOptionPanel() {
        optionPnl = new JPanel();
        optionPnl.setLayout(new GridLayout(1,1));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        optionPnl.add(quitBtn);
    }

    public void searchFile() {
        if(searchTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a search term.");
        }
        else {
            displayTA.setText("");
            keyword = searchTF.getText().toLowerCase();
            try {
                RandomAccessFile file = new RandomAccessFile(FILEPATH, "r");
                String record;
                while ((record = file.readLine()) != null) { // make sure that the lines read have content
                    String ID = record.substring(0, 6).trim();
                    String name = record.substring(6,41).trim();
                    String description = record.substring(41, 116).trim();
                    String cost = record.substring(116).trim();

                    if (name.toLowerCase().contains(keyword) ||
                            description.toLowerCase().contains(keyword) ||
                            cost.toLowerCase().contains(keyword) ||
                            ID.toLowerCase().contains(keyword)) {
                        displayTA.append("ID: "+ID
                                +", Product Name: "+name
                                +", Description: "
                                +description
                                +", Cost: "+cost
                                +"\n");
                    }

                }
                file.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
