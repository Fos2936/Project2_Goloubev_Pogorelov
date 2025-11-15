val alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
val values = listOf(
    21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2,
    28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5,
    30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
)

val alphabetToValueMap = alphabet.indices.associate { i -> alphabet[i] to values[i] }

val valueToAlphabetMap = alphabetToValueMap.entries.associate { it.value to it.key }

fun task1() {
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

    fun task1Count(matrix: Array<IntArray>): Int {
        val uniqueDigits = mutableSetOf<Char>()
        for (row in matrix) {
            for (num in row) {
                num.toString().forEach { digit -> uniqueDigits.add(digit) }
            }
        }
        return uniqueDigits.size
    }

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

        val unique = task1Count(matrix)
        println("Количество различных цифр: $unique")
    } else {
        println("Некорректный ввод: введите положительные целые числа.")
    }
}


fun task2() {
    fun isSymmetric(matrix: Array<Array<Int>>): Boolean {
        for (i in matrix.indices) {
            for (j in i + 1 until matrix.size) {
                if (matrix[i][j] != matrix[j][i]) return false
            }
        }
        return true
    }

    val size = 5
    val matrix = Array(size) { Array(size) { (1..9).random() } }
    println("Матрица:")
    for (row in matrix) {
        println(row.joinToString("\t"))
    }
    println(if (isSymmetric(matrix)) "Матрица симметрична." else "Матрица не симметрична.")
}


fun task3() {
    fun isRussianLetters(input: String): Boolean {
        return input.isNotEmpty() && input.all { it in alphabet }
    }

    fun inputWord(): String {
        var result: String
        do {
            print("Введите слово: ")
            result = readln().trim().lowercase()
            if (!isRussianLetters(result)) {
                println("Ошибка! Введите буквы из кириллицы и без лишних символов!")
            }
        } while (!isRussianLetters(result))
        return result
    }

    fun encrypt(
        secretWord: List<Pair<Char, Int>>,
        text: List<Pair<Char, Int>>
    ): List<Pair<Char, Int>> {
        return text.zip(secretWord) { textPair, secretPair ->
            val sum = textPair.second + secretPair.second
            val newValue = ((sum - 1) % 33) + 1
            val newChar = valueToAlphabetMap[newValue] ?: '?'
            newChar to newValue
        }
    }
    fun decrypt(
        secretWord: List<Pair<Char, Int>>,
        text: List<Pair<Char, Int>>
    ): List<Pair<Char, Int>> {
        return text.zip(secretWord) { textPair, secretPair ->
            val dif = textPair.second - secretPair.second
            val newValue = ((dif - 1 + 33) % 33) + 1
            val newChar = valueToAlphabetMap[newValue] ?: '?'
            newChar to newValue
        }
    }

    print("Ключевое слово: ")
    val secret = inputWord()
    val secretValues: List<Pair<Char, Int>> = secret.map { it to alphabetToValueMap[it]!! }

    print("Исходный текст: ")
    val text = inputWord()
    val textValues: List<Pair<Char, Int>> = text.map { it to alphabetToValueMap[it]!! }

    var mode: Char
    do {
        print("Шифрование (e) или дешифрование (d): ")
        mode = readln().trim().firstOrNull() ?: ' '
    } while (mode != 'e' && mode != 'd')

    val repeatedSecret = List(textValues.size) { index -> secretValues[index % secretValues.size] }
    val result = if (mode == 'e') {
        encrypt(repeatedSecret, textValues)
    } else {
        decrypt(repeatedSecret, textValues)
    }

    val resultString = result.joinToString("") { it.first.toString() }
    println("Результат: $resultString")
}


fun task5() {
    print("Сколько слов вы введёте: ")
    val count = readln().toInt()
    println("Введите различные слова: ")
    val wordList = mutableListOf<String>()
    for (i in 0 until count) {
        val words = readln()
        wordList.add(words)
    }
    print(wordList)

    val generalList = mutableListOf<Char>()
    for (word in wordList) {
        val characters: List<Char> = word.toList()
        println("Слово '$word' состоит из символов: $characters")
        for (char in characters) {
            println(" -> Символ: $char")
        }
        generalList.addAll(characters)
    }

    print(generalList)

}

fun showMenu() {
    while (true) {
        println(
            "1) Программа запрашивает количество строк и столбцов для двухмерного массива." +
                    "Пользователь вводит необходимое количество трёхзначных чисел (числа могут повторяться)." +
                    "Происходит подсчёт различных цифр в полученном массиве и на консоль выводится двумерный массив из введённых чисел и количество различных цифр используемых  в данном массиве.\n\n" +

                    "2) Программа создаёт квадратную матрицу разменостью 5 и определяет симметрична ли она относительно главной диагонали, т.е. - элемент (1, 2) = (2, 1), элемент (1, 3) = (3, 1) и т.д.\n\n" +

                    "3) Программа создаёт массив, содержащий символы русского алфавита. Символы алфавита нумеруются от 1 до 33." +
                    "Каждое число используется только один раз. Сообщение шифруется с помощью ключевого слова, задаваемого пользователем." +
                    "Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита." +
                    "Программа шифрует и дешифрует строковое выражение (т.е. программа спрашивает - зашифровать или расшифровать текст и ключевое слово)." +
                    "Первый массив считается закольцованным. Регистр букв не имеет значения.\n\n" +

                    "4) Task 4.\n\n" +

                    "5) Task 5."
        )
        print("Выбор: ")
        val choice = readln().toIntOrNull()
        when (choice) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> {

            }
            5 -> task5()
            else -> println("Такого пункта нет")
        }
        println()
        print("Вы хотите продолжить выполнение программы (y/n): ")
        if (readln().trim().firstOrNull() == 'n') break
    }
}


fun main() {
    showMenu()
}
