package com.atlanta.project.rest.response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class ResponseUser(
    @SerialName("id") val id: Long,
    @SerialName("username") val username: String,
    @SerialName("discriminator") val discriminator: String,
    @SerialName("avatar") val avatar: String?,
    @SerialName("bot") val isBot: Boolean? = null,
    @SerialName("system") val isSystem: Boolean? = null,
    @SerialName("mfa_enabled") val isMFAEnabled: Boolean? = null,
    @SerialName("locale") val locale: String? = null,
    @SerialName("verified") val verified: Boolean? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("flags") val flags: Int? = null,
    @SerialName("premium_type") val premium: PremiumType? = null,
    @SerialName("public_flags") val publicFlags: Int? = null
)

@Serializable(with = PremiumTypeSerializer::class)
enum class PremiumType(val id: Int) {

    NONE(0),
    CLASSIC(1),
    GAMER(2)

}

object PremiumTypeSerializer: KSerializer<PremiumType> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("premium_type", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): PremiumType = PremiumType.values().first { it.id == decoder.decodeInt() }

    override fun serialize(encoder: Encoder, value: PremiumType) = encoder.encodeInt(value.id)

}