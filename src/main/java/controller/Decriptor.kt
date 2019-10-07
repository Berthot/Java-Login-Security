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
        val hash = CreateHash.getHash("aa8c")
        var startTime = System.currentTimeMillis()
        breakMd5(hash, 4, num = true, upper = false, show = false)
        var endtTime = System.currentTimeMillis()
        val finish = (endtTime - startTime) / 1000
        println("$finish - Segundos")

    }

    fun breakMd5(hash: String, size: Int, upper: Boolean = false, num: Boolean = false, show: Boolean = false) {

        when {
            upper and num -> charPool = ('a'..'z') + ('A'..'Z') + ('1'..'9')
            upper -> charPool = ('a'..'z') + ('A'..'Z')
            num -> charPool = ('a'..'z') + ('1'..'9')
        }
        val th = RandonHash(size, show, hash, "1", charPool)
        val th1 = RandonHash(size, show, hash, "2", charPool)
        val th2 = RandonHash(size, show, hash, "3", charPool)
        val th3 = RandonHash(size, show, hash, "4", charPool)

        th.start()
        th1.start()
        th2.start()
        th3.start()


        th1.join()
        th.join()
        th2.join()
        th3.join()

        println("finishhhhh")
    }

}

class RandonHash(val size: Int, val show: Boolean, val hash: String, val th_name: String, val characters: List<Char>)
    : Thread() {
    private var result: String = ""
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
                println("FALLLLLLLSOOOOOOOOOOOOO  $th_name")
                break
            }
        } while (hash != rhs)
        Decriptor.myBoolean.set(false)

        println("ACABOOOOOOOOOOOOOOOOOOOOOOOOOOO $th_name")


        this.result = str
    }

    fun generateStr(n: Int): String { // gera string aleatoria
        return (1..n).map { ThreadLocalRandom.current().nextInt(0, cpSize) }
                .map(characters::get)
                .joinToString("");
    }

}