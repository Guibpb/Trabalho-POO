def escaneia():
    lingua = input('Pick a coding language: ')
    return lingua

def printa (parametro):
    mensagem = f"Code in {parametro}!"
    print(mensagem)
    
mensagem = escaneia()
printa(mensagem)
