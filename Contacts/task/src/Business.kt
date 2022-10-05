package contacts

class Business(var name: String, var address: String) : Contact() {

    override val type = "BusinessType"

    override fun toString(): String = name

    override fun searchString(): String = "$name + $address + $phoneNumber"

    constructor(_name: String, _address: String, _phoneNumber: String) : this(_name, _address) {
        phoneNumber = _phoneNumber
    }

    override fun printInfo() {
        println("Organization name: $name")
        println("Address: $address")
        println("Number: $phoneNumber")
        println("Time created: $createdTimestamp")
        println("Time last edit: $updatedTimestamp")
    }
}