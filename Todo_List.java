import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Todo_List
{
    static File file = new File("tasks.txt");

    public static void main(String [] args)
    {
        JFrame f = new JFrame(" To Do List");
        f.setLayout(null);

        JTextField t = new JTextField();
        JButton add = new JButton("Add");
        JButton done = new JButton("Mark Done");
        JButton delete = new JButton("Delete");
        JButton clr = new JButton("Clear All");

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JScrollPane sp = new JScrollPane(list);
        JLabel l = new JLabel("Your Tasks");

        l.setBounds(120,10,200,20);
        t.setBounds(30,40,180,30);
        add.setBounds(220,40,90,30);
        sp.setBounds(30,90,280,180);
        done.setBounds(30,290,120,30);
        delete.setBounds(170,290,100,30);
        clr.setBounds(90,330,140,30);

        f.add(l);
        f.add(t);
        f.add(add);
        f.add(sp);
        f.add(done);
        f.add(delete);
        f.add(clr);
            
            try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
            {
                model.addElement(sc.nextLine());
            }
            sc.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(f,"NO File ");
        }
       

        add.addActionListener(e->{
            String task = t.getText();
            if(task.equals(""))
            {
                JOptionPane.showMessageDialog(f,"Enter Task!");
            }
            else
            {
                model.addElement(task);
                t.setText("");
                saveTasks(model);
            }
        });
        done.addActionListener(e->{
            int i = list.getSelectedIndex();
            if(i==-1)
            {
                JOptionPane.showMessageDialog(f,"Select task First!");
            }
            else
            {
                String task = model.get(i);

                if(!task.startsWith("✓  "))
                {
                    model.set(i,"✓"+task);

                    saveTasks(model);
                }
            }
        });

        delete.addActionListener(e->{
            int i = list.getSelectedIndex();

            if(i==-1)
            {
                JOptionPane.showMessageDialog(f,"Select task!");
            }
            else
            {
                model.remove(i);
                saveTasks(model);
            }
        });

        clr.addActionListener(e->{
            model.clear();
            saveTasks(model);
        });

        f.setSize(360,430);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static void saveTasks(DefaultListModel<String> model)
    {
        try
        {
            PrintWriter pw = new PrintWriter(file);

            for(int i= 0; i<model.size();i++)
            {
                pw.println(model.get(i));
            }
            pw.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error!");
        }
     }
}
