![image](https://github.com/user-attachments/assets/7c14cc14-5e17-4807-befd-236f36336f20)
# CÓDIGO
    @startuml
    left to right direction

    actor "Usuário" as User
    actor "Administrador" as Admin

    rectangle "Ecopla" {
        User --> (Vender Plástico Reciclável)
        Admin --> (Aprovar Venda)
    
        User --> (Comprar Filamentos)
        User --> (Realizar Pagamento)
        User --> (Acompanhar Pedido)
    
    @enduml
