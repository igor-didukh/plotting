/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ploting;

// <editor-fold defaultstate="collapsed" desc="Import">
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
// </editor-fold>
/**
 *
 * @author dyschemist
 */
public class Okno extends javax.swing.JFrame {
    int num;
    double left, right, eps;
    double left_bound ,right_bound;                             // For calculation - changable;
    FunctionsToPlot curFunction;                                // Selected function
    XYSeriesCollection dataset = new XYSeriesCollection();      // For drawing - changable;
    
    double methodOneIteration(MethodsOfCalc mcalc, JTextArea area, int i) {
        double xi = mcalc.f(left_bound, right_bound, curFunction);
        if ( Math.signum(curFunction.f(left_bound)) != Math.signum(curFunction.f(xi)) ) 
            right_bound = xi; 
        else 
            left_bound = xi;
        double fxi = curFunction.f(xi);
        area.append(i + ". x = " + xi + "; a = " + left_bound + "; b = " + right_bound + "; f(x) = " + fxi + "\n");
        mcalc.setSeriesItem(xi, fxi);
        return xi;
    }
    
    void doMethod(MethodsOfCalc mcalc, JTextArea textArea) {
        double x;
        left_bound = left; right_bound = right;
        
        textArea.append(mcalc.getName() + ". ");
        if (num == 0) textArea.append("(accuracy calculation): \n"); else textArea.append("(iteration calculation): \n");
        textArea.append("0. a = " + left_bound + "; b = " + right_bound + "\n");
        
        if (num == 0) {                                                                   // Accuracy calculation
            final int alert_counter = 50;
            int i = 1;
            do {
                x = methodOneIteration(mcalc, textArea, i);
                i++;
            } while ( (Math.abs(curFunction.f(x)) >= eps) & (i <= alert_counter) );
            if (i > alert_counter) textArea.append("FORCED CYCLE TERMINATIION!");
        }
        else for (int i = 1; i <= num; i++) methodOneIteration(mcalc, textArea, i);      // Iteration calculation
    }
    
    void rahuj() {
        dataset.removeAllSeries();
        
        FalsePosition mFalsePosition = new FalsePosition();
        doMethod(mFalsePosition, jtaFalsePosition);
        dataset.addSeries(mFalsePosition.getSeries());
        
        Bisection mBisection = new Bisection();
        doMethod(mBisection, jtaBisection);
        dataset.addSeries(mBisection.getSeries());
        
        calcDataset();
    }
    
    void calcDataset() {
        final int count = 300;
        XYSeries series = new XYSeries(curFunction.getName());
        
        double[] x = new double[count];
        double step = (right - left) / ((double)count - 1.0);
        x[0] = left;
        series.add( x[0], curFunction.f(x[0]) );
        
        for (int i=1; i<x.length; i++) {
            x[i] = x[i-1] + step;
            series.add( x[i], curFunction.f(x[i]) );
        }
        dataset.addSeries(series);
    }
    
    void rysuj() {
        String name = curFunction.getName();
        
        JFreeChart jFreeChart = ChartFactory.createXYLineChart(name, "x", name, dataset, PlotOrientation.VERTICAL, true, false, false);
        
        XYPlot xyPlot = (XYPlot) jFreeChart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        
        renderer.setSeriesShapesVisible(2, false);
        
        renderer.setSeriesPaint(0, Color.RED);              // Set color for every series
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GRAY);
        
        xyPlot.setRenderer(renderer);
        
        ChartPanel CP = new ChartPanel(jFreeChart);

