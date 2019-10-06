package controller

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
        "1" -> println("Acessar conta")
        "2" -> println("Recuperar senha")
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