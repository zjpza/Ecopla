@startuml ComprarFilamentos

actor usuario as user
boundary telaPedido as tela
control administrador as adm
participant servicoImpressao as servico

user -> tela: iniciarPedidoCompraFilamento()
activate tela
tela -> adm: analisarPedido()
activate adm
adm --> tela: retornoAnalise()
deactivate adm

alt pedido valido
    user -> tela: enviarModelos3d()
    activate tela
    tela -> adm: analisarModelo()
    activate adm
    adm --> tela: modeloValido()
    deactivate adm
    tela --> user: modelo recebido com sucesso
    deactivate tela
    adm -> servico: enviarModeloParaImpressao()
    activate servico
    servico --> adm: modelo impresso com sucesso
    deactivate servico
    else modelo invalido
    loop envie novo modelo 3d
        user -> tela: enviarModelos3d()
        activate tela
        tela -> adm: analisarModelo()
        activate adm
        adm --> tela: modeloValido()
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
