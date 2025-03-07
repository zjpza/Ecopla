![image](https://github.com/user-attachments/assets/8b45d5b5-3958-4e87-b151-01c73e225c8d)

# CÓDIGO
    @startuml
    left to right direction

    actor "Usuário" as User
    actor "Administrador" as Admin

    rectangle "Ecopla" {
        User --> (Cadastro)
        User --> (Login)
        User --> (Recuperação de Senha)
        User --> (Gerenciar Perfil)
    
        Admin --> (Gerenciar Usuários)
        Admin --> (Incluir Produtos no Sitema)
    }
    @enduml
