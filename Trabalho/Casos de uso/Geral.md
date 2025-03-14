![image](https://github.com/user-attachments/assets/70120e99-eb30-4fcc-8cae-045357069e22)



# CÓDIGO  
    @startuml
    left to right direction
    
    actor "Usuário" as User
    actor "Administrador" as Admin
    actor "Reciclador" as Reciclador
    
    rectangle "Ecopla" {
      User -- (Vender Plástico Reciclável)
      User -- (Comprar Filamentos)
      User -- (Realizar Pagamento)
      User -- (Acompanhar Pedido)
      User -- (Cadastro)
      User -- (Login)
      User -- (Recuperação de Senha)
      User -- (Gerenciar Perfil)
      User -- (Upload de Arquivo 3D)
      User -- (Configurar Impressão)
      User -- (Consultar Histórico)
    
      Admin -- (Gerenciar Usuários)
      Admin -- (Incluir Produtos no Sitema)
      Admin -- (Aprovar Venda)
      Admin -- (Gestão de Usuários)
      Admin -- (Monitoramento do Sistema)
    
      Reciclador -- (Produção e Envio)
    @enduml
