package programaExplicaFabi;

import java.util.Scanner;

public class Inversao {

    public static void main(String[] args) {
		        // Cria um objeto Scanner para capturar a entrada do usuário
		Scanner scanner = new Scanner(System.in);
		        
		        // Solicita que o usuário digite uma palavra
		System.out.print("Digite uma palavra: ");
		String palavra = scanner.nextLine();
		        
		        // Inverte a palavra
		String palavraInvertida = new StringBuilder(palavra).reverse().toString();
		        
		        // Exibe a palavra invertida
		System.out.println("Palavra invertida: " + palavraInvertida);
		        
		        // Fecha o scanner
		scanner.close();
	}


}

