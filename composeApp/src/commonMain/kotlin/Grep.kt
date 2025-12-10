/**
 * A simple grep-like utility function for filtering lines based on a regex pattern.
 * 
 * This function filters a list of strings based on a regex pattern and applies
 * an action to each matching line.
 * 
 * @param lines The list of strings to search through
 * @param pattern The regex pattern to match against
 * @param action The action to perform on each matching line
 */
fun grep(lines: List<String>, pattern: String, action: (String) -> Unit) {
    val regex = pattern.toRegex()
    lines.filter(regex::containsMatchIn)
        .forEach(action)
}