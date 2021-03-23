/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pde;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Thuan
 */
public class mainFrame extends javax.swing.JFrame {
    private static final int FILE_OPEN = 1;
    private static final int FILE_SAVE = 2;
    private String boundary, equation;
    private double[][] GCOORD;
    public mainFrame() {
        setResizable(false);
        setLocation(500,300);
        initComponents();
        tpintro.setText("Updated:\n>>  FEM :\n*1D :\n\t- Poison :\n\t\t+Dirichlet\n\t\t+Neumann\n\t\t+Robin.\n\n\t"+
                "-Elliptic:\n\t\t+Dirichlet\n\t\t+Neumann\n\t\t+Robin.\n\n\t-Parabolic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)."+
                                           "\n*2D :\n\t- Poison:\n\t+Dirichlet(T3-T6-Q4-Q8)\n\t+Neumann(Updating)\n\t+Robin(Updating)\n\n\t"+
                "-Elliptic:\n\t+Dirichlet(T3-T6-Q4-Q8)\n\t+Neumann(Updating)\n\t+Robin(Updating)\n\n\t"+
                "-Parabolic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)"+
                                "\n>>  FVM :\n*1D :\n\t - Poison :\n\t\t+Dirichlet\n\t\t+Neumann\n\t+Robin(Updating).\n\n\t"+
                "-Elliptic:\n\t\t+Dirichlet\n\t\t+Neumann\n\t+Robin(Updating).\n\n\t-Parabolic:\n\t\t+Dirichlet\n\t\t+Neumann\n\t\t+Robin."+
                                           "\n*2D  :\n\t- Poison:\n\t+Dirichle(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)\n\n\t"+
                "-Elliptic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)\n\n\t"+
                "-Parabolic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)"+
                               "\n>>  FDM :\n*1D :\n\t - Poison :\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating).\n\n\t"+
                "-Elliptic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating).\n\n\t-Parabolic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)."+
                                           "\n*2D  :\n\t- Poison:\n\t+Dirichle(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)\n\n\t"+
                "-Elliptic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)\n\n\t"+
                "-Parabolic:\n\t+Dirichlet(Updating)\n\t+Neumann(Updating)\n\t+Robin(Updating)");
        initFrame();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        rb1d = new javax.swing.JRadioButton();
        rb2d = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cbsm = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rbpoison = new javax.swing.JRadioButton();
        rbelliptic = new javax.swing.JRadioButton();
        rbparabolic = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rbdirichlet = new javax.swing.JRadioButton();
        rbneumann = new javax.swing.JRadioButton();
        rbrobin = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbya = new javax.swing.JLabel();
        lbyb = new javax.swing.JLabel();
        tfxa = new javax.swing.JTextField();
        tfxb = new javax.swing.JTextField();
        tfya = new javax.swing.JTextField();
        tfyb = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lbny = new javax.swing.JLabel();
        tfny = new javax.swing.JTextField();
        tfnx = new javax.swing.JTextField();
        lbgy = new javax.swing.JLabel();
        lbbeta = new javax.swing.JLabel();
        tfalpha = new javax.swing.JTextField();
        tfbeta = new javax.swing.JTextField();
        lbalpha = new javax.swing.JLabel();
        lbgx = new javax.swing.JLabel();
        tfgx = new javax.swing.JTextField();
        tfgy = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taout = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpintro = new javax.swing.JTextPane();
        btok = new javax.swing.JButton();
        btclear = new javax.swing.JButton();
        btcancel = new javax.swing.JButton();
        cbts = new javax.swing.JComboBox();
        lbt = new javax.swing.JLabel();
        tfdt = new javax.swing.JTextField();
        tftime = new javax.swing.JTextField();
        lbdt = new javax.swing.JLabel();
        lbte = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        tfl1 = new javax.swing.JTextField();
        lbl2 = new javax.swing.JLabel();
        tfl2 = new javax.swing.JTextField();
        lbkappa = new javax.swing.JLabel();
        tfkappa = new javax.swing.JTextField();
        lbts = new javax.swing.JLabel();
        cbte = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tauex = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        taf = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taduex = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        miopen = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solve Practical Difference Equation");

        buttonGroup1.add(rb1d);
        rb1d.setText("1D");
        rb1d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1dActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb2d);
        rb2d.setText("2D");
        rb2d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb2dActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Function f:");

        cbsm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Finite Difference", "Finite Volume", "Finite Element" }));
        cbsm.setToolTipText("Select method to solve equation");
        cbsm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbsmActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Dimension :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Select Method :");

