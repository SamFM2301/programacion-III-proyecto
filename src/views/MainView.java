package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.UserModel;

public class MainView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd;
    private JButton btnModify;
    private JButton btnDelete;

    public MainView() {
        initFrame();
        initComponents();
    }

    private void initFrame() {
        setTitle("Ventana Principal");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icono = tk.getImage("src/assets/img/iniciosesion.png");
        setIconImage(icono);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 480));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        getRootPane().setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel buttonsOptionsPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Agregar");
        btnModify = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        
        buttonsOptionsPanel.add(btnAdd);
        buttonsOptionsPanel.add(btnModify);
        buttonsOptionsPanel.add(btnDelete);

        // Tabla
        String[] columns = {"Nombre", "Email", "Genero", "Fecha de nacimiento"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnLogOut = new JButton("Cerrar Sesion");
        btnLogOut.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this, "Seguro que deseas cerrar sesion?");
            if (option == JOptionPane.YES_OPTION) {
                new LoginView();
                dispose();
            }
        });
        bottomPanel.add(btnLogOut);

        add(buttonsOptionsPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public void addAddListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    public void addModifyListener(ActionListener listener) {
        btnModify.addActionListener(listener);
    }
    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public UserModel getSelectedUser() {
        int row = table.getSelectedRow();
        if (row == -1) return null;

        String name     = (String) tableModel.getValueAt(row, 0);
        String email    = (String) tableModel.getValueAt(row, 1);
        String gender   = (String) tableModel.getValueAt(row, 2);
        String birthDate = (String) tableModel.getValueAt(row, 3);

        return new UserModel(name, email, null, gender, birthDate);
    }
    
    public void loadUsers(List<UserModel> users) {
        tableModel.setRowCount(0); // limpia la tabla
        for (UserModel user : users) {
            tableModel.addRow(new Object[]{
                user.getName(),
                user.getEmail(),
                user.getGender(),
                user.getBirthDate()
            });
        }
    }
}