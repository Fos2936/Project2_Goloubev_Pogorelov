fun fillingMatrix(matrix: Array<DoubleArray>) {
    for (i in 0.. matrix.size - 1) {
        println("Строка $i: ")
        for (j in 0.. matrix[0].size - 1) {


        }
        println()
    }
}
fun main(args: Array<String>) {
    while (true) {
        println(
            "1) Task 1.\n" +
                    "2) Task 2.\n" +
                    "3) Task 3.\n" +
                    "4) Task 4.\n" +
                    "5) Task 5."
        )
        print("Выбор: ")
        val choice = readln().toIntOrNull()
        when (choice) {
            1 -> {
                print("Количество строк: ")
                val rows = readln().toIntOrNull()

                print("Количество столбцов: ")
                val columns = readln().toIntOrNull()

                if (rows != null && columns != null && rows > 0 && columns > 0) {
                    val matrix = Array(rows) { DoubleArray(columns) }
                    println("Введите различные трёхзначные числа (числа могут повторяться): ")
                    fillingMatrix(matrix);

                } else {
                    println("Некорректный ввод: введите положительные целые числа.")
                }
            }

            2 -> {

            }

            3 -> {

            }

            4 -> {

            }

            5 -> {

            }

            else -> {
                println("Такого пункта нет")
            }
        }
        println()
        print("Вы хотите продолжить выполнение программы (y/n): ")
        val agree = readln().trim().firstOrNull()
        if (agree == 'n') return
    }
}