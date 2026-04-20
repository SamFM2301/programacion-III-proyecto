package views;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.UserModel;

public class MainView extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

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
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Tabla
        String[] columns = {"Nombre", "Email", "Genero", "Fecha de nacimiento"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla de solo lectura
            }
        };

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);

        JButton btnLogOut = new JButton("Cerrar Sesion");
        btnLogOut.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this, "Seguro que deseas cerrar sesion?");
            if (option == JOptionPane.YES_OPTION) {
                new LoginView();
                dispose();
            }
        });

        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnLogOut);

        add(mainPanel);
        setVisible(true);
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