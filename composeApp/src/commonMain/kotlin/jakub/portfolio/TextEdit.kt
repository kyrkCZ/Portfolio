package jakub.portfolio

fun CharSequence.removeDiacritics(): String {
    val result = StringBuilder(this.length)
    for (char in this) {
        result.append(
            when (char) {
                'á', 'ä', 'à', 'â', 'ã', 'å' -> 'a'
                'Á', 'Ä', 'À', 'Â', 'Ã', 'Å' -> 'A'
                'é', 'ë', 'è', 'ê' -> 'e'
                'É', 'Ë', 'È', 'Ê' -> 'E'
                'í', 'ï', 'ì', 'î' -> 'i'
                'Í', 'Ï', 'Ì', 'Î' -> 'I'
                'ó', 'ö', 'ò', 'ô', 'õ' -> 'o'
                'Ó', 'Ö', 'Ò', 'Ô', 'Õ' -> 'O'
                'ú', 'ü', 'ù', 'û' -> 'u'
                'Ú', 'Ü', 'Ù', 'Û' -> 'U'
                'ñ' -> 'n'
                'Ñ' -> 'N'
                'ç' -> 'c'
                'Ç' -> 'C'
                else -> char
            }
        )
    }
    return result.toString()
}