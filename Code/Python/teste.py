import matplotlib.pyplot as plt
import numpy as np

# Gerar dados
x = np.linspace(0, 10, 100)  # 100 pontos entre 0 e 10
y = np.sin(x)  # Função seno

# Criar o gráfico
plt.figure(figsize=(8, 6))  # Tamanho da figura
plt.plot(x, y, label='y = sin(x)', color='blue')  # Plotar a função
plt.xlabel('x')  # Rótulo do eixo x
plt.ylabel('y')  # Rótulo do eixo y
plt.title('Gráfico de y = sin(x)')  # Título do gráfico
plt.legend()  # Mostrar a legenda
plt.grid(True)  # Mostrar a grade
plt.savefig('plot.png') #salva o gráfico em um arquivo PNG
print("Gráfico salvo como 'plot.png'")
