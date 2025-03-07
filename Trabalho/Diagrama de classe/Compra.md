![image](https://github.com/user-attachments/assets/70557b34-b833-4190-a016-24a1019c3ab0)

# CÓDIGO 
    @startuml
    class Cliente {
    
    }

    class MaterialReciclavel {

    }

    class VendaMaterial {
    
    }

    Cliente "1" -- "0..*" VendaMaterial
    VendaMaterial "1" -- "1" MaterialReciclavel
    @enduml
