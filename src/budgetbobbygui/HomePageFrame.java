package budgetbobbygui;

import budgetbobby.DataStructures.LinkedList;
import budgetbobby.DataStructures.Node;
import budgetbobby.FoodItem;
import budgetbobby.Manager;
import budgetbobby.User;
import budgetbobbygui.Header;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javaswingdev.drawer.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class HomePageFrame extends javax.swing.JFrame {

    private DrawerController drawer;
    private JTextArea textArea;
    private List<String> selectedRows;
    Manager manage;

    public HomePageFrame(User currUser, Manager manager) {
        initComponents();
        addRowToJTable();

        setInitialVisibility(); // Set the initial visibility

        this.manage = manager;

        JLabel cartHeading = new JLabel();
        cartHeading.setBackground(new Color(253, 231, 76));
        cartHeading.setText("                              Your Cart:");
        cartHeading.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        cartHeading.setForeground(Color.white);

        // Create a JTextArea to display multiple lines of text
        textArea = new JTextArea();
        textArea.setBackground(new Color(253, 231, 76));
        textArea.setEditable(false); // Ensure the text area is not editable
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));

        // Initialize the list of selected rows
        selectedRows = new ArrayList<>();

        // Create a JScrollPane to make the text area scrollable
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Confirm order button
        JButton confirmOrder = new JButton("Confirm Order");
        //confirmOrder.setBackground(new Color(255, 156, 98)); // FF9C62

        DrawerItem viewProfileItem = new DrawerItem("                         View Profile");
        viewProfileItem.setForeground(Color.white);
        viewProfileItem.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        drawer = Drawer.newDrawer(this)
                .header(new Header())
                .separator(1, new Color(253, 231, 76))
                .separator(10, new Color(253, 231, 76))
                .background(Color.DARK_GRAY)
                .backgroundTransparent(0.3f)
                .duration(400)
                .drawerBackground(new Color(253, 231, 76))
                .enableScroll(true)
                .drawerWidth(300)
                //.addChild(new DrawerItem("Item Name").icon(new ImageIcon(getClass().getResource(name))).build())  // to add image icon
                .addChild(viewProfileItem.build())
                .separator(10, new Color(253, 231, 76))
                .addChild(cartHeading)
                .separator(2, new Color(253, 231, 76))
                .addChild(scrollPane)
                .separator(5, new Color(253, 231, 76))
                .separator(5, new Color(253, 231, 76))
                .addChild(confirmOrder)
                .separator(10, new Color(253, 231, 76))
                .event(new EventDrawer() {
                    @Override
                    public void selected(int i, DrawerItem di) {
                        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    public void closed() {
                        getContentPane().setBackground(Color.WHITE);
                    }

                })
                .build();

        sidebarButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (drawer.isShow()) {
                    drawer.hide();
                } else {
                    drawer.show();
                }
            }
        });

        viewProfileItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UserProfile up = new UserProfile(currUser, manage);
                up.show();

                setVisible(false);
            }
        });
        confirmOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a panel to hold the components
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                // Add a label above the JComboBox
                JLabel label = new JLabel("Please select delivery time:");
                panel.add(label);

                // Add a JComboBox for selecting delivery time
                String[] deliveryTimes = {"asap", "30 min", "45 min", "1 hr", "1.5hr", "2 hr", "2.5hr", "3 hr"}; // Add more options as needed
                JComboBox<String> timeComboBox = new JComboBox<>(deliveryTimes);
                panel.add(timeComboBox);

                int result = JOptionPane.showConfirmDialog(HomePageFrame.this, panel,
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                // Check the user's choice in the confirmation dialog
                if (result == JOptionPane.YES_OPTION) {
                    // User clicked "Yes" - handle confirmation logic here
//                        String selectedTime = (String) timeComboBox.getSelectedItem();
//                        JOptionPane.showMessageDialog(HomePageFrame.this, "Order confirmed!\nDelivery time: " + selectedTime);
                    bill bill = new bill();
                    bill.show();

                    // setVisible(false);
//                        textArea.setText(""); // Clear a text area (probably displaying order details)
                } else {
                    // User clicked "No" or closed the dialog - handle cancellation logic here
                    JOptionPane.showMessageDialog(HomePageFrame.this, "Order canceled.");
                }
            }
        });

        // Add a ListSelectionListener to jTable1 to capture row selection events
        comboTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Handle row selection
                    updateTextArea();
                }
            }
        });

    }

    public class Rest {

        public String name;
        public double rating;
        public String combo;
        public double price;
        public int calories;

        public Rest(String name, double rating, String combo, double price, int calories) {
            this.name = name;
            this.rating = rating;
            this.combo = combo;
            this.price = price;
            this.calories = calories;
        }

    }

    public ArrayList ListRests() {
        ArrayList<Rest> list = new ArrayList<Rest>();
        Rest r1 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r2 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r3 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r4 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r5 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r6 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r7 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r8 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r9 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);
        Rest r10 = new Rest("Rest_1", 4.0, "item1+item2+item3", 2500, 400);

        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
        list.add(r7);
        list.add(r8);
        list.add(r9);
        list.add(r10);
        return list;
    }

    public void addRowToJTable() {
        DefaultTableModel model = (DefaultTableModel) comboTable.getModel();
        ArrayList<Rest> list = ListRests();
        Object rowData[] = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = false;
            rowData[1] = list.get(i).name;
            rowData[2] = list.get(i).rating;
            rowData[3] = list.get(i).combo;
            rowData[4] = list.get(i).price;
            rowData[5] = list.get(i).calories;

            model.addRow(rowData);
        }
    }

    class jPanelGradient extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(253, 231, 76);
            // Color color1 = new Color  (249,237,204);
            Color color2 = new Color(238, 39, 30);
            GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    private Set<Integer> selectedRowIndices = new HashSet<>();

    // Method to update the textArea with selected rows
    private void updateTextArea() {
        // Append the selected rows to the list
        String restaurant = "";
        String combination = "";
        String price = "";
        for (int selectedRow : comboTable.getSelectedRows()) {
            // Get data from the selected row
            restaurant = (String) comboTable.getValueAt(selectedRow, 1);
            combination = (String) comboTable.getValueAt(selectedRow, 3);
            //price = (String)comboTable.getValueAt(selectedRow, 4);
            price = String.valueOf(comboTable.getValueAt(selectedRow, 4));

            selectedRows.add(restaurant + ": " + combination + " - Price: " + price);
        }
        textArea.setText(String.join("\n\n", selectedRows));
    }

    private void setInitialVisibility() {
        // Set the initial visibility of the components
        tablePanel.setVisible(false);
        emptyLabel.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new jPanelGradient();
        sidebarButton = new javax.swing.JButton();
        userBudget = new javax.swing.JTextField();
        MealTimeComboBox = new javax.swing.JComboBox<>();
        CuisineComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        comboTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        emptyLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        generateButton = new javax.swing.JButton();
        topRatedPic = new swing.ImageAvatar();
        jLabel4 = new javax.swing.JLabel();
        calories = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(232, 228, 228));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(0, 182, 178));
        jPanel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        sidebarButton.setText("|||");
        sidebarButton.setBorderPainted(false);
        sidebarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sidebarButtonActionPerformed(evt);
            }
        });

        userBudget.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 156, 98)));
        userBudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userBudgetActionPerformed(evt);
            }
        });

        MealTimeComboBox.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        MealTimeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Meal Time", "Breakfast", "Meal" }));
        MealTimeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MealTimeComboBoxActionPerformed(evt);
            }
        });

        CuisineComboBox.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        CuisineComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select cuisine", "FastFood", "BBQ", "Chinese", "Continental", "Italian", "Mediterranean", "Rice", "Desserts", "Beverages", "Breakfast", "Japanese", "Vegetarian", "Seafood", "Salad", "Lunch", "Wrap", "Indian", "Bowl", "Mexican", "Dinner", "Thai" }));
        CuisineComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CuisineComboBoxActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new java.awt.CardLayout());

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));

        comboTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Select", "Restaurant", "Rating", "Combination", "Price", "Calories"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        comboTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        comboTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(comboTable);
        if (comboTable.getColumnModel().getColumnCount() > 0) {
            comboTable.getColumnModel().getColumn(0).setResizable(false);
            comboTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            comboTable.getColumnModel().getColumn(0).setHeaderValue("Select");
            comboTable.getColumnModel().getColumn(1).setResizable(false);
            comboTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            comboTable.getColumnModel().getColumn(1).setHeaderValue("Restaurant");
            comboTable.getColumnModel().getColumn(2).setResizable(false);
            comboTable.getColumnModel().getColumn(2).setPreferredWidth(80);
            comboTable.getColumnModel().getColumn(2).setHeaderValue("Rating");
            comboTable.getColumnModel().getColumn(3).setResizable(false);
            comboTable.getColumnModel().getColumn(3).setPreferredWidth(700);
            comboTable.getColumnModel().getColumn(3).setHeaderValue("Combination");
            comboTable.getColumnModel().getColumn(4).setResizable(false);
            comboTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            comboTable.getColumnModel().getColumn(4).setHeaderValue("Price");
            comboTable.getColumnModel().getColumn(5).setResizable(false);
            comboTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            comboTable.getColumnModel().getColumn(5).setHeaderValue("Calories");
        }

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel5.setText("Combinations under your budget:");

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(tablePanel, "card3");

        emptyLabel.setBackground(new java.awt.Color(255, 255, 255));
        emptyLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 48)); // NOI18N
        emptyLabel.setForeground(new java.awt.Color(204, 204, 204));
        emptyLabel.setText("          looks a bit empty here...");
        jPanel3.add(emptyLabel, "card2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(877, 877, 877)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Eras Bold ITC", 0, 19)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Top Rated Restaurant:");

        generateButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        topRatedPic.setBorderSize(2);
        topRatedPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/test/topRated3.jpg"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("Fresh Delights");

        calories.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 156, 98)));
        calories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caloriesActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(232, 228, 228));
        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Enter your budget:");

        jLabel1.setBackground(new java.awt.Color(232, 228, 228));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter calories:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topRatedPic, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(327, 327, 327))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sidebarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calories, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(MealTimeComboBox, 0, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CuisineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userBudget, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(generateButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sidebarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userBudget, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(MealTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CuisineComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(calories, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(generateButton))
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topRatedPic, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel4.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:

        int budget = Integer.parseInt(this.userBudget.getText());

        String mealtime = (String) this.MealTimeComboBox.getSelectedItem();
        String category = (String) this.CuisineComboBox.getSelectedItem();
        int calories;
        if (this.calories.getText().length() == 0) {
            calories = Integer.MAX_VALUE;
        } else {
            calories = Integer.parseInt(this.calories.getText());
        }
        manage.combinationAllRestaurants(4, budget, calories, mealtime, category);

        // Toggle the visibility of jTable1 and emptyLabel
        tablePanel.setVisible(true);
        emptyLabel.setVisible(false);

        //removing previous rows
        DefaultTableModel model = (DefaultTableModel) comboTable.getModel();
        model.setRowCount(0); // Remove all previous rows

        LinkedList<LinkedList<LinkedList<FoodItem>>>[] allCombinations = manage.getAllcombinations();

        if (comboTable.isVisible()) {
            // Add your logic to update the content of jTable1 based on user interaction
            // For example, you can repopulate jTable1 with data here.
//            Object[] rowData = {false, "Restaurant A", 4, "Combination 1", 25.5, 500};
//
//            model.addRow(rowData);
            System.out.println("IN COMBO TABLE");
            for (int i = 0; i < allCombinations.length; i++) {//loops 5 times for each area
                for (int j = 0; j < allCombinations[i].getLength(); j++) {//iterates through individual restuarants
                    for (int k = 0; k < allCombinations[i].getNode(j).getData().getLength(); k++) {//iterates through each budget possibility of individual restaurants
                        for (int l = 0; l < allCombinations[i].getNode(j).getData().getNode(k).getData().getLength(); l++) {//iterates through each combination
                            LinkedList<FoodItem> combination = allCombinations[i].getNode(j).getData().getNode(k).getData();
                            if (combination!=null) {
                                System.out.println("COMBO AINT NULL");
                                Node<FoodItem> curr = combination.getHead();
                                int Price = 0;
                                String comboToDisplay = "";
                                int totalCalorie = 0;
                                int rating = (int) (Math.random() * 5) + 1;
                                while (curr != null) {
                                    Price += curr.getData().getPrice();
                                    comboToDisplay += curr.getData().getName() + " + ";
                                    totalCalorie += curr.getData().getCalorie();
                                    curr = curr.getNext();
                                }
                                comboToDisplay = comboToDisplay.substring(0, comboToDisplay.length() - 2);
                                Object[] rowData = {false, "Restaurant A", rating, comboToDisplay, Price, totalCalorie};

                                model.addRow(rowData);
                            }
                        }

                    }

                }
            }
        }

    }//GEN-LAST:event_generateButtonActionPerformed

    private void CuisineComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuisineComboBoxActionPerformed
        CuisineComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCuisine = (String) CuisineComboBox.getSelectedItem();
                // Use the selectedCuisine as needed in your code
            }
        });
    }//GEN-LAST:event_CuisineComboBoxActionPerformed

    private void MealTimeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MealTimeComboBoxActionPerformed
        // TODO add your handling code here:
        MealTimeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMealTime = (String) MealTimeComboBox.getSelectedItem();
                // Use the selectedMealTime as needed in your code
            }
        });
    }//GEN-LAST:event_MealTimeComboBoxActionPerformed

    private void userBudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userBudgetActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        userBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typedBudget = userBudget.getText();
            }
        });
    }//GEN-LAST:event_userBudgetActionPerformed

    private void sidebarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sidebarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sidebarButtonActionPerformed

    private void caloriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caloriesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caloriesActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HomePageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new HomePageFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CuisineComboBox;
    private javax.swing.JComboBox<String> MealTimeComboBox;
    private javax.swing.JTextField calories;
    private javax.swing.JTable comboTable;
    private javax.swing.JLabel emptyLabel;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton sidebarButton;
    private javax.swing.JPanel tablePanel;
    private swing.ImageAvatar topRatedPic;
    private javax.swing.JTextField userBudget;
    // End of variables declaration//GEN-END:variables
}
