package com.aluraconversor.ui;

// All for the interface
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
//import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.aluraconversor.api.Exchange;
import com.aluraconversor.model.Monedas;
import com.aluraconversor.util.ValidationUtils;
import javax.swing.UIManager;
import java.awt.Color;

public class MainWindow extends JFrame {

	private JPanel contentPane;	
	private Exchange objExchange = new Exchange();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setAlwaysOnTop(true);
		setTitle("MRC Conversor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFrom.setBounds(38, 91, 33, 14);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTo.setBounds(56, 128, 15, 14);
		contentPane.add(lblTo);
		
		JComboBox<Monedas> cbTo = new JComboBox<Monedas>();
		cbTo.setToolTipText("To");
		cbTo.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 15));
		cbTo.setBounds(81, 123, 224, 25);
		loadCombo(cbTo);
		contentPane.add(cbTo);
		
		JComboBox<Monedas> cbFrom = new JComboBox<Monedas>();
		cbFrom.setToolTipText("From");
		cbFrom.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 15));
		cbFrom.setBounds(81, 87, 224, 25);
		loadCombo(cbFrom);
		contentPane.add(cbFrom);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmount.setBounds(27, 54, 49, 14);
		contentPane.add(lblAmount);
		
		JTextField txtAmount = new JTextField();
		txtAmount.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 17));
		txtAmount.setBounds(81, 46, 224, 30);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JTextField txtResult = new JTextField();
		txtResult.setHorizontalAlignment(SwingConstants.CENTER);
		txtResult.setForeground(new Color(65, 105, 225));
		txtResult.setBorder(null);
		txtResult.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtResult.setEditable(false);
		txtResult.setBounds(27, 213, 278, 30);
		contentPane.add(txtResult);
		txtResult.setColumns(10);
		
		
		// Button 
		JButton btnConvert = new JButton("Convert");
		btnConvert.setForeground(UIManager.getColor("EditorPane.background"));
		btnConvert.setBorderPainted(false);
		btnConvert.setBackground(new Color(65, 105, 225));
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Monedas fromObject = (Monedas)cbFrom.getSelectedItem();
				Monedas toObject = (Monedas)cbTo.getSelectedItem();
				
				String from = fromObject.getSymbol();
				String to = toObject.getSymbol();
				
				
				if(ValidationUtils.validateNumbers(txtAmount.getText())) {
					double amount = Double.parseDouble(txtAmount.getText());
					
					String conversion = String.valueOf(getResult(from, to, amount));
					txtResult.setText(conversion);
				} else {
					JOptionPane.showMessageDialog(btnConvert, "Error, u should only introduce a number", "Error", JOptionPane.ERROR_MESSAGE);
					txtAmount.setText("");
				}
			}
		});
		
		btnConvert.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 17));
		btnConvert.setBounds(27, 167, 278, 30);
		contentPane.add(btnConvert);
		
		JLabel lblTitle = new JLabel("Currency Converter");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 335, 39);
		contentPane.add(lblTitle);
	}
	
	
	private double getResult(String from, String to, double amount) {
		String urlConversion = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=" + amount;
		String response = objExchange.apiConnection(urlConversion);
		double result = objExchange.getExchange(response);
		
		return result;
	}
	
	private void loadCombo(JComboBox<Monedas> combo) {
		ArrayList<Monedas> data = new ArrayList<Monedas>();
		
		String urlSymbols = "https://api.exchangerate.host/symbols";
		String response = objExchange.apiConnection(urlSymbols);
		data = objExchange.getData(response);
		
		for (Monedas moneyData : data) {
			combo.addItem(moneyData);
		}
	}
	
	
}
