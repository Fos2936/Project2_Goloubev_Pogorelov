val alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
val values = listOf(
    21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2,
    28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5,
    30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
)

// indices - свойство, возращающее объект IntRange от 0 до size - 1
// associate - функция для преобзазования коллекции в Map
val alphabetToValueMap = alphabet.indices.associate { i -> alphabet[i] to values[i] }

// entries - свойство, возращающее неизменяемый список всех элементов перечисления в порядке их объявления
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
                // padStart() - метод расширения для строк, дополняющий строку символами в начале до достижения заданной длины
                // Например, val str = "123"; val padStr = str.padStart(6, '0'); -> "000123"
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
        fillingMatrix(matrix)

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
        val size = 5
        for (i in 0 until size) {
            for (j in i + 1 until size) {
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
    // !! - оператор принудительного извлечения
    val secretValues: List<Pair<Char, Int>> = secret.map { it to alphabetToValueMap[it]!! }

    print("Исходный текст: ")
    val text = inputWord()
    val textValues: List<Pair<Char, Int>> = text.map { it to alphabetToValueMap[it]!! }

    var mode: Char
    do {
        print("Шифрование (ш) или дешифрование (д): ")
        mode = readln().trim().firstOrNull() ?: ' '
    } while (mode != 'ш' && mode != 'д')

    val repeatedSecret = List(textValues.size) { index -> secretValues[index % secretValues.size] }
    val result =
        if (mode == 'ш') {
            encrypt(repeatedSecret, textValues)
        } else {
            decrypt(repeatedSecret, textValues)
        }

    val resultString = result.joinToString("") { it.first.toString() }
    println("Результат: $resultString")
}

fun task4() {
    fun readPositiveInt(prompt: String): Int? {
        print(prompt)
        val input = readlnOrNull() ?: return null
        if (input.isEmpty() || !input.all { it.isDigit() }) return null
        val value = input.toIntOrNull() ?: return null
        return if (value > 0) value else null
    }

    fun readIntWithValidation(prompt: String): Int? {
        print(prompt)
        val input = readlnOrNull() ?: return null
        if (input.isEmpty() || input.contains(Regex("[^0-9-]"))) return null
        return input.toIntOrNull()
    }

    val size1 = readPositiveInt("Введите размерность 1-го массива: ")
    if (size1 == null) {
        println("Ошибка: Введите положительное целое число для размерности 1-го массива")
        return
    }

    val intList1 = mutableListOf<Int>()
    println("Числа 1-го массива: ")
    for (i in 0 until size1) {
        var number: Int?
        do {
            number = readIntWithValidation("Введите число ${i + 1}: ")
            if (number == null) {
                println("Ошибка: Введите целое число")
            }
        } while (number == null)
        intList1.add(number)
    }

    val size2 = readPositiveInt("Введите размерность 2-го массива: ")
    if (size2 == null) {
        println("Ошибка: Введите положительное целое число для размерности 2-го массива")
        return
    }

    val intList2 = mutableListOf<Int>()
    println("Числа 2-го массива: ")
    for (i in 0 until size2) {
        var number: Int?
        do {
            number = readIntWithValidation("Введите число ${i + 1}: ")
            if (number == null) {
                println("Ошибка: Введите целое число")
            }
        } while (number == null)
        intList2.add(number)
    }

    println("Массив 1: $intList1")
    println("Массив 2: $intList2")

    val intersectionList = mutableListOf<Int>()
    val mutableList2 = intList2.toMutableList()

    for (number in intList1) {
        if (mutableList2.contains(number)) {
            intersectionList.add(number)
            mutableList2.remove(number)
        }
    }

    println("Пересечение массивов: $intersectionList")
}
fun task5() {
    fun readPositiveInt(prompt: String): Int? {
        print(prompt)
        val input = readlnOrNull() ?: return null
        if (input.isEmpty() || !input.all { it.isDigit() }) return null
        val value = input.toIntOrNull() ?: return null
        return if (value > 0) value else null
    }

    fun readNonEmptyString(prompt: String): String? {
        print(prompt)
        val input = readlnOrNull() ?: return null
        return if (input.isNotEmpty()) input else null
    }

    val count = readPositiveInt("Сколько слов вы введёте: ")
    if (count == null) {
        println("Ошибка: Введите положительное целое число для количества слов")
        return
    }

    println("Введите различные слова: ")
    val wordList = mutableListOf<String>()
    for (i in 0 until count) {
        var word: String?
        do {
            word = readNonEmptyString("Введите слово ${i + 1}: ")
            if (word == null) {
                println("Ошибка: Слово не может быть пустым")
            }
        } while (word == null)
        wordList.add(word)
    }

    println(wordList)
    val groupedByLetters = wordList.groupBy { word ->
        word.lowercase().toCharArray().sorted().joinToString("")
    }

    groupedByLetters.forEach { group ->
        println("Группа слов: ${group.value}")
        println("-".repeat(20))
    }
}

fun showMenu() {
    while (true) {
        println(
            "1) Программа запрашивает количество строк и столбцов для двухмерного массива.\n" +
                    "2) Программа создаёт квадратную матрицу разменостью 5 и определяет симметрична ли она относительно главной диагонали.\n" +
                    "3) Программа создаёт массив, содержащий символы русского алфавита. Символы алфавита нумеруются от 1 до 33.\n" +
                    "4) Программа принимает 2 массива, введённых пользователем. На выходе приложение выдаёт пересечение этих массивов.\n" +
                    "5) Программа принимает массив, на выходе приложение должно показать слова сгруппированные по признаку \"состоят из одинаковых букв\"."
        )
        print("Выбор: ")
        val choice = readln().toIntOrNull()
        when (choice) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            else -> println("Такого пункта нет")
        }
        println()
        print("Вы хотите продолжить выполнение программы (Да/Нет): ")
        if (readln().trim().lowercase().firstOrNull() == 'н') break
    }
}

fun main() {
    // showMenu()
    val mesaage = "1 2 3 "
    println(mesaage.trim())
    val uniqueDigits = mutableSetOf<Char>('5', '5')
    println(uniqueDigits)
}