        this.jPanelPlot.setLayout(new java.awt.BorderLayout());
        this.jPanelPlot.add(CP, BorderLayout.CENTER);
        this.jPanelPlot.validate();
        this.jPanelPlot.setVisible(true);
    }

    /**
     * Creates new form Okno
     */
    public Okno() {
        initComponents();
    }

    private void closeProgram(java.awt.AWTEvent evt) {
        String message = "Are you really want to exit?";
        String title = "Exit";
        // display the JOptionPane showConfirmDialog
        int reply = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonWykres = new javax.swing.ButtonGroup();
        buttonStopMethod = new javax.swing.ButtonGroup();
        jPanelPlot = new javax.swing.JPanel();
        jPanelResult = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaBisection = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaFalsePosition = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbFunctions = new javax.swing.JComboBox<>();
        jlLeft = new javax.swing.JLabel();
        jtfLeft = new javax.swing.JTextField();
        jlRight = new javax.swing.JLabel();
        jtfRight = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jrbStopMethod1 = new javax.swing.JRadioButton();
        jrbStopMethod2 = new javax.swing.JRadioButton();
        jtfEpsilon = new javax.swing.JTextField();
        jtfNumber = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButtonPlot = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelPlot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelPlotLayout = new javax.swing.GroupLayout(jPanelPlot);
        jPanelPlot.setLayout(jPanelPlotLayout);
        jPanelPlotLayout.setHorizontalGroup(
            jPanelPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanelPlotLayout.setVerticalGroup(
            jPanelPlotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelResult.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelResult.setToolTipText("");
        jPanelResult.setName(""); // NOI18N

        jtaBisection.setColumns(20);
        jtaBisection.setRows(5);
        jScrollPane1.setViewportView(jtaBisection);

        jtaFalsePosition.setColumns(20);
        jtaFalsePosition.setRows(5);
        jScrollPane2.setViewportView(jtaFalsePosition);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Bisection method:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("False position method:");

        javax.swing.GroupLayout jPanelResultLayout = new javax.swing.GroupLayout(jPanelResult);
        jPanelResult.setLayout(jPanelResultLayout);
        jPanelResultLayout.setHorizontalGroup(
            jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelResultLayout.createSequentialGroup()
                        .addGroup(jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelResultLayout.setVerticalGroup(
            jPanelResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Select function to plot:");

        jcbFunctions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "f(x) = sin(x) - 0,5", "f(x) = cos(x)", "f(x) = exp(x) - 3" }));
        jcbFunctions.setSelectedIndex(1);
        jcbFunctions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFunctionsActionPerformed(evt);
            }
        });

        jlLeft.setText("Left bound:");

        jtfLeft.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfLeft.setText("0");

        jlRight.setText("Right bound:");

        jtfRight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfRight.setText("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbFunctions, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jlLeft)
                    .addComponent(jlRight)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfRight, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbFunctions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jtfLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRight)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Select stopping criterion:");

        buttonStopMethod.add(jrbStopMethod1);
        jrbStopMethod1.setSelected(true);
        jrbStopMethod1.setText("Achievement of the specified accuracy:");

        buttonStopMethod.add(jrbStopMethod2);
        jrbStopMethod2.setText("Execution of a specified number of iterations:");

        jtfEpsilon.setEditable(false);
        jtfEpsilon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfEpsilon.setText("0.01");

        jtfNumber.setEditable(false);
        jtfNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfNumber.setText("20");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jrbStopMethod1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jrbStopMethod2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                        .addComponent(jtfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jtfEpsilon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbStopMethod1)
                    .addComponent(jtfEpsilon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbStopMethod2)
                    .addComponent(jtfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonPlot.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonPlot.setText("Plot & calc");
        jButtonPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlotActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPlot, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanelResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.closeProgram(evt);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.closeProgram(evt);
    }//GEN-LAST:event_formWindowClosing

    private void jButtonPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlotActionPerformed
        jtaBisection.setText("");
        jtaFalsePosition.setText("");
        
        try {
            left = Double.parseDouble(this.jtfLeft.getText());
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error in left bound!", "Wrong bound!", ERROR_MESSAGE);
            return;
        }
        
        try {
            right = Double.parseDouble(this.jtfRight.getText());
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Error in rihgt bound!", "Wrong bound!", ERROR_MESSAGE);
            return;
        }
        
        if (jrbStopMethod1.isSelected()) {
            num = 0;
            try {
                eps = Double.parseDouble(this.jtfEpsilon.getText());
                if (eps <= 0) {
                    JOptionPane.showMessageDialog(this, "The accuracy must be a positive number!", "Wrong accuracy!", ERROR_MESSAGE);
                    return;
                }
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error in accuracy field!", "Wrong accuracy!", ERROR_MESSAGE);
                return;
            }
        }
        else {
            eps = 0.0;
            try {
                num = Integer.parseInt(this.jtfNumber.getText());
                if (num <= 0) {
                    JOptionPane.showMessageDialog(this, "The number of iterations must be positive!", "Wrong iterations!", ERROR_MESSAGE);
                    return;
                }
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error in iterations' field!", "Wrong iterations!", ERROR_MESSAGE);
                return;
            }
        }
        
        if (left < right){
            if (left < curFunction.getLeftBound()) {
                JOptionPane.showMessageDialog(this, "Left bound is less than " + curFunction.getLeftBound() + "!", "Wrong values!", ERROR_MESSAGE);
                return;
            }
            
            if (right > curFunction.getRightBound()) {
                JOptionPane.showMessageDialog(this, "Right bound is more than " + curFunction.getRightBound()+ "!", "Wrong values!", ERROR_MESSAGE);
                return;
            }
            
            if (curFunction.f(left) * curFunction.f(right) > 0) {
                JOptionPane.showMessageDialog(this, "Selected function must have values of different signs on the bounds!", "Wrong values!", ERROR_MESSAGE);
                return;
            }
            this.rahuj();
            this.rysuj();
        } 
        else {
            JOptionPane.showMessageDialog(this, "The right bound must be greater than the left one!", "Wrong bound input", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonPlotActionPerformed

    private void setCurFunction() {
        switch ( jcbFunctions.getSelectedIndex() ) {
            case 0:
                curFunction = new f1();
                break;
            case 1:
                curFunction = new f2();
                break;
            case 2:
                curFunction = new f3();
                break;
        }
        jlLeft.setText("Left bound (more than " + curFunction.getLeftBound() + "):");
        jlRight.setText("Right bound (less than " + curFunction.getRightBound() + "):");
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setCurFunction();
    }//GEN-LAST:event_formWindowOpened

    private void jcbFunctionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFunctionsActionPerformed
        setCurFunction();
    }//GEN-LAST:event_jcbFunctionsActionPerformed

    // <editor-fold defaultstate="collapsed" desc="Variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonStopMethod;
    private javax.swing.ButtonGroup buttonWykres;
    private javax.swing.JButton jButtonPlot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelPlot;
    private javax.swing.JPanel jPanelResult;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcbFunctions;
    private javax.swing.JLabel jlLeft;
    private javax.swing.JLabel jlRight;
    private javax.swing.JRadioButton jrbStopMethod1;
    private javax.swing.JRadioButton jrbStopMethod2;
    private javax.swing.JTextArea jtaBisection;
    private javax.swing.JTextArea jtaFalsePosition;
    private javax.swing.JTextField jtfEpsilon;
    private javax.swing.JTextField jtfLeft;
    private javax.swing.JTextField jtfNumber;
    private javax.swing.JTextField jtfRight;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}