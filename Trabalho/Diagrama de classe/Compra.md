![image](https://github.com/user-attachments/assets/4d89473d-f90c-4804-a101-9099623b0b9d)

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
