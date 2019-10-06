package controller

import br.com.ConexaoBanco.TreatmentModel
import br.com.ConexaoBanco.User
import view.View

var flag = true


fun main(args: Array<String>) {
    while (flag){
        View.menu()
        val choice = readLine()
        if (choice != null) {
            indexer(choice)
        }
    }
}

fun indexer(choice: String){
    when(choice) { // swich case with super powers
        "0" -> {
            println("Criar conta")
            createAccont()
        }
        "1" -> {
            println("Acessar conta")

        }
        "2" -> {
            println("Recuperar senha")
            recoveryPassword()
        }
        "3" -> flag = false
    }
}

private fun createAccont() {

    val userData = View.newAccont() // chamada da view

    val name = userData[0]!!.capitalize()
    val last = if (!userData[1].isNullOrBlank()) userData[1]!!.capitalize() else null
    val birth = if (!userData[2].isNullOrBlank()) userData[2] else null
    val sex = if (!userData[3].isNullOrBlank()) userData[3]!!.get(0) else null
    val login = userData[4]
    val encriptedPassword = CreateHash.getHash(userData[5])

    val usuario = User(name, last, birth, sex, login, encriptedPassword)
    if (usuario.save()) {
        println("USUARIO SALVO COM SUCESSO")
    } else {
        println("ERRO USUARIO NÃO SALVO")
    }


}

private fun recoveryPassword() {
    while(true){

        println("--------------------------")
        println("Primeiro passo nos indique seu login:")
        println("Seu login: ")
        print('>')
        var login = readLine()
        while (login.isNullOrBlank()) login = readLine()
        println("--------------------------")
        println("Segundo passo nos indique seu Primeiro Nome:")
        println("Primeiro Nome: ")
        var firstName = readLine()
        while (firstName.isNullOrBlank()) firstName = readLine()
        if(TreatmentModel.verifyFirstName(login, firstName) != true){
            println("Login e Primeiro nome nao compativeis")
            println("--------------------------")
            break
        }
        println("--------------------------")
        val id = TreatmentModel.getIdFromLoginUser(login)

        println("Insira sua nova senha:")
        var newPass = CreateHash.getHash(readLine())
        while (newPass.isNullOrBlank()) newPass = readLine()

        println("Insira novamente sua senha:")
        var newPassVer = CreateHash.getHash(readLine())
        while (newPassVer.isNullOrBlank()) newPassVer = readLine()
        var passw = newPass
        if(newPass != newPassVer) passw = verifyTwoPass(newPass, newPassVer)
        TreatmentModel.setNewPassword(passw, id)
        println("Trocou a senha com Sucesso")


    }

}

private fun verifyTwoPass(pass1: String, pass2: String): String{
    while(pass1 != pass2) {
        println("Insira sua nova senha:")
        var newPass = CreateHash.getHash(readLine())
        while (newPass.isNullOrBlank()) newPass = readLine()

        println("Insira novamente sua senha:")
        var newPassVer = CreateHash.getHash(readLine())
        while (newPassVer.isNullOrBlank()) newPassVer = readLine()
    }

    return pass1
}