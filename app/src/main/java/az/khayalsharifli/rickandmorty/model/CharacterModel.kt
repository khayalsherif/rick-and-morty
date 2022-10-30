package az.khayalsharifli.rickandmorty.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class CharacterModel(
    val info: Info,
    val results: List<CharacterResult>
) : Parcelable

@Parcelize
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
) : Parcelable

@Parcelize
data class CharacterResult(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class Location(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
data class Origin(
    val name: String,
    val url: String
) : Parcelable

