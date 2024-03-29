package com.hughod.app.ui.detail

import android.content.Intent
import android.net.Uri
import com.hughod.app.ui.ext.decimalFormat
import com.hughod.interaction.entities.DetailEntity

data class DetailModel(
    val id: Int,
    val imdbId: String,
    val imagePath: String,
    val caption: String,

    val title: String,
    val description: String,

    val extraInfo: String,

    val genres: String,
    val productionCompanies: String,
    val budgetAndRevenue: String,
    val homepage: Intent
) {

    companion object {
        fun fromDomain(domain: DetailEntity): DetailModel = DetailModel(
            id = domain.id,
            imdbId = domain.imdbId,
            title = domain.name,
            caption = domain.tagLine,
            description = domain.overview,
            extraInfo = "${domain.releaseDate} - ${domain.runtime} minutes",
            genres = domain.genres.joinToString(separator = " - ") { it.name },
            imagePath = domain.backdropPath,
            productionCompanies = domain.productionCompanies.joinToString(separator = ", \n") { it.name },
            budgetAndRevenue = getBudgetAndRevenue(domain),
            homepage = Intent().apply {
                this.action = Intent.ACTION_VIEW
                this.data = Uri.parse(domain.homepage)
            }
        )

        private fun getBudgetAndRevenue(domain: DetailEntity): String {
            val sb = StringBuilder("")

            if (domain.hasBudget()) {
                sb.append("Budget: £${domain.budget.decimalFormat()}")
            }

            if (domain.hasBudget() && domain.hasRevenue()) {
                sb.append(";\n")
            }

            if (domain.hasRevenue()) {
                sb.append("Revenue: £${domain.revenue.decimalFormat()}")
            }

            return sb.toString()
        }
    }
}
