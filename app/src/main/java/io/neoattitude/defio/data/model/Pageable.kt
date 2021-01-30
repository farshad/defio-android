package io.neoattitude.defio.data.model

import com.google.gson.annotations.SerializedName

data class Pageable(
	val sort: Sort,
	val offset: Int,
	val pageSize: Int,
	val pageNumber: Int,
	val paged: Boolean,
	@SerializedName("unpaged") val unPaged: Boolean
)