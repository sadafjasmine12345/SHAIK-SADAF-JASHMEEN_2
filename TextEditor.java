//Import Statements: 
//The program uses Swing for the GUI components and the java.io package for file operations.
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
//Class Declaration: TextEditor extends JFrame and implements ActionListener.
public class TextEditor extends JFrame implements ActionListener {
    // Text area to display and edit text
    private JTextArea textArea;
    // Scroll pane for text area
    private JScrollPane scrollPane;
    // Menu bar and its items
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem, saveItem, exitItem;

    public TextEditor() {
        // Set the title of the window
        setTitle("Text Editor");
        // Set the size of the window
        setSize(800, 600);
        // Set the default close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize the text area
        textArea = new JTextArea();
        // Initialize the scroll pane with the text area
        scrollPane = new JScrollPane(textArea);
        // Add the scroll pane to the content pane
        add(scrollPane);

        // Initialize the menu bar
        menuBar = new JMenuBar();
        // Initialize the file menu
        fileMenu = new JMenu("File");
        // Initialize the menu items
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        // Add action listeners to the menu items
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        // Add the menu items to the file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add the file menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);
    }
 // actionPerform method
//    Handles actions for the menu items.
//    Calls the appropriate method (openFile, saveFile, or exits the application).
    @Override
    public void actionPerformed(ActionEvent e) {
        // Determine which menu item was clicked
        if (e.getSource() == openItem) {
            openFile();
        } else if (e.getSource() == saveItem) {
            saveFile();
        } else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }
//    openFile Method:
//    	Uses a JFileChooser to let the user select a text file.
//    	Reads the content of the file into the text area.
    private void openFile() {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        // Set the file filter to only show .txt files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        // Show the open dialog
        int option = fileChooser.showOpenDialog(this);
        // If the user approves, open the file
        if (option == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File file = fileChooser.getSelectedFile();
            try {
                // Create a reader for the file
                BufferedReader reader = new BufferedReader(new FileReader(file));
                // Read the file into the text area
                textArea.read(reader, null);
                // Close the reader
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
//    saveFile Method:
//    	Uses a JFileChooser to let the user choose a location to save the text file.
//    	Writes the content of the text area to the file.
    private void saveFile() {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        // Set the file filter to only show .txt files
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        // Show the save dialog
        int option = fileChooser.showSaveDialog(this);
        // If the user approves, save the file
        if (option == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File file = fileChooser.getSelectedFile();
            try {
                // Create a writer for the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                // Write the text area content to the file
                textArea.write(writer);
                // Close the writer
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
//    main Method:
//    	Creates an instance of the TextEditor and makes it visible.
    public static void main(String[] args) {
        // Create and show the text editor
        TextEditor editor = new TextEditor();
        editor.setVisible(true);
    }
}
