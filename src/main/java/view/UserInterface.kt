package view

object View {
    // espressão regular que verifica se a data esta no formato correto YYYY-MM-DD
    private val regex = """^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$""".toRegex()

    fun menu() {
//        println("Bem vindo")
        println("-----------------------------")
        println("[1] - Criar Conta:           ")
        println("[2] - Acessar sua conta:     ")
        println("[3] - Recuperar sua senha:   ")
//        println("[4] - Quebrar senha de um ID:")
        println("[0] - Sair:                  ")
        println("-----------------------------")
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
        var pass1: String
        var pass2: String

        do {
            println("*Sua senha:")
            pass1 = readLine()!!
            while (pass1.isBlank()) pass1 = readLine()!!

            println("Digite novamente a senha:")
            pass2 = readLine()!!
            while (pass2.isBlank()) pass2 = readLine()!!

            if (pass1 != pass2) {
                println("As senhas não batem, por favor digite novamente")
            }

        }while (pass1 != pass2)

        println("--------------------------")
        println("Nome: $name $lastname" +
                "\nData de Nascimento: $birth" +
                "\nSexo: $sex" +
                "\nLogin: $login" +
                "\nSenha: ${replacePassword(pass1)}\n")

        return listOf(name, lastname, birth, sex, login, pass1)


    }

    fun replacePassword(pass: String): String {
        return (pass.map { "*" }).joinToString("")
    }



}
