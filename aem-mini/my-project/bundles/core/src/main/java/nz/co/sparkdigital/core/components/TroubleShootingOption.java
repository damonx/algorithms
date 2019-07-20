package nz.co.sparkdigital.core.components;

import com.google.gson.annotations.SerializedName;

public class TroubleShootingOption {
    @SerializedName("troubleshootingoption")
    private String troubleshootingOption;
    @SerializedName("troubleshootingoptionlink")
    private String troubleshootingOptionLink;
    @SerializedName("troubleshootingresult")
    private String troubleShootingResult;
    @SerializedName("troubleshootingpagelink")
    private String troubleshootingPageLink;

    public String getTroubleshootingOption() {
        return troubleshootingOption;
    }

    public void setTroubleshootingOption(final String troubleshootingOption) {
        this.troubleshootingOption = troubleshootingOption;
    }

    public String getTroubleshootingOptionLink() {
        return troubleshootingOptionLink;
    }

    public void setTroubleshootingOptionLink(final String troubleshootingOptionLink) {
        this.troubleshootingOptionLink = troubleshootingOptionLink;
    }

    public String getTroubleShootingResult() {
        return troubleShootingResult;
    }

    public void setTroubleShootingResult(final String troubleShootingResult) {
        this.troubleShootingResult = troubleShootingResult;
    }

    public String getTroubleshootingPageLink() {
        return troubleshootingPageLink;
    }

    public void setTroubleshootingPageLink(final String troubleshootingPageLink) {
        this.troubleshootingPageLink = troubleshootingPageLink;
    }
}
