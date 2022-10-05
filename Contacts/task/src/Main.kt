package contacts

fun main() {
    val filePath = "phonebook.json"

    val phoneBook = PhoneBook()
    //phoneBook.diskData("load", filePath)

    do {

        println("\n[menu] Enter action (add, list, search, count, exit):")

        when(readln().lowercase()) {
            "add" -> phoneBook.addContact()
            "list" -> phoneBook.listAllContacts()
            "search" -> phoneBook.searchForContact()
            "count" -> println("The Phone Book has ${phoneBook.countContacts()} records.")
            "exit" -> {
                phoneBook.diskData("save", filePath)
                return
            }
            else -> println("Unknown command! Please made a valid selection!")
        }

    } while (true)
}