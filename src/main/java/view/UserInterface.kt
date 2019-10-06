package view
import br.com.ConexaoBanco.TreatmentModel
import br.com.ConexaoBanco.User

object View {
    // espressão regular que verifica se a data esta no formato correto YYYY-MM-DD
    private val regex = """^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$""".toRegex()

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

    fun newAccont(): List<String?> { //retorna uma lista com os dados do usuario
        println()
        println("Criando conta")
        println("Compos com * são obrigatórios")
        println("--------------------------")

        println("*Digite seu nome: ")
        var name = readLine()
        while (name.isNullOrBlank()) name = readLine()

        println("Digite seu sobrenome: ")
        val lastname = readLine()

        println("Digite sua data de nascimento: ")
        println("Formato YYYY-MM-DD")
        var birth = readLine()
        while (!(birth.isNullOrBlank()) and !(birth?.let { regex.matches(it) }!!)) birth = readLine()

        println("Seu sexo: ")
        println("M/F")
        var sex = readLine()!!.toUpperCase()
        while (!(sex.isBlank())  and (sex != "M") and (sex != "F")) sex = readLine()!!.toUpperCase()

        println("*Seu login: ")
        var login = readLine()
        while (login.isNullOrBlank()) login = readLine()

        println("*Sua senha: ")
        var pass = readLine()
        while (pass.isNullOrBlank()) pass = readLine()

        println("--------------------------")
        println("Nome: $name $lastname" +
                "\nData de Nascimento: $birth" +
                "\nSexo: $sex" +
                "\nLogin: $login" +
                "\nSenha: ${replacePassword(pass)}\n")

        return listOf(name, lastname, birth, sex, login, pass)


    }

    fun replacePassword(pass: String): String {
        return (pass.map { "*" }).joinToString("")
    }


}
