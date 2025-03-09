![image](https://github.com/user-attachments/assets/c82075d2-aa9e-4e32-94b9-23746edd49d2)

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
      User --> (Confirmar Pedido)
      User --> (Acompanhar Pedido)
      User --> (Consultar Histórico)

      Admin --> (Gerenciar Usuários)
      Admin --> (Incluir Produtos no Sitema)
      Admin --> (Aprovar Venda)
      Admin --> (Gestão de Usuários)
      Admin --> (Monitoramento do Sistema)
      Admin --> (Incluir Produtos no Sitema)

      System --> (Produção e Envio)

      @enduml
