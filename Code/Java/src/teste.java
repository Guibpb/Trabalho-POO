import java.util.Scanner;

public class teste {
    public static void main(String[] args) {
        Scanner nomedavariavel = new Scanner(System.in);

        System.out.print("Coloque o dia em que voce naisceu: ");
        int dia = nomedavariavel.nextInt();
        System.out.print("Coloque o mês em que você naisceu: ");
        String buffer = nomedavariavel.nextLine();
        String mes = nomedavariavel.nextLine();
        System.out.println("Você naisceu dia " + dia + " de " + mes);
    }
}
