package nz.co.sparkdigital.core.components;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TroubleShootingQuestion {

    @SerializedName("question")
    private String troubleshootingQuestion;
    @SerializedName("questionid")
    private String troubleshootingQuestionId;
    @SerializedName("troubleshootingoptions")
    private List<TroubleShootingOption> troubleShootingOptionsList;

    public String getTroubleshootingQuestion() {
        return troubleshootingQuestion;
    }

    public void setTroubleshootingQuestion(final String troubleshootingQuestion) {
        this.troubleshootingQuestion = troubleshootingQuestion;
    }

    public String getTroubleshootingQuestionId() {
        return troubleshootingQuestionId;
    }

    public void setTroubleshootingQuestionId(final String troubleshootingQuestionId) {
        this.troubleshootingQuestionId = troubleshootingQuestionId;
    }

    public List<TroubleShootingOption> getTroubleShootingOptionsList() {
        return troubleShootingOptionsList;
    }

    public void setTroubleShootingOptionsList(final List<TroubleShootingOption> troubleShootingOptionsList) {
        this.troubleShootingOptionsList = troubleShootingOptionsList;
    }
}
