package principal;

import api.Exchange;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	
	Exchange ex = new Exchange();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFrom.setBounds(27, 44, 130, 14);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTo.setBounds(177, 44, 130, 14);
		contentPane.add(lblTo);
		
		JComboBox cbTo = new JComboBox();
		cbTo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbTo.setBounds(177, 69, 130, 22);
		contentPane.add(cbTo);
		
		JComboBox cbFrom = new JComboBox();
		cbFrom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbFrom.setBounds(27, 69, 130, 22);
		contentPane.add(cbFrom);
		
		// Tendria que hacer un for o un foreach
		//cbFrom.addItem(ex.getSymbols());
		
		
		//Tengo que pensar como haria esto
		
		
		
		
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmount.setBounds(27, 115, 49, 14);
		contentPane.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAmount.setBounds(101, 112, 130, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConvert.setBounds(121, 149, 77, 23);
		contentPane.add(btnConvert);
		
		JLabel lblTitle = new JLabel("Currency Converter");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 335, 35);
		contentPane.add(lblTitle);
	}
}
