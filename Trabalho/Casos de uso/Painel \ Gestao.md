![image](https://github.com/user-attachments/assets/9133741a-69dd-4db0-b456-0c4135b57010)
# CODIGO
    @startuml
    left to right direction

    actor "Usuário" as User
    actor "Administrador" as Admin

    rectangle "Ecopla" {
           User --> (Acompanhar Pedido)
        User --> (Consultar Histórico)
    
        Admin --> (Gestão de Usuários)
        Admin --> (Monitoramento do Sistema)
        Admin --> (Incluir Produtos no Sitema)
    @enduml
