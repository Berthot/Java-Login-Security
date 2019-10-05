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
    when(choice) {
        "0" -> {
            println("criar conta")
            View.newAccont()
        }
        "1" -> println("Acessar conta")
        "2" -> println("Recuperar senha")
        "3" -> flag = false
    }
}

private fun creat_accont() {
    val usuario = User("Kotlin", "melhor que java", null, 'M', "kt", "kt")
    if(usuario.save()) {
        println(usuario)
    } else {
        println("Errooo")
    }

}