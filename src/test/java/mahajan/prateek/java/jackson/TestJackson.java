package mahajan.prateek.java.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by: pramahajan on 5/6/20 8:19 PM GMT+05:30
 */
public class TestJackson {

    @Test
    public void test1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Map<String, Object> values = new HashMap<>();
        Map<String, String> threshold = new LinkedHashMap<>();
        threshold.put("approvalRequired", "true");
        threshold.put("approvalProcessType", "POST BOOKING");
        values.put("threshold", threshold);

        String lll = objectMapper.writeValueAsString(values);
        System.out.println(lll);
    }

    @Test
    public void test2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Map<String, Object> values = new HashMap<>();
        Threshold threshold = new Threshold("true", "POST BOOKING");
        values.put("threshold", threshold);

        String lll = objectMapper.writeValueAsString(values);
        System.out.println(lll);
    }


    private static class Threshold {
        private String approvalRequired;
        private String approvalProcessType;

        public Threshold(String approvalRequired, String approvalProcessType) {
            this.approvalRequired = approvalRequired;
            this.approvalProcessType = approvalProcessType;
        }

        public String getApprovalRequired() {
            return approvalRequired;
        }

        public void setApprovalRequired(String approvalRequired) {
            this.approvalRequired = approvalRequired;
        }

        public String getApprovalProcessType() {
            return approvalProcessType;
        }

        public void setApprovalProcessType(String approvalProcessType) {
            this.approvalProcessType = approvalProcessType;
        }
    }
}
