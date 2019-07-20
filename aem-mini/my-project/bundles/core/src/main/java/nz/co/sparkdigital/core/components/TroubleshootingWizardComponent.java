package nz.co.sparkdigital.core.components;

import com.adobe.cq.sightly.WCMUsePojo;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

import javax.jcr.Node;

public class TroubleshootingWizardComponent extends WCMUsePojo {

    protected static final String QUESTIONS_PROPERTY_NAME = "troubleshootingquestions";

    private List<TroubleShootingQuestion> troubleShootingQuestions;

    @Override
    public void activate() throws Exception {
        final Node currentNode = getResource().adaptTo(Node.class);
        if (currentNode != null && currentNode.hasProperty(QUESTIONS_PROPERTY_NAME)) {
            troubleShootingQuestions = SparkDigitalCommonUtils.mapPropertiesToObjects(currentNode, QUESTIONS_PROPERTY_NAME, TroubleShootingQuestion.class);
            final Map<String, List<TroubleShootingOption>> questionIdTroubleshootingOptions = troubleShootingQuestions.stream()
                    .collect(Collectors.toMap(TroubleShootingQuestion::getTroubleshootingQuestionId, TroubleShootingQuestion::getTroubleShootingOptionsList));
            questionIdTroubleshootingOptions.keySet().iterator().forEachRemaining(questionId ->{
                final LongAdder count = new LongAdder();
                questionIdTroubleshootingOptions.get(questionId).stream().forEach(option -> {
                    if(StringUtils.isEmpty(option.getTroubleshootingOptionLink())) {
                        option.setTroubleshootingOptionLink(StringUtils.defaultIfEmpty(questionId, "") + "TROUBLESHOOTING" + count);
                        count.increment();
                    }
                });
            });
        }
    }

    public List<TroubleShootingQuestion> getTroubleShootingQuestions() {
        return troubleShootingQuestions;
    }
}
