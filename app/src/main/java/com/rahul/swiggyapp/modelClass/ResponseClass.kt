package com.rahul.swiggyapp.modelClass

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class ResponseClass(

	@field:SerializedName("Search")
	val search: List<SearchClass?>? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("Response")
	val response: String? = null
)