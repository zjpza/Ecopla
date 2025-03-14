![image](https://github.com/user-attachments/assets/4f3b0a7d-4d85-430c-97d6-1ca42926137b)

# CÓDIGO
    @startuml
    class Usuario {
    }
    
    class Cliente {
    }
    
    class Administrador {
    }
    
    class Reciclador {
    }
    
    class Pedido {
    }
    
    class ItemPedido {
    }
    
    class Produto {
    }
    
    class Filamento {
    }
    
    class Pagamento {
    }
    
    class Impressao3D {
    }
    
    class Arquivo3D {
    }
    
    class MaterialReciclavel {
    }
    
    class VendaMaterial {
    }
    
    Usuario <|-- Cliente
    Usuario <|-- Administrador
    Usuario <|-- Reciclador
    
    
    Cliente "1" -- "1*..." Pagamento : realiza
    Cliente "1" -- "0..*" Pedido : realiza
    Pedido "1" -- "1..*" ItemPedido : contém
    Pedido "1" -- "1" Pagamento : é pago por
    ItemPedido "1" -- "1" Produto : contém
    
    Cliente "1" -- "0..*" Impressao3D : solicita
    Impressao3D "1" -- "1" Arquivo3D : utiliza
    Impressao3D "1" -- "1" Filamento : é impresso com
    
    Reciclador "1" -- "0..*" VendaMaterial : vende
    VendaMaterial "1" -- "1" MaterialReciclavel : contém
    Filamento "1*..." -- "1" MaterialReciclavel : feito por
    @enduml
