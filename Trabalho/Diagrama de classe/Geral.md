![image](https://github.com/user-attachments/assets/f31983a0-3ff4-4abf-a7c1-174a24e54f56)

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


