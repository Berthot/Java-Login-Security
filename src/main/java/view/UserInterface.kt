package view
import br.com.ConexaoBanco.User

object View {

    fun menu() {
        println("Bem vindo")
        println("--------------------------")
        println("[0] - Criar Conta:        ")
        println("[1] - Acessar sua conta:  ")
        println("[2] - Recuperar sua senha:")
        println("[3] - Sair:")
        println("--------------------------")
        println()
    }

    fun newAccont() {
        println()
        println("Criando conta")
        println("Compos com * são obrigatórios")
        println("--------------------------")
        println("*Digite seu nome:")
        val name = readLine()
        println("Digite seu sobrenome:")
        val lastname = readLine()
        println("Digite sua data de nascimento:")
        println("Formato YYYY-MM-DD")
        val birth = readLine()
        println("Seu sexo:")
        println("M/F")
        val sex = readLine()
        println("*Seu login:")
        val login = readLine()
        println("*Sua senha:")
        val pass = readLine()
        println("--------------------------")
        println("$name - $lastname - $birth - $sex - $login - $pass")
    }




}
