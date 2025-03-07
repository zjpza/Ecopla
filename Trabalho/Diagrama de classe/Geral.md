![image](https://github.com/user-attachments/assets/d9a632e6-1b98-46a7-acb4-e3ce32e5e467)

# CÓDIGO
    @startuml
    class Usuario {
   
    }

    class Cliente {
   
    }

    class Pedido {

    }

    class ItemPedido {
   
    }

    class Pagamento {
  
    }

    class Impressao3D {
       
    }   

    Usuario <|-- Cliente
    Usuario "1" -- "0..*" Pedido
    Pedido "1" -- "1..*" ItemPedido
    Pedido "1" -- "1" Pagamento
    Usuario "1" -- "0..*" Impressao3D
    @enduml


