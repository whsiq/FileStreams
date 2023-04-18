import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {

    static final String FILEPATH = "C:\\Users\\Chase\\IdeaProjects\\FileStreams\\src\\ProductTestData2.txt";
    JPanel mainPnl;
    JPanel entryPnl;
    JPanel optionPnl;
    JTextField nameTF;
    JTextField descTF;
    JTextField idTF;
    JTextField costTF;
    JTextField recordCountTF;
    JLabel nameLbl;
    JLabel descLbl;
    JLabel idLbl;
    JLabel costLbl;
    JLabel recordCountLbl;
    JButton quitBtn;
    JButton addBtn;
    int recordCount = 0;

    public RandProductMaker() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createEntryPanel();
        mainPnl.add(entryPnl, BorderLayout.CENTER);

        createOptionPanel();
        mainPnl.add(optionPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(300, 400);
        setTitle("Random Product Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void createEntryPanel() {
        entryPnl = new JPanel();
        entryPnl.setLayout(new GridLayout(5, 2));

        nameTF = new JTextField(35);
        descTF = new JTextField(75);
        idTF = new JTextField(6);
        costTF = new JTextField(); // might have to set TF length
        recordCountTF = new JTextField();
        recordCountTF.setEditable(false);

        nameLbl = new JLabel("Name:");
        descLbl = new JLabel("Description:");
        idLbl = new JLabel("ID Number:");
        costLbl = new JLabel("Cost ($):");
        recordCountLbl = new JLabel("Entries Recorded:");

        entryPnl.add(nameLbl);
        entryPnl.add(nameTF);
        entryPnl.add(descLbl);
        entryPnl.add(descTF);
        entryPnl.add(idLbl);
        entryPnl.add(idTF);
        entryPnl.add(costLbl);
        entryPnl.add(costTF);
        entryPnl.add(recordCountLbl);
        entryPnl.add(recordCountTF);


    }
    public void createOptionPanel() {
        optionPnl = new JPanel();
        optionPnl.setLayout(new GridLayout(1,2));

        addBtn = new JButton("Add");
        addBtn.addActionListener((ActionEvent ae) -> writeToFile());

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        optionPnl.add(addBtn);
        optionPnl.add(quitBtn);

    }

    private void writeToFile() {
        if (validFields()) {
            Product p = padFields(); // product with fields padded
            try {
                RandomAccessFile file = new RandomAccessFile(FILEPATH, "rw");
                file.seek(file.length());
                String product = String.format("%-6s%-35s%-75s%.2f",
                        p.getID(), p.getName(), p.getDescription(), p.getCost());
                file.writeBytes(product);
                file.write("\n".getBytes());
                file.close();

                // Add the record count
                recordCount = recordCount + 1;
                recordCountTF.setText(""+recordCount);

                // clear all fields
                clearFields();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "error");
        }

    }

    private boolean validFields() {
        // Check if fields are empty
        if (nameTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a name.");
            return false;
        }
        else if (descTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a description.");
            return false;
        }
        else if (idTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter an ID.");
            return false;
        }
        else if (costTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a cost.");
            return false;
        }
        // Check if cost value is a double
        try {
            double cost = Double.parseDouble(costTF.getText());
            return true;
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Enter a valid cost.");
            return false;
        }
    }

    private Product padFields() {
        Product product = new Product();
        product.setName(nameTF.getText());
        product.setDescription(descTF.getText());
        product.setID(idTF.getText());
        product.setCost(Double.parseDouble(costTF.getText()));

        return product;
    }

    private void clearFields() {
        nameTF.setText("");
        descTF.setText("");
        idTF.setText("");
        costTF.setText("");
    }

}
