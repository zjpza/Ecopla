@startuml VenderPlastico

actor usuario as user
participant TelaCadastro as tela
participant CadastroControler as ctrl
participant Verificador as ver
database Dados as dado

activate user
activate tela
tela -> tela: ApesentarCadastro()
activate tela
deactivate tela
deactivate tela

loop while usuarioDisponivel = False
    ctrl -> tela: solicitarUsuarioCadastro()
    activate ctrl
    activate tela
    user -> tela: Insere Nome de Usuario
    tela --> ctrl : usuarioDesejado
    deactivate tela
    ctrl --> ver : verificaUser(usuarioDesejado)
    activate ver
    ver -> ver: verifica se o usuario já exite
    ver --> ctrl: False
    ctrl -> tela : usuarioDisponivel(False)
    activate tela
end


ver --> ctrl: True
ctrl --> dado : usuarioDisponivel(usuarioDesejado)
activate dado
deactivate dado
deactivate ver
ctrl -> tela : usuarioDisponivel(True)
deactivate tela

loop while emailDisponivel = False
    ctrl -> tela: solicitarEmailCadastro()
    activate tela
    user -> tela: Insere Email
    tela --> ctrl : emailDesejado
    deactivate tela
    ctrl --> ver : verificaEmail(emailDesejado)
    activate ver
    ver -> ver: verifica se o email já foi cadastrado
    ver --> ctrl: False
    ctrl -> tela : emailDisponivel(False)
    activate tela
end

ver --> ctrl: True
ctrl --> dado : salvarEmail(emailDesejado)
activate dado
deactivate dado
deactivate ver
ctrl -> tela : emailDisponivel(True)
deactivate tela

ctrl -> tela : solicitarSenha()
activate tela
user -> tela : Insere Senha
tela --> ctrl : senha
deactivate tela
ctrl --> dado: salvarSenha(senha)
activate dado
deactivate dado
ctrl -> tela : cadastroCompleto()
activate tela
deactivate tela
@enduml
