package contacts

class Business(var name: String, var address: String) : Contact() {

    override fun toString(): String = name

    constructor(_name: String, _address: String, _phoneNumber: String) : this(_name, _address) {
        phoneNumber = _phoneNumber
    }

    override fun printInfo() {
        println("Organization name: $name")
        println("Address: $address")
        println("Number: $phoneNumber")
        println("Time created: ${createdTimestamp.toString().substring(0,16)}")
        println("Time last edit: ${updatedTimestamp.toString().substring(0,16)}")
    }
}