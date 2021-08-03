import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;


public class Ventana3{

    public void mostrarTabla(DefaultTableModel modelo, String query) {
        Conex conex = new Conex();
        Connection c = conex.miconex(); 
                if(c!=null){
				modelo.setRowCount(0);
            
             
                try{
                    Statement st = c.createStatement();
                    ResultSet rs = st.executeQuery(query);

                         while(rs.next()){
                         Object[] tablaSQL = new Object[4];

                         for (int i=0; i<4; i++){
                             tablaSQL[i] = rs.getObject(i+1);
                         }
                            modelo.addRow(tablaSQL);
                        }
            
           c.close();
           }catch(SQLException se){
               JOptionPane.showMessageDialog(null,se);
           }
        
            }
    }



    public static void main(String[] args){
        Ventana3 v3 = new Ventana3();
        JFrame v = new JFrame("Ventana 3");
        /*Conex conex = new Conex();*/

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("employeeid");
        modelo.addColumn("name");
        modelo.addColumn("salary");
        modelo.addColumn("jobtitle");
        
        JButton boton_show = new JButton();
        JButton boton_insert = new JButton();
        JButton boton_update = new JButton();
        JButton boton_delete = new JButton();
        JButton boton2 = new JButton("Select");
        JTable tabla1 = new JTable(modelo);
        JScrollPane scroll1 = new JScrollPane(tabla1);
        JTextField tfBuscar = new JTextField();

        boton_show.setBounds(20,20,50,25);
        boton_insert.setBounds(75,20,50,25);
        boton_update.setBounds(130,20,50,25);
        boton_delete.setBounds(185,20,50,25);
        boton2.setBounds(20,50,100,25);
        tfBuscar.setBounds(135,50,100,25);
        tabla1.setBounds(20,80,250,150);
        scroll1.setBounds(20,75,250,150);

        tfBuscar.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent ke){}
            public void keyReleased(KeyEvent ke){
                String dato = tfBuscar.getText();
                String consulta = "SELECT * FROM employees WHERE name LIKE '"+dato+"%' OR salary LIKE '"+dato+"%' OR jobtitle LIKE '"+dato+"%' OR employeid LIKE '"+dato+"%'";
                v3.mostrarTabla(modelo,consulta);
            }
            public void keyTyped(KeyEvent ke){}
        }); //Final Key Listener

         ActionListener listener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            

            if (e.getSource()==boton_show){
            v3.mostrarTabla(modelo,"SELECT * FROM employees");
        }

        if (e.getSource()==boton_insert){
            JDialog insert = new JDialog(v,"Insertar");

                JLabel tIns1 = new JLabel("Name:");
                JTextField tfIns1 = new JTextField();
                JLabel tIns2 = new JLabel("Salary:");
                //JTextField tfIns2 = new JTextField();
                JLabel tIns3 = new JLabel("Jobtitle:");
                //JTextField tfIns4 = new JTextField();
                String[] salarios = {"3200.0","3400.0","3500.0","3700.0","3800.0","3900.0"};
                JComboBox<String> cbIns1 = new JComboBox<>(salarios);
                JRadioButton rbIns1 = new JRadioButton("Web Developer", true);
                rbIns1.setActionCommand("Web Developer");
                JRadioButton rbIns2 = new JRadioButton("System Administrator", false);
                rbIns2.setActionCommand("System Administrator");
                JRadioButton rbIns3 = new JRadioButton("IT Support Manager", false);
                rbIns3.setActionCommand("IT Support Manager");
                JRadioButton rbIns4 = new JRadioButton("Database Administrator", false);
                rbIns4.setActionCommand("Database Administrator");
                JRadioButton rbIns5 = new JRadioButton("Application Developer", false);
                rbIns5.setActionCommand("Application Developer");
                JButton bIns1 = new JButton("Insertar");

                ButtonGroup bgIns1 = new ButtonGroup();
                bgIns1.add(rbIns1);
                bgIns1.add(rbIns2);
                bgIns1.add(rbIns3);
                bgIns1.add(rbIns4);
                bgIns1.add(rbIns5);



                tIns1.setBounds(20,20,100,25);
                tfIns1.setBounds(125,20,100,25);
                tIns2.setBounds(20,60,100,25);
                cbIns1.setBounds(125,60,100,25);
                tIns3.setBounds(20,100,100,25);
                rbIns1.setBounds(125,100,150,15);
                rbIns2.setBounds(125,130,150,25);
                rbIns3.setBounds(125,160,150,25);
                rbIns4.setBounds(125,190,150,25);
                rbIns5.setBounds(125,220,150,25);

                bIns1.setBounds(125,260,100,25);

                bIns1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae2){
                        String nombre = tfIns1.getText();
                        String salarios1 = (String)cbIns1.getSelectedItem();
                        String trabajo = bgIns1.getSelection().getActionCommand();

                        if (nombre.equals("")){
                            JOptionPane.showMessageDialog(null, "No se aceptan datos nulos.");
                        }else{
                            Conex conInsert = new Conex();
                            Connection c3 = conInsert.miconex();
                            if  (c3 != null){
                                try{
                                    Statement st = c3.createStatement();
                                    st.executeUpdate("INSERT INTO employees(name,salary,jobtitle) VALUES(AES_ENCRYPT('"+nombre+"','llave'),'"+salarios1+"','"+trabajo+"')");
                                    v3.mostrarTabla(modelo,"SELECT * FROM employees");
                                    c3.close();
                                    JOptionPane.showMessageDialog(null, "Se insertaron los datos correctamente.");
                                    tfIns1.setText("");
                                    cbIns1.setSelectedItem(0);
                                    rbIns1.setSelected(true);
                                }catch(SQLException sqlex2){
                                    JOptionPane.showMessageDialog(null, sqlex2);
                                }
                            }
                        }
                    }
                });

                insert.add(bIns1);
                insert.add(tIns1);
                insert.add(tfIns1);
                insert.add(tIns2);
                insert.add(cbIns1);
                insert.add(tIns3);
                insert.add(rbIns1);
                insert.add(rbIns2);
                insert.add(rbIns3);
                insert.add(rbIns4);
                insert.add(rbIns5);



            insert.setLayout(null);
            insert.setResizable(false);
            insert.setSize(400,400);
            insert.setModal(true);
            insert.setLocationRelativeTo(null);
            insert.setDefaultCloseOperation(insert.DISPOSE_ON_CLOSE);
            insert.setVisible(true);
            
        }

        if (e.getSource()==boton_update){
            JDialog update = new JDialog(v,"Actualizar");
            /*update.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent we){
                    JOptionPane.showMessageDialog(null, "Se ha modificado la tabla.");
                }
            });*/

            int linea = tabla1.getSelectedRow();
            if (linea != -1){
                

                JLabel tUpd1 = new JLabel("Employee ID:");
                JTextField tfUpd1 = new JTextField(tabla1.getValueAt(linea,0).toString());
                JLabel tUpd2 = new JLabel("Name:");
                JTextField tfUpd2 = new JTextField(tabla1.getValueAt(linea,1).toString());
                JLabel tUpd3 = new JLabel("Salary:");
                //JTextField tfUpd3 = new JTextField(tabla1.getValueAt(linea,2).toString());
                JLabel tUpd4 = new JLabel("Jobtitle:");
                //JTextField tfUpd4 = new JTextField(tabla1.getValueAt(linea,3).toString());
                String[] salariosUpd = {"3200.0","3400.0","3500.0","3700.0","3800.0","3900.0"};
                String salariosUpd1 = tabla1.getValueAt(linea,2).toString();
                JComboBox<String> cbUpd1 = new JComboBox<>(salariosUpd);
                cbUpd1.setSelectedItem(salariosUpd1);

                boolean primero = false;
                boolean segundo = false;
                boolean tercero = false;
                boolean cuarto = false;
                boolean quinto = false;

                if (tabla1.getValueAt(linea,3).toString().equals("Web Developer") ){
                    primero = true;
                }
                if (tabla1.getValueAt(linea,3).toString().equals("Systems Administrator")){
                    segundo = true;
                }
                if (tabla1.getValueAt(linea,3).toString().equals("IT Support Manager")){
                    tercero = true;
                }
                if (tabla1.getValueAt(linea,3).toString().equals("Database Administrator")){
                    cuarto = true;
                }
                if (tabla1.getValueAt(linea,3).toString().equals("Application Developer")){
                    quinto = true;
                }
                JRadioButton rbUpd1 = new JRadioButton("Web Developer", primero);
                rbUpd1.setActionCommand("Web Developer");
                JRadioButton rbUpd2 = new JRadioButton("Systems Administrator", segundo);
                rbUpd2.setActionCommand("Systems Administrator");
                JRadioButton rbUpd3 = new JRadioButton("IT Support Manager", tercero);
                rbUpd3.setActionCommand("IT Support Manager");
                JRadioButton rbUpd4 = new JRadioButton("Database Administrator", cuarto);
                rbUpd4.setActionCommand("Database Administrator");
                JRadioButton rbUpd5 = new JRadioButton("Application Developer", quinto);
                rbUpd5.setActionCommand("Application Developer");
                JButton bUpd1 = new JButton("Actualizar");

                ButtonGroup bgUpd1 = new ButtonGroup();
                bgUpd1.add(rbUpd1);
                bgUpd1.add(rbUpd2);
                bgUpd1.add(rbUpd3);
                bgUpd1.add(rbUpd4);
                bgUpd1.add(rbUpd5);

                tUpd1.setBounds(20,20,100,25);
                tfUpd1.setBounds(125,20,100,25);
                tUpd2.setBounds(20,60,100,25);
                tfUpd2.setBounds(125,60,100,25);
                tUpd3.setBounds(20,100,100,25);
                //tfUpd3.setBounds(125,100,100,25);
                tUpd4.setBounds(20,140,100,25);
                //tfUpd4.setBounds(125,140,100,25);
                cbUpd1.setBounds(125,100,100,25);
                //tUpd3.setBounds(20,100,100,25);
                rbUpd1.setBounds(125,140,150,25);
                rbUpd2.setBounds(125,170,150,25);
                rbUpd3.setBounds(125,200,150,25);
                rbUpd4.setBounds(125,230,150,25);
                rbUpd5.setBounds(125,260,150,25);

                bUpd1.setBounds(125,300,100,25);
                update.add(bUpd1);

                bUpd1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae1){
                        Conex conex = new Conex();
                        Connection c2 = conex.miconex();
                        if (c2 != null){
                            try{
                    Statement st2 = c2.createStatement();
                    st2.executeUpdate("UPDATE employees SET name='"+tfUpd2.getText()+"',salary='"+(String)cbUpd1.getSelectedItem()+"',jobtitle='"+bgUpd1.getSelection().getActionCommand()+"' WHERE employeeid="+tfUpd1.getText());
            JOptionPane.showMessageDialog(null, "Actualizaste con exito.");
            v3.mostrarTabla(modelo,"SELECT * FROM employees");
           c2.close();
           }catch(SQLException sqlex1){
               JOptionPane.showMessageDialog(null,sqlex1);
           }
                        } //Fin if
                    }
                });//fin boton

                update.add(tUpd1);
                update.add(tfUpd1);
                update.add(tUpd2);
                update.add(tfUpd2);
                /*update.add(tUpd3);
                update.add(tfUpd3);*/
                update.add(tUpd4);
                //update.add(tfUpd4);
                update.add(cbUpd1);
                update.add(tUpd3);
                update.add(rbUpd1);
                update.add(rbUpd2);
                update.add(rbUpd3);
                update.add(rbUpd4);
                update.add(rbUpd5);

                tfUpd1.setEditable(false);

            update.setLayout(null);
            update.setResizable(false);
            update.setSize(400,400);
            update.setModal(true);
            update.setLocationRelativeTo(null);
            update.setDefaultCloseOperation(update.DISPOSE_ON_CLOSE);
            update.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "No haz seleccionado ningun dato... ");
            }
                
        }

        if (e.getSource()==boton_delete){
            int linea = tabla1.getSelectedRow();
            if (linea != -1) {

                int resp = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar a: "+tabla1.getValueAt(linea,0)+", "
            +tabla1.getValueAt(linea,1)+"?");

            if (JOptionPane.OK_OPTION == resp){
                Conex conexDel = new Conex();
                        Connection cDel = conexDel.miconex();
                        if (cDel != null){
                            try{
                    Statement stDel = cDel.createStatement();
                    stDel.executeUpdate("DELETE FROM employees WHERE employeeid = "+tabla1.getValueAt(linea,0));
            JOptionPane.showMessageDialog(null, "Se elimino el dato.");
            v3.mostrarTabla(modelo,"SELECT * FROM employees");
           cDel.close();
           }catch(SQLException sqlex3){
               JOptionPane.showMessageDialog(null,sqlex3);
           }
                        }
            }

            }else{
                JOptionPane.showMessageDialog(null, "Selecciona un dato.");
            }
        } //Fin Delete

        if (e.getSource()==boton2){
            
            try{
                int linea = tabla1.getSelectedRow();
            JOptionPane.showMessageDialog(null,tabla1.getValueAt(linea,0)+", "
            +tabla1.getValueAt(linea,1)+", "
            +tabla1.getValueAt(linea,2)+", "
            +tabla1.getValueAt(linea,3));
            }catch(Exception ex2){
                JOptionPane.showMessageDialog(null, "No haz seleccionado un dato.");
            }
            
        }
            
            
        
        }
         };




        boton_show.setIcon(new ImageIcon("table.png"));
        boton_insert.setIcon(new ImageIcon("insert.png"));
        boton_update.setIcon(new ImageIcon("update.png"));
        boton_delete.setIcon(new ImageIcon("delete.png"));
        v.add(scroll1);
        v.add(boton_show);
        v.add(boton_insert);
        v.add(boton_update);
        v.add(boton_delete);
        v.add(boton2);
        v.add(tfBuscar);
        boton_show.addActionListener(listener);
        boton_insert.addActionListener(listener);
        boton_update.addActionListener(listener);
        boton_delete.addActionListener(listener);
        boton2.addActionListener(listener);



        v.setResizable(false);
        v.setSize(300,300);
        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
        v.setLocationRelativeTo(null);
        v.setLayout(null);
        v.setVisible(true);

    }
}