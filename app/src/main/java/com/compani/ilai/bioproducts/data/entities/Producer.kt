package com.compani.ilai.bioproducts.data.entities

data class Producer(
    val producerId: String = "",
    val fullName: String = "",
    val address: String = "",
    val phone: Int,
    val shortBio: String = "",
    val imageUrl: ByteArray?,
    val productType: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Producer

        if (producerId != other.producerId) return false
        if (fullName != other.fullName) return false
        if (address != other.address) return false
        if (phone != other.phone) return false
        if (shortBio != other.shortBio) return false
        if (!imageUrl.contentEquals(other.imageUrl)) return false
        if (productType != other.productType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = producerId.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + phone
        result = 31 * result + shortBio.hashCode()
        result = 31 * result + imageUrl.contentHashCode()
        result = 31 * result + productType.hashCode()
        return result
    }
}