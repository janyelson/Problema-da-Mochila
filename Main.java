import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan;
		int n;
		int m;
		int[] pi;
		int[] vi;

		int max = Integer.MIN_VALUE;
		int indiceMax = -1;
		boolean[][] xi;
		int[][] result;

		
		scan = new Scanner(System.in);
		String linha = "";

		linha = scan.nextLine();

		n = Integer.parseInt(linha.split("\\s+")[0]);
       	m = Integer.parseInt(linha.split("\\s+")[1]) + 1;

        linha = scan.nextLine();

        pi = new int[n];
        vi = new int[n];

        for(int i = 0; i < n; i++) {
        	pi[i] = Integer.parseInt(linha.split("\\s+")[i]);
        }

        linha = scan.nextLine();

       	for(int i = 0; i < n; i++) {
       		vi[i] = Integer.parseInt(linha.split("\\s+")[i]);
       	}

       	scan.close();

		result = new int[n+1][];
		xi = new boolean[n][];

		result[n] = new int[m];

		for(int j = 0; j < m; j++) {
			result[n][j] = 0;
		}

		for(int i = n-1; i >= 0; i--) {
			result[i] = new int[m];
			xi[i] = new boolean[m];
			for(int j = 0; j < m; j++) {
				if((j-pi[i]) < 0) {
					result[i][j] = result[i+1][j];
					xi[i][j] = false;
				}
				else {

					if(result[i+1][j] < (result[i+1][j-pi[i]] + vi[i])) {

						result[i][j] = (result[i+1][j-pi[i]] + vi[i]);
						xi[i][j] = true;

						if(max < result[i][j]) {
							indiceMax = j;
							max = result[i][j];
						}

					}else {
						result[i][j] = result[i+1][j];
						xi[i][j] = false;
					}
				}
			}
		}

		System.out.println("Valor: " + max);
		System.out.print("Produtos escolhidos: ");
		for(int i = 0; i < n; i++) {
			if(indiceMax >= 0 && xi[i][indiceMax] ) {
				System.out.print("x" + (i+1) + " ");
				indiceMax = (indiceMax - pi[i]);
			}
		}
	}
}