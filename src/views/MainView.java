package views;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.filechooser.FileNameExtensionFilter;
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
    private JButton btnPdf;
    private JButton btnMain;
    private JButton btnUsersTable;
    private JButton btnTemp;
    
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

        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        usersPanel.setBackground(AppColors.PANEL);
        
        JPanel sectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        sectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        sectionPanel.setOpaque(false);
        
        btnMain = createButton("Inicio", AppColors.FIELDS, AppColors.TEXT_LIGHT, 16);
        btnUsersTable = createButton("Usuarios", AppColors.FIELDS, AppColors.TEXT_LIGHT, 16);
        
        btnMain.addActionListener(e -> {
            usersPanel.setVisible(false);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        btnUsersTable.addActionListener(e -> {
            usersPanel.setVisible(true);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        btnTemp = createButton("Otros", AppColors.FIELDS, AppColors.TEXT_LIGHT, 16);
        
        sectionPanel.add(btnMain);
        sectionPanel.add(btnUsersTable);
        sectionPanel.add(btnTemp);
       
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
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);

        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        btnAdd = createButton("Agregar",  
        		AppColors.YELLOW, AppColors.TEXT_DARK,  13);
        btnModify = createButton("Editar",   
        		AppColors.FIELDS, AppColors.TEXT_LIGHT, 13);
        btnDelete = createButton("Eliminar", new Color(180, 50, 50), 
        		AppColors.TEXT_LIGHT, 13);
        btnPdf = createButton("Exportar PDF", new Color(180, 50, 50), 
        		AppColors.TEXT_LIGHT, 13);

        addHover(btnAdd, AppColors.YELLOW, AppColors.YELLOW_HOVER);
        addHover(btnModify, AppColors.FIELDS, AppColors.FIELDS_HOVER);
        addHover(btnDelete, new Color(180, 50, 50), new Color(210, 70, 70));
        addHover(btnPdf, new Color(180, 50, 50), new Color(210, 70, 70));
        
        buttonsPanel.add(btnAdd);
        buttonsPanel.add(btnModify);
        buttonsPanel.add(btnDelete);
        buttonsPanel.add(btnPdf);
        
        JButton btnLogOut = createButton("Cerrar Sesión",
        		AppColors.FIELDS, AppColors.TEXT_LIGHT, 13);
        btnLogOut.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btnLogOut.setAlignmentX(CENTER_ALIGNMENT);
        addHover(btnLogOut, AppColors.FIELDS, AppColors.FIELDS_HOVER);
        btnLogOut.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this,
            		"¿Seguro que deseas cerrar sesión?");
            if (option == JOptionPane.YES_OPTION) {
                new LoginView();
                dispose();
            }
        });

        
        usersPanel.add(scrollPane);
        usersPanel.add(Box.createVerticalStrut(10));
        usersPanel.add(buttonsPanel);
        usersPanel.add(Box.createVerticalStrut(10));
        usersPanel.add(btnLogOut);
        usersPanel.setVisible(false);

        mainPanel.add(sectionPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(usersPanel);
        
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

    
    public void setWindowSize(int width, int height) {
		setSize(width, height);
	}
	
	public void setWindowLocation(int x, int y) {
		setLocation(x, y);
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
    public void addPdfListener(ActionListener listener) { 
    	btnPdf.addActionListener(listener); 
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

    public File selectPdfFile() {
		
		String path = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(path);
		
		chooser.setSelectedFile(new File("reporte-usuarios.pdf"));
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF",  "pdf");
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		
		int option = chooser.showDialog(this, "Exportar PDF de usuarios");
		
		if(option != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		
		File file = chooser.getSelectedFile();
		
		if(!file.getName().toLowerCase().endsWith(".pdf")) {
			file = new File(file.getAbsolutePath() + ".pdf");
		}
		
		return file;
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