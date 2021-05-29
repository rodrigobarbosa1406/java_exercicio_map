package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			Map<String, Integer> votos = new HashMap<>();
			
			System.out.print("Informe o caminho do arquivo CSV: ");
			String strArquivoCSV = sc.nextLine();
			
			try (BufferedReader br = new BufferedReader(new FileReader(strArquivoCSV))){
				String line = br.readLine();
				
				while (line != null) {
					String[] vetor = line.split(",");
					String nome = vetor[0];
					Integer qtde = Integer.parseInt(vetor[1]);
					
					if (votos.containsKey(nome)) {
						qtde = Integer.parseInt(vetor[1]) + votos.get(nome);
					}
					
					votos.put(nome, qtde);
					
					line = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for (String chave : votos.keySet()) {
				System.out.println(chave + ": " + votos.get(chave));
			}
		} catch (RuntimeException e) {
			System.out.println("Erro...");
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

}
