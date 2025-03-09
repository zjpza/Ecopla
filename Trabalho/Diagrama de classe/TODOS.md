![image](https://github.com/user-attachments/assets/4f3b0a7d-4d85-430c-97d6-1ca42926137b)

# CÓDIGO
@startuml
class Usuario {
    +cadastrar()
    +fazerLogin()
    +recuperarSenha()
}

class Cliente {
    +realizarCompra()
    +acompanharPedido()
}

class Administrador {
    +gerenciarUsuarios()
    +monitorarSistema()
}

class Reciclador {
    +cadastrarMaterial()
    +verificarStatusVenda()
}

class Pedido {
    +realizarPedido()
    +cancelarPedido()
}

class ItemPedido {
}

class Produto {
}

class Filamento {
}

class Pagamento {
    +processarPagamento()
}

class Impressao3D {
    +processarImpressao()
}

class Arquivo3D {
    +uploadArquivo()
}

class MaterialReciclavel {
    +validarMaterial()
}

class VendaMaterial {
    +finalizarVenda()
}

Usuario <|-- Cliente
Usuario <|-- Administrador
Usuario <|-- Reciclador

Cliente "1" -- "0..*" Pedido : realiza
Pedido "1" -- "1..*" ItemPedido : contém
Pedido "1" -- "1" Pagamento : é pago por
ItemPedido "1" -- "1" Produto : contém

Cliente "1" -- "0..*" Impressao3D : solicita
Impressao3D "1" -- "1" Arquivo3D : utiliza
Impressao3D "1" -- "1" Filamento : é impresso com

Reciclador "1" -- "0..*" VendaMaterial : vende
VendaMaterial "1" -- "1" MaterialReciclavel : contém
@enduml
