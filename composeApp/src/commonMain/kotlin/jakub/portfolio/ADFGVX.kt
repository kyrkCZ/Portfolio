package jakub.portfolio

class ADFGVXCipher(private val keyword: String, private val useExtendedAlphabet: Boolean = false) {

    private val standardAlphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ"
    private val extendedAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private val adfgvx = "ADFGVX"

    private val polybiusSquare = if (useExtendedAlphabet) {
        generatePolybiusSquare(extendedAlphabet)
    } else {
        generatePolybiusSquare(standardAlphabet)
    }

    fun encode(plaintext: String): String {
        val substitutedText = plaintext.uppercase().map { char ->
            if (char in polybiusSquare) {
                val (row, col) = polybiusSquare[char]!!
                "${adfgvx[row]}${adfgvx[col]}"
            } else {
                "" // Ignore characters not in the alphabet
            }
        }.joinToString("")

        return transpose(substitutedText, keyword)
    }

    fun decode(ciphertext: String): String {
        val untransposedText = untranspose(ciphertext, keyword)

        return untransposedText.chunked(2).map { pair ->
            val row = adfgvx.indexOf(pair[0])
            val col = adfgvx.indexOf(pair[1])
            if (row != -1 && col != -1) {
                polybiusSquare.entries.find { it.value == (row to col) }?.key ?: '?'
            } else {
                '?' // Handle invalid pairs
            }
        }.joinToString("")
    }

    private fun generatePolybiusSquare(alphabet: String): Map<Char, Pair<Int, Int>> {
        val shuffledAlphabet = alphabet.toList().shuffled()
        val squareSize = if (useExtendedAlphabet) 6 else 5
        return shuffledAlphabet.mapIndexed { index, char ->
            char to (index / squareSize to index % squareSize)
        }.toMap()
    }

    private fun transpose(text: String, keyword: String): String {
        val numCols = keyword.length
        val numRows = (text.length + numCols - 1) / numCols
        val matrix = Array(numRows) { CharArray(numCols) { ' ' } }

        var index = 0
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                if (index < text.length) {
                    matrix[i][j] = text[index++]
                }
            }
        }

        val sortedKeyword = keyword.toCharArray().sortedArray().joinToString("")
        val colOrder = keyword.map { sortedKeyword.indexOf(it) }

        val transposedText = StringBuilder()
        for (j in colOrder) {
            for (i in 0 until numRows) {
                transposedText.append(matrix[i][j])
            }
        }

        return transposedText.toString()
    }

    private fun untranspose(text: String, keyword: String): String {
        val numCols = keyword.length
        val numRows = (text.length + numCols - 1) / numCols
        val matrix = Array(numRows) { CharArray(numCols) { ' ' } }

        val sortedKeyword = keyword.toCharArray().sortedArray().joinToString("")
        val colOrder = keyword.map { sortedKeyword.indexOf(it) }

        var index = 0
        for (j in colOrder) {
            for (i in 0 until numRows) {
                if (index < text.length) {
                    matrix[i][j] = text[index++]
                }
            }
        }

        val untransposedText = StringBuilder()
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                untransposedText.append(matrix[i][j])
            }
        }

        return untransposedText.toString().trim()
    }
}