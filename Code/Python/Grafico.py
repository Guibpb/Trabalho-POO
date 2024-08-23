import matplotlib.pyplot as plt

x1 = [10000000, 50000000, 100000000, 500000000, 1000000000]
y1 = [0.15, 0.73, 1.42, 7.08, 14.14]
plt.plot(x1, y1, label = "Média 2", color = "blue", linestyle = "dashed", linewidth = 2, marker = 'o', markerfacecolor = 'blue', markersize = 8)

x2 = [5000, 10000, 20000, 40000, 50000, 80000, 100000]
y2 = [0.08, 0.28, 1.08, 4.37, 6.82, 17.07, 27.31]
plt.plot(x2, y2, label = "Média 1", color = "red", linestyle = "dashed", linewidth = 2, marker = 'o', markerfacecolor = 'red', markersize = 8)

plt.xlabel('Tamanho do Vetor')
plt.ylabel('Tempo de Execução (em segundos)')
plt.title('Gráfico PRÁTICA 1: Análise Experimental')
plt.legend()

plt.show('Tarefa1TinosMedias1e2.png')
