![image](https://github.com/user-attachments/assets/98a1fb82-735a-4d6a-ba33-3c8c9e824c7f)


# CÓDIGO
    @startuml
    class Arquivo3D {
    
    }

    class Filamento {
    
    }

    class Impressao3D {
   
    }

    Usuario "1" -- "0..*" Arquivo3D
    Arquivo3D "1" -- "1" Impressao3D
    Impressao3D "1" -- "1" Filamento
    @enduml
