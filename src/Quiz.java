/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Project.ConnectionProvider;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 *
 * @author Jhonson
 */
public class Quiz extends javax.swing.JFrame {

    public String QuestionId="1";
    public String Answer;
    public int min=0;
    public int sec=0;
    public int marks=0;
    
    
    public void answerCheck()
    {
        String Fanswer="";
        if(jRadioButton1.isSelected())
        {
            Fanswer=jRadioButton1.getText();
        }
        else if(jRadioButton2.isSelected())
        {
            Fanswer=jRadioButton2.getText();
        }
        else if(jRadioButton3.isSelected())
        {
            Fanswer=jRadioButton3.getText();
        }
        else 
        {
            Fanswer=jRadioButton4.getText();
        }
        
        
        if(Fanswer.equals(Answer))
        {
            marks=marks+1;
   
        }
        else
        {
            try{
            Connection con=ConnectionProvider.getCon();
              Statement st=con.createStatement();
              
            ResultSet rs=st.executeQuery("select answer from question where id='"+QuestionId+"'");
            JOptionPane.showMessageDialog(null,"The correct answer is "+rs);
        }
            catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
    
}  
        }
        
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        //Q.no change
        int qid=Integer.parseInt(QuestionId);
        qid=qid+1;
        QuestionId=String.valueOf(qid);
        
        
        
        //hide next for last qns
        if(QuestionId.equals("10"))
        {
            jButton1.setVisible(false);
        }
        
        
        
    }
    public void question()
    {
        try{
              Connection con=ConnectionProvider.getCon();
              Statement st=con.createStatement();
              
            ResultSet rs=st.executeQuery("select * from question where id='"+QuestionId+"'");
            while(rs.next())
            {
                jLabel8.setText(rs.getString(1));
                jLabel10.setText(rs.getString(2));
                jRadioButton1.setText(rs.getString(3));
                jRadioButton2.setText(rs.getString(4));
                jRadioButton3.setText(rs.getString(5));
                jRadioButton4.setText(rs.getString(6));
                Answer=rs.getString(7);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            
        } 
    }
    public void submit(){
        String RollNo=jLabel12.getText();
        answerCheck();
        try{
            Connection con=ConnectionProvider.getCon();
              Statement st=con.createStatement();
              st.executeUpdate("update student set marks='"+marks+"'where RollNo=='"+RollNo+"'");
              String marks1=String.valueOf(marks);
              setVisible(false);
              new Finished(marks1).setVisible(true);
              //JOptionPane.showMessageDialog(null,marks1);
              
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
    
}  
    }
  
    /**
     * Creates new form Quiz
     */
    public Quiz() {
        initComponents();
    }
    Timer time;
    public Quiz(String RollNo) {
        initComponents();
         jTextArea1.setEditable(false);
        jLabel12.setText(RollNo);
       
        try{
            Connection con=ConnectionProvider.getCon();
              Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from question where id='"+QuestionId+"'");
            while(rs.next())
            {
                jLabel8.setText(rs.getString(1));
                jLabel10.setText(rs.getString(2));
                jRadioButton1.setText(rs.getString(3));
                jRadioButton2.setText(rs.getString(4));
                jRadioButton3.setText(rs.getString(5));
                jRadioButton4.setText(rs.getString(6));
                Answer=rs.getString(7);
            }
            
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
            
       }
        
        //time
        setLocationRelativeTo(this);
        time=new Timer(1000,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //throw new UnsupportedOperationException("Not supported yet.");
                jLabel5.setText(String.valueOf(sec));
                jLabel4.setText(String.valueOf(min));
                if(sec==60)
                    {
                        sec=0;
                        min++;
                        if(min==15)
        {
            time.stop();
            answerCheck();
            submit();
        }
                    }
                sec++;
            }
        });
            time.start();   
                }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel1.setText("Total Time");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel2.setText("15 min");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel3.setText("Time Taken");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel4.setText("00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel5.setText("00");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel6.setText("Total Question");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel7.setText("Question No");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel8.setText("00");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel10.setText("Question");

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton2.setText("jRadioButton2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton3.setText("jRadioButton3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jRadioButton4.setText("jRadioButton4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jButton2.setText("Finish Test");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel11.setText("Roll No");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel13.setText("00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(87, 87, 87)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8)))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel13))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(169, 169, 169)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jRadioButton2)
                                .addComponent(jRadioButton1)
                                .addComponent(jRadioButton3)
                                .addComponent(jRadioButton4)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7)
                        .addComponent(jLabel13)))
                .addGap(73, 73, 73)
                .addComponent(jLabel10)
                .addGap(56, 56, 56)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton4)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        answerCheck();
        question();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null,"Do you really want to submit?","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            answerCheck();
        question();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected())
        {
            jRadioButton2.setSelected(false);
                    jRadioButton3.setSelected(false);
                            jRadioButton4.setSelected(false);
                                    
                            
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton2.isSelected())
        {
            jRadioButton1.setSelected(false);
                    jRadioButton3.setSelected(false);
                            jRadioButton4.setSelected(false);
                                    
                            
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton3.isSelected())
        {
            jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                            jRadioButton4.setSelected(false);
                                    
                            
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton4.isSelected())
        {
            jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                            jRadioButton3.setSelected(false);
                                    
                            
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
