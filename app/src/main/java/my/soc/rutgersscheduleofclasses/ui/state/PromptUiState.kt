package my.soc.rutgersscheduleofclasses.ui.state

data class PromptUiState(
    var year: String? = null,
    var term: String? = null,
    var campus: String? = null,
    var level: String? = null,
    var subject: String? = null
)
