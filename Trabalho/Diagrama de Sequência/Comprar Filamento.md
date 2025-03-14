![image](https://github.com/user-attachments/assets/4c07016f-6c80-4b8e-a621-e269d002b05f)
# CÓDIGO
    @startuml
    actor usuario as user
    boundary telaPedido as tela
    actor administrador as adm
    participant servicoImpressao as servico
    
    user -> tela: iniciarPedidoCompraFilamento()
    activate tela
    tela --> user: pedido iniciado
    deactivate adm
    
    alt pedido valido
        user -> tela: enviarModelos3d()
        activate tela
        tela -> adm: analisarModelo()
        activate adm
        adm --> tela: retorno da analise
        deactivate adm
        tela --> user: modelo recebido com sucesso
        deactivate tela
        adm -> servico: enviarModeloParaImpressao()
        activate servico
        servico --> adm: modelo recebido com sucesso
        deactivate servico
        else modelo invalido
        loop envie novo modelo 3d
            user -> tela: enviarModelos3d()
            activate tela
            tela -> adm: analisarModelo()
            activate adm
            adm --> tela: retorno da analise
            deactivate adm
            tela --> user: modelo recebido com sucesso
            deactivate tela
        end 
    
    else pedido invalido
        tela --> user: pedido invalido, tente novamente
    end
    
    user -> tela: acompanharPedido()
    activate tela
    tela --> user: status do pedido
    deactivate tela
    @enduml