        buttonGroup2.add(rbpoison);
        rbpoison.setText("Poison Equation");
        rbpoison.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbpoisonActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbelliptic);
        rbelliptic.setText("Ellipctic Equation");
        rbelliptic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbellipticActionPerformed(evt);
            }
        });

        buttonGroup2.add(rbparabolic);
        rbparabolic.setText("Parabolic Equation");
        rbparabolic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbparabolicActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Select Equation :");

        buttonGroup3.add(rbdirichlet);
        rbdirichlet.setText("Dirichlet");
        rbdirichlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdirichletActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbneumann);
        rbneumann.setText("Neumann");
        rbneumann.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbneumannActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbrobin);
        rbrobin.setText("Robin");
        rbrobin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbrobinActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Input values :");

        jLabel6.setText("xb =");
        jLabel6.setToolTipText("Right boundary");

        jLabel7.setText("xa =");
        jLabel7.setToolTipText("Left boundary");

        lbya.setText("ya  = ");
        lbya.setToolTipText("Down boundary");

        lbyb.setText("yb = ");
        lbyb.setToolTipText("Up boundary");

        tfxa.setText("0.0");
        tfxa.setToolTipText("");

        tfxb.setText("1.0");

        tfya.setText("0.0");

        tfyb.setText("1.0");

        jLabel10.setText("Nx =");
        jLabel10.setToolTipText("Number element on x axis");

        lbny.setText("Ny =");
        lbny.setToolTipText("Number element on y axis");

        tfny.setText("15");

        tfnx.setText("10");

        lbgy.setText("Y Gauss =");
        lbgy.setToolTipText("Number point gauss on y axis");

        lbbeta.setText("beta = ");

        tfalpha.setText("1.0");

        tfbeta.setText("1.0");

        lbalpha.setText("alpha = ");
        lbalpha.setToolTipText("");

        lbgx.setText("X Gauss =");
        lbgx.setToolTipText("Number point gauss on x axis");

        tfgx.setText("3");

        tfgy.setText("2");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel16.setText("Result :");

        taout.setEditable(false);
        taout.setColumns(20);
        taout.setRows(5);
        jScrollPane1.setViewportView(taout);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel17.setText("Type Boundary :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel18.setText("Introduction :");

        tpintro.setEditable(false);
        jScrollPane2.setViewportView(tpintro);

        btok.setText("Ok");
        btok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btokActionPerformed(evt);
            }
        });

        btclear.setText("Set Default");
        btclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclearActionPerformed(evt);
            }
        });

        btcancel.setText("Cancel");
        btcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcancelActionPerformed(evt);
            }
        });

        cbts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EXPLICIT", "IMPLICIT", "CRANK-NICOLSON" }));
        cbts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtsActionPerformed(evt);
            }
        });

        lbt.setText("Nt  = ");
        lbt.setToolTipText("Number time for parabolic equation");

        tfdt.setText("0.005");

        tftime.setText("20");

        lbdt.setText("dt =");
        lbdt.setToolTipText("Time step ");

        lbte.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbte.setText("Type Element :");

        lbl1.setText("lambda1=");
        lbl1.setToolTipText("Coefficient for Robin boundary condition");

        tfl1.setText("0.5");

        lbl2.setText("lambda2=");
        lbl2.setToolTipText("Coefficient for Robin boundary condition");

        tfl2.setText("0.5");

        lbkappa.setText("kappa =");
        lbkappa.setToolTipText(" diffusion coefficient");

        tfkappa.setText("0.0625");

        lbts.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbts.setText("Type Scheme :");

        cbte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbteActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Div of Exact solution");

        tauex.setColumns(20);
        tauex.setLineWrap(true);
        tauex.setRows(5);
        tauex.setText("x*(x + t)");
        tauex.setWrapStyleWord(true);
        jScrollPane5.setViewportView(tauex);

        taf.setColumns(20);
        taf.setLineWrap(true);
        taf.setRows(5);
        taf.setText(" x - 1/8 + 1*x*(x + t) + 1*(t + 2*x)");
        taf.setWrapStyleWord(true);
        jScrollPane6.setViewportView(taf);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Exact Solution :");

        taduex.setColumns(20);
        taduex.setRows(5);
        taduex.setText("2*x + t");
        jScrollPane3.setViewportView(taduex);

        menuFile.setText("File");
        menuFile.setToolTipText("Click here to input the coordinates of mesh.");

        miopen.setText("Open ");
        miopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miopenActionPerformed(evt);
            }
        });
        menuFile.add(miopen);
        menuFile.add(jSeparator2);

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuFile.add(jMenuItem1);

        jMenuBar1.add(menuFile);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbsm, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbalpha)
                                            .addComponent(lbgx)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel7)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfgx, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbgy))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfalpha, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbbeta))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfnx, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfxa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lbny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(tfny, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(tfxb, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(37, 37, 37)
                                                        .addComponent(lbya)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(116, 116, 116)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(lbdt)
                                                                    .addComponent(lbl2))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(tfl2)
                                                                    .addComponent(tfdt, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfya, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lbyb)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(tfyb, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(tfgy, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(25, 25, 25)
                                                        .addComponent(lbkappa)
                                                        .addGap(12, 12, 12)
                                                        .addComponent(tfkappa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(tfbeta, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(11, 11, 11)
                                                        .addComponent(lbl1))
                                                    .addComponent(lbt, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfl1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tftime, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel5)
                                        .addContainerGap())))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbte, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbts, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbts, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbparabolic)
                            .addComponent(rbelliptic)
                            .addComponent(rbpoison)
                            .addComponent(rb2d)
                            .addComponent(rb1d)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rbneumann)
                            .addComponent(rbrobin)
                            .addComponent(rbdirichlet))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel16)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btok)
                                        .addGap(62, 62, 62)
                                        .addComponent(btclear)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btcancel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator3)))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rb1d, rb2d});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel8, lbte, lbts});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rbelliptic, rbparabolic, rbpoison});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rbdirichlet, rbneumann, rbrobin});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, lbyb});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfbeta, tfnx, tfny, tfxa, tfxb, tfya, tfyb});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btcancel, btclear, btok});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbsm, cbte, cbts});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane3, jScrollPane5, jScrollPane6});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(lbya)
                        .addComponent(lbyb)
                        .addComponent(tfxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfxb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfyb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel10)
                    .addComponent(lbny)
                    .addComponent(tfny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfnx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbdt))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb1d))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(rb2d))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfbeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbbeta)
                                    .addComponent(tfalpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbalpha)
                                    .addComponent(lbl1)
                                    .addComponent(tfl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl2)
                                    .addComponent(tfl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbgx)
                                            .addComponent(tfgx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbgy)
                                            .addComponent(tfgy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfkappa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbkappa)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbpoison)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(rbelliptic)
                        .addGap(18, 18, 18)
                        .addComponent(rbparabolic)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbdirichlet)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbneumann)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbrobin)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lbte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbts)
                                .addGap(18, 18, 18)
                                .addComponent(cbts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btcancel)
                            .addComponent(btclear)
                            .addComponent(btok))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rb1d, rb2d});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbsm, cbte, cbts, jLabel1, jLabel17, jLabel2, jLabel3, jLabel4, jLabel8, lbte});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rbelliptic, rbparabolic, rbpoison});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rbdirichlet, rbneumann, rbrobin});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7, lbya, lbyb});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfbeta, tfnx, tfny, tfxa, tfxb});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btclear, btok});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbkappa, lbl1});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbsmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbsmActionPerformed
        if(cbsm.getSelectedIndex() != 2){
            lbte.setEnabled(false);
            cbte.setEnabled(false);
            tfgx.setEnabled(false);
            tfgy.setEnabled(false);
            lbgx.setEnabled(false);
            lbgy.setEnabled(false);}
        else{
            lbte.setEnabled(true);
            cbte.setEnabled(true);
            tfgx.setEnabled(true);
            tfgy.setEnabled(true);
            lbgx.setEnabled(true);
            lbgy.setEnabled(true);
            buttonGroup1.clearSelection();
        }
    }//GEN-LAST:event_cbsmActionPerformed

    private void rb1dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1dActionPerformed
        tfgy.setText("0");
        checkDim();
    }//GEN-LAST:event_rb1dActionPerformed

    private void rb2dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb2dActionPerformed
        checkDim();
    }//GEN-LAST:event_rb2dActionPerformed

    private void rbpoisonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbpoisonActionPerformed
        this.equation = "POISON";
        checkDim();
        tfalpha.setText("0");
        tfbeta.setText("0");
        lbts.setEnabled(false);
        cbts.setEnabled(false);
        lbalpha.setEnabled(false);
        tfalpha.setEnabled(false);
        lbbeta.setEnabled(false);
        tfbeta.setEnabled(false);
        lbkappa.setEnabled(false);
        tfkappa.setEnabled(false);
        lbt.setEnabled(false);
        tftime.setEnabled(false);
        lbdt.setEnabled(false);
        tfdt.setEnabled(false);
        taout.setText(null);
    }//GEN-LAST:event_rbpoisonActionPerformed

    private void rbellipticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbellipticActionPerformed
        this.equation = "ELLIPTIC";
        checkDim();
        tfalpha.setText("1");
        tfbeta.setText("1");
        lbts.setEnabled(false);
        cbts.setEnabled(false);
        lbalpha.setEnabled(true);
        tfalpha.setEnabled(true);
        lbbeta.setEnabled(true);
        tfbeta.setEnabled(true);
        lbkappa.setEnabled(false);
        tfkappa.setEnabled(false);
        lbt.setEnabled(false);
        tftime.setEnabled(false);
        lbdt.setEnabled(false);
        tfdt.setEnabled(false);
        taout.setText(null);
    }//GEN-LAST:event_rbellipticActionPerformed

    private void rbparabolicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbparabolicActionPerformed
        this.equation = "PARABOLIC";
        checkDim();
        lbts.setEnabled(true);
        cbts.setEnabled(true);
        lbalpha.setEnabled(true);
        tfalpha.setEnabled(true);
        lbbeta.setEnabled(true);
        tfbeta.setEnabled(true);
        lbkappa.setEnabled(true);
        tfkappa.setEnabled(true);
        lbt.setEnabled(true);
        tftime.setEnabled(true);
        lbdt.setEnabled(true);
        tfdt.setEnabled(true);
        taout.setText(null);
    }//GEN-LAST:event_rbparabolicActionPerformed

    private void rbdirichletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdirichletActionPerformed
        this.boundary = "DIRICHLET";
        lbl1.setEnabled(false);
        lbl2.setEnabled(false);
        tfl1.setEnabled(false);
        tfl2.setEnabled(false);
        taout.setText(null);
    }//GEN-LAST:event_rbdirichletActionPerformed

    private void rbneumannActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbneumannActionPerformed
        this.boundary = "NEUMANN";
        lbl1.setEnabled(false);
        lbl2.setEnabled(false);
        tfl1.setEnabled(false);
        tfl2.setEnabled(false);
        taout.setText(null);
    }//GEN-LAST:event_rbneumannActionPerformed

    private void rbrobinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbrobinActionPerformed
        this.boundary = "ROBIN";
        lbl1.setEnabled(true);
        lbl2.setEnabled(true);
        tfl1.setEnabled(true);
        tfl2.setEnabled(true);
        taout.setText(null);
        
    }//GEN-LAST:event_rbrobinActionPerformed

    private void cbtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtsActionPerformed
        taout.setText(null);
    }//GEN-LAST:event_cbtsActionPerformed

    private void btclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclearActionPerformed
        initFrame();
    }//GEN-LAST:event_btclearActionPerformed

    private void btokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btokActionPerformed
        
        //<editor-fold defaultstate="collapsed" desc="Test condition">

        if(!checkString(tauex.getText())){
            JOptionPane.showMessageDialog(null, "Error format...");
            tauex.requestFocus(true);
            return;
        }
        if(!checkString(taduex.getText())){
            JOptionPane.showMessageDialog(null, "Error format...");
            taduex.requestFocus(true);
            return;
        }
        if(!checkString(taf.getText())){
            JOptionPane.showMessageDialog(null, "Error format...");
            taf.requestFocus(true);
            return;
        }
        if (!readFloat(tfxa.getText()) || !readFloat(tfya.getText()) || !readFloat(tfxb.getText()) || !readFloat(tfyb.getText()) || !readInteger(tfnx.getText()) || 
                !readInteger(tfny.getText()) || !readInteger(tfgx.getText()) || !readInteger(tfgy.getText())|| !readInteger(tftime.getText())|| !readFloat(tfkappa.getText()) ||
                !readFloat(tfalpha.getText()) || !readFloat(tfbeta.getText())|| !readFloat(tfdt.getText())) {
            taout.setText(null);
            JOptionPane.showMessageDialog(null, "Error format...");
            return;
        }

        if (Integer.parseInt(tfgx.getText()) > 5  || Integer.parseInt(tfgy.getText()) > 5 ) {
            JOptionPane.showMessageDialog(null," Quadrature order too high for triangular quadrature !");
            return;
        }
        
        if (!rb1d.isSelected() && !rb2d.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select dimension...");
            return;
        }
        if (this.equation == null) {
            JOptionPane.showMessageDialog(null, "Please select type equation...");
            return;
        }
        if (this.boundary==null){
                JOptionPane.showMessageDialog(null, "Please select type boundary...");
            return;
        }
        if (cbsm.getSelectedIndex()==2 && cbts.getSelectedItem().toString()==null){
                        JOptionPane.showMessageDialog(null, "Please choose type element...");
            return;
        }
        //</editor-fold>
        
        if( rb2d.isSelected() && (boundary.compareTo("NEUMANN" )==0 ||boundary.compareTo("ROBIN" )==0)){
            taout.setText(boundary +" boundary condition is updating.\nPlease choose another boundary !");
            JOptionPane.showMessageDialog(null,"Updating...");
            return;
        }

        
        //BEGIN SOLVE
        long start = getTime();

        if (cbsm.getSelectedIndex() == 2) { // FINITE ELEMENT METHOD METHOD
            if (rb1d.isSelected()) {// FINITE ELEMENT METHOD IN 1D
                if (equation.compareTo("PARABOLIC") == 0) {
                    JOptionPane.showMessageDialog(null, "Updating...");
                    return;
                } else { // ------------------------------------------------------------------------------
                    MainFEM fem1 = new MainFEM(Integer.parseInt(tfnx.getText()), Float.parseFloat(tfxa.getText()),Float.parseFloat(tfxb.getText()),
                            Float.parseFloat(tfalpha.getText()), Float.parseFloat(tfbeta.getText()), Integer.parseInt(tfgx.getText()),boundary,
                            cbte.getSelectedItem().toString(), Float.parseFloat(tfl1.getText()), Float.parseFloat(tfl2.getText()),
                            tauex.getText(),taduex.getText(),taf.getText(),GCOORD);
                    GCOORD = null;
                    
                    try {
                        fem1.mainEllipticFEM1D();
                    } catch (OutOfMemoryError e) {
                        taout.setText("Error: Not enough memory...");
                        return;
                    } catch (NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Warning: Matrix is singular to working precision. Please choose other type element...");
                        return;
                    } catch (IOException ex) {
                        Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        return;
                    }
                }

            } else if (rb2d.isSelected()) {// FINITE ELEMENT METHOD IN 2D
                
                if (equation.compareTo("PARABOLIC") == 0) {
                    JOptionPane.showMessageDialog(null, "Updating...");
                    return;
                } else {
                        MainFEM fem2 = new MainFEM(Integer.parseInt(tfnx.getText()), Integer.parseInt(tfny.getText()),Float.parseFloat(tfxa.getText()), Float.parseFloat(tfxb.getText()),
                                Float.parseFloat(tfya.getText()), Float.parseFloat(tfyb.getText()),Float.parseFloat(tfalpha.getText()), Float.parseFloat(tfbeta.getText()),
                                Integer.parseInt(tfgx.getText()), Integer.parseInt(tfgy.getText()), boundary, cbte.getSelectedItem().toString(), 
                                Float.parseFloat(tfl1.getText()), Float.parseFloat(tfl2.getText()),tauex.getText(),taduex.getText(),taf.getText(),GCOORD);
                        GCOORD = null;       
                    try {
                        fem2.mainEllipticFEM2D();
                    } catch (IOException ex) {
                        Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        return;
                    } catch (OutOfMemoryError e) {
                        taout.setText("Error: Not enough memory...");
                        return;
                    }
                }
            } else 
                return;
        } else if (cbsm.getSelectedIndex() == 1) { // FINITE VOLUME METHOD METHOD 
                String typeElement = "Q4";
                if (rb1d.isSelected()){
                    
                    if (equation.compareTo("POISON") == 0 || equation.compareTo("ELLIPTIC") == 0) {
                            MainFVM fvm = new MainFVM(Integer.parseInt(tfnx.getText()), Float.parseFloat(tfxa.getText()), Float.parseFloat(tfxb.getText()),
                                    Float.parseFloat(tfalpha.getText()),Float.parseFloat(tfbeta.getText()),boundary,typeElement,
                                    Float.parseFloat(tfl1.getText()), Float.parseFloat(tfl2.getText()),tauex.getText(),taduex.getText(),taf.getText(),GCOORD);
                            GCOORD = null;
                        try {
                            fvm.mainEllipticFVM1D();
                        } catch (OutOfMemoryError e) {
                            taout.setText("Error: Not enough memory...");
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            return;
                        }catch(NullPointerException e){
                            return;
                        }
                    } else {
                            MainFVM fvm = new MainFVM(Integer.parseInt(tfnx.getText()), Integer.parseInt(tftime.getText()),Float.parseFloat(tfdt.getText()),
                                    Float.parseFloat(tfxa.getText()), Float.parseFloat(tfxb.getText()),Float.parseFloat(tfkappa.getText()), Float.parseFloat(tfalpha.getText()),
                                    Float.parseFloat(tfbeta.getText()),boundary,typeElement,cbts.getSelectedItem().toString(),
                                    Float.parseFloat(tfl1.getText()), Float.parseFloat(tfl2.getText()),tauex.getText(),taduex.getText(),taf.getText(),GCOORD);
                            GCOORD = null;
                        try {
                            fvm.mainParabolicFVM1D();
                        } catch (OutOfMemoryError e) {
                            taout.setText("Error: Not enough memory...");
                            return;
                        } catch (IOException ex) {
                            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            return;
                        }catch(NullPointerException e){
                            taout.setText(null);
                            return;
                        }
                    }
                    
                }
                else{// FVM 2D
                    JOptionPane.showMessageDialog(null,"Updating...");
                    return; 
                }   
            
        } else if (cbsm.getSelectedIndex() == 0) { // FINITE DIFFERENCE METHOD METHOD 
           JOptionPane.showMessageDialog(null,"Updating...");
            return;
        } else {
            return;
        }

        if (boundary.compareTo("NEUMANN") == 0 && tfbeta.getText().compareTo("0") == 0) {
            JOptionPane.showMessageDialog(null, "Warning: Matrix is close to singular or badly scaled. Results may be inaccurate !");
        }
        
        long end = getTime();
        String time = timeRun(start, end);// END SOLVE
        Information(time);

    }//GEN-LAST:event_btokActionPerformed

    private void btcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btcancelActionPerformed

    private void cbteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbteActionPerformed

    private void miopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miopenActionPerformed
        try {
            GCOORD = operateFile("Open coordinates file name",FILE_OPEN);
        } catch (IOException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_miopenActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    private void checkDim(){
        // set type element 
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(); 
            if (rb1d.isSelected()){
                lbya.setEnabled(false);
                lbyb.setEnabled(false);
                tfya.setEnabled(false);
                tfyb.setEnabled(false);
                tfny.setEnabled(false);
                lbny.setEnabled(false);
                lbgy.setEnabled(false);
                tfgy.setEnabled(false);
                if(cbsm.getSelectedIndex()== 2){
                    model.addElement("L2");
                    model.addElement("L3");
                    model.addElement("L4");
                    cbte.setModel(model);
                }
             }
            if (rb2d.isSelected()){
                lbya.setEnabled(true);
                lbyb.setEnabled(true);
                tfya.setEnabled(true);
                tfyb.setEnabled(true);
                lbny.setEnabled(true);
                tfny.setEnabled(true);
                if(cbsm.getSelectedIndex()== 2){
                    lbgy.setEnabled(true);
                    tfgy.setEnabled(true);
                    model.addElement("T3");
                    model.addElement("T6");
                    model.addElement("Q4");
                    model.addElement("Q8");
                    cbte.setModel(model);
                }
            }
            taout.setText(null);
    }

    private void initFrame(){
        
        
        cbsm.setSelectedIndex(2);
        lbts.setEnabled(false);
        cbts.setEnabled(false);
        tfnx.setEditable(true);
        tfny.setEditable(true);
        tfxa.setEditable(true);
        tfxb.setEditable(true);
        tfya.setEditable(true);
        tfyb.setEditable(true);
        tfxa.setText("0.0");
        tfya.setText("0.0");
        tfxb.setText("1.0");
        tfyb.setText("1.0");
        tftime.setText("20");
        tfdt.setText("0.005");
        tfnx.setText("10");
        tfny.setText("15");
        tfgx.setText("3");
        tfgy.setText("2");
        tfkappa.setText("0.0625");
        tfalpha.setText("1.0");
        tfbeta.setText("1.0");
        taout.setText(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
    
    }
    private void Information(String time){
        String tboundary="", tequation="";
        if (rb1d.isSelected()){
            if (equation.compareTo("POISON")==0)
                tequation = "- Uxx = F(x)";
            if (equation.compareTo("ELLIPTIC")==0)
                tequation = "- Uxx + alpha*Ux + beta*U = F(x)";
            if (equation.compareTo("PARABOLIC")==0)
                tequation = " Ut - kappa*Uxx + alpha*Ux + beta*U = F(x)";
            if (boundary.compareTo("DIRICHLET")==0)
                tboundary = " U(xa) = U0 & U(xb) = U1";
            if (boundary.compareTo("NEUMANN")==0)
                tboundary = " dU(xa) = dU0   & dU(xb) = dU1";
            if (boundary.compareTo("ROBIN")==0)
                tboundary = " dU(xa) + lampda1*U(xa) = U0   & dU(xb) + lampda2*U(xb)= U1";
        }
        else{
            if (equation.compareTo("POISON")==0)
                tequation = "- Uxx - Uyy = F(x,y)";
            if (equation.compareTo("ELLIPTIC")==0)
                tequation = "- Uxx - Uyy + alpha*(Ux+Uy) + beta*U = F(x,y)";
            if (equation.compareTo("PARABOLIC")==0)
                tequation = " Ut - kappa*(Uxx+Uyy) + alpha*(Ux+Uy) + beta*U = F(x,y)";
            if (boundary.compareTo("DIRICHLET")==0)
                tboundary = " U(x,y) = G(x,y) on the box boundary";
            if (boundary.compareTo("NEUMANN")==0)
                tboundary = " dU(x,y) = G(x,y) on the box boundary";
            if (boundary.compareTo("ROBIN")==0)
                tboundary = "dU(x,y) + lampda*U(x,y) = G(x,y) on the box boundary";
        }
        
        
        taout.setText("Using "+cbsm.getSelectedItem().toString()+" method to solve "+equation+ " equation."
                + "\n"
                +"\t"+ tequation +"\n"
                + "Subject to "+boundary +" boundary condition:"
                + "\n"
                + "\t"+tboundary +"\n\n"
                +"Success...\nDiscrete soluntion filename is u_dis!."+
            "\n\nThe time for running is about: "+ time);
    }

    private double[][] operateFile(String title, int type) throws IOException{
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        chooser.setSize(500, 700);
        int choose  = -1;
        double[][]gcoord = null;
        switch (type){
            case 1:
                choose = chooser.showOpenDialog(null);
                break;
            case 2:
                choose = chooser.showSaveDialog(null);
                break;
        }
        if( choose == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            switch (type){
                case 1:
                    gcoord = readGcoord(file);
                    setDataRead(gcoord);
                    break;
                case 2:
                    saveFile();
                    break;
            }
            
        }
        
        
        
        return gcoord;
    }
    private double[][] readGcoord(File file) throws IOException {
        try{
            FileReader fr = new FileReader(file);
            FileReader fr1 = new FileReader(file);
            int row;
            double[][] gcoord;
            try (BufferedReader br = new BufferedReader(fr)) {
                try (BufferedReader br1 = new BufferedReader(fr1)) {
                    
                    // get total of number row
                    row = 0;
                    while ((br1.readLine()) != null)
                        row ++;
                    
                    gcoord = new double[row][2];
                    for (int i = 0; i < row; i++) {
                        String line = br.readLine();
                        
                        int size_item1 = 0;
                        while (line.charAt(size_item1) != ' ' && size_item1 != line.length()-1  )
                            size_item1++;
                        int temp;
                        if ( size_item1 == line.length()-1 ){
                            size_item1++;
                            gcoord[i][0] = Float.parseFloat(line.substring(0, size_item1));
                            gcoord[i][1] = 0;
                        }
                        else{
                            temp = size_item1;
                            while (line.charAt(temp + 1) == ' ')
                                temp++;
                            gcoord[i][0] = Float.parseFloat(line.substring(0, size_item1));
                            gcoord[i][1] = Float.parseFloat(line.substring(temp, line.length()));
                        }
                    }
                    br1.close();
                    fr1.close();
                }
                br.close();
                fr.close();
            }
            return gcoord;

        }catch(FileNotFoundException e ){
            JOptionPane.showMessageDialog(null,"File not found !.");
            return null;
        }

    } 
    
    private void saveFile(){
    
    }
    private void setDataRead(double[][]gcoord){
        
            tfnx.setEditable(false);
            tfny.setEditable(false);
            tfxa.setEditable(false);
            tfxb.setEditable(false);
            tfya.setEditable(false);
            tfyb.setEditable(false);

            tfxa.setText(gcoord[0][0]+"");
            tfxb.setText(gcoord[gcoord.length-1][0]+"");
            tfya.setText(gcoord[0][1]+"");
            tfyb.setText(gcoord[gcoord.length-1][1]+"");
            
            if(!rb1d.isSelected() && !rb2d.isSelected()){
                try{
                String s = JOptionPane.showInputDialog("Please input dimesion !.");
                if(s.compareTo("1d")==0 || s.compareTo("1D") == 0){
                    rb1d.setSelected(true);

                }else if(s.compareTo("2d")==0 || s.compareTo("2D") == 0){
                    rb2d.setSelected(true);

                }else{
                        JOptionPane.showMessageDialog(null,"Dimesion is 1D or 2D !");
                        return;
                    }
                }catch(NullPointerException e){
                    return;
                }
                checkDim();
            }
            if (rb1d.isSelected()){
                    // test format file.
                    int sum = 0;
                    for (double[] gcoord1 : gcoord)
                    sum += gcoord1[1];

                    if (sum != 0){
                        JOptionPane.showMessageDialog(null,"Wrong format. Please select an other file  !");
                        tfnx.setText("0");
                        return;
                    }
                    else{
                        tfnx.setText(gcoord.length -1 +"");//String.valueOf(gcoord.length)  
                    }
            }else {
                // test format file
                int sum = 0;
                for(double[]gcoord1 : gcoord)
                    sum += gcoord1[1];
                    if (sum == 0){
                        JOptionPane.showMessageDialog(null,"Wrong format. Please select an other file  !");
                        tfnx.setText("0");
                        tfny.setText("0");
                        return;
                    }
                    else{
                        int countNx = 0;
                        for (int i = 0; i < gcoord.length/2; i++){
                            if ( gcoord[i+1][1]==gcoord[i][1])
                                countNx++;
                            else
                                i =  gcoord.length/2-1;
                        }
                        int countNy = gcoord.length/(countNx + 1) - 1; // nn = nodeX*Nodey (Langange P1) nodeX = 2*Nx + 1, nodeY = 2*Ny + 1
                        tfnx.setText(countNx+"");
                        tfny.setText(countNy+"");
                    }
            }
    }
 
    
    private boolean checkString(String s){
       char[]c = s.toCharArray();
       
       int temp = 0;
       for (int i = 0; i < c.length; i++) {
           if ( (c[i] < 48 || c[i] > 57) && c[i] != '+'&& c[i] !='-'&& c[i] != '*'&& c[i] !='/'
              && c[i] != '^'&& c[i] !='x'&& c[i] != 'y'&& c[i] !='t'&& c[i] != ' '&& c[i] !=')'&& c[i] !='(')
               temp+=1;   
       }  
       return temp == 0;
   }
    private static boolean readInteger(String s) {
        try {
            s.charAt(0);
            int n = Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;

    }
    private static boolean readFloat(String s) {
        try {
            s.charAt(0);
            float n = Float.parseFloat(s);
        } catch (Exception e) {
            return false;
        }
        return true;

    }
    private long getTime(){
        return System.nanoTime();
    }
    private String timeRun(long start, long end){
        long time = end - start;
        if (time < 5000000)
            return time + "ns";
        else
            return (long) (time/1000000) + "ms";
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
                
            }
        });
    }
