![image](https://github.com/user-attachments/assets/3939ca0f-31ec-4c01-9c98-3cff7cbbcd91)

# CODIGO
    @startuml
    left to right direction

    actor "Usuário" as User
    actor "Ecopla" as System

    rectangle "Ecopla" {
        User --> (Upload de Arquivo 3D)
        User --> (Configurar Impressão)
        User --> (Confirmar Pedido)
    
        System --> (Produção e Envio)
    }
    @enduml
