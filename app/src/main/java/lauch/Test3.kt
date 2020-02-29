package lauch

abstract class Test3 {
    var num = 0

    fun updateNum(temNum : Int) {
        this.num = temNum
    }
}

class Test4 : Test3() {

}

fun main() {
    val test4 = Test4().also { it.updateNum(10) }
//    test4.updateNum(10)
    for (index in 0..10) {
        println(test4.num)
    }

}