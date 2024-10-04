package com.example.trabalho;

import java.io.FileWriter;
import java.io.IOException;

public class RecordUsuario {
    public RecordUsuario() {
    }

    public static void escreverArq(String dados) {
        try {
            FileWriter escreverArq = new FileWriter("Banco.csv", true);

            try {
                escreverArq.append(dados);
                escreverArq.close();
            } catch (Throwable var5) {
                try {
                    escreverArq.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            escreverArq.close();
        } catch (IOException var6) {
            System.out.println("Erro! Arquivo não encontrado.");
        }

    }

    public static void substituirArq(String dados) {
        try {
            FileWriter escreverArq = new FileWriter("Banco.csv");

            try {
                escreverArq.append(dados);
                escreverArq.close();
            } catch (Throwable var5) {
                try {
                    escreverArq.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            escreverArq.close();
        } catch (IOException var6) {
            System.out.println("Erro! Arquivo não encontrado.");
        }

    }
}
