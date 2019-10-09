package controller

import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.atomic.AtomicBoolean

object Decriptor {
    private var charPool: List<Char> = ('a'..'z').map { it }
    var result: String = ""
    var startTime = System.currentTimeMillis()
    var myBoolean = AtomicBoolean(true)

    @JvmStatic
    fun main(args: Array<String>) {
        println("Insira a sua senha")
        print(">> ")
        var pass = readLine()
        val hash = CreateHash.getHash(pass)
        var startTime = System.currentTimeMillis()
        if (pass != null) {
            breakMd5(hash, pass.length, num = true, upper = true, show = false)
        }
        var endtTime = System.currentTimeMillis()
        val finish = (endtTime - startTime) / 1000
        if (finish > 60){
            val min = finish%60
            val sec = finish - (min * 60)
            println("Tempo de quebra de $min: $sec")
        }else{
            println("Tempo de quebra de $finish Segundos")
        }

    }

    fun breakHash(){
        while(true) {
            println("Insira a sua senha")
            print(">> ")
            var pass = readLine()
            val hash = CreateHash.getHash(pass)
            var startTime = System.currentTimeMillis()
            if (pass != null) {
                breakMd5(hash, pass.length, num = true, upper = true, show = false)
            }
            var endtTime = System.currentTimeMillis()
            val finish = (endtTime - startTime) / 1000
            if (finish > 60) {
                val min = finish % 60
                val sec = finish - (min * 60)
                println("$min: $sec")
                break
            } else {
                println("$finish - Segundos")
                break
            }
        }
    }





    fun breakMd5(hash: String, size: Int, upper: Boolean = false, num: Boolean = false, show: Boolean = false) {

        when {
            upper and num -> charPool = ('a'..'z') + ('A'..'Z') + ('1'..'9')
            upper -> charPool = ('a'..'z') + ('A'..'Z')
            num -> charPool = ('a'..'z') + ('1'..'9')
        }
        val th = RandonHash(size, show, hash, "1", charPool)
        th.start()

        val th1 = RandonHash(size, show, hash, "2", charPool)
        th1.start()

        val th2 = RandonHash(size, show, hash, "3", charPool)
        th2.start()

        val th3 = RandonHash(size, show, hash, "4", charPool)
        th3.start()

        val th4 = RandonHash(size, show, hash, "5", charPool)
        th4.start()

        val th5 = RandonHash(size, show, hash, "6", charPool)
        th5.start()

        val th6 = RandonHash(size, show, hash, "7", charPool)
        th6.start()

        val th7 = RandonHash(size, show, hash, "8", charPool)
        th7.start()



        th1.join()
        th.join()
        th2.join()
        th3.join()
        th4.join()
        th5.join()
        th6.join()
        th7.join()

        when (hash) {
            CreateHash.getHash(th1.result) -> println("Thread 1 - ${th1.result}")
            CreateHash.getHash(th.result) -> println("Thread 2 - ${th.result}")
            CreateHash.getHash(th2.result) -> println("Thread 3  - ${th2.result}")
            CreateHash.getHash(th3.result) -> println("Thread 4 - ${th3.result}")
            CreateHash.getHash(th4.result) -> println("Thread 5  - ${th4.result}")
            CreateHash.getHash(th5.result) -> println("Thread 6 - ${th5.result}")
            CreateHash.getHash(th6.result) -> println("Thread 7  - ${th6.result}")
            CreateHash.getHash(th7.result) -> println("Thread 8 - ${th7.result}")
        }



    }

}

class RandonHash(val size: Int, val show: Boolean, val hash: String, val th_name: String, val characters: List<Char>)
    : Thread() {
    var result: String = ""
    private val cpSize = characters.size


    override fun run() {
        var str: String = ""
        var rhs: String = ""
        do {
            if (Decriptor.myBoolean.get()) {
                str = generateStr(size)
                if (show) println(str)
                rhs = CreateHash.getHash(str)
            } else {
//                println("Thread Interrompida $th_name")
                break
            }
        } while (hash != rhs)

        Decriptor.myBoolean.set(false)

//        println("Thread Finalizada $th_name")


        this.result = str
    }

    fun generateStr(n: Int): String { // gera string aleatoria
        return (1..n).map { ThreadLocalRandom.current().nextInt(0, cpSize) }
                .map(characters::get)
                .joinToString("");
    }



}