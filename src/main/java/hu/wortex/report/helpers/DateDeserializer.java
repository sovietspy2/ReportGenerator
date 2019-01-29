//package hu.wortex.report.helpers;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//
//import java.util.Date;
//
//public class DateDeserializer extends JsonDeserializer<Date> {
//
//    private SimpleDateFormat dateFormat = new SimpleDateFormat(
//            "yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public Date deserialize(JsonParser paramJsonParser,
//                            DeserializationContext paramDeserializationContext)
//            throws IOException, JsonProcessingException {
//        String str = paramJsonParser.getText().trim();
//        try {
//            return dateFormat.parse(str);
//        } catch (ParseException e) {
//            // Handle exception here
//        }
//        return paramDeserializationContext.parseDate(str);
//    }
//}
