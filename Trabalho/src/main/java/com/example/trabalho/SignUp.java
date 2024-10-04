package com.example.trabalho;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignUp {
    private static int idAtual;

    public SignUp() {
    }

    public static boolean signUp(String usuario, String email, String senha, String tipoUsuario) throws FileNotFoundException {
        if (compararUsuarios(usuario, email, senha, senha)) {
            String dados = String.format("\n%s,%s,%s,%s", usuario, email, senha, tipoUsuario);
            RecordUsuario.escreverArq(dados);
            return true;
        } else {
            return false;
        }
    }

    private static boolean compararUsuarios(String usuario, String email, String senha, String senha2) throws FileNotFoundException {
        boolean usuarioValido = true;
        Scanner scanArquivo = new Scanner("Banco.csv");

        try {
            String[] usuarioInfo = null;

            while(true) {
                if (!scanArquivo.hasNextLine() || !usuarioValido) {
                    try {
                        idAtual = Integer.parseInt(usuarioInfo[0]) + 1;
                    } catch (NumberFormatException var9) {
                    }
                    break;
                }

                String inputInfo = scanArquivo.nextLine();
                usuarioInfo = inputInfo.split(",");
                if (usuarioInfo[1].equals(usuario)) {
                    System.out.println("Esse usuário já existe.");
                    usuarioValido = false;
                }

                if (usuarioInfo[2].equals(email)) {
                    System.out.println("Esse e-mail já está cadastrado.");
                    usuarioValido = false;
                }

                if (usuario.contains(",")) {
                    System.out.println("Formato de usuário inválido(caracter inapropriado).");
                    usuarioValido = false;
                }

                if (email.contains(",") || !email.contains("@") || !email.contains(".")) {
                    System.out.println("Formato de e-mail inválido.");
                    usuarioValido = false;
                }

                if (senha.contains(",")) {
                    System.out.println("Formato de senha inválida(caracter inapropriado).");
                    usuarioValido = false;
                }

                if (!senha.equals(senha2)) {
                    System.out.println("Senhas incompatíveis.");
                    usuarioValido = false;
                }
            }
        } catch (Throwable var10) {
            try {
                scanArquivo.close();
            } catch (Throwable var8) {
                var10.addSuppressed(var8);
            }

            throw var10;
        }

        scanArquivo.close();
        return usuarioValido;
    }
}
