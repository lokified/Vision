package com.loki.britam.util

import com.loki.britam.R
import com.loki.britam.presentation.insurance.Insurance

object NavigationUtils {



    fun applyContentScreen(title: String): Insurance {

        return when(title) {

            "Britam Biashara" -> Insurance(
                content = R.string.britam_biashara_content,
                coverage = listOf(
                    "Fire (compulsory section)",
                    "Burglary (optional)",
                    "All Risks (optional)",
                    "Money (optional)",
                    "Goods in Transit – GIT (optional)",
                    "Work Injury Benefit - WIBA (optional)",
                    "Public Liability (optional)"
                )
            )

            "Theft Insurance" -> Insurance(
                content = R.string.theft_insurance_content,
                coverage = listOf()
            )

            "Cyber Insurance" -> Insurance(
                content = R.string.cyber_insurance_content,
                coverage = listOf()
            )

            "Fire Insurance" -> Insurance(
                content = R.string.fire_insurance_content,
                coverage = listOf(
                    "Industrial All Risk",
                    "Stock Floater",
                    "Loss of Profit",
                    "Fire and Perils"
                )
            )

            "Liability Insurance" -> Insurance(
                content = R.string.liability_insurance_content,
                coverage = listOf(
                    "Directors and Officer’s Liability",
                    "Carriers Liability",
                    "Trustee`s Liability",
                    "Public liability",
                    "Product liability",
                    "Employer’s liability"
                )
            )

            "Political Violence and Terrorism Insurance" -> Insurance(
                content = R.string.political_insurance_content,
                coverage = listOf()
            )

            "Home Insurance" -> Insurance(
                content = R.string.home_insurance_content,
                coverage = listOf(
                    "Covers loss or damage to personal belongings like furniture, household goods, electrical equipment and other personal items if they are stolen, destroyed by fire or other insured event",
                    "Covers items that are taken out of the house like jewelry, laptop computers, mobile phones, cameras etc.",
                    "Two domestic workers are covered for free"
                ),
                isPersonal = true
            )

            "Britam Motor Insurance" -> Insurance(
                content = R.string.motor_insurance_content,
                coverage = listOf(
                    "Free cover for windscreen, theft of audio and video accessories",
                    "Free cover for spare wheel, jack and toolbox",
                    "Free cover for protection, recovery & towing after accident"
                ),
                isPersonal = true
            )

            "Fire And Burglary" -> Insurance(
                content = R.string.burglary_content,
                coverage = listOf(),
                isPersonal = true
            )

            "Accident Insurance" -> Insurance(
                content = R.string.accident_content,
                coverage = listOf(
                    "Group Personal Accident",
                    "Student Personal Accident"
                ),
                isPersonal = true
            )

            "Family Protection Plans" -> Insurance(
                content = R.string.family_plans_content,
                coverage = listOf(
                    "Tegemeo",
                    "Family Income",
                    "Critical Illness",
                    "Cancer Plan"
                ),
                isPersonal = true
            )

            "Funeral Covers" -> Insurance(
                content = R.string.funeral_content,
                coverage = listOf(
                    "Fariji",
                    "Family Funeral"
                ),
                isPersonal = true
            )

            else -> Insurance(
                content = R.string.unknown_content,
                coverage = listOf()
            )
        }
    }
}