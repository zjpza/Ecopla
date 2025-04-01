![image](https://github.com/user-attachments/assets/ea438156-312d-4e5a-9f55-f0410d164efb)

# CODIGO

    
        @startuml
    actor "Usuário" as user
    participant ":TelaPedido" as tela
    participant ":Administrador" as adm
    participant ":ServicoImpressao" as servico
    database ":Banco de Dados" as BD
    
    user -> tela : iniciarPedidoCompraFilamento()
    activate tela
    
    tela -> tela : mostrarMensagem("Pedido iniciado")
    
    tela -> user : solicitarModelo3D()
    user --> tela : mtOutModelo
    
    tela -> adm : analisarModelo(mtOutModelo)
    activate adm
    
    alt Modelo Válido
        adm --> tela : modeloValido()
        deactivate adm
    
        tela -> BD : armazenarModelo(mtOutModelo)
        activate BD
        BD --> tela : confirmaçãoArmazenamento()
        deactivate BD
    
        tela -> tela : mostrarMensagem("Modelo Confimado!")
    
        tela -> servico : setModeloImpressao(mtOutModelo)
        activate servico
        servico --> tela : modeloImpresso()
        deactivate servico
    
    else Modelo Inválido
        adm --> tela : modeloInvalido()
        deactivate adm
    
        loop Reenvio do Modelo 3D
            tela -> user : solicitarModelo3D()
            user --> tela : mtOutModelo
    
            tela -> adm : analisarModelo(mtOutModelo)
            activate adm
    
            alt Modelo Reenviado Válido
                adm --> tela : modeloValido()
                deactivate adm
    
                tela -> BD : armazenarModelo(mtOutModelo)
                activate BD
                BD --> tela : confirmaçãoArmazenamento()
                deactivate BD
    
                tela -> tela : mostrarMensagem("Modelo Confimado!")
    
                tela -> servico :setModeloImpressao(mtOutModelo)
                activate servico
                servico --> tela : modeloImpresso()
                deactivate servico
    
            else Modelo Reenviado Inválido
                tela -> tela : mostrarMensagem("Modelo inválido, envie novamente")
            end
        end
    end
    
    deactivate tela
    @enduml
