package PetMS;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Database {
    private File file=null;
    private FileWriter fWrite=null;
    private FileReader fRead=null;
    private Scanner scan=null;
    private Vector<String> row, column;

    public Database() {
    }
    /**parameterized constructor w/c sets the filename of a textfile
     * @author martzel baste
     * @param filename */
    public Database(String filename) {
        file=new File(filename);
    }
    /**another option for setting a filename
     * @author martzel baste
     * @param filename*/
    public void setFilename(String filename) {
        new Database(filename);
    }
    /**when you want to read or get the name of the file
     * @return String - name of the file */
    public String getFilename() { return file.getName(); }
    /**universal error message
     * @param msg */
    public void errorMessage(String msg){
        JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
    }
    /**Store information to a file
     * @param records - the information to be stored */
    public void storeToFile(String records){
        try {
            fWrite=new FileWriter(file);
            fWrite.write(records);
            fWrite.flush();
        } catch (Exception e) {
            errorMessage("Error 101: storeToFile\n"+e.getMessage());
        }
    }
    public void displayRecords(DefaultTableModel model) {
        try {
            fRead = new FileReader(file);
            scan = new Scanner(fRead);

            String data[];

            while(scan.hasNext()) {
                data = scan.nextLine().split("#");
                row = new Vector<String>();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    row.add(data[i]);
                }
                model.addRow(row);
            }
        } catch (Exception e) {
            errorMessage("Error 102: displayRecords\n" + e.getMessage());
        }
    }
    public void fillToComboBox(JComboBox<String> combo) {
        try {
            fRead= new FileReader(file);
            scan = new Scanner(fRead);
            while(scan.hasNext()) {
                combo.addItem(scan.nextLine());
            }
        } catch (Exception e) {
            errorMessage("Error 103: filltoComboBox\n" + e.getMessage());
        }
    }//end of storeToFile
} //end of class