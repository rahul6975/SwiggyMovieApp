package com.rahul.swiggyapp.modelClass

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class SearchClass(

	@field:SerializedName("Title")
	val title: String? = null,

	@field:SerializedName("Year")
	val year: String? = null,

	@field:SerializedName("imdbID")
	val imdbID: String? = null,

	@field:SerializedName("Type")
	val type: String? = null,

	@field:SerializedName("Poster")
	val poster: String? = null
)