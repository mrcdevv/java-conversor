package principal;

import javax.swing.JOptionPane;

public class Principal {
	
	private static String[] lista = {"algo", "algo1", "algo2", "algo3", "algo4", "algo5"}; 
	
	public static void main(String[] args) {
		String cantidad = JOptionPane.showInputDialog(null, "Ingrese la cantidad a convertir:", "Conversor de Monedas", JOptionPane.PLAIN_MESSAGE);
		
		if(validarNumeros(cantidad)) {
			String monedaOrigen = (String) JOptionPane.showInputDialog(null, "Seleccione la divisa de origen:", "Conversor de Monedas", JOptionPane.PLAIN_MESSAGE, null, lista, lista[0]);
			String monedaDestino = (String) JOptionPane.showInputDialog(null, "Seleccione la divisa de destino:", "Conversor de Monedas", JOptionPane.PLAIN_MESSAGE, null, lista, lista[0]);
		}
		
        
      
		//System.out.println("Seleccion " + monedaOrigen);
		//System.out.println("Seleccion " + monedaDestino);
	}
	
	private static boolean validarNumeros(String n) 
	{
		try {
			double algo =  Double.parseDouble(n);
			return true;
			
		} catch (NumberFormatException e) {
			return false;
		}
		
	}
}
