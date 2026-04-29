package views;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.RoundedButton;
import models.UserModel;
import utils.AppColors;
import utils.AppFonts;

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
        setMinimumSize(new Dimension(800, 700));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(AppColors.PANEL);
        mainPanel.setBorder(new EmptyBorder(25, 35, 25, 35));

        
        JLabel lblTitle = new JLabel("Gestión de Usuarios");
        lblTitle.setFont(AppFonts.title());
        lblTitle.setForeground(AppColors.TEXT_LIGHT);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, lblTitle.getPreferredSize().height));

       
        String[] columns = {"Nombre", "Email", "Genero", "Fecha de nacimiento"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { 
            	return false; 
            	}
        };
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(AppFonts.regular(12));
        table.setRowHeight(22);
        table.setBackground(new Color(35, 40, 58));
        table.setForeground(AppColors.TEXT_LIGHT);
        table.setSelectionBackground(AppColors.YELLOW);
        table.setSelectionForeground(AppColors.TEXT_DARK);
        table.setGridColor(AppColors.FIELDS);
        table.getTableHeader().setFont(AppFonts.bold(12));
        table.getTableHeader().setBackground(AppColors.FIELDS);
        table.getTableHeader().setForeground(AppColors.TEXT_LIGHT);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(new Color(35, 40, 58));
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);

        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        btnAdd = createButton("Agregar",  
        		AppColors.YELLOW, AppColors.TEXT_DARK,  13);
        btnModify = createButton("Editar",   
        		AppColors.FIELDS, AppColors.TEXT_LIGHT, 13);
        btnDelete = createButton("Eliminar", new Color(180, 50, 50), 
        		AppColors.TEXT_LIGHT, 13);

        addHover(btnAdd, AppColors.YELLOW, AppColors.YELLOW_HOVER);
        addHover(btnModify, AppColors.FIELDS, AppColors.FIELDS_HOVER);
        addHover(btnDelete, new Color(180, 50, 50), new Color(210, 70, 70));

        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnModify);
        buttonsPanel.add(btnDelete);

        
        JButton btnLogOut = createButton("Cerrar Sesión",
        		AppColors.FIELDS, AppColors.TEXT_LIGHT, 13);
        btnLogOut.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        addHover(btnLogOut, AppColors.FIELDS, AppColors.FIELDS_HOVER);
        btnLogOut.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this,
            		"¿Seguro que deseas cerrar sesión?");
            if (option == JOptionPane.YES_OPTION) {
                new LoginView();
                dispose();
            }
        });

        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnLogOut);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text, Color bg, Color fg, int size) {
        RoundedButton btn = new RoundedButton(text, 8);
        btn.setFont(AppFonts.bold(size));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void addHover(JButton btn, Color normal, Color hover) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	btn.setBackground(hover); 
            	}
            public void mouseExited(MouseEvent e)  { 
            	btn.setBackground(normal); 
            	}
        });
    }

    
    public void addAddListener(ActionListener listener)    { 
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
        return new UserModel(
            (String) tableModel.getValueAt(row, 0),
            (String) tableModel.getValueAt(row, 1),
            null,
            (String) tableModel.getValueAt(row, 2),
            (String) tableModel.getValueAt(row, 3)
        );
    }

    public void loadUsers(List<UserModel> users) {
        tableModel.setRowCount(0);
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