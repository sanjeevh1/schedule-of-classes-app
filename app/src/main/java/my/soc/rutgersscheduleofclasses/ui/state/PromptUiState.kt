package my.soc.rutgersscheduleofclasses.ui.state

//Stores the values entered by the user
data class PromptUiState(
    var year: String? = null,
    var term: String? = null,
    var campus: String? = null,
    var level: String? = null,
    var subject: String? = null
)
