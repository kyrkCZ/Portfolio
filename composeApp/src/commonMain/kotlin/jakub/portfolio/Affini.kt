package jakub.portfolio

/**
 * Affine Cipher implementation - a type of monoalphabetic substitution cipher.
 * 
 * The affine cipher is a mathematical encryption method that uses the formula:
 * E(x) = (ax + b) mod m
 * where 'a' and 'b' are the keys, 'm' is the alphabet size, and 'x' is the letter's position.
 * 
 * For decryption: D(y) = a^(-1) * (y - b) mod m
 * where a^(-1) is the modular multiplicative inverse of 'a'.
 * 
 * @property alphabet The alphabet to use for encryption (default: a-z + 0-9)
 */
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
        return input.mapIndexed { index, char ->
            val ciphered = cipherChar(char, a, b).toString()
            if ((index + 1) % 5 == 0 && index < input.length - 1) "$ciphered " else ciphered
        }.joinToString("").uppercase()
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
        var x = a
        var y = b
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }
}