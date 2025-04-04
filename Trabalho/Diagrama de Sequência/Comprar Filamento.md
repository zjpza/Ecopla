![image](https://github.com/user-attachments/assets/065d1953-6902-4985-8f61-c88af9d15ed5)

# CODIGO

      @startuml
    actor "Usuário" as user
    participant ":TelaPedido" as tela
    participant ":Controller" as Controller 
    participant ":Administrador" as adm
    participant ":ServicoImpressao" as servico
    database ":Banco de Dados" as BD
    
    user -> tela : iniciarPedidoCompraFilamento()
    activate tela
    
    Controller --> tela : mostrarMensagem("Pedido iniciado")
    
    Controller -> tela : solicitarModelo3D()
    tela --> Controller : mtOutModelo
    
    Controller -> adm : analisarModelo(mtOutModelo)
    activate adm
    
    alt Modelo Válido
        adm --> Controller : modeloValido()
        deactivate adm
    
        Controller -> BD : armazenarModelo(mtOutModelo)
        activate BD
        BD --> Controller : confirmaçãoArmazenamento()
        deactivate BD
    
        tela -> tela : mostrarMensagem("Modelo Confimado!")
    
        Controller -> servico : setModeloImpressao(mtOutModelo)
        activate servico
        servico --> Controller : modeloImpresso()
        deactivate servico
    
    else Modelo Inválido
        adm --> Controller : modeloInvalido()
        deactivate adm
    
        loop Reenvio do Modelo 3D
            Controller -> tela : solicitarModelo3D()
            tela --> Controller : mtOutModelo
    
            Controller -> adm : analisarModelo(mtOutModelo)
            activate adm
    
            alt Modelo Reenviado Válido
                adm --> Controller : modeloValido()
                deactivate adm
    
                Controller -> BD : armazenarModelo(mtOutModelo)
                activate BD
                BD --> Controller : confirmaçãoArmazenamento()
                deactivate BD
    
                tela -> tela : mostrarMensagem("Modelo Confimado!")
    
                Controller -> servico :setModeloImpressao(mtOutModelo)
                activate servico
                servico --> Controller : modeloImpresso()
                deactivate servico
    
            else Modelo Reenviado Inválido
                tela -> tela : mostrarMensagem("Modelo inválido, envie novamente")
            end
        end
    end
    
    deactivate tela
    @enduml
    deactivate tela
    @enduml
