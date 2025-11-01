fun fillingMatrix(matrix: Array<IntArray>) {
    for (i in 0 until matrix.size) {
        println("Строка ${i + 1}: ")
        for (j in 0 until matrix[0].size) {
            while (true) {
                val input = readln().trim()
                if (input.length == 3 && input.all { it.isDigit() }) {
                    val number = input.toInt()
                    matrix[i][j] = number
                    break
                } else {
                    println("Ошибка! Введите ровно три цифры: ")
                }
            }
        }
    }
}

fun printMatrix(matrix: Array<IntArray>) {
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[0].size) {
            print("${matrix[i][j].toString().padStart(3, '0')}\t")
        }
        println()
    }
}

fun task1(matrix: Array<IntArray>) : Int {
    val uniqueDigits = mutableSetOf<Char>()
    for (row in matrix) {
        for (number in row) {
            number.toString().forEach { digit ->
                if (digit.isDigit()) {
                    uniqueDigits.add(digit)
                }
            }
        }
    }

    return uniqueDigits.size
}

fun checkingSimm (matrix: Array<Array<Int>>) : Boolean {
    var count = 0;
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix.size) {
            if (matrix[i][j] == matrix[j][i]) {
                count++
            }
        }
    }
   return if (count == 10) true else false
}

fun main() {
    while (true) {
        println(
            "1) Программа запрашивает количество строк и столбцов для двухмерного массива. Пользователь вводит необходимое количество трёхзначных чисел (числа могут повторяться). Происходит подсчёт различных цифр в полученном массиве и на консоль выводится двумерный массив из введённых чисел и количество различных цифр использумемых в данном массиве.\n" +
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

                    val unique = task1(matrix)
                    println("В массиве использовано $unique различных цифр")
                } else {
                    println("Некорректный ввод: введите положительные целые числа.")
                }
            }

            2 -> {
                val size = 5
                val matrix: Array<Array<Int>> = Array(size) {
                    Array(size) {(1 ..9).random() }
                }
                println("Исходная матрица:")
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        print("${matrix[i][j]}\t")
                    }
                    print("\n")
                }
                if (checkingSimm(matrix)) {
                    println("Матрица является симметричным относительно главной диагонали")
                }
                else {
                    println("Матрица не является симметричной относительно главной диагонали")
                }
            }

            3 -> {
                val counter = arrayOf(
                    21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16,
                    15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
                )
                val russianAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
                val alphabetMap = counter.zip(russianAlphabet.toList()).toMap()

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