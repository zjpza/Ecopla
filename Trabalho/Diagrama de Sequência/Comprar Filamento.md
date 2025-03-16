@startuml ComprarFilamentos

actor usuario as user
boundary telaPedido as tela
actor administrador as adm
actor servicoImpressao as servico

user -> tela: iniciarPedidoCompraFilamento()
activate tela

tela -> user: solicitarModelo3d()
user --> tela: setModelo3D(mtOutModelo)

tela -> adm: analisarModelo(getModelo3D())
activate adm

alt modelo valido
    adm --> tela: modeloValido()
    deactivate adm

    tela --> user: modelo recebido com sucesso

    tela -> servico: enviarModeloParaImpressao(getModelo3D())
    activate servico
    servico --> tela: modelo impresso com sucesso
    deactivate servico

else modelo invalido
    adm --> tela: modeloInvalido()
    deactivate adm

    loop envie novo modelo 3D
        tela -> user: solicitarModelo3d()
        user --> tela: setModelo3D(mtOutModelo)
        
        tela -> adm: analisarModelo(getModelo3D())
        activate adm

        alt modelo reenviado valido
            adm --> tela: modeloValido()
            deactivate adm
            
            tela --> user: modelo recebido com sucesso

            tela -> servico: enviarModeloParaImpressao(getModelo3D())
            activate servico
            servico --> tela: modelo impresso com sucesso
            deactivate servico
        else modelo reenviado invalido
            tela -> tela: voltar ao loop
        end
    end
end

user -> tela: acompanharPedido()
tela --> user: getStatusPedido()

deactivate tela

@enduml
