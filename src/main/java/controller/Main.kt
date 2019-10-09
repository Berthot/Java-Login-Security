@file:Suppress("UNREACHABLE_CODE")

package controller

import br.com.ConexaoBanco.TreatmentModel
import br.com.ConexaoBanco.User
import view.View

var flag = true

fun main() {

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
        "1" -> {
            println("Criar conta")
            createAccont()
        }
        "2" -> {
            println("Acessar conta")
            acessAccount()
        }
        "3" -> {
            println("Recuperar senha")
            recoveryPassword()
        }
        "hash" -> {
            println("Quebrar senha de um Login")
            breakHash()
        }
        "0" -> flag = false
    }
}

private fun createAccont() {

    val userData = View.newAccont() // chamada da view

    val name = userData[0]!!.capitalize()
    val last = if (!userData[1].isNullOrBlank()) userData[1]!!.capitalize() else null
    val birth = if (!userData[2].isNullOrBlank()) userData[2] else null
    val sex = if (!userData[3].isNullOrBlank()) userData[3]!![0] else null
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
        if(TreatmentModel.verifyFirstName(login, firstName.capitalize()) != true){
            println("Login e Primeiro nome nao compativeis")
            println("--------------------------")
            break
        }
        println("--------------------------")
        val id = TreatmentModel.getIdFromLoginUser(login)

        println("Insira sua nova senha:")
        var newPass = CreateHash.getHash(readLine())
        while (newPass.isNullOrBlank()) newPass = CreateHash.getHash(readLine())

        println("Insira novamente sua senha:")
        var newPassVer = CreateHash.getHash(readLine())
        while (newPassVer.isNullOrBlank()) newPassVer = CreateHash.getHash(readLine())

        if(newPass != newPassVer) newPass = verifyTwoPass(newPass, newPassVer)
        TreatmentModel.setNewPassword(newPass, id)
        println("Trocou a senha com Sucesso")
        break

    }

}

private fun verifyTwoPass(pass1: String, pass2: String): String{
    while(pass1 != pass2) {
        println("Insira sua nova senha:")
        var newPass = CreateHash.getHash(readLine())
        while (newPass.isNullOrBlank()) newPass = CreateHash.getHash(readLine())

        println("Insira novamente sua senha:")
        var newPassVer = CreateHash.getHash(readLine())
        while (newPassVer.isNullOrBlank()) newPassVer = CreateHash.getHash(readLine())
    }

    return pass1
}

private fun acessAccount(){
    while(true) {
        println("--------------------------")
        print("Seu login: ")
        var login = readLine()
        while (login.isNullOrBlank()) login = readLine()
        val id = TreatmentModel.getIdFromLoginUser(login)
        print("Sua Senha: ")
        var passsword = CreateHash.getHash(readLine())
        while (passsword.isNullOrBlank()) passsword = CreateHash.getHash(readLine())

        println("--------------------------")
        if(!TreatmentModel.matchPassword(id,passsword)) {
            println("Usuario ou senha incorretos!")
            break
        }
        println("Acesso Garantido!!")





        var user = User.get_user(id.toShort())
        println("Seu ID: " + user.id)
        println("Seu Primeiro nome: " + user.name)
        println("Seu Sobremnome: " + user.last_name)
        println("Sua data de aniversario: " + user.birth)
        println("Seu Sexo: " + getSex(user.sex))
        println("Seu login: " + user.login)
        println("Data de criação: " + user.timeStamp)
        break




    }
}

fun getSex(sex: Char): String {
    return if (sex == 'M') {
        "Homem"
    } else {
        "Mulher"
    }
}

fun breakHash(){
    println("Insira o Login que quer quebrar a senha!")
    print(">> ")
    var login = readLine()
    var id = TreatmentModel.getIdFromLoginUser(login)
    var pass = id.toShort().let { TreatmentModel.getPassFromId(it) }
    val hash = CreateHash.getHash(pass)
    var startTime = System.currentTimeMillis()
    println("Start Timer!!")
    if (pass != null) {
        Decriptor.breakMd5(hash, pass.length, num = true, upper = true, show = false)
    }
    var endtTime = System.currentTimeMillis()
    val finish = (endtTime - startTime) / 1000
    if (finish > 60) {
        val min = finish % 60
        val sec = finish - (min * 60)
        println("$min: $sec")

    } else {
        println("$finish - Segundos")

    }

}
