![image](https://github.com/user-attachments/assets/fc5ddffa-19fc-4370-af07-457dddde26ec)


# CÓDIGO  
    @startuml
    left to right direction

    actor "Usuário" as User
    actor "Administrador" as Admin
    actor "Ecopla" as System

    rectangle "Ecopla" {
      User --> (Vender Plástico Reciclável)
      User --> (Comprar Filamentos)
      User --> (Realizar Pagamento)
      User --> (Acompanhar Pedido)
      User --> (Cadastro)
      User --> (Login)
      User --> (Recuperação de Senha)
      User --> (Gerenciar Perfil)
      User --> (Upload de Arquivo 3D)
      User --> (Configurar Impressão)
      User --> (Consultar Histórico)

      Admin --> (Gerenciar Usuários)
      Admin --> (Incluir Produtos no Sitema)
      Admin --> (Aprovar Venda)
      Admin --> (Gestão de Usuários)
      Admin --> (Monitoramento do Sistema)

      System --> (Produção e Envio)

      @enduml
