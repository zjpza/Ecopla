![image](https://github.com/user-attachments/assets/7f9b1616-daf2-43ea-b6f5-571bcb2c2eb1)

@startuml VenderPlastico

actor usuario as user
boundary telaVenda as tela
actor administrador as adm
participant deposito as dpst
participant Banco as bank

user -> tela: iniciarVendaPlastico()
activate tela

tela -> user: solicitarTipoPlastico()
user --> tela: selecionaTipoPlastico()

tela -> adm: analisarTipoPlastico(selecionaTipo())
activate adm

alt plastico aceito
    adm --> tela: plasticoAceito()
    deactivate adm

    tela --> user: plastico aceito para venda
    tela --> user: solicta envio do plastico
    
    activate dpst
    dpst --> adm: plasticoEntregue()
    deactivate dpst
    
    activate adm
    adm --> tela: plasticoEntregue()
    tela --> user: pedido recebido
    
    adm --> bank: realizar tranferencia()
    deactivate adm
    activate bank
    bank --> user: envia valor da venda
    deactivate bank
    
    user -> tela: acompanharPedidoDeVenda()
    tela --> user: getStatusPedidoDeVenda()

else plastico recusado

    activate adm
    adm --> tela: plasticoRecusado()
    tela --> user: tipo de plastico recusado
    deactivate adm

end



deactivate tela

@enduml
