package jakub.portfolio

class AffineCipher(val alphabet: String = ('a'..'z').joinToString("") + ('0'..'9').joinToString("")) {
    val m = alphabet.length
    val alphabetArray = alphabet.toCharArray()


    fun mod(int: Int, mod: Int): Int {
        var result = int % mod
        if (result < 0) {
            result += mod
        }
        return result
    }

    fun encode(input: String, a: Int, b: Int): String {
        var counter = 1
        var output = ""
        for (char in input) {
            output += cipherChar(char, a, b).toString()
            if (counter == 5) {
                output += " "
                counter = 0
            }
            counter++
        }
        return output.uppercase()
    }

    fun cipherChar(char: Char, a: Int, b: Int): Char {
        val defaultIndex = alphabet.indexOf(char.lowercaseChar())
        val index = mod((a * defaultIndex + b), m)
        return alphabetArray[index]
    }

    fun decode(input: String, a: Int, b: Int): String {
        val mmiA = mmi(a, m) // Modular Multiplicative Inverse of 'a' modulo 'm'
        val decoded = StringBuilder()

        val inputWithoutSpaces = input.replace(" ", "") // Remove spaces

        for (char in inputWithoutSpaces) {
            if (char.isLetterOrDigit()) {
                val index = alphabet.indexOf(char.lowercaseChar())
                // Decoding using the formula: y = a^(-1) * (x - b) mod m
                val decodedIndex = (mmiA * (index - b + m)) % m
                decoded.append(if (decodedIndex < alphabet.length) alphabetArray[decodedIndex] else (decodedIndex - alphabet.length + '0'.code).toChar())
            }
        }

        return decoded.toString()
    }

    fun mmi(a: Int, b: Int): Int = (1 until b).dropWhile { (a * it) % b != 1 }.firstOrNull() ?: 1

    fun gcd(a: Int, b: Int): Int {
        var gcd = 1
        generateSequence(1) { it + 1 }
            .takeWhile { it <= a && it <= b }
            .forEach { if (a % it == 0 && b % it == 0) gcd = it }
        return gcd
    }
}