fun fillingMatrix(matrix: Array<IntArray>) {
    for (i in 0 until matrix.size) {
        println("Строка ${i + 1}: ")
        for (j in 0 until matrix[0].size) {
            val number = readln().toInt()
            matrix[i][j] = number
        }
        println()
    }
}

fun printMatrix(matrix: Array<IntArray>) {
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            print("${matrix[i][j]} \t")
        }
        println()
    }
}

fun task1(matrix: Array<IntArray>) : Int {
    val unique = 0
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {

        }
    }

    return unique
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
                    val matrix = Array(rows) { IntArray(columns) }
                    println("Введите различные трёхзначные числа (числа могут повторяться): ")
                    fillingMatrix(matrix);

                    println("Исходная матрица:")
                    printMatrix(matrix)
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