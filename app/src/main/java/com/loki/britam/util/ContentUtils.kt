package com.loki.britam.util

import com.loki.britam.R
import com.loki.britam.presentation.products.Content

object ContentUtils {



    fun applyContentScreen(title: String): Content {

        return when(title) {

            "Britam Biashara" -> Content(
                header = R.string.britam_biashara_content,
                benefits = listOf(
                    "Fire (compulsory section)",
                    "Burglary (optional)",
                    "All Risks (optional)",
                    "Money (optional)",
                    "Goods in Transit – GIT (optional)",
                    "Work Injury Benefit - WIBA (optional)",
                    "Public Liability (optional)"
                ),
                url = "https://ke.britam.com/home/business/protect-your-business/britam-biashara"
            )

            "Theft Insurance" -> Content(
                header = R.string.theft_insurance_content,
                benefits = listOf(),
                url = "https://ke.britam.com/home/business/protect-your-business/theft-insurance"
            )

            "Cyber Insurance" -> Content(
                header = R.string.cyber_insurance_content,
                benefits = listOf(),
                url = "https://ke.britam.com/home/business/protect-your-business/cyber-insurance"
            )

            "Fire Insurance" -> Content(
                header = R.string.fire_insurance_content,
                benefits = listOf(
                    "Industrial All Risk",
                    "Stock Floater",
                    "Loss of Profit",
                    "Fire and Perils"
                ),
                url = "https://ke.britam.com/home/personal/protect-what-you-love/fire-and-burglary"
            )

            "Liability Insurance" -> Content(
                header = R.string.liability_insurance_content,
                benefits = listOf(
                    "Directors and Officer’s Liability",
                    "Carriers Liability",
                    "Trustee`s Liability",
                    "Public liability",
                    "Product liability",
                    "Employer’s liability"
                ),
                url = "https://ke.britam.com/home/business/protect-your-business/liability-insurance"
            )

            "Political Violence and Terrorism Insurance" -> Content(
                header = R.string.political_insurance_content,
                benefits = listOf(),
                url = "https://ke.britam.com/home/business/protect-your-business/political-violence-terrorism-insurance"
            )

            "Home Insurance" -> Content(
                header = R.string.home_insurance_content,
                benefits = listOf(
                    "Covers loss or damage to personal belongings like furniture, household goods, electrical equipment and other personal items if they are stolen, destroyed by fire or other insured event",
                    "Covers items that are taken out of the house like jewelry, laptop computers, mobile phones, cameras etc.",
                    "Two domestic workers are covered for free"
                ),
                isPersonal = true,
                url = "https://ke.britam.com/home/personal/protect-what-you-love/home-insurance"
            )

            "Britam Motor Insurance" -> Content(
                header = R.string.motor_insurance_content,
                benefits = listOf(
                    "Free cover for windscreen, theft of audio and video accessories",
                    "Free cover for spare wheel, jack and toolbox",
                    "Free cover for protection, recovery & towing after accident"
                ),
                isPersonal = true,
                url = "https://ke.britam.com/home/personal/protect-what-you-love/britam-motor-insurance"
            )

            "Fire And Burglary" -> Content(
                header = R.string.burglary_content,
                benefits = listOf(),
                isPersonal = true,
                url = "https://ke.britam.com/home/personal/protect-what-you-love/fire-and-burglary"
            )

            "Accident Insurance" -> Content(
                header = R.string.accident_content,
                benefits = listOf(
                    "Group Personal Accident",
                    "Student Personal Accident"
                ),
                isPersonal = true,
                url = "https://ke.britam.com/home/personal/protect-who-you-love/personal-accident-covers"
            )

            "Family Protection Plans" -> Content(
                header = R.string.family_plans_content,
                benefits = listOf(
                    "Tegemeo",
                    "Family Income",
                    "Critical Illness",
                    "Cancer Plan"
                ),
                isPersonal = true,
                url = "https://ke.britam.com/home/personal/protect-who-you-love/family-protection-plans"
            )

            "Funeral Covers" -> Content(
                header = R.string.funeral_content,
                benefits = listOf(
                    "Fariji",
                    "Family Funeral"
                ),
                isPersonal = true,
                url = "https://ke.britam.com/home/personal/protect-who-you-love/funeral-covers"
            )

            "Money Market Fund" -> Content(
                header = R.string.mmf_content,
                benefits = listOf(
                    "Low risk investment",
                    "Suitable for a 1 year investment horizon",
                    "Minimum investment of Kshs. 1,000",
                    "Easily track your savings and transact via our digital channels"
                ),
                url = "https://ke.britam.com/save-and-invest/business/invest/unit-trust-funds/money-market-fund"
            )

            "Britam Bond Plus Fund" -> Content(
                header = R.string.bond_plus_content,
                benefits = listOf(
                    "It is suitable for investors seeking a regular income from their investment periodically, ideally after a 2 – 3-year period.",
                    "The yields are not guaranteed, and past performance does not guarantee future investment performance",
                ),
                url = "https://ke.britam.com/save-and-invest/personal/invest/unit-trust-funds/bond-plus-fund"
            )

            "Balanced Fund" -> Content(
                header = R.string.balanced_content,
                benefits = listOf(),
                url = "https://ke.britam.com//save-and-invest/business/invest/unit-trust-funds/balanced-fund"
            )

            "Equity Fund" -> Content(
                header = R.string.equity_content,
                benefits = listOf(),
                url = "https://ke.britam.com//save-and-invest/business/invest/unit-trust-funds/equity-fund"
            )

            "Endowment Funds" -> Content(
                header = R.string.endowmend_content,
                benefits = listOf(
                    "A portion of donations can be used as capital or seed investment.",
                    "Returns received regularly can be used to fund organisation operations.",
                    "The principle remains untouched throughout the investment term."
                ),
                url = "https://ke.britam.com/save-and-invest/business/invest/endowment-fund"
            )

            "Gratuity Funds" -> Content(
                header = R.string.gratuity_content,
                benefits = listOf(
                    "Valuable part of your overall remuneration package",
                    "Supports the company’s recruitment and retention policies and goals.",
                    "Deliverable at a cost acceptable to the company",
                    "Benefits provided by the scheme compares favourably with peers."
                ),
                url = "https://ke.britam.com/save-and-invest/business/invest/gratuity-fund"
            )

            else -> Content(
                header = R.string.unknown_content,
                benefits = listOf(),
                url = "https://ke.britam.com/"
            )
        }
    }
}