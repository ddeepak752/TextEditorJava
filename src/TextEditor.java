import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, saveFile,openFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;

    TextEditor(){
        //Initialise frame
        frame = new JFrame();
        //Initialize textArea
        textArea = new JTextArea();
        //change background color of textarea
//        textArea.setBackground(Color.BLUE);
        //Initialise menu bar
        menuBar = new JMenuBar();

        //Create Content Pane
        JPanel panel = new JPanel();panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Add panel to frame
        frame.add(panel);

        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //Initialise menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Add scroll pane to the panel
        panel.add(scrollPane);

        //Initialise MenuItems
        //File MenuItems
        newFile = new JMenuItem("New Window");
        saveFile = new JMenuItem("Save file");
        openFile = new JMenuItem("Open File");

        //Edit Menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close Window");
        //Adding menu items to file menu

        //Add action listener to the file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add action listener to the edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);


        //Set menu bar to our frame
        frame.setJMenuBar(menuBar);
        //Set dimensions of the frame
        frame.setBounds(100,100,800,500);
        //Set frame to be visible
        frame.setVisible(true);
//        frame.add(textArea);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //if cut menu item is clicked
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        //if copy menu item is clicked
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        //if paste menu item is clicked
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        //if selectAll menu item is clicked
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        //if close menu item is clicked
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        //if newFile menu item is clicked
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow = new TextEditor();
        }
        //if openFile menu item is clicked
        if(actionEvent.getSource()==openFile){
            //Initialized a file chooser
            JFileChooser fileChooser = new JFileChooser("F:");
            //getting chosen option in file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            //if the chosen option is the approved option
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected file from file chooser
                File file = fileChooser.getSelectedFile();
                //Getting path of the selected file
                String filePath = file.getPath();
                try{
//                    BufferedReader bufferReader = new BufferedReader(new FileReader(filePath));
                    //Initialized file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialized buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //intermediate for current line and output for complete content of file
                    String intermediate = "", output = "";
                    //Read line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    bufferedReader.close();
                    //Set text area with the content of the file
                    textArea.setText(output);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        //if saveFile menu item is clicked
        if(actionEvent.getSource()==saveFile){
            //Initialize file chooser
            JFileChooser fileChooser = new JFileChooser("F:");
            //Get chosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //If chosen option is the approve option
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with directory's path
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try{
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

    }
}