//<editor-fold defaultstate="collapsed" desc="Variables declaration">
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcancel;
    private javax.swing.JButton btclear;
    private javax.swing.JButton btok;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cbsm;
    private javax.swing.JComboBox cbte;
    private javax.swing.JComboBox cbts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbalpha;
    private javax.swing.JLabel lbbeta;
    private javax.swing.JLabel lbdt;
    private javax.swing.JLabel lbgx;
    private javax.swing.JLabel lbgy;
    private javax.swing.JLabel lbkappa;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbny;
    private javax.swing.JLabel lbt;
    private javax.swing.JLabel lbte;
    private javax.swing.JLabel lbts;
    private javax.swing.JLabel lbya;
    private javax.swing.JLabel lbyb;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem miopen;
    private javax.swing.JRadioButton rb1d;
    private javax.swing.JRadioButton rb2d;
    private javax.swing.JRadioButton rbdirichlet;
    private javax.swing.JRadioButton rbelliptic;
    private javax.swing.JRadioButton rbneumann;
    private javax.swing.JRadioButton rbparabolic;
    private javax.swing.JRadioButton rbpoison;
    private javax.swing.JRadioButton rbrobin;
    private javax.swing.JTextArea taduex;
    private javax.swing.JTextArea taf;
    private javax.swing.JTextArea taout;
    private javax.swing.JTextArea tauex;
    private javax.swing.JTextField tfalpha;
    private javax.swing.JTextField tfbeta;
    private javax.swing.JTextField tfdt;
    private javax.swing.JTextField tfgx;
    private javax.swing.JTextField tfgy;
    private javax.swing.JTextField tfkappa;
    private javax.swing.JTextField tfl1;
    private javax.swing.JTextField tfl2;
    private javax.swing.JTextField tfnx;
    private javax.swing.JTextField tfny;
    private javax.swing.JTextField tftime;
    private javax.swing.JTextField tfxa;
    private javax.swing.JTextField tfxb;
    private javax.swing.JTextField tfya;
    private javax.swing.JTextField tfyb;
    private javax.swing.JTextPane tpintro;
    // End of variables declaration//GEN-END:variables
}
//</editor-fold>