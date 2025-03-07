![image](https://github.com/user-attachments/assets/34173436-3615-4a7e-bfcf-b31c4b620912)



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